package com.rcpt.mvp.presenter;

import com.rcpt.base.mvp.BasePresenter;
import com.rcpt.mvp.contract.BidRecordChildContract;
import com.rcpt.mvp.contract.BidRecordContract;
import com.rcpt.mvp.module.BidRecordChildModule;

/**
 * Created by 860617003 on 2017/5/25.
 */

public class BidRecordChildPresenter extends BasePresenter<BidRecordChildContract.View> implements BidRecordChildContract.Presenter {

    private BidRecordChildModule mModule;

    @Override
    public void attach(BidRecordChildContract.View view) {
        super.attach(view);
        mModule = new BidRecordChildModule();
    }

    @Override
    public void initFragments() {
        getView().bindTabFragment(mModule.getFragmentList(getView().getBidType()), mModule.getTitleList());
    }
}
