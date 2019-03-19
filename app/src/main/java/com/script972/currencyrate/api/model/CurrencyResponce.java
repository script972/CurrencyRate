package com.script972.currencyrate.api.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CurrencyResponce {

    @SerializedName("r030")
    @Expose
    private String index;

    @SerializedName("txt")
    @Expose
    private String title;

    @SerializedName("rate")
    @Expose
    private double rate;

    @SerializedName("cc")
    @Expose
    private String titleShort;

    @SerializedName("exchangedate")
    @Expose
    private String date;

    public CurrencyResponce() {
    }

    public CurrencyResponce(String index, String title, double rate, String titleShort, String date) {
        this.index = index;
        this.title = title;
        this.rate = rate;
        this.titleShort = titleShort;
        this.date = date;
    }

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
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

    @Override
    public String toString() {
        return "CurrencyResponce{" +
                "index='" + index + '\'' +
                ", title='" + title + '\'' +
                ", rate=" + rate +
                ", titleShort='" + titleShort + '\'' +
                ", date='" + date + '\'' +
                '}';
    }
}
