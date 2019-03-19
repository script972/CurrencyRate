package com.script972.currencyrate.managers;

import com.script972.currencyrate.core.CurrencyApplication;
import com.script972.currencyrate.domain.database.dao.CurrencyDao;
import com.script972.currencyrate.domain.database.dao.CurrencyValueDao;
import com.script972.currencyrate.domain.database.AppDatabase;

import androidx.room.Room;

public class DatabaseManager {

    private static AppDatabase db;
    private static DatabaseManager ourInstance;

    public static DatabaseManager getInstance() {
        if(ourInstance == null){
            initDatabase();
        }
        return ourInstance;
    }

    private DatabaseManager() {
    }

    private static void initDatabase() {
        db = Room.databaseBuilder(CurrencyApplication.getApplication(),
                AppDatabase.class, "database")
                .addMigrations(AppDatabase.MIGRATION_1_2).build();

        ourInstance = new DatabaseManager();
    }

    public CurrencyDao getCurrencyDao() {
        return db.currencyDao();
    }

    public CurrencyValueDao getCurrencyValueDao() {
        return db.currencyValueDao();
    }

}
