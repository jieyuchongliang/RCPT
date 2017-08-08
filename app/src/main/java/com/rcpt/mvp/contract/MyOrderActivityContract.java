package com.rcpt.mvp.contract;

import com.rcpt.base.mvp.BaseView;
import com.rcpt.base.mvp.ViewPagerWithTabContract;

/**
 * Created by 860116021 on 2017/5/10.
 */

public interface MyOrderActivityContract {

    interface View extends BaseView, ViewPagerWithTabContract.View{

    }

    interface Presenter extends ViewPagerWithTabContract.Presenter{

    }
}
