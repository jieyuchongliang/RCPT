package com.rcpt.ui.home.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.rcpt.R;
import com.rcpt.base.adapter.FragmentAdapter;
import com.rcpt.base.ui.BaseActivity;
import com.rcpt.databinding.ActivityJieFaBaoBinding;
import com.rcpt.mvp.contract.JieFaBaoContract;
import com.rcpt.mvp.presenter.JieFaBaoPresenter;

import java.util.List;

/**
 * 接发包的详情页面
 */
public class JieFaBaoActivity extends BaseActivity<ActivityJieFaBaoBinding, JieFaBaoContract.View, JieFaBaoPresenter> implements JieFaBaoContract.View {

    private FragmentAdapter mFragmentAdapter;
    private RadioGroup mTitleTabGroup;

    @Override
    protected void setupTitle() {
        mTitleTabGroup = (RadioGroup) setTitleCenterViewRes(R.layout.layout_tab_white_blue_select, false);
        openTitleLeftView(true);
        mPresenter.setupTitle();

    }

    @Override
    public void setTitleTabSelect(int position) {
        ((RadioButton) mTitleTabGroup.getChildAt(position)).setChecked(true);
    }

    @Override
    public void setTitleTabCheckedChangeListener(RadioGroup.OnCheckedChangeListener listener) {
        mTitleTabGroup.setOnCheckedChangeListener(listener);
    }

    @Override
    public void changeCurrentItem(int currentItem) {
        mDataBinding.viewPager.setCurrentItem(currentItem);
    }

    @Override
    protected void initViews() {
        mPresenter.initFragment();
    }

    @Override
    public void initViewPager() {
        mFragmentAdapter = new FragmentAdapter(getSupportFragmentManager());
        mDataBinding.viewPager.setAdapter(mFragmentAdapter);
    }

    @Override
    public void setupViewPageWithFragment(List<Fragment> fragmentList, List<String> titleList) {
        mFragmentAdapter.addFragmentList(fragmentList);
        for (int i = 0; i < mTitleTabGroup.getChildCount(); i++) {
            android.view.View childView = mTitleTabGroup.getChildAt(i);
            if (childView instanceof RadioButton) {
                ((RadioButton) childView).setText(titleList.get(i));
            }
        }
    }

    @Override
    public void addPagerChangeListener(ViewPager.OnPageChangeListener listener) {
        mDataBinding.viewPager.addOnPageChangeListener(listener);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_jie_fa_bao;
    }

    @Override
    protected JieFaBaoPresenter createPresenter() {
        return new JieFaBaoPresenter();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}
