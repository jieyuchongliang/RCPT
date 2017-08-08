package com.rcpt.mvp.presenter;

import android.support.v7.widget.RecyclerView;

import com.rcpt.base.mvp.BasePresenter;
import com.rcpt.base.mvp.OnDataGetCallback;
import com.rcpt.bean.RecruitFragmentListBean;
import com.rcpt.mvp.contract.HomeInfoContract;
import com.rcpt.mvp.contract.JobGuideFragmentContract;
import com.rcpt.mvp.module.JobGuideFragmentModule;
import com.rcpt.ui.home.activity.HomeInfoActivity;

import java.util.List;

/**
 * Created by lvzp on 2017/3/29.
 * 类描述：
 */

public class JobGuideFragmentPresenter extends BasePresenter<JobGuideFragmentContract.View> implements JobGuideFragmentContract.Presenter {

    private JobGuideFragmentModule mModule;

    @Override
    public void attach(JobGuideFragmentContract.View view) {
        super.attach(view);
        mModule = new JobGuideFragmentModule();
        getView().initRecyclerView();
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

    @Override
    public void loadListData() {
        loadListData(1);
    }

    private void loadListData(int page) {
        mModule.requestJobGuideList(getView().getContext(), getView().getJobGuideTypeId(), page, new OnDataGetCallback<List<RecruitFragmentListBean.SubBean>>() {
            @Override
            public void onSuccessResult(List<RecruitFragmentListBean.SubBean> data) {
                getView().updateIsEnd(mModule.isEnd());
                getView().bindListData(data);
            }
        });
    }


    @Override
    public void onItemClick(RecyclerView.ViewHolder vh, int position) {
        HomeInfoActivity.actionStart(getView().getContext(), mModule.getJobGuideList().get(position).getId(), HomeInfoContract.INFO_TYPE_EMPLOYMENT);
    }
}
