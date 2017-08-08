package com.rcpt.mvp.contract;

import com.rcpt.base.mvp.BaseView;
import com.rcpt.bean.BidInfoBean;

/**
 * Created by lvzp on 2017/2/24.
 * 类描述：
 */

public interface BidInfoContract {

    interface View extends BaseView {
        String getBidId();

        void setBean(BidInfoBean bean);
    }

    interface Presenter {
        void loadInfo();

        void onCloseClick();

        void onClickBid();
        //竞标成功与否的回调
        void isBidSuccess(String result);
    }

}
