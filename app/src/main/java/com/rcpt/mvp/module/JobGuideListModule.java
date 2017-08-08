package com.rcpt.mvp.module;

import android.content.Context;
import android.support.v4.app.Fragment;

import com.rcpt.base.HttpResult;
import com.rcpt.base.mvp.OnDataGetCallback;
import com.rcpt.bean.AttrSelectBean;
import com.rcpt.http.ProgressSubscriber;
import com.rcpt.http.RequestImpl;
import com.rcpt.http.RetrofitManager;
import com.rcpt.http.api.ApiClient;
import com.rcpt.ui.recruit.fragment.JobGuideFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lvzp on 2017/3/29.
 * 类描述：
 */

public class JobGuideListModule {

    private List<Fragment> mFragmentList;
    private List<String> mTitleList;


    public JobGuideListModule() {
        mFragmentList = new ArrayList<>();
        mTitleList = new ArrayList<>();
    }

    public void requestJobGuideType(Context context, final OnDataGetCallback<List<AttrSelectBean>> callback) {
        RetrofitManager.toSubscribe(
                ApiClient.getApiService().getJobGuideType()
                , new ProgressSubscriber<HttpResult<List<AttrSelectBean>>>(context, new RequestImpl<HttpResult<List<AttrSelectBean>>>() {
                    @Override
                    public void onNext(HttpResult<List<AttrSelectBean>> result) {
                        callback.onSuccessResult(result.getData());
                    }
                })
        );
    }

    public void buildFragmentAddList(String id) {
        mFragmentList.add(JobGuideFragment.newInstance(id));
    }

    public void buildTitleAddList(String title) {
        mTitleList.add(title);
    }

    public List<Fragment> getFragmentList() {
        return mFragmentList;
    }

    public List<String> getTitleList() {
        return mTitleList;
    }
}
