package com.rcpt.mvp.presenter;

import com.rcpt.LoginHelper;
import com.rcpt.base.mvp.BasePresenter;
import com.rcpt.base.mvp.OnDataGetCallback;
import com.rcpt.bean.UserManageFilterBean;
import com.rcpt.bean.UserManageListBean;
import com.rcpt.mvp.contract.UserManageFragmentContract;
import com.rcpt.mvp.module.UserManageFragmentModule;

import java.util.List;

/**
 * Created by 860617003 on 2017/6/2.
 */

public class UserManageFragmentPresenter extends BasePresenter<UserManageFragmentContract.View> implements UserManageFragmentContract.Presenter {

    private UserManageFragmentModule mModule;


    @Override
    public void attach(UserManageFragmentContract.View view) {
        super.attach(view);
        mModule = new UserManageFragmentModule();
        getView().initRecyclerView();
    }

    /**
     * 加载更多的数据
     * 只需要根据相应的页码加载相应的数据，无需关心刷新和加载更多
     *
     * @param page
     */
    @Override
    public void onLoadMore(int page) {
        UserManageFilterBean filterBean = getView().getFilterBean();
        String mFilterUsername;
        String mFilterUserStatus;
        String mFilterCompanyName;
        String mFilterUserType;
        String mFilterProvince;
        String mFilterCity;
        if (filterBean != null && !filterBean.isEmpty()) {
            mFilterUserType = filterBean.getUserTypeId();
            mFilterUserStatus = filterBean.getUserStatusId();
            mFilterCity = filterBean.getCityId();
            mFilterProvince = filterBean.getProvinceId();
            mFilterCompanyName = filterBean.getGroupName();
            mFilterUsername = filterBean.getUserName();
        } else {
            mFilterUserType = null;
            mFilterUserStatus = null;
            mFilterCity = null;
            mFilterProvince = null;
            mFilterCompanyName = null;
            mFilterUsername = null;
        }
        mModule.requestUserList(getView().getContext()
                , mFilterUserType
                , mFilterUserStatus
                , mFilterUsername
                , mFilterCompanyName
                , mFilterProvince
                , mFilterCity
                , getView().getUserType()
                , page
                , new OnDataGetCallback<List<UserManageListBean.UserInfoBean>>() {
                    @Override
                    public void onSuccessResult(List<UserManageListBean.UserInfoBean> data) {
                        getView().updateIsEnd(mModule.isEnd());
                        getView().bindListData(data);
                    }
                });
    }

    @Override
    public void loadListData() {
        onLoadMore(1);
    }


    /**
     * 当点击展开布局时调用的方法
     */
    @Override
    public void onClickExpandLayout() {
        int position = getView().getClickItemPosition();
        UserManageListBean.UserInfoBean bean = mModule.getListData().get(position);
        if (bean.isExpand()) {
            bean.setExpand(false);
            getView().updateLayoutCollapse();
        } else {
            bean.setExpand(true);
            getView().updateLayoutExpand();
        }
    }

    /**
     * 重置密码
     */
    @Override
    public void onClickRestPassword() {
        getView().showInputPasswordDialog();
    }

    /**
     * 当输入完密码后的回调
     *
     * @param password
     */
    @Override
    public void onInputPasswordCallback(String password) {
        int position = getView().getClickItemPosition();
        UserManageListBean.UserInfoBean bean = mModule.getListData().get(position);
        String user_id = bean.getUser_id();
        mModule.requestChangePassword(getView().getContext(), password, user_id, new OnDataGetCallback<String>() {
            @Override
            public void onSuccessResult(String data) {
                getView().showToast(data);
            }
        });
    }

