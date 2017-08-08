package com.rcpt.mvp.presenter;

import com.rcpt.LoginHelper;
import com.rcpt.base.mvp.BasePresenter;
import com.rcpt.base.mvp.OnDataGetCallback;
import com.rcpt.bean.VideoPaymentBean;
import com.rcpt.mvp.contract.VideoPaymentContract;
import com.rcpt.mvp.module.VideoPaymentModule;
import com.rcpt.pay_utils.alipay.AliPayUtils;
import com.rcpt.pay_utils.wxpays.WXPayUtils;
import com.rcpt.ui.pay.PayStatusActivity;

import java.util.Arrays;
import java.util.List;

import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.functions.Func2;

/**
 * Created by lvzp on 2017/5/9.
 * 类描述：
 */

public class VideoPaymentPresenter extends BasePresenter<VideoPaymentContract.View> implements VideoPaymentContract.Presenter {

    private VideoPaymentModule mModule;

    @Override
    public void attach(VideoPaymentContract.View view) {
        super.attach(view);
        mModule = new VideoPaymentModule();
    }

    /**
     * 点击支付
     */
    @Override
    public void onClickStartPay() {
        VideoPaymentBean videoPayment = getView().getVideoPayment();
        if (videoPayment == null) return;
        if (getView().isFree()) {
            mModule.requestVideoFreePay(getView().getContext(), LoginHelper.getInstance().getUserToken()
                    , videoPayment.getCourseName()
                    , videoPayment.getCommodityId()
                    , videoPayment.getCourseName()
                    , videoPayment.getCourseId()
                    , videoPayment.getSubject_id()
                    , videoPayment.getTeaching_style()
                    , videoPayment.getSubject()
                    , videoPayment.getCoursePrice()
                    , new OnDataGetCallback<String>() {
                        @Override
                        public void onSuccessResult(String data) {
                            getView().showToast(data);
                            goPayStatus(true);
                        }
                    });
        } else {
            AliPayUtils aliPayUtils = new AliPayUtils(getView().getContext());
            aliPayUtils.setOnPayResultListener(this);
            if (getView().isSelectAliPay()) {
                aliPayUtils.openPay(LoginHelper.getInstance().getUserToken()
                        , videoPayment.getCourseName()
                        , videoPayment.getCommodityId()
                        , videoPayment.getCourseName()
                        , videoPayment.getCourseId()
                        , videoPayment.getSubject_id()
                        , videoPayment.getTeaching_style()
                        , videoPayment.getSubject()
                        , videoPayment.getCoursePrice()
                        , getView().getOrderSn());
            } else if (getView().isSelectWeixinPay()) {
                WXPayUtils wxPayUtils = new WXPayUtils(getView().getContext());
                wxPayUtils.openWxPay(LoginHelper.getInstance().getUserToken()
                        , videoPayment.getCourseName()
                        , videoPayment.getCourseName()
                        , videoPayment.getCourseId()
                        , videoPayment.getCoursePrice()
                        , videoPayment.getCommodityId()
                        , videoPayment.getSubject_id()
                        , videoPayment.getTeaching_style()
                        , videoPayment.getSubject()
                        , getView().getOrderSn());
            } else {
                getView().showToast("请选择支付方式");
            }
        }
    }

    @Override
    public void onSuccess() {
        goPayStatus(true);
    }

    @Override
    public void onFailure() {
        goPayStatus(false);
    }

    /**
     * 进入到支付状态页面
     *
     * @param isSuccess
     */
    private void goPayStatus(boolean isSuccess) {
        PayStatusActivity.actionStart(getView().getContext(), isSuccess, getView().getOrderSn() != null);
        getView().closeActivity();
    }

}
