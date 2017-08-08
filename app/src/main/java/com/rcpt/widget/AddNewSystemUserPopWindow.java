package com.rcpt.widget;

import android.app.Activity;
import android.databinding.DataBindingUtil;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.rcpt.R;
import com.rcpt.base.mvp.OnDataGetCallback;
import com.rcpt.bean.SystemUserInfoBean;
import com.rcpt.databinding.LayoutPopAddNewSystemUserBinding;

/**
 * Created by 860617003 on 2017/6/7.
 */

public class AddNewSystemUserPopWindow extends TextTitlePopWindow {

    private LayoutPopAddNewSystemUserBinding mDataBinding;
    private SystemUserInfoBean mUpdateUserBean;
    private boolean isEdit;

    /**
     * 初始化一个PopupWindow
     *
     * @param context 上下文对象
     */
    public AddNewSystemUserPopWindow(final Activity context) {
        super(context, R.layout.layout_pop_add_new_system_user);
        mDataBinding = DataBindingUtil.bind(getWindowRootView());
        setAnimationStyle(android.R.style.Animation_Dialog);
        mRightView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isEdit) {
                    String password = mDataBinding.editPassword.getText().toString();
                    String againPassword = mDataBinding.editAgainPassword.getText().toString();
                    if (TextUtils.isEmpty(password)) {
                        Toast.makeText(context, "请输入密码", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    if (!password.equals(againPassword)) {
                        Toast.makeText(context, "两次密码输入的不一致", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    mUpdateUserBean.setPASSWORD(password);
                }
                mDataBinding.getPresenter().uploadSystemUser(new OnDataGetCallback<Boolean>() {
                    @Override
                    public void onSuccessResult(Boolean data) {
                        if (data) {
                            dismiss();
                            mDataBinding.getPresenter().loadListDataWithClean();
                        }
                    }
                });
            }
        });
    }

    public LayoutPopAddNewSystemUserBinding getPopDataBinding() {
        return mDataBinding;
    }

    public SystemUserInfoBean getUpdateUserBean() {
        return mUpdateUserBean;
    }

    public boolean isEdit() {
        return isEdit;
    }

    public void bindUserBean(SystemUserInfoBean bean, boolean isEdit) {
        this.isEdit = isEdit;
        if (bean == null) {
            mUpdateUserBean = new SystemUserInfoBean();
        } else {
            mUpdateUserBean = bean;
        }
        mDataBinding.setIsEdit(this.isEdit);
        mDataBinding.setUserBean(mUpdateUserBean);
    }

}
