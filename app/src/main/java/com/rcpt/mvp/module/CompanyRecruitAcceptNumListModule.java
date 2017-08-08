package com.rcpt.mvp.module;

import android.content.Context;

import com.rcpt.base.HttpResult;
import com.rcpt.base.mvp.OnDataGetCallback;
import com.rcpt.bean.CompanyRecruitAcceptNumListBean;
import com.rcpt.http.ProgressSubscriber;
import com.rcpt.http.RequestImpl;
import com.rcpt.http.RetrofitManager;
import com.rcpt.http.api.ApiClient;

import java.util.List;
import java.util.Map;

/**
 * Created by lvzp on 2017/5/2.
 * 类描述：
 */

public class CompanyRecruitAcceptNumListModule {


    private List<CompanyRecruitAcceptNumListBean.JobPeopleListBean> mListData;
    private boolean isEnd;

    public void updateCVPreviewStatus(Context context, String companyId, String userId, String cvId, final OnDataGetCallback<Boolean> callback){
        RetrofitManager.toSubscribe(
                ApiClient.getApiService().updateCVPreviewStatus(companyId,userId,cvId)
                ,new ProgressSubscriber<HttpResult<String>>(context, new RequestImpl<HttpResult<String>>() {
                    @Override
                    public void onNext(HttpResult<String> result) {
                        callback.onSuccessResult(true);
                    }
                })
        );
    }

    public void requestRecruitAcceptList(Context context, String userId, String infoId, int pageNum, final OnDataGetCallback<List<CompanyRecruitAcceptNumListBean.JobPeopleListBean>> callback) {
        RetrofitManager.toSubscribe(
                ApiClient.getApiService().getJobPeople(userId, infoId, pageNum)
                , new ProgressSubscriber<HttpResult<CompanyRecruitAcceptNumListBean>>(context, new RequestImpl<HttpResult<CompanyRecruitAcceptNumListBean>>() {
                    @Override
                    public void onNext(HttpResult<CompanyRecruitAcceptNumListBean> result) {
                        CompanyRecruitAcceptNumListBean data = result.getData();
                        isEnd = !data.isIsNext();
                        if (mListData == null) {
                            mListData = data.getJobPeopleList();
                        } else {
                            mListData.addAll(data.getJobPeopleList());
                        }
                        callback.onSuccessResult(mListData);
                    }
                })
        );
    }

    public void sendJobInterviewNotice(Context context, Map<String, String> params, final OnDataGetCallback<String> callback) {
        RetrofitManager.toSubscribe(
                ApiClient.getApiService().addNotice(params)
                , new ProgressSubscriber<HttpResult<String>>(context, new RequestImpl<HttpResult<String>>() {
                    @Override
                    public void onNext(HttpResult<String> result) {
                        callback.onSuccessResult(result.getMsg());
                    }
                })
        );
    }

    public boolean isEnd() {
        return isEnd;
    }

    public List<CompanyRecruitAcceptNumListBean.JobPeopleListBean> getListData() {
        return mListData;
    }
}
