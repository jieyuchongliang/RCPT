package com.rcpt.mvp.module;

import android.support.v4.app.Fragment;

import com.rcpt.base.mvp.ViewPagerWithTabContract;
import com.rcpt.ui.me.video.MyOrderListFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 860116021 on 2017/5/10.
 */

public class MyOrderModule implements ViewPagerWithTabContract.Module {

    private List<Fragment> mFragmentList;
    private List<String> mTitleList;

    public void initTab() {
        addTitle();
        createFragment();
    }

    private void addTitle() {
        if (mTitleList == null) {
            mTitleList = new ArrayList<>();
        } else {
            mTitleList.clear();
        }
        mTitleList.add("全部");
        mTitleList.add("已完成");
        mTitleList.add("未完成");
    }

    private void createFragment() {
        if (mFragmentList == null) {
            mFragmentList = new ArrayList<>();
        } else {
            mFragmentList.clear();
        }
        mFragmentList.add(MyOrderListFragment.newInstance(MyOrderListFragment.ORDER_TYPE_ALL));
        mFragmentList.add(MyOrderListFragment.newInstance(MyOrderListFragment.ORDER_TYPE_FINISH));
        mFragmentList.add(MyOrderListFragment.newInstance(MyOrderListFragment.ORDER_TYPE_WAIT_PAY));
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
