package com.rcpt.ui.home.test;

import android.os.Bundle;

import com.rcpt.R;
import com.rcpt.base.ui.BaseActivity;
import com.rcpt.databinding.ActivityTestSelectBinding;
import com.rcpt.mvp.contract.TestSelectContract;
import com.rcpt.mvp.presenter.TestSelectPresenter;

/**
 * 测评类型选择
 */
public class TestSelectActivity extends BaseActivity<ActivityTestSelectBinding, TestSelectContract.View, TestSelectPresenter>
        implements TestSelectContract.View {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    /**
     * 未实现的方法
     */
    @Override
    protected void setupTitle() {
        setTitleText("测评");
        openTitleLeftView(true);
    }

    @Override
    protected void initViews() {
        mDataBinding.setPresenter(mPresenter);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_test_select;
    }

    @Override
    protected TestSelectPresenter createPresenter() {
        return new TestSelectPresenter();
    }

    @Override
    public void actionStartGoTestHint(String testType) {
        TestHintActivity.actionStart(mContext, testType);
    }
}
