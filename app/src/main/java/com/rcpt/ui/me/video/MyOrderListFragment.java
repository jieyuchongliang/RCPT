package com.rcpt.ui.me.video;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.databinding.library.baseAdapters.BR;
import com.liaoinstan.springview.container.DefaultFooter;
import com.liaoinstan.springview.container.DefaultHeader;
import com.liaoinstan.springview.widget.SpringView;
import com.rcpt.Constant;
import com.rcpt.R;
import com.rcpt.base.adapter.BindingViewHolder;
import com.rcpt.base.adapter.SimpleBindingAdapter;
import com.rcpt.base.ui.BaseFragment;
import com.rcpt.base.ui.LazyFragment;
import com.rcpt.bean.MyOrderListBean;
import com.rcpt.bean.VideoPaymentBean;
import com.rcpt.databinding.FragmentMyOrderListBinding;
import com.rcpt.databinding.ItemLayoutMyOrderListBinding;
import com.rcpt.mvp.contract.MyOrderListFragmentContract;
import com.rcpt.mvp.presenter.MyOrderListPresenter;
import com.rcpt.ui.home.video.VideoInfoActivity;
import com.rcpt.ui.home.video.VideoPaymentActivity;

import java.util.List;

/**
 * Created by 860116021 on 2017/5/10.
 */

public class MyOrderListFragment extends LazyFragment<FragmentMyOrderListBinding, MyOrderListFragmentContract.View, MyOrderListPresenter>
        implements MyOrderListFragmentContract.View, SpringView.OnFreshListener {

    private SimpleBindingAdapter<MyOrderListBean.OrderListBean> mAdapter;

    private boolean isEnd;


    public final static String ORDER_TYPE_ALL = "";
    public final static String ORDER_TYPE_WAIT_PAY = "0";
    public final static String ORDER_TYPE_FINISH = "1";


    public static MyOrderListFragment newInstance(String orderType) {
        MyOrderListFragment fragment = new MyOrderListFragment();
        Bundle bundle = new Bundle();
        bundle.putString("orderType", orderType);
        fragment.setArguments(bundle);
        return fragment;
    }

    private LocalBroadcastManager mBroadcastManager;
    private BroadcastReceiver mBroadcastReceiver;

    private boolean isNeedRefresh;

    private int mClickItemPosition;
    private boolean isLoadDataCompleted;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        refreshList();
    }


    @Override
    public void onResume() {
        super.onResume();
        refreshList();
    }

    private void refreshList() {
        if (isNeedRefresh && mDataBinding.springView != null && getUserVisibleHint() && isLoadDataCompleted) {
            onRefresh();
        }
    }

    /**
     * 获取点击的条目position
     *
     * @return
     */
    @Override
    public int getClickItemPosition() {
        return mClickItemPosition;
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

    /**
     * 删除列表中的指定条目
     *
     * @param position
     */
    @Override
    public void removeListItem(int position) {
        mAdapter.notifyItemRemoved(position);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void initRecyclerView() {
        mDataBinding.springView.setListener(this);
        mDataBinding.springView.setHeader(new DefaultHeader(mContext));
        mDataBinding.springView.setFooter(new DefaultFooter(mContext));

        mDataBinding.recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mDataBinding.recyclerView.setRefrshView(mDataBinding.springView);
        mDataBinding.recyclerView.setEmptyView(mDataBinding.getRoot().findViewById(R.id.empty_view));
        mAdapter = new SimpleBindingAdapter<MyOrderListBean.OrderListBean>(R.layout.item_layout_my_order_list, BR.bean) {
            @Override
            protected void bindingViews(final BindingViewHolder<ViewDataBinding> holder, int position, final MyOrderListBean.OrderListBean orderListBean) {
                super.bindingViews(holder, position, orderListBean);

                ItemLayoutMyOrderListBinding binding = (ItemLayoutMyOrderListBinding) holder.getBinding();
                binding.dashedLine.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
                binding.delOrder.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mClickItemPosition = holder.getAdapterPosition();
                        mPresenter.onClickDelOrder();
                    }
                });
                binding.btnPay.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        VideoPaymentBean bean = new VideoPaymentBean();
                        bean.setCourseName(orderListBean.getTitle());
                        bean.setCoursePrice(TextUtils.isEmpty(orderListBean.getPrice()) ? "0.00" : orderListBean.getPrice());
                        VideoPaymentActivity.actionStart(mContext, bean, orderListBean.getOrderNum());
                    }
                });
                binding.getRoot().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        VideoInfoActivity.actionStart(mContext, orderListBean.getCourse_id(), null, null, null);
                    }
                });
            }
        };
        mDataBinding.recyclerView.setAdapter(mAdapter);
    }

    @Override
    public void bindListData(List<MyOrderListBean.OrderListBean> beanList) {
        setListData(beanList);
        mAdapter.setupData(beanList);
        isLoadDataCompleted = true;
        isNeedRefresh = false;
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
    protected int getLayoutRes() {
        return R.layout.fragment_my_order_list;
    }

    @Override
    protected void loadData() {
        mPresenter.loadListData();
    }

    @Override
    protected void initViews() {

        mBroadcastManager = LocalBroadcastManager.getInstance(mContext);
        IntentFilter filter = new IntentFilter();
        filter.addAction(Constant.LOCAL_BROADCAST_UPDATE_ORDER_LIST);
        mBroadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                isNeedRefresh = true;
            }
        };
        mBroadcastManager.registerReceiver(mBroadcastReceiver, filter);
    }

    @Override
    public void onDestroy() {
        mBroadcastManager.unregisterReceiver(mBroadcastReceiver);
        super.onDestroy();
    }


    @Override
    public String getOrderType() {
        return getArguments().getString("orderType");
    }

    @Override
    protected void loadListData(int page) {
        mPresenter.onLoadMore(page);
    }

    @Override
    protected MyOrderListPresenter createPresenter() {
        return new MyOrderListPresenter();
    }
}