    /**
     * 点击锁定用户
     */
    @Override
    public void onClickLocked() {
        final int position = getView().getClickItemPosition();
        final String status = "4";
        final UserManageListBean.UserInfoBean bean = mModule.getListData().get(position);
        String userId = bean.getUser_id();
        if (getView().isPerson()) {
            changePersonStatus(userId, status, position);
        } else {
            mModule.requestLockedUser(getView().getContext(), userId, LoginHelper.getInstance().getUserBean().getUserName(), new OnDataGetCallback<String>() {
                @Override
                public void onSuccessResult(String data) {
                    changeListStatus(bean, status, position);
                    getView().showToast(data);
                }
            });
        }
    }

    /**
     * 解锁用户
     */
    @Override
    public void onClickUnlocked() {
        int position = getView().getClickItemPosition();
        String status = "2";
        UserManageListBean.UserInfoBean bean = mModule.getListData().get(position);
        String userId = bean.getUser_id();
        if (getView().isPerson()) {
            changePersonStatus(userId, status, position);
        } else {
            updateUserStatusNormal(position, status, bean, userId);
        }
    }

    /**
     * 审核通过
     */
    @Override
    public void onClickAdopt() {
        int position = getView().getClickItemPosition();
        UserManageListBean.UserInfoBean bean = mModule.getListData().get(position);
        String user_id = bean.getUser_id();
        updateUserStatusNormal(position, "2", bean, user_id);
    }

    /**
     * 点击删除用户
     */
    @Override
    public void onClickDelUser() {
        final int position = getView().getClickItemPosition();
        UserManageListBean.UserInfoBean bean = mModule.getListData().get(position);
        mModule.requestDelUser(getView().getContext(), bean.getUser_id(), String.valueOf(bean.getUser_type()), new OnDataGetCallback<String>() {
            @Override
            public void onSuccessResult(String data) {
                getView().showToast(data);
                mModule.getListData().remove(position);
                getView().removeAdapterItem(position);
            }
        });
    }

    /**
     * 驳回用户
     */
    @Override
    public void onClickFailUser() {
        final int position = getView().getClickItemPosition();
        final UserManageListBean.UserInfoBean bean = mModule.getListData().get(position);
        mModule.requestReviewFail(getView().getContext(), bean.getUser_id(), LoginHelper.getInstance().getUserBean().getUserName(), new OnDataGetCallback<String>() {
            @Override
            public void onSuccessResult(String data) {
                getView().showToast(data);
                changeListStatus(bean, "3", position);

            }
        });
    }

    /**
     * 更新用户状态，改为审核通过的状态
     *
     * @param position
     * @param status
     * @param bean
     * @param userId
     */
    private void updateUserStatusNormal(final int position, final String status, final UserManageListBean.UserInfoBean bean, String userId) {
        mModule.requestReviewUnlockUser(getView().getContext(), userId, LoginHelper.getInstance().getUserBean().getUserName(), String.valueOf(bean.getUser_type()), new OnDataGetCallback<String>() {
            @Override
            public void onSuccessResult(String data) {
                changeListStatus(bean, status, position);
                getView().showToast(data);
            }
        });
    }

    /**
     * 改变用户的状态
     *
     * @param userId
     * @param status
     * @param position
     */
    private void changePersonStatus(String userId, final String status, final int position) {
        mModule.requestUpdateStatus(getView().getContext(), userId, status, new OnDataGetCallback<String>() {
            @Override
            public void onSuccessResult(String data) {
                getView().showToast(data);
                UserManageListBean.UserInfoBean bean = mModule.getListData().get(position);
                changeListStatus(bean, status, position);
                getView().showToast(data);
            }
        });
    }

    /**
     * 修改列表的状态
     *
     * @param bean
     * @param status
     * @param position
     */
    private void changeListStatus(UserManageListBean.UserInfoBean bean, String status, int position) {
        bean.setUser_status(Integer.decode(status));
        if (getView().isPerson()) {
            bean.setStatus("被锁定");
        } else {
            if (bean.getUser_status() == 3) {
                bean.setStatus("未通过");
            } else if (bean.getUser_status() == 4) {
                bean.setStatus("锁定");
            }
        }
        getView().notifyAdapterItem(position);

    }
}
