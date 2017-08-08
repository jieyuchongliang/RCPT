package com.rcpt.mvp.module;

import android.support.v4.app.Fragment;

import com.rcpt.ui.home.video.VideoInfoBasicFragment;
import com.rcpt.ui.home.video.VideoInfoCatalogFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lvzp on 2017/5/8.
 * 类描述：
 */

public class VideoInfoModule {

    private List<Fragment> mFragmentList;
    private List<String> mTitleList;


    public void initPageFragment(String classTypeId) {
        mFragmentList = new ArrayList<>();
        mTitleList = new ArrayList<>();
        mFragmentList.add(VideoInfoBasicFragment.newInstance(classTypeId));
        mFragmentList.add(VideoInfoCatalogFragment.newInstance(classTypeId));
        mTitleList.add("介绍");
        mTitleList.add("视频目录");
    }

    public List<Fragment> getFragmentList() {
        return mFragmentList;
    }

    public List<String> getTitleList() {
        return mTitleList;
    }
}
