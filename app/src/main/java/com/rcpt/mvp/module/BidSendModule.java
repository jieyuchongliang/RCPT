package com.rcpt.mvp.module;

import android.content.Context;

import com.rcpt.base.HttpResult;
import com.rcpt.base.mvp.OnDataGetCallback;
import com.rcpt.bean.BidContactsBean;
import com.rcpt.http.ProgressSubscriber;
import com.rcpt.http.RequestImpl;
import com.rcpt.http.RetrofitManager;
import com.rcpt.http.api.ApiClient;

import java.util.Map;

/**
 * Created by lvzp on 2017/4/19.
 * 类描述：
 */

public class BidSendModule {

    public void requestSendBidContacts(Context context, String userId, final OnDataGetCallback<BidContactsBean> callback) {
        RetrofitManager.toSubscribe(
                ApiClient.getApiService().getBidContract(userId)
                , new ProgressSubscriber<HttpResult<BidContactsBean>>(context, new RequestImpl<HttpResult<BidContactsBean>>() {
                    @Override
                    public void onNext(HttpResult<BidContactsBean> result) {
                        callback.onSuccessResult(result.getData());
                    }
                })
        );
    }

    public void sendPreson(Context context, Map<String, String> params, final OnDataGetCallback<String> callback) {
        RetrofitManager.toSubscribe(
                ApiClient.getApiService().companyPresenterBid(params)
                , new ProgressSubscriber<HttpResult<String>>(context, new RequestImpl<HttpResult<String>>() {
                    @Override
                    public void onNext(HttpResult<String> result) {
                        callback.onSuccessResult(result.getMsg());
                    }
                })
        );
    }

    public void sendProject(Context context, Map<String, String> params, final OnDataGetCallback<String> callback) {
        RetrofitManager.toSubscribe(
                ApiClient.getApiService().companyProjectBid(params)
                , new ProgressSubscriber<HttpResult<String>>(context, new RequestImpl<HttpResult<String>>() {
                    @Override
                    public void onNext(HttpResult<String> result) {
                        callback.onSuccessResult(result.getMsg());
                    }
                })
        );
    }

}
