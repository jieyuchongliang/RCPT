package com.rcpt.mvp.module;

import android.content.Context;

import com.rcpt.base.HttpResult;
import com.rcpt.base.mvp.OnDataGetCallback;
import com.rcpt.bean.CompanyRecruitListBean;
import com.rcpt.http.ProgressSubscriber;
import com.rcpt.http.RequestImpl;
import com.rcpt.http.RetrofitManager;
import com.rcpt.http.api.ApiClient;

import java.util.List;

/**
 * Created by lvzp on 2017/4/17.
 * 类描述：
 */

public class CompanyRecruitModule {

    private boolean isEnd;
    private List<CompanyRecruitListBean.RecruitlistBean> mListData;


    public void requestRecruitList(Context context, String userId, int pageNum, String cvUserId,String cvId,final OnDataGetCallback<List<CompanyRecruitListBean.RecruitlistBean>> callback) {
        RetrofitManager.toSubscribe(
                ApiClient.getApiService().getRecruitList(userId, pageNum,cvUserId,cvId)
                , new ProgressSubscriber<HttpResult<CompanyRecruitListBean>>(context, new RequestImpl<HttpResult<CompanyRecruitListBean>>() {
                    @Override
                    public void onNext(HttpResult<CompanyRecruitListBean> result) {
                        CompanyRecruitListBean data = result.getData();
                        isEnd = !data.isIsNext();
                        if (mListData == null) {
                            mListData = data.getRecruitlist();
                        } else {
                            mListData.addAll(data.getRecruitlist());
                        }
                        callback.onSuccessResult(mListData);
                    }
                })
        );
    }

    public boolean isEnd() {
        return isEnd;
    }

    public List<CompanyRecruitListBean.RecruitlistBean> getListData() {
        return mListData;
    }
}
