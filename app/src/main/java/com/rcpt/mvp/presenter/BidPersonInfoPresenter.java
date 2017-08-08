package com.rcpt.mvp.presenter;

import com.rcpt.Constant;
import com.rcpt.LoginHelper;
import com.rcpt.R;
import com.rcpt.base.mvp.BasePresenter;
import com.rcpt.base.mvp.OnDataGetCallback;
import com.rcpt.bean.BidPersonInfoBean;
import com.rcpt.mvp.contract.BidPersonInfoContract;
import com.rcpt.mvp.module.BidPersonInfoModule;
import com.rcpt.ui.home.activity.BidSendActivity;
import com.rcpt.ui.login.LoginActivity;

/**
 * Created by 860116021 on 2017/4/19.
 */

public class BidPersonInfoPresenter extends BasePresenter<BidPersonInfoContract.View> implements BidPersonInfoContract.Presenter {

    private BidPersonInfoBean bean;
    private BidPersonInfoModule mModule;
    private String userType;
    //判断竞标是否成功的标记
    private static boolean isBidSuccess;

    @Override
    public void attach(BidPersonInfoContract.View view) {
        super.attach(view);
        bean = new BidPersonInfoBean();
        mModule = new BidPersonInfoModule();
        userType = LoginHelper.getInstance().getUserBean().getUserType();
    }

    @Override
    public void loadPersonInfo() {
        mModule.getBidPersonInfo(getView().getContext(), getView().getBidPersonId(), LoginHelper.getInstance().getUserToken(), new OnDataGetCallback<BidPersonInfoBean>() {
            @Override
            public void onSuccessResult(BidPersonInfoBean data) {
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
            if (LoginHelper.getInstance().getUserToken().equals(mModule.getBidInfoBean().getPublishCompanyId())) {
                getView().showToast("不能竞标自己发布的项目");
                return;
            }
            if (mModule.getBidInfoBean().getCheck() == 1) {
                getView().showToast("该项目已竞标，不能重复竞标");
                return;
            }
            if (LoginHelper.getInstance().getUserBean().getUserType().equals(Constant.UserType.PERSON.getValue())) {
                //如果是个人用户
                getView().showToast("个人用户不能投标");
            } else {
                //非个人用户
                BidSendActivity.actionStart(getView().getContext(), bean.getPersonnelId(), bean.getPublishCompanyName(), BidSendActivity.FROM_WHERE_TAG_PERSON);
            }
        } else {
            getView().showToast("该项目已竞标，不能重复竞标");
            return;
        }

    }

    /**
     * 竞标是否成功的回调
     *
     * @param result
     */
    @Override
    public void isBidSuccess(String result) {
        if ("竞标成功".equals(result)) {
            isBidSuccess = true;
        } else {
            isBidSuccess = false;
        }
    }
}
