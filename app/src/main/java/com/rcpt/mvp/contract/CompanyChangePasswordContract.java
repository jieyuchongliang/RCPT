package com.rcpt.mvp.contract;

import com.rcpt.base.mvp.BaseView;

/**
 * Created by lvzp on 2017/4/18.
 * 类描述：
 */

public interface CompanyChangePasswordContract {

    interface View extends BaseView {
        /**
         * 获取原有密码
         *
         * @return
         */
        String getOldPassword();

        /**
         * 获取新密码
         *
         * @return
         */
        String getNewPassword();

        /**
         * 获取再次输入的密码
         *
         * @return
         */
        String getAgainPassword();
    }

    interface Presenter {
        /**
         * 点击保存
         */
        void onClickSave();
    }

}
