package com.rcpt.mvp.module;

import android.content.Context;

import com.rcpt.base.HttpResult;
import com.rcpt.base.mvp.OnDataGetCallback;
import com.rcpt.http.ProgressSubscriber;
import com.rcpt.http.RequestImpl;
import com.rcpt.http.RetrofitManager;
import com.rcpt.http.api.ApiClient;

import java.io.File;
import java.util.Map;

import top.zibin.luban.OnCompressListener;

/**
 * Created by 860116021 on 2017/3/15.
 */

public class RegisterGroupModule {

    public void registerStart(Context context, Map<String, String> params, final OnDataGetCallback<String> callback,
                              File license) {
        RetrofitManager.toSubscribe(
                ApiClient.getApiService().enterpriseRegister(ApiClient.getFileBody("license", license), params)
                , new ProgressSubscriber<>(context, new RequestImpl<HttpResult<String>>() {
                    @Override
                    public void onNext(HttpResult<String> stringHttpResult) {
                        callback.onSuccessResult(stringHttpResult.getMsg());
                    }
                })
        );
    }
}
