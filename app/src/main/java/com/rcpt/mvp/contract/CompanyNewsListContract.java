package com.rcpt.mvp.contract;

import com.rcpt.base.mvp.BaseView;
import com.rcpt.base.mvp.PullToRefeshContract;
import com.rcpt.base.mvp.RecyclerViewContract;
import com.rcpt.bean.CompanyNewsListBean;


/**
 * Created by 860116021 on 2017/4/7.
 */

public interface CompanyNewsListContract {
    interface View extends BaseView, RecyclerViewContract.View<CompanyNewsListBean.CompanyNewsBean>, PullToRefeshContract.View {

    }

    interface Presenter extends RecyclerViewContract.Presenter, PullToRefeshContract.Presenter {

    }
}
