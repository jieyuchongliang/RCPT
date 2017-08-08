package com.rcpt.mvp.module;

import android.content.Context;

import com.rcpt.base.HttpResult;
import com.rcpt.base.mvp.OnDataGetCallback;
import com.rcpt.bean.TestMajorListBean;
import com.rcpt.bean.TestMajorTypeBean;
import com.rcpt.http.ProgressSubscriber;
import com.rcpt.http.RequestImpl;
import com.rcpt.http.RetrofitManager;
import com.rcpt.http.api.ApiClient;

import java.util.List;

import rx.Subscriber;

/**
 * Created by lvzp on 2017/4/5.
 * 类描述：
 */

public class TestMajorModule {

    private List<TestMajorTypeBean> mMajorTypeList;
    private List<TestMajorListBean.TestlistBean> mMajorList;
    private boolean isEnd;

    public void requestTestMajorType(final OnDataGetCallback<List<TestMajorTypeBean>> callback) {

        RetrofitManager.toSubscribe(
                ApiClient.getApiService().getTestMajorType()
                , new Subscriber<HttpResult<List<TestMajorTypeBean>>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(HttpResult<List<TestMajorTypeBean>> listHttpResult) {
                        if (listHttpResult.isResult()) {
                            mMajorTypeList = listHttpResult.getData();
                            TestMajorTypeBean bean = new TestMajorTypeBean();
                            bean.setPointId("");
                            bean.setPointName("全部");
                            mMajorTypeList.add(0, bean);
                            callback.onSuccessResult(mMajorTypeList);
                        }
                    }
                }
        );

    }

    public void requestTestMajorList(Context context, int pageNum, String typeId, String searchKey, final OnDataGetCallback<List<TestMajorListBean.TestlistBean>> callback) {
        RetrofitManager.toSubscribe(
                ApiClient.getApiService().getTestMajorList(pageNum, typeId, searchKey)
                , new ProgressSubscriber<HttpResult<TestMajorListBean>>(context, new RequestImpl<HttpResult<TestMajorListBean>>() {
                    @Override
                    public void onNext(HttpResult<TestMajorListBean> result) {
                        isEnd = !result.getData().isIsNext();
                        List<TestMajorListBean.TestlistBean> testlist = result.getData().getTestlist();
                        if (mMajorList == null) {
                            mMajorList = testlist;
                        } else {
                            mMajorList.addAll(testlist);
                        }
                        callback.onSuccessResult(mMajorList);
                    }
                })
        );
    }


    public List<TestMajorListBean.TestlistBean> getMajorList() {
        return mMajorList;
    }

    public boolean isEnd() {
        return isEnd;
    }

    public List<TestMajorTypeBean> getTestMajorTypeList() {
        return mMajorTypeList;
    }
}
