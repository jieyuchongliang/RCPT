package com.rcpt.mvp.contract;

import android.widget.TextView;

import com.rcpt.base.mvp.BaseView;
import com.rcpt.base.mvp.PullToRefeshContract;
import com.rcpt.base.mvp.RecyclerViewContract;
import com.rcpt.bean.CompanyRecruitAcceptNumListBean;

/**
 * Created by lvzp on 2017/5/2.
 * 类描述：
 */

public interface CompanyRecruitAcceptNumListContract {

    interface View extends BaseView, RecyclerViewContract.View<CompanyRecruitAcceptNumListBean.JobPeopleListBean>, PullToRefeshContract.View {
        /**
         * 获取招聘信息id
         *
         * @return
         */
        String getRecruitmentInfoId();

        /**
         * 隐藏发送面试通知的Dialog
         */
        void hideSendNoticeDialog();

        /**
         * 显示发送面试通知的Dialog
         */
        void showSendNoticeDialog();

        /**
         * 显示日期时间选择器
         */
        void showDateTimeSelectDialog(long dateTime);

        /**
         * 获取开始的时间
         *
         * @return
         */
        String getSelectStartTime();

        /**
         * 获取结束的时间
         *
         * @return
         */
        String getSelectEndTime();

        /**
         * 获取内容描述
         */
        String getDescribeContentText();

        /**
         * 获取时间选择器选择后的数据
         *
         * @return
         */
        long getDateTimeSelectData();

        /**
         * 获取点击的条目
         *
         * @return
         */
        int getClickPosition();

        /**
         * 更新列表的条目
         *
         * @param position
         */
        void updateListItemForPosition(int position);
    }

    interface Presenter extends RecyclerViewContract.Presenter, PullToRefeshContract.Presenter {
        /**
         * 更新阅读状态
         */
        void updateReadingStatus(String cvId, String cvUserId);

        /**
         * 通知按钮的点击事件
         */
        void onClickSendNotice();

        /**
         * 点击取消
         */
        void onDialogClickCancel();

        /**
         * 点击确认
         */
        void onDialogClickConfirm();

        /**
         * 开启点击的
         */
        void onDialogClickOpenDateSelect(TextView view);


        /**
         * 当时间选择器的
         */
        void onDialogClickSetting();
    }

}
