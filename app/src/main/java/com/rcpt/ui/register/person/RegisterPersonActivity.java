package com.rcpt.ui.register.person;

import android.os.Bundle;

import com.rcpt.R;
import com.rcpt.base.adapter.FragmentAdapter;
import com.rcpt.base.ui.BaseActivity;
import com.rcpt.databinding.ActivityRegisterPersonBinding;
import com.rcpt.mvp.contract.RegisterPersonContract;
import com.rcpt.mvp.presenter.RegisterPersonPresenter;

/**
 * 个人注册的页面
 */
public class RegisterPersonActivity extends BaseActivity<ActivityRegisterPersonBinding,
        RegisterPersonContract.View,RegisterPersonPresenter> implements RegisterPersonContract.View {


    @Override
    protected void setupTitle() {

        openTitleLeftView(true);
        setTitleText("个人注册", DEFAULT_TITLE_TEXT_COLOR);
    }

    @Override
    protected void initViews() {
        FragmentAdapter adapter = new FragmentAdapter(getSupportFragmentManager());
        adapter.addFragment(EmailRegisterFragment.newInstance());
        adapter.addFragment(PhoneRegisterFragment.newInstance());
        mDataBinding.viewPager.setAdapter(adapter);
        mDataBinding.index.bindViewPageAndTitle(mDataBinding.viewPager, mDataBinding.radioGroup);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_register_person;
    }

    @Override
    protected RegisterPersonPresenter createPresenter() {
        return new RegisterPersonPresenter();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}
