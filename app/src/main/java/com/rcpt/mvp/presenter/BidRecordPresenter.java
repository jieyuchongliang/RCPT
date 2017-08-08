package com.rcpt.mvp.presenter;

import android.support.v4.view.ViewPager;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.rcpt.base.mvp.BasePresenter;
import com.rcpt.mvp.contract.BidRecordContract;
import com.rcpt.mvp.module.BidRecordModule;

/**
 * Created by 860617003 on 2017/5/25.
 */

public class BidRecordPresenter  extends BasePresenter<BidRecordContract.View>
        implements BidRecordContract.Presenter,RadioGroup.OnCheckedChangeListener, ViewPager.OnPageChangeListener{

    private BidRecordModule mModule;

    @Override
    public void attach(BidRecordContract.View view) {
        super.attach(view);
        mModule = new BidRecordModule();
    }

    @Override
    public void initFragment() {
        getView().initViewPager();
        getView().addPagerChangeListener(this);
        getView().setupViewPageWithFragment(mModule.getFragmentList(),mModule.getTitleList());
    }

    @Override
    public void setupTitle() {
        getView().setTitleTabCheckedChangeListener(this);
        getView().setTitleTabSelect(0);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        getView().setTitleTabSelect(position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }


    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        int childCount = group.getChildCount();
        for (int i = 0; i < childCount; i++) {
            android.view.View childView = group.getChildAt(i);
            if (childView instanceof RadioButton && ((RadioButton) childView).isChecked()) {
                getView().changeCurrentItem(i);
            }
        }
    }
}
