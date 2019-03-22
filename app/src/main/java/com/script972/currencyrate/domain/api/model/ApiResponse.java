package com.script972.currencyrate.domain.api.model;


import java.util.List;

public class ApiResponse {
    private List<CurrencyResponce> currencyResponceList;
    private Throwable error;

    public ApiResponse(List<CurrencyResponce> currencyResponces) {
        this.currencyResponceList = currencyResponces;
        this.error = null;
    }

    public ApiResponse(Throwable error) {
        this.error = error;
        this.currencyResponceList = null;
    }

    public List<CurrencyResponce> getCurrencyResponceList() {
        return currencyResponceList;
    }

    public void setCurrencyResponceList(List<CurrencyResponce> currencyResponceList) {
        this.currencyResponceList = currencyResponceList;
    }

    public Throwable getError() {
        return error;
    }
}
