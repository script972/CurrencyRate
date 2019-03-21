package com.script972.currencyrate.domain.database.entity;

public class CurrencySelectValue {

    private String titleShort;
    private String title;
    private double rate;

    public CurrencySelectValue() {
    }

    public CurrencySelectValue(String titleShort, String title, double rate) {
        this.titleShort = titleShort;
        this.title = title;
        this.rate = rate;
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
}
