package com.rcpt.ui.me.video;

import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.liaoinstan.springview.container.DefaultFooter;
import com.liaoinstan.springview.container.DefaultHeader;
import com.liaoinstan.springview.widget.SpringView;
import com.rcpt.BR;
import com.rcpt.R;
import com.rcpt.base.adapter.SimpleBindingAdapter;
import com.rcpt.base.ui.BaseActivity;
import com.rcpt.bean.VideoListBean;
import com.rcpt.databinding.ActivityMyCourseBinding;
import com.rcpt.mvp.contract.MyCourseContract;
import com.rcpt.mvp.presenter.MyCoursePresenter;
import com.rcpt.recycler_listener.OnRecyclerItemClickListener;

import java.util.List;

/**
 * 我的课程
 */
public class MyCourseActivity extends BaseActivity<ActivityMyCourseBinding, MyCourseContract.View, MyCoursePresenter>
        implements MyCourseContract.View, SpringView.OnFreshListener {

    private boolean isEnd;
    private SimpleBindingAdapter<VideoListBean.VideoItemBean> mAdapter;

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
        mDataBinding.springView.setHeader(new DefaultHeader(mContext));
        mDataBinding.springView.setFooter(new DefaultFooter(mContext));
        mDataBinding.springView.setListener(this);
        mDataBinding.recyclerView.addOnItemTouchListener(new OnItemClickListener(getRecyclerView()));
        mDataBinding.recyclerView.setLayoutManager(new GridLayoutManager(mContext, 2));
        mAdapter = new SimpleBindingAdapter<>(R.layout.item_layout_my_video_course_list, BR.bean);
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
    protected void setupTitle() {
        setTitleText("我的课程");
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
    protected int getLayoutId() {
        return R.layout.activity_my_course;
    }

    @Override
    protected MyCoursePresenter createPresenter() {
        return new MyCoursePresenter();
    }

    private class OnItemClickListener extends OnRecyclerItemClickListener {

        public OnItemClickListener(RecyclerView recyclerView) {
            super(recyclerView);
        }

        @Override
        public void onItemClick(RecyclerView.ViewHolder vh) {
            mPresenter.onItemClick(vh, vh.getAdapterPosition());
        }
    }

}
