package com.rcpt.mvp.presenter;

import com.rcpt.LoginHelper;
import com.rcpt.base.mvp.BasePresenter;
import com.rcpt.base.mvp.OnDataGetCallback;
import com.rcpt.bean.CompanyRecruitListBean;
import com.rcpt.mvp.contract.CompanyRecruitContract;
import com.rcpt.mvp.module.CompanyRecruitModule;
import com.rcpt.ui.me.company.CompanyRecruitActivity;

import java.util.List;

/**
 * Created by lvzp on 2017/4/17.
 * 类描述：
 */

public class CompanyRecruitPresenter extends BasePresenter<CompanyRecruitContract.View> implements CompanyRecruitContract.Presenter {

    private CompanyRecruitModule mModule;

    @Override
    public void attach(CompanyRecruitContract.View view) {
        super.attach(view);
        mModule = new CompanyRecruitModule();
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

    /**
     * 列表点击事件
     */
    @Override
    public void onItemClick() {
        int position = getView().getClickPosition();
        CompanyRecruitListBean.RecruitlistBean recruitlistBean = mModule.getListData().get(position);
        String fromWhere = getView().getFromWhere();
        switch (fromWhere) {
            case CompanyRecruitActivity.FROM_WHERE_TAG_CV_INFO:
                if (recruitlistBean.getInvitation() > 0) {
                    getView().showSnackbar("当前职位已邀请过该用户或该用户已向您投递过简历");
                    return;
                }
                getView().startGoSendJobInvitation(recruitlistBean, getView().getCVId());
                break;
            case CompanyRecruitActivity.FROM_WHERE_TAG_ME:
                getView().startGoRecruitInfo(recruitlistBean.getRecruitmentInfoId());
                break;
        }
    }


    private void loadListData(int page) {
        String cvUserId = null;
        String cvId = null;
        if (getView().getFromWhere().equals(CompanyRecruitActivity.FROM_WHERE_TAG_CV_INFO)) {
            cvUserId = getView().getCVUserId();
            cvId = getView().getCVId();
        }
        mModule.requestRecruitList(getView().getContext(), LoginHelper.getInstance().getUserToken(), page, cvUserId, cvId, new OnDataGetCallback<List<CompanyRecruitListBean.RecruitlistBean>>() {
            @Override
            public void onSuccessResult(List<CompanyRecruitListBean.RecruitlistBean> data) {
                getView().updateIsEnd(mModule.isEnd());
                getView().bindListData(data);
            }
        });
    }

}
