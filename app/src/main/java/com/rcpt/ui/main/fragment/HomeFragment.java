package com.rcpt.ui.main.fragment;


import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.lzp.statusbar.StatusBarCompat;
import com.rcpt.BR;
import com.rcpt.R;
import com.rcpt.adapter.holder.LocalImageHolderView;
import com.rcpt.base.adapter.SimpleBindingAdapter;
import com.rcpt.base.ui.BaseFragment;
import com.rcpt.bean.InstituteBean;
import com.rcpt.databinding.FragmentHomeBinding;
import com.rcpt.mvp.contract.HomeContract;
import com.rcpt.mvp.contract.HomeInfoContract;
import com.rcpt.mvp.module.HomeModule;
import com.rcpt.mvp.presenter.HomePresenter;
import com.rcpt.recycler_listener.OnRecyclerItemClickListener;
import com.rcpt.ui.home.activity.HomeInfoActivity;
import com.rcpt.ui.main.activity.MainActivity;
import com.rcpt.widget.ObservableScrollView;

import java.util.List;


/**
 * 首页的Fragment
 */
public class HomeFragment extends BaseFragment<FragmentHomeBinding, HomeContract.View, HomePresenter>
        implements HomeContract.View, SwipeRefreshLayout.OnRefreshListener {

    //    private HomeAdapter mAdapter;
    private SimpleBindingAdapter<InstituteBean.InstitutionListBean> mAdapter;

    private int mViewFlipperTopHeight = -1;//ViewFlipper距离顶部的距离

    public static HomeFragment newInstance() {
        HomeFragment fragment = new HomeFragment();
        return fragment;
    }

    private boolean isLoadingSuccess;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initViews() {

        StatusBarCompat.setStatusBarColor(getActivity(), mTitleCompatLayout, true);
        setTitleText("首页");
        mDataBinding.setPresenter(mPresenter);
        mPresenter.loadListData();
//        mPresenter.bindBanner();
//        mPresenter.bindAfficheViews();
        mPresenter.setItemRes();
        mDataBinding.swipeRefreshLayout.setOnRefreshListener(this);
////        mTitleCompatLayout.setAlpha(0);
        mDataBinding.viewFlipper.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                if (mViewFlipperTopHeight < 0) {
                    mViewFlipperTopHeight = mDataBinding.llViewFlipperParent.getTop();
                    if (Build.VERSION.SDK_INT >= 16)
                        mTitleCompatLayout.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                }
            }
        });
        mDataBinding.slContent.setOnScrollChangedListener(new ObservableScrollView.OnScrollChangedListener() {
            @Override
            public void onScrollChanged(ObservableScrollView scrollView, int x, int y, int oldx, int oldy) {
                if (y <= mViewFlipperTopHeight) {
                    if (!mDataBinding.viewFlipper.isFlipping())//如果没有运行，设置为运行
                        mDataBinding.viewFlipper.startFlipping();
                } else {
                    if (mDataBinding.viewFlipper.isFlipping())//如果正在运行设置为未运行
                        mDataBinding.viewFlipper.stopFlipping();
                }
            }
        });
    }

    @Override
    protected HomePresenter createPresenter() {
        return new HomePresenter();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (hidden) {
            mDataBinding.viewFlipper.stopFlipping();
            mDataBinding.convenientBanner.stopTurning();
        } else {
            mDataBinding.viewFlipper.startFlipping();
            mDataBinding.convenientBanner.startTurning(3000);
            if (!isLoadingSuccess) {
                mPresenter.loadListData();
            }
        }
    }


    @Override
    public void bindAfficheViews(View... views) {
        for (View view : views) {
            mDataBinding.viewFlipper.addView(view);
        }
    }

    @Override
    public View getAfficheView(final String affiche, final String afficheId) {
        TextView afficheView = new TextView(mContext);
        FrameLayout.LayoutParams lp = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        afficheView.setTextColor(ContextCompat.getColor(mContext, R.color.colorTextBlack));
        afficheView.setGravity(Gravity.CENTER_VERTICAL);
        afficheView.setText(affiche);
        afficheView.setLayoutParams(lp);
        afficheView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HomeInfoActivity.actionStart(mContext, afficheId, HomeInfoContract.INFO_TYPE_NEWS);
            }
        });
        return afficheView;
    }


    @Override
    public void initRecyclerView() {
        mDataBinding.recyclerView.setLayoutManager(new LinearLayoutManager(mContext) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        });
        mDataBinding.recyclerView.addItemDecoration(new DividerItemDecoration(mContext, DividerItemDecoration.VERTICAL));
        mDataBinding.recyclerView.addOnItemTouchListener(new OnItemClickListener(getRecyclerView()));
        mAdapter = new SimpleBindingAdapter<>(R.layout.item_layout_home_news_follow, BR.newsFollowBean);
        mDataBinding.recyclerView.setAdapter(mAdapter);
    }

    @Override
    public void bindListData(List<InstituteBean.InstitutionListBean> beanList) {
        isLoadingSuccess = true;
        mDataBinding.swipeRefreshLayout.setRefreshing(false);
        setListData(beanList);
        mAdapter.setupData(beanList);
    }


    @Override
    public RecyclerView getRecyclerView() {
        return mDataBinding.recyclerView;
    }

    @Override
    public void setConvenientBannerHolder(CBViewHolderCreator<LocalImageHolderView> holderCreator, List<String> images) {
        mDataBinding.convenientBanner
                .setPages(holderCreator, images)
                .setPageIndicator(new int[]{R.drawable.ic_banner_page_indicator, R.drawable.ic_banner_page_indicator_focused})
                .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.CENTER_HORIZONTAL)
                .startTurning(3000);
    }

    @Override
    public void bindItemRes(HomeModule.ItemRes itemRes) {
        int childCount = mDataBinding.llOneLayout.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childView = mDataBinding.llOneLayout.getChildAt(i);
            if (childView.getId() == itemRes.id) {
                ImageView ivIcon = (ImageView) childView.findViewById(R.id.iv_icon);
                TextView tvTitle = (TextView) childView.findViewById(R.id.tv_title);
                ivIcon.setBackgroundResource(itemRes.itemBackground);
                ivIcon.setImageResource(itemRes.itemSrc);
                tvTitle.setText(itemRes.title);
                return;
            }
        }
    }

    @Override
    public void changeMainPage(int position) {
        Activity activity = getActivity();
        if (activity instanceof MainActivity) {
            ((MainActivity) activity).changeCurrentItem(position);
        }
    }

    @Override
    public void setViewClickListener(View.OnClickListener listener) {
        addViewOnClickListener(listener, mDataBinding.includeTest.getRoot(), mDataBinding.includeNews.getRoot(), mDataBinding.includeConsult.getRoot(), mDataBinding.includeTraining.getRoot());
    }

    @Override
    public void onRefresh() {
        isLoadingSuccess = false;
        mPresenter.loadListData();
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
