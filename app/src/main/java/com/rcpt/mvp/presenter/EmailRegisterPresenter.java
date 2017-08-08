package com.rcpt.mvp.presenter;

import android.text.TextUtils;

import com.rcpt.base.HttpResult;
import com.rcpt.base.bean.CodeBean;
import com.rcpt.base.mvp.BasePresenter;
import com.rcpt.http.ProgressSubscriber;
import com.rcpt.http.RequestImpl;
import com.rcpt.mvp.contract.EmailRegisterContract;
import com.rcpt.mvp.module.RequestModule;
import com.rcpt.ui.register.RegisterClauseActivity;
import com.rcpt.utils.RECheckUtils;

/**
 * Created by lvzp on 2017/2/21.
 * 类描述：
 */

public class EmailRegisterPresenter extends BasePresenter<EmailRegisterContract.View> implements EmailRegisterContract.Presenter {


    private RequestModule mModule;

    @Override
    public void attach(EmailRegisterContract.View view) {
        super.attach(view);
        mModule = new RequestModule();
    }

    @Override
    public void onClickGetVerify() {
        String email = getView().getInputEmail();
        if (TextUtils.isEmpty(email)) {
            getView().showToast("请输入邮箱号");
            return;
        }else if (!RECheckUtils.checkEmail(email)){
            getView().showToast("请输入正确的邮箱");
            return;
        }
        mModule.startSendEmailVerify(email, new ProgressSubscriber<HttpResult<CodeBean>>(getView().getContext(),
                new RequestImpl<HttpResult<CodeBean>>() {
                    @Override
                    public void onNext(HttpResult<CodeBean> result) {
                        if (result.isResult()) {
                            getView().startCountDownTimer();
                            getView().showToast("发送成功");
                        }
                    }
                }));
    }

    /**
     * 进入注册条款界面
     */
    @Override
    public void onClickGoRegisterClause() {
        getView().actionStartActivity(RegisterClauseActivity.class);
    }

    @Override
    public void onClickRegister() {
        String email = getView().getInputEmail();
        String password = getView().getInputPassword();
        String account = getView().getInputAccount();
        String againPassword = getView().getInputAgainPassword();
        String verify = getView().getInputVerify();
        if (TextUtils.isEmpty(email)) {
            getView().showToast("请输入邮箱号");
            return;
        } else if (!RECheckUtils.checkEmail(email)) {
            getView().showToast("请输入正确的邮箱号");
            return;
        }
        if (TextUtils.isEmpty(account)) {
            getView().showToast("请输入帐号");
            return;
        } else if (!RECheckUtils.checkUsername(account)) {
            getView().showToast("请输入正确的帐号");
            return;
        }else if (account.length()<4){
            getView().showToast("用户名至少需要四位");
            return;
        }
        if (TextUtils.isEmpty(password)) {
            getView().showToast("请输入密码");
            return;
        } else if (RECheckUtils.checkPassword(password) == 1) {
            getView().showToast("请输入符合规范的密码");
            return;
        }else if (RECheckUtils.checkPassword(password) == 2){
            getView().showToast("密码中不能包含中文字符");
            return;
        }
        if (TextUtils.isEmpty(againPassword)) {
            getView().showToast("请输入确认密码");
            return;
        } else if (!password.equals(againPassword)) {
            getView().showToast("两次密码输入的不一致");
            return;
        }
        if (TextUtils.isEmpty(verify)) {
            getView().showToast("请输入验证码");
            return;
        }

        mModule.startRegisteEmail(
                account,
                password,
                email,
                verify,
                new ProgressSubscriber<>(getView().getContext(),
                        new RequestImpl<HttpResult<String>>() {
                            @Override
                            public void onNext(HttpResult<String> stringHttpResult) {
                                getView().showToast("注册成功");
                                getView().resetRegisterOk();
                                getView().closeActivity();
                            }
                        }));
    }

}
