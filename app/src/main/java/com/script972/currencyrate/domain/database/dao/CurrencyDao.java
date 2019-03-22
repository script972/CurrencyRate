package com.script972.currencyrate.domain.database.dao;

import com.script972.currencyrate.domain.database.entity.CurrencyEntity;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface CurrencyDao {

    @Query("SELECT * FROM CurrencyEntity")
    LiveData<List<CurrencyEntity>> getAll();

    @Query("SELECT * FROM CurrencyEntity")
    List<CurrencyEntity> getAllList();

    @Insert
    void insert(CurrencyEntity value);

    @Insert
    void insertAll(List<CurrencyEntity> value);

    @Query("SELECT * FROM CurrencyEntity WHERE title_short = :shortTitle")
    CurrencyEntity getByShortValue(String shortTitle);


    @Query("UPDATE CurrencyEntity SET top_currency = 1 WHERE title_short = :currency")
    void markAsFavorite(String currency);
}
