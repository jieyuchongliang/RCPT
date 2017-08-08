package com.rcpt.mvp.presenter;

import android.text.TextUtils;

import com.rcpt.LoginHelper;
import com.rcpt.base.mvp.BasePresenter;
import com.rcpt.base.mvp.OnDataGetCallback;
import com.rcpt.mvp.contract.PushManageContract;
import com.rcpt.mvp.module.PushManageModule;

/**
 * Created by 860617003 on 2017/6/2.
 */

public class PushManagePresenter extends BasePresenter<PushManageContract.View> implements PushManageContract.Presenter {

    private PushManageModule mModule;


    @Override
    public void attach(PushManageContract.View view) {
        super.attach(view);
        mModule = new PushManageModule();
    }

    /**
     * 点击发布消息
     */
    @Override
    public void onClickSendPush() {
        String userType = getView().getUserType();
        String message = getView().getMessage();
        String title = getView().getPushTitle();
        if (TextUtils.isEmpty(title)) {
            getView().showToast("请输入标题");
            return;
        }
        if (TextUtils.isEmpty(message)) {
            getView().showToast("请输入推送内容");
            return;
        }
        mModule.publishMessage(getView().getContext(), userType, title, message, LoginHelper.getInstance().getUserToken(), new OnDataGetCallback<String>() {
            @Override
            public void onSuccessResult(String data) {
                getView().showToast(data);
                getView().closeActivityForOk();
            }
        });
    }
}
