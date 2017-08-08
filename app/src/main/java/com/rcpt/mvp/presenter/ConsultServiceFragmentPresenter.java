package com.rcpt.mvp.presenter;

import android.support.v7.widget.RecyclerView;

import com.rcpt.base.mvp.BasePresenter;
import com.rcpt.base.mvp.OnDataGetCallback;
import com.rcpt.bean.ConsultServiceListBean;
import com.rcpt.mvp.contract.ConsultServiceFragmentContract;
import com.rcpt.mvp.contract.HomeInfoContract;
import com.rcpt.mvp.module.ConsultServiceListModule;
import com.rcpt.ui.home.activity.HomeInfoActivity;

import java.util.List;

/**
 * Created by lvzp on 2017/2/23.
 * 类描述：
 */

public class ConsultServiceFragmentPresenter extends BasePresenter<ConsultServiceFragmentContract.View> implements ConsultServiceFragmentContract.Presenter {

    private ConsultServiceListModule mServiceListModule;

    @Override
    public void attach(ConsultServiceFragmentContract.View view) {
        super.attach(view);
        mServiceListModule = new ConsultServiceListModule();
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
        mServiceListModule.requestConsultServiceList(getView().getServiceId(), page, new OnDataGetCallback<List<ConsultServiceListBean.ConsultancyListBean>>() {
            @Override
            public void onSuccessResult(List<ConsultServiceListBean.ConsultancyListBean> consultServiceListBeen) {
                getView().updateIsEnd(mServiceListModule.isEnd());
                getView().bindListData(consultServiceListBeen);
            }
        });
    }

    @Override
    public void onItemClick(RecyclerView.ViewHolder vh, int position) {
        ConsultServiceListBean.ConsultancyListBean bean = mServiceListModule.getConsultServiceList().get(position);
        HomeInfoActivity.actionStart(getView().getContext(), bean.getId(), HomeInfoContract.INFO_TYPE_CONSULT_SERVICE);
    }

}
