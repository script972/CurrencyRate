package com.script972.currencyrate.utils;

import android.content.Context;

import com.facebook.stetho.BuildConfig;
import com.facebook.stetho.Stetho;
import com.facebook.stetho.okhttp3.StethoInterceptor;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

public class NetworkLoggingSettingsUtils {

    public static void init(Context appContext) {
        Stetho.initialize(Stetho
                .newInitializerBuilder(appContext)
                .enableWebKitInspector(Stetho.defaultInspectorModulesProvider(appContext))
                .build());
    }

    public static OkHttpClient.Builder configOkHttpClient(OkHttpClient.Builder okClientBuilder) {
        HttpLoggingInterceptor.Level level;
        if (BuildConfig.DEBUG) {
            level = HttpLoggingInterceptor.Level.BASIC;
        } else {
            level = HttpLoggingInterceptor.Level.BODY;
        }

        return okClientBuilder
                .addInterceptor(new HttpLoggingInterceptor().setLevel(level))
                .addNetworkInterceptor(new StethoInterceptor());
    }
}
