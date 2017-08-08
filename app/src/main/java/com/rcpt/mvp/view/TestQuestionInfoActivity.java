package com.rcpt.mvp.view;

import android.app.Dialog;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PagerSnapHelper;
import android.support.v7.widget.SnapHelper;
import android.view.LayoutInflater;
import android.view.View;

import com.android.databinding.library.baseAdapters.BR;
import com.rcpt.R;
import com.rcpt.adapter.TestMajorQuestionAdapter;
import com.rcpt.base.ui.BaseActivity;
import com.rcpt.bean.TestAnswerBean;
import com.rcpt.bean.TestQuestionInfoBean;
import com.rcpt.databinding.LayoutDialogTestResultBinding;
import com.rcpt.mvp.contract.TestQuestionInfoContract;
import com.rcpt.mvp.presenter.TestQuestionInfoPresenter;
import com.rcpt.utils.DialogUtils;

import java.util.List;

/**
 * Created by lvzp on 2017/4/7.
 * 类描述：
 */

public abstract class TestQuestionInfoActivity<D extends ViewDataBinding, V extends TestQuestionInfoContract.View, P extends TestQuestionInfoPresenter<V>> extends BaseActivity<D, V, P> implements TestQuestionInfoContract.View {

    private LinearLayoutManager mLayoutManager;
    private TestMajorQuestionAdapter mAdapter;

    @Override
    public void initRecyclerView() {
        mLayoutManager = new LinearLayoutManager(mContext);
        mLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        getRecyclerView().setLayoutManager(mLayoutManager);

        mAdapter = new TestMajorQuestionAdapter(getTestType());

        SnapHelper snapHelper = new PagerSnapHelper();
        snapHelper.attachToRecyclerView(getRecyclerView());

        getRecyclerView().setAdapter(mAdapter);
    }

    @Override
    protected void initViews() {
        mPresenter.loadListData();
        mDataBinding.setVariable(BR.presenter, mPresenter);
    }

    @Override
    public void bindListData(List<TestQuestionInfoBean.ContentBean> beanList) {
        mAdapter.setupData(beanList);
    }

    /**
     * 获取当前显示的View的Position
     *
     * @return
     */
    @Override
    public int getCurrentVisiblePosition() {
        return mLayoutManager.findFirstVisibleItemPosition();
    }

    /**
     * 获取题目的总数量
     *
     * @return
     */
    @Override
    public int getQuestionCount() {
        return mAdapter.getItemCount();
    }

    /**
     * 将题目滚动到相应的位置
     *
     * @param position
     */
    @Override
    public void scrollViewToPosition(int position) {
        getRecyclerView().smoothScrollToPosition(position);
    }

    /**
     * 显示Sanckbar提示框
     *
     * @param text
     */
    @Override
    public void showSnackbar(String text) {
        Snackbar.make(mDataBinding.getRoot(), text, Snackbar.LENGTH_SHORT).show();
    }

    /**
     * 显示最后得分的提示框
     * @param bean
     */
    public void showDialog(TestAnswerBean bean) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.layout_dialog_test_result, null);
        LayoutDialogTestResultBinding bind = DataBindingUtil.bind(view);
        bind.setBean(bean);
        bind.btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                closeActivity();
            }
        });
        Dialog dialog = DialogUtils.builderEmptyDialog(view);
        dialog.show();
    }
}
