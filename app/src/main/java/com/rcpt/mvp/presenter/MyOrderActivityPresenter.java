package com.rcpt.mvp.presenter;

import com.rcpt.base.mvp.BasePresenter;
import com.rcpt.mvp.contract.MyOrderActivityContract;
import com.rcpt.mvp.module.MyOrderModule;

/**
 * Created by 860116021 on 2017/5/10.
 */

public class MyOrderActivityPresenter extends BasePresenter<MyOrderActivityContract.View> implements MyOrderActivityContract.Presenter {

    private MyOrderModule mModule;

    @Override
    public void initFragments() {
        mModule.initTab();
        getView().bindTabFragment(mModule.getFragmentList(), mModule.getTitleList());
    }

    @Override
    public void attach(MyOrderActivityContract.View view) {
        super.attach(view);
        mModule = new MyOrderModule();
    }
}
