package com.rcpt.mvp.contract;

import com.rcpt.base.mvp.BaseView;
import com.rcpt.base.mvp.PullToRefeshContract;
import com.rcpt.base.mvp.RecyclerViewContract;
import com.rcpt.bean.CompanyFavoritesListBean;

/**
 * Created by lvzp on 2017/4/14.
 * 类描述：
 */

public interface CompanyFavoriteContract {

    interface View extends BaseView, RecyclerViewContract.View<CompanyFavoritesListBean.CompanyFavoritesBean>, PullToRefeshContract.View {
        int getClickPosition();

        void deleteListItem(int position);
    }

    interface Presenter extends RecyclerViewContract.Presenter, PullToRefeshContract.Presenter {
        void onItemClick();

        void onItemLongClick();
    }

}
