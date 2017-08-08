package com.rcpt.mvp.module;

import android.content.Context;

import com.rcpt.base.HttpResult;
import com.rcpt.base.mvp.OnDataGetCallback;
import com.rcpt.bean.AlipayBean;
import com.rcpt.http.ProgressSubscriber;
import com.rcpt.http.RequestImpl;
import com.rcpt.http.RetrofitManager;
import com.rcpt.http.api.ApiClient;

/**
 * Created by 860617003 on 2017/5/23.
 */

public class VideoPaymentModule {

    public void requestVideoFreePay(Context context, String user_id, String course_name, String commodity_id, String body, String course_id, String subject_id, String teaching_style, String subject, String total_amount, final OnDataGetCallback<String> callback) {
        RetrofitManager.toSubscribe(
                ApiClient.getApiService().videoFreePay(user_id, course_name, commodity_id, body, course_id, subject_id, teaching_style, subject, total_amount)
                , new ProgressSubscriber<HttpResult<String>>(context, new RequestImpl<HttpResult<String>>() {
                    @Override
                    public void onNext(HttpResult<String> result) {
                        callback.onSuccessResult(result.getMsg());
                    }
                })
        );
    }

}
