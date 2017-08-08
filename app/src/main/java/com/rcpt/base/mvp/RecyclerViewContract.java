package com.rcpt.base.mvp;

import android.support.v7.widget.RecyclerView;

import java.util.List;

/**
 * Created by lvzp on 2017/2/23.
 * 类描述：
 */

public interface RecyclerViewContract {
    interface View<T> {
        void initRecyclerView();

        void bindListData(List<T> beanList);

        RecyclerView getRecyclerView();
    }

    interface Presenter {
        void loadListData();
    }

}
