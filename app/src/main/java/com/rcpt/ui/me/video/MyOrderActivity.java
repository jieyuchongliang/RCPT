package com.rcpt.ui.me.video;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.widget.Adapter;

import com.rcpt.R;
import com.rcpt.base.adapter.FragmentAdapter;
import com.rcpt.base.ui.BaseActivity;
import com.rcpt.databinding.ActivityMyOrderBinding;
import com.rcpt.mvp.contract.MyOrderActivityContract;
import com.rcpt.mvp.presenter.MyOrderActivityPresenter;

import java.util.List;

public class MyOrderActivity extends BaseActivity<ActivityMyOrderBinding, MyOrderActivityContract.View, MyOrderActivityPresenter> implements MyOrderActivityContract.View {

    private FragmentAdapter mAdapter;

    @Override
    protected void setupTitle() {
        setTitleText("我的订单");
        openTitleLeftView(true);
    }

    @Override
    protected void initViews() {
        mPresenter.initFragments();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_my_order;
    }

    @Override
    protected MyOrderActivityPresenter createPresenter() {
        return new MyOrderActivityPresenter();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void bindTabFragment(List<Fragment> fragmentList, List<String> titleList) {
        if (fragmentList.isEmpty() || titleList.isEmpty()) {
            return;
        }
        mAdapter = new FragmentAdapter(getSupportFragmentManager());
        mAdapter.addFragmentWithTitle(fragmentList, titleList);
        mDataBinding.viewPager.setAdapter(mAdapter);
        mDataBinding.tabLayout.setupWithViewPager(mDataBinding.viewPager);
        mDataBinding.viewPager.setOffscreenPageLimit(0);
    }
}
