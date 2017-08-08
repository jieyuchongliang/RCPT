package com.rcpt.mvp.module;

import android.content.Context;

import com.rcpt.base.HttpResult;
import com.rcpt.base.mvp.OnDataGetCallback;
import com.rcpt.http.ProgressSubscriber;
import com.rcpt.http.RequestImpl;
import com.rcpt.http.RetrofitManager;
import com.rcpt.http.api.ApiClient;

/**
 * Created by lvzp on 2017/4/18.
 * 类描述：
 */

public class CompanyChangePasswordModule {

    public void changePassworkd(Context context, String userId, String oldPassword, String newPassword, final OnDataGetCallback<String> callback){
        RetrofitManager.toSubscribe(
                ApiClient.getApiService().updatePassword(userId,oldPassword,newPassword)
                ,new ProgressSubscriber<HttpResult<String>>(context, new RequestImpl<HttpResult<String>>() {
                    @Override
                    public void onNext(HttpResult<String> result) {
                        callback.onSuccessResult(result.getMsg());
                    }
                })
        );
    }

}
