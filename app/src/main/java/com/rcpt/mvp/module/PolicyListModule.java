package com.rcpt.mvp.module;

import com.rcpt.base.HttpResult;
import com.rcpt.base.mvp.OnDataGetCallback;
import com.rcpt.bean.PolicyListBean;
import com.rcpt.http.RetrofitManager;
import com.rcpt.http.api.ApiClient;

import java.util.List;

import rx.Subscriber;

/**
 * Created by lvzp on 2017/3/14.
 * 类描述：
 */

public class PolicyListModule {

    private List<PolicyListBean.PolicylistBean> mPolicyList;
    private boolean isEnd;


    public void requestPolicyList(String id, int page, final OnDataGetCallback<List<PolicyListBean.PolicylistBean>> callback) {
        RetrofitManager.toSubscribe(
                ApiClient.getApiService().policyList(id, page)
                , new Subscriber<HttpResult<PolicyListBean>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(HttpResult<PolicyListBean> policyListBeanHttpResult) {
                        PolicyListBean bean = policyListBeanHttpResult.getData();
                        if (mPolicyList == null)
                            mPolicyList = bean.getPolicylist();
                        else
                            mPolicyList.addAll(bean.getPolicylist());
                        isEnd = !bean.isIsNext();
                        callback.onSuccessResult(mPolicyList);
                    }
                }
        );
    }


    public List<PolicyListBean.PolicylistBean> getPolicyList() {
        return mPolicyList;
    }

    public boolean isEnd() {
        return isEnd;
    }
}
