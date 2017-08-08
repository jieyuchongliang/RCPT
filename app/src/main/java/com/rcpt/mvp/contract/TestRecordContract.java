package com.rcpt.mvp.contract;

import com.rcpt.base.mvp.BaseView;
import com.rcpt.base.mvp.PullToRefeshContract;
import com.rcpt.base.mvp.RecyclerViewContract;
import com.rcpt.bean.TestRecordListBean;

/**
 * Created by 860617003 on 2017/5/25.
 */

public interface TestRecordContract {

    interface View extends BaseView, RecyclerViewContract.View<TestRecordListBean.CompanyFavoritesBean>, PullToRefeshContract.View {

    }

    interface Presenter extends RecyclerViewContract.Presenter, PullToRefeshContract.Presenter {

    }

}
