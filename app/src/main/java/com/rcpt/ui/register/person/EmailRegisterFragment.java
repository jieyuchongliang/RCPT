package com.rcpt.ui.register.person;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;

import com.rcpt.R;
import com.rcpt.base.ui.BaseFragment;
import com.rcpt.databinding.FragmentEmailRegisterBinding;
import com.rcpt.mvp.contract.EmailRegisterContract;
import com.rcpt.mvp.presenter.EmailRegisterPresenter;
import com.rcpt.service.RegisterCodeTimer;

public class EmailRegisterFragment extends BaseFragment<FragmentEmailRegisterBinding,
        EmailRegisterContract.View, EmailRegisterPresenter> implements EmailRegisterContract.View {


    private InputMethodManager mInputMethodManager;
    private RegisterCodeTimer mRegisterCodeTimer;

    public static EmailRegisterFragment newInstance() {
        EmailRegisterFragment fragment = new EmailRegisterFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public int getLayoutRes() {
        return R.layout.fragment_email_register;
    }

    @Override
    public void initViews() {
        mRegisterCodeTimer = new RegisterCodeTimer(mDataBinding.tvEmailGetVerify);
        mDataBinding.setPresenter(mPresenter);
    }

    @Override
    protected EmailRegisterPresenter createPresenter() {
        return new EmailRegisterPresenter();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    /**
     * 开始进行倒计时
     */
    @Override
    public void startCountDownTimer() {
        mRegisterCodeTimer.startTiming(R.color.windowBackground, R.color.windowBackground);
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (!isVisibleToUser) {
            //1.得到InputMethodManager对象
            if (mInputMethodManager == null && getContext() != null)
                mInputMethodManager = (InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
            if (mInputMethodManager != null)
                //2.调用hideSoftInputFromWindow方法隐藏软键盘
                mInputMethodManager.hideSoftInputFromWindow(mDataBinding.getRoot().getWindowToken(),
                        InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

    /**
     * 返回注册成功的消息
     */
    @Override
    public void resetRegisterOk() {
        getActivity().setResult(Activity.RESULT_OK);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mRegisterCodeTimer.onFinish();
    }

    @Override
    public String getInputEmail() {
        return mDataBinding.inputEmail.getInputText().toString().trim();
    }

    @Override
    public String getInputAccount() {
        return mDataBinding.inputAccount.getInputText().toString().trim();
    }

    @Override
    public String getInputPassword() {
        return mDataBinding.inputPassword.getInputText().toString().trim();
    }

    @Override
    public String getInputAgainPassword() {
        return mDataBinding.inputAgainPassword.getInputText().toString().trim();
    }

    @Override
    public String getInputVerify() {
        return mDataBinding.inputVerify.getInputText().toString().trim();
    }
}
