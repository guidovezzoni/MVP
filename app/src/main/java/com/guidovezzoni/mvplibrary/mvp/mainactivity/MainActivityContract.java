package com.guidovezzoni.mvplibrary.mvp.mainactivity;

import com.guidovezzoni.mvp.ExtendedContract;
import com.guidovezzoni.mvplibrary.model.Forecast;

public interface MainActivityContract
        extends ExtendedContract<Forecast, Long,
        MainActivityContract.View, MainActivityContract.Presenter, MainActivityContract.Model> {

    interface View extends ExtendedContract.View<Forecast, Presenter> {

    }

    interface Presenter extends ExtendedContract.Presenter<View, Model> {
        void viewPressedLondon();

        void viewPressedNY();
    }

    interface Model extends ExtendedContract.Model<Long, Presenter> {

    }
}
