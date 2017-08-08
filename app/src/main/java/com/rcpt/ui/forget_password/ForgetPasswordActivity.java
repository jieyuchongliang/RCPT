package com.rcpt.ui.forget_password;

import android.os.Bundle;

import com.rcpt.R;
import com.rcpt.base.adapter.FragmentAdapter;
import com.rcpt.base.ui.BaseActivity;
import com.rcpt.databinding.ActivityForgetPasswordBinding;
import com.rcpt.mvp.contract.ForgetPassworActivityContract;
import com.rcpt.mvp.presenter.ForgetPasswordActivityPresenter;

public class ForgetPasswordActivity
        extends BaseActivity<ActivityForgetPasswordBinding, ForgetPassworActivityContract.View,
        ForgetPasswordActivityPresenter>
        implements ForgetPassworActivityContract.View {

    @Override
    protected void setupTitle() {
        setTitleText("忘记密码", DEFAULT_TITLE_TEXT_COLOR);
        openTitleLeftView(true);
    }

    @Override
    protected void initViews() {
        FragmentAdapter adapter = new FragmentAdapter(getSupportFragmentManager());
        adapter.addFragment(ForgetPasswordEmailFragment.newInstance());
        adapter.addFragment(ForgetPasswordPhoneFragment.newInstance());
        mDataBinding.viewPager.setAdapter(adapter);
        mDataBinding.index.bindViewPageAndTitle(mDataBinding.viewPager, mDataBinding.radioGroup);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_forget_password;
    }

    @Override
    protected ForgetPasswordActivityPresenter createPresenter() {
        return new ForgetPasswordActivityPresenter();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}
