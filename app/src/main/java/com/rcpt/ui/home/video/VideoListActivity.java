package com.rcpt.ui.home.video;

import android.databinding.ViewDataBinding;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.liaoinstan.springview.container.DefaultFooter;
import com.liaoinstan.springview.container.DefaultHeader;
import com.liaoinstan.springview.widget.SpringView;
import com.rcpt.BR;
import com.rcpt.R;
import com.rcpt.adapter.FilterListAdapter;
import com.rcpt.base.adapter.BindingViewHolder;
import com.rcpt.base.adapter.SimpleBindingAdapter;
import com.rcpt.bean.FilterSelectBean;
import com.rcpt.bean.VideoListBean;
import com.rcpt.databinding.ActivityVideoListBinding;
import com.rcpt.databinding.ItemLayoutVideoListBinding;
import com.rcpt.mvp.contract.VideoListContract;
import com.rcpt.mvp.presenter.VideoListPresenter;
import com.rcpt.mvp.view.ListFilterActivity;
import com.rcpt.recycler_listener.OnRecyclerItemClickListener;

import java.util.List;

/**
 * 视频课程列表的页面
 */
public class VideoListActivity extends ListFilterActivity<ActivityVideoListBinding, VideoListContract.View, VideoListPresenter>
        implements VideoListContract.View, SpringView.OnFreshListener {

    private boolean isEnd;
    private SimpleBindingAdapter<VideoListBean.VideoItemBean> mAdapter;
    private FilterListAdapter mFilterAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    /**
     * 绑定筛选器列表数据
     *
     * @param listData
     */
    @Override
    public void bindFilterListData(List<FilterSelectBean> listData) {
        mFilterAdapter = new FilterListAdapter();
        mFilterAdapter.setOnAutoSelectCallback(mPresenter);
        mFilterAdapter.setupData(listData);
        mFilterAdapter.setClickMode(FilterListAdapter.CLICK_MODE_AUTOMATIC_ONE);
        mDataBinding.filterRecyclerView.setAdapter(mFilterAdapter);
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
        mDataBinding.cbxVideoCourseType.setText("全部课程");
        mDataBinding.cbxVideoSubjectType.setText("学科小类");
        mDataBinding.cbxVideoTeachMode.setText("授课方式");
        mDataBinding.cbxVideoSortMode.setText("排列方式");
    }

    /**
     * 未实现的方法
     */
    @Override
    protected void setupTitle() {
        setTitleText("视频课程");
        openTitleLeftView(true);
        setTitleRightText("重置");
        getTitleRightView().setVisibility(View.GONE);
    }

    @Override
    protected void initViews() {
        mPresenter.loadListData();
        mDataBinding.cbxVideoSortMode.setOnClickListener(mPresenter);
        mDataBinding.cbxVideoTeachMode.setOnClickListener(mPresenter);
        mDataBinding.cbxVideoSubjectType.setOnClickListener(mPresenter);
        mDataBinding.cbxVideoCourseType.setOnClickListener(mPresenter);
        mDataBinding.bgGray.setOnClickListener(mPresenter);
        getTitleRightView().setOnClickListener(mPresenter);
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

        mDataBinding.recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mAdapter = new SimpleBindingAdapter<VideoListBean.VideoItemBean>(R.layout.item_layout_video_list, BR.bean) {
            @Override
            protected void bindingViews(final BindingViewHolder<ViewDataBinding> holder, final int position, VideoListBean.VideoItemBean videoItemBean) {
                super.bindingViews(holder, position, videoItemBean);
                final ItemLayoutVideoListBinding binding = (ItemLayoutVideoListBinding) holder.getBinding();
                binding.getRoot().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mPresenter.onItemClick(binding.ivLogo, position);
                    }
                });
            }
        };
        mDataBinding.recyclerView.setAdapter(mAdapter);
        //mDataBinding.recyclerView.addOnItemTouchListener(new OnItemClickListener(getRecyclerView()));
        mDataBinding.recyclerView.setRefrshView(mDataBinding.springView);
        mDataBinding.recyclerView.setEmptyView(findViewById(R.id.empty_view));

        mDataBinding.filterRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));

    }

    @Override
    public void bindListData(List<VideoListBean.VideoItemBean> beanList) {
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

    @Override
    protected int getLayoutId() {
        return R.layout.activity_video_list;
    }

    @Override
    protected VideoListPresenter createPresenter() {
        return new VideoListPresenter();
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

    @Override
    public void resetSubjectTypeFilterName() {
        mDataBinding.cbxVideoSubjectType.setText("学科小类");
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
        return mDataBinding.llParent.getHeight() + mDataBinding.llVideoFilter.getHeight();
    }

}
