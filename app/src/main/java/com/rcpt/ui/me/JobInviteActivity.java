package com.rcpt.ui.me;

import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.android.databinding.library.baseAdapters.BR;
import com.liaoinstan.springview.container.DefaultFooter;
import com.liaoinstan.springview.container.DefaultHeader;
import com.liaoinstan.springview.widget.SpringView;
import com.rcpt.R;
import com.rcpt.base.adapter.BindingViewHolder;
import com.rcpt.base.adapter.SimpleBindingAdapter;
import com.rcpt.base.ui.BaseActivity;
import com.rcpt.bean.JobInviteListBean;
import com.rcpt.databinding.ActivityJobInviteBinding;
import com.rcpt.mvp.contract.JobInviteContract;
import com.rcpt.mvp.presenter.JobInvitePresenter;

import java.util.List;

/**
 * 职位邀请的Activity
 */
public class JobInviteActivity extends BaseActivity<ActivityJobInviteBinding, JobInviteContract.View, JobInvitePresenter>
        implements JobInviteContract.View, SpringView.OnFreshListener {

    private SimpleBindingAdapter<JobInviteListBean.JobInviteBean> mAdapter;
    private int mClickItemPosition;
    private boolean isEnd;

    @Override
    protected void setupTitle() {
        setTitleText("职位邀请");
        openTitleLeftView(true);
    }

    @Override
    protected void initViews() {

        mPresenter.loadListData();
    }

    @Override
    public void initRecyclerView() {

        mDataBinding.springView.setHeader(new DefaultHeader(mContext));
        mDataBinding.springView.setFooter(new DefaultFooter(mContext));
        mDataBinding.springView.setListener(this);

        mDataBinding.recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mDataBinding.recyclerView.setRefrshView(mDataBinding.springView);
        mDataBinding.recyclerView.setEmptyView(findViewById(R.id.empty_view));
        mAdapter = new SimpleBindingAdapter<JobInviteListBean.JobInviteBean>(R.layout.item_layout_job_invite, BR.bean) {
            @Override
            protected void bindingViews(final BindingViewHolder<ViewDataBinding> holder, int position, JobInviteListBean.JobInviteBean jobInviteBean) {
                super.bindingViews(holder, position, jobInviteBean);
                holder.getBinding().getRoot().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mClickItemPosition = holder.getLayoutPosition();
                        mPresenter.onItemClick();
                    }
                });
            }
        };
        mDataBinding.recyclerView.setAdapter(mAdapter);
    }

    /**
     * 下拉刷新，回调接口
     */
    @Override
    public void onRefresh() {
        onRefresh(isEnd);
    }

    /**
     * 上拉加载，回调接口
     */
    @Override
    public void onLoadmore() {
        onLoadMore(isEnd);
    }

    /**
     * 获取点击题条目的Position
     *
     * @return
     */
    @Override
    public int getClickItemPosition() {
        return mClickItemPosition;
    }

    @Override
    public void bindListData(List<JobInviteListBean.JobInviteBean> beanList) {
        setListData(beanList);
        mAdapter.setupData(beanList);
    }

    @Override
    public void updateIsEnd(boolean isEnd) {
        this.isEnd = isEnd;
    }

    @Override
    protected void loadListData(int page) {
        mPresenter.onLoadMore(page);
    }

    @Override
    protected ViewGroup getRefreshView() {
        return mDataBinding.springView;
    }

    @Override
    public RecyclerView getRecyclerView() {
        return mDataBinding.recyclerView;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_job_invite;
    }

    @Override
    protected JobInvitePresenter createPresenter() {
        return new JobInvitePresenter();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

}
