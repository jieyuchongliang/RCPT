package com.rcpt.mvp.contract;

import com.rcpt.base.mvp.BaseView;

/**
 * Created by lvzp on 2017/2/21.
 * 类描述：
 */

public interface ForgetPasswordPhoneContract {

    interface View extends BaseView {

        void inputPhoneOut();

        void sendVerifyIn();

        void sendVerifyOut();

        void saveNewPasswordIn();

        void setNumProgress(int progress);

        void showSendVerifyHint(CharSequence hint);

        void showSavePasswordHint(CharSequence hint);

        String getInputPhoneNum();

        String getInputVerify();

        String getInputNewPassword();

        String getInputVerifyHint();

        String getSaveNewPasswordHint();

        void startCountDownTimer();


        boolean checkVerifyInputEmpty();

        boolean checkPhoneInputEmpty();

        boolean checkNewPasswordInputEmpty();
    }

    interface Presenter {
        void onClickGetVerify();

        void onClickInputPhoneNext();

        void onClickSendVerifyNext();

        void onClickSaveNewPassword();
    }

}
