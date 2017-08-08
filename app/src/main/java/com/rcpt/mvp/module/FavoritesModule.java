package com.rcpt.mvp.module;

import android.content.Context;

import com.rcpt.base.HttpResult;
import com.rcpt.base.mvp.OnDataGetCallback;
import com.rcpt.bean.FavoritesBean;
import com.rcpt.http.ProgressSubscriber;
import com.rcpt.http.RequestImpl;
import com.rcpt.http.RetrofitManager;
import com.rcpt.http.api.ApiClient;

import java.util.List;

/**
 * Created by lvzp on 2017/3/7.
 * 类描述：
 */

public class FavoritesModule {

    private List<FavoritesBean.FavoritesListBean> mListData;
    private boolean isEnd;

    public void deleteFavorite(Context context, String userId, String id, final OnDataGetCallback<String> callback) {
        RetrofitManager.toSubscribe(
                ApiClient.getApiService().deleteFavorite(userId, id)
                , new ProgressSubscriber<HttpResult<String>>(context, new RequestImpl<HttpResult<String>>() {
                    @Override
                    public void onNext(HttpResult<String> result) {
                        callback.onSuccessResult(result.getMsg());
                    }
                })
        );
    }

    public void getList(final Context context, String userId, int page, final OnDataGetCallback<List<FavoritesBean.FavoritesListBean>> callback) {
        RetrofitManager.toSubscribe(ApiClient.getApiService().getFavoriteslist(userId, page), new ProgressSubscriber<>(context, new RequestImpl<HttpResult<FavoritesBean>>() {
            @Override
            public void onNext(HttpResult<FavoritesBean> result) {
                FavoritesBean bean = result.getData();
                if (bean != null) {
                    if (mListData == null)
                        mListData = bean.getFavoriteslist();
                    else
                        mListData.addAll(bean.getFavoriteslist());
                    isEnd = !bean.isNext();
                }
                callback.onSuccessResult(mListData);
            }
        }));
    }

    public List<FavoritesBean.FavoritesListBean> getListData() {
        return mListData;
    }

    public boolean isEnd() {
        return isEnd;
    }


}
