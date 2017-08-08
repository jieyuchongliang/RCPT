package com.rcpt.ui.home.test;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.rcpt.Constant;
import com.rcpt.R;
import com.rcpt.base.ui.BaseActivity;
import com.rcpt.bean.TestHintBean;
import com.rcpt.databinding.ActivityTestHintBinding;
import com.rcpt.mvp.contract.TestHintContract;
import com.rcpt.mvp.presenter.TestHintPresenter;

/**
 * 测试的提示信息
 */
public class TestHintActivity extends BaseActivity<ActivityTestHintBinding, TestHintContract.View, TestHintPresenter>
        implements TestHintContract.View {

    private String mTestType;

    public static void actionStart(Context context, String type) {
        Intent intent = new Intent(context, TestHintActivity.class);
        intent.putExtra("type", type);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initFieldBeforeMethods() {
        mTestType = getIntent().getStringExtra("type");
    }

    /**
     * 未实现的方法
     */
    @Override
    protected void setupTitle() {
        String title = "";
        switch (mTestType) {
            case Constant.TEST_TYPE_MAJOR:
                title = "专业知识测试";
                break;
            case Constant.TEST_TYPE_ABILITY:
                title = "能力测试体系";
                break;
            case Constant.TEST_TYPE_PROFESSION:
                title = "职业倾向测试";
                break;
        }
        setTitleText(title);
        openTitleLeftView(true);
    }

    @Override
    protected void initViews() {
        mPresenter.initPageData();
        mDataBinding.setPresenter(mPresenter);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_test_hint;
    }

    @Override
    protected TestHintPresenter createPresenter() {
        return new TestHintPresenter();
    }

    /**
     * 获取测试的类型
     *
     * @return
     */
    @Override
    public String getTestType() {
        return mTestType;
    }

    /**
     * 绑定页面的数据
     *
     * @param bean
     */
    @Override
    public void bindPageData(TestHintBean bean) {
        mDataBinding.setHintBean(bean);
        mDataBinding.ivShow.setImageResource(bean.getShowRes());
    }
}
