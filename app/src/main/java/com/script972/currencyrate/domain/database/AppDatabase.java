package com.script972.currencyrate.domain.database;

import com.script972.currencyrate.domain.database.dao.CurrencyDao;
import com.script972.currencyrate.domain.database.dao.CurrencyValueDao;
import com.script972.currencyrate.domain.database.entity.CurrencyEntity;
import com.script972.currencyrate.domain.database.entity.CurrencyValueEntity;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {CurrencyEntity.class, CurrencyValueEntity.class},
        version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    /**
     * Migration handler. For future
     */
    public static final Migration MIGRATION_1_2 = new Migration(1, 2) {
        @Override
        public void migrate(SupportSQLiteDatabase database) {

        }
    };

    public abstract CurrencyDao currencyDao();

    public abstract CurrencyValueDao currencyValueDao();


}
