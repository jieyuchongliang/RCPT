package com.rcpt.mvp.module;

import android.support.v4.app.Fragment;

import com.rcpt.ui.home.fragment.BidListFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lvzp on 2017/2/24.
 * 类描述：
 */

public class JieFaBaoModule {

    private List<Fragment> mFragmentList;
    private List<String> mTitleList;

    public List<Fragment> getFragmentList() {
        if (mFragmentList == null)
            mFragmentList = new ArrayList<>();
        mFragmentList.add(BidListFragment.newInstance(BidListFragment.BID_TYPE_PROJECT));
        mFragmentList.add(BidListFragment.newInstance(BidListFragment.BID_TYPE_PERSON));
        return mFragmentList;
    }

    public List<String> getTitleList() {
        if (mTitleList == null)
            mTitleList = new ArrayList<>();
        mTitleList.add("竞标项目");
        mTitleList.add("人员");
        return mTitleList;
    }

}
