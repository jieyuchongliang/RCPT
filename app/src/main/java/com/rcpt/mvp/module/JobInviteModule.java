package com.rcpt.mvp.module;

import android.content.Context;

import com.rcpt.base.HttpResult;
import com.rcpt.base.mvp.OnDataGetCallback;
import com.rcpt.bean.JobInviteListBean;
import com.rcpt.http.ProgressSubscriber;
import com.rcpt.http.RequestImpl;
import com.rcpt.http.RetrofitManager;
import com.rcpt.http.api.ApiClient;

import java.util.List;

/**
 * Created by lvzp on 2017/3/6.
 * 类描述：
 */

public class JobInviteModule {

    private List<JobInviteListBean.JobInviteBean> mListData;
    private boolean isEnd;

    public void requestJobInviteList(Context context, String userId, int pageNum, final OnDataGetCallback<List<JobInviteListBean.JobInviteBean>> callback) {
        RetrofitManager.toSubscribe(
                ApiClient.getApiService().getInvitationList(userId, pageNum)
                , new ProgressSubscriber<HttpResult<JobInviteListBean>>(context, new RequestImpl<HttpResult<JobInviteListBean>>() {
                    @Override
                    public void onNext(HttpResult<JobInviteListBean> result) {
                        JobInviteListBean data = result.getData();
                        if (data != null) {
                            isEnd = !data.isNext();
                            if (mListData == null) {
                                mListData = data.getPosInvitationList();
                            } else {
                                mListData.addAll(data.getPosInvitationList());
                            }
                        }
                        callback.onSuccessResult(mListData);
                    }
                })
        );
    }

    public boolean isEnd() {
        return isEnd;
    }

    public List<JobInviteListBean.JobInviteBean> getListData() {
        return mListData;
    }
}
