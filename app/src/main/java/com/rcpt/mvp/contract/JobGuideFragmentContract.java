package com.rcpt.mvp.contract;

import com.rcpt.base.mvp.BaseView;
import com.rcpt.base.mvp.PullToRefeshContract;
import com.rcpt.base.mvp.RecyclerViewContract;
import com.rcpt.bean.RecruitFragmentListBean;

/**
 * Created by lvzp on 2017/3/29.
 * 类描述：
 */

public interface JobGuideFragmentContract {

    interface View extends BaseView, RecyclerViewContract.View<RecruitFragmentListBean.SubBean>, PullToRefeshContract.View {
        String getJobGuideTypeId();
    }

    interface Presenter extends RecyclerViewContract.Presenter, PullToRefeshContract.Presenter {

    }

}
