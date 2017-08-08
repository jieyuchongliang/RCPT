package com.rcpt.base.mvp;

import android.support.v4.app.Fragment;

import java.util.List;

/**
 * Created by lvzp on 2017/2/23.
 * 类描述：ViewPage和TabLayout的联用
 */

public interface ViewPagerWithTabContract {
    interface View {
        void bindTabFragment(List<Fragment> fragmentList, List<String> titleList);
    }

    interface Presenter {
        void initFragments();
    }

    interface Module {
        List<Fragment> getFragmentList();

        List<String> getTitleList();
    }
}
