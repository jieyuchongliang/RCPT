package com.rcpt.mvp.presenter;

import com.rcpt.base.mvp.BasePresenter;
import com.rcpt.base.mvp.OnDataCallback;
import com.rcpt.bean.RegisterClauseBean;
import com.rcpt.mvp.contract.RegisterClauseContract;
import com.rcpt.mvp.module.RegisterClauseModule;

/**
 * Created by lvzp on 2017/4/25.
 * 类描述：
 */

public class RegisterClausePresenter extends BasePresenter<RegisterClauseContract.View> implements RegisterClauseContract.Presenter {

    private RegisterClauseModule mModule;

    @Override
    public void attach(RegisterClauseContract.View view) {
        super.attach(view);
        mModule = new RegisterClauseModule();
    }

    @Override
    public void loadPageData() {
        getView().showLoadingProgress();
        mModule.requestRegisterClause(getView().getContext(), new OnDataCallback<RegisterClauseBean>() {
            @Override
            public void onSuccessResult(RegisterClauseBean data) {
                getView().bindWebData(data);
            }

            @Override
            public void onError(String errMsg) {
                getView().showToast(errMsg);
                getView().hintLoadingProgress();
            }
        });
    }
}
