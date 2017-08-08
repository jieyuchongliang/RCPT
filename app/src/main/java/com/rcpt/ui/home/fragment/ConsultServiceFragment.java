package com.rcpt.ui.home.fragment;


import android.os.Bundle;
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
import com.rcpt.bean.ConsultServiceListBean;
import com.rcpt.databinding.FragmentConsultServiceBinding;
import com.rcpt.mvp.contract.ConsultServiceFragmentContract;
import com.rcpt.mvp.presenter.ConsultServiceFragmentPresenter;
import com.rcpt.recycler_listener.OnRecyclerItemClickListener;

import java.util.List;

/**
 * 咨询服务列表界面
 */
public class ConsultServiceFragment extends BaseFragment<FragmentConsultServiceBinding, ConsultServiceFragmentContract.View, ConsultServiceFragmentPresenter>
        implements ConsultServiceFragmentContract.View, SpringView.OnFreshListener {

    private static final String CONSULT_SERVICE_TAG = "consult_service_tag";
    private SimpleBindingAdapter<ConsultServiceListBean.ConsultancyListBean> mAdapter;


    private String mServiceId;
    private boolean isEnd;

    public static ConsultServiceFragment newInstance(String id) {
        ConsultServiceFragment fragment = new ConsultServiceFragment();
        Bundle args = new Bundle();
        args.putString(CONSULT_SERVICE_TAG, id);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mServiceId = getArguments().getString(CONSULT_SERVICE_TAG);
        }
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_consult_service;
    }

    @Override
    protected void initViews() {
        mPresenter.loadListData();
    }

    @Override
    protected ConsultServiceFragmentPresenter createPresenter() {
        return new ConsultServiceFragmentPresenter();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return super.onCreateView(inflater, container, savedInstanceState);
    }


    /**
     * 获取咨询服务类别的id
     *
     * @return
     */
    @Override
    public String getServiceId() {
        return mServiceId;
    }

    @Override
    public void initRecyclerView() {
        mDataBinding.springView.setListener(this);
        mDataBinding.springView.setHeader(new DefaultHeader(mContext));
        mDataBinding.springView.setFooter(new DefaultFooter(mContext));
        mDataBinding.recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mDataBinding.recyclerView.addOnItemTouchListener(new OnItemClcikListener(getRecyclerView()));
        mDataBinding.recyclerView.setRefrshView(mDataBinding.springView);
        mDataBinding.recyclerView.setEmptyView(mDataBinding.getRoot().findViewById(R.id.empty_view));
        // mDataBinding.recyclerView.addItemDecoration(new DividerItemDecoration(mContext, DividerItemDecoration.VERTICAL));
        mAdapter = new SimpleBindingAdapter<>(R.layout.item_layout_home_consult_service_list, BR.bean);
        mDataBinding.recyclerView.setAdapter(mAdapter);
    }

    @Override
    protected void loadListData(int page) {
        mPresenter.onLoadMore(page);
    }

    @Override
    public RecyclerView getRecyclerView() {
        return mDataBinding.recyclerView;
    }

/*    @Override
    public void addItemClickListener(OnRecyclerItemClickListener listener) {
        mDataBinding.recyclerView.addOnItemTouchListener(listener);
    }

    @Override
    public void onItemClick(ConsultServiceListBean.ConsultancyListBean bean, int position) {
        HomeInfoActivity.actionStart(mContext, bean.getId(), HomeInfoContract.INFO_TYPE_CONSULT_SERVICE);
    }*/

    @Override
    public void bindListData(List<ConsultServiceListBean.ConsultancyListBean> beanList) {
        setListData(beanList);
        mDataBinding.springView.onFinishFreshAndLoad();
        mAdapter.setupData(beanList);
    }

    @Override
    protected ViewGroup getRefreshView() {
        return mDataBinding.springView;
    }

    @Override
    public void updateIsEnd(boolean isEnd) {
        this.isEnd = isEnd;
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

    private class OnItemClcikListener extends OnRecyclerItemClickListener {

        public OnItemClcikListener(RecyclerView recyclerView) {
            super(recyclerView);
        }

        @Override
        public void onItemClick(RecyclerView.ViewHolder vh) {
            mPresenter.onItemClick(vh, vh.getLayoutPosition());
        }
    }

}
