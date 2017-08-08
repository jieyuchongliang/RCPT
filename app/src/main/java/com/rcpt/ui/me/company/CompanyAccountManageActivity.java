package com.rcpt.ui.me.company;

import android.os.Bundle;

import com.rcpt.R;
import com.rcpt.base.ui.BaseActivity;
import com.rcpt.databinding.ActivityAccountManageBinding;
import com.rcpt.mvp.contract.CompanyAccountManageContract;
import com.rcpt.mvp.presenter.CompanyAccountManagePresenter;

public class CompanyAccountManageActivity extends BaseActivity<ActivityAccountManageBinding, CompanyAccountManageContract.View, CompanyAccountManagePresenter>
        implements CompanyAccountManageContract.View {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    /**
     * 未实现的方法
     */
    @Override
    protected void setupTitle() {
        setTitleText("账号信息");
        openTitleLeftView(true);
    }

    @Override
    protected void initViews() {
        mDataBinding.setPresenter(mPresenter);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_account_manage;
    }

    @Override
    protected CompanyAccountManagePresenter createPresenter() {
        return new CompanyAccountManagePresenter();
    }
}
