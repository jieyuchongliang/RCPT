package com.rcpt.mvp.contract;

import com.rcpt.base.mvp.BaseView;
import com.rcpt.base.mvp.PullToRefeshContract;
import com.rcpt.base.mvp.RecyclerViewContract;
import com.rcpt.bean.PolicyListBean;

/**
 * Created by lvzp on 2017/2/27.
 * 类描述：
 */

public interface PolicyListContract {

    interface View extends BaseView, RecyclerViewContract.View<PolicyListBean.PolicylistBean>, PullToRefeshContract.View {
        String getPolicyId();
    }

    interface Presenter extends RecyclerViewContract.Presenter, PullToRefeshContract.Presenter {

    }

}
