/*
 * Copyright 2016, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.rcpt.base.mvp;

import android.support.v7.widget.RecyclerView;

import com.rcpt.recycler_listener.OnRecyclerItemClickListener;

import java.lang.ref.WeakReference;

public class BasePresenter<V extends BaseView> {

    private WeakReference<V> mWeakReferenceView;
    protected OnRecyclerItemClickListener mOnItemClickListener;

    public void attach(V view) {
        mWeakReferenceView = new WeakReference<>(view);
    }

    public void createItemClickListener(RecyclerView recyclerView) {
        mOnItemClickListener = new OnRecyclerItemClickListener(recyclerView) {
            @Override
            public void onItemClick(RecyclerView.ViewHolder vh) {
                BasePresenter.this.onItemClick(vh, vh.getLayoutPosition());
            }
        };
    }

    public void deAttach() {
        if (mWeakReferenceView != null) {
            mWeakReferenceView.clear();
            mWeakReferenceView = null;
        }
    }

    public V getView() {
        return mWeakReferenceView != null ? mWeakReferenceView.get() : null;
    }

    public void onItemClick(RecyclerView.ViewHolder vh, int position) {

    }
}
