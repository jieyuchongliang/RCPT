package com.rcpt.base.mvp;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.widget.RadioGroup;

import java.util.List;

/**
 * ViewPager和RadioGroup之间的联用
 */

public interface ViewPagerWithGroupContract {

    interface View {
        void setTitleTabCheckedChangeListener(RadioGroup.OnCheckedChangeListener listener);

        void addPagerChangeListener(ViewPager.OnPageChangeListener listener);

        void initViewPager();

        void setupViewPageWithFragment(List<Fragment> fragmentList, List<String> titleList);

        /**
         * 当RadioButton点击改变时的回调
         *
         * @param currentItem
         */
        void changeCurrentItem(int currentItem);

        /**
         * 当ViewPager的切换事的回调方法
         *
         * @param position
         */
        void setTitleTabSelect(int position);
    }

    interface Presenter {

        void initFragment();

        void setupTitle();

    }


}
