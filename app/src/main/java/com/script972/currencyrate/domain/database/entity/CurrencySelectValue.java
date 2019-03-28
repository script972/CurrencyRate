package com.script972.currencyrate.domain.database.entity;

import androidx.room.ColumnInfo;
import androidx.room.Ignore;

public class CurrencySelectValue {

    @ColumnInfo(name = "title_short")
    private String titleShort;
    private String title;
    private double rate;
    @ColumnInfo(name = "top_currency")
    private Boolean topCurrency = false;

    @Ignore
    public CurrencySelectValue() {
    }

    public CurrencySelectValue(String titleShort, String title, double rate, Boolean topCurrency) {
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

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public Boolean getTopCurrency() {
        return topCurrency;
    }

    public void setTopCurrency(Boolean topCurrency) {
        this.topCurrency = topCurrency;
    }
}
