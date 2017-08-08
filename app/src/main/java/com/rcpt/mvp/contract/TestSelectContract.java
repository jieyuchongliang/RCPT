package com.rcpt.mvp.contract;

import com.rcpt.base.mvp.BaseView;

/**
 * Created by lvzp on 2017/4/1.
 * 类描述：
 */

public interface TestSelectContract {

    interface View extends BaseView {
        void actionStartGoTestHint(String testType);
    }

    interface Presenter {
        /**
         * 进入道专业测试界面
         */
        void onClickGoMajorTest();

        /**
         * 进入到能力测试界面
         */
        void onClickGoAbilityTest();

        /**
         * 进入到职业测试
         */
        void onClickGoProfessionTest();

    }

}
