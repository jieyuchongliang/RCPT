package com.rcpt.mvp.contract;

import com.rcpt.base.mvp.BaseView;
import com.rcpt.base.mvp.PullToRefeshContract;
import com.rcpt.base.mvp.RecyclerViewContract;
import com.rcpt.bean.NewsListBean;

/**
 * Created by lvzp on 2017/2/23.
 * 类描述：
 */

public interface NewsFragmentContract {

    interface View extends BaseView, RecyclerViewContract.View<NewsListBean.GetlistNewsBean>, PullToRefeshContract.View {
        String getNewsId();

        int getNewsType();
    }

    interface Presenter extends RecyclerViewContract.Presenter, PullToRefeshContract.Presenter {
    }

}
