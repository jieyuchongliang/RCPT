package com.rcpt.mvp.contract;

import com.rcpt.base.mvp.BaseView;
import com.rcpt.base.mvp.PullToRefeshContract;
import com.rcpt.base.mvp.RecyclerViewContract;
import com.rcpt.bean.FavoritesBean;

/**
 * Created by lvzp on 2017/3/7.
 * 类描述：
 */

public interface FavoritesContract {

    interface View extends BaseView, RecyclerViewContract.View<FavoritesBean.FavoritesListBean>, PullToRefeshContract.View {
        String getRecordId();

        int getClickPosition();

        void deleteListItemUpdate(int position);
    }

    interface Presenter extends RecyclerViewContract.Presenter, PullToRefeshContract.Presenter {
        void onItemLongClick();
    }

}
