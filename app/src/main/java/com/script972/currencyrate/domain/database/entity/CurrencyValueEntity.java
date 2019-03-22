package com.script972.currencyrate.domain.database.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import static androidx.room.ForeignKey.CASCADE;

@Entity(foreignKeys = @ForeignKey(entity = CurrencyEntity.class,
        parentColumns = "id",
        childColumns = "currency_id",
        onDelete = CASCADE))
public class CurrencyValueEntity {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private double rate;

    private long date;

    @ColumnInfo(name = "currency_id", index = true)
    private long currencyId;

    @Ignore
    public CurrencyValueEntity() {
    }

    public CurrencyValueEntity(int id, double rate, long date, long currencyId) {
        this.id = id;
        this.rate = rate;
        this.date = date;
        this.currencyId = currencyId;
    }

    public long getCurrencyId() {
        return currencyId;
    }

    public void setCurrencyId(long currencyId) {
        this.currencyId = currencyId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }
}
