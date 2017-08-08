package com.rcpt.mvp.module;

import android.content.Context;

import com.rcpt.base.HttpResult;
import com.rcpt.base.mvp.OnDataGetCallback;
import com.rcpt.bean.HomeInfoBean;
import com.rcpt.http.ProgressSubscriber;
import com.rcpt.http.RequestImpl;
import com.rcpt.http.RetrofitManager;
import com.rcpt.http.api.ApiClient;
import com.rcpt.mvp.contract.HomeInfoContract;

/**
 * Created by lvzp on 2017/3/15.
 * 类描述：
 */

public class HomeInfoModule {

    public void requestHomeInfo(Context context, String id, String type, final OnDataGetCallback<HomeInfoBean> callback) {
        switch (type) {
            case HomeInfoContract.INFO_TYPE_COMPANY_NEWS:
                RetrofitManager.toSubscribe(
                        ApiClient.getApiService().companyNewsDetail(id)
                        , new ProgressSubscriber<HttpResult<HomeInfoBean>>(context, new RequestImpl<HttpResult<HomeInfoBean>>() {
                            @Override
                            public void onNext(HttpResult<HomeInfoBean> result) {
                                callback.onSuccessResult(result.getData());
                            }
                        })
                );
                break;
            case HomeInfoContract.INFO_TYPE_NEWS:
                RetrofitManager.toSubscribe(
                        ApiClient.getApiService().newsDetail(id)
                        , new ProgressSubscriber<HttpResult<HomeInfoBean>>(context, new RequestImpl<HttpResult<HomeInfoBean>>() {
                            @Override
                            public void onNext(HttpResult<HomeInfoBean> result) {
                                callback.onSuccessResult(result.getData());
                            }
                        })
                );
                break;
            case HomeInfoContract.INFO_TYPE_CONSULT_SERVICE:
                RetrofitManager.toSubscribe(
                        ApiClient.getApiService().consultancyDetail(id)
                        , new ProgressSubscriber<HttpResult<HomeInfoBean>>(context, new RequestImpl<HttpResult<HomeInfoBean>>() {
                            @Override
                            public void onNext(HttpResult<HomeInfoBean> result) {
                                callback.onSuccessResult(result.getData());
                            }
                        })
                );
                break;
            case HomeInfoContract.INFO_TYPE_POLICY:
                RetrofitManager.toSubscribe(
                        ApiClient.getApiService().policyDetail(id)
                        , new ProgressSubscriber<HttpResult<HomeInfoBean>>(context, new RequestImpl<HttpResult<HomeInfoBean>>() {
                            @Override
                            public void onNext(HttpResult<HomeInfoBean> result) {
                                callback.onSuccessResult(result.getData());
                            }
                        })
                );
                break;
            case HomeInfoContract.INFO_TYPE_EMPLOYMENT:
                RetrofitManager.toSubscribe(
                        ApiClient.getApiService().getEmploymentGuide(id)
                        , new ProgressSubscriber<HttpResult<HomeInfoBean>>(context, new RequestImpl<HttpResult<HomeInfoBean>>() {
                            @Override
                            public void onNext(HttpResult<HomeInfoBean> result) {
                                callback.onSuccessResult(result.getData());
                            }
                        })
                );
                break;
        }

    }

}
