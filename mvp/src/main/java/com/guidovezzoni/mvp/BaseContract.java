/*
 * Copyright (C) 2016 Guido Vezzoni
 *
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package com.guidovezzoni.mvp;

import android.content.Context;

public interface BaseContract<V extends BaseContract.View, P extends BaseContract.Presenter, M extends BaseContract.Model> {

    interface View<P> {

        /**
         * Obtain a reference to the Presenter for usage within the View
         *
         * @return current Model instance
         */
        P getPresenter();

    }

    interface Presenter<V, M> {

        /**
         * Obtain a reference to the Model for usage within the Presenter
         *
         * @return current Model instance
         */
        M getModel();

        /**
         * Obtain a reference to the View for usage within the Presenter. Default implementation
         * contains a weakreference
         *
         * @return current View instance
         */
        V getView();

        /**
         * Template Method that instantiate the Model
         *
         * @return a newly created Model instance
         */
        M initModel();

        /**
         * Set a reference to the view the view - into weak-reference by default
         *
         * @param baseView
         */
        void attachView(V baseView);

        /**
         * Disconnects the view
         */
        void detachView();

        /**
         * In default implementation this is called just before detaching the presenter and is meant to cancel any
         * async request that is being processed, f.i.: AsyncTasks, network requests, etc.
         */
        void cancelAsyncRequests();

        /**
         * We shouldn't have the context in our Presenter, however due to Android structure, sometimes it's needed.
         * When we do set it, we emphasise that we shouldn't.
         *
         * Deprecated because I'll remove it as soon as I find a better solution
         * @param context
         */
        @Deprecated
        void setContextAndBreakMVPPattern(Context context);

        /**
         * We shouldn't have the context in our Presenter, however due to Android structure, sometimes it's needed.
         * When we do get it, we emphasise that we shouldn't.
         *
         * Deprecated because I'll remove it as soon as I find a better solution
         * @return
         */
        @Deprecated
        Context getContextAndBreakMVPPattern();
    }

    interface Model<P> {

        /**
         * Obtain a reference to the Presenter for usage within the Model
         *
         * @return current Presenter instance
         */
        P getPresenter();

        /**
         * attach model to the calling presenter
         *
         * @param basePresenter
         */
        void attachPresenter(P basePresenter);

        /**
         * disconnects the presenter
         */
        void detachPresenter();

        /**
         * In default implementation this is called just before detaching the Model and is meant to cancel any
         * async request that is being processed, f.i.: AsyncTasks, network requests, etc.
         */
        void cancelAsyncRequests();

        /**
         * We shouldn't have the context in our Model, however due to Android structure, sometimes it's needed.
         * When we do set it, we emphasise that we shouldn't.
         *
         * Deprecated because I'll remove it as soon as I find a better solution
         * @param context
         */
        @Deprecated
        void setContextAndBreakMVPPattern(Context context);

        /**
         * We shouldn't have the context in our Model, however due to Android structure, sometimes it's needed.
         * When we do get it, we emphasise that we shouldn't.
         *
         * Deprecated because I'll remove it as soon as I find a better solution
         * @return
         */
        @Deprecated
        Context getContextAndBreakMVPPattern();
    }
}
