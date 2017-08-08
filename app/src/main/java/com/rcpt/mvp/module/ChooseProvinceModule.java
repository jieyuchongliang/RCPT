package com.rcpt.mvp.module;

import android.content.Context;

import com.rcpt.base.HttpResult;
import com.rcpt.base.mvp.OnDataGetCallback;
import com.rcpt.bean.ChooseProvinceBean;
import com.rcpt.http.ProgressSubscriber;
import com.rcpt.http.RequestImpl;
import com.rcpt.http.RetrofitManager;
import com.rcpt.http.api.ApiClient;

import java.util.List;

/**
 * Created by 860116021 on 2017/3/16.
 */

public class ChooseProvinceModule {

    private List<ChooseProvinceBean> mListData;

    public void requestProvinceList(Context context, String provinceId, final OnDataGetCallback<List<ChooseProvinceBean>> callback) {
        RetrofitManager.toSubscribe(
                ApiClient.getApiService().provinceList(provinceId)
                , new ProgressSubscriber<HttpResult<List<ChooseProvinceBean>>>(context, new RequestImpl<HttpResult<List<ChooseProvinceBean>>>() {
                    @Override
                    public void onNext(HttpResult<List<ChooseProvinceBean>> result) {
                        mListData = result.getData();
                        callback.onSuccessResult(mListData);
                    }
                })

        );
    }

    public List<ChooseProvinceBean> getListData() {
        return mListData;
    }
}
