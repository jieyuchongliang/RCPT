package com.rcpt.mvp.contract;

import com.rcpt.base.mvp.BaseView;

/**
 * Created by lvzp on 2017/3/6.
 * 类描述：
 */

public interface MessageContract {

    interface View extends BaseView {
        void onBindBadgeView(android.view.View targetView, int num);

        android.view.View getSystemMessageLayout();

        android.view.View getInterviewMessageLayout();
    }

    interface Presenter {
        void onClickGoSystemMessage();

        void onClickGoInterviewMessage();

    }

}
