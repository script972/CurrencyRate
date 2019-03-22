package com.script972.currencyrate.domain.api.services;

import com.script972.currencyrate.domain.api.model.CurrencyResponce;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface CurrencyApiService {

    @GET("/NBUStatService/v1/statdirectory/exchange?json")
    Call<List<CurrencyResponce>> getAllCurrencyForToday();

    @GET("/NBUStatService/v1/statdirectory/exchange?json")
    Call<List<CurrencyResponce>> getAllCurrencyForDate(@Query("date") String value);

    @GET("/NBUStatService/v1/statdirectory/exchange?json")
    Call<List<CurrencyResponce>> getOnlyCurrencyForDate(@Query("date") String valueDate, @Query("valcode") String valueCurrency);


}
