package com.rcpt.mvp.contract;

import com.rcpt.base.mvp.BaseView;
import com.rcpt.bean.TestHintBean;

/**
 * Created by lvzp on 2017/4/1.
 * 类描述：
 */

public interface TestHintContract {

    interface View extends BaseView {
        /**
         * 获取测试的类型
         *
         * @return
         */
        String getTestType();

        /**
         * 绑定页面的数据
         */
        void bindPageData(TestHintBean bean);
    }

    interface Presenter {
        /**
         * 初始化页面数据
         */
        void initPageData();

        /**
         * 开始测试的点击事件
         */
        void onClickStartTest();
    }

}
