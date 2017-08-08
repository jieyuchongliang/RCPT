package com.rcpt.mvp.presenter;

import com.rcpt.LoginHelper;
import com.rcpt.base.mvp.BasePresenter;
import com.rcpt.base.mvp.OnDataGetCallback;
import com.rcpt.bean.JobInviteListBean;
import com.rcpt.mvp.contract.JobInviteContract;
import com.rcpt.mvp.module.JobInviteModule;
import com.rcpt.ui.recruit.RecruitJobInfoActivity;

import java.util.List;

/**
 * Created by lvzp on 2017/3/3.
 * 类描述：
 */

public class JobInvitePresenter extends BasePresenter<JobInviteContract.View> implements JobInviteContract.Presenter {

    private JobInviteModule mModule;

    @Override
    public void attach(JobInviteContract.View view) {
        super.attach(view);
        mModule = new JobInviteModule();
        getView().initRecyclerView();
    }

    @Override
    public void loadListData() {
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
        mModule.requestJobInviteList(getView().getContext(), LoginHelper.getInstance().getUserToken(), page, new OnDataGetCallback<List<JobInviteListBean.JobInviteBean>>() {
            @Override
            public void onSuccessResult(List<JobInviteListBean.JobInviteBean> data) {
                getView().updateIsEnd(mModule.isEnd());
                getView().bindListData(mModule.getListData());
            }
        });
    }

    @Override
    public void onItemClick() {
        int clickItemPosition = getView().getClickItemPosition();
        JobInviteListBean.JobInviteBean jobInviteBean = mModule.getListData().get(clickItemPosition);
        RecruitJobInfoActivity.actionStart(getView().getContext(), jobInviteBean.getCompanyId(), jobInviteBean.getRecruitmentInfoId());
    }
}
