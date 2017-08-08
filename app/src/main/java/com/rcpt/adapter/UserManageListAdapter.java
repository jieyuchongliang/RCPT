package com.rcpt.adapter;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Build;
import android.view.View;

import com.rcpt.R;
import com.rcpt.base.adapter.BindingBaseRecycleAdapter;
import com.rcpt.base.adapter.BindingViewHolder;
import com.rcpt.bean.UserManageListBean;
import com.rcpt.databinding.ItemLayoutSystemUserManageListBinding;
import com.rcpt.mvp.contract.UserManageFragmentContract;
import com.rcpt.utils.AnimationUtils;
import com.rcpt.utils.DialogUtils;

/**
 * 平台管理会员管理的列表Adapter
 */

public class UserManageListAdapter extends BindingBaseRecycleAdapter<UserManageListBean.UserInfoBean, ItemLayoutSystemUserManageListBinding> {

    private UserManageFragmentContract.Presenter mPresenter;
    private Context mContext;
    private int mClickItemPosition;

    public UserManageListAdapter(Context context, UserManageFragmentContract.Presenter presenter) {
        super(R.layout.item_layout_system_user_manage_list);
        mContext = context;
        mPresenter = presenter;
    }

    @Override
    protected void bindingViews(BindingViewHolder<ItemLayoutSystemUserManageListBinding> holder, final int position, final UserManageListBean.UserInfoBean userInfoBean) {
        final ItemLayoutSystemUserManageListBinding binding = holder.getBinding();
        binding.setUserBean(userInfoBean);
       /* if (userInfoBean.getUser_type() == 1) {
            binding.expandPersonLayout.initExpand(userInfoBean.isExpand());
        } else {
            binding.expandGroupLayout.initExpand(userInfoBean.isExpand());
        }*/
     /*   //点击隐藏提示的内容并显示详细信息
        binding.ivAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mClickItemPosition = position;
                mDataBinding = binding;
                mPresenter.onClickExpandLayout();
            }
        });
        //点击隐藏提示的内容并显示详细信息
        binding.tvHint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mClickItemPosition = position;
                mDataBinding = binding;
                mPresenter.onClickExpandLayout();
            }
        });*/
        //重置密码
        binding.tvReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mClickItemPosition = position;
                mPresenter.onClickRestPassword();
            }
        });
        //审核通过
        binding.tvAdopt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mClickItemPosition = position;
                DialogUtils.showSimpleDialog(mContext, "您是否要审核通过该用户", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mPresenter.onClickAdopt();
                    }
                });
            }
        });
        //删除用户
        binding.tvDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mClickItemPosition = position;
                DialogUtils.showSimpleDialog(mContext, "您是否要删除该用户", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mPresenter.onClickDelUser();
                    }
                });
            }
        });
        //锁定用户（或者是解锁用户），当为个人用户的时候，根据传入的内容，直接修改
        binding.tvLocked.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mClickItemPosition = position;
                if (userInfoBean.getUser_status() == 2) {
                    DialogUtils.showSimpleDialog(mContext, "您是否要锁定该用户", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            mPresenter.onClickLocked();
                        }
                    });
                } else {
                    DialogUtils.showSimpleDialog(mContext, "您是否要解锁该用户", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            mPresenter.onClickUnlocked();
                        }
                    });
                }

            }
        });
        //审核驳回
        binding.tvFail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mClickItemPosition = position;
                DialogUtils.showSimpleDialog(mContext, "您是否要驳回该用户", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mPresenter.onClickFailUser();
                    }
                });
            }
        });
    }

/*    public void updateLayoutExpand(boolean isPerson) {
        if (isPerson)
            mDataBinding.expandPersonLayout.expand();
        else
            mDataBinding.expandGroupLayout.expand();
        mDataBinding.tvHint.setVisibility(View.GONE);
        AnimationUtils.rotationExpandIcon(mDataBinding.ivAdd, 0, 45);
    }

    public void updateLayoutCollapse(boolean isPerson) {
        if (isPerson)
            mDataBinding.expandPersonLayout.collapse();
        else
            mDataBinding.expandGroupLayout.collapse();
        mDataBinding.tvHint.setVisibility(View.VISIBLE);
        AnimationUtils.rotationExpandIcon(mDataBinding.ivAdd, 45, 0);
    }*/

    public int getClickItemPosition() {
        return mClickItemPosition;
    }

}
