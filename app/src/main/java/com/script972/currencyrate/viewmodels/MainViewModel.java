package com.script972.currencyrate.viewmodels;

import com.script972.currencyrate.ui.model.CurrencySelectValue;
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
     * Filter data by substring
     */
    public void filterData(String value) {
        if (value == null || value.isEmpty()) {
            dataList.setValue(apiResponse);
            return;
        }

        List<CurrencySelectValue> newData = new ArrayList<>();
        for (int i = 0; i < apiResponse.size(); i++) {
            if (apiResponse.get(i).getTitle().toLowerCase().contains(value.toLowerCase()) ||
                    apiResponse.get(i).getTitleShort().toLowerCase().contains(value.toLowerCase())) {
                newData.add(apiResponse.get(i));
            }
        }
        dataList.setValue(newData);
    }
}
