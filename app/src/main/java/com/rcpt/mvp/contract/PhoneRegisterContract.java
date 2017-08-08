package com.rcpt.mvp.contract;

import com.rcpt.base.mvp.BaseView;

/**
 * Created by lvzp on 2017/2/21.
 * 类描述：
 */

public interface PhoneRegisterContract {

    interface View extends BaseView {
        String getInputPhoneNum();

        String getInputUsername();

        String getInputPassword();

        String getInputRPassword();

        String getInputVerify();

        boolean checkInputPhoneNumEmpty();

        boolean checkInputUsernameEmpty();

        boolean checkInputPasswordEmpty();

        boolean checkRPassword();

        boolean checkInputVerifyEmpty();

        /**
         * 开始进行倒计时
         */
        void startCountDownTimer();

        /**
         * 返回注册成功的消息
         */
        void resetRegisterOk();
    }

    interface Presenter {
        void onClickGetVerify();

        void onClickSubmit();
        /**
         * 进入注册条款界面
         */
        void onClickGoRegisterClause();
    }
}
