package com.rcpt.ui.home.test;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import com.rcpt.Constant;
import com.rcpt.R;
import com.rcpt.databinding.ActivityTestAbilityQuestionInfoBinding;
import com.rcpt.mvp.contract.TestAbilityQuestionInfoContract;
import com.rcpt.mvp.presenter.TestAbilityQuestionInfoPresenter;
import com.rcpt.mvp.view.TestQuestionInfoActivity;

/**
 * 能力测试的Activity
 */
public class TestAbilityQuestionInfoActivity extends TestQuestionInfoActivity<ActivityTestAbilityQuestionInfoBinding, TestAbilityQuestionInfoContract.View, TestAbilityQuestionInfoPresenter>
        implements TestAbilityQuestionInfoContract.View {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public RecyclerView getRecyclerView() {
        return mDataBinding.recyclerView;
    }

    /**
     * 获取试卷的id
     *
     * @return
     */
    @Override
    public String getTestId() {
        return null;
    }

    /**
     * 获取测试的类型
     *
     * @return
     */
    @Override
    public String getTestType() {
        return Constant.TEST_TYPE_ABILITY;
    }

    /**
     * 未实现的方法
     */
    @Override
    protected void setupTitle() {
        setTitleText("能力测试");
        openTitleLeftView(true);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_test_ability_question_info;
    }

    @Override
    protected TestAbilityQuestionInfoPresenter createPresenter() {
        return new TestAbilityQuestionInfoPresenter();
    }
}
