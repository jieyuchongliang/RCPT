package com.rcpt.ui.home.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.rcpt.R;
import com.rcpt.base.adapter.FragmentAdapter;
import com.rcpt.base.ui.BaseActivity;
import com.rcpt.databinding.ActivityConsultSeiviceBinding;
import com.rcpt.mvp.contract.ConsultServiceContract;
import com.rcpt.mvp.presenter.ConsultServicePresenter;

import java.util.List;

/**
 * 咨询服务的Activity页面
 */
public class ConsultServiceActivity extends BaseActivity<ActivityConsultSeiviceBinding, ConsultServiceContract.View, ConsultServicePresenter>
        implements ConsultServiceContract.View {

    @Override
    protected void setupTitle() {
        setTitleText("咨询服务", DEFAULT_TITLE_TEXT_COLOR);
        openTitleLeftView(true);
    }

    @Override
    protected void initViews() {
        mPresenter.initFragments();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_consult_seivice;
    }

    @Override
    protected ConsultServicePresenter createPresenter() {
        return new ConsultServicePresenter();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void bindTabFragment(List<Fragment> fragmentList, List<String> titleList) {
        if (fragmentList.isEmpty() || titleList.isEmpty()) return;
        FragmentAdapter adapter = new FragmentAdapter(getSupportFragmentManager());
        adapter.addFragmentWithTitle(fragmentList, titleList);
        mDataBinding.viewPager.setAdapter(adapter);
        mDataBinding.tabLayout.setupWithViewPager(mDataBinding.viewPager);
    }
}
