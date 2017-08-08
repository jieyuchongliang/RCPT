package com.rcpt.mvp.contract;

import android.app.Activity;

import com.rcpt.base.mvp.BaseView;

/**
 * Created by lvzp on 2017/2/21.
 * 类描述：
 */

public interface RegisterSelectContract {


    interface View extends BaseView {

        void startGoRegister(Class<? extends Activity> activityClass);
    }

    interface Presenter {
        void onClickGoPersonRegister();

        void onClickGoGroupRegister();
    }

}
