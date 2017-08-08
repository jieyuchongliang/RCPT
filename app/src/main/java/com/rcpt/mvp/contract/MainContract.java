package com.rcpt.mvp.contract;

import com.rcpt.base.mvp.BaseView;

/**
 * Created by lvzp on 2017/2/21.
 * 类描述：
 */

public interface MainContract {

    interface View extends BaseView {
        void changeCurrentItem(int position);
    }

    interface Presenter {

    }

}
