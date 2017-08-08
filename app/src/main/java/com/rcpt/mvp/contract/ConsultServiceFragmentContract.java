package com.rcpt.mvp.contract;

import com.rcpt.base.mvp.BaseView;
import com.rcpt.base.mvp.PullToRefeshContract;
import com.rcpt.base.mvp.RecyclerViewContract;
import com.rcpt.bean.ConsultServiceListBean;

/**
 * Created by lvzp on 2017/2/23.
 * 类描述：
 */

public interface ConsultServiceFragmentContract {

    interface View extends BaseView, RecyclerViewContract.View<ConsultServiceListBean.ConsultancyListBean>, PullToRefeshContract.View {
        /**
         * 获取咨询服务类别的id
         *
         * @return
         */
        String getServiceId();
    }

    interface Presenter extends RecyclerViewContract.Presenter, PullToRefeshContract.Presenter {

    }

}
