package com.rcpt.mvp.module;

import android.content.Context;

import com.rcpt.base.HttpResult;
import com.rcpt.base.mvp.OnDataGetCallback;
import com.rcpt.bean.CompanyNewsListBean;
import com.rcpt.http.ProgressSubscriber;
import com.rcpt.http.RequestImpl;
import com.rcpt.http.RetrofitManager;
import com.rcpt.http.api.ApiClient;

import java.util.List;

/**
 * Created by 860116021 on 2017/4/7.
 */

public class CompanyNewsListModule {

    private List<CompanyNewsListBean.CompanyNewsBean> mListData;
    private boolean isEnd;

    public void getList(final Context context, String userId, int page, final OnDataGetCallback<List<CompanyNewsListBean.CompanyNewsBean>> callback){
        RetrofitManager.toSubscribe(ApiClient.getApiService().getCompanyNewsList(userId, page), new ProgressSubscriber<>(context, new RequestImpl<HttpResult<CompanyNewsListBean>>() {


            @Override
            public void onNext(HttpResult<CompanyNewsListBean> result) {
                CompanyNewsListBean bean = result.getData();
                if(mListData == null){
                    mListData = bean.getCompanyNews();
                }else {
                    mListData.addAll(bean.getCompanyNews());
                }
                isEnd = !bean.isIsNext();
                callback.onSuccessResult(mListData);
            }
        }));
    }

    public List<CompanyNewsListBean.CompanyNewsBean> getListData(){
        return mListData;
    }

    public boolean isEnd(){
        return isEnd;
    }
}
