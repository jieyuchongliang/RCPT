package com.rcpt.mvp.module;

import android.support.v4.app.Fragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lvzp on 2017/3/3.
 * 类描述：
 */

public class CreateCVInfoModule {


    private List<Fragment> mTaskFragmentList;
    private List<String> mTaskFragmentTitleList;

    public CreateCVInfoModule() {
        mTaskFragmentList = new ArrayList<>();
        mTaskFragmentTitleList = new ArrayList<>();
    }

    public void addFragmentToTask(Fragment fragment, String title) {
        mTaskFragmentList.add(fragment);
        mTaskFragmentTitleList.add(title);
    }

    public List<String> getTaskFragmentTitleList() {
        return mTaskFragmentTitleList;
    }

    public List<Fragment> getTaskFragmentList() {
        return mTaskFragmentList;
    }

}
