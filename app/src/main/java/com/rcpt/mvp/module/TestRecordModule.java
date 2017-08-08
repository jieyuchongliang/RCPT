package com.rcpt.mvp.module;

import android.content.Context;

import com.rcpt.base.HttpResult;
import com.rcpt.base.mvp.OnDataGetCallback;
import com.rcpt.bean.TestRecordListBean;
import com.rcpt.http.ProgressSubscriber;
import com.rcpt.http.RequestImpl;
import com.rcpt.http.RetrofitManager;
import com.rcpt.http.api.ApiClient;

import java.util.List;

/**
 * Created by 860617003 on 2017/5/25.
 */

public class TestRecordModule {

    private boolean isEnd;
    private List<TestRecordListBean.CompanyFavoritesBean> mListData;

    public void requestTestRecordList(Context context, String userId, int pageNum, final OnDataGetCallback<List<TestRecordListBean.CompanyFavoritesBean>> callback) {
        RetrofitManager.toSubscribe(
                ApiClient.getApiService().getTestRecordList(userId, pageNum)
                , new ProgressSubscriber<HttpResult<TestRecordListBean>>(context, new RequestImpl<HttpResult<TestRecordListBean>>() {
                    @Override
                    public void onNext(HttpResult<TestRecordListBean> result) {
                        TestRecordListBean data = result.getData();
                        if (data != null) {
                            if (mListData == null) {
                                mListData = data.getCompanyFavorites();
                            } else {
                                mListData.addAll(data.getCompanyFavorites());
                            }
                            isEnd = !data.isIsNext();
                        }
                        callback.onSuccessResult(mListData);
                    }
                })
        );
    }


    public boolean isEnd() {
        return isEnd;
    }

    public List<TestRecordListBean.CompanyFavoritesBean> getListData() {
        return mListData;
    }
}
