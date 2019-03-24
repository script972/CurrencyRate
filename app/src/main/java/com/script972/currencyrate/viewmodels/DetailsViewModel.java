package com.script972.currencyrate.viewmodels;

import com.script972.currencyrate.domain.repository.CurrencyRepository;
import com.script972.currencyrate.domain.repository.impl.CurrencyRepositoryImpl;
import com.script972.currencyrate.ui.model.CurrencyValueModel;
import com.script972.currencyrate.utils.DateDiffUtils;
import com.script972.currencyrate.utils.DateUtils;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.ViewModel;

public class DetailsViewModel extends ViewModel {

    private final MediatorLiveData<List<CurrencyValueModel>> currencyValueLiveData;

    private CurrencyRepository currencyRepository;

    public DetailsViewModel() {
        this.currencyValueLiveData = new MediatorLiveData<>();
        this.currencyRepository = new CurrencyRepositoryImpl();

    }

    @NonNull
    public LiveData<List<CurrencyValueModel>> getCurrencyValueLiveData() {
        return currencyValueLiveData;
    }


    public void rangeDateUpdate(Calendar im, Calendar endDate, String currencyValue) {
        Calendar startDate = (Calendar) im.clone();
        List<Long> dataQuery = formatDateQuery(startDate, endDate);
        LiveData<List<CurrencyValueModel>> source = currencyRepository.findAllCurrencyForDates(dataQuery, currencyValue);
        currencyValueLiveData.addSource(source, currencyValueLiveData::setValue);
    }

    /**
     * Format list for date query
     *
     * @param startDate
     * @param endDate
     * @return list with str date
     */
    private List<Long> formatDateQuery(Calendar startDate, Calendar endDate) {
        List<Long> outDate = new ArrayList<>();
        int days = DateDiffUtils.daysBetween(startDate.getTime(), endDate.getTime());
        while (days >= 0) {
            days--;
            outDate.add(DateUtils.roundDate(startDate.getTime().getTime()));
            startDate.add(Calendar.DAY_OF_MONTH, 1);
        }
        return outDate;
    }

    public void changeFavorite(String currency) {
        currencyRepository.changeFavorite(currency);
    }

}
