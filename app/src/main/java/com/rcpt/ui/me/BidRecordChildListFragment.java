package com.rcpt.ui.me;


import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.liaoinstan.springview.container.DefaultFooter;
import com.liaoinstan.springview.container.DefaultHeader;
import com.liaoinstan.springview.widget.SpringView;
import com.rcpt.BR;
import com.rcpt.R;
import com.rcpt.base.adapter.SimpleBindingAdapter;
import com.rcpt.base.ui.LazyFragment;
import com.rcpt.bean.BidRecordListBean;
import com.rcpt.databinding.FragmentBidRecordChildListBinding;
import com.rcpt.mvp.contract.BidRecordChildListContract;
import com.rcpt.mvp.presenter.BidRecordChildListPresenter;

import java.util.List;

/**
 * 投标记录列表界面
 */
public class BidRecordChildListFragment extends LazyFragment<FragmentBidRecordChildListBinding, BidRecordChildListContract.View, BidRecordChildListPresenter>
        implements BidRecordChildListContract.View, SpringView.OnFreshListener {

    public static final String ALL = "";
    public static final String BID_GO_ON = "0";
    public static final String BID_WIN = "1";
    public static final String BID_LOSE = "2";

    private static final String BID_STATUS = "bid_status";
    private static final String BID_TYPE = "bid_type";

    private String mBidStatus;
    private String mBidType;

    public static BidRecordChildListFragment newInstance(String bidType, String bidStatus) {
        BidRecordChildListFragment fragment = new BidRecordChildListFragment();
        Bundle args = new Bundle();
        args.putString(BID_STATUS, bidStatus);
        args.putString(BID_TYPE, bidType);
        fragment.setArguments(args);
        return fragment;
    }

    private boolean isEnd;
    private SimpleBindingAdapter<BidRecordListBean.ProjectProlistBean> mAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mBidStatus = getArguments().getString(BID_STATUS);
            mBidType = getArguments().getString(BID_TYPE);

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_bid_record_child_list;
    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void loadData() {
        mPresenter.loadListData();
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
    public void updateIsEnd(boolean isEnd) {
        this.isEnd = isEnd;
    }

    @Override
    public void initRecyclerView() {

        mDataBinding.springView.setHeader(new DefaultHeader(mContext));
        mDataBinding.springView.setFooter(new DefaultFooter(mContext));
        mDataBinding.springView.setListener(this);

        mAdapter = new SimpleBindingAdapter<>(R.layout.item_layout_bid_record_child_list, BR.bidListBean);
        mDataBinding.recyclerView.setAdapter(mAdapter);
        mDataBinding.recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mDataBinding.recyclerView.addItemDecoration(new DividerItemDecoration(mContext, DividerItemDecoration.VERTICAL));
        mDataBinding.recyclerView.setRefrshView(mDataBinding.springView);
        mDataBinding.recyclerView.setEmptyView(mDataBinding.getRoot().findViewById(R.id.empty_view));

    }

    @Override
    public void bindListData(List<BidRecordListBean.ProjectProlistBean> beanList) {
        setListData(beanList);
        mAdapter.setupData(beanList);
    }

    @Override
    public RecyclerView getRecyclerView() {
        return mDataBinding.recyclerView;
    }

    @Override
    protected ViewGroup getRefreshView() {
        return mDataBinding.springView;
    }

    @Override
    protected void loadListData(int page) {
        mPresenter.onLoadMore(page);
    }

    /**
     * 获取投标的类型
     *
     * @return
     */
    @Override
    public String getBidType() {
        return mBidType;
    }

    /**
     * 获取投标的状态
     *
     * @return
     */
    @Override
    public String getBidStatus() {
        return mBidStatus;
    }

    @Override
    protected BidRecordChildListPresenter createPresenter() {
        return new BidRecordChildListPresenter();
    }
}
