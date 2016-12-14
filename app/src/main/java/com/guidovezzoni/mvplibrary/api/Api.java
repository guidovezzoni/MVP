package com.guidovezzoni.mvplibrary.api;

/**
 * Created by guido on 09/12/16.
 */

public class Api {
    // query params
    public static final String QUERY_PARAM_CITY_ID = "id";

    public static final String QUERY_PARAM_APP_ID = "APPID";

    public static final String QUERY_PARAM_UNITS = "units";
    public static final String QUERY_PARAM_UNITS_METRIC = "metric";
    public static final String QUERY_PARAM_UNITS_IMPERIAL = "imperial";

    // root folders
    public static final String DATA = "data";


    // api version
    public static final String V2_5_API = "2.5";


    // endpoint
    public static final String FORECAST = "forecast";


    // full endpoints
    public static final String DATA_FORECAST = DATA + "/" + V2_5_API + "/" + FORECAST;
}
