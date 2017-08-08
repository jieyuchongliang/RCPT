package com.rcpt.mvp.presenter;

import android.text.TextUtils;

import com.rcpt.base.mvp.BasePresenter;
import com.rcpt.base.mvp.OnDataGetCallback;
import com.rcpt.mvp.contract.PhoneRegisterContract;
import com.rcpt.mvp.module.PhoneRegisterModule;
import com.rcpt.ui.register.RegisterClauseActivity;
import com.rcpt.utils.RECheckUtils;

/**
 * Created by lvzp on 2017/2/21.
 * 类描述：
 */

public class PhoneRegisterPresenter extends BasePresenter<PhoneRegisterContract.View> implements PhoneRegisterContract.Presenter {

    private PhoneRegisterModule mPhoneRegisterModule;
    private String REG_TYPE_CODE_PHONE = "phone";

    @Override
    public void attach(PhoneRegisterContract.View view) {
        super.attach(view);
        mPhoneRegisterModule = new PhoneRegisterModule();
    }

    @Override
    public void onClickGetVerify() {
        String phoneNum = getView().getInputPhoneNum();
        if (getView().checkInputPhoneNumEmpty()) {
            getView().showToast("请输入手机号码");
            return;
        } else if (!RECheckUtils.checkPhoneNum(phoneNum)) {
            getView().showToast("请输入正确的手机号");
        }

        mPhoneRegisterModule.getVerify(getView().getContext(), phoneNum, new OnDataGetCallback<String>() {
            @Override
            public void onSuccessResult(String s) {
                getView().startCountDownTimer();
                getView().showToast(s);
            }
        });
    }

    /**
     * 进入注册条款界面
     */
    @Override
    public void onClickGoRegisterClause() {
        getView().actionStartActivity(RegisterClauseActivity.class);
    }

    @Override
    public void onClickSubmit() {
        String phoneNum = getView().getInputPhoneNum();
        String username = getView().getInputUsername();
        String password = getView().getInputPassword();
        String verify = getView().getInputVerify();
        if (getView().checkInputPhoneNumEmpty()) {
            getView().showToast("请输入手机号码");
            return;
        } else if (!RECheckUtils.checkPhoneNum(phoneNum)) {
            getView().showToast("请输入正确的手机号");
            return;
        }

        if (getView().checkInputUsernameEmpty()) {
            getView().showToast("请输入用户名");
            return;
        } else if (!RECheckUtils.checkUsername(username)) {
            getView().showToast("请输入正确的用户名");
            return;
        } else if (username.length() < 4) {
            getView().showToast("用户名至少要有四位");
            return;
        }
        if (getView().checkInputPasswordEmpty()) {
            getView().showToast("请输入密码");
            return;
        } else if (RECheckUtils.checkPassword(password) == 1) {
            getView().showToast("请输入合法的密码");
            return;
        } else if (RECheckUtils.checkPassword(password) == 2) {
            getView().showToast("密码中不能包含中文字符");
            return;
        }
        if (TextUtils.isEmpty(getView().getInputRPassword())) {
            getView().showToast("请输入确认密码");
            return;
        } else if (!getView().checkRPassword()) {
            getView().showToast("两次输入密码不一致");
            return;
        }
        if (getView().checkInputVerifyEmpty()) {
            getView().showToast("请输入验证码");
            return;
        }

        mPhoneRegisterModule.personReg(getView().getContext(), REG_TYPE_CODE_PHONE, username, password, phoneNum, verify,
                new OnDataGetCallback<String>() {

                    @Override
                    public void onSuccessResult(String s) {
                        getView().showToast("注册成功");
                        getView().resetRegisterOk();
                        getView().closeActivity();
                    }
                });
    }
}
