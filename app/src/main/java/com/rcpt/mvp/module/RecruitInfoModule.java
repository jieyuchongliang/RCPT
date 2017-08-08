package com.rcpt.mvp.module;

import android.content.Context;

import com.rcpt.base.HttpResult;
import com.rcpt.base.mvp.OnDataGetCallback;
import com.rcpt.bean.CVSelectBean;
import com.rcpt.bean.RecruitJobInfoBean;
import com.rcpt.http.ProgressSubscriber;
import com.rcpt.http.RequestImpl;
import com.rcpt.http.RetrofitManager;
import com.rcpt.http.api.ApiClient;

import java.util.List;

/**
 * Created by lvzp on 2017/3/27.
 * 类描述：
 */

public class RecruitInfoModule {
    /**
     * 请求获取职位详情
     *
     * @param context
     * @param companyId
     * @param recruitmentInfoId
     * @param callback
     */
    public void requestJobInfo(Context context, String companyId, String userId, String recruitmentInfoId, final OnDataGetCallback<RecruitJobInfoBean> callback) {
        RetrofitManager.toSubscribe(
                ApiClient.getApiService().getRecruitJobInfo(companyId, userId, recruitmentInfoId)
                , new ProgressSubscriber<HttpResult<RecruitJobInfoBean>>(context, new RequestImpl<HttpResult<RecruitJobInfoBean>>() {
                    @Override
                    public void onNext(HttpResult<RecruitJobInfoBean> result) {
                        callback.onSuccessResult(result.getData());
                    }
                })
        );
    }

    /**
     * 请求获取简历列表
     *
     * @param context
     * @param userId
     * @param callback
     */
    public void requestGetCVList(Context context, String userId, final OnDataGetCallback<List<CVSelectBean>> callback) {
        RetrofitManager.toSubscribe(
                ApiClient.getApiService().getCVSelectList(userId)
                , new ProgressSubscriber<HttpResult<List<CVSelectBean>>>(context, new RequestImpl<HttpResult<List<CVSelectBean>>>() {
                    @Override
                    public void onNext(HttpResult<List<CVSelectBean>> result) {
                        callback.onSuccessResult(result.getData());
                    }
                })
        );
    }

    /**
     * 请求开始投递简历
     */
    public void requestSendResume(Context context, String cvId, String userId, String recruitmentId, String companyId, final OnDataGetCallback<String> callback) {
        RetrofitManager.toSubscribe(
                ApiClient.getApiService().startSendResume(cvId, userId, recruitmentId, companyId)
                , new ProgressSubscriber<HttpResult<String>>(context, new RequestImpl<HttpResult<String>>() {
                    @Override
                    public void onNext(HttpResult<String> result) {
                        callback.onSuccessResult(result.getMsg());
                    }
                })
        );
    }

    /**
     * 收藏职位
     *
     * @param context
     * @param userId
     * @param recruitmentId
     * @param companyId
     * @param callback
     */
    public void requestFollowJob(Context context, String userId, String recruitmentId, String companyId, final OnDataGetCallback<String> callback) {
        RetrofitManager.toSubscribe(
                ApiClient.getApiService().collectJob(userId, recruitmentId, companyId)
                , new ProgressSubscriber<HttpResult<String>>(context, new RequestImpl<HttpResult<String>>() {
                    @Override
                    public void onNext(HttpResult<String> result) {
                        callback.onSuccessResult(result.getMsg());
                    }
                })
        );
    }

}
