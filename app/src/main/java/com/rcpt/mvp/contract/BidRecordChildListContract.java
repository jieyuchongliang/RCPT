package com.rcpt.mvp.contract;

import com.rcpt.base.mvp.BaseView;
import com.rcpt.base.mvp.PullToRefeshContract;
import com.rcpt.base.mvp.RecyclerViewContract;
import com.rcpt.bean.BidRecordListBean;

/**
 * Created by 860617003 on 2017/5/25.
 */

public interface BidRecordChildListContract {

    interface View extends BaseView, RecyclerViewContract.View<BidRecordListBean.ProjectProlistBean>, PullToRefeshContract.View {
        /**
         * 获取投标的类型
         *
         * @return
         */
        String getBidType();

        /**
         * 获取投标的状态
         *
         * @return
         */
        String getBidStatus();
    }

    interface Presenter extends RecyclerViewContract.Presenter, PullToRefeshContract.Presenter {

    }

}
