package com.rcpt.mvp.module;

import android.content.Context;

import com.rcpt.base.HttpResult;
import com.rcpt.base.mvp.OnDataGetCallback;
import com.rcpt.bean.VideoInfoBaseBean;
import com.rcpt.bean.VideoInfoCatalogBean;
import com.rcpt.http.ProgressSubscriber;
import com.rcpt.http.RequestImpl;
import com.rcpt.http.RetrofitManager;
import com.rcpt.http.api.ApiClient;

/**
 * Created by 860617003 on 2017/5/15.
 */

public class VideoInfoBaseModule {


    public void requestVideoInfo(Context context, String userId, String calssTypeId, final OnDataGetCallback<VideoInfoBaseBean> callback) {
        RetrofitManager.toSubscribe(
                ApiClient.getApiService().getVideoInfoBase(userId, calssTypeId)
                , new ProgressSubscriber<HttpResult<VideoInfoBaseBean>>(context, new RequestImpl<HttpResult<VideoInfoBaseBean>>() {
                    @Override
                    public void onNext(HttpResult<VideoInfoBaseBean> result) {
                        callback.onSuccessResult(result.getData());
                    }
                })
        );
    }

}
