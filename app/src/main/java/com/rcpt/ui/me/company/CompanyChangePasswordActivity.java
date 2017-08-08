package com.rcpt.ui.me.company;

import android.os.Bundle;

import com.rcpt.R;
import com.rcpt.base.ui.BaseActivity;
import com.rcpt.databinding.ActivityCompanyChangePasswordBinding;
import com.rcpt.mvp.contract.CompanyChangePasswordContract;
import com.rcpt.mvp.presenter.CompanyChangePasswordPresenter;

/**
 * 企业-----修改密码
 */
public class CompanyChangePasswordActivity extends BaseActivity<ActivityCompanyChangePasswordBinding, CompanyChangePasswordContract.View, CompanyChangePasswordPresenter>
        implements CompanyChangePasswordContract.View {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    /**
     * 未实现的方法
     */
    @Override
    protected void setupTitle() {
        setTitleText("密码修改");
        openTitleLeftView(true);
    }

    @Override
    protected void initViews() {
        mDataBinding.setPresenter(mPresenter);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_company_change_password;
    }

    @Override
    protected CompanyChangePasswordPresenter createPresenter() {
        return new CompanyChangePasswordPresenter();
    }


    /**
     * 获取原有密码
     *
     * @return
     */
    @Override
    public String getOldPassword() {
        return mDataBinding.editOldPassword.getText().toString();
    }

    /**
     * 获取新密码
     *
     * @return
     */
    @Override
    public String getNewPassword() {
        return mDataBinding.editNewPassword.getText().toString();
    }

    /**
     * 获取再次输入的密码
     *
     * @return
     */
    @Override
    public String getAgainPassword() {
        return mDataBinding.editAgainPassword.getText().toString();
    }
}
