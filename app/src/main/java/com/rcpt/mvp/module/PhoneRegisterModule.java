package com.rcpt.mvp.module;

import android.content.Context;

import com.rcpt.base.HttpResult;
import com.rcpt.base.bean.CodeBean;
import com.rcpt.base.mvp.OnDataGetCallback;
import com.rcpt.http.ProgressSubscriber;
import com.rcpt.http.RequestImpl;
import com.rcpt.http.RetrofitManager;
import com.rcpt.http.api.ApiClient;

/**
 * Created by 860116021 on 2017/3/23.
 */

public class PhoneRegisterModule {

    public void getVerify(final Context context, String phoneNum, final OnDataGetCallback<String> callback){
        RetrofitManager.toSubscribe(ApiClient.getApiService().personSendPhoneCode(phoneNum),
                new ProgressSubscriber<>(context, new RequestImpl<HttpResult<CodeBean>>() {
                    @Override
                    public void onNext(HttpResult<CodeBean> codeBeanHttpResult) {
                        callback.onSuccessResult(codeBeanHttpResult.getMsg());
                    }
                }));
    }

    public void personReg(final Context context, String regType, String username, String password, String phoneNum,
                          String verify, final OnDataGetCallback<String> callback){
        RetrofitManager.toSubscribe(ApiClient.getApiService().personReg(regType,username,password,null,phoneNum,verify),
                new ProgressSubscriber<>(context, new RequestImpl<HttpResult<String>>() {
                    @Override
                    public void onNext(HttpResult<String> stringHttpResult) {
                        callback.onSuccessResult(stringHttpResult.getMsg());
                    }
                }));
    }
}
