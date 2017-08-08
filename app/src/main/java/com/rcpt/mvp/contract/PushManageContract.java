package com.rcpt.mvp.contract;

import com.rcpt.base.mvp.BaseView;

/**
 * Created by 860617003 on 2017/6/2.
 */

public interface PushManageContract {

    interface View extends BaseView {
        /**
         * 获取用户的类型
         *
         * @return
         */
        String getUserType();

        String getPushTitle();

        String getMessage();

        void closeActivityForOk();
    }

    interface Presenter {
        /**
         * 点击发布消息
         */
        void onClickSendPush();
    }

}
