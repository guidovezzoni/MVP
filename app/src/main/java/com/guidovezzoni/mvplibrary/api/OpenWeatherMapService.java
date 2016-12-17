package com.guidovezzoni.mvplibrary.api;

import com.guidovezzoni.mvplibrary.model.Forecast;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface OpenWeatherMapService {
    // sample: http://api.openweathermap.org/data/2.5/forecast?id=%d&APPID=d85b2bcb9955bfad728e28a87699c5bb
    @GET(Api.DATA_FORECAST)
    Call<Forecast> getDataForecast(@Query(Api.QUERY_PARAM_CITY_ID) long id,
                                   @Query(Api.QUERY_PARAM_APP_ID) String appId,
                                   @Query(Api.QUERY_PARAM_UNITS) String units);
}
