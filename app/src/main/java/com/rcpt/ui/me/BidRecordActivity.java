package com.rcpt.ui.me;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.rcpt.R;
import com.rcpt.base.adapter.FragmentAdapter;
import com.rcpt.base.ui.BaseActivity;
import com.rcpt.databinding.ActivityBidRecordBinding;
import com.rcpt.mvp.contract.BidRecordContract;
import com.rcpt.mvp.presenter.BidRecordPresenter;

import java.util.List;

/**
 * 投标记录的Activity
 */
public class BidRecordActivity extends BaseActivity<ActivityBidRecordBinding, BidRecordContract.View, BidRecordPresenter>
        implements BidRecordContract.View {

    private RadioGroup mTitleGroup;
    private FragmentAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    /**
     * 未实现的方法
     */
    @Override
    protected void setupTitle() {
        mTitleGroup = (RadioGroup) setTitleCenterViewRes(R.layout.layout_tab_white_blue_select, false);
        openTitleLeftView(true);
        mPresenter.setupTitle();
    }

    @Override
    protected void initViews() {
        mPresenter.initFragment();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_bid_record;
    }

    @Override
    public void setTitleTabCheckedChangeListener(RadioGroup.OnCheckedChangeListener listener) {
        mTitleGroup.setOnCheckedChangeListener(listener);
    }

    @Override
    public void addPagerChangeListener(ViewPager.OnPageChangeListener listener) {
        mDataBinding.viewPager.addOnPageChangeListener(listener);
    }

    @Override
    public void initViewPager() {
        mAdapter = new FragmentAdapter(getSupportFragmentManager());
        mDataBinding.viewPager.setAdapter(mAdapter);
    }

    @Override
    public void setupViewPageWithFragment(List<Fragment> fragmentList, List<String> titleList) {
        mAdapter.addFragmentList(fragmentList);
        for (int i = 0; i < mTitleGroup.getChildCount(); i++) {
            android.view.View childView = mTitleGroup.getChildAt(i);
            if (childView instanceof RadioButton) {
                ((RadioButton) childView).setText(titleList.get(i));
            }
        }
    }

    @Override
    public void changeCurrentItem(int currentItem) {
        mDataBinding.viewPager.setCurrentItem(currentItem);
    }

    @Override
    public void setTitleTabSelect(int position) {
        ((RadioButton) mTitleGroup.getChildAt(position)).setChecked(true);
    }

    @Override
    protected BidRecordPresenter createPresenter() {
        return new BidRecordPresenter();
    }
}
