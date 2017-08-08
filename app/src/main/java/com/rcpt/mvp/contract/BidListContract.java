package com.rcpt.mvp.contract;

import com.rcpt.base.mvp.BaseView;
import com.rcpt.base.mvp.PullToRefeshContract;
import com.rcpt.base.mvp.RecyclerViewContract;
import com.rcpt.bean.BidListBean;

import java.util.List;

/**
 * Created by lvzp on 2017/2/24.
 * 类描述：
 */

public interface BidListContract {

    interface View extends BaseView, RecyclerViewContract.View<BidListBean.ProjectlistBean>,PullToRefeshContract.View {
        String getBidType();

        void bindBidPersonData(List<BidListBean.personnelProlistBean> bidPersonData);
    }

    interface Presenter extends RecyclerViewContract.Presenter, PullToRefeshContract.Presenter {

    }

}
