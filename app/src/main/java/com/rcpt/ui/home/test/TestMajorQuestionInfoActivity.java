package com.rcpt.ui.home.test;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import com.rcpt.Constant;
import com.rcpt.R;
import com.rcpt.databinding.ActivityTestMajorQuestionInfoBinding;
import com.rcpt.mvp.contract.TestMajorQuestionInfoContract;
import com.rcpt.mvp.presenter.TestMajorQuestionInfoPresenter;
import com.rcpt.mvp.view.TestQuestionInfoActivity;
import com.rcpt.utils.CountDownTimerUtils;
import com.rcpt.utils.DialogUtils;

/**
 * 专业知识测试的详情
 */
public class TestMajorQuestionInfoActivity extends TestQuestionInfoActivity<ActivityTestMajorQuestionInfoBinding, TestMajorQuestionInfoContract.View, TestMajorQuestionInfoPresenter>
        implements TestMajorQuestionInfoContract.View {

    private CountDownTimerUtils mDownTimerUtils;

    public static void actionStart(Context context, String testId) {
        Intent intent = new Intent(context, TestMajorQuestionInfoActivity.class);
        intent.putExtra("test_id", testId);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    /**
     * 未实现的方法
     */
    @Override
    protected void setupTitle() {
        setTitleText("专业知识测试");
        openTitleLeftView(true);
    }

    /**
     * 获取测试的类型
     *
     * @return
     */
    @Override
    public String getTestType() {
        return Constant.TEST_TYPE_MAJOR;
    }


    @Override
    protected int getLayoutId() {
        return R.layout.activity_test_major_question_info;
    }

    @Override
    protected TestMajorQuestionInfoPresenter createPresenter() {
        return new TestMajorQuestionInfoPresenter();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mDownTimerUtils != null)
            mDownTimerUtils.cancel();
    }

    /**
     * 获取试卷的id
     *
     * @return
     */
    @Override
    public String getTestId() {
        return getIntent().getStringExtra("test_id");
    }

    @Override
    public RecyclerView getRecyclerView() {
        return mDataBinding.recyclerView;
    }


    @Override
    public void bindTestName(String testName) {
        mDataBinding.setTestName(testName);
    }

    @Override
    public void setupTestUsedTime(int time) {
        mDownTimerUtils = new CountDownTimerUtils(time * 60 * 1000);
        mDownTimerUtils.setTimeFormat("HH时mm分ss秒");
        mDownTimerUtils.bindTextView(mDataBinding.tvDownTime);
        mDownTimerUtils.start();
        mDownTimerUtils.setOnCountDownFinishListener(new CountDownTimerUtils.OnCountDownFinishListener() {
            @Override
            public void onFinish() {
                DialogUtils.builderAlertDialog(mContext, "温馨提示", "考试时间已结束，您是否立即交卷")
                        .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                                closeActivity();
                            }
                        })
                        .setPositiveButton("交卷", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                                mPresenter.onClickHandExams();
                            }
                        }).show();
            }
        });
    }

}
