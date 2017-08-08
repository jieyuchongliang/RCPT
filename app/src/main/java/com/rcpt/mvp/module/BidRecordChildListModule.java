package com.rcpt.mvp.module;

import android.content.Context;

import com.rcpt.base.HttpResult;
import com.rcpt.base.mvp.OnDataGetCallback;
import com.rcpt.bean.BidRecordListBean;
import com.rcpt.http.ProgressSubscriber;
import com.rcpt.http.RequestImpl;
import com.rcpt.http.RetrofitManager;
import com.rcpt.http.api.ApiClient;

import java.util.List;

/**
 * Created by 860617003 on 2017/5/25.
 */

public class BidRecordChildListModule {

    private List<BidRecordListBean.ProjectProlistBean> mListData;
    private boolean isEnd;

    /**
     * 获取项目竞标的列表
     *
     * @param context
     * @param userId
     * @param bidStatus
     * @param pageNum
     * @param callback
     */
    public void requestProjectBidRecordList(Context context, String userId, String bidStatus, int pageNum, final OnDataGetCallback<List<BidRecordListBean.ProjectProlistBean>> callback) {
        RetrofitManager.toSubscribe(
                ApiClient.getApiService().getProjectBidListInfo(userId, bidStatus, pageNum)
                , new ProgressSubscriber<HttpResult<BidRecordListBean>>(context, new RequestImpl<HttpResult<BidRecordListBean>>() {
                    @Override
                    public void onNext(HttpResult<BidRecordListBean> result) {
                        BidRecordListBean data = result.getData();
                        if (data != null) {
                            if (mListData == null) {
                                mListData = data.getList();
                            } else {
                                mListData.addAll(data.getList());
                            }
                            isEnd = !data.isIsNext();
                        }
                        callback.onSuccessResult(mListData);
                    }
                })
        );
    }

    /**
     * 获取人员竞标的列表
     *
     * @param context
     * @param userId
     * @param bidStatus
     * @param pageNum
     * @param callback
     */
    public void requestPersonBidRecordList(Context context, String userId, String bidStatus, int pageNum, final OnDataGetCallback<List<BidRecordListBean.ProjectProlistBean>> callback) {
        RetrofitManager.toSubscribe(
                ApiClient.getApiService().getPersonBidListInfo(userId, bidStatus, pageNum)
                , new ProgressSubscriber<HttpResult<BidRecordListBean>>(context, new RequestImpl<HttpResult<BidRecordListBean>>() {
                    @Override
                    public void onNext(HttpResult<BidRecordListBean> result) {
                        BidRecordListBean data = result.getData();
                        if (data != null) {
                            if (mListData == null) {
                                mListData = data.getList();
                            } else {
                                mListData.addAll(data.getList());
                            }
                            isEnd = !data.isIsNext();
                        }
                        callback.onSuccessResult(mListData);
                    }
                })
        );
    }

    public List<BidRecordListBean.ProjectProlistBean> getListData() {
        return mListData;
    }

    public boolean isEnd() {
        return isEnd;
    }
}
