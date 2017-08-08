package com.rcpt.mvp.presenter;

import android.text.TextUtils;

import com.igexin.sdk.PushManager;
import com.rcpt.Constant;
import com.rcpt.LoginHelper;
import com.rcpt.base.HttpResult;
import com.rcpt.base.mvp.BasePresenter;
import com.rcpt.bean.User;
import com.rcpt.http.ProgressSubscriber;
import com.rcpt.http.RequestImpl;
import com.rcpt.mvp.contract.LoginContract;
import com.rcpt.mvp.module.RequestModule;
import com.rcpt.ui.forget_password.ForgetPasswordActivity;
import com.rcpt.ui.main.activity.MainActivity;
import com.rcpt.ui.register.RegisterSelectActivity;
import com.rcpt.ui.system.SystemMainActivity;

/**
 * Created by lvzp on 2017/2/21.
 * 类描述：
 */

public class LoginPresenter extends BasePresenter<LoginContract.View> implements LoginContract.Presenter {

    private RequestModule mRequestModule;

    @Override
    public void attach(LoginContract.View view) {
        super.attach(view);
        mRequestModule = new RequestModule();
    }

    @Override
    public void onClickLogin() {

        getView().getInputPassword();
        String userName = getView().getInputUserName();
        String password = getView().getInputPassword();
        if (TextUtils.isEmpty(userName) || TextUtils.isEmpty(password)) {
            getView().showToast("帐号或密码不可为空");
            return;
        }

        mRequestModule.startLogin(userName, password, new ProgressSubscriber<>(getView().getContext(), new RequestImpl<HttpResult<User>>() {
            @Override
            public void onNext(HttpResult<User> userHttpResult) {
                getView().showToast(userHttpResult.getMsg());
                LoginHelper.getInstance().userExit();
                User user = userHttpResult.getData();
                getView().saveUser(user);
                LoginHelper.getInstance().setOnline(true);
                mRequestModule.saveCID(user.getUserName(), PushManager.getInstance().getClientid(getView().getContext()));
                //如果登录的用户为管理员用户，则通知MainActivity进行关闭，那么就会走他的onActivityResult()方法
                if (Constant.UserType.MANAGE.getValue().equals(user.getUserType())) {
                    getView().closeMain();
                } else {
                    getView().actionStartActivity(MainActivity.class);
                }
                getView().closeActivity();
            }
        }));


    }

    @Override
    public void onClickGoRegister() {
        getView().actionStartActivity(RegisterSelectActivity.class);
    }

    @Override
    public void onClickForgetPassword() {
        getView().actionStartActivity(ForgetPasswordActivity.class);
    }

    @Override
    public void onClickClose() {
        getView().closeActivity();
    }
}
