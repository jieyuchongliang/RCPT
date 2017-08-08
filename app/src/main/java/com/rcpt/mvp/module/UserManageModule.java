package com.rcpt.mvp.module;

import android.support.v4.app.Fragment;

import com.rcpt.ui.system.fragment.UserManageFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 860617003 on 2017/6/2.
 */

public class UserManageModule {

    private List<Fragment> mFragmentList;
    private List<String> mTitleList;

    public UserManageModule() {

        mFragmentList = new ArrayList<>();
        mTitleList = new ArrayList<>();
        mFragmentList.add(UserManageFragment.newInstance(UserManageFragment.GROUP_USER));
        mFragmentList.add(UserManageFragment.newInstance(UserManageFragment.PERSON_USER));

        mTitleList.add("团体会员");
        mTitleList.add("个人会员");

    }

    public List<Fragment> getFragmentList() {
        return mFragmentList;
    }

    public List<String> getTitleList() {
        return mTitleList;
    }
}
