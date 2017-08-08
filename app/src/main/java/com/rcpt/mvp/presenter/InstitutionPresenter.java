package com.rcpt.mvp.presenter;

import android.support.v7.widget.RecyclerView;

import com.rcpt.base.mvp.BasePresenter;
import com.rcpt.base.mvp.OnDataGetCallback;
import com.rcpt.bean.InstituteBean;
import com.rcpt.mvp.contract.InstitutionContract;
import com.rcpt.mvp.module.InstitutionModule;
import com.rcpt.ui.home.activity.InstitutionInfoActivity;

import java.util.List;

/**
 * Created by 860116021 on 2017/4/13.
 */

public class InstitutionPresenter extends BasePresenter<InstitutionContract.View> implements InstitutionContract.Presenter{

    private InstitutionModule mModule;
    private String industry = "";
    private String institutionName = "";

    @Override
    public void attach(InstitutionContract.View view) {
        super.attach(view);
        mModule = new InstitutionModule();
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
        mModule.listData(getView().getContext(), page, industry, institutionName, new OnDataGetCallback<List<InstituteBean.InstitutionListBean>>() {


            @Override
            public void onSuccessResult(List<InstituteBean.InstitutionListBean> data) {
                getView().updateIsEnd(mModule.IsEnd());
                getView().bindListData(data);
            }
        });
    }

    @Override
    public void onItemClick(RecyclerView.ViewHolder vh, int position) {
        InstituteBean.InstitutionListBean bean = mModule.getListData().get(position);
        InstitutionInfoActivity.actionStartActivity(getView().getContext(),bean.getInstitutionId());
    }
}
