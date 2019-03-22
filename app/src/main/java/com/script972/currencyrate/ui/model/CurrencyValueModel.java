package com.script972.currencyrate.ui.model;


public class CurrencyValueModel {

    private String rate;

    private String currency;

    private String date;

    public CurrencyValueModel() {
    }

    public CurrencyValueModel(String rate, String currency, String date) {
        this.rate = rate;
        this.currency = currency;
        this.date = date;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }


    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

}
