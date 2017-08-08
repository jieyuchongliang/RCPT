package com.rcpt.mvp.presenter;

import android.text.TextUtils;

import com.rcpt.base.mvp.BasePresenter;
import com.rcpt.bean.UserManageFilterBean;
import com.rcpt.mvp.contract.UserManageFilterContract;
import com.rcpt.mvp.module.UserManageFilterModule;

import java.util.List;

/**
 * 会员管理筛选条件的Presenter
 */

public class UserManageFilterPresenter extends BasePresenter<UserManageFilterContract.View> implements UserManageFilterContract.Presenter {

    private UserManageFilterModule mModule;


    @Override
    public void attach(UserManageFilterContract.View view) {
        super.attach(view);
        mModule = new UserManageFilterModule();
    }

    /**
     * 点击开启地址选择的页面
     */
    @Override
    public void onClickGoSelectAddress() {
        getView().startGoAddressSelect();
    }

    /**
     * 初始化选择的数据
     */
    @Override
    public void initSelectData() {
        mModule.buildData(getView().getContext());
    }

    /**
     * 检测初始值的信息
     */
    @Override
    public void checkInitData() {
        UserManageFilterBean filterBean = getView().getFilterBean();
        if (filterBean != null) {
            boolean group = getView().isGroup();
            //对用户状态的值进行处理
            int userStatusPosition = -1;
            List<String> statusList;
            if (group) {
                statusList = mModule.getGroupUserStatusList();
            } else {
                statusList = mModule.getPersonUserStatusList();
            }
            if (!TextUtils.isEmpty(filterBean.getUserStatus()))
                //根据之前筛选的用户状态值，获取出其在列表中的位置
                userStatusPosition = statusList.indexOf(filterBean.getUserStatus());
            if (userStatusPosition >= 0)
                getView().setupUserStatusSelectPosition(userStatusPosition);
            //对用户类型的值进行处理
            int userTypePosition = -1;
            if (!TextUtils.isEmpty(filterBean.getUserType()))
                //根据之前筛选的用户类型值，获取出其在列表中的位置
                userTypePosition = mModule.getGroupUserTypeList().indexOf(filterBean.getUserType());
            if (userTypePosition >= 0)
                getView().setupUserTypeSelectPosition(userTypePosition);
        }
    }

    @Override
    public void onUserStatusSelect() {
        String userStatus = getView().getUserStatusValue();
        boolean group = getView().isGroup();
        if (group) {
            String value = mModule.getGroupUserStatus().get(userStatus);
            if (!TextUtils.isEmpty(value)) {
                getView().updateUserStatusId(value);
            } else {
                getView().showToast("筛选信息异常");
            }
        } else {
            String value = mModule.getPersonUserStatus().get(userStatus);
            if (!TextUtils.isEmpty(value)) {
                getView().updateUserStatusId(value);
            } else {
                getView().showToast("筛选信息异常");
            }
        }
    }



    @Override
    public void onUserTypeStatus() {
        String userType = getView().getUserTypeValue();
        String value = mModule.getGroupUserTypes().get(userType);
        if (!TextUtils.isEmpty(value)) {
            getView().updateUserTypeId(value);
        } else {
            getView().showToast("筛选信息异常");
        }
    }
}
