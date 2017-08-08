package com.rcpt.mvp.contract;

import android.databinding.ViewDataBinding;

import com.rcpt.base.adapter.BindingViewHolder;
import com.rcpt.base.mvp.BaseView;
import com.rcpt.base.mvp.PullToRefeshContract;
import com.rcpt.base.mvp.RecyclerViewContract;
import com.rcpt.bean.UserManageFilterBean;
import com.rcpt.bean.UserManageListBean;
import com.rcpt.widget.ExpandLayout;

/**
 * Created by 860617003 on 2017/6/2.
 */

public interface UserManageFragmentContract {

    interface View extends BaseView, RecyclerViewContract.View<UserManageListBean.UserInfoBean>, PullToRefeshContract.View {
        /**
         * 获取用户的类型
         *
         * @return
         */
        String getUserType();

        /**
         * 获取点击条目的位置
         *
         * @return
         */
        int getClickItemPosition();

        /**
         * 更新适配器的对应Position的Item
         *
         * @param position
         */
        void notifyAdapterItem(int position);

        /**
         * 删除适配器中的某个条目
         *
         * @param position
         */
        void removeAdapterItem(int position);

        void updateLayoutCollapse();

        void updateLayoutExpand();

        /**
         * 显示输入密码的提示框
         */
        void showInputPasswordDialog();

        /**
         * 是否为个人的列表
         *
         * @return
         */
        boolean isPerson();

        /**
         * 获取用于封装用户筛选的条件的实体类
         *
         * @return
         */
        UserManageFilterBean getFilterBean();
    }

    interface Presenter extends RecyclerViewContract.Presenter, PullToRefeshContract.Presenter {

        /**
         * 当点击展开布局时调用的方法
         */
        void onClickExpandLayout();

        /**
         * 重置密码
         */
        void onClickRestPassword();

        /**
         * 当输入完密码后的回调
         *
         * @param password
         */
        void onInputPasswordCallback(String password);

        /**
         * 点击锁定用户
         */
        void onClickLocked();

        /**
         * 解锁用户
         */
        void onClickUnlocked();

        /**
         * 审核通过
         */
        void onClickAdopt();

        /**
         * 点击删除用户
         */
        void onClickDelUser();

        /**
         * 驳回用户
         */
        void onClickFailUser();
    }

}
