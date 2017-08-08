package com.rcpt.ui.home.fragment;


import android.os.Bundle;
import android.support.v4.content.ContextCompat;
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
import com.rcpt.adapter.BidListAdapter;
import com.rcpt.base.adapter.SimpleBindingAdapter;
import com.rcpt.base.ui.BaseFragment;
import com.rcpt.base.ui.LazyFragment;
import com.rcpt.bean.BidListBean;
import com.rcpt.databinding.FragmentBidListBinding;
import com.rcpt.mvp.contract.BidListContract;
import com.rcpt.mvp.presenter.BidListPresenter;
import com.rcpt.recycler_listener.OnRecyclerItemClickListener;

import java.util.List;

/**
 * 投标列表的Fragment
 */
public class BidListFragment extends LazyFragment<FragmentBidListBinding, BidListContract.View, BidListPresenter> implements BidListContract.View, SpringView.OnFreshListener {

    public static final String BID_TYPE_PROJECT = "project";
    public static final String BID_TYPE_PERSON = "person";

    private static final String BID_TYPE = "bid_type";
    private String bidType;

    private BidListAdapter mAdapter;
    private SimpleBindingAdapter mSimpleAdapter;
    private boolean isEnd;

    public static BidListFragment newInstance(String bidType) {
        BidListFragment fragment = new BidListFragment();
        Bundle bundle = new Bundle();
        bundle.putString(BID_TYPE, bidType);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            bidType = getArguments().getString(BID_TYPE);
        }
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_bid_list;
    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void loadData() {
        mPresenter.loadListData();
    }

    @Override
    protected BidListPresenter createPresenter() {
        return new BidListPresenter();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void initRecyclerView() {
        mDataBinding.springView.setHeader(new DefaultHeader(mContext));
        mDataBinding.springView.setFooter(new DefaultFooter(mContext));
        mDataBinding.springView.setListener(this);

        mDataBinding.recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mDataBinding.recyclerView.addOnItemTouchListener(new OnItemClickListener(getRecyclerView()));
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(mContext, DividerItemDecoration.VERTICAL);
        dividerItemDecoration.setDrawable(ContextCompat.getDrawable(mContext, R.drawable.divider_line_10dp));
        mDataBinding.recyclerView.addItemDecoration(dividerItemDecoration);
        mDataBinding.recyclerView.setRefrshView(mDataBinding.springView);
        mDataBinding.recyclerView.setEmptyView(mDataBinding.getRoot().findViewById(R.id.empty_view));
        if (BID_TYPE_PROJECT.equals(bidType)) {
            mAdapter = new BidListAdapter();
            mDataBinding.recyclerView.setAdapter(mAdapter);
        } else if (BID_TYPE_PERSON.equals(bidType)) {
            mSimpleAdapter = new SimpleBindingAdapter(R.layout.item_layout_bid_person_list, BR.bean);
            mDataBinding.recyclerView.setAdapter(mSimpleAdapter);
        }

    }

    @Override
    public void bindListData(List<BidListBean.ProjectlistBean> beanList) {
        setListData(beanList);
        mAdapter.setupData(beanList);
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
    public void updateIsEnd(boolean isEnd) {
        this.isEnd = isEnd;
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
    protected void loadListData(int page) {
        mPresenter.onLoadMore(page);
    }

    @Override
    public String getBidType() {
        return bidType;
    }

    @Override
    public void bindBidPersonData(List<BidListBean.personnelProlistBean> bidPersonData) {
        setListData(bidPersonData);
        mSimpleAdapter.setupData(bidPersonData);
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
