package com.rcpt.ui.pay;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.view.View;

import com.rcpt.Constant;
import com.rcpt.R;
import com.rcpt.base.ui.BaseActivity;
import com.rcpt.databinding.ActivityPayStatusBinding;
import com.rcpt.mvp.contract.PayStatusContract;
import com.rcpt.mvp.presenter.PayStatusPresenter;
import com.rcpt.utils.IconUtils;

/**
 * 显示最终支付状态的页面
 */
public class PayStatusActivity extends BaseActivity<ActivityPayStatusBinding, PayStatusContract.View, PayStatusPresenter>
        implements PayStatusContract.View {

    public static void actionStart(Context context, boolean isPaySuccess, boolean isFromOrderList) {
        Intent intent = new Intent(context, PayStatusActivity.class);
        intent.putExtra(PAY_STATUS, isPaySuccess);
        intent.putExtra(IS_FROM_ORDER_LIST, isFromOrderList);
        context.startActivity(intent);
    }

    private static final String PAY_STATUS = "pay_status";
    private static final String IS_FROM_ORDER_LIST = "is_from_order_list";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void setupTitle() {
        setTitleText("订单结果");
        openTitleLeftView(true).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.onClickBack();
            }
        });
    }

    @Override
    public void onBackPressed() {
        mPresenter.onClickBack();
    }

    @Override
    protected void initViews() {
        boolean booleanExtra = getIntent().getBooleanExtra(PAY_STATUS, false);
        if (booleanExtra) {
            LocalBroadcastManager instance = LocalBroadcastManager.getInstance(mContext);
            Intent intent = new Intent();
            intent.setAction(Constant.LOCAL_BROADCAST_UPDATE_ORDER_LIST);
            instance.sendBroadcast(intent);
        }
        mDataBinding.setPayStatus(booleanExtra);
        mDataBinding.setPresenter(mPresenter);
        mDataBinding.tvIcon.setTypeface(IconUtils.getIcon(mContext));
    }

    /**
     * 获取当前的订单信息是否来自于订单列表页面
     *
     * @return
     */
    @Override
    public boolean isFromOrderList() {
        return getIntent().getBooleanExtra(IS_FROM_ORDER_LIST, false);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_pay_status;
    }

    @Override
    protected PayStatusPresenter createPresenter() {
        return new PayStatusPresenter();
    }
}
