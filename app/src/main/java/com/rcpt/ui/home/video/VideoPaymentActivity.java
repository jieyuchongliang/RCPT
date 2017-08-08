package com.rcpt.ui.home.video;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.rcpt.LoginHelper;
import com.rcpt.R;
import com.rcpt.base.ui.BaseActivity;
import com.rcpt.bean.VideoPaymentBean;
import com.rcpt.databinding.ActivityVideoPaymentBinding;
import com.rcpt.mvp.contract.VideoPaymentContract;
import com.rcpt.mvp.presenter.VideoPaymentPresenter;
import com.rcpt.ui.login.LoginActivity;

public class VideoPaymentActivity extends BaseActivity<ActivityVideoPaymentBinding, VideoPaymentContract.View, VideoPaymentPresenter>
        implements VideoPaymentContract.View {

    public static void actionStart(Context context, VideoPaymentBean courseBean, String orderSN) {
        Intent intent = new Intent(context, VideoPaymentActivity.class);
        intent.putExtra(COURSE_BEAN, courseBean);
        intent.putExtra(ORDER_SN, orderSN);
        context.startActivity(intent);
    }

    private static final String ORDER_SN = "order_sn";
    private static final String COURSE_BEAN = "bean";

    private VideoPaymentBean mPaymentBean;
    private boolean isFree;//是否为免费的视频

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    /**
     * 未实现的方法
     */
    @Override
    protected void setupTitle() {
        setTitleText("购买课程");
        openTitleLeftView(true);
    }

    @Override
    protected void initFieldBeforeMethods() {
        mPaymentBean = getIntent().getParcelableExtra(COURSE_BEAN);
        float price = 0;
        try {
            price = Float.parseFloat(mPaymentBean.getCoursePrice());
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (price == 0) {
            isFree = true;
        }
    }

    @Override
    protected void initViews() {
        mDataBinding.setBean(mPaymentBean);
        mDataBinding.setPresenter(mPresenter);
        if (isFree()) {
            mDataBinding.radioGroup.setVisibility(View.GONE);
        }
    }

    /**
     * 判断是否免费
     *
     * @return
     */
    @Override
    public boolean isFree() {
        return isFree;
    }

    /**
     * 获取订单号
     *
     * @return
     */
    @Override
    public String getOrderSn() {
        return getIntent().getStringExtra(ORDER_SN);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_video_payment;
    }

    @Override
    protected VideoPaymentPresenter createPresenter() {
        return new VideoPaymentPresenter();
    }

    /**
     * 是否选择了支付宝支付
     *
     * @return
     */
    @Override
    public boolean isSelectAliPay() {
        return mDataBinding.radioBtnAlipay.isChecked();
    }

    /**
     * 获取是否选择了微信支付
     *
     * @return
     */
    @Override
    public boolean isSelectWeixinPay() {
        return mDataBinding.radioBtnWeixin.isChecked();
    }

    @Override
    public VideoPaymentBean getVideoPayment() {
        return mPaymentBean;
    }
}
