package com.rcpt.mvp.contract;

import com.rcpt.base.mvp.BaseView;
import com.rcpt.bean.User;

/**
 * Created by lvzp on 2017/2/21.
 * 类描述：
 */

public interface LoginContract {

    interface View extends BaseView {
        String getInputUserName();

        String getInputPassword();

        void saveUser(User user);

        void closeMain();
    }

    interface Presenter {
        void onClickLogin();

        void onClickGoRegister();

        void onClickForgetPassword();

        void onClickClose();
    }

}
