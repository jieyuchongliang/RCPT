package com.rcpt.ui.recruit.fragment;


import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.databinding.library.baseAdapters.BR;
import com.liaoinstan.springview.container.DefaultFooter;
import com.liaoinstan.springview.container.DefaultHeader;
import com.liaoinstan.springview.widget.SpringView;
import com.rcpt.R;
import com.rcpt.base.adapter.SimpleBindingAdapter;
import com.rcpt.base.ui.LazyFragment;
import com.rcpt.bean.RecruitFragmentListBean;
import com.rcpt.databinding.FragmentJobGuideBinding;
import com.rcpt.mvp.contract.JobGuideFragmentContract;
import com.rcpt.mvp.presenter.JobGuideFragmentPresenter;
import com.rcpt.recycler_listener.OnRecyclerItemClickListener;

import java.util.List;

/**
 * 求职指南列表，根据类型不同显示不同的列表
 */
public class JobGuideFragment extends LazyFragment<FragmentJobGuideBinding, JobGuideFragmentContract.View, JobGuideFragmentPresenter>
        implements JobGuideFragmentContract.View, SpringView.OnFreshListener {

    private static final String ARG_PARAM_JOB_GUIDE_ID = "id";
    private boolean isEnd;
    private SimpleBindingAdapter<RecruitFragmentListBean.SubBean> mAdapter;

    public static JobGuideFragment newInstance(String jobGuideId) {
        JobGuideFragment fragment = new JobGuideFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM_JOB_GUIDE_ID, jobGuideId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public String getJobGuideTypeId() {
        if (getArguments() != null) {
            return getArguments().getString(ARG_PARAM_JOB_GUIDE_ID);
        }
        return null;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    protected void loadData() {
        mPresenter.loadListData();
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_job_guide;
    }

    @Override
    protected void initViews() {

    }

    @Override
    protected JobGuideFragmentPresenter createPresenter() {
        return new JobGuideFragmentPresenter();
    }

    @Override
    public void updateIsEnd(boolean isEnd) {
        this.isEnd = isEnd;
    }

    @Override
    public void initRecyclerView() {

        mDataBinding.springView.setListener(this);
        mDataBinding.springView.setHeader(new DefaultHeader(mContext));
        mDataBinding.springView.setFooter(new DefaultFooter(mContext));
        mDataBinding.recyclerView.addItemDecoration(new DividerItemDecoration(mContext, DividerItemDecoration.VERTICAL));
        mDataBinding.recyclerView.addOnItemTouchListener(new OnItemClickListener(getRecyclerView()));
        mDataBinding.recyclerView.setRefrshView(mDataBinding.springView);
        mDataBinding.recyclerView.setEmptyView(mDataBinding.getRoot().findViewById(R.id.empty_view));
        mDataBinding.recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mAdapter = new SimpleBindingAdapter<>(R.layout.item_layout_job_guide_list, BR.subBean);
        mDataBinding.recyclerView.setAdapter(mAdapter);
    }

    @Override
    public void bindListData(List<RecruitFragmentListBean.SubBean> beanList) {
        setListData(beanList);
        mAdapter.setupData(beanList);
    }

    @Override
    public RecyclerView getRecyclerView() {
        return mDataBinding.recyclerView;
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

    @Override
    protected ViewGroup getRefreshView() {
        return mDataBinding.springView;
    }

    @Override
    protected void loadListData(int page) {
        mPresenter.onLoadMore(page);
    }

    private class OnItemClickListener extends OnRecyclerItemClickListener {

        public OnItemClickListener(RecyclerView recyclerView) {
            super(recyclerView);
        }

        @Override
        public void onItemClick(RecyclerView.ViewHolder vh) {
            mPresenter.onItemClick(vh, vh.getLayoutPosition());
        }
    }

}
