package com.script972.currencyrate.viewmodels;

import com.script972.currencyrate.domain.database.entity.CurrencySelectValue;
import com.script972.currencyrate.domain.repository.impl.CurrencyRepositoryImpl;
import com.script972.currencyrate.domain.repository.CurrencyRepository;
import com.script972.currencyrate.ui.model.CurrencySelectValueUi;

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
    private List<CurrencySelectValueUi> dataList = new ArrayList<>();

    private final MediatorLiveData<List<CurrencySelectValueUi>> currenciesLiveData;

    private CurrencyRepository currencyRepository = new CurrencyRepositoryImpl();

    public MainViewModel() {
        this.currenciesLiveData = new MediatorLiveData<>();
        LiveData<List<CurrencySelectValueUi>> source = currencyRepository.findAllCurrencyForToday();
        currenciesLiveData.addSource(
                source,
                dataListRep -> {
                    this.dataList = dataListRep;
                    this.currenciesLiveData.setValue(dataListRep);
                }
        );
    }

    /**
     * Filter data by substring
     */
    public void filterData(String value) {
        if (value == null || value.isEmpty()) {
            currenciesLiveData.setValue(dataList);
            return;
        }

        List<CurrencySelectValueUi> newData = new ArrayList<>();
        for (int i = 0; i < dataList.size(); i++) {
            if (dataList.get(i).getTitle().toLowerCase().contains(value.toLowerCase()) ||
                    dataList.get(i).getTitleShort().toLowerCase().contains(value.toLowerCase())) {
                newData.add(dataList.get(i));
            }
        }
        currenciesLiveData.setValue(newData);
    }

    @NonNull
    public LiveData<List<CurrencySelectValueUi>> getCurrenciesLiveData() {
        return currenciesLiveData;
    }
}
