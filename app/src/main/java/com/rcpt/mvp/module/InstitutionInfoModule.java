package com.rcpt.mvp.module;

import android.content.Context;

import com.rcpt.base.HttpResult;
import com.rcpt.base.mvp.OnDataGetCallback;
import com.rcpt.bean.InstitutionDetailBean;
import com.rcpt.http.ProgressSubscriber;
import com.rcpt.http.RequestImpl;
import com.rcpt.http.RetrofitManager;
import com.rcpt.http.api.ApiClient;

/**
 * Created by 860116021 on 2017/4/14.
 */

public class InstitutionInfoModule {

    public void getInstitutionInfo(final Context context, String institutionsId, final OnDataGetCallback<InstitutionDetailBean> callback){
        RetrofitManager.toSubscribe(ApiClient.getApiService().getInstitutionInfo(institutionsId),new ProgressSubscriber<>(context, new RequestImpl<HttpResult<InstitutionDetailBean>>() {
            @Override
            public void onNext(HttpResult<InstitutionDetailBean> result) {
                callback.onSuccessResult(result.getData());
            }
        }));
    }
}
