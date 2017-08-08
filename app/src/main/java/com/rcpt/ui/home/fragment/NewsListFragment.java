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
import com.rcpt.bean.NewsListBean;
import com.rcpt.databinding.FragmentNewsBinding;
import com.rcpt.mvp.contract.NewsFragmentContract;
import com.rcpt.mvp.presenter.NewsFragmentPresenter;
import com.rcpt.recycler_listener.OnRecyclerItemClickListener;

import java.util.List;

/**
 * 首页中最新关注的列表界面
 */
public class NewsListFragment extends BaseFragment<FragmentNewsBinding, NewsFragmentContract.View, NewsFragmentPresenter>
        implements NewsFragmentContract.View, SpringView.OnFreshListener {

    private SimpleBindingAdapter<NewsListBean.GetlistNewsBean> mAdapter;


    private static final String NEWS_TAG = "news_tag";
    private static final String NEWS_TYPE = "news_type";

    private String mNewsId;
    private int mNewsType;
    private boolean isEnd;

    public static NewsListFragment newInstance(String newsId, int newsType) {
        NewsListFragment fragment = new NewsListFragment();
        Bundle bundle = new Bundle();
        bundle.putString(NEWS_TAG, newsId);
        bundle.putInt(NEWS_TYPE, newsType);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            Bundle bundle = getArguments();
            mNewsId = bundle.getString(NEWS_TAG);
            mNewsType = bundle.getInt(NEWS_TYPE);
        }
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_news;
    }

    @Override
    protected void initViews() {
        mPresenter.loadListData();
    }

    @Override
    protected NewsFragmentPresenter createPresenter() {
        return new NewsFragmentPresenter();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public String getNewsId() {
        return mNewsId;
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

        mDataBinding.recyclerView.addOnItemTouchListener(new OnItemClickListener(mDataBinding.recyclerView));
        mDataBinding.recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mDataBinding.recyclerView.addItemDecoration(new DividerItemDecoration(mContext, DividerItemDecoration.VERTICAL));
        mDataBinding.recyclerView.setRefrshView(mDataBinding.springView);
        mDataBinding.recyclerView.setEmptyView(mDataBinding.getRoot().findViewById(R.id.empty_view));
        mAdapter = new SimpleBindingAdapter<>(R.layout.item_layout_home_news_list, BR.newsListBean);
        mDataBinding.recyclerView.setAdapter(mAdapter);
    }

/*    @Override
    public void addItemClickListener(OnRecyclerItemClickListener listener) {
        mDataBinding.recyclerView.addOnItemTouchListener(listener);
    }

    @Override
    public void onItemClick(NewsListBean.GetlistNewsBean bean, int position) {


    }*/

    @Override
    public int getNewsType() {
        return mNewsType;
    }

    @Override
    public RecyclerView getRecyclerView() {
        return mDataBinding.recyclerView;
    }

    @Override
    public void bindListData(List<NewsListBean.GetlistNewsBean> beanList) {
        setListData(beanList);
        mDataBinding.springView.onFinishFreshAndLoad();
        mAdapter.setupData(beanList);
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


    @Override
    protected ViewGroup getRefreshView() {
        return mDataBinding.springView;
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
