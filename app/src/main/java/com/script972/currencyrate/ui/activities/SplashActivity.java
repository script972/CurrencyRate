package com.script972.currencyrate.ui.activities;

import android.os.Bundle;
import android.os.Handler;

import com.script972.currencyrate.R;
import com.script972.currencyrate.utils.ActivityUtils;
import com.script972.currencyrate.managers.DatabaseManager;

public class SplashActivity extends BaseActivity {

    private final long delay = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        waiter();
    }

    /**
     * Method wich provide freeze window on fix time
     */
    private void waiter() {
        syncData();
        new Handler().postDelayed(this::openNextActivity, this.delay);
    }

    private void syncData() {
        DatabaseManager.getInstance().getCurrencyDao();
    }

    /**
     * Method wich decided what activity is next
     */
    private void openNextActivity() {
        ActivityUtils.startMainActivity(this);
    }
}
