package com.rcpt.mvp.module;

import android.content.Context;

import com.rcpt.base.HttpResult;
import com.rcpt.base.mvp.OnDataGetCallback;
import com.rcpt.bean.CompanyFavoritesListBean;
import com.rcpt.http.ProgressSubscriber;
import com.rcpt.http.RequestImpl;
import com.rcpt.http.RetrofitManager;
import com.rcpt.http.api.ApiClient;

import java.util.List;

/**
 * Created by lvzp on 2017/4/14.
 * 类描述：
 */

public class CompanyFavoriteModule {

    private boolean isEnd;
    private List<CompanyFavoritesListBean.CompanyFavoritesBean> mCompanyFavoritesList;

    public void requestCompanyFavoriteList(Context context, String userId, int page, final OnDataGetCallback<List<CompanyFavoritesListBean.CompanyFavoritesBean>> callback) {
        RetrofitManager.toSubscribe(
                ApiClient.getApiService().getCompanyFavorites(userId, page)
                , new ProgressSubscriber<HttpResult<CompanyFavoritesListBean>>(context, new RequestImpl<HttpResult<CompanyFavoritesListBean>>() {
                    @Override
                    public void onNext(HttpResult<CompanyFavoritesListBean> result) {
                        CompanyFavoritesListBean data = result.getData();
                        if (data!=null){
                            isEnd = !data.isIsNext();
                            if (mCompanyFavoritesList == null) {
                                mCompanyFavoritesList = data.getCompanyFavorites();
                            } else {
                                mCompanyFavoritesList.addAll(data.getCompanyFavorites());
                            }
                        }
                        callback.onSuccessResult(mCompanyFavoritesList);
                    }
                })
        );
    }

    public void deleteCompanyFavorites(Context context, String userId, String id, final OnDataGetCallback<String> callback) {
        RetrofitManager.toSubscribe(
                ApiClient.getApiService().deleteCompanyFavorites(userId, id)
                , new ProgressSubscriber<HttpResult<String>>(context, new RequestImpl<HttpResult<String>>() {
                    @Override
                    public void onNext(HttpResult<String> result) {
                        callback.onSuccessResult(result.getMsg());
                    }
                })
        );
    }

    public boolean isEnd() {
        return isEnd;
    }

    public List<CompanyFavoritesListBean.CompanyFavoritesBean> getCompanyFavoritesList() {
        return mCompanyFavoritesList;
    }
}
