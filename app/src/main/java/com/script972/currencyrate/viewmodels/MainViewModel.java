package com.script972.currencyrate.viewmodels;

import com.script972.currencyrate.api.model.ApiResponse;
import com.script972.currencyrate.api.model.CurrencyResponce;
import com.script972.currencyrate.domain.repository.impl.CurrencyRepositoryImpl;
import com.script972.currencyrate.domain.repository.CurrencyRepository;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.ViewModel;

public class MainViewModel extends ViewModel {

    /**
     * Immutable data
     */
    private ApiResponse apiResponse;
    private final MediatorLiveData<List<CurrencyResponce>> dataList;

    private CurrencyRepository currencyRepository;

    public MainViewModel() {
        this.dataList = new MediatorLiveData<>();
        this.currencyRepository = new CurrencyRepositoryImpl();
    }

    @NonNull
    public LiveData<List<CurrencyResponce>> getDataForList() {
        return dataList;
    }

    /**
     * Load currency data for today
     */
    public void loadCurrency() {
        LiveData<ApiResponse> source = currencyRepository.findAllCurrencyForToday();
        dataList.addSource(
                source,
                apiResponse -> {
                    if (this.dataList.hasActiveObservers()) {
                        this.dataList.removeSource(source);
                    }
                    if (apiResponse.getError() != null) {
                        return;
                    }
                    fillDatabase(apiResponse.getCurrencyResponceList());
                    this.apiResponse = apiResponse;
                    this.dataList.setValue(apiResponse.getCurrencyResponceList());
                }
        );
    }

    /**
     * Fill database if need
     */
    private void fillDatabase(List<CurrencyResponce> currencyResponceList) {
        currencyRepository.updaAllDatabaseCurrencyIfNeed(currencyResponceList);
    }

    /**
     * Filter data by subsring
     */
    public void filterData(String value) {
        if (value == null || value.isEmpty() || apiResponse == null) {
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
        dataList.setValue(newData);
    }
}
