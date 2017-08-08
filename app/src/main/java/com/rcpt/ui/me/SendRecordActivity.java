package com.rcpt.ui.me;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.liaoinstan.springview.container.DefaultFooter;
import com.liaoinstan.springview.container.DefaultHeader;
import com.liaoinstan.springview.widget.SpringView;
import com.rcpt.R;
import com.rcpt.adapter.SendRecordAdapter;
import com.rcpt.base.ui.BaseActivity;
import com.rcpt.bean.SendRecordBean;
import com.rcpt.databinding.ActivitySendRecordBinding;
import com.rcpt.mvp.contract.SendRecordContract;
import com.rcpt.mvp.presenter.SendRecordPresenter;

import java.util.List;

/**
 * 投递记录的Activity
 */
public class SendRecordActivity extends BaseActivity<ActivitySendRecordBinding, SendRecordContract.View, SendRecordPresenter>
        implements SendRecordContract.View, SendRecordAdapter.OnItemDeleteClickListener, SpringView.OnFreshListener{

    private SendRecordAdapter mAdapter;
    private int mDeletePosition;
    private boolean isEnd;

    @Override
    protected void setupTitle() {
        setTitleText("投递记录");
        openTitleLeftView(true);
    }

    @Override
    protected void initViews() {
        mPresenter.loadListData();
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
    public void onRefresh() {
        onRefresh(isEnd);
    }

    @Override
    public void onLoadmore() {
        onLoadMore(isEnd);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_send_record;
    }

    @Override
    protected SendRecordPresenter createPresenter() {
        return new SendRecordPresenter();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void initRecyclerView() {
        mDataBinding.springView.setHeader(new DefaultHeader(mContext));
        mDataBinding.springView.setFooter(new DefaultFooter(mContext));
        mDataBinding.springView.setListener(this);
        mDataBinding.recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mDataBinding.recyclerView.setRefrshView(mDataBinding.springView);
        mDataBinding.recyclerView.setEmptyView(findViewById(R.id.empty_view));
        mAdapter = new SendRecordAdapter();
        mAdapter.setOnDeleteClickListener(this);
        mDataBinding.recyclerView.setAdapter(mAdapter);
    }



    @Override
    public void bindListData(List<SendRecordBean.DeliveryRecordListBean> beanList) {
        setListData(beanList);
        mAdapter.setupData(beanList);
    }

    @Override
    public RecyclerView getRecyclerView() {
        return mDataBinding.recyclerView;
    }

    /**
     * 获取删除的点击条目
     *
     * @return
     */
    @Override
    public int getDeleteClickPosition() {
        return mDeletePosition;
    }

    /**
     * 列表中条目删除后，进行更新
     *
     * @param position
     */
    @Override
    public void listItemDeleteUpdate(int position) {
        mAdapter.notifyItemRemoved(position);
    }

    @Override
    public void onItemClick(View view, int position) {
        mDeletePosition = position;
        mPresenter.onItemClick();
    }

    @Override
    public void updateIsEnd(boolean isEnd) {
        this.isEnd = isEnd;
    }
}
