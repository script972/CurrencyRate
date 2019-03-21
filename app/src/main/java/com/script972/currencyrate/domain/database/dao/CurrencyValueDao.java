package com.script972.currencyrate.domain.database.dao;

import com.script972.currencyrate.domain.database.entity.CurrencySelectValue;
import com.script972.currencyrate.domain.database.entity.CurrencyValueEntity;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface CurrencyValueDao {

    @Query("SELECT * FROM CurrencyValueEntity WHERE currencyId = (SELECT id FROM CurrencyEntity " +
            "WHERE titleShort =:currency) AND date >= :startDate AND date <=:endDate ORDER BY date DESC")
    LiveData<List<CurrencyValueEntity>> getValuesForCurrency(String currency, long startDate,
                                                             long endDate);

    @Insert
    void insert(CurrencyValueEntity value);

    @Query("SELECT * FROM CurrencyValueEntity WHERE date=:date AND currencyId=:currencyValue")
    CurrencyValueEntity getItemCurrencyForDate(long date, long currencyValue);

    @Query("SELECT ce.titleShort, ce.title , cv.rate FROM CurrencyEntity AS ce, " +
            "CurrencyValueEntity AS cv WHERE cv.date = :dateValue group by ce.titleShort")
    LiveData<List<CurrencySelectValue>> getValuesForCurrency(long dateValue);

    @Query("SELECT * FROM CurrencyValueEntity WHERE date=:date AND currencyId=:id")
    CurrencyValueEntity getValueForCurrencyAndDate(int id, long date);
}
