package com.rcpt.mvp.module;

import android.content.Context;

import com.rcpt.base.HttpResult;
import com.rcpt.base.mvp.OnDataGetCallback;
import com.rcpt.bean.CompanyRecruitInfoBean;
import com.rcpt.http.ProgressSubscriber;
import com.rcpt.http.RequestImpl;
import com.rcpt.http.RetrofitManager;
import com.rcpt.http.api.ApiClient;

/**
 * Created by lvzp on 2017/4/18.
 * 类描述：
 */

public class CompanyRecruitInfoModule {


    private CompanyRecruitInfoBean mRecruitInfoBean;

    public void requestCompanyRecruitInfo(Context context, String userId, String id, final OnDataGetCallback<CompanyRecruitInfoBean> callback) {
        RetrofitManager.toSubscribe(
                ApiClient.getApiService().getCompanyRecruitInfo(userId, id)
                , new ProgressSubscriber<HttpResult<CompanyRecruitInfoBean>>(context, new RequestImpl<HttpResult<CompanyRecruitInfoBean>>() {
                    @Override
                    public void onNext(HttpResult<CompanyRecruitInfoBean> result) {
                        mRecruitInfoBean = result.getData();
                        callback.onSuccessResult(mRecruitInfoBean);
                    }
                })
        );
    }

    public CompanyRecruitInfoBean getRecruitInfoBean() {
        return mRecruitInfoBean;
    }
}
