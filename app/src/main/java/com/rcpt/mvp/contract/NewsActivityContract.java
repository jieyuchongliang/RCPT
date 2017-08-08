package com.rcpt.mvp.contract;

import com.rcpt.base.mvp.BaseView;
import com.rcpt.base.mvp.ViewPagerWithTabContract;

/**
 * Created by lvzp on 2017/2/23.
 * 类描述：
 */

public interface NewsActivityContract {

    interface View extends BaseView, ViewPagerWithTabContract.View {

    }

    interface Presenter extends ViewPagerWithTabContract.Presenter {

    }

}