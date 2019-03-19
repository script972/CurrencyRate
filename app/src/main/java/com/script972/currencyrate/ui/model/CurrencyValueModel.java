package com.script972.currencyrate.ui.model;


public class CurrencyValueModel {

    private int id;

    private String title;

    private String rate;

    private String titleShort;

    private String currency;

    private String date;

    public CurrencyValueModel() {
    }

    public CurrencyValueModel(String title, String rate, String titleShort, String currency, String date) {
        this.title = title;
        this.rate = rate;
        this.titleShort = titleShort;
        this.currency = currency;
        this.date = date;
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

    public String getTitleShort() {
        return titleShort;
    }

    public void setTitleShort(String titleShort) {
        this.titleShort = titleShort;
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

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
