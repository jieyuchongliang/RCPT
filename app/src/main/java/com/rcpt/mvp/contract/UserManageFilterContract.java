package com.rcpt.mvp.contract;

import com.rcpt.base.mvp.BaseView;
import com.rcpt.bean.UserManageFilterBean;

/**
 * Created by 860617003 on 2017/6/8.
 */

public interface UserManageFilterContract {

    interface View extends BaseView {
        /**
         * 开启地址选择
         */
        void startGoAddressSelect();


        /**
         * 获取用户类型的值
         *
         * @return
         */
        String getUserTypeValue();

        /**
         * 获取用户状态的值
         *
         * @return
         */
        String getUserStatusValue();

        /**
         * 更新筛选用户状态的id
         *
         * @param id
         */
        void updateUserStatusId(String id);

        /**
         * 更新用户类型的状态id
         *
         * @param id
         */
        void updateUserTypeId(String id);

        /**
         * 是否为团体类型用户
         *
         * @return
         */
        boolean isGroup();

        /**
         * 获取筛选条件的实体类
         *
         * @return
         */
        UserManageFilterBean getFilterBean();

        /**
         * 更新用户状态选择的Position
         *
         * @param position
         */
        void setupUserStatusSelectPosition(int position);

        /**
         * 更新用户类型选择的Position
         *
         * @param position
         */
        void setupUserTypeSelectPosition(int position);

        /**
         * 清空所有筛选条件
         */
        void cleanFilterAll();
    }

    interface Presenter {
        /**
         * 初始化选择的数据
         */
        void initSelectData();

        /**
         * 检测初始值的信息
         */
        void checkInitData();

        /**
         * 点击开启地址选择的页面
         */
        void onClickGoSelectAddress();

        /**
         * 当用户状态被筛选时的调用
         */
        void onUserStatusSelect();

        /**
         * 当用户类型被筛选时的回调
         */
        void onUserTypeStatus();

    }

}
