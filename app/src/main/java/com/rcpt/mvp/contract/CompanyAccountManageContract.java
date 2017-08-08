package com.rcpt.mvp.contract;

import com.rcpt.base.mvp.BaseView;

/**
 * Created by lvzp on 2017/4/18.
 * 类描述：
 */

public interface CompanyAccountManageContract {

    interface View extends BaseView {

    }

    interface Presenter {
        /**
         * 点击修改密码
         */
        void onClickChangePassword();
    }

}
