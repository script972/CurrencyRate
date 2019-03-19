package com.script972.currencyrate.core;

import android.app.Application;
import android.content.Context;

import com.script972.currencyrate.BuildConfig;
import com.script972.currencyrate.managers.DatabaseManager;
import com.script972.currencyrate.utils.NetworkLoggingSettingsUtils;

public class CurrencyApplication extends Application {

    private static CurrencyApplication application;


    @Override
    public void onCreate() {
        super.onCreate();
        application = this;

        DatabaseManager.getInstance();
        initDebugMode();
    }

    private void initDebugMode() {
        if(BuildConfig.DEBUG) {
            NetworkLoggingSettingsUtils.init(this);
        }
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
    }

    public static CurrencyApplication getApplication() {
        return application;
    }
}
