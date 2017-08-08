package com.rcpt.mvp.module;

import android.content.Context;

import com.rcpt.base.HttpResult;
import com.rcpt.base.mvp.OnDataCallback;
import com.rcpt.base.mvp.OnDataGetCallback;
import com.rcpt.bean.SystemMessageBean;
import com.rcpt.http.ProgressSubscriber;
import com.rcpt.http.RequestImpl;
import com.rcpt.http.RetrofitManager;
import com.rcpt.http.api.ApiClient;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lvzp on 2017/3/6.
 * 类描述：
 */

public class SystemMessageModule {

    private List<SystemMessageBean.MessageListBean> mListData;
    private boolean isEnd;


    public void requestMessageList(Context context, String userType, int pageNum, final OnDataGetCallback<List<SystemMessageBean.MessageListBean>> callback) {
        RetrofitManager.toSubscribe(
                ApiClient.getApiService().getMessageList(userType, pageNum)
                , new ProgressSubscriber<HttpResult<SystemMessageBean>>(context, new RequestImpl<HttpResult<SystemMessageBean>>() {
                    @Override
                    public void onNext(HttpResult<SystemMessageBean> result) {
                        SystemMessageBean data = result.getData();
                        if (data != null) {
                            if (mListData == null) {
                                mListData = data.getMessageList();
                            } else {
                                mListData.addAll(data.getMessageList());
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

    public List<SystemMessageBean.MessageListBean> getListData() {
        return mListData;
    }
}
