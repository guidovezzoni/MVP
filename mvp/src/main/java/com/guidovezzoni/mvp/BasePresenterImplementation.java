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
import android.support.annotation.Nullable;

import java.lang.ref.WeakReference;

public abstract class BasePresenterImplementation<V extends BaseContract.View, M extends BaseContract.Model>
        implements BaseContract.Presenter<V, M> {
    protected final String TAG = this.getClass().getSimpleName();

    private WeakReference<V> mView;
    private M mModel;

    private Context mContext;

    public BasePresenterImplementation(M model) {
        mModel = model;
    }

    /**
     * Obtain a reference to the Model for usage within the Presenter
     *
     * @return current Model instance
     */
    @Override
    public M getModel() {
        return mModel;
    }

    /**
     * Obtain a reference to the View for usage within the Presenter. Default implementation
     * contains a weakreference
     * <p>
     * Return the view, if available and not GCed
     * Please ensure to check that by calling {@link #isViewAttached()}
     *
     * @return either null or the view
     */
    @Override
    @Nullable
    public V getView() {
        // if GC kicked in, mView.get() will be null
        return mView != null ? mView.get() : null;
    }

    /**
     * Set a reference to the view into a weak-reference
     * It then creates the model and get it attached.
     * If a context has been set then it will propagate it to the model
     *
     * @param view
     */
    @Override
    public void attachView(V view) {
        mView = new WeakReference<>(view);
        mModel.attachPresenter(this);

        // if a context is set, then propagate it to the Model
        if (mContext != null && mModel != null) {
            mModel.setContextAndBreakMVPPattern(mContext);
        }
    }

    /**
     * Disconnects the view and the presenter, cancels any registered async request
     */
    @Override
    public void detachView() {
        cancelAsyncRequests();
        mModel.detachPresenter();
        mView = null;
    }

    /**
     * We shouldn't have the context in our Presenter, however due to Android structure, sometimes it's needed.
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
     * We shouldn't have the context in our Presenter, however due to Android structure, sometimes it's needed.
     * When we do set it, we emphasise that we shouldn't.
     *
     * Deprecated because I'll remove it as soon as I find a better solution
     * @param context
     */
    @Deprecated
    @Override
    public void setContextAndBreakMVPPattern(Context context) {
        mContext = context;
        if (mModel != null) {
            mModel.setContextAndBreakMVPPattern(context);
        }
    }

    /**
     * Checks whether the view is still available or not
     *
     * @return
     */
    protected boolean isViewAttached() {
        return (mView != null) && (mView.get() != null);
    }


}
