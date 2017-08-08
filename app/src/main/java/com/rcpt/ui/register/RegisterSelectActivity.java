package com.rcpt.ui.register;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import com.lzp.statusbar.utils.ChangeStatusBarStatus;
import com.rcpt.R;
import com.rcpt.base.ui.BaseActivity;
import com.rcpt.databinding.ActivityRegisterSelectBinding;
import com.rcpt.mvp.contract.RegisterSelectContract;
import com.rcpt.mvp.presenter.RegisterSelectPresenter;

public class RegisterSelectActivity extends BaseActivity<ActivityRegisterSelectBinding, RegisterSelectContract.View,
        RegisterSelectPresenter> implements RegisterSelectContract.View {


    private static final int REQUEST_CODE_REGISTER = 0x00000012;

    @Override
    protected void setupTitle() {
//        ChangeStatusBarStatus.setStatusBarDarkMode(this, true, Color.WHITE);
        setTitleText("注册");
        openTitleLeftView(true);
    }

    @Override
    protected void initViews() {
        mDataBinding.setPresenter(mPresenter);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_register_select;
    }

    @Override
    protected RegisterSelectPresenter createPresenter() {
        return new RegisterSelectPresenter();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void startGoRegister(Class<? extends Activity> activityClass) {
        Intent intent = new Intent(mContext, activityClass);
        startActivityForResult(intent, REQUEST_CODE_REGISTER);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == REQUEST_CODE_REGISTER) {
                finish();
            }
        }
    }
}
