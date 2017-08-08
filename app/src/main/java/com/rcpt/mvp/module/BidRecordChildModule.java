package com.rcpt.mvp.module;


import android.support.v4.app.Fragment;

import com.rcpt.ui.me.BidRecordChildListFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 860617003 on 2017/5/25.
 */

public class BidRecordChildModule {

    private List<Fragment> mFragmentList;
    private List<String> mTitleList;

    public BidRecordChildModule() {
        mFragmentList = new ArrayList<>();
        mTitleList = new ArrayList<>();
    }

    public List<Fragment> getFragmentList(String bidType) {
        mFragmentList.add(BidRecordChildListFragment.newInstance(bidType, BidRecordChildListFragment.ALL));
        mFragmentList.add(BidRecordChildListFragment.newInstance(bidType, BidRecordChildListFragment.BID_GO_ON));
        mFragmentList.add(BidRecordChildListFragment.newInstance(bidType, BidRecordChildListFragment.BID_WIN));
        mFragmentList.add(BidRecordChildListFragment.newInstance(bidType, BidRecordChildListFragment.BID_LOSE));
        return mFragmentList;
    }

    public List<String> getTitleList() {
        return mTitleList;
    }
}
