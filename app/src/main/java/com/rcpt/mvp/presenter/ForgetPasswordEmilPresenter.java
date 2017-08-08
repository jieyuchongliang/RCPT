package com.rcpt.mvp.presenter;

import android.text.TextUtils;
import android.util.Log;

import com.rcpt.R;
import com.rcpt.base.HttpResult;
import com.rcpt.base.bean.CodeBean;
import com.rcpt.base.mvp.BasePresenter;
import com.rcpt.http.ProgressSubscriber;
import com.rcpt.http.RequestImpl;
import com.rcpt.mvp.contract.ForgetPasswordEmilContract;
import com.rcpt.mvp.module.EmailGetModule;
import com.rcpt.mvp.module.RequestModule;
import com.rcpt.utils.RECheckUtils;

/**
 * Created by lvzp on 2017/2/21.
 * 类描述：
 */

public class ForgetPasswordEmilPresenter extends BasePresenter<ForgetPasswordEmilContract.View> implements ForgetPasswordEmilContract.Presenter {

    private EmailGetModule mModule;
    private RequestModule mRequestModule;

    @Override
    public void attach(ForgetPasswordEmilContract.View view) {
        super.attach(view);
        mModule = new EmailGetModule();
        mRequestModule = new RequestModule();
    }

    @Override
    public void onClickGetVerify() {
        String email = getView().getInputEmail();
        mModule.setVerifyCode(null);
        getView().startCountDownTimer();
        mRequestModule.startSendCodeByEmail(email, new ProgressSubscriber<>(getView().getContext(), new RequestImpl<HttpResult<CodeBean>>() {
            @Override
            public void onNext(HttpResult<CodeBean> httpResult) {
                mModule.setVerifyCode(httpResult.getData().getRandom());
            }
        }));
    }

    @Override
    public void onClickInputEmailNext() {
        String email = getView().getInputEmail();

        if (TextUtils.isEmpty(email)) {
            getView().showToast("请填写您的邮箱");
            return;
        }

        mModule.setEmail(email);
        mRequestModule.startSendCodeByEmail(email, new ProgressSubscriber<>(getView().getContext(), new RequestImpl<HttpResult<CodeBean>>() {
            @Override
            public void onNext(HttpResult<CodeBean> codeBeanHttpResult) {
                mModule.setVerifyCode(codeBeanHttpResult.getData().getRandom());
                //getView().showToast(codeBeanHttpResult.getData().getRandom());
                getView().inputEmailOut();
                getView().sendVerifyIn();
                getView().setNumProgress(1);
                getView().startCountDownTimer();
                getView().showSendVerifyHint(
                        mModule.setSendVerifyFormat(
                                String.format(getView().getInputVerifyHint(), mModule.getEmail()),
                                getView().getResColor(R.color.colorBlue),
                                getView().getResColor(R.color.colorTextBlack),
                                20,
                                "验证码",
                                ":"
                        )
                );
            }
        }));
    }

    @Override
    public void onClickSendVerifyNext() {
        if (getView().checkVerifyInputEmpty()) {
            getView().showToast("请填写您邮箱收到的验证码");
            return;
        }
        String verify = getView().getInputVerify();
        String verifyCode = mModule.getVerifyCode();

        Log.d("emailCode:", mModule.getVerifyCode());

        if (!verify.equals(verifyCode)) {
            getView().showToast("验证码不正确");
            return;
        }

        getView().sendVerifyOut();
        getView().saveNewPasswordIn();
        getView().setNumProgress(2);
       /* getView().showSavePasswordHint(
                mModule.setSaveNewPasswordFormat(
                        String.format(getView().getSaveNewPasswordHint(), mModule.getEmail()),
                        "帐号",
                        getView().getResColor(R.color.colorTextBlack),
                        20
                ));*/
    }

    @Override
    public void onClickSaveNewPassword() {
        if (getView().checkNewPasswordInputEmpty()) {
            getView().showToast("密码不可为空");
            return;
        }

        String email = getView().getInputEmail();
        String pwd = getView().getInputNewPassword();
        mModule.setNewPassword(pwd);
        if (RECheckUtils.checkPassword(pwd)==1) {
            getView().showToast("请输入合法的密码");
            return;
        }else if (RECheckUtils.checkPassword(pwd) == 2) {
            getView().showToast("密码中不能包含中文字符");
            return;
        }
        mRequestModule.startEditCodeByEmail(email, pwd, new ProgressSubscriber<>(getView().getContext(), new RequestImpl<HttpResult<String>>() {

            @Override
            public void onNext(HttpResult<String> stringHttpResult) {
                getView().showToast("您设置的新密码成功");
                getView().closeActivity();
            }
        }));
    }
}
