package com.rcpt.mvp.presenter;

import com.rcpt.Constant;
import com.rcpt.LoginHelper;
import com.rcpt.R;
import com.rcpt.base.mvp.BasePresenter;
import com.rcpt.base.mvp.OnDataGetCallback;
import com.rcpt.bean.BidInfoBean;
import com.rcpt.mvp.contract.BidInfoContract;
import com.rcpt.mvp.module.BidInfoModule;
import com.rcpt.ui.home.activity.BidSendActivity;
import com.rcpt.ui.login.LoginActivity;

/**
 * Created by lvzp on 2017/2/24.
 * 类描述：
 */

public class BidInfoPresenter extends BasePresenter<BidInfoContract.View> implements BidInfoContract.Presenter {

    private BidInfoBean bean;
    private BidInfoModule mModule;
    private String userType;
    //判断竞标是否成功的标记
    private static boolean isBidSuccess;

    @Override
    public void attach(BidInfoContract.View view) {
        super.attach(view);
        bean = new BidInfoBean();
        mModule = new BidInfoModule();
        userType = LoginHelper.getInstance().getUserBean().getUserType();
    }

    @Override
    public void loadInfo() {
        mModule.getBidInfo(getView().getContext(), getView().getBidId(), LoginHelper.getInstance().getUserToken(), new OnDataGetCallback<BidInfoBean>() {
            @Override
            public void onSuccessResult(BidInfoBean data) {
                bean = data;
                getView().setBean(data);
            }
        });
    }

    @Override
    public void onCloseClick() {
        getView().closeActivity();
    }

    @Override
    public void onClickBid() {

        if (!LoginHelper.getInstance().isOnline()) {
            getView().actionStartActivity(LoginActivity.class);
            getView().showToast(getView().getContext().getString(R.string.logo_message));
            return;
        }

        if (!isBidSuccess) {
            if (LoginHelper.getInstance().getUserToken().equals(mModule.getBidBean().getPublishCompanyId())) {
                getView().showToast("不能竞标自己发布的项目");
                return;
            }

            if (mModule.getBidBean().getCheck() == 1) {
                getView().showToast("该项目已竞标，不能重复竞标");
                return;
            }

            if (LoginHelper.getInstance().getUserBean().getUserType().equals(Constant.UserType.PERSON.getValue())) {
                //如果是个人用户
                getView().showToast("个人用户不能投标");
            } else {
                //非个人用户
                BidSendActivity.actionStart(getView().getContext(), bean.getProjectId(), bean.getPublishCompanyName(), BidSendActivity.FROM_WHERE_TAG_PROJECT);
            }
        } else {
            getView().showToast("该项目已竞标，不能重复竞标");
        }
    }

    @Override
    public void isBidSuccess(String result) {
        if ("竞标成功".equals(result)) {
            isBidSuccess = true;
        } else {
            isBidSuccess = false;
        }
    }

}
