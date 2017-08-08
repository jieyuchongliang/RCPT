package com.rcpt.mvp.presenter;

import com.rcpt.bean.TestQuestionInfoBean;
import com.rcpt.mvp.contract.TestMajorQuestionInfoContract;

import static android.R.attr.data;

/**
 * Created by lvzp on 2017/4/6.
 * 类描述：
 */

public class TestMajorQuestionInfoPresenter extends TestQuestionInfoPresenter<TestMajorQuestionInfoContract.View> implements TestMajorQuestionInfoContract.Presenter {

    @Override
    public void loadPageDataSuccess(TestQuestionInfoBean bean) {
        getView().bindTestName(bean.getName());
        getView().setupTestUsedTime(bean.getDuration());
    }
}
