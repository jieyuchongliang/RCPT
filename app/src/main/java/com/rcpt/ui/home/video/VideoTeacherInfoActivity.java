package com.rcpt.ui.home.video;

import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.liaoinstan.springview.container.DefaultFooter;
import com.liaoinstan.springview.container.DefaultHeader;
import com.liaoinstan.springview.widget.SpringView;
import com.rcpt.BR;
import com.rcpt.R;
import com.rcpt.base.adapter.BindingViewHolder;
import com.rcpt.base.adapter.SimpleBindingAdapter;
import com.rcpt.base.ui.BaseActivity;
import com.rcpt.bean.VideoListBean;
import com.rcpt.databinding.ActivityVideoTeacherInfoBinding;
import com.rcpt.databinding.ItemLayoutVideoTeacherVideoListBinding;
import com.rcpt.mvp.contract.VideoTeacherInfoContract;
import com.rcpt.mvp.presenter.VideoTeacherInfoPresenter;

import java.util.List;

public class VideoTeacherInfoActivity extends BaseActivity<ActivityVideoTeacherInfoBinding, VideoTeacherInfoContract.View, VideoTeacherInfoPresenter>
        implements VideoTeacherInfoContract.View, SpringView.OnFreshListener {

    private SimpleBindingAdapter<VideoListBean.VideoItemBean> mAdapter;
    private boolean isEnd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    /**
     * 未实现的方法
     */
    @Override
    protected void setupTitle() {
        mDataBinding.ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                closeActivity();
            }
        });
    }

    @Override
    protected void initViews() {
        mPresenter.loadListData();
    }

    @Override
    public void updateIsEnd(boolean isEnd) {
        this.isEnd = isEnd;
    }

    @Override
    public void initRecyclerView() {

        mDataBinding.springView.setFooter(new DefaultFooter(mContext));
        mDataBinding.springView.setListener(this);
        mDataBinding.recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mAdapter = new SimpleBindingAdapter<VideoListBean.VideoItemBean>(R.layout.item_layout_video_teacher_video_list, BR.bean) {
            @Override
            protected void bindingViews(BindingViewHolder<ViewDataBinding> holder, int position, VideoListBean.VideoItemBean videoItemBean) {
                super.bindingViews(holder, position, videoItemBean);
                ItemLayoutVideoTeacherVideoListBinding binding = (ItemLayoutVideoTeacherVideoListBinding) holder.getBinding();
                binding.videoInfo.setBean(videoItemBean);
            }
        };
        mDataBinding.recyclerView.setAdapter(mAdapter);
    }

    @Override
    public void bindListData(List<VideoListBean.VideoItemBean> beanList) {
        setListData(beanList);
        mAdapter.setupData(beanList);
    }

    @Override
    public RecyclerView getRecyclerView() {
        return mDataBinding.recyclerView;
    }

    @Override
    public void onRefresh() {

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
    protected void loadListData(int page) {
        mPresenter.onLoadMore(page);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_video_teacher_info;
    }

    @Override
    protected VideoTeacherInfoPresenter createPresenter() {
        return new VideoTeacherInfoPresenter();
    }

}
