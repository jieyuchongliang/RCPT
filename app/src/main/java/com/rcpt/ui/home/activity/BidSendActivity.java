package com.rcpt.ui.home.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.rcpt.R;
import com.rcpt.base.ui.BaseActivity;
import com.rcpt.bean.BidContactsBean;
import com.rcpt.databinding.ActivityBidSendBinding;
import com.rcpt.mvp.contract.BidSendContract;
import com.rcpt.mvp.presenter.BidSendPresenter;

public class BidSendActivity extends BaseActivity<ActivityBidSendBinding, BidSendContract.View, BidSendPresenter>
        implements BidSendContract.View {

    public final static String FROM_WHERE_TAG_PERSON = "person";
    public final static String FROM_WHERE_TAG_PROJECT = "project";

    public static void actionStart(Context context, String bidId, String bidCompanyName, String fromWhere) {
        Intent intent = new Intent(context, BidSendActivity.class);
        intent.putExtra("from_where", fromWhere);
        intent.putExtra("bid_company_name", bidCompanyName);
        intent.putExtra("bid_id", bidId);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    /**
     * 未实现的方法
     */
    @Override
    protected void setupTitle() {
        setTitleText("填写竞标信息");
        openTitleLeftView(true);
    }

    @Override
    protected void initViews() {
        mDataBinding.setPresenter(mPresenter);
        mPresenter.loadPageData();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_bid_send;
    }

    @Override
    protected BidSendPresenter createPresenter() {
        return new BidSendPresenter();
    }

    /**
     * 绑定页面数据
     *
     * @param bean
     */
    @Override
    public void bindPageData(BidContactsBean bean) {
        mDataBinding.setBean(bean);
    }

    /**
     * 获取竞标公司名称
     *
     * @return
     */
    @Override
    public String getBidCompanyName() {
        return getIntent().getStringExtra("bid_company_name");
    }

    /**
     * 获取投标的Id
     *
     * @return
     */
    @Override
    public String getBidId() {
        return getIntent().getStringExtra("bid_id");
    }

    /**
     * 获取来自于啊哪里
     *
     * @return
     */
    @Override
    public String getFromWhere() {
        return getIntent().getStringExtra("from_where");
    }

    /**
     * 获取竞标的联系人
     *
     * @return
     */
    @Override
    public String getBidContacts() {
        return mDataBinding.editName.getText().toString();
    }

    /**
     * 获取竞标的手机号
     *
     * @return
     */
    @Override
    public String getBidPhone() {
        return mDataBinding.editTel.getText().toString();
    }

    /**
     * 获取竞标的邮箱
     *
     * @return
     */
    @Override
    public String getBidEmail() {
        return mDataBinding.editEmail.getText().toString();
    }

    /**
     * 获取竞标的方案
     *
     * @return
     */
    @Override
    public String getBidContent() {
        return mDataBinding.editBidContent.getText().toString();
    }
}
