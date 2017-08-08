package com.rcpt.mvp.contract;

import com.rcpt.base.mvp.BaseView;
import com.rcpt.base.mvp.PullToRefeshContract;
import com.rcpt.base.mvp.RecyclerViewContract;
import com.rcpt.bean.SystemMessageBean;

/**
 * Created by lvzp on 2017/3/6.
 * 类描述：
 */

public interface SystemMessageContract {

    interface View extends BaseView, RecyclerViewContract.View<SystemMessageBean.MessageListBean>,PullToRefeshContract.View {
        /**
         * 获取点击条目的位置
         * @return
         */
        int getClickItemPosition();
    }

    interface Presenter extends RecyclerViewContract.Presenter ,PullToRefeshContract.Presenter{
        /**
         * 条目的点击事件
         */
        void onItemClick();
    }

}
