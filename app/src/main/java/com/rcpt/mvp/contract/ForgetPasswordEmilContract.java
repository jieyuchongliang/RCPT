package com.rcpt.mvp.contract;

import com.rcpt.base.mvp.BaseView;

/**
 * Created by lvzp on 2017/2/21.
 * 类描述：
 */

public interface ForgetPasswordEmilContract {

    interface View extends BaseView {
        void inputEmailOut();

        void sendVerifyIn();

        void sendVerifyOut();

        void saveNewPasswordIn();

        void setNumProgress(int progress);

        void showSendVerifyHint(CharSequence hint);

        String getInputEmail();

        String getInputVerify();

        String getInputNewPassword();

        String getInputVerifyHint();

        String getSaveNewPasswordHint();

        void startCountDownTimer();

        void showSavePasswordHint(CharSequence hint);

        boolean checkVerifyInputEmpty();

        boolean checkEmailInputEmpty();

        boolean checkNewPasswordInputEmpty();

    }

    interface Presenter {
        void onClickGetVerify();

        void onClickInputEmailNext();

        void onClickSendVerifyNext();

        void onClickSaveNewPassword();
    }

}
