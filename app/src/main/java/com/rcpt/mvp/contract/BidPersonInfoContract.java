package com.rcpt.mvp.contract;

import com.rcpt.base.mvp.BaseView;
import com.rcpt.bean.BidPersonInfoBean;

/**
 * Created by 860116021 on 2017/4/19.
 */

public interface BidPersonInfoContract {

    interface View extends BaseView{
        String getBidPersonId();

        void setBean(BidPersonInfoBean bean);

    }

    interface Presenter{
        void loadPersonInfo();

        void onCloseClick();

        void onClickBid();
        //竞标成功与否的回调
        void isBidSuccess(String result);
    }
}
