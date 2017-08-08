package com.rcpt.mvp.module;

import android.content.Context;

import com.rcpt.base.HttpResult;
import com.rcpt.base.mvp.OnDataGetCallback;
import com.rcpt.bean.BidPersonInfoBean;
import com.rcpt.http.ProgressSubscriber;
import com.rcpt.http.RequestImpl;
import com.rcpt.http.RetrofitManager;
import com.rcpt.http.api.ApiClient;

/**
 * Created by 860116021 on 2017/4/19.
 */

public class BidPersonInfoModule {

    private BidPersonInfoBean bean;

    public void getBidPersonInfo(final Context context, String bidInfoId, String userId, final OnDataGetCallback<BidPersonInfoBean> callback) {
        RetrofitManager.toSubscribe(ApiClient.getApiService().getBidPersonInfo(bidInfoId, userId), new ProgressSubscriber<>(context, new RequestImpl<HttpResult<BidPersonInfoBean>>() {
            @Override
            public void onNext(HttpResult<BidPersonInfoBean> result) {
                bean = result.getData();
                callback.onSuccessResult(result.getData());
            }
        }));
    }

    public BidPersonInfoBean getBidInfoBean() {
        return bean;
    }
}
