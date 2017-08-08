package com.rcpt.ui.forget_password;


import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.rcpt.R;
import com.rcpt.base.ui.BaseFragment;
import com.rcpt.databinding.FragmentPhoneGetBinding;
import com.rcpt.mvp.contract.ForgetPasswordPhoneContract;
import com.rcpt.mvp.presenter.ForgetPasswordPresenter;
import com.rcpt.service.RegisterCodeTimer;
import com.rcpt.utils.ViewUtils;
import com.rcpt.widget.NumProgressLayout;

/**
 * 忘记密码手机找回
 */
public class ForgetPasswordPhoneFragment extends BaseFragment<FragmentPhoneGetBinding, ForgetPasswordPhoneContract.View,
        ForgetPasswordPresenter> implements ForgetPasswordPhoneContract.View {

    private RegisterCodeTimer mRegisterCodeTimer;

    public static ForgetPasswordPhoneFragment newInstance() {
        ForgetPasswordPhoneFragment fragment = new ForgetPasswordPhoneFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_phone_get;
    }


    @Override
    protected void initViews() {
        mRegisterCodeTimer = new RegisterCodeTimer(mDataBinding.tvGetVerify);
        mDataBinding.setPresenter(mPresenter);
    }


    @Override
    protected ForgetPasswordPresenter createPresenter() {
        return new ForgetPasswordPresenter();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mRegisterCodeTimer.onFinish();
    }

    @Override
    public void inputPhoneOut() {
        ViewUtils.viewOut(mDataBinding.llInputPhone);
    }

    @Override
    public void sendVerifyIn() {
        ViewUtils.viewIn(mDataBinding.llSendVerify);
    }

    @Override
    public void sendVerifyOut() {
        ViewUtils.viewOut(mDataBinding.llSendVerify);
    }

    @Override
    public void saveNewPasswordIn() {
        ViewUtils.viewIn(mDataBinding.llSavePassword);
    }

    @Override
    public void setNumProgress(int progress) {
        View childView = mDataBinding.llNumProgress.getChildAt(progress);
        if (childView instanceof NumProgressLayout) {
            ((NumProgressLayout) childView).setSelect(true);
        }
    }

    @Override
    public String getInputPhoneNum() {
        return mDataBinding.inputPhone.getInputText().toString();
    }

    @Override
    public String getInputVerify() {
        return mDataBinding.inputVerify.getInputText().toString();
    }

    @Override
    public String getInputNewPassword() {
        return mDataBinding.inputNewPassword.getInputText().toString();
    }

    @Override
    public void showSendVerifyHint(CharSequence hint) {
        mDataBinding.tvSendVerifyHint.setText(hint);
    }

    @Override
    public void showSavePasswordHint(CharSequence hint) {
        mDataBinding.tvSaveNewPasswordHint.setText(hint);
    }

    @Override
    public String getInputVerifyHint() {
        return mDataBinding.tvSendVerifyHint.getText().toString();
    }

    @Override
    public String getSaveNewPasswordHint() {
        return mDataBinding.tvSaveNewPasswordHint.getText().toString();
    }

    @Override
    public void startCountDownTimer() {
        mRegisterCodeTimer.startTiming(R.color.windowBackground, R.color.windowBackground);
    }

    @Override
    public boolean checkVerifyInputEmpty() {
        return checkInputEmpty(mDataBinding.inputVerify.getInputEditView());
    }

    @Override
    public boolean checkPhoneInputEmpty() {
        return checkInputEmpty(mDataBinding.inputPhone.getInputEditView());
    }

    @Override
    public boolean checkNewPasswordInputEmpty() {
        return checkInputEmpty(mDataBinding.inputNewPassword.getInputEditView());
    }

    private boolean checkInputEmpty(TextView textView) {
        String text = textView.getText().toString();
        return TextUtils.isEmpty(text);
    }


}
