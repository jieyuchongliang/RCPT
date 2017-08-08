package com.rcpt.ui.home.fragment;


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
import com.rcpt.base.ui.BaseFragment;
import com.rcpt.bean.PolicyListBean;
import com.rcpt.databinding.FragmentPolicyListBinding;
import com.rcpt.mvp.contract.PolicyListContract;
import com.rcpt.mvp.presenter.PolicyListPresenter;
import com.rcpt.recycler_listener.OnRecyclerItemClickListener;

import java.util.List;

/**
 * 政策法规的列表页面
 */
public class PolicyListFragment extends BaseFragment<FragmentPolicyListBinding, PolicyListContract.View, PolicyListPresenter>
        implements PolicyListContract.View, SpringView.OnFreshListener {


    private static final String POLICY_TAG = "tag";

    private String mPolicyId;

    private SimpleBindingAdapter<PolicyListBean.PolicylistBean> mAdapter;
    private boolean isEnd;

    public static PolicyListFragment newInstance(String policyId) {
        PolicyListFragment fragment = new PolicyListFragment();
        Bundle bundle = new Bundle();
        bundle.putString(POLICY_TAG, policyId);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mPolicyId = getArguments().getString(POLICY_TAG);
        }
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_policy_list;
    }

    @Override
    protected void initViews() {
        mPresenter.loadListData();
    }

    @Override
    protected PolicyListPresenter createPresenter() {
        return new PolicyListPresenter();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return super.onCreateView(inflater, container, savedInstanceState);
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

        mDataBinding.recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mDataBinding.recyclerView.addOnItemTouchListener(new OnItemClickListener(getRecyclerView()));
        mDataBinding.recyclerView.setRefrshView(mDataBinding.springView);
        mDataBinding.recyclerView.setEmptyView(mDataBinding.getRoot().findViewById(R.id.empty_view));
        mDataBinding.recyclerView.addItemDecoration(new DividerItemDecoration(mContext, DividerItemDecoration.VERTICAL));
        mAdapter = new SimpleBindingAdapter<>(R.layout.item_layout_home_policy_list, BR.policyLisBean);
        mDataBinding.recyclerView.setAdapter(mAdapter);
    }
/*
    @Override
    public void addItemClickListener(OnRecyclerItemClickListener listener) {
        mDataBinding.recyclerView.addOnItemTouchListener(listener);
    }
    @Override
    public void onItemClick(PolicyListBean.PolicylistBean bean, int position) {
        HomeInfoActivity.actionStart(mContext, bean.getPoliciesId(), HomeInfoContract.INFO_TYPE_POLICY);
    }
*/

    @Override
    public void bindListData(List<PolicyListBean.PolicylistBean> beanList) {
        setListData(beanList);
        mDataBinding.springView.onFinishFreshAndLoad();
        mAdapter.setupData(beanList);
    }


    @Override
    public String getPolicyId() {
        return mPolicyId;
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
