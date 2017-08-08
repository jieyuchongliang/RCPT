package com.rcpt.mvp.presenter;

import com.rcpt.Constant;
import com.rcpt.LoginHelper;
import com.rcpt.R;
import com.rcpt.base.mvp.BasePresenter;
import com.rcpt.mvp.contract.TestHintContract;
import com.rcpt.mvp.module.TestHintModule;
import com.rcpt.ui.home.test.TestAbilityQuestionInfoActivity;
import com.rcpt.ui.home.test.TestMajorListActivity;
import com.rcpt.ui.home.test.TestProfessionQuestionInfoActivity;
import com.rcpt.ui.login.LoginActivity;

/**
 * Created by lvzp on 2017/4/1.
 * 类描述：
 */

public class TestHintPresenter extends BasePresenter<TestHintContract.View> implements TestHintContract.Presenter {

    private TestHintModule mModule;

    @Override
    public void attach(TestHintContract.View view) {
        super.attach(view);
        mModule = new TestHintModule();
    }

    /**
     * 初始化页面数据
     */
    @Override
    public void initPageData() {
        getView().bindPageData(mModule.getPageData(getView().getContext(), getView().getTestType()));
    }

    /**
     * 开始测试的点击事件
     */
    @Override
    public void onClickStartTest() {
        String testType = getView().getTestType();
        switch (testType) {
            case Constant.TEST_TYPE_MAJOR://专业知识测评
                getView().actionStartActivity(TestMajorListActivity.class);
                break;
            case Constant.TEST_TYPE_PROFESSION://职业测评
                if (!LoginHelper.getInstance().isOnline()) {
                    getView().actionStartActivity(LoginActivity.class);
                    getView().showToast(getView().getContext().getString(R.string.logo_message));
                    return;
                }
                getView().actionStartActivity(TestProfessionQuestionInfoActivity.class);
                break;
            case Constant.TEST_TYPE_ABILITY://能力测评
                if (!LoginHelper.getInstance().isOnline()) {
                    getView().actionStartActivity(LoginActivity.class);
                    getView().showToast(getView().getContext().getString(R.string.logo_message));
                    return;
                }
                getView().actionStartActivity(TestAbilityQuestionInfoActivity.class);
                break;
        }

    }
}
