package com.rcpt.mvp.presenter;

import com.rcpt.base.mvp.BasePresenter;
import com.rcpt.base.mvp.OnDataGetCallback;
import com.rcpt.bean.AttrSelectBean;
import com.rcpt.mvp.contract.JobGuideListContract;
import com.rcpt.mvp.module.JobGuideListModule;

import java.util.List;

/**
 * Created by lvzp on 2017/3/29.
 * 类描述：
 */

public class JobGuideListPresenter extends BasePresenter<JobGuideListContract.View> implements JobGuideListContract.Presenter {

    private JobGuideListModule mModule;

    @Override
    public void attach(JobGuideListContract.View view) {
        super.attach(view);
        mModule = new JobGuideListModule();
    }

    @Override
    public void initFragments() {
        mModule.requestJobGuideType(getView().getContext(), new OnDataGetCallback<List<AttrSelectBean>>() {
            @Override
            public void onSuccessResult(List<AttrSelectBean> data) {
                if (data != null) {
                    for (AttrSelectBean bean : data) {
                        mModule.buildFragmentAddList(bean.getDistinguishId());
                        mModule.buildTitleAddList(bean.getValue());
                    }
                    getView().bindPageData(mModule.getFragmentList(), mModule.getTitleList());
                }
            }
        });
    }
}
