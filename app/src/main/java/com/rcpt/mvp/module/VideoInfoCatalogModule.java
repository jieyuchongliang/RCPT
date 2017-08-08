package com.rcpt.mvp.module;

import android.content.Context;

import com.rcpt.base.HttpResult;
import com.rcpt.base.mvp.OnDataGetCallback;
import com.rcpt.bean.VideoInfoCatalogBean;
import com.rcpt.bean.VideoPermissionBean;
import com.rcpt.http.ProgressSubscriber;
import com.rcpt.http.RequestImpl;
import com.rcpt.http.RetrofitManager;
import com.rcpt.http.api.ApiClient;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lvzp on 2017/5/9.
 * 类描述：
 */

public class VideoInfoCatalogModule {

    private List<VideoInfoCatalogBean> mListData;


    public void requestVideoCatalogList(Context context, String userId, String classTypeId, final OnDataGetCallback<List<VideoInfoCatalogBean>> callback) {
        RetrofitManager.toSubscribe(
                ApiClient.getApiService().getVideoCatalogList(userId, classTypeId)
                , new ProgressSubscriber<HttpResult<List<VideoInfoCatalogBean>>>(context, new RequestImpl<HttpResult<List<VideoInfoCatalogBean>>>() {
                    @Override
                    public void onNext(HttpResult<List<VideoInfoCatalogBean>> result) {
                        mListData = result.getData();
                        callback.onSuccessResult(mListData);
                    }
                })
        );
    }

    public void checkVideoPermission(Context context, String userId, String classTypeId, String moduleId, String lectureId, final OnDataGetCallback<Integer> callback) {
        RetrofitManager.toSubscribe(
                ApiClient.getApiService().getCoursePermission(userId, classTypeId, moduleId, lectureId)
                , new ProgressSubscriber<HttpResult<VideoPermissionBean>>(context, new RequestImpl<HttpResult<VideoPermissionBean>>() {
                    @Override
                    public void onNext(HttpResult<VideoPermissionBean> result) {
                        VideoPermissionBean data = result.getData();
                        if (data != null)
                            callback.onSuccessResult(data.getHasPermission());
                    }
                })
        );
    }

    public List<VideoInfoCatalogBean> getListData() {
        return mListData;
    }
}
