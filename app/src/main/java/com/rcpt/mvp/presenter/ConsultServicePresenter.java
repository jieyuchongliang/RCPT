package com.rcpt.mvp.presenter;

import com.rcpt.base.mvp.BasePresenter;
import com.rcpt.base.mvp.OnDataGetCallback;
import com.rcpt.mvp.contract.ConsultServiceContract;
import com.rcpt.mvp.module.ConsultServiceModule;

/**
 * Created by lvzp on 2017/2/23.
 * 类描述：
 */

public class ConsultServicePresenter extends BasePresenter<ConsultServiceContract.View> implements ConsultServiceContract.Presenter {

    private ConsultServiceModule mModule;


    @Override
    public void attach(ConsultServiceContract.View view) {
        super.attach(view);
        mModule = new ConsultServiceModule();
    }

    @Override
    public void initFragments() {
        mModule.requestConsultMenu( getView().getContext(),new OnDataGetCallback<Void>() {
            @Override
            public void onSuccessResult(Void aVoid) {
                getView().bindTabFragment(mModule.getFragmentList(), mModule.getTitleList());
            }
        });
    }
}
