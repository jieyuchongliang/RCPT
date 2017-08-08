package com.rcpt.ui.me;

import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.liaoinstan.springview.container.DefaultFooter;
import com.liaoinstan.springview.container.DefaultHeader;
import com.liaoinstan.springview.widget.SpringView;
import com.rcpt.BR;
import com.rcpt.R;
import com.rcpt.base.adapter.SimpleBindingAdapter;
import com.rcpt.base.ui.BaseActivity;
import com.rcpt.bean.TestRecordListBean;
import com.rcpt.databinding.ActivityTestRecordBinding;
import com.rcpt.mvp.contract.TestRecordContract;
import com.rcpt.mvp.presenter.TestRecordPresenter;

import java.util.List;

/**
 * 测试记录页面
 */
public class TestRecordActivity extends BaseActivity<ActivityTestRecordBinding, TestRecordContract.View, TestRecordPresenter>
        implements TestRecordContract.View, SpringView.OnFreshListener {

    private boolean isEnd;
    private SimpleBindingAdapter<TestRecordListBean.CompanyFavoritesBean> mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    /**
     * 未实现的方法
     */
    @Override
    protected void setupTitle() {
        setTitleText("测评管理");
        openTitleLeftView(true);
    }

    @Override
    protected void initViews() {
        mPresenter.loadListData();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_test_record;
    }

    @Override
    protected TestRecordPresenter createPresenter() {
        return new TestRecordPresenter();
    }


    @Override
    public void updateIsEnd(boolean isEnd) {
        this.isEnd = isEnd;
    }

    @Override
    public void initRecyclerView() {

        mDataBinding.springView.setHeader(new DefaultHeader(mContext));
        mDataBinding.springView.setFooter(new DefaultFooter(mContext));
        mDataBinding.springView.setListener(this);
        mDataBinding.recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mDataBinding.recyclerView.addItemDecoration(new DividerItemDecoration(mContext, DividerItemDecoration.VERTICAL));
        mAdapter = new SimpleBindingAdapter<>(R.layout.item_layout_test_record_list, BR.testBean);
        mDataBinding.recyclerView.setAdapter(mAdapter);
        mDataBinding.recyclerView.setRefrshView(mDataBinding.springView);
        mDataBinding.recyclerView.setEmptyView(findViewById(R.id.empty_view));
    }

    @Override
    public void bindListData(List<TestRecordListBean.CompanyFavoritesBean> beanList) {
        setListData(beanList);
        mAdapter.setupData(beanList);
    }

    @Override
    public void onRefresh() {
        onRefresh(isEnd);
    }

    @Override
    public void onLoadmore() {
        onLoadMore(isEnd);
    }

    @Override
    protected ViewGroup getRefreshView() {
        return mDataBinding.springView;
    }

    @Override
    protected void loadListData(int page) {
        mPresenter.onLoadMore(page);
    }

    @Override
    public RecyclerView getRecyclerView() {
        return mDataBinding.recyclerView;
    }
}
