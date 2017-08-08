package com.rcpt.ui.me;

import android.content.DialogInterface;
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
import com.rcpt.bean.FavoritesBean;
import com.rcpt.databinding.ActivityFavoritesBinding;
import com.rcpt.mvp.contract.FavoritesContract;
import com.rcpt.mvp.presenter.FavoritesPresenter;
import com.rcpt.recycler_listener.OnRecyclerItemClickListener;
import com.rcpt.utils.DialogUtils;

import java.util.List;

/**
 * 收藏夹页面
 */
public class FavoritesActivity extends BaseActivity<ActivityFavoritesBinding, FavoritesContract.View, FavoritesPresenter>
        implements FavoritesContract.View, SpringView.OnFreshListener {

    private SimpleBindingAdapter<FavoritesBean.FavoritesListBean> mAdapter;
    private boolean isEnd;
    private String recordId;
    private int mClickPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public void initRecyclerView() {

        mDataBinding.springView.setListener(this);
        mDataBinding.springView.setHeader(new DefaultHeader(this));
        mDataBinding.springView.setFooter(new DefaultFooter(this));

        mDataBinding.recyclerView.addOnItemTouchListener(new OnItemClickListener(getRecyclerView()));
        mDataBinding.recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mDataBinding.recyclerView.addItemDecoration(new DividerItemDecoration(mContext, DividerItemDecoration.VERTICAL));
        mDataBinding.recyclerView.setRefrshView(mDataBinding.springView);
        mDataBinding.recyclerView.setEmptyView(findViewById(R.id.empty_view));
        mAdapter = new SimpleBindingAdapter<>(R.layout.item_layout_favorites_list, BR.bean);
        mDataBinding.recyclerView.setAdapter(mAdapter);
    }


    @Override
    public void bindListData(List<FavoritesBean.FavoritesListBean> beanList) {
        setListData(beanList);
        mAdapter.setupData(beanList);
    }


    @Override
    public RecyclerView getRecyclerView() {
        return mDataBinding.recyclerView;
    }

    @Override
    protected void setupTitle() {
        setTitleText("我的收藏夹");
        openTitleLeftView(true);
    }

    @Override
    protected void initViews() {
        mPresenter.loadListData();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_favorites;
    }

    @Override
    protected FavoritesPresenter createPresenter() {
        return new FavoritesPresenter();
    }

    @Override
    public void updateIsEnd(boolean isEnd) {
        this.isEnd = isEnd;
    }

    @Override
    public void deleteListItemUpdate(int position) {
        mAdapter.notifyItemRemoved(position);
    }

    @Override
    public String getRecordId() {
        return recordId;
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
    public int getClickPosition() {
        return mClickPosition;
    }

    private class OnItemClickListener extends OnRecyclerItemClickListener {


        public OnItemClickListener(RecyclerView recyclerView) {
            super(recyclerView);
        }

        @Override
        public void onItemClick(RecyclerView.ViewHolder vh) {
            mPresenter.onItemClick(vh, vh.getLayoutPosition());
        }

        @Override
        public boolean onItemLongClick(RecyclerView.ViewHolder vh) {
            mClickPosition = vh.getAdapterPosition();
            DialogUtils
                    .buildAlertDialogWithCancel(mContext, "温馨提示", "您是取消此职位的收藏")
                    .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            mPresenter.onItemLongClick();
                        }
                    }).show();
            return true;
        }
    }

}
