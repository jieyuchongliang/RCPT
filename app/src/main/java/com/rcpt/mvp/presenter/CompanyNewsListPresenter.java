package com.rcpt.mvp.presenter;

import android.support.v7.widget.RecyclerView;

import com.rcpt.LoginHelper;
import com.rcpt.base.mvp.BasePresenter;
import com.rcpt.base.mvp.OnDataGetCallback;
import com.rcpt.bean.CompanyNewsListBean;
import com.rcpt.mvp.contract.CompanyNewsListContract;
import com.rcpt.mvp.contract.HomeInfoContract;
import com.rcpt.mvp.module.CompanyNewsListModule;
import com.rcpt.ui.home.activity.HomeInfoActivity;

import java.util.List;

/**
 * Created by 860116021 on 2017/4/7.
 * 企业新闻
 */

public class CompanyNewsListPresenter extends BasePresenter<CompanyNewsListContract.View> implements CompanyNewsListContract.Presenter{

    private CompanyNewsListModule mModule;
    private String userId;

    @Override
    public void attach(CompanyNewsListContract.View view){
        super.attach(view);
        mModule = new CompanyNewsListModule();
        userId = LoginHelper.getInstance().getUserToken();
    }

    @Override
    public void onLoadMore(int page) {
        loadListData(page);
    }

    @Override
    public void loadListData() {
        getView().initRecyclerView();
        loadListData(1);
    }

    private void loadListData(int page){
        mModule.getList(getView().getContext(), userId, page, new OnDataGetCallback<List<CompanyNewsListBean.CompanyNewsBean>>() {


            @Override
            public void onSuccessResult(List<CompanyNewsListBean.CompanyNewsBean> data) {
                getView().updateIsEnd(mModule.isEnd());
                getView().bindListData(data);
            }
        });
    }

    @Override
    public void onItemClick(RecyclerView.ViewHolder vh, int position) {
        super.onItemClick(vh, position);
        CompanyNewsListBean.CompanyNewsBean bean = mModule.getListData().get(position);
        HomeInfoActivity.actionStart(getView().getContext(), bean.getId(), HomeInfoContract.INFO_TYPE_COMPANY_NEWS);
    }
}
