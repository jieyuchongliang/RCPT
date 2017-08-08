package com.rcpt.mvp.module;

import android.content.Context;

import com.rcpt.base.HttpResult;
import com.rcpt.base.mvp.OnDataGetCallback;
import com.rcpt.bean.MyOrderListBean;
import com.rcpt.http.ProgressSubscriber;
import com.rcpt.http.RequestImpl;
import com.rcpt.http.RetrofitManager;
import com.rcpt.http.api.ApiClient;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 860116021 on 2017/5/10.
 */

public class MyOrderListModule {

    private List<MyOrderListBean.OrderListBean> mOrderList;

    private boolean isEnd;

    public void requestPayOrderList(Context context, String userId, String payFlag, int pageNum, final OnDataGetCallback<List<MyOrderListBean.OrderListBean>> callback) {
        RetrofitManager.toSubscribe(
                ApiClient.getApiService().getVideoPayOrderList(userId, payFlag, pageNum)
                , new ProgressSubscriber<HttpResult<MyOrderListBean>>(context, new RequestImpl<HttpResult<MyOrderListBean>>() {
                    @Override
                    public void onNext(HttpResult<MyOrderListBean> result) {
                        MyOrderListBean data = result.getData();
                        if (data != null) {
                            isEnd = !data.isNext();
                            if (mOrderList == null) {
                                mOrderList = data.getOrderList();
                            } else {
                                mOrderList.addAll(data.getOrderList());
                            }
                        }
                        callback.onSuccessResult(mOrderList);
                    }
                })
        );
    }

    public void delOrder(Context context, String userId, String orderSn, final OnDataGetCallback<String> callback) {
        RetrofitManager.toSubscribe(
                ApiClient.getApiService().delOrder(userId, orderSn)
                , new ProgressSubscriber<HttpResult<String>>(context, new RequestImpl<HttpResult<String>>() {
                    @Override
                    public void onNext(HttpResult<String> result) {
                        callback.onSuccessResult(result.getMsg());
                    }
                })
        );
    }

    public List<MyOrderListBean.OrderListBean> getOrderList() {
        return mOrderList;
    }

    public boolean isEnd() {
        return isEnd;
    }

}
