package com.rcpt.ui.home.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.rcpt.R;
import com.rcpt.base.ui.BaseActivity;
import com.rcpt.bean.BidInfoBean;
import com.rcpt.databinding.ActivityBidInfoBinding;
import com.rcpt.mvp.contract.BidInfoContract;
import com.rcpt.mvp.presenter.BidInfoPresenter;

public class BidInfoActivity extends BaseActivity<ActivityBidInfoBinding, BidInfoContract.View, BidInfoPresenter>
        implements BidInfoContract.View {

    private static final String BID_ID_TAG = "bid_id";

    public static void actionStart(Context context, String bidId) {
        Intent intent = new Intent(context, BidInfoActivity.class);
        intent.putExtra(BID_ID_TAG, bidId);
        context.startActivity(intent);
    }

    private String mBidId;

    @Override
    protected void setupTitle() {
        setTitleText("竞标详情", DEFAULT_TITLE_TEXT_COLOR);
        openTitleLeftView(true);
    }

    @Override
    protected void initViews() {
        mBidId = getIntent().getStringExtra(BID_ID_TAG);
        mPresenter.loadInfo();
        mDataBinding.setPresenter(mPresenter);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_bid_info;
    }

    @Override
    protected BidInfoPresenter createPresenter() {
        return new BidInfoPresenter();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public String getBidId() {
        return mBidId;
    }

    @Override
    public void setBean(BidInfoBean bean) {
        mDataBinding.setBean(bean);
    }

    @Override
    protected void onPause() {
        super.onPause();
        //界面不可见时将竞标是否成功的标记置为false
        mPresenter.isBidSuccess(null);
    }
}
