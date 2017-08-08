package com.rcpt.mvp.contract;

import android.widget.TextView;

import com.rcpt.base.mvp.BaseView;
import com.rcpt.bean.CompanyRecruitListBean;

/**
 * Created by lvzp on 2017/4/17.
 * 类描述：
 */

public interface SendJobInvitationContract {

    interface View extends BaseView {
        /**
         * 显示日期时间选择器
         */
        void showDateTimeSelectDialog(long dateTime);

        /**
         * 获取时间选择器选择后的数据
         *
         * @return
         */
        long getDateTimeSelectData();

        /**
         * 获取备注信息
         *
         * @return
         */
        String getRemarks();

        /**
         * 获取简历Id
         *
         * @return
         */
        String getCVId();

        /**
         * 获取简历用户的id
         * @return
         */
        String getCVUserId();

        /**
         * 获取招聘职位的信息
         * @return
         */
        CompanyRecruitListBean.RecruitlistBean getRecruitBean();

        /**
         * 当发送成功后，关闭页面
         */
        void onSendSuccessCloseActivity();
    }

    interface Presenter {
        /**
         * 点击显示开始时间的Dialog
         */
        void onClickShowStartTimeDialog(TextView tv);

        /**
         * 点击显示结束时间的Dialog
         */
        void onClickShowEndTimeDialog(TextView tv);

        /**
         * 点击发送职位邀请的按钮
         */
        void onClickSendJobInvitation();

        /**
         * Dialog中的设置按钮点击时执行的方法
         */
        void onDialogClickSetting();

    }

}
