package com.rcpt.mvp.module;

import android.content.Context;

import com.rcpt.base.HttpResult;
import com.rcpt.base.mvp.OnDataGetCallback;
import com.rcpt.bean.EnterpriseInfoBean;
import com.rcpt.http.ProgressSubscriber;
import com.rcpt.http.RequestImpl;
import com.rcpt.http.RetrofitManager;
import com.rcpt.http.api.ApiClient;

import java.util.List;

/**
 * Created by lvzp on 2017/3/27.
 * 类描述：
 */

public class EnterpriseInfoModule {

    private List<EnterpriseInfoBean.JobListBean> mJobListData;

    public void requestEnterpriseInfo(Context context, String enterpriseId, final OnDataGetCallback<EnterpriseInfoBean> callback) {
        RetrofitManager.toSubscribe(
                ApiClient.getApiService().getEnterpriseInfo(enterpriseId)
                , new ProgressSubscriber<HttpResult<EnterpriseInfoBean>>(context, new RequestImpl<HttpResult<EnterpriseInfoBean>>() {
                    @Override
                    public void onNext(HttpResult<EnterpriseInfoBean> result) {
                        mJobListData = result.getData().getJobList();
                        callback.onSuccessResult(result.getData());
                    }
                })
        );
    }

    public List<EnterpriseInfoBean.JobListBean> getJobListData() {
        return mJobListData;
    }
}
