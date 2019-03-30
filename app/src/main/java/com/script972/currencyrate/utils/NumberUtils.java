package com.script972.currencyrate.utils;

public class NumberUtils {

    public static String convertToStringWithRound(double value, double flo) {
        return String.valueOf(Math.round(value * flo) / flo);
        //return String.valueOf(value);
    }

}
