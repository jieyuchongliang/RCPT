package com.rcpt.mvp.contract;

import com.rcpt.base.mvp.BaseView;
import com.rcpt.base.mvp.PullToRefeshContract;
import com.rcpt.base.mvp.RecyclerViewContract;
import com.rcpt.bean.InterviewMessageListBean;

/**
 * Created by lvzp on 2017/3/6.
 * 类描述：
 */

public interface InterviewMessageContract {

    interface View extends BaseView, RecyclerViewContract.View<InterviewMessageListBean.GetInterviewBean>, PullToRefeshContract.View {
        /**
         * 获取点击条目的位置
         *
         * @return
         */
        int getClickPosition();

        /**
         * 显示详情Dialog的数据
         */
        void showInfoDialog(InterviewMessageListBean.GetInterviewBean bean);

        /**
         * 关闭详情的Dialog
         */
        void infoDialogClose();

        /**
         * 获取拒绝的理由
         *
         * @return
         */
        String getDisagreeContent();

        /**
         * 显示输入拒绝理由的页面
         */
        void showInputDisagreeReasonLayout();

        /**
         * 隐藏设置面试通知状态的按钮布局
         */
        void hintDialogSettingLayout();

        /**
         * 显示Dialog中的确认按钮
         */
        void showDialogConfirm();

        /**
         * 更新列表中显示的某一条数据
         *
         * @param position
         */
        void updateListItem(int position);
    }

    interface Presenter extends RecyclerViewContract.Presenter, PullToRefeshContract.Presenter {
        /**
         * 点击面试邀请处理
         */
        void onClickDisposeInvitation();

        /**
         * 条目的点击事件
         */
        void onItemClick();

        /**
         * Dialog点击关闭
         */
        void onDialogClickClose();

        /**
         * Dialog点击同意
         */
        void onDialogClickAgree();

        /**
         * Dialog点击拒绝
         */
        void onDialogClickDisagree();

        /**
         * Dialog中点击拒绝后显示的确认的按钮
         */
        void onDialogClickConfirm();
    }

}
