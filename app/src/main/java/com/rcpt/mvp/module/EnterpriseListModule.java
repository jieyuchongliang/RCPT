package com.rcpt.mvp.module;

import android.support.v4.app.Fragment;

import com.rcpt.base.mvp.ViewPagerWithTabContract;
import com.rcpt.ui.enterprise.EnterpriseChildFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lvzp on 2017/2/27.
 * 类描述：
 */

public class EnterpriseListModule implements ViewPagerWithTabContract.Module {

    private List<Fragment> mFragmentList;
    private List<String> mTitleList;

    public EnterpriseListModule() {
        mFragmentList = new ArrayList<>();
        mTitleList = new ArrayList<>();
    }


    public void initFragment(String id) {
        mFragmentList.add(EnterpriseChildFragment.newInstance(id));
    }
    public void initFragmentTitle(String title){
        mTitleList.add(title);
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
