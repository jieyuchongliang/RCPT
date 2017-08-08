package com.rcpt.mvp.module;

import android.content.Context;

import com.rcpt.base.HttpResult;
import com.rcpt.base.mvp.OnDataCallback;
import com.rcpt.bean.RegisterClauseBean;
import com.rcpt.http.RetrofitManager;
import com.rcpt.http.api.ApiClient;
import com.rcpt.http.utils.NetworkUtils;

import rx.Subscriber;

/**
 * Created by lvzp on 2017/4/25.
 * 类描述：
 */

public class RegisterClauseModule {


    public void requestRegisterClause(Context context, final OnDataCallback<RegisterClauseBean> callback) {
        RetrofitManager.toSubscribe(
                ApiClient.getApiService().registerClause()
                , new Subscriber<HttpResult<RegisterClauseBean>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.onError(NetworkUtils.getErrorMsg(e));
                    }

                    @Override
                    public void onNext(HttpResult<RegisterClauseBean> result) {
                        if (result.isResult()) {
                            callback.onSuccessResult(result.getData());
                        } else {
                            callback.onError(result.getMsg());
                        }
                    }
                }
        );
    }

}
