package com.script972.currencyrate.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPreferencesUtils {

    private static final String PREFS_NAME = "com.script972.currencyrate.MAIN_PREFS";

    private static final String LAST_SYNC_DATE = "LAST_SYNC_DATE";

    private static SharedPreferences getSharedPreferences(Context context) {
        return context.getSharedPreferences(context.getPackageName() + PREFS_NAME, Context.MODE_PRIVATE);
    }

    /**
     * Save sync currency date
     *
     * @param context
     * @param timestemp
     */ //TODO
    public static void setLastSyncDate(Context context, long timestemp) {
        SharedPreferences.Editor prefEditor = getSharedPreferences(context).edit();
        prefEditor.putLong(LAST_SYNC_DATE, timestemp);
        prefEditor.apply();
    }

    /**
     * Get last sync currency date
     *
     * @param context
     * @return
     */
    public static long getLastSyncDate(Context context) {
        return getSharedPreferences(context).getLong(LAST_SYNC_DATE, -1);
    }


}
