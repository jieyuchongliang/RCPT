package com.rcpt.mvp.module;

import android.content.Context;

import com.rcpt.base.HttpResult;
import com.rcpt.base.mvp.OnDataGetCallback;
import com.rcpt.bean.UserManageListBean;
import com.rcpt.http.ProgressSubscriber;
import com.rcpt.http.RequestImpl;
import com.rcpt.http.RetrofitManager;
import com.rcpt.http.api.ApiClient;
import com.rcpt.ui.system.fragment.UserManageFragment;

import java.util.List;

/**
 * Created by 860617003 on 2017/6/2.
 */

public class UserManageFragmentModule {

    private List<UserManageListBean.UserInfoBean> mListData;
    private boolean isEnd;

    public void requestUserList(Context context, String userTypeCode, String userStatus, String username, String compantyName, String provinceId, String cityId, String userType, int pageNum, final OnDataGetCallback<List<UserManageListBean.UserInfoBean>> callback) {

        if (UserManageFragment.PERSON_USER.equals(userType)) {
            RetrofitManager.toSubscribe(
                    ApiClient.getApiService().getOperateUser(username, userStatus, pageNum)
                    , new ProgressSubscriber<HttpResult<UserManageListBean>>(context, new RequestImpl<HttpResult<UserManageListBean>>() {
                        @Override
                        public void onNext(HttpResult<UserManageListBean> result) {
                            setupData(result, callback);
                        }
                    }));
        } else {
            RetrofitManager.toSubscribe(
                    ApiClient.getApiService().getOperateCompany(compantyName, username, userStatus, provinceId, cityId, userTypeCode, pageNum)
                    , new ProgressSubscriber<HttpResult<UserManageListBean>>(context, new RequestImpl<HttpResult<UserManageListBean>>() {
                        @Override
                        public void onNext(HttpResult<UserManageListBean> result) {
                            setupData(result, callback);
                        }
                    }));
        }
    }

    private void setupData(HttpResult<UserManageListBean> result, OnDataGetCallback<List<UserManageListBean.UserInfoBean>> callback) {
        UserManageListBean data = result.getData();
        if (data != null) {
            isEnd = !data.isNext();
            if (mListData == null) {
                mListData = data.getVarList();
            } else {
                mListData.addAll(data.getVarList());
            }
        }
        callback.onSuccessResult(mListData);
    }

    public void requestChangePassword(Context context, String password, String userId, final OnDataGetCallback<String> callback) {
        RetrofitManager.toSubscribe(
                ApiClient.getApiService().resetPassword(userId, password)
                , new ProgressSubscriber<HttpResult<String>>(context, new RequestImpl<HttpResult<String>>() {
                    @Override
                    public void onNext(HttpResult<String> result) {
                        callback.onSuccessResult(result.getMsg());
                    }
                })
        );
    }

    /**
     * 锁定用户
     *
     * @param context
     * @param userId
     * @param reviewer
     * @param callback
     */
    public void requestLockedUser(Context context, String userId, String reviewer, final OnDataGetCallback<String> callback) {
        RetrofitManager.toSubscribe(
                ApiClient.getApiService().companyLocked(userId, reviewer)
                , new ProgressSubscriber<HttpResult<String>>(context, new RequestImpl<HttpResult<String>>() {
                    @Override
                    public void onNext(HttpResult<String> result) {
                        callback.onSuccessResult(result.getMsg());
                    }
                })
        );
    }

    /**
     * 解锁用户（审核通过用户）
     *
     * @param context
     * @param userId
     * @param reviewer
     * @param callback
     */
    public void requestReviewUnlockUser(Context context, String userId, String reviewer, String userType, final OnDataGetCallback<String> callback) {
        RetrofitManager.toSubscribe(
                ApiClient.getApiService().companyReviewUnlock(userId, reviewer, userType)
                , new ProgressSubscriber<HttpResult<String>>(context, new RequestImpl<HttpResult<String>>() {
                    @Override
                    public void onNext(HttpResult<String> result) {
                        callback.onSuccessResult(result.getMsg());
                    }
                })
        );
    }

    /**
     * 审核驳回
     *
     * @param context
     * @param userId
     * @param reviewer
     * @param callback
     */
    public void requestReviewFail(Context context, String userId, String reviewer, final OnDataGetCallback<String> callback) {
        RetrofitManager.toSubscribe(
                ApiClient.getApiService().companyReviewFail(userId, reviewer)
                , new ProgressSubscriber<HttpResult<String>>(context, new RequestImpl<HttpResult<String>>() {
                    @Override
                    public void onNext(HttpResult<String> result) {
                        callback.onSuccessResult(result.getMsg());
                    }
                })
        );
    }

    /**
     * 修改个人用户的状态
     *
     * @param context
     * @param userId
     * @param status
     * @param callback
     */
    public void requestUpdateStatus(Context context, String userId, String status, final OnDataGetCallback<String> callback) {
        RetrofitManager.toSubscribe(
                ApiClient.getApiService().updatePersonStatus(userId, status)
                , new ProgressSubscriber<HttpResult<String>>(context, new RequestImpl<HttpResult<String>>() {
                    @Override
                    public void onNext(HttpResult<String> result) {
                        callback.onSuccessResult(result.getMsg());
                    }
                })
        );
    }

    public void requestDelUser(Context context, String userId, String userType, final OnDataGetCallback<String> callback) {
        RetrofitManager.toSubscribe(
                ApiClient.getApiService().companyDelUser(userId, userType)
                , new ProgressSubscriber<HttpResult<String>>(context, new RequestImpl<HttpResult<String>>() {
                    @Override
                    public void onNext(HttpResult<String> result) {
                        callback.onSuccessResult(result.getMsg());
                    }
                })
        );
    }

    public List<UserManageListBean.UserInfoBean> getListData() {
        return mListData;
    }

    public boolean isEnd() {
        return isEnd;
    }
}
