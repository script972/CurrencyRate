package com.script972.currencyrate.domain.api;

public class ApiClient {

    public enum Environment {DEV, STAGE, PROD}

    private static final String DEV = null;
    private static final String STAGE = null;
    private static final String PROD = "bank.gov.ua";
    private static final String BASE_URL = getBaseUrl(Environment.PROD);
    private static final String BASE_ENDPOINT = "";

    public static final String BASE_API_URL = "https://" + BASE_URL + "/" + BASE_ENDPOINT;

    private static String getBaseUrl(Environment buildEnvironment) {
        switch (buildEnvironment) {
            case DEV:
                return DEV;
            case STAGE:
                return STAGE;
            case PROD:
                return PROD;
            default:
                return PROD;
        }
    }

}
