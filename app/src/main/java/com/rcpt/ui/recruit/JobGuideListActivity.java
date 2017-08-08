package com.rcpt.ui.recruit;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.rcpt.R;
import com.rcpt.base.adapter.FragmentAdapter;
import com.rcpt.base.ui.BaseActivity;
import com.rcpt.databinding.ActivityJobGuideListBinding;
import com.rcpt.mvp.contract.JobGuideListContract;
import com.rcpt.mvp.presenter.JobGuideListPresenter;

import java.util.List;

/**
 * 求职指南列表
 */
public class JobGuideListActivity extends BaseActivity<ActivityJobGuideListBinding, JobGuideListContract.View, JobGuideListPresenter>
        implements JobGuideListContract.View {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    /**
     * 未实现的方法
     */
    @Override
    protected void setupTitle() {
        setTitleText("求职指南");
        openTitleLeftView(true);
    }

    @Override
    protected void initViews() {
        mPresenter.initFragments();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_job_guide_list;
    }

    @Override
    protected JobGuideListPresenter createPresenter() {
        return new JobGuideListPresenter();
    }

    @Override
    public void bindPageData(List<Fragment> fragmentList, List<String> titleList) {
        FragmentAdapter adapter = new FragmentAdapter(getSupportFragmentManager());
        adapter.addFragmentWithTitle(fragmentList, titleList);
        mDataBinding.viewPager.setAdapter(adapter);
        mDataBinding.viewPager.setOffscreenPageLimit(fragmentList.size());
        mDataBinding.tabLayout.setupWithViewPager(mDataBinding.viewPager);
    }
}
