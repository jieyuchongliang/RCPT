package com.rcpt.mvp.presenter;

import android.content.Intent;

import com.rcpt.base.mvp.BasePresenter;
import com.rcpt.mvp.contract.PayStatusContract;
import com.rcpt.ui.home.video.VideoListActivity;
import com.rcpt.ui.main.activity.MainActivity;
import com.rcpt.ui.me.video.MyOrderActivity;

/**
 * Created by 860617003 on 2017/5/19.
 */

public class PayStatusPresenter extends BasePresenter<PayStatusContract.View> implements PayStatusContract.Presenter {

    @Override
    public void onClickBack() {
        if (!getView().isFromOrderList()) {
            getView().actionStartActivity(VideoListActivity.class);
        }
        getView().closeActivity();
    }

    @Override
    public void onClickGoOrderList() {
        if (!getView().isFromOrderList()) {
            Intent[] intents = new Intent[2];
            Intent intentMain = new Intent();
            Intent intentOrder = new Intent();
            intentMain.setClass(getView().getContext(), VideoListActivity.class);
            intentOrder.setClass(getView().getContext(), MyOrderActivity.class);
            intents[0] = intentMain;
            intents[1] = intentOrder;
            getView().getContext().startActivities(intents);
        }
        getView().closeActivity();
    }
}
