package com.rcpt.mvp.presenter;

import android.support.v7.widget.RecyclerView;

import com.rcpt.base.mvp.BasePresenter;
import com.rcpt.base.mvp.OnDataGetCallback;
import com.rcpt.bean.PolicyListBean;
import com.rcpt.mvp.contract.HomeInfoContract;
import com.rcpt.mvp.contract.PolicyListContract;
import com.rcpt.mvp.module.PolicyListModule;
import com.rcpt.ui.home.activity.HomeInfoActivity;

import java.util.List;

/**
 * Created by lvzp on 2017/2/27.
 * 类描述：政策法规的控制器
 */

public class PolicyListPresenter extends BasePresenter<PolicyListContract.View> implements PolicyListContract.Presenter {

    private PolicyListModule mPolicyListModule;


    @Override
    public void attach(PolicyListContract.View view) {
        super.attach(view);
        mPolicyListModule = new PolicyListModule();
        createItemClickListener(getView().getRecyclerView());

    }

    @Override
    public void loadListData() {
        getView().initRecyclerView();
        loadListData(1);
    }

    /**
     * 加载更多的数据
     * 只需要根据相应的页码加载相应的数据，无需关心刷新和加载更多
     *
     * @param page
     */
    @Override
    public void onLoadMore(int page) {
        loadListData(page);
    }

    private void loadListData(int page) {
        mPolicyListModule.requestPolicyList(getView().getPolicyId(), page, new OnDataGetCallback<List<PolicyListBean.PolicylistBean>>() {
            @Override
            public void onSuccessResult(List<PolicyListBean.PolicylistBean> policylistBeen) {
                getView().updateIsEnd(mPolicyListModule.isEnd());
                getView().bindListData(policylistBeen);
            }
        });
    }

    @Override
    public void onItemClick(RecyclerView.ViewHolder vh, int position) {
        String policiesId = mPolicyListModule.getPolicyList().get(position).getPoliciesId();
        HomeInfoActivity.actionStart(getView().getContext(), policiesId, HomeInfoContract.INFO_TYPE_POLICY);
    }

}
