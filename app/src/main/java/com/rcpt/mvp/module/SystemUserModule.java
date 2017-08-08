package com.rcpt.mvp.module;

import android.content.Context;

import com.rcpt.base.HttpResult;
import com.rcpt.base.mvp.OnDataGetCallback;
import com.rcpt.bean.AttrSelectBean;
import com.rcpt.bean.SystemUserFilterBean;
import com.rcpt.bean.SystemUserListBean;
import com.rcpt.http.ProgressSubscriber;
import com.rcpt.http.RequestImpl;
import com.rcpt.http.RetrofitManager;
import com.rcpt.http.api.ApiClient;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by 860617003 on 2017/6/7.
 */

public class SystemUserModule {

    private List<SystemUserListBean.UserListBean> mListData;
    private boolean isEnd;
    private List<AttrSelectBean> mRoleList;
    private List<AttrSelectBean> mAreaList;

    /**
     * 获取系统管理员的列表
     *
     * @param context
     * @param searchKey
     * @param pageNum
     * @param callback
     */
    public void requestSystemUserList(Context context, String searchKey, int pageNum, final OnDataGetCallback<List<SystemUserListBean.UserListBean>> callback) {

        RetrofitManager.toSubscribe(
                ApiClient.getApiService().getSystemUserList(pageNum, searchKey)
                , new ProgressSubscriber<HttpResult<SystemUserListBean>>(context, new RequestImpl<HttpResult<SystemUserListBean>>() {
                    @Override
                    public void onNext(HttpResult<SystemUserListBean> result) {

                        SystemUserListBean data = result.getData();
                        if (data != null) {
                            isEnd = !data.isIsNext();
                            if (mListData == null) {
                                mListData = data.getUserList();
                            } else {
                                mListData.addAll(data.getUserList());
                            }
                        }
                        callback.onSuccessResult(mListData);
                    }
                })
        );

    }

    /**
     * 获取账户类型和所属单位的筛选列表
     *
     * @param context
     * @param callback
     */
    public void requestGetSystemUserFilterList(Context context, final OnDataGetCallback<Void> callback) {
        RetrofitManager.toSubscribe(
                ApiClient.getApiService().getSystemUserFilterList(),
                new ProgressSubscriber<HttpResult<SystemUserFilterBean>>(context, new RequestImpl<HttpResult<SystemUserFilterBean>>() {
                    @Override
                    public void onNext(HttpResult<SystemUserFilterBean> result) {
                        buildList(result);
                        callback.onSuccessResult(null);
                    }
                })
        );
    }

    /**
     * 创建新的系统管理员提交
     *
     * @param context
     * @param params
     * @param callback
     */
    public void saveSystemUser(Context context, Map<String, String> params, final OnDataGetCallback<String> callback) {
        RetrofitManager.toSubscribe(
                ApiClient.getApiService().saveSystemUser(params)
                , new ProgressSubscriber<HttpResult<String>>(context, new RequestImpl<HttpResult<String>>() {
                    @Override
                    public void onNext(HttpResult<String> result) {
                        callback.onSuccessResult(result.getMsg());
                    }
                })
        );
    }

    /**
     * 修改系统管理员提交
     *
     * @param context
     * @param params
     * @param callback
     */
    public void updateSystemUser(Context context, Map<String, String> params, final OnDataGetCallback<String> callback) {
        RetrofitManager.toSubscribe(
                ApiClient.getApiService().updateSystemUser(params)
                , new ProgressSubscriber<HttpResult<String>>(context, new RequestImpl<HttpResult<String>>() {
                    @Override
                    public void onNext(HttpResult<String> result) {
                        callback.onSuccessResult(result.getMsg());
                    }
                })
        );
    }

    /**
     * 删除系统用户
     *
     * @param context
     * @param userId
     * @param callback
     */
    public void delSystemUser(Context context, String userId, final OnDataGetCallback<String> callback) {
        RetrofitManager.toSubscribe(
                ApiClient.getApiService().delSystemUser(userId)
                , new ProgressSubscriber<HttpResult<String>>(context, new RequestImpl<HttpResult<String>>() {
                    @Override
                    public void onNext(HttpResult<String> result) {
                        callback.onSuccessResult(result.getMsg());
                    }
                })
        );
    }

    /**
     * 修改系统用户密码
     *
     * @param context
     * @param userId
     * @param password
     * @param callback
     */
    public void resetSystemUserPassword(Context context, String userId,String userName, String password, final OnDataGetCallback<String> callback) {
        RetrofitManager.toSubscribe(
                ApiClient.getApiService().resetSystemUserPasswrod(userId,userName, password)
                , new ProgressSubscriber<HttpResult<String>>(context, new RequestImpl<HttpResult<String>>() {
                    @Override
                    public void onNext(HttpResult<String> result) {
                        callback.onSuccessResult(result.getMsg());
                    }
                })
        );
    }

    private void buildList(HttpResult<SystemUserFilterBean> result) {
        mAreaList = new ArrayList<>();
        mRoleList = new ArrayList<>();
        SystemUserFilterBean data = result.getData();
        if (data == null) return;
        List<SystemUserFilterBean.AreaListBean> areaList = data.getAreaList();
        List<SystemUserFilterBean.RoleListBean> roleList = data.getRoleList();
        for (SystemUserFilterBean.AreaListBean areaListBean : areaList) {
            mAreaList.add(new AttrSelectBean(areaListBean.getRegion_id(), areaListBean.getRegion_name()));
        }
        for (SystemUserFilterBean.RoleListBean roleListBean : roleList) {
            mRoleList.add(new AttrSelectBean(roleListBean.getRole_ID(), roleListBean.getRole_NAME()));
        }
    }

    public List<AttrSelectBean> getRoleList() {
        return mRoleList;
    }

    public List<AttrSelectBean> getAreaList() {
        return mAreaList;
    }

    public List<SystemUserListBean.UserListBean> getListData() {
        return mListData;
    }

    public boolean isEnd() {
        return isEnd;
    }
}
