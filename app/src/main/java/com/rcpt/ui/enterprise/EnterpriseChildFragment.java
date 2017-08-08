package com.rcpt.ui.enterprise;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.liaoinstan.springview.container.DefaultFooter;
import com.liaoinstan.springview.container.DefaultHeader;
import com.liaoinstan.springview.widget.SpringView;
import com.rcpt.R;
import com.rcpt.adapter.EnterpriseListAdapter;
import com.rcpt.base.ui.LazyFragment;
import com.rcpt.bean.EnterpriseListBean;
import com.rcpt.databinding.FragmentEnterpriseChildBinding;
import com.rcpt.mvp.contract.EnterpriseChildContract;
import com.rcpt.mvp.presenter.EnterpriseChildPresenter;
import com.rcpt.observer_design.Observer;
import com.rcpt.recycler_listener.OnRecyclerItemClickListener;

import java.util.ArrayList;
import java.util.List;

/**
 * 企业列表的子页面
 */
public class EnterpriseChildFragment extends LazyFragment<FragmentEnterpriseChildBinding, EnterpriseChildContract.View, EnterpriseChildPresenter>
        implements EnterpriseChildContract.View, SpringView.OnFreshListener, Observer {
    private OnItemClickListener mOnItemClickListener;
    private EnterpriseListAdapter mAdapter;
    private String mItemId;
    private boolean isEnd;
    private boolean isLoadingSuccess;

    private static String mKeyWork;

    private boolean isResetting;//企业列表中点击重置按钮的标记，true表示点击了重置。false代表没有点击重置按钮。
    private List<String> category = new ArrayList<>();//页面更新的标记。如果某个界面被更新了，就加入到集合中，下次再进入此界面时无需重新加载数据。
    public static EnterpriseChildFragment newInstance(String category) {
        EnterpriseChildFragment fragment = new EnterpriseChildFragment();
        Bundle bundle = new Bundle();
        bundle.putString("category", category);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        if (!hidden && !isLoadingSuccess) {
            mPresenter.loadListData();
        }
    }

    /**
     * fragment可见与不可见调用的方法。
     * @param isVisibleToUser true为可见。
     */

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            if (isResetting){//用户点击了重置
                onRefresh();
                isResetting = false;//恢复初始值
            }
            if (!category.contains(mItemId) && mKeyWork != null && !TextUtils.isEmpty(mKeyWork)) {
                onRefresh();
                if (mItemId != null) {
                    category.add(mItemId);
                }
            }
        }
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_enterprise_child;
    }

    @Override
    protected void loadData() {
        mPresenter.loadListData();
    }

    @Override
    protected void initViews() {
        mOnItemClickListener = new OnItemClickListener(getRecyclerView());
        mDataBinding.recyclerView.addOnItemTouchListener(mOnItemClickListener);
    }

    @Override
    public String getEnterpriseId() {
        return mItemId;
    }

    @Override
    public String getEnterpriseKeyWord() {
        return mKeyWork;
    }


    @Override
    protected EnterpriseChildPresenter createPresenter() {
        return new EnterpriseChildPresenter();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mItemId = getArguments().getString("category");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return super.onCreateView(inflater, container, savedInstanceState);
    }


    @Override
    public void bindListData(List<EnterpriseListBean.BusinesslistBean> beanList) {
        isLoadingSuccess = true;
        setListData(beanList);
        mAdapter.setupData(beanList);
    }



    @Override
    public void updateIsEnd(boolean isEnd) {
        this.isEnd = isEnd;
    }

    @Override
    public RecyclerView getRecyclerView() {
        return mDataBinding.recyclerView;
    }

    @Override
    public void initRecyclerView() {

        mDataBinding.springView.setListener(this);
        mDataBinding.springView.setHeader(new DefaultHeader(mContext));
        mDataBinding.springView.setFooter(new DefaultFooter(mContext));

        mDataBinding.recyclerView.setLayoutManager(new GridLayoutManager(mContext, 2));
        mAdapter = new EnterpriseListAdapter(R.layout.item_layout_enterprise_list);
        mDataBinding.recyclerView.setRefrshView(mDataBinding.springView);
        mDataBinding.recyclerView.setEmptyView(mDataBinding.getRoot().findViewById(R.id.empty_view));
        mDataBinding.recyclerView.setAdapter(mAdapter);
    }

    @Override
    protected void loadListData(int page) {
        isLoadingSuccess = false;
        mPresenter.loadListData(page);
    }

    @Override
    protected ViewGroup getRefreshView() {
        return mDataBinding.springView;
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
     * 观察者的回调方法（此处被观察者是EnterpriseFragment）
     *
     * @param message
     */

    @Override
    public void update(String message,boolean isResetting) {
        mKeyWork = message;
        this.isResetting = isResetting;
        category.clear();//先清空集合中的标记
    }


    private class OnItemClickListener extends OnRecyclerItemClickListener {

        OnItemClickListener(RecyclerView recyclerView) {
            super(recyclerView);
        }

        @Override
        public void onItemClick(RecyclerView.ViewHolder vh) {
            mPresenter.onItemClick(vh, vh.getLayoutPosition());
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mKeyWork = null;
        isResetting=false;
    }
}
