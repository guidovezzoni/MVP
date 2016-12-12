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

/**
 * This Contract defines an interface for a basic exchange of data. It's limited to one use-case,
 * it's more an example of a simple use.
 * Whenever more than one use-case is needed it's recommended to design a custom contract
 *
 * @param <D> data being exchanged
 * @param <V> view
 * @param <P> presenter
 * @param <M> model
 */
public interface ExtendedContract<D, A, V extends ExtendedContract.View, P extends ExtendedContract.Presenter, M extends ExtendedContract.Model>
        extends BaseContract<V, P, M> {

    interface View<D, P> extends BaseContract.View<P> {

        void dataRequestSuccessful(D data);

        void dataRequestError(String message);

        void dataRequestCancelled();

    }

    interface Presenter<V, M> extends BaseContract.Presenter<V, M> {

        void viewNeedsData();

    }

    interface Model<A, P> extends BaseContract.Model<P> {

        interface OnModelListener<D> {

            void onDataRetrieved(D data);

            void onDataUnavailable(String message);

            void onRequestCancelled();
        }

        void retrieveData(A arg, OnModelListener listener);

    }

}
