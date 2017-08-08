package com.rcpt.mvp.module;

import android.content.Context;

import com.rcpt.base.HttpResult;
import com.rcpt.base.mvp.OnDataGetCallback;
import com.rcpt.bean.CVPreviewInfoBean;
import com.rcpt.http.ProgressSubscriber;
import com.rcpt.http.RequestImpl;
import com.rcpt.http.RetrofitManager;
import com.rcpt.http.api.ApiClient;

/**
 * Created by lvzp on 2017/3/31.
 * 类描述：
 */

public class RecruitCVPreviewInfoModule {
    /**
     * 企业用户查看----请求获取简历预览的详情
     *
     * @param context
     * @param cvId
     * @param cvUserId
     * @param userId
     * @param callback
     */
    public void requestCVPreviewInfo(Context context, String cvId, String cvUserId, String userId, final OnDataGetCallback<CVPreviewInfoBean> callback) {
        RetrofitManager.toSubscribe(
                ApiClient.getApiService().getCVPreviewInfo(cvId, cvUserId, userId)
                , new ProgressSubscriber<HttpResult<CVPreviewInfoBean>>(context, new RequestImpl<HttpResult<CVPreviewInfoBean>>() {
                    @Override
                    public void onNext(HttpResult<CVPreviewInfoBean> result) {
                        callback.onSuccessResult(result.getData());
                    }
                })
        );
    }

    /**
     * 个人用户查看-----请求获取简历预览的详情
     *
     * @param context
     * @param cvId
     * @param userId
     * @param callback
     */
    public void requestCVPreviewInfo(Context context, String cvId, String userId, final OnDataGetCallback<CVPreviewInfoBean> callback) {
        RetrofitManager.toSubscribe(
                ApiClient.getApiService().getCVPreviewResume(cvId, userId)
                , new ProgressSubscriber<HttpResult<CVPreviewInfoBean>>(context, new RequestImpl<HttpResult<CVPreviewInfoBean>>() {
                    @Override
                    public void onNext(HttpResult<CVPreviewInfoBean> result) {
                        callback.onSuccessResult(result.getData());
                    }
                })
        );
    }

    /**
     * 收藏简历
     *
     * @param context
     * @param cvId     简历id
     * @param cvUserId 简历用户id
     * @param userId   当前用户id
     * @param callback
     */
    public void collectResume(Context context, String cvId, String cvUserId, String userId, final OnDataGetCallback<String> callback) {
        RetrofitManager.toSubscribe(
                ApiClient.getApiService().collectResume(cvId, cvUserId, userId)
                , new ProgressSubscriber<HttpResult<String>>(context, new RequestImpl<HttpResult<String>>() {
                    @Override
                    public void onNext(HttpResult<String> result) {
                        callback.onSuccessResult(result.getMsg());
                    }
                })
        );
    }

}
