package com.rcpt.mvp.presenter;

import com.rcpt.base.HttpResult;
import com.rcpt.base.mvp.BasePresenter;
import com.rcpt.bean.PolicyTypeBean;
import com.rcpt.http.ProgressSubscriber;
import com.rcpt.http.RequestImpl;
import com.rcpt.mvp.contract.NewsActivityContract;
import com.rcpt.mvp.module.HomeNewsModule;
import com.rcpt.mvp.module.RequestModule;

import java.util.List;

/**
 * Created by lvzp on 2017/2/23.
 * 类描述：
 */

public class NewsActivityPresenter extends BasePresenter<NewsActivityContract.View> implements NewsActivityContract.Presenter {

    private HomeNewsModule mModule;
    private RequestModule mRequestModule;


    @Override
    public void attach(NewsActivityContract.View view) {
        super.attach(view);
        mRequestModule = new RequestModule();
        mModule = new HomeNewsModule();
    }

    @Override
    public void initFragments() {
        mRequestModule.newsType(new ProgressSubscriber<HttpResult<List<PolicyTypeBean>>>( getView().getContext(),new RequestImpl<HttpResult<List<PolicyTypeBean>>>() {
            @Override
            public void onNext(HttpResult<List<PolicyTypeBean>> listHttpResult) {
                mModule.setPolicyTypeList(listHttpResult.getData());
                getView().bindTabFragment(mModule.getFragmentList(), mModule.getTitleList());
            }
        }));

    }
}
