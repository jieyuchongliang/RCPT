package com.rcpt.mvp.presenter;

import android.text.TextUtils;

import com.rcpt.LoginHelper;
import com.rcpt.base.mvp.BasePresenter;
import com.rcpt.base.mvp.OnDataGetCallback;
import com.rcpt.bean.BidContactsBean;
import com.rcpt.http.api.ApiClient;
import com.rcpt.http.bean.RequestBidSendBean;
import com.rcpt.http.bean.RequestPersonBidSendBean;
import com.rcpt.mvp.contract.BidInfoContract;
import com.rcpt.mvp.contract.BidPersonInfoContract;
import com.rcpt.mvp.contract.BidSendContract;
import com.rcpt.mvp.module.BidSendModule;
import com.rcpt.ui.home.activity.BidSendActivity;
import com.rcpt.utils.RECheckUtils;

import java.util.Map;

/**
 * Created by lvzp on 2017/4/19.
 * 类描述：
 */

public class BidSendPresenter extends BasePresenter<BidSendContract.View> implements BidSendContract.Presenter {

    private BidSendModule mModule;
    private BidInfoContract.Presenter mBidInfoContract;
    private BidPersonInfoContract.Presenter mBidPersonInfoContract;
    @Override
    public void attach(BidSendContract.View view) {
        super.attach(view);
        mModule = new BidSendModule();
        mBidInfoContract = new BidInfoPresenter();
        mBidPersonInfoContract= new BidPersonInfoPresenter();
    }

    /**
     * 加载页面的数据
     */
    @Override
    public void loadPageData() {
        mModule.requestSendBidContacts(getView().getContext(), LoginHelper.getInstance().getUserToken(), new OnDataGetCallback<BidContactsBean>() {
            @Override
            public void onSuccessResult(BidContactsBean data) {
                getView().bindPageData(data);
            }
        });
    }

    /**
     * 点击投标
     */
    @Override
    public void onClickSendBid() {
        String name = getView().getBidContacts();
        String phone = getView().getBidPhone();
        String email = getView().getBidEmail();
        String content = getView().getBidContent();
        if (TextUtils.isEmpty(name) || TextUtils.isEmpty(phone) || TextUtils.isEmpty(email)) {
            getView().showToast("请将信息填写完整");
            return;
        }
        if (!RECheckUtils.checkPersionEmail(email)){
            getView().showToast("邮箱格式不合法");
            return;
        }
        if (!RECheckUtils.checkPhoneNum(phone)){
            getView().showToast("电话格式不合法");
            return;
        }
        String bidId = getView().getBidId();
        String bidCompanyName = getView().getBidCompanyName();


        String fromWhere = getView().getFromWhere();
        switch (fromWhere) {
            case BidSendActivity.FROM_WHERE_TAG_PERSON:
                RequestPersonBidSendBean personBidSendBean = new RequestPersonBidSendBean();
                personBidSendBean.setBidCompanyName(bidCompanyName);
                personBidSendBean.setCompanyId(LoginHelper.getInstance().getUserToken());
                personBidSendBean.setEmail(email);
                personBidSendBean.setPhone(phone);
                personBidSendBean.setIntroduction(content);
                personBidSendBean.setName(name);
                personBidSendBean.setPersonnelId(bidId);
                Map<String, String> param = ApiClient.createBodyMap(personBidSendBean);
                mModule.sendPreson(getView().getContext(), param, new OnDataGetCallback<String>() {
                    @Override
                    public void onSuccessResult(String data) {
                        mBidPersonInfoContract.isBidSuccess(data);
                        getView().showToast(data);
                        getView().closeActivity();
                    }
                });
                break;
            case BidSendActivity.FROM_WHERE_TAG_PROJECT:
                RequestBidSendBean bean = new RequestBidSendBean();
                bean.setBidCompanyName(bidCompanyName);
                bean.setCompanyId(LoginHelper.getInstance().getUserToken());
                bean.setEmail(email);
                bean.setPhone(phone);
                bean.setName(name);
                bean.setProjectId(bidId);
                bean.setIntroduction(content);
                Map<String, String> params = ApiClient.createBodyMap(bean);
                mModule.sendProject(getView().getContext(), params, new OnDataGetCallback<String>() {
                    @Override
                    public void onSuccessResult(String data) {
                        mBidInfoContract.isBidSuccess(data);
                        getView().showToast(data);
                        getView().closeActivity();
                    }
                });
                break;
        }
    }
}
