package com.rcpt.mvp.presenter;

import com.rcpt.base.mvp.BasePresenter;
import com.rcpt.mvp.contract.CompanyAccountManageContract;
import com.rcpt.ui.me.company.CompanyChangePasswordActivity;

/**
 * Created by lvzp on 2017/4/18.
 * 类描述：
 */

public class CompanyAccountManagePresenter extends BasePresenter<CompanyAccountManageContract.View> implements CompanyAccountManageContract.Presenter {

    /**
     * 点击修改密码
     */
    @Override
    public void onClickChangePassword() {
        getView().actionStartActivity(CompanyChangePasswordActivity.class);
    }
}
