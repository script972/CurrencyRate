package com.script972.currencyrate.domain.database.dao;

import com.script972.currencyrate.domain.database.entity.CurrencySelectValue;
import com.script972.currencyrate.domain.database.entity.CurrencyValueEntity;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

@Dao
public interface CurrencyValueDao {

    @Query("SELECT * FROM CurrencyValueEntity WHERE currency_id = (SELECT id FROM CurrencyEntity " +
            "WHERE title_short =:currency) AND date >= :startDate AND date <=:endDate ORDER BY date DESC")
    LiveData<List<CurrencyValueEntity>> getValuesForDate(String currency, long startDate,
                                                         long endDate);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(CurrencyValueEntity value);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertList(List<CurrencyValueEntity> value);

    @Query("SELECT * FROM CurrencyValueEntity WHERE date=:date AND currency_id=:currencyValue")
    CurrencyValueEntity getItemCurrencyForDate(long date, long currencyValue);

    @Query("SELECT title_short, title , rate, top_currency FROM CurrencyValueEntity INNER JOIN CurrencyEntity " +
            "ON CurrencyValueEntity.currency_id = CurrencyEntity.id WHERE date = :dateValue")
    LiveData<List<CurrencySelectValue>> getValuesForDate(long dateValue);

    @Query("SELECT * FROM CurrencyValueEntity WHERE date=:date AND currency_id=:id")
    CurrencyValueEntity getValueForCurrencyAndDate(int id, long date);
}
