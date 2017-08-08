package com.rcpt.mvp.contract;

import com.rcpt.base.mvp.BaseView;
import com.rcpt.base.mvp.ViewPagerWithGroupContract;

/**
 * Created by 860617003 on 2017/6/2.
 */

public interface UserManageContract {

    interface View extends BaseView, ViewPagerWithGroupContract.View {

    }

    interface Presenter extends ViewPagerWithGroupContract.Presenter {

    }

}
