package com.rcpt.mvp.module;

import android.content.Context;

import com.rcpt.base.HttpResult;
import com.rcpt.base.mvp.OnDataGetCallback;
import com.rcpt.bean.AcademyInfoBean;
import com.rcpt.http.ProgressSubscriber;
import com.rcpt.http.RequestImpl;
import com.rcpt.http.RetrofitManager;
import com.rcpt.http.api.ApiClient;

/**
 * Created by lvzp on 2017/3/29.
 * 类描述：
 */

public class SchoolInfoModule {

    public void requestSchoolInfo(Context context, String id, final OnDataGetCallback<AcademyInfoBean> callback) {
        RetrofitManager.toSubscribe(
                ApiClient.getApiService().getSchoolInfo(id)
                , new ProgressSubscriber<HttpResult<AcademyInfoBean>>(context, new RequestImpl<HttpResult<AcademyInfoBean>>() {
                    @Override
                    public void onNext(HttpResult<AcademyInfoBean> result) {
                        callback.onSuccessResult(result.getData());
                    }
                })
        );
    }

}
