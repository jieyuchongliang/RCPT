package com.rcpt.mvp.module;

import android.content.Context;

import com.rcpt.base.HttpResult;
import com.rcpt.base.mvp.OnDataGetCallback;
import com.rcpt.bean.SendRecordBean;
import com.rcpt.http.ProgressSubscriber;
import com.rcpt.http.RequestImpl;
import com.rcpt.http.RetrofitManager;
import com.rcpt.http.api.ApiClient;

import org.jsoup.select.Evaluator;

import java.util.List;

/**
 * Created by lvzp on 2017/3/2.
 * 类描述：
 */

public class SendRecordModule {

    private List<SendRecordBean.DeliveryRecordListBean> mListData;
    private boolean isEnd;

    public void requestSendRecordList(Context context, String userId, int page, final OnDataGetCallback<List<SendRecordBean.DeliveryRecordListBean>> callback) {
        RetrofitManager.toSubscribe(ApiClient.getApiService().getSendRecordList(userId, page)
                , new ProgressSubscriber<HttpResult<SendRecordBean>>(context, new RequestImpl<HttpResult<SendRecordBean>>() {
                    @Override
                    public void onNext(HttpResult<SendRecordBean> result) {
                        SendRecordBean bean = result.getData();
                        if (bean != null) {
                            if (mListData == null) {
                                mListData = bean.getDeliveryRecordList();
                            } else {
                                mListData.addAll(bean.getDeliveryRecordList());
                            }
                            isEnd = !bean.isNext();
                        }
                        callback.onSuccessResult(mListData);
                    }
                }));
    }


    public void delectSendRecord(Context context, String userId, final String recordId, final OnDataGetCallback<String> callback) {
        RetrofitManager.toSubscribe(
                ApiClient.getApiService().deleteCVSendRecord(recordId, userId)
                , new ProgressSubscriber<HttpResult<String>>(context, new RequestImpl<HttpResult<String>>() {
                    @Override
                    public void onNext(HttpResult<String> result) {
                        callback.onSuccessResult(result.getMsg());
                    }
                })
        );
    }

    public List<SendRecordBean.DeliveryRecordListBean> getListData() {
        return mListData;
    }

    public boolean isEnd() {
        return isEnd;
    }

}
