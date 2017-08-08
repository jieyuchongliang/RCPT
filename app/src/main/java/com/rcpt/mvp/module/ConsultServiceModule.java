package com.rcpt.mvp.module;

import android.content.Context;
import android.support.v4.app.Fragment;

import com.rcpt.base.HttpResult;
import com.rcpt.base.mvp.OnDataGetCallback;
import com.rcpt.base.mvp.ViewPagerWithTabContract;
import com.rcpt.bean.PolicyTypeBean;
import com.rcpt.http.ProgressSubscriber;
import com.rcpt.http.RequestImpl;
import com.rcpt.http.RetrofitManager;
import com.rcpt.http.api.ApiClient;
import com.rcpt.ui.home.fragment.ConsultServiceFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lvzp on 2017/2/23.
 * 类描述：
 */

public class ConsultServiceModule implements ViewPagerWithTabContract.Module {

    private List<Fragment> mFragmentList;
    private List<String> mTitleList;
    private List<PolicyTypeBean> mPolicyTypeList;

    /**
     * 从网络中请求政策法规的父级菜单
     */
    public void requestConsultMenu(Context context, final OnDataGetCallback<Void> callback) {
        RetrofitManager.toSubscribe(
                ApiClient.getApiService().consultancyType()
                , new ProgressSubscriber<HttpResult<List<PolicyTypeBean>>>(context, new RequestImpl<HttpResult<List<PolicyTypeBean>>>() {
                    @Override
                    public void onNext(HttpResult<List<PolicyTypeBean>> listHttpResult) {
                        mPolicyTypeList = listHttpResult.getData();
                        initList();
                        if (mPolicyTypeList == null)
                            return;
                        addTitle();
                        createFragment();
                        callback.onSuccessResult(null);
                    }
                })
        );
    }

    private void addTitle() {
        for (PolicyTypeBean policyTypeBean : mPolicyTypeList) {
            mTitleList.add(policyTypeBean.getValue());
        }
    }

    private void createFragment() {
        for (PolicyTypeBean policyTypeBean : mPolicyTypeList) {
            mFragmentList.add(ConsultServiceFragment.newInstance(policyTypeBean.getDistinguishId()));
        }
    }

    @Override
    public List<Fragment> getFragmentList() {
        return mFragmentList;
    }

    @Override
    public List<String> getTitleList() {
        return mTitleList;
    }

    private void initList() {
        if (mTitleList == null)
            mTitleList = new ArrayList<>();
        else
            mTitleList.clear();
        if (mFragmentList == null)
            mFragmentList = new ArrayList<>();
        else
            mFragmentList.clear();
    }
}
