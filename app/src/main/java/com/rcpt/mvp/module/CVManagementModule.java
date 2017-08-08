package com.rcpt.mvp.module;

import android.content.Context;

import com.rcpt.base.HttpResult;
import com.rcpt.base.mvp.OnDataGetCallback;
import com.rcpt.bean.CVInfoBean;
import com.rcpt.bean.CVSelfAppraisalInfoBean;
import com.rcpt.http.ProgressSubscriber;
import com.rcpt.http.RequestImpl;
import com.rcpt.http.RetrofitManager;
import com.rcpt.http.api.ApiClient;

import java.util.List;

/**
 * Created by lvzp on 2017/3/20.
 * 类描述：
 */

public class CVManagementModule {

    private CVInfoBean mInfoBean;
    private String mCVId;

    /**
     * 更新简历的公开和隐藏状态
     *
     * @param context
     * @param userId
     * @param cvId
     * @param publicSetStatus
     * @param callback
     */
    public void updateCVPublicSet(Context context, String userId, String cvId, String publicSetStatus, final OnDataGetCallback<String> callback) {
        RetrofitManager.toSubscribe(
                ApiClient.getApiService().updateCVPublisSet(userId, cvId, publicSetStatus)
                , new ProgressSubscriber<HttpResult<String>>(context, new RequestImpl<HttpResult<String>>() {
                    @Override
                    public void onNext(HttpResult<String> result) {
                        callback.onSuccessResult(result.getMsg());
                    }
                })
        );
    }

    /**
     * 获取简历信息
     *
     * @param context
     * @param id
     * @param callback
     */
    public void requestCVInfo(Context context, String id, final OnDataGetCallback<CVInfoBean> callback) {
        RetrofitManager.toSubscribe(
                ApiClient.getApiService().getCvInfo(id)
                , new ProgressSubscriber<HttpResult<List<CVInfoBean>>>(context, new RequestImpl<HttpResult<List<CVInfoBean>>>() {
                    @Override
                    public void onNext(HttpResult<List<CVInfoBean>> cvInfoBeanHttpResult) {
                        List<CVInfoBean> data = cvInfoBeanHttpResult.getData();
                        CVInfoBean bean = null;
                        if (!data.isEmpty()) {
                            bean = data.get(0);
                        }
                        if (bean != null) {
                            mCVId = bean.getCvId();
                            mInfoBean = bean;
                        }
                        callback.onSuccessResult(bean);
                    }
                })
        );
    }

    /**
     * 获取自我评价的信息
     *
     * @param context
     * @param userId
     * @param cvId
     * @param callback
     */
    public void requestSelfAppraisalInfo(Context context, String userId, String cvId, final OnDataGetCallback<String> callback) {
        RetrofitManager.toSubscribe(
                ApiClient.getApiService().getCVSelfAppraisalInfo(userId, cvId)
                , new ProgressSubscriber<>(context, new RequestImpl<HttpResult<CVSelfAppraisalInfoBean>>() {
                    @Override
                    public void onNext(HttpResult<CVSelfAppraisalInfoBean> stringHttpResult) {
                        CVSelfAppraisalInfoBean data = stringHttpResult.getData();
                        String selfAppraisal = "";
                        if (data != null)
                            selfAppraisal = data.getSelfAppraisal();
                        callback.onSuccessResult(selfAppraisal);
                    }
                })
        );
    }

    /**
     * 更新简历名称
     *
     * @param context
     * @param userId
     * @param cvId
     * @param cvName
     * @param callback
     */
    public void updateCVName(Context context, String userId, String cvId, String cvName, final OnDataGetCallback<String> callback) {
        RetrofitManager.toSubscribe(
                ApiClient.getApiService().updateCVName(userId, cvId, cvName)
                , new ProgressSubscriber<HttpResult<String>>(context, new RequestImpl<HttpResult<String>>() {
                    @Override
                    public void onNext(HttpResult<String> stringHttpResult) {
                        callback.onSuccessResult(stringHttpResult.getMsg());
                    }
                })
        );
    }

    /**
     * 更新自我评价的接口
     *
     * @param context
     * @param userId
     * @param cvId
     * @param selfAppr
     * @param callback
     */
    public void updateSelfAppraisalInfo(Context context, String userId, String cvId, String selfAppr, final OnDataGetCallback<String> callback) {
        RetrofitManager.toSubscribe(
                ApiClient.getApiService().updateCVSelfAppraisalInfo(userId, cvId, selfAppr)
                , new ProgressSubscriber<HttpResult<String>>(context, new RequestImpl<HttpResult<String>>() {
                    @Override
                    public void onNext(HttpResult<String> stringHttpResult) {
                        callback.onSuccessResult(stringHttpResult.getMsg());
                    }
                })
        );
    }

    public void checkPersonInfo(Context context, String userId, final OnDataGetCallback<String> callback){
        RetrofitManager.toSubscribe(
                ApiClient.getApiService().checkPersonInfo(userId),
                new ProgressSubscriber<HttpResult<String>>(context, new RequestImpl<HttpResult<String>>() {
                    @Override
                    public void onNext(HttpResult<String> result) {
                        callback.onSuccessResult(null);
                    }
                })
        );
    }

    public String getCVId() {
        return mCVId;
    }

    public CVInfoBean getInfoBean() {
        return mInfoBean;
    }
}
