package com.rcpt.ui.system.fragment;


import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.liaoinstan.springview.container.DefaultFooter;
import com.liaoinstan.springview.container.DefaultHeader;
import com.liaoinstan.springview.widget.SpringView;
import com.lzp.statusbar.utils.SystemUtils;
import com.rcpt.R;
import com.rcpt.adapter.UserManageListAdapter;
import com.rcpt.base.ui.LazyFragment;
import com.rcpt.bean.UserManageFilterBean;
import com.rcpt.bean.UserManageListBean;
import com.rcpt.databinding.FragmentUserManageBinding;
import com.rcpt.databinding.LayoutDialogInputPasswordBinding;
import com.rcpt.mvp.contract.UserManageFragmentContract;
import com.rcpt.mvp.presenter.UserManageFragmentPresenter;
import com.rcpt.ui.system.UserManageActivity;
import com.rcpt.ui.system.UserManageFilterActivity;
import com.rcpt.utils.DialogUtils;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link UserManageFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class UserManageFragment extends LazyFragment<FragmentUserManageBinding, UserManageFragmentContract.View, UserManageFragmentPresenter>
        implements UserManageFragmentContract.View, SpringView.OnFreshListener {

    public static final String PERSON_USER = "person_user";//个人用户
    public static final String GROUP_USER = "group_user";//团体用户

    private static final String USER_TYPE = "user_type";

    private String mUserType;
    private boolean isEnd;
    private boolean isPerson;

    private UserManageListAdapter mAdapter;
    private UserManageFilterBean mFilterBean;

    public static UserManageFragment newInstance(String userType) {
        UserManageFragment fragment = new UserManageFragment();
        Bundle args = new Bundle();
        args.putString(USER_TYPE, userType);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mUserType = getArguments().getString(USER_TYPE);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return super.onCreateView(inflater, container, savedInstanceState);
    }


    @Override
    public void updateIsEnd(boolean isEnd) {
        this.isEnd = isEnd;
    }

    /**
     * 是否为个人的列表
     *
     * @return
     */
    @Override
    public boolean isPerson() {
        return isPerson;
    }

    /**
     * 删除适配器中的某个条目
     *
     * @param position
     */
    @Override
    public void removeAdapterItem(int position) {
        mAdapter.notifyItemRemoved(position);
    }

    /**
     * 获取用户的类型
     *
     * @return
     */
    @Override
    public String getUserType() {
        return mUserType;
    }

    @Override
    public void initRecyclerView() {
        mDataBinding.springView.setHeader(new DefaultHeader(mContext));
        mDataBinding.springView.setFooter(new DefaultFooter(mContext));
        mDataBinding.springView.setListener(this);
        mDataBinding.recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mAdapter = new UserManageListAdapter(mContext, mPresenter);
        mDataBinding.recyclerView.setAdapter(mAdapter);
        mDataBinding.recyclerView.setRefrshView(mDataBinding.springView);
        mDataBinding.recyclerView.setEmptyView(mDataBinding.getRoot().findViewById(R.id.empty_view));
    }

    @Override
    public void updateLayoutCollapse() {
       /* mAdapter.updateLayoutCollapse(isPerson);*/
    }

    @Override
    public void updateLayoutExpand() {
       /* mAdapter.updateLayoutExpand(isPerson);*/
    }

    /**
     * 更新适配器的对应Position的Item
     *
     * @param position
     */
    @Override
    public void notifyAdapterItem(int position) {
        mAdapter.notifyItemChanged(position);
    }

    /**
     * 获取点击条目的位置
     *
     * @return
     */
    @Override
    public int getClickItemPosition() {
        return mAdapter.getClickItemPosition();
    }

    @Override
    public void bindListData(List<UserManageListBean.UserInfoBean> beanList) {
        setListData(beanList);
        mAdapter.setupData(beanList);
    }

    @Override
    public RecyclerView getRecyclerView() {
        return mDataBinding.recyclerView;
    }

    @Override
    protected ViewGroup getRefreshView() {
        return mDataBinding.springView;
    }

    @Override
    protected void loadData() {
        mPresenter.loadListData();
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_user_manage;
    }

    @Override
    protected void initViews() {
        isPerson = PERSON_USER.equals(getUserType());
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
    protected void loadListData(int page) {
        mPresenter.onLoadMore(page);
    }

    /**
     * 显示输入密码的提示框
     */
    @Override
    public void showInputPasswordDialog() {
        View view = LayoutInflater.from(mContext).inflate(R.layout.layout_dialog_input_password, null);
        final LayoutDialogInputPasswordBinding binding = DataBindingUtil.bind(view);
        DialogUtils.buildAlertDialogWithCancel(mContext, "请输入您要修改的密码", "")
                .setView(view)
                .setPositiveButton("确认", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String s = binding.editPassword.getText().toString();
                        mPresenter.onInputPasswordCallback(s);
                    }
                }).show();
    }

    @Override
    protected UserManageFragmentPresenter createPresenter() {
        return new UserManageFragmentPresenter();
    }

    /**
     * 获取用于封装用户筛选的条件的实体类
     *
     * @return
     */
    @Override
    public UserManageFilterBean getFilterBean() {
        return mFilterBean;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            switch (requestCode) {
                case UserManageActivity.USER_FILTER_REQUEST_CODE:
                    mFilterBean = data.getParcelableExtra(UserManageFilterActivity.RESULT_BEAN);
                    mDataBinding.recyclerView.scrollToPosition(0);
                    onRefresh();
                    break;
            }
        }
    }
}
