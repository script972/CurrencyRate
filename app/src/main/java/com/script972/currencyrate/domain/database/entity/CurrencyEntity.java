package com.script972.currencyrate.domain.database.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class CurrencyEntity {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String index;

    private String title;

    private String titleShort;

    private Boolean topCurrency = false;

    public CurrencyEntity() {
    }


    public CurrencyEntity(int id, String index, String title, String titleShort, boolean topCurrency) {
        this.id = id;
        this.index = index;
        this.title = title;
        this.titleShort = titleShort;
        this.topCurrency = topCurrency;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getTitleShort() {
        return titleShort;
    }

    public void setTitleShort(String titleShort) {
        this.titleShort = titleShort;
    }

    public Boolean getTopCurrency() {
        return topCurrency;
    }

    public void setTopCurrency(Boolean topCurrency) {
        this.topCurrency = topCurrency;
    }
}
