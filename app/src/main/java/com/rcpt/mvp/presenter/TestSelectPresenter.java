package com.rcpt.mvp.presenter;

import com.rcpt.Constant;
import com.rcpt.base.mvp.BasePresenter;
import com.rcpt.mvp.contract.TestSelectContract;

/**
 * Created by lvzp on 2017/4/1.
 * 类描述：
 */

public class TestSelectPresenter extends BasePresenter<TestSelectContract.View> implements TestSelectContract.Presenter {

    /**
     * 进入道专业测试界面
     */
    @Override
    public void onClickGoMajorTest() {
        getView().actionStartGoTestHint(Constant.TEST_TYPE_MAJOR);
    }

    /**
     * 进入到能力测试界面
     */
    @Override
    public void onClickGoAbilityTest() {
        getView().actionStartGoTestHint(Constant.TEST_TYPE_ABILITY);
    }

    /**
     * 进入到职业测试
     */
    @Override
    public void onClickGoProfessionTest() {
        getView().actionStartGoTestHint(Constant.TEST_TYPE_PROFESSION);
    }
}
