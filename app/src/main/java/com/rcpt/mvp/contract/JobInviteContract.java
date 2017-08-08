package com.rcpt.mvp.contract;

import com.rcpt.base.mvp.BaseView;
import com.rcpt.base.mvp.PullToRefeshContract;
import com.rcpt.base.mvp.RecyclerViewContract;
import com.rcpt.bean.JobInviteListBean;

/**
 * Created by lvzp on 2017/3/3.
 * 类描述：
 */

public interface JobInviteContract {

    interface View extends BaseView, RecyclerViewContract.View<JobInviteListBean.JobInviteBean>, PullToRefeshContract.View {
        /**
         * 获取点击题条目的Position
         *
         * @return
         */
        int getClickItemPosition();
    }

    interface Presenter extends RecyclerViewContract.Presenter, PullToRefeshContract.Presenter {
        void onItemClick();
    }

}
