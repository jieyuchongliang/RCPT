package com.rcpt.mvp.presenter;

import com.rcpt.base.HttpResult;
import com.rcpt.base.mvp.BasePresenter;
import com.rcpt.bean.PolicyTypeBean;
import com.rcpt.http.ProgressSubscriber;
import com.rcpt.http.RequestImpl;
import com.rcpt.mvp.contract.PolicyContract;
import com.rcpt.mvp.module.HomePolicyModule;
import com.rcpt.mvp.module.RequestModule;

import java.util.List;

/**
 * Created by lvzp on 2017/2/27.
 * 类描述：
 */

public class PolicyPresenter extends BasePresenter<PolicyContract.View> implements PolicyContract.Presenter {

    private HomePolicyModule mModule;
    private RequestModule mRequestModule;

    @Override
    public void attach(PolicyContract.View view) {
        super.attach(view);
        mModule = new HomePolicyModule();
        mRequestModule = new RequestModule();
    }


    @Override
    public void initFragments() {
        mRequestModule.policyType(new ProgressSubscriber<HttpResult<List<PolicyTypeBean>>>(getView().getContext(), new RequestImpl<HttpResult<List<PolicyTypeBean>>>() {
            @Override
            public void onNext(HttpResult<List<PolicyTypeBean>> listHttpResult) {
                mModule.setPolicyTypeList(listHttpResult.getData());
                getView().bindTabFragment(mModule.getFragmentList(), mModule.getTitleList());
            }
        }));

    }
}
