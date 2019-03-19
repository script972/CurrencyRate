package com.script972.currencyrate.domain.database.dao;

import com.script972.currencyrate.domain.database.entity.CurrencyEntity;
import com.script972.currencyrate.domain.database.entity.CurrencyValueEntity;

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

    @Query("SELECT * FROM CurrencyEntity WHERE titleShort=:shortTitle")
    CurrencyEntity getByShortValue(String shortTitle);
}
