package com.rcpt.mvp.contract;

/**
 * Created by lvzp on 2017/4/6.
 * 类描述：
 */

public interface TestMajorQuestionInfoContract {

    interface View extends TestQuestionInfoContract.View {

        /**
         * 绑定测试题的标题到页面
         *
         * @param testName
         */
        void bindTestName(String testName);

        /**
         * 设置测试题的用时
         *
         * @param time
         */
        void setupTestUsedTime(int time);
    }

    interface Presenter {

    }

}
