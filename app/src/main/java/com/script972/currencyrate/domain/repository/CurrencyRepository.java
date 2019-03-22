package com.script972.currencyrate.domain.repository;

import com.script972.currencyrate.ui.model.CurrencySelectValue;
import com.script972.currencyrate.ui.model.CurrencyValueModel;

import java.util.List;

import androidx.lifecycle.LiveData;

public interface CurrencyRepository {

    LiveData<List<CurrencySelectValue>> findAllCurrencyForToday();

    LiveData<List<CurrencyValueModel>> findAllCurrencyForDates(List<Long> dataQuery, String currencyValue1);

    void changeFavorite(String currency);
}
