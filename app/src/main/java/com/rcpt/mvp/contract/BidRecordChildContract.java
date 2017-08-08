package com.rcpt.mvp.contract;

import com.rcpt.base.mvp.BaseView;
import com.rcpt.base.mvp.ViewPagerWithTabContract;

/**
 * Created by 860617003 on 2017/5/25.
 */

public interface BidRecordChildContract {

    interface View extends BaseView, ViewPagerWithTabContract.View {
        /**
         * 获取投标的类型
         *
         * @return
         */
        String getBidType();
    }

    interface Presenter extends ViewPagerWithTabContract.Presenter {

    }

}
