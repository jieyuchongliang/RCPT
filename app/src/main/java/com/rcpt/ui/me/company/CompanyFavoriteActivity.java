package com.rcpt.ui.me.company;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
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
import com.rcpt.bean.CompanyFavoritesListBean;
import com.rcpt.databinding.ActivityCompanyFavoriteBinding;
import com.rcpt.mvp.contract.CompanyFavoriteContract;
import com.rcpt.mvp.presenter.CompanyFavoritePresenter;
import com.rcpt.recycler_listener.OnRecyclerItemClickListener;
import com.rcpt.utils.DialogUtils;

import java.util.List;

public class CompanyFavoriteActivity extends BaseActivity<ActivityCompanyFavoriteBinding, CompanyFavoriteContract.View, CompanyFavoritePresenter>
        implements CompanyFavoriteContract.View, SpringView.OnFreshListener {

    private SimpleBindingAdapter<CompanyFavoritesListBean.CompanyFavoritesBean> mAdapter;
    private boolean isEnd;
    private OnItemClickListener mOnItemClickListener;
    private int mClickPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    /**
     * 未实现的方法
     */
    @Override
    protected void setupTitle() {
        setTitleText("收藏夹");
        openTitleLeftView(true);
    }

    @Override
    protected void initViews() {
        mPresenter.loadListData();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_company_favorite;
    }

    @Override
    protected CompanyFavoritePresenter createPresenter() {
        return new CompanyFavoritePresenter();
    }

    @Override
    public void initRecyclerView() {
        mDataBinding.springView.setHeader(new DefaultHeader(mContext));
        mDataBinding.springView.setFooter(new DefaultFooter(mContext));
        mDataBinding.springView.setListener(this);

        mOnItemClickListener = new OnItemClickListener(getRecyclerView());
        mDataBinding.recyclerView.addOnItemTouchListener(mOnItemClickListener);
        mDataBinding.recyclerView.addItemDecoration(new DividerItemDecoration(mContext, DividerItemDecoration.VERTICAL));
        mDataBinding.recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mAdapter = new SimpleBindingAdapter<>(R.layout.item_layout_company_cv_collect_list, BR.collectBean);
        mDataBinding.recyclerView.setAdapter(mAdapter);
        mDataBinding.recyclerView.setRefrshView(mDataBinding.springView);
        mDataBinding.recyclerView.setEmptyView(findViewById(R.id.empty_view));
    }

    @Override
    public void updateIsEnd(boolean isEnd) {
        this.isEnd = isEnd;
    }

    @Override
    public void bindListData(List<CompanyFavoritesListBean.CompanyFavoritesBean> beanList) {
        setListData(beanList);
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
    public int getClickPosition() {
        return mClickPosition;
    }

    @Override
    public void deleteListItem(int position) {
        mAdapter.notifyItemRemoved(position);
    }

    @Override
    protected void loadListData(int page) {
        mPresenter.onLoadMore(page);
    }

    private class OnItemClickListener extends OnRecyclerItemClickListener {

        public OnItemClickListener(RecyclerView recyclerView) {
            super(recyclerView);
        }

        @Override
        public void onItemClick(RecyclerView.ViewHolder vh) {
            mClickPosition = vh.getLayoutPosition();
            mPresenter.onItemClick();
        }

        @Override
        public boolean onItemLongClick(final RecyclerView.ViewHolder vh) {
            DialogUtils.buildAlertDialogWithCancel(mContext, "温馨提示", "您是否要删除该收藏条目")
                    .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            mClickPosition = vh.getAdapterPosition();
                            mPresenter.onItemLongClick();
                        }
                    }).show();
            return true;
        }
    }
}
