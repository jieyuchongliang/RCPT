package com.rcpt.mvp.presenter;

import com.rcpt.base.mvp.BasePresenter;
import com.rcpt.mvp.contract.RegisterSelectContract;
import com.rcpt.ui.register.group.RegisterGroupActivity;
import com.rcpt.ui.register.person.RegisterPersonActivity;

/**
 * Created by lvzp on 2017/2/21.
 * 类描述：
 */

public class RegisterSelectPresenter extends BasePresenter<RegisterSelectContract.View> implements
        RegisterSelectContract.Presenter {
    @Override
    public void onClickGoPersonRegister() {
        getView().startGoRegister(RegisterPersonActivity.class);
    }

    @Override
    public void onClickGoGroupRegister() {
        getView().startGoRegister(RegisterGroupActivity.class);
    }
}
