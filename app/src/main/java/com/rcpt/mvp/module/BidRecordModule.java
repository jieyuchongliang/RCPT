package com.rcpt.mvp.module;

import android.support.v4.app.Fragment;

import com.rcpt.ui.home.fragment.BidListFragment;
import com.rcpt.ui.me.BidRecordChildFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 860617003 on 2017/5/25.
 */

public class BidRecordModule {

    private List<Fragment> mFragmentList;
    private List<String> mTitleList;

    public List<Fragment> getFragmentList() {
        if (mFragmentList == null)
            mFragmentList = new ArrayList<>();
        mFragmentList.add(BidRecordChildFragment.newInstance(BidRecordChildFragment.BID_PROJECT));
        mFragmentList.add(BidRecordChildFragment.newInstance(BidRecordChildFragment.BID_PERSON));
        return mFragmentList;
    }

    public List<String> getTitleList() {
        if (mTitleList == null)
            mTitleList = new ArrayList<>();
        mTitleList.add("项目竞标");
        mTitleList.add("人员竞标");
        return mTitleList;
    }

}
