package com.rcpt.mvp.module;

import android.content.Context;
import android.content.RestrictionsManager;

import com.rcpt.base.HttpResult;
import com.rcpt.base.mvp.OnDataGetCallback;
import com.rcpt.bean.BidListBean;
import com.rcpt.http.ProgressSubscriber;
import com.rcpt.http.RequestImpl;
import com.rcpt.http.RetrofitManager;
import com.rcpt.http.api.ApiClient;
import com.rcpt.http.api.ApiService;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lvzp on 2017/2/24.
 * 类描述：
 */

public class BidListModule {

    private List<BidListBean.ProjectlistBean> mBidBeanList;
    private List<BidListBean.personnelProlistBean> mBidPersonList;
    private boolean isEnd;

    public void listData(final Context context , int page, final OnDataGetCallback<List<BidListBean.ProjectlistBean>> callback){
        RetrofitManager.toSubscribe(ApiClient.getApiService().getBidObjectList(page), new ProgressSubscriber<>(context, new RequestImpl<HttpResult<BidListBean>>() {
            @Override
            public void onNext(HttpResult<BidListBean> result) {
                BidListBean bean = result.getData();
                if(mBidBeanList == null){
                    mBidBeanList = bean.getProjectlist();
                }else {
                    mBidBeanList.addAll(bean.getProjectlist());
                }
                isEnd = !bean.isIsNext();
                callback.onSuccessResult(mBidBeanList);
            }
        }));
    }

    public void listPerson(final Context context, int page, final OnDataGetCallback<List<BidListBean.personnelProlistBean>> callback){
        RetrofitManager.toSubscribe(ApiClient.getApiService().getBidPersonList(page),new ProgressSubscriber<>(context, new RequestImpl<HttpResult<BidListBean>>() {
            @Override
            public void onNext(HttpResult<BidListBean> result) {
                BidListBean bean = result.getData();
                if(mBidPersonList == null){
                    mBidPersonList = bean.getPersonnelProlist();
                }else{
                    mBidPersonList.addAll(bean.getPersonnelProlist());
                }
                isEnd = !bean.isIsNext();
                callback.onSuccessResult(mBidPersonList);
            }
        }));
    }

    public List<BidListBean.ProjectlistBean> getBidBeanList() {
        return mBidBeanList;
    }

    public List<BidListBean.personnelProlistBean> getBidPersonList(){
        return mBidPersonList;
    }

    public boolean isEnd() {
        return isEnd;
    }
}
