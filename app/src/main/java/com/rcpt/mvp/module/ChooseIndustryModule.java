package com.rcpt.mvp.module;

import android.content.Context;

import com.rcpt.base.HttpResult;
import com.rcpt.base.mvp.OnDataGetCallback;
import com.rcpt.bean.ChooseIndustryBean;
import com.rcpt.http.ProgressSubscriber;
import com.rcpt.http.RequestImpl;
import com.rcpt.http.RetrofitManager;
import com.rcpt.http.api.ApiClient;

import java.util.List;

/**
 * Created by 860116021 on 2017/3/15.
 */

public class ChooseIndustryModule {

    private List<ChooseIndustryBean> mListDate;

    public void requestChooseIndustryList(Context context, final OnDataGetCallback<List<ChooseIndustryBean>> callback) {
        RetrofitManager.toSubscribe(
                ApiClient.getApiService().industryList()
                , new ProgressSubscriber<>(context, new RequestImpl<HttpResult<List<ChooseIndustryBean>>>() {
                    @Override
                    public void onNext(HttpResult<List<ChooseIndustryBean>> listHttpResult) {
                        mListDate = listHttpResult.getData();
                        callback.onSuccessResult(mListDate);
                    }
                })
        );
    }

    public List<ChooseIndustryBean> getListDate() {
        return mListDate;
    }
}
