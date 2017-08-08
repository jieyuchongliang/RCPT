package com.rcpt.ui.forget_password;


import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.rcpt.R;
import com.rcpt.base.ui.BaseFragment;
import com.rcpt.databinding.FragmentEmilGetBinding;
import com.rcpt.mvp.contract.ForgetPasswordEmilContract;
import com.rcpt.mvp.presenter.ForgetPasswordEmilPresenter;
import com.rcpt.service.RegisterCodeTimer;
import com.rcpt.utils.ViewUtils;
import com.rcpt.widget.NumProgressLayout;

/**
 * 忘记密码邮箱找回
 */
public class ForgetPasswordEmailFragment extends BaseFragment<FragmentEmilGetBinding, ForgetPasswordEmilContract.View, ForgetPasswordEmilPresenter>
        implements ForgetPasswordEmilContract.View {

    private RegisterCodeTimer mRegisterCodeTimer;

    public static ForgetPasswordEmailFragment newInstance() {
        ForgetPasswordEmailFragment fragment = new ForgetPasswordEmailFragment();
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
        return R.layout.fragment_emil_get;
    }

    @Override
    protected void initViews() {
        mRegisterCodeTimer = new RegisterCodeTimer(mDataBinding.tvGetVerify);
        mDataBinding.setPresenter(mPresenter);
    }

    @Override
    protected ForgetPasswordEmilPresenter createPresenter() {
        return new ForgetPasswordEmilPresenter();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void inputEmailOut() {
        ViewUtils.viewOut(mDataBinding.llInputEmail);
    }

    @Override
    public void sendVerifyIn() {
        ViewUtils.viewIn(mDataBinding.llSendVerify);
    }

    @Override
    public void sendVerifyOut() {
        ViewUtils.viewIn(mDataBinding.llSavePassword);
    }

    @Override
    public void saveNewPasswordIn() {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mRegisterCodeTimer.onFinish();
    }

    @Override
    public void setNumProgress(int progress) {
        View childView = mDataBinding.llNumProgress.getChildAt(progress);
        if (childView instanceof NumProgressLayout) {
            ((NumProgressLayout) childView).setSelect(true);
        }
    }

    @Override
    public void showSendVerifyHint(CharSequence hint) {
        mDataBinding.tvSendVerifyHint.setText(hint);
    }

    @Override
    public String getInputEmail() {
        return mDataBinding.inputEmail.getInputText().toString();
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
    public String getInputVerifyHint() {
        return mDataBinding.tvSendVerifyHint.getText().toString();
    }

    @Override
    public void startCountDownTimer() {
        mRegisterCodeTimer.startTiming(R.color.windowBackground, R.color.windowBackground);
    }

    @Override
    public String getSaveNewPasswordHint() {
        return mDataBinding.tvSaveNewPasswordHint.getText().toString();
    }

    @Override
    public void showSavePasswordHint(CharSequence hint) {
        mDataBinding.tvSaveNewPasswordHint.setText(hint);
    }

    @Override
    public boolean checkVerifyInputEmpty() {
        return checkInputEmpty(mDataBinding.inputVerify.getInputEditView());
    }

    @Override
    public boolean checkEmailInputEmpty() {
        return checkInputEmpty(mDataBinding.inputEmail.getInputEditView());
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
