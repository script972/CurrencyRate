package com.script972.currencyrate.domain.repository.impl;

import android.os.AsyncTask;

import com.script972.currencyrate.api.model.CurrencyResponce;
import com.script972.currencyrate.api.services.CurrencyApiService;
import com.script972.currencyrate.core.CurrencyApplication;
import com.script972.currencyrate.domain.database.entity.CurrencySelectValue;
import com.script972.currencyrate.managers.RetrofitManager;
import com.script972.currencyrate.domain.database.entity.CurrencyEntity;
import com.script972.currencyrate.domain.database.entity.CurrencyValueEntity;
import com.script972.currencyrate.domain.repository.CurrencyRepository;
import com.script972.currencyrate.mappers.MapperCurrencyCommon;
import com.script972.currencyrate.ui.model.CurrencyValueModel;
import com.script972.currencyrate.utils.DateUtils;
import com.script972.currencyrate.managers.DatabaseManager;
import com.script972.currencyrate.utils.SharedPreferencesUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CurrencyRepositoryImpl implements CurrencyRepository {

    private CurrencyApiService apiService;
    private DatabaseManager db;

    public CurrencyRepositoryImpl() {
        this.apiService = RetrofitManager.getInstance()
                .apiRetrofit.create(CurrencyApiService.class);
        db = DatabaseManager.getInstance();
    }

    @Override
    public LiveData<List<CurrencySelectValue>> findAllCurrencyForToday() {
        final MutableLiveData<List<CurrencySelectValue>> liveData = new MutableLiveData<>();
        db.getCurrencyValueDao().getValuesForCurrency(DateUtils.entityDateFromCalendar(
                Calendar.getInstance())).observeForever(liveData::setValue);
        Call<List<CurrencyResponce>> call = apiService.getAllCurrencyForToday();
        call.enqueue(new Callback<List<CurrencyResponce>>() {
            @Override
            public void onResponse(Call<List<CurrencyResponce>> call, Response<List<CurrencyResponce>> response) {
                updaAllDatabaseCurrencyIfNeed(response.body());
                SharedPreferencesUtils.setLastSyncDate(CurrencyApplication.getApplication(), Calendar.getInstance().getTimeInMillis());
            }

            @Override
            public void onFailure(Call<List<CurrencyResponce>> call, Throwable t) {
            }
        });
        return liveData;
    }

    private void updaAllDatabaseCurrencyIfNeed(List<CurrencyResponce> currencyResponceList) {
        new FillCurrency(currencyResponceList).execute();
    }

    @Override
    public LiveData<List<CurrencyValueModel>> findAllCurrencyForDates(List<Long> dateQuery, String currencyValue) {
        long startDate = dateQuery.get(0);
        long endDate = dateQuery.get(dateQuery.size() - 1);
        new CurrencyValueForDates(db, dateQuery, currencyValue).execute();
        LiveData<List<CurrencyValueEntity>> listLiveData = db.getCurrencyValueDao().getValuesForCurrency(currencyValue, startDate, endDate);
        return Transformations.map(listLiveData, input -> {
            List<CurrencyValueModel> outData = new ArrayList<>();
            for (int i = 0; i < input.size(); i++) {
                outData.add(MapperCurrencyCommon.MapperCurrencyValue.mapDbToUi(input.get(i)));
            }
            return outData;
        });
    }

    @Override
    public void changeFavorite(String currency) {
        //TODO
    }

    /**
     * Insert currency value list to database for dates if need
     */
    class CurrencyValueForDates extends AsyncTask<Void, Void, Void> {

        private List<Long> dateQuery;
        private String currencyValue;
        private DatabaseManager db;

        public CurrencyValueForDates(DatabaseManager db, List<Long> dateQuery, String currencyValue) {
            this.db = db;
            this.dateQuery = dateQuery;
            this.currencyValue = currencyValue;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            for (int i = 0; i < dateQuery.size(); i++) {
                Call<List<CurrencyResponce>> call = apiService.getOnlyCurrencyForDate(
                        DateUtils.soutDateForApi(dateQuery.get(i)), currencyValue);
                try {
                    CurrencyValueEntity entity = MapperCurrencyCommon.MapperCurrencyValue.mapNetworkToDb(call.execute()
                            .body().get(0));
                    entity.setCurrencyId(db.getCurrencyDao().getByShortValue(currencyValue).getId());
                    if (db.getCurrencyValueDao().getItemCurrencyForDate(dateQuery.get(i), entity.getCurrencyId()) == null) {
                        db.getCurrencyValueDao().insert(entity);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
        }
    }

    /**
     * Insert currency  list to database if need
     */
    class FillCurrency extends AsyncTask<Void, Void, Void> {

        List<CurrencyResponce> data;

        public FillCurrency(List<CurrencyResponce> currencyResponceList) {
            this.data = currencyResponceList;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
        }

        @Override
        protected Void doInBackground(Void... voids) {
            List<CurrencyEntity> list = DatabaseManager.getInstance().getCurrencyDao().getAllList();
            //TODO check every item equels. Not size
            if (list == null || list.size() == 0) {
                for (int i = 0; i < data.size(); i++) {
                    //fill currency
                    db.getCurrencyDao().insert(MapperCurrencyCommon.MapperCurrency.mapNetworkToDb(data.get(i)));
                }
            }

            for (int i = 0; i < data.size(); i++) {
                CurrencyEntity curr = db.getCurrencyDao().getByShortValue(data.get(i).getTitleShort());
                if (existingDataForCurrencyAtDate(curr, DateUtils.entityDate(data.get(i).getDate()))) {
                    continue;
                }
                CurrencyValueEntity currencyValue = MapperCurrencyCommon.MapperCurrencyValue
                        .responceToEntity(curr, data.get(i));
                db.getCurrencyValueDao().insert(currencyValue);
            }
            return null;
        }

        private boolean existingDataForCurrencyAtDate(CurrencyEntity curr, long entityDate) {
            return db.getCurrencyValueDao().getValueForCurrencyAndDate(curr.getId(), entityDate) != null;
        }
    }

}


