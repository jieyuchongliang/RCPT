package com.rcpt.ui.home.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.rcpt.R;
import com.rcpt.base.adapter.FragmentAdapter;
import com.rcpt.base.ui.BaseActivity;
import com.rcpt.databinding.ActivityPolicyBinding;
import com.rcpt.mvp.contract.PolicyContract;
import com.rcpt.mvp.presenter.PolicyPresenter;

import java.util.List;

public class PolicyActivity extends BaseActivity<ActivityPolicyBinding, PolicyContract.View, PolicyPresenter>
        implements PolicyContract.View {

    private FragmentAdapter mAdapter;

    @Override
    protected void setupTitle() {
        setTitleText("政策法规", DEFAULT_TITLE_TEXT_COLOR);
        openTitleLeftView(true);
    }

    @Override
    protected void initViews() {
        mPresenter.initFragments();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_policy;
    }

    @Override
    protected PolicyPresenter createPresenter() {
        return new PolicyPresenter();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void bindTabFragment(List<Fragment> fragmentList, List<String> titleList) {
        if (fragmentList.isEmpty() || titleList.isEmpty()) return;
        mAdapter = new FragmentAdapter(getSupportFragmentManager());
        mAdapter.addFragmentWithTitle(fragmentList, titleList);
        mDataBinding.viewPager.setAdapter(mAdapter);
        mDataBinding.tabLayout.setupWithViewPager(mDataBinding.viewPager);
    }
}
