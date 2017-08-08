package com.rcpt.mvp.presenter;

import android.text.TextUtils;

import com.rcpt.LoginHelper;
import com.rcpt.base.mvp.BasePresenter;
import com.rcpt.base.mvp.OnDataGetCallback;
import com.rcpt.mvp.contract.CompanyChangePasswordContract;
import com.rcpt.mvp.module.CompanyChangePasswordModule;
import com.rcpt.utils.RECheckUtils;

/**
 * Created by lvzp on 2017/4/18.
 * 类描述：
 */

public class CompanyChangePasswordPresenter extends BasePresenter<CompanyChangePasswordContract.View> implements CompanyChangePasswordContract.Presenter {
    private CompanyChangePasswordModule mModule;

    @Override
    public void attach(CompanyChangePasswordContract.View view) {
        super.attach(view);
        mModule = new CompanyChangePasswordModule();
    }

    /**
     * 点击保存
     */
    @Override
    public void onClickSave() {
        String oldPas = getView().getOldPassword();
        String newPas = getView().getNewPassword();
        String againPas = getView().getAgainPassword();
        if (TextUtils.isEmpty(oldPas) ) {
            getView().showToast("请输入当前密码");
            return;
        }

        if (TextUtils.isEmpty(newPas)) {
            getView().showToast("请输入新密码");
            return;
        } else if (RECheckUtils.checkPassword(newPas)==1) {
            getView().showToast("请输入符合规范的密码");
            return;
        }else if (RECheckUtils.checkPassword(newPas) == 2){
            getView().showToast("密码中不能包含中文字符");
            return;
        }
        if (TextUtils.isEmpty(againPas)) {
            getView().showToast("请再次输入密码");
            return;
        }
        if (!newPas.equals(againPas)) {
            getView().showToast("输入的新密码两次不相同");
            return;
        }
        mModule.changePassworkd(getView().getContext(), LoginHelper.getInstance().getUserToken(), oldPas, newPas, new OnDataGetCallback<String>() {
            @Override
            public void onSuccessResult(String data) {
                getView().showToast(data);
                getView().closeActivity();
            }
        });
    }
}
