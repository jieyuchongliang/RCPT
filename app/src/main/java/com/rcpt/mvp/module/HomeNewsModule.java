package com.rcpt.mvp.module;

import android.support.v4.app.Fragment;

import com.rcpt.base.mvp.ViewPagerWithTabContract;
import com.rcpt.bean.PolicyTypeBean;
import com.rcpt.ui.home.fragment.NewsListFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lvzp on 2017/2/23.
 * 类描述：
 */

public class HomeNewsModule implements ViewPagerWithTabContract.Module {

    private List<Fragment> mFragmentList;
    private List<String> mTitleList;
    private List<PolicyTypeBean> mPolicyTypeList;


    public void setPolicyTypeList(List<PolicyTypeBean> policyTypeList) {
        mPolicyTypeList = policyTypeList;
        addTitle();
        createFragment();
    }

    private void addTitle() {
        if (mTitleList == null)
            mTitleList = new ArrayList<>();
        else
            mTitleList.clear();
        for (PolicyTypeBean policyTypeBean : mPolicyTypeList) {
            mTitleList.add(policyTypeBean.getValue());
        }
    }

    private void createFragment() {
        if (mFragmentList == null)
            mFragmentList = new ArrayList<>();
        else
            mFragmentList.clear();
        for (PolicyTypeBean policyTypeBean : mPolicyTypeList) {
            mFragmentList.add(NewsListFragment.newInstance(policyTypeBean.getDistinguishId(),policyTypeBean.getType()));
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

}
