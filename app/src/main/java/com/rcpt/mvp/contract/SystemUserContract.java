package com.rcpt.mvp.contract;

import android.support.annotation.IdRes;

import com.rcpt.base.mvp.BaseView;
import com.rcpt.base.mvp.OnDataGetCallback;
import com.rcpt.base.mvp.PullToRefeshContract;
import com.rcpt.base.mvp.RecyclerViewContract;
import com.rcpt.bean.AttrSelectBean;
import com.rcpt.bean.SystemUserInfoBean;
import com.rcpt.bean.SystemUserListBean;

import java.util.List;

/**
 * 系统用户的契约类
 */

public interface SystemUserContract {


    interface View extends BaseView, RecyclerViewContract.View<SystemUserListBean.UserListBean>, PullToRefeshContract.View {
        /**
         * 显示筛选的PopWindow
         *
         * @param selectVale
         */
        void showSelectPop(List<String> listData, String selectVale);

        /**
         * 设置筛选Pop的标题
         *
         * @param popTitle
         */
        void setSelectPopTitle(String popTitle);

        /**
         * 获取账户类型的名称
         *
         * @return
         */
        String getSelectRoleName();

        /**
         * 获取所属单位的名称
         *
         * @return
         */
        String getSelectAddressName();

        void updatePupSelectData(@IdRes int viewId, String selectId);

        /**
         * 获取筛选列表的点击条目Position
         *
         * @return
         */
        int getClickSelectListPosition();

        /**
         * 获取筛选列表的选择的值
         *
         * @return
         */
        String getSelectValue();

        /**
         * 系统用户是否处于编辑状态
         *
         * @return
         */
        boolean isEditSystemUser();

        /**
         * 获取系统用户的实体类
         *
         * @return
         */
        SystemUserInfoBean getUploadSystemUserBean();

        /**
         * 获取点击列表中点击Item的Position
         *
         * @return
         */
        int getClickListItemPosition();

        /**
         * 删除列表中的某一个Item
         *
         * @param position
         */
        void removeAdapterItem(int position);

    }

    interface Presenter extends RecyclerViewContract.Presenter, PullToRefeshContract.Presenter {
        /**
         * 点击选择管理员类型的筛选列表
         */
        void onClickOpenRoleSelect();

        /**
         * 点击开启商务局的筛选列表
         */
        void onClickOpenAddressSelect();

        /**
         * 当所属单位或账户类型选择以后进行的回调
         */
        void onSelectValueCallback();

        /**
         * 上传系统用户的信息
         */
        void uploadSystemUser(OnDataGetCallback<Boolean> callback);

        void loadListDataWithClean();

        /**
         * 删除系统用户
         */
        void onClickDelSystemUser();

        /**
         * 重置用户的密码
         *
         * @param password
         */
        void onClickResetSystemUserPassword(String password);
    }

}
