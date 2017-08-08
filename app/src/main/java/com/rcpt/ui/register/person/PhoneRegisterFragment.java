package com.rcpt.ui.register.person;


import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;

import com.rcpt.R;
import com.rcpt.base.ui.BaseFragment;
import com.rcpt.databinding.FragmentPhoneRegisterBinding;
import com.rcpt.mvp.contract.PhoneRegisterContract;
import com.rcpt.mvp.presenter.PhoneRegisterPresenter;
import com.rcpt.service.RegisterCodeTimer;

/**
 * 手机注册的Fragment
 */
public class PhoneRegisterFragment extends BaseFragment<FragmentPhoneRegisterBinding, PhoneRegisterContract.View, PhoneRegisterPresenter> implements PhoneRegisterContract.View {

    private InputMethodManager mInputMethodManager;
    private RegisterCodeTimer mRegisterCodeTimer;

    @Override
    public String getInputPhoneNum() {
        return mDataBinding.inputPhoneNum.getInputText().toString().trim();
    }

    @Override
    public String getInputUsername() {
        return mDataBinding.inputUsername.getInputText().toString().trim();
    }

    @Override
    public String getInputPassword() {
        return mDataBinding.inputPassword.getInputText().toString().trim();
    }

    @Override
    public String getInputRPassword() {
        return mDataBinding.inputRPassword.getInputText().toString().trim();
    }

    @Override
    public String getInputVerify() {
        return mDataBinding.inputVerify.getInputText().toString();
    }

    @Override
    public boolean checkInputPhoneNumEmpty() {
        return checkInputEmpty(mDataBinding.inputPhoneNum.getInputEditView());
    }

    @Override
    public boolean checkInputUsernameEmpty() {
        return checkInputEmpty(mDataBinding.inputUsername.getInputEditView());
    }

    @Override
    public boolean checkInputPasswordEmpty() {
        return checkInputEmpty(mDataBinding.inputPassword.getInputEditView());
    }

    @Override
    public boolean checkRPassword() {
        return mDataBinding.inputPassword.getInputText().toString().equals(mDataBinding.inputRPassword.getInputText().toString());
    }

    @Override
    public boolean checkInputVerifyEmpty() {
        return checkInputEmpty(mDataBinding.inputVerify.getInputEditView());
    }

    private boolean checkInputEmpty(TextView textView) {
        String text = textView.getText().toString();
        return TextUtils.isEmpty(text);
    }


    public static PhoneRegisterFragment newInstance() {
        PhoneRegisterFragment fragment = new PhoneRegisterFragment();
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
        return R.layout.fragment_phone_register;
    }

    @Override
    protected void initViews() {
        mRegisterCodeTimer = new RegisterCodeTimer(mDataBinding.tvGetVerify);
        mDataBinding.setPresenter(mPresenter);
    }

    @Override
    protected PhoneRegisterPresenter createPresenter() {
        return new PhoneRegisterPresenter();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    /**
     * 返回注册成功的消息
     */
    @Override
    public void resetRegisterOk() {
        getActivity().setResult(Activity.RESULT_OK);
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

    @Override
    public void onDestroy() {
        super.onDestroy();
        mRegisterCodeTimer.onFinish();
    }

}
