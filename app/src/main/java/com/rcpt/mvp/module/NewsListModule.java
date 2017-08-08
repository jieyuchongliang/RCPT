package com.rcpt.mvp.module;

import com.rcpt.base.HttpResult;
import com.rcpt.base.mvp.OnDataGetCallback;
import com.rcpt.bean.NewsListBean;
import com.rcpt.http.RetrofitManager;
import com.rcpt.http.api.ApiClient;

import java.util.List;

import rx.Subscriber;

/**
 * Created by lvzp on 2017/2/23.
 * 类描述：
 */

public class NewsListModule {

    private List<NewsListBean.GetlistNewsBean> mNewsList;

    private boolean isEnd;

    public void requestNewsList(String newsId, int page, final OnDataGetCallback<List<NewsListBean.GetlistNewsBean>> callback) {
        RetrofitManager.toSubscribe(
                ApiClient.getApiService().newsList(newsId, page)
                , new Subscriber<HttpResult<NewsListBean>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(HttpResult<NewsListBean> listHttpResult) {
                        NewsListBean bean = listHttpResult.getData();
                        isEnd = !bean.isIsNext();
                        if (mNewsList == null) {
                            mNewsList = bean.getGetlistNews();
                        } else {
                            mNewsList.addAll(bean.getGetlistNews());
                        }
                        callback.onSuccessResult(mNewsList);
                    }
                });
    }

    public List<NewsListBean.GetlistNewsBean> getNewsList() {
        return mNewsList;
    }

    public boolean isEnd() {
        return isEnd;
    }
}
