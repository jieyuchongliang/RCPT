package com.rcpt.mvp.contract;

import com.rcpt.base.mvp.BaseView;
import com.rcpt.bean.VideoPaymentBean;
import com.rcpt.pay_utils.OnPayResultListener;

/**
 * Created by lvzp on 2017/5/9.
 * 类描述：
 */

public interface VideoPaymentContract {

    interface View extends BaseView {
        /**
         * 是否选择了支付宝支付
         *
         * @return
         */
        boolean isSelectAliPay();

        /**
         * 获取是否选择了微信支付
         *
         * @return
         */
        boolean isSelectWeixinPay();

        /**
         * 获取视频支付的对象
         *
         * @return
         */
        VideoPaymentBean getVideoPayment();

        /**
         * 获取订单号
         *
         * @return
         */
        String getOrderSn();

        /**
         * 判断是否免费
         * @return
         */
        boolean isFree();
    }

    interface Presenter extends OnPayResultListener {
        /**
         * 点击支付
         */
        void onClickStartPay();
    }

}
