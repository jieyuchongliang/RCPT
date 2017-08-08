package com.rcpt.mvp.presenter;

import com.rcpt.base.mvp.BasePresenter;
import com.rcpt.base.mvp.OnDataGetCallback;
import com.rcpt.bean.AttrSelectBean;
import com.rcpt.mvp.contract.EnterpriseListContract;
import com.rcpt.mvp.module.EnterpriseListModule;
import com.rcpt.mvp.module.CreateCVInfoMenuModule;

import java.util.List;

/**
 * Created by lvzp on 2017/2/27.
 * 类描述：
 */

public class EnterpriseListPresenter extends BasePresenter<EnterpriseListContract.View> implements EnterpriseListContract.Presenter {

    private EnterpriseListModule mModule;
    private CreateCVInfoMenuModule mInputModule;

    @Override
    public void attach(EnterpriseListContract.View view) {
        super.attach(view);
        mInputModule = new CreateCVInfoMenuModule();
        mModule = new EnterpriseListModule();
    }

    @Override
    public void initFragments() {
        mInputModule.requestCompanyTypeList(getView().getContext(), new OnDataGetCallback<List<AttrSelectBean>>() {
            @Override
            public void onSuccessResult(List<AttrSelectBean> data) {
                if (data != null) {
                    data.add(0,new AttrSelectBean("","全部"));
                    for (AttrSelectBean attrSelectBean : data) {
                        mModule.initFragment(attrSelectBean.getDistinguishId());
                        mModule.initFragmentTitle(attrSelectBean.getValue());
                    }
                    getView().bindTabFragment(mModule.getFragmentList(), mModule.getTitleList());
                }
            }
        });
    }
}
