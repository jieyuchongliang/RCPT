package com.rcpt.mvp.contract;

import com.rcpt.base.mvp.BaseView;
import com.rcpt.bean.BidContactsBean;

/**
 * Created by lvzp on 2017/4/19.
 * 类描述：
 */

public interface BidSendContract {

    interface View extends BaseView {
        /**
         * 获取竞标的联系人
         *
         * @return
         */
        String getBidContacts();

        /**
         * 获取竞标的手机号
         *
         * @return
         */
        String getBidPhone();

        /**
         * 获取竞标的邮箱
         *
         * @return
         */
        String getBidEmail();

        /**
         * 获取竞标的方案
         *
         * @return
         */
        String getBidContent();

        /**
         * 获取来自于啊哪里
         *
         * @return
         */
        String getFromWhere();

        /**
         * 获取投标的Id
         *
         * @return
         */
        String getBidId();

        /**
         * 获取竞标公司名称
         *
         * @return
         */
        String getBidCompanyName();

        /**
         * 绑定页面数据
         * @param bean
         */
        void bindPageData(BidContactsBean bean);
    }

    interface Presenter {
        /**
         * 加载页面的数据
         */
        void loadPageData();

        /**
         * 点击投标
         */
        void onClickSendBid();
    }
}
