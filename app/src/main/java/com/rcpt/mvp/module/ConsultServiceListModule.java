package com.rcpt.mvp.module;

import com.rcpt.base.HttpResult;
import com.rcpt.base.mvp.OnDataGetCallback;
import com.rcpt.bean.ConsultServiceListBean;
import com.rcpt.http.RetrofitManager;
import com.rcpt.http.api.ApiClient;

import java.util.List;

import rx.Subscriber;

/**
 * Created by lvzp on 2017/2/23.
 * 类描述：
 */

public class ConsultServiceListModule {

    private List<ConsultServiceListBean.ConsultancyListBean> mConsultServiceList;
    private boolean isEnd;

    public void requestConsultServiceList(String id, int page, final OnDataGetCallback<List<ConsultServiceListBean.ConsultancyListBean>> callback) {
        RetrofitManager.toSubscribe(
                ApiClient.getApiService().consultancyList(id, page)
                , new Subscriber<HttpResult<ConsultServiceListBean>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(HttpResult<ConsultServiceListBean> listHttpResult) {
                        if (mConsultServiceList == null) {
                            mConsultServiceList = listHttpResult.getData().getConsultancyList();
                        } else {
                            mConsultServiceList.addAll(listHttpResult.getData().getConsultancyList());
                        }
                        isEnd = !listHttpResult.getData().hasNext();
                        callback.onSuccessResult(mConsultServiceList);
                    }
                }
        );
    }

    public boolean isEnd() {
        return isEnd;
    }

    public List<ConsultServiceListBean.ConsultancyListBean> getConsultServiceList() {
        return mConsultServiceList;
    }

}
