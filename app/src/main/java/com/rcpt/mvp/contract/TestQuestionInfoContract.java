package com.rcpt.mvp.contract;

import com.rcpt.base.mvp.BaseView;
import com.rcpt.base.mvp.RecyclerViewContract;
import com.rcpt.bean.TestAnswerBean;
import com.rcpt.bean.TestQuestionInfoBean;

/**
 * Created by lvzp on 2017/4/7.
 * 类描述：
 */

public interface TestQuestionInfoContract {

    interface View extends BaseView, RecyclerViewContract.View<TestQuestionInfoBean.ContentBean> {
        /**
         * 获取当前显示的View的Position
         *
         * @return
         */
        int getCurrentVisiblePosition();

        /**
         * 获取题目的总数量
         *
         * @return
         */
        int getQuestionCount();

        /**
         * 将题目滚动到相应的位置
         *
         * @param position
         */
        void scrollViewToPosition(int position);

        /**
         * 显示Sanckbar提示框
         *
         * @param text
         */
        void showSnackbar(String text);

        /**
         * 获取试卷的id
         *
         * @return
         */
        String getTestId();

        /**
         * 获取测试的类型
         *
         * @return
         */
        String getTestType();

        /**
         * 显示测试成绩的页面Dialog
         *
         * @param bean
         */
        void showDialog(TestAnswerBean bean);
    }

    interface Presenter extends RecyclerViewContract.Presenter {
        /**
         * 上一题的点击事件
         */
        void onClickGoLast();

        /**
         * 下一题的点击事件
         */
        void onClickGoNext();

        /**
         * 交卷的点击事件
         */
        void onClickHandExams();

    }

}
