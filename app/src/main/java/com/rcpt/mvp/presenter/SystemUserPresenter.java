package com.rcpt.mvp.presenter;

import com.rcpt.R;
import com.rcpt.base.mvp.BasePresenter;
import com.rcpt.base.mvp.OnDataGetCallback;
import com.rcpt.bean.AttrSelectBean;
import com.rcpt.bean.SystemUserListBean;
import com.rcpt.http.api.ApiClient;
import com.rcpt.mvp.contract.SystemUserContract;
import com.rcpt.mvp.module.SystemUserModule;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by 860617003 on 2017/6/7.
 */

public class SystemUserPresenter extends BasePresenter<SystemUserContract.View> implements SystemUserContract.Presenter {

    private SystemUserModule mModule;
    private int mSelectViewId;

    @Override
    public void attach(SystemUserContract.View view) {
        super.attach(view);
        mModule = new SystemUserModule();
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
        mModule.requestSystemUserList(getView().getContext(), keyword, page, new OnDataGetCallback<List<SystemUserListBean.UserListBean>>() {
            @Override
            public void onSuccessResult(List<SystemUserListBean.UserListBean> data) {
                getView().updateIsEnd(mModule.isEnd());
                getView().bindListData(data);
            }
        });
    }

    @Override
    public void loadListData() {
        onLoadMore(1);
    }

    //加载页面数据，并清空列表中的缓存数据
    public void loadListDataWithClean() {
        if (mModule.getListData() != null)
            mModule.getListData().clear();
        loadListData();
    }

    /**
     * 点击选择管理员类型的筛选列表
     */
    @Override
    public void onClickOpenRoleSelect() {
        mSelectViewId = R.id.tv_role;
        getView().setSelectPopTitle("账户类型选择");
        if (mModule.getRoleList() == null) {
            mModule.requestGetSystemUserFilterList(getView().getContext(), new OnDataGetCallback<Void>() {
                @Override
                public void onSuccessResult(Void data) {
                    showRolePop();
                }
            });
        } else {
            showRolePop();
        }
    }

    private void showRolePop() {
        showSelectPop(mModule.getRoleList(), false, getView().getSelectRoleName());
    }

    private void showAddressPop() {
        showSelectPop(mModule.getAreaList(), true, getView().getSelectAddressName());
    }

    private void showSelectPop(List<AttrSelectBean> sourList, boolean isAdd, String selectValue) {
        List<String> listData = new ArrayList<>();
        for (AttrSelectBean bean : sourList) {
            if (isAdd)
                listData.add(bean.getValue() + "商务局");
            else
                listData.add(bean.getValue());
        }
        getView().showSelectPop(listData, selectValue);
    }

    /**
     * 当所属单位或账户类型选择以后进行的回调
     */
    @Override
    public void onSelectValueCallback() {
        int position = getView().getClickSelectListPosition();
        String selectId = null;
        switch (mSelectViewId) {
            case R.id.tv_role:
                selectId = mModule.getRoleList().get(position).getDistinguishId();
                break;
            case R.id.tv_authorities:
                selectId = mModule.getAreaList().get(position).getDistinguishId();
                break;
        }
        getView().updatePupSelectData(mSelectViewId, selectId);
    }

    /**
     * 点击开启商务局的筛选列表
     */
    @Override
    public void onClickOpenAddressSelect() {
        mSelectViewId = R.id.tv_authorities;
        if (mModule.getAreaList() == null) {
            mModule.requestGetSystemUserFilterList(getView().getContext(), new OnDataGetCallback<Void>() {
                @Override
                public void onSuccessResult(Void data) {
                    showAddressPop();
                }
            });
        } else {
            showAddressPop();
        }
    }

    /**
     * 上传系统用户的信息
     */
    @Override
    public void uploadSystemUser(final OnDataGetCallback<Boolean> callback) {
        boolean isEdit = getView().isEditSystemUser();
        Map<String, String> params = ApiClient.createBodyMap(getView().getUploadSystemUserBean());
        if (isEdit) {
            mModule.updateSystemUser(getView().getContext(), params, new OnDataGetCallback<String>() {
                @Override
                public void onSuccessResult(String data) {
                    getView().showToast(data);
                    callback.onSuccessResult(true);
                }
            });
        } else {
            mModule.saveSystemUser(getView().getContext(), params, new OnDataGetCallback<String>() {
                @Override
                public void onSuccessResult(String data) {
                    getView().showToast(data);
                    callback.onSuccessResult(true);
                }
            });
        }
    }

    @Override
    public void onClickDelSystemUser() {
        final int position = getView().getClickListItemPosition();
        SystemUserListBean.UserListBean userListBean = mModule.getListData().get(position);
        mModule.delSystemUser(getView().getContext(), userListBean.getUSER_ID(), new OnDataGetCallback<String>() {
            @Override
            public void onSuccessResult(String data) {
                getView().showToast(data);
                mModule.getListData().remove(position);
                getView().removeAdapterItem(position);
            }
        });
    }

    /**
     * 重置用户的密码
     *
     * @param password
     */
    @Override
    public void onClickResetSystemUserPassword(String password) {
        int position = getView().getClickListItemPosition();
        SystemUserListBean.UserListBean userListBean = mModule.getListData().get(position);
        mModule.resetSystemUserPassword(getView().getContext(), userListBean.getUSER_ID(), userListBean.getUSERNAME(), password, new OnDataGetCallback<String>() {
            @Override
            public void onSuccessResult(String data) {
                getView().showToast(data);
            }
        });
    }

    private String keyword;
    public void setKeyWord(String searchText) {
        keyword = searchText;
    }
}
