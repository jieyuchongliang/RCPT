package com.rcpt.mvp.module;

import android.content.Context;

import com.rcpt.base.HttpResult;
import com.rcpt.base.mvp.OnDataGetCallback;
import com.rcpt.bean.InterviewMessageListBean;
import com.rcpt.http.ProgressSubscriber;
import com.rcpt.http.RequestImpl;
import com.rcpt.http.RetrofitManager;
import com.rcpt.http.api.ApiClient;

import java.util.List;

/**
 * Created by lvzp on 2017/3/6.
 * 类描述：
 */

public class InterviewMessageModule {

    private List<InterviewMessageListBean.GetInterviewBean> mListData;
    private boolean isEnd;

    public void updateInterviewStatus(Context context, String id, String status, String reason, final OnDataGetCallback<String> callback) {
        RetrofitManager.toSubscribe(
                ApiClient.getApiService().updateInterviewStatus(id, status, reason)
                , new ProgressSubscriber<HttpResult<String>>(context, new RequestImpl<HttpResult<String>>() {
                    @Override
                    public void onNext(HttpResult<String> result) {
                        callback.onSuccessResult(result.getMsg());
                    }
                })
        );
    }

    public void requestInterviewMessageList(Context context, String userId, int pageNum, final OnDataGetCallback<List<InterviewMessageListBean.GetInterviewBean>> callback) {
        RetrofitManager.toSubscribe(
                ApiClient.getApiService().getInterviewMessageList(userId, pageNum)
                , new ProgressSubscriber<HttpResult<InterviewMessageListBean>>(context, new RequestImpl<HttpResult<InterviewMessageListBean>>() {
                    @Override
                    public void onNext(HttpResult<InterviewMessageListBean> result) {

                        InterviewMessageListBean data = result.getData();
                        isEnd = !data.isIsNext();
                        if (mListData == null) {
                            mListData = data.getGetInterview();
                        } else {
                            mListData.addAll(data.getGetInterview());
                        }
                        callback.onSuccessResult(mListData);
                    }
                })
        );
    }

    public boolean isEnd() {
        return isEnd;
    }

    public List<InterviewMessageListBean.GetInterviewBean> getListData() {
        return mListData;
    }
}
