package com.script972.currencyrate.domain.repository.impl;

import android.os.AsyncTask;
import android.util.Log;

import com.script972.currencyrate.domain.api.model.CurrencyResponce;
import com.script972.currencyrate.domain.api.services.CurrencyApiService;
import com.script972.currencyrate.core.CurrencyApplication;
import com.script972.currencyrate.domain.database.dao.CurrencyDao;
import com.script972.currencyrate.domain.database.dao.CurrencyValueDao;
import com.script972.currencyrate.ui.model.CurrencySelectValue;
import com.script972.currencyrate.managers.RetrofitManager;
import com.script972.currencyrate.domain.database.entity.CurrencyEntity;
import com.script972.currencyrate.domain.database.entity.CurrencyValueEntity;
import com.script972.currencyrate.domain.repository.CurrencyRepository;
import com.script972.currencyrate.mappers.MapperCurrencyCommon;
import com.script972.currencyrate.ui.model.CurrencyValueModel;
import com.script972.currencyrate.utils.DateUtils;
import com.script972.currencyrate.managers.DatabaseManager;
import com.script972.currencyrate.utils.SharedPreferencesUtils;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.Transformations;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CurrencyRepositoryImpl implements CurrencyRepository {

    private final CurrencyApiService apiService;
    private final CurrencyValueDao currencyValueDao;
    private final CurrencyDao currencyDao;

    public CurrencyRepositoryImpl() {
        this.apiService = RetrofitManager.getInstance()
                .apiRetrofit.create(CurrencyApiService.class);
        this.currencyValueDao = DatabaseManager.getInstance().getCurrencyValueDao();
        this.currencyDao = DatabaseManager.getInstance().getCurrencyDao();
    }

    @Override
    public LiveData<List<CurrencySelectValue>> findAllCurrencyForToday() {
        // Call<List<CurrencyResponce>> call = apiService.getAllCurrencyForToday();
        Call<List<CurrencyResponce>> call = apiService
                .getAllCurrencyForDate(DateUtils.soutDateForApi(Calendar.getInstance().getTimeInMillis()));
        call.enqueue(new Callback<List<CurrencyResponce>>() {
            @Override
            public void onResponse(@NotNull Call<List<CurrencyResponce>> call,
                                   @NotNull Response<List<CurrencyResponce>> response) {
                updaAllDatabaseCurrencyIfNeed(response.body());
            }

            @Override
            public void onFailure(@NotNull Call<List<CurrencyResponce>> call, @NotNull Throwable t) {
            }
        });
        return currencyValueDao.getValuesForDate(DateUtils.entityDateFromCalendar(Calendar.getInstance()));
    }

    private void updaAllDatabaseCurrencyIfNeed(List<CurrencyResponce> currencyResponceList) {
        new FillCurrency(currencyResponceList, currencyDao, currencyValueDao).execute();
    }

    @Override
    public LiveData<List<CurrencyValueModel>> findAllCurrencyForDates(List<Long> dateQuery, String currencyValue) {
        long startDate = dateQuery.get(0);
        long endDate = dateQuery.get(dateQuery.size() - 1);
        new CurrencyValueForDates(currencyDao, currencyValueDao, apiService, dateQuery, currencyValue)
                .execute();
        LiveData<List<CurrencyValueEntity>> listLiveData = currencyValueDao.getValuesForDate(currencyValue, startDate, endDate);
        return Transformations.map(listLiveData, input -> {
            List<CurrencyValueModel> outData = new ArrayList<>();
            for (int i = 0; i < input.size(); i++) {
                outData.add(MapperCurrencyCommon.MapperCurrencyValue.mapDbToUi(input.get(i)));
            }
            return outData;
        });
    }

    //TODO implement
    @Override
    public void changeFavorite(String currency) {
        currencyDao.markAsFavorite(currency);
    }

    /**
     * Insert currency value list to database for dates if need
     */
    class CurrencyValueForDates extends AsyncTask<Void, Void, Void> {

        private List<Long> dateQuery;
        private String currencyValue;
        private final CurrencyApiService apiService;
        private final CurrencyDao currencyDao;
        private final CurrencyValueDao currencyValueDao;

        CurrencyValueForDates(CurrencyDao currencyDao, CurrencyValueDao currencyValueDao,
                              CurrencyApiService apiService,
                              List<Long> dateQuery, String currencyValue) {
            this.currencyDao = currencyDao;
            this.currencyValueDao = currencyValueDao;
            this.apiService = apiService;
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

                CurrencyValueEntity entity;
                try {
                    List<CurrencyResponce> responce = call.execute().body();
                    if (!(responce.size() == 0)) {
                        entity = MapperCurrencyCommon.MapperCurrencyValue.mapNetworkToDb(
                                responce.get(0));
                        entity.setCurrencyId(currencyDao.getByShortValue(currencyValue).getId());
                        if (currencyValueDao.getItemCurrencyForDate(dateQuery.get(i),
                                entity.getCurrencyId()) == null) {
                            currencyValueDao.insert(entity);
                        }
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

        private List<CurrencyResponce> data;
        private final CurrencyDao currencyDao;
        private final CurrencyValueDao currencyValueDao;

        FillCurrency(List<CurrencyResponce> currencyResponceList,
                     CurrencyDao currencyDao, CurrencyValueDao currencyValueDao) {
            this.data = currencyResponceList;
            this.currencyDao = currencyDao;
            this.currencyValueDao = currencyValueDao;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            List<CurrencyEntity> list = DatabaseManager.getInstance().getCurrencyDao().getAllList();
            //TODO check every item equels. Not size
            if (list == null || list.size() == 0) {
                //fill currency
                List<CurrencyEntity> toDb = new ArrayList<>();
                for (int i = 0; i < data.size(); i++) {
                    toDb.add(MapperCurrencyCommon.MapperCurrency.mapNetworkToDb(data.get(i)));
                }
                currencyDao.insertAll(toDb);
            }

            List<CurrencyValueEntity> toDb = new ArrayList<>();
            for (int i = 0; i < data.size(); i++) {
                CurrencyEntity curr = currencyDao.getByShortValue(data.get(i).getTitleShort());
                if (existingDataForCurrencyAtDate(curr, DateUtils.entityDate(data.get(i).getDate()))) {
                    continue;
                }
                toDb.add(MapperCurrencyCommon.MapperCurrencyValue
                        .responceToEntity(curr, data.get(i)));
            }
            currencyValueDao.insertList(toDb);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            SharedPreferencesUtils.setLastSyncDate(CurrencyApplication.getApplication(), Calendar.getInstance().getTimeInMillis());
            super.onPostExecute(aVoid);
        }

        private boolean existingDataForCurrencyAtDate(CurrencyEntity curr, long entityDate) {
            if (curr == null)
                return false;
            return currencyValueDao.getValueForCurrencyAndDate(curr.getId(), entityDate) != null;
        }
    }

}


