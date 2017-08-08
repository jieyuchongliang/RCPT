package com.rcpt.mvp.module;

import android.content.Context;

import com.rcpt.base.HttpResult;
import com.rcpt.base.mvp.OnDataGetCallback;
import com.rcpt.bean.TestAnswerBean;
import com.rcpt.bean.TestQuestionInfoBean;
import com.rcpt.http.ProgressSubscriber;
import com.rcpt.http.RequestImpl;
import com.rcpt.http.RetrofitManager;
import com.rcpt.http.api.ApiClient;

import java.util.List;

/**
 * Created by lvzp on 2017/4/6.
 * 类描述：
 */

public class TestQuestionInfoModule {

    private List<TestQuestionInfoBean.ContentBean> mTestQuestionList;
    private String mTestId;

    public void requestTestMajorQuestionInfo(Context context, String userId, String id, final OnDataGetCallback<TestQuestionInfoBean> callback) {
        RetrofitManager.toSubscribe(
                ApiClient.getApiService().getTestMajorInfo(id, userId)
                , new ProgressSubscriber<HttpResult<TestQuestionInfoBean>>(context, new RequestImpl<HttpResult<TestQuestionInfoBean>>() {
                    @Override
                    public void onNext(HttpResult<TestQuestionInfoBean> result) {
                        TestQuestionInfoBean data = result.getData();
                        mTestQuestionList = data.getContent();
                        mTestId = data.getId();
                        callback.onSuccessResult(data);
                    }
                })
        );
    }

    public void requestTestQuestionInfo(Context context, String userId, String fieldId, final OnDataGetCallback<TestQuestionInfoBean> callback) {
        RetrofitManager.toSubscribe(
                ApiClient.getApiService().getTestQuestionInfo(fieldId, userId)
                , new ProgressSubscriber<HttpResult<TestQuestionInfoBean>>(context, new RequestImpl<HttpResult<TestQuestionInfoBean>>() {
                    @Override
                    public void onNext(HttpResult<TestQuestionInfoBean> result) {
                        TestQuestionInfoBean data = result.getData();
                        mTestId = data.getId();
                        mTestQuestionList = data.getContent();
                        callback.onSuccessResult(data);
                    }
                })
        );
    }

    public void uploadTestMajorAnswer(Context context, String userId, String id, String as, final OnDataGetCallback<TestAnswerBean> callback) {
        RetrofitManager.toSubscribe(
                ApiClient.getApiService().uploadTestMajorSubmit(userId, id, as)
                , new ProgressSubscriber<HttpResult<TestAnswerBean>>(context, new RequestImpl<HttpResult<TestAnswerBean>>() {
                    @Override
                    public void onNext(HttpResult<TestAnswerBean> result) {
                        callback.onSuccessResult(result.getData());
                    }
                })
        );
    }

    public void uploadTestAbilityAnswer(Context context, String userId, String id, String as, final OnDataGetCallback<TestAnswerBean> callback) {
        RetrofitManager.toSubscribe(
                ApiClient.getApiService().uploadTestAbilitySubmit(userId, id, as)
                , new ProgressSubscriber<HttpResult<TestAnswerBean>>(context, new RequestImpl<HttpResult<TestAnswerBean>>() {
                    @Override
                    public void onNext(HttpResult<TestAnswerBean> result) {
                        callback.onSuccessResult(result.getData());
                    }
                })
        );
    }

    public void uploadTestOccupation(Context context, String userId, String id, String as, final OnDataGetCallback<TestAnswerBean> callback) {
        RetrofitManager.toSubscribe(
                ApiClient.getApiService().uploadTestOccupation(userId, id, as)
                , new ProgressSubscriber<HttpResult<TestAnswerBean>>(context, new RequestImpl<HttpResult<TestAnswerBean>>() {
                    @Override
                    public void onNext(HttpResult<TestAnswerBean> result) {
                        callback.onSuccessResult(result.getData());
                    }
                })
        );
    }

    public List<TestQuestionInfoBean.ContentBean> getTestQuestionList() {
        return mTestQuestionList;
    }

    public String getTestId() {
        return mTestId;
    }
}
