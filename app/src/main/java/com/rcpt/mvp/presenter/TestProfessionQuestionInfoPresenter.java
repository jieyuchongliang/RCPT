package com.rcpt.mvp.presenter;

import com.rcpt.bean.TestQuestionInfoBean;
import com.rcpt.mvp.contract.TestProfessionQuestionInfoContract;

/**
 * Created by lvzp on 2017/4/7.
 * 类描述：
 */

public class TestProfessionQuestionInfoPresenter extends TestQuestionInfoPresenter<TestProfessionQuestionInfoContract.View> implements TestProfessionQuestionInfoContract.Presenter {

    @Override
    public void loadPageDataSuccess(TestQuestionInfoBean bean) {

    }
}
