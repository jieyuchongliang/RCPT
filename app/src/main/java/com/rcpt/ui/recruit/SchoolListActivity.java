package com.rcpt.ui.recruit;

import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.liaoinstan.springview.container.DefaultFooter;
import com.liaoinstan.springview.container.DefaultHeader;
import com.liaoinstan.springview.widget.SpringView;
import com.rcpt.BR;
import com.rcpt.R;
import com.rcpt.adapter.FilterListAdapter;
import com.rcpt.base.adapter.SimpleBindingAdapter;
import com.rcpt.bean.FilterSelectBean;
import com.rcpt.bean.RecruitFragmentListBean;
import com.rcpt.databinding.ActivitySchoolListBinding;
import com.rcpt.mvp.contract.SchoolListContract;
import com.rcpt.mvp.presenter.SchoolListPresenter;
import com.rcpt.mvp.view.ListFilterActivity;
import com.rcpt.recycler_listener.OnRecyclerItemClickListener;

import java.util.List;

/**
 * 校园列表
 */
public class SchoolListActivity extends ListFilterActivity<ActivitySchoolListBinding, SchoolListContract.View, SchoolListPresenter>
        implements SchoolListContract.View, SpringView.OnFreshListener {

    private SimpleBindingAdapter<RecruitFragmentListBean.SubBean> mAdapter;
    private boolean isEnd;
    private FilterListAdapter mFilterAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void updateIsEnd(boolean isEnd) {
        this.isEnd = isEnd;
    }

    @Override
    public void initRecyclerView() {
        mDataBinding.springView.setHeader(new DefaultHeader(this));
        mDataBinding.springView.setFooter(new DefaultFooter(this));
        mDataBinding.springView.setListener(this);

        mAdapter = new SimpleBindingAdapter<>(R.layout.item_layout_recruit_fragment_two_type, BR.subBean);
        mDataBinding.recyclerView.addOnItemTouchListener(new OnItemClickListener(getRecyclerView()));
        mDataBinding.recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        mDataBinding.recyclerView.setAdapter(mAdapter);
        mDataBinding.recyclerView.setRefrshView(mDataBinding.springView);
        mDataBinding.recyclerView.setEmptyView(findViewById(R.id.empty_view));

        //初始化筛选列表的RecyclerView
        mDataBinding.filterRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
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
     * 未实现的方法
     */
    @Override
    protected void setupTitle() {
        setTitleText("院校列表");
        openTitleLeftView(true);
        setTitleRightText("重置");
        hintTitleRightView();
    }

    @Override
    protected void initViews() {
        setOnClickListener(getTitleRightView(), mDataBinding.cbxSchoolFilterBatch, mDataBinding.cbxSchoolFilterType, mDataBinding.bgGray);
        mPresenter.loadFilterList();
        mPresenter.loadListData();
    }

    @Override
    public void bindFilterListData(List<FilterSelectBean> listData) {
        mFilterAdapter = new FilterListAdapter(FilterListAdapter.CLICK_MODE_AUTOMATIC_ONE);
        mFilterAdapter.setOnAutoSelectCallback(mPresenter);
        mDataBinding.filterRecyclerView.setAdapter(mFilterAdapter);
        mFilterAdapter.setupData(listData);
    }

    @Override
    protected ViewGroup getRefreshView() {
        return mDataBinding.springView;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_school_list;
    }

    @Override
    protected SchoolListPresenter createPresenter() {
        return new SchoolListPresenter();
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
     * 显示右侧的按钮
     */
    @Override
    public void showTitleRightView() {
        getTitleRightView().setVisibility(View.VISIBLE);
    }

    /**
     * 隐藏标题栏右侧的按钮
     */
    @Override
    public void hintTitleRightView() {
        getTitleRightView().setVisibility(View.GONE);
    }


    /**
     * 重置筛选器Item 的显示内容
     */
    @Override
    public void resetFilterAllName() {
        mFilterAdapter.dataReset();
        mDataBinding.cbxSchoolFilterType.setText("类型");
        mDataBinding.cbxSchoolFilterBatch.setText("批次");
    }

    /**
     * 获取背景的View
     */
    @Override
    protected View getGrayBgView() {
        return mDataBinding.bgGray;
    }

    /**
     * 获取用于筛选的布局
     *
     * @return
     */
    @Override
    protected View getFilterLayout() {
        return mDataBinding.llParent;
    }

    /**
     * 获取筛选布局的高度
     *
     * @return
     */
    @Override
    protected int getFilterLayoutHeight() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
            return mDataBinding.llParent.getHeight();
        return mDataBinding.llParent.getHeight() + mDataBinding.llSchoolFilter.getHeight();
    }

    @Override
    protected void loadListData(int page) {
        mPresenter.onLoadMore(page);
    }

    public void setOnClickListener(View... views) {
        for (View view : views) {
            view.setOnClickListener(mPresenter);
        }
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
