package com.rcpt.ui.home.activity;

import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.android.databinding.library.baseAdapters.BR;
import com.liaoinstan.springview.container.DefaultFooter;
import com.liaoinstan.springview.container.DefaultHeader;
import com.liaoinstan.springview.widget.SpringView;
import com.rcpt.R;
import com.rcpt.base.adapter.SimpleBindingAdapter;
import com.rcpt.base.ui.BaseActivity;
import com.rcpt.bean.InstituteBean;
import com.rcpt.databinding.ActivityInstitutionBinding;
import com.rcpt.mvp.contract.InstitutionContract;
import com.rcpt.mvp.presenter.InstitutionPresenter;
import com.rcpt.recycler_listener.OnRecyclerItemClickListener;

import java.util.List;

public class InstitutionActivity extends BaseActivity<ActivityInstitutionBinding, InstitutionContract.View, InstitutionPresenter>
        implements InstitutionContract.View, SpringView.OnFreshListener{

    private boolean isEnd;
    private SimpleBindingAdapter<InstituteBean.InstitutionListBean> mAdapter;

    @Override
    protected void setupTitle() {
        setTitleText("培训机构");
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
    protected int getLayoutId() {
        return R.layout.activity_institution;
    }

    @Override
    protected InstitutionPresenter createPresenter() {
        return new InstitutionPresenter();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
    public void updateIsEnd(boolean isEnd) {
        this.isEnd = isEnd;
    }

    @Override
    public void initRecyclerView() {
        mDataBinding.springView.setListener(this);
        mDataBinding.springView.setHeader(new DefaultHeader(this));
        mDataBinding.springView.setFooter(new DefaultFooter(this));

        mDataBinding.recyclerView.addOnItemTouchListener(new InstitutionActivity.OnItemClickListener(getRecyclerView()));
        mDataBinding.recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mDataBinding.recyclerView.addItemDecoration(new DividerItemDecoration(mContext, DividerItemDecoration.VERTICAL));
        mAdapter = new SimpleBindingAdapter<>(R.layout.item_layout_home_news_follow, BR.newsFollowBean);
        mDataBinding.recyclerView.setAdapter(mAdapter);
    }

    @Override
    public void bindListData(List<InstituteBean.InstitutionListBean> beanList) {
        setListData(beanList);
        mAdapter.setupData(beanList);
    }

    @Override
    public RecyclerView getRecyclerView() {
        return mDataBinding.recyclerView;
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
