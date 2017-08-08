package com.rcpt.ui.home.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;

import com.rcpt.R;
import com.rcpt.base.adapter.FragmentAdapter;
import com.rcpt.base.ui.BaseActivity;
import com.rcpt.databinding.ActivityNewsBinding;
import com.rcpt.mvp.contract.NewsActivityContract;
import com.rcpt.mvp.presenter.NewsActivityPresenter;

import java.util.List;

public class NewsActivity extends BaseActivity<ActivityNewsBinding, NewsActivityContract.View, NewsActivityPresenter> implements NewsActivityContract.View {

    private FragmentAdapter mAdapter;

    @Override
    protected void setupTitle() {
        setTitleText("新闻", DEFAULT_TITLE_TEXT_COLOR);
        openTitleLeftView(true);
    }

    @Override
    protected void initViews() {
        mPresenter.initFragments();
        mDataBinding.tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_news;
    }

    @Override
    protected NewsActivityPresenter createPresenter() {
        return new NewsActivityPresenter();
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
