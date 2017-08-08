package com.rcpt.mvp.contract;

import com.rcpt.adapter.FilterListAdapter;
import com.rcpt.base.mvp.BaseView;
import com.rcpt.base.mvp.PullToRefeshContract;
import com.rcpt.base.mvp.RecyclerViewContract;
import com.rcpt.bean.RecruitFragmentListBean;

/**
 * Created by lvzp on 2017/3/29.
 * 类描述：
 */

public interface SchoolListContract {

    interface View extends BaseView, RecyclerViewContract.View<RecruitFragmentListBean.SubBean>, PullToRefeshContract.View, ListFilterContract.View {
    }

    interface Presenter extends RecyclerViewContract.Presenter, PullToRefeshContract.Presenter, FilterListAdapter.OnAutoSelectDataCallback, android.view.View.OnClickListener {
        /**
         * 加载筛选器列表
         */
        void loadFilterList();
    }

}
