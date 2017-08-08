package com.rcpt.mvp.contract;

import com.rcpt.base.mvp.BaseView;

/**
 * Created by 860617003 on 2017/5/19.
 */

public interface PayStatusContract {

    interface View extends BaseView {
        /**
         * 获取当前的订单信息是否来自于订单列表页面
         *
         * @return
         */
        boolean isFromOrderList();
    }

    interface Presenter {
        /**
         * 当点击返回键的时的回调
         */
        void onClickBack();

        /**
         * 当点击查看订单的回调
         */
        void onClickGoOrderList();
    }

}
