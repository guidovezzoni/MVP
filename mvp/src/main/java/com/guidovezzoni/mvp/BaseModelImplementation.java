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

public abstract class BaseModelImplementation<P extends BaseContract.Presenter>
        implements BaseContract.Model<P> {
    protected final String TAG = this.getClass().getSimpleName();

    private P mPresenter;
    private Context mContext;

    /**
     * Obtain a reference to the Presenter for usage within the Model
     *
     * @return current Presenter instance
     */
    @Override
    public P getPresenter() {
        return mPresenter;
    }

    /**
     * attach model to the calling presenter
     *
     * @param basePresenter
     */
    @Override
    public void attachPresenter(P basePresenter) {
        mPresenter = basePresenter;
    }

    /**
     * Disconnects the presenter
     * <p>
     * Cancels any pending async request registered under {@link #cancelAsyncRequests()}
     */
    @Override
    public void detachPresenter() {
        cancelAsyncRequests();
        mPresenter = null;
    }

    /**
     * We shouldn't have the context in our Model, however due to Android structure, sometimes it's needed.
     * When we do set it, we emphasise that we shouldn't.
     *
     * Deprecated because I'll remove it as soon as I find a better solution
     * @return
     */
    @Deprecated
    @Override
    public Context getContextAndBreakMVPPattern() {
        if (mContext == null) {
            throw new IllegalStateException("Not only you requested a Context in your model, you didn't even initialise it");
        }
        return mContext;
    }

    /**
     * We shouldn't have the context in our Model, however due to Android structure, sometimes it's needed.
     * When we do set it, we emphasise that we shouldn't.
     *
     * Deprecated because I'll remove it as soon as I find a better solution
     * @param context
     */
    @Deprecated
    @Override
    public void setContextAndBreakMVPPattern(Context context) {
        mContext = context;
    }
}
