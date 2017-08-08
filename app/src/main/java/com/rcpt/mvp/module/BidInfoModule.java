package com.rcpt.mvp.module;

import android.content.Context;

import com.rcpt.base.HttpResult;
import com.rcpt.base.mvp.OnDataGetCallback;
import com.rcpt.bean.BidInfoBean;
import com.rcpt.http.ProgressSubscriber;
import com.rcpt.http.RequestImpl;
import com.rcpt.http.RetrofitManager;
import com.rcpt.http.api.ApiClient;

/**
 * Created by lvzp on 2017/2/24.
 * 类描述：
 */

public class BidInfoModule {

    private BidInfoBean bean;

    public void getBidInfo(final Context context, String bidId,String userId, final OnDataGetCallback<BidInfoBean> callback){
        RetrofitManager.toSubscribe(ApiClient.getApiService().getBidInfo(bidId,userId),new ProgressSubscriber<>(context, new RequestImpl<HttpResult<BidInfoBean>>() {
            @Override
            public void onNext(HttpResult<BidInfoBean> result) {
                bean = result.getData();
                callback.onSuccessResult(result.getData());
            }
        }));
    }

    public BidInfoBean getBidBean(){
        return bean;
    }
}
