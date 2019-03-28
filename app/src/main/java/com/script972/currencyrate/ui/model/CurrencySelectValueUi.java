package com.script972.currencyrate.ui.model;

import androidx.room.ColumnInfo;

public class CurrencySelectValueUi {

    private String titleShort;
    private String title;
    private String rate;
    private Boolean topCurrency = false;

    public CurrencySelectValueUi() {
    }

    public CurrencySelectValueUi(String titleShort, String title, String rate, Boolean topCurrency) {
        this.titleShort = titleShort;
        this.title = title;
        this.rate = rate;
        this.topCurrency = topCurrency;
    }

    public String getTitleShort() {
        return titleShort;
    }

    public void setTitleShort(String titleShort) {
        this.titleShort = titleShort;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public Boolean getTopCurrency() {
        return topCurrency;
    }

    public void setTopCurrency(Boolean topCurrency) {
        this.topCurrency = topCurrency;
    }
}
