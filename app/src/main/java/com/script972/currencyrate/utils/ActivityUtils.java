package com.script972.currencyrate.utils;

import android.content.Context;
import android.content.Intent;

import com.script972.currencyrate.domain.database.entity.CurrencySelectValue;
import com.script972.currencyrate.ui.activities.DetailsActivity;
import com.script972.currencyrate.ui.activities.MainActivity;
import com.script972.currencyrate.ui.model.CurrencySelectValueUi;

public class ActivityUtils {

    public static final String EXTRA_CURRENCY = "currency";

    public static void startMainActivity(Context context) {
        Intent intent = new Intent(context, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        context.startActivity(intent);
    }

    public static void startDetailsActivity(Context context, CurrencySelectValueUi currencyResponce) {
        Intent intent = new Intent(context, DetailsActivity.class);
        intent.putExtra(EXTRA_CURRENCY, currencyResponce.getTitleShort());
        context.startActivity(intent);
    }

}
