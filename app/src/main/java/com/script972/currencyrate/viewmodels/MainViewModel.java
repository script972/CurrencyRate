package com.script972.currencyrate.viewmodels;

import android.app.Application;
import android.util.Log;

import com.script972.currencyrate.api.model.ApiResponse;
import com.script972.currencyrate.api.model.CurrencyResponce;
import com.script972.currencyrate.core.CurrencyApplication;
import com.script972.currencyrate.domain.database.entity.CurrencySelectValue;
import com.script972.currencyrate.domain.repository.impl.CurrencyRepositoryImpl;
import com.script972.currencyrate.domain.repository.CurrencyRepository;
import com.script972.currencyrate.utils.SharedPreferencesUtils;

import java.security.AlgorithmParameters;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.ViewModel;

public class MainViewModel extends ViewModel {

    /**
     * Immutable data
     */
    private List<CurrencySelectValue> apiResponse;
    private final MediatorLiveData<List<CurrencySelectValue>> dataList;

    private CurrencyRepository currencyRepository;

    public MainViewModel() {
        this.dataList = new MediatorLiveData<>();
        this.currencyRepository = new CurrencyRepositoryImpl();
    }

    @NonNull
    public LiveData<List<CurrencySelectValue>> getDataForList() {
        return dataList;
    }

    /**
     * Load currency data for today
     */
    public void loadCurrency() {
        LiveData<List<CurrencySelectValue>> source = currencyRepository.findAllCurrencyForToday();
        dataList.addSource(
                source,
                dataListRep -> {
                    this.apiResponse = dataListRep;
                    this.dataList.setValue(dataListRep);
                }
        );
    }

    /**
     * Filter data by subsring
     */
    public void filterData(String value) {
        /*if (value == null || value.isEmpty() || apiResponse != null) {
            dataList.setValue(apiResponse.getCurrencyResponceList());
            return;
        }
        List<CurrencyResponce> listData = new ArrayList<>();
        listData.addAll(dataList.getValue());

        List<CurrencyResponce> newData = new ArrayList<>();
        for (int i = 0; i < listData.size(); i++) {
            if (listData.get(i).getTitle().toLowerCase().contains(value.toLowerCase()) ||
                    listData.get(i).getTitleShort().toLowerCase().contains(value.toLowerCase())) {
                newData.add(listData.get(i));
            }
        }
        dataList.setValue(newData);*/
    }
}
