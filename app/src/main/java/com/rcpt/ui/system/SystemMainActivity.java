package com.rcpt.ui.system;

import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.v7.view.menu.MenuItemImpl;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.ViewGroup;
import android.widget.Toast;

import com.rcpt.BR;
import com.rcpt.R;
import com.rcpt.base.adapter.BindingViewHolder;
import com.rcpt.base.adapter.SimpleBindingAdapter;
import com.rcpt.base.ui.BaseActivity;
import com.rcpt.bean.MenuBean;
import com.rcpt.databinding.ActivitySystemMainBinding;
import com.rcpt.databinding.ItemLayoutSystemMainMenuBinding;
import com.rcpt.mvp.contract.SystemMainContract;
import com.rcpt.mvp.presenter.SystemMainPresenter;
import com.rcpt.recycler_listener.OnRecyclerItemClickListener;
import com.rcpt.utils.DividerGridItemDecoration;
import com.rcpt.utils.MenuBuildUtils;

import java.util.List;

public class SystemMainActivity extends BaseActivity<ActivitySystemMainBinding, SystemMainContract.View, SystemMainPresenter>
        implements SystemMainContract.View {

    private SimpleBindingAdapter<MenuBean> mAdapter;
    private int mClickPosition;
    private long mExitTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    /**
     * 未实现的方法
     */
    @Override
    protected void setupTitle() {
        setTitleText("平台管理");
    }

    @Override
    protected void initViews() {
        mPresenter.initMenu();
        mPresenter.loadListData();
    }


    @Override
    public void initRecyclerView() {
        final int itemCount = 3;
        int widthPixels = mContext.getResources().getDisplayMetrics().widthPixels;
        final int itemSize = widthPixels / itemCount;
        mDataBinding.recyclerView.setLayoutManager(new GridLayoutManager(mContext, itemCount));
        mDataBinding.recyclerView.addItemDecoration(new DividerGridItemDecoration(mContext));
        mDataBinding.recyclerView.addOnItemTouchListener(new OnItemClickListener(getRecyclerView()));
        mAdapter = new SimpleBindingAdapter<MenuBean>(R.layout.item_layout_system_main_menu, BR.menuBean) {
            @Override
            public BindingViewHolder<ViewDataBinding> onCreateViewHolder(ViewGroup parent, int viewType) {
                BindingViewHolder<ViewDataBinding> viewHolder = super.onCreateViewHolder(parent, viewType);
                ItemLayoutSystemMainMenuBinding binding = (ItemLayoutSystemMainMenuBinding) viewHolder.getBinding();
                if (binding.llParent.getLayoutParams() != null) {
                    GridLayoutManager.LayoutParams layoutParams = (GridLayoutManager.LayoutParams) binding.llParent.getLayoutParams();
                    layoutParams.width = itemSize;
                    layoutParams.height = itemSize;
                }
                return viewHolder;
            }
        };
        mDataBinding.recyclerView.setAdapter(mAdapter);

    }

    @Override
    public void bindListData(List<MenuBean> beanList) {
        mAdapter.setupData(beanList);
    }

    @Override
    public List<MenuItemImpl> getMenuList() {
        return MenuBuildUtils.buildMenuItemList(mContext, R.menu.system_main_menu);
    }

    @Override
    public RecyclerView getRecyclerView() {
        return mDataBinding.recyclerView;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_system_main;
    }

    @Override
    protected SystemMainPresenter createPresenter() {
        return new SystemMainPresenter();
    }

    @Override
    public int getClickPosition() {
        return mClickPosition;
    }

    /**
     * 对返回键进行监听
     *
     * @param keyCode
     * @param event
     * @return
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            exit();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    public void exit() {
        if ((System.currentTimeMillis() - mExitTime) > 2000) {
            Toast.makeText(mContext, "再按一次退出应用", Toast.LENGTH_SHORT).show();
            mExitTime = System.currentTimeMillis();
        } else {
            finish();
        }
    }

    private class OnItemClickListener extends OnRecyclerItemClickListener {

        public OnItemClickListener(RecyclerView recyclerView) {
            super(recyclerView);
        }

        @Override
        public void onItemClick(RecyclerView.ViewHolder vh) {
            mClickPosition = vh.getAdapterPosition();
            mPresenter.onItemClick();
        }
    }

}
