package com.rcpt.ui.home.test;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import com.rcpt.Constant;
import com.rcpt.R;
import com.rcpt.databinding.ActivityTestProfessionQuestionInfoBinding;
import com.rcpt.mvp.contract.TestProfessionQuestionInfoContract;
import com.rcpt.mvp.presenter.TestProfessionQuestionInfoPresenter;
import com.rcpt.mvp.view.TestQuestionInfoActivity;

/**
 * 职业测试的详情
 */
public class TestProfessionQuestionInfoActivity extends TestQuestionInfoActivity<ActivityTestProfessionQuestionInfoBinding, TestProfessionQuestionInfoContract.View, TestProfessionQuestionInfoPresenter>
        implements TestProfessionQuestionInfoContract.View {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    /**
     * 未实现的方法
     */
    @Override
    protected void setupTitle() {
        setTitleText("职业测试");
        openTitleLeftView(true);
    }

    /**
     * 获取测试的类型
     *
     * @return
     */
    @Override
    public String getTestType() {
        return Constant.TEST_TYPE_PROFESSION;
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
        return "";
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_test_profession_question_info;
    }

    @Override
    protected TestProfessionQuestionInfoPresenter createPresenter() {
        return new TestProfessionQuestionInfoPresenter();
    }
}
