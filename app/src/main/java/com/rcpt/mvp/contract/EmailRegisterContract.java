package com.rcpt.mvp.contract;

import com.rcpt.base.mvp.BaseView;

/**
 * Created by lvzp on 2017/2/21.
 * 类描述：
 */

public interface EmailRegisterContract {


    interface View extends BaseView {

        String getInputEmail();

        String getInputAccount();

        String getInputPassword();

        String getInputAgainPassword();

        String getInputVerify();

        void startCountDownTimer();

        /**
         * 返回注册成功的消息
         */
        void resetRegisterOk();
    }

    interface Presenter {
        void onClickRegister();

        void onClickGetVerify();

        /**
         * 进入注册条款界面
         */
        void onClickGoRegisterClause();
    }

}
