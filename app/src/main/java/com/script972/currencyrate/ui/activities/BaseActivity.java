package com.script972.currencyrate.ui.activities;

import android.os.Bundle;

import com.google.firebase.analytics.FirebaseAnalytics;
import com.script972.currencyrate.R;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public abstract class BaseActivity extends AppCompatActivity {

    private FirebaseAnalytics mFirebaseAnalytics;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initFirebseModules();
    }

    protected void initFirebseModules() {
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);
    }
}
