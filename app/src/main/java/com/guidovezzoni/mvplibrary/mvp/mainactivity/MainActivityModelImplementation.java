package com.guidovezzoni.mvplibrary.mvp.mainactivity;

import android.support.annotation.NonNull;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.guidovezzoni.mvp.BaseModelImplementation;
import com.guidovezzoni.mvplibrary.api.Api;
import com.guidovezzoni.mvplibrary.api.OpenWeatherMapService;
import com.guidovezzoni.mvplibrary.model.Forecast;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivityModelImplementation
        extends BaseModelImplementation<MainActivityContract.Presenter>
        implements MainActivityContract.Model {

    private static final String BASE_URL = "http://api.openweathermap.org/";
    private static final String APPID = "d85b2bcb9955bfad728e28a87699c5bb";
    private static final String SERVICE_ERROR = "Service error";

    private OpenWeatherMapService mOpenWeatherMapService;
    private Call<Forecast> mForecastCall;

    @Override
    public void attachPresenter(MainActivityContract.Presenter basePresenter) {
        super.attachPresenter(basePresenter);

        mOpenWeatherMapService = getOpenWeatherMapService();
    }

    /**
     * start network request and handle retrofit callbacks
     *
     * @param arg             city id
     * @param onModelListener
     */
    @Override
    public void retrieveData(Long arg, @NonNull final OnModelListener onModelListener) {
        if (onModelListener == null) {
            throw new NullPointerException("onModelListener");
        }

        mForecastCall = mOpenWeatherMapService.getDataForecast(arg, APPID, Api.QUERY_PARAM_UNITS_METRIC);
        mForecastCall.enqueue(new Callback<Forecast>() {
            @Override
            public void onResponse(Call<Forecast> call, Response<Forecast> response) {
                if (response.isSuccessful()) {
                    onModelListener.onDataRetrieved(response.body());
                } else {

                    // TODO this could be more sophisticated....
                    String errorMsg;
                    try {
                        errorMsg = response.errorBody().string();
                    } catch (IOException e) {
                        errorMsg = SERVICE_ERROR;
                    }
                    onModelListener.onDataUnavailable(errorMsg);
                }
            }

            @Override
            public void onFailure(Call<Forecast> call, Throwable t) {
                if (call.isCanceled()) {
                    // this case will occur when retrofit request gets cancelled
                    onModelListener.onRequestCancelled();
                } else {
                    onModelListener.onDataUnavailable(t.getMessage());
                }
            }
        });
    }

    @Override
    public void cancelAsyncRequests() {
        // in case it happend before the Call has been created
        if (mForecastCall!=null) {
            mForecastCall.cancel();
        }
    }

    /**
     * build retrofit service with additional code to handle timestamps and logs
     *
     * @return
     */
    private OpenWeatherMapService getOpenWeatherMapService() {

        // handle logs
        OkHttpClient okHttpClient = new OkHttpClient
                .Builder()
                .addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .build();

        // handle timestamps
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd' 'HH:mm:ss")
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        return retrofit.create(OpenWeatherMapService.class);
    }

}
