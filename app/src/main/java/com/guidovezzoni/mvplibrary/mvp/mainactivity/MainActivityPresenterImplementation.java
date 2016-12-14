package com.guidovezzoni.mvplibrary.mvp.mainactivity;

import com.guidovezzoni.mvp.BasePresenterImplementation;
import com.guidovezzoni.mvp.ExtendedContract;
import com.guidovezzoni.mvplibrary.model.Forecast;

/**
 * Created by guido on 10/12/16.
 */

public class MainActivityPresenterImplementation
        extends BasePresenterImplementation<MainActivityContract.View, MainActivityContract.Model>
        implements MainActivityContract.Presenter {

    private static final Long LONDON_ID = 2643743L;
    private static final Long NEWYORK_ID= 5128638L;

    private Long mCurrentCityId = null;

    /**
     * simply refresh current city id
     */
    @Override
    public void viewNeedsData() {
        if (mCurrentCityId!=null){
            requestData(mCurrentCityId);
        } else {
            viewPressedLondon();
        }
    }

    @Override
    public MainActivityContract.Model initModel() {
        return new MainActivityModelImplementation();
    }

    @Override
    public void cancelAsyncRequests() {
    }

    @Override
    public void viewPressedLondon() {
        mCurrentCityId = LONDON_ID;
        requestData(mCurrentCityId);
    }

    @Override
    public void viewPressedNY() {
        mCurrentCityId = NEWYORK_ID;
        requestData(mCurrentCityId);
    }

    private void requestData(Long cityId) {
        getModel().retrieveData(cityId, new ExtendedContract.Model.OnModelListener<Forecast>() {
            @Override
            public void onDataRetrieved(Forecast data) {
                if (getView() != null) {
                    getView().dataRequestSuccessful(data);
                }
            }

            @Override
            public void onDataUnavailable(String message) {
                if (getView() != null) {
                    getView().dataRequestError(message);
                }
            }

            @Override
            public void onRequestCancelled() {
                if (getView() != null) {
                    getView().dataRequestCancelled();
                }
            }
        });

    }
}
