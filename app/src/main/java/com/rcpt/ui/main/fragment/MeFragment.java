package com.rcpt.ui.main.fragment;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.view.menu.MenuItemImpl;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.lzp.statusbar.StatusBarCompat;
import com.rcpt.Constant;
import com.rcpt.LoginHelper;
import com.rcpt.R;
import com.rcpt.adapter.MeMenuAdapter;
import com.rcpt.base.ui.LazyFragment;
import com.rcpt.bean.MenuBean;
import com.rcpt.bean.User;
import com.rcpt.databinding.FragmentMeBinding;
import com.rcpt.http.api.ApiService;
import com.rcpt.mvp.contract.MeContract;
import com.rcpt.mvp.presenter.MePresenter;
import com.rcpt.recycler_listener.OnRecyclerItemClickListener;
import com.rcpt.utils.GlideCircleTransform;
import com.rcpt.utils.MenuBuildUtils;
import com.rcpt.utils.SpaceItemDecoration;

import java.util.List;


/**
 * 个人的Fragment
 */
public class MeFragment extends LazyFragment<FragmentMeBinding, MeContract.View, MePresenter>
        implements MeContract.View {

    private MeMenuAdapter mMenuAdapter;
    private LocalBroadcastManager mBraoadcastManager;
    private BroadcastReceiver broadcastReceiver;

    public static MeFragment newInstance() {
        MeFragment fragment = new MeFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_me;
    }

    @Override
    protected void initViews() {
        mBraoadcastManager = LocalBroadcastManager.getInstance(mContext);
        IntentFilter filter = new IntentFilter();
        filter.addAction(Constant.LOCAL_BROADCAST_USER_AVATAR_FLAG);
        filter.addAction(Constant.LOCAL_BROADCAST_USER_NAME_FLAG);

        broadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                String action = intent.getAction();
                User userBean = LoginHelper.getInstance().getUserBean();
                switch (action) {
                    case Constant.LOCAL_BROADCAST_USER_AVATAR_FLAG:
                        String avatarPath = userBean.getAvatar();
                        Glide.with(MeFragment.this)
                                .load(ApiService.IMAGE_BASE + avatarPath)
                                .error(R.drawable.ic_vector_default_avatar)
                                .bitmapTransform(new GlideCircleTransform(mContext))
                                .into(mDataBinding.ivAvatar);
                        break;
                    case Constant.LOCAL_BROADCAST_USER_NAME_FLAG:
                        String name = userBean.getName();
                        mDataBinding.tvUserName.setText(name);
                        break;
                }

            }
        };
        mBraoadcastManager.registerReceiver(broadcastReceiver, filter);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mBraoadcastManager.unregisterReceiver(broadcastReceiver);
    }

    @Override
    protected MePresenter createPresenter() {
        return new MePresenter();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    protected void loadData() {
        String userType = LoginHelper.getInstance().getUserBean().getUserType();
        String title;
        if (Constant.UserType.ENTERPRISE.getValue().equals(userType)) {
            title = "控制台";
        } else {
            title = "我的";
        }
        setTitleText(title);
        StatusBarCompat.setStatusBarColor(getActivity(), mTitleCompatLayout, true);
        mPresenter.initMenu();
        mDataBinding.setPresenter(mPresenter);
    }

    @Override
    public List<MenuItemImpl> getMenuList() {
        User user = LoginHelper.getInstance().getUserBean();
        mDataBinding.setUser(user);
        if (user != null) {
            //如果是企业用户登录进入相应的样式
            if (Constant.UserType.ENTERPRISE.getValue().equals(user.getUserType()) || Constant.UserType.OTHER.getValue().equals(user.getUserType())) {
                // new SupportMenuInflater(mContext).inflate(R.menu.me_company_menu, menu);
                mDataBinding.llUserInfo.setBackgroundColor(Color.WHITE);
                mDataBinding.tvUserName.setTextColor(Color.BLACK);
                mDataBinding.llSpace.setVisibility(View.VISIBLE);
                mDataBinding.ivGo.setImageResource(R.drawable.ic_vector_go_gray);
                return MenuBuildUtils.buildMenuItemList(mContext, R.menu.me_company_menu);
            } else {
                //new SupportMenuInflater(mContext).inflate(R.menu.me_person_menu, menu);
                return MenuBuildUtils.buildMenuItemList(mContext, R.menu.me_person_menu);
            }
        }
        return null;
    }

    @Override
    public void initRecyclerView() {
        mDataBinding.recyclerView.setLayoutManager(new LinearLayoutManager(mContext) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        });
        mDataBinding.recyclerView.addOnItemTouchListener(new OnItemClickListener(getRecyclerView()));
        mMenuAdapter = new MeMenuAdapter(R.layout.item_layout_me_menu);
        mDataBinding.recyclerView.setAdapter(mMenuAdapter);
    }

    @Override
    public void bindListData(List<MenuBean> beanList) {
        Log.d("----", "------设置进来了数据-----" + beanList.size());
        mDataBinding.recyclerView.addItemDecoration(new SpaceItemDecoration(mContext, beanList));
        mMenuAdapter.setupData(beanList);
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
