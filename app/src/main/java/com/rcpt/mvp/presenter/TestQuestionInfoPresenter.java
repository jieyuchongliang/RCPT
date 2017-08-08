package com.rcpt.mvp.presenter;

import android.content.Context;

import com.google.gson.Gson;
import com.rcpt.Constant;
import com.rcpt.LoginHelper;
import com.rcpt.base.mvp.BasePresenter;
import com.rcpt.base.mvp.OnDataGetCallback;
import com.rcpt.bean.AnswerBean;
import com.rcpt.bean.TestAnswerBean;
import com.rcpt.bean.TestQuestionInfoBean;
import com.rcpt.mvp.contract.TestQuestionInfoContract;
import com.rcpt.mvp.module.TestQuestionInfoModule;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by lvzp on 2017/4/7.
 * 类描述：
 */

public abstract class TestQuestionInfoPresenter<V extends TestQuestionInfoContract.View> extends BasePresenter<V> implements TestQuestionInfoContract.Presenter {

    private TestQuestionInfoModule mModule;

    @Override
    public void attach(V view) {
        super.attach(view);
        mModule = new TestQuestionInfoModule();
        getView().initRecyclerView();
    }

    @Override
    public void loadListData() {
        String testType = getView().getTestType();
        Context context = getView().getContext();
        String userToken = LoginHelper.getInstance().getUserToken();
        switch (testType) {
            case Constant.TEST_TYPE_MAJOR:
                mModule.requestTestMajorQuestionInfo(context, userToken, getView().getTestId(), new OnDataGetCallback<TestQuestionInfoBean>() {
                    @Override
                    public void onSuccessResult(TestQuestionInfoBean data) {
                        loadPageDataSuccess(data);
                        getView().bindListData(mModule.getTestQuestionList());
                    }
                });
                break;
            case Constant.TEST_TYPE_PROFESSION:
                mModule.requestTestQuestionInfo(context, userToken, Constant.TEST_QUESTION_TYPE_PROFESSION, new OnDataGetCallback<TestQuestionInfoBean>() {
                    @Override
                    public void onSuccessResult(TestQuestionInfoBean data) {
                        getView().bindListData(mModule.getTestQuestionList());
                    }
                });
                break;
            case Constant.TEST_TYPE_ABILITY:
                mModule.requestTestQuestionInfo(context, userToken, Constant.TEST_QUESTION_TYPE_ABILITY, new OnDataGetCallback<TestQuestionInfoBean>() {
                    @Override
                    public void onSuccessResult(TestQuestionInfoBean data) {
                        getView().bindListData(mModule.getTestQuestionList());
                    }
                });
                break;
        }
    }


    @Override
    public void onClickGoLast() {
        scrollToPosition(false);
    }

    @Override
    public void onClickGoNext() {
        scrollToPosition(true);
    }

    private void scrollToPosition(boolean isNext) {
        int currentPosition = getView().getCurrentVisiblePosition();
        if (isNext) {
            if (currentPosition >= getView().getQuestionCount() - 1) {
                getView().showSnackbar("已到达最后一题");
                return;
            }
            currentPosition++;
        } else {
            if (currentPosition == 0) {
                getView().showSnackbar("已到达第一题");
                return;
            }
            currentPosition--;
        }
        getView().scrollViewToPosition(currentPosition);
    }

    /**
     * 交卷的点击事件
     */
    @Override
    public void onClickHandExams() {
        Map<String, AnswerBean> answerMap = new TreeMap<>();
        Gson gson = new Gson();
        List<TestQuestionInfoBean.ContentBean> testQuestionList = mModule.getTestQuestionList();
        for (TestQuestionInfoBean.ContentBean contentBean : testQuestionList) {
            AnswerBean bean = new AnswerBean();
            String answer = "";
            for (TestQuestionInfoBean.ContentBean.AppChoiceListBean choiceListBean : contentBean.getAppChoiceList()) {
                if (choiceListBean.isSelect()) {
                    answer = choiceListBean.getKey();
                }
            }
            bean.setAnswer(answer);
            bean.setQuestion_type_id(contentBean.getQuestionTypeId());
            bean.setPoint(contentBean.getQuestionPoint());
            answerMap.put(contentBean.getQuestionId(), bean);
        }
        String as = gson.toJson(answerMap);
        String type = getView().getTestType();
        String userId = LoginHelper.getInstance().getUserToken();
        String testId = mModule.getTestId();
        switch (type) {
            case Constant.TEST_TYPE_ABILITY:
                mModule.uploadTestAbilityAnswer(getView().getContext(), userId, testId, as, new OnDataGetCallback<TestAnswerBean>() {
                    @Override
                    public void onSuccessResult(TestAnswerBean data) {
                        getView().showDialog(data);
                    }
                });
                break;
            case Constant.TEST_TYPE_MAJOR:
                mModule.uploadTestMajorAnswer(getView().getContext(), userId, testId, as, new OnDataGetCallback<TestAnswerBean>() {
                    @Override
                    public void onSuccessResult(TestAnswerBean data) {
                        getView().showDialog(data);
                    }
                });
                break;
            case Constant.TEST_TYPE_PROFESSION:
                mModule.uploadTestOccupation(getView().getContext(), userId, testId, as, new OnDataGetCallback<TestAnswerBean>() {
                    @Override
                    public void onSuccessResult(TestAnswerBean data) {
                        getView().showDialog(data);
                    }
                });
                break;
        }

    }


    public abstract void loadPageDataSuccess(TestQuestionInfoBean bean);
}
