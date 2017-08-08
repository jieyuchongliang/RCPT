package com.rcpt.mvp.presenter;

import com.rcpt.adapter.RecruitFragmentAdapter;
import com.rcpt.base.mvp.BasePresenter;
import com.rcpt.base.mvp.OnDataGetCallback;
import com.rcpt.bean.RecruitFragmentListBean;
import com.rcpt.mvp.contract.HomeInfoContract;
import com.rcpt.mvp.contract.RecruitContract;
import com.rcpt.mvp.module.RecruitFragmentModule;
import com.rcpt.ui.home.activity.HomeInfoActivity;
import com.rcpt.ui.recruit.JobGuideListActivity;
import com.rcpt.ui.recruit.RecruitJobListActivity;
import com.rcpt.ui.recruit.SchoolInfoActivity;
import com.rcpt.ui.recruit.SchoolListActivity;

import java.util.List;

/**
 * Created by lvzp on 2017/2/24.
 * 首页中招聘Tab的控制器
 */

public class RecruitPresenter extends BasePresenter<RecruitContract.View> implements RecruitContract.Presenter {

    private RecruitFragmentModule mModule;

    @Override
    public void attach(RecruitContract.View view) {
        super.attach(view);
        mModule = new RecruitFragmentModule();
        getView().initRecyclerView();
    }

    @Override
    public void loadListData() {
        mModule.requestRecruitList(new OnDataGetCallback<List<RecruitFragmentListBean>>() {
            @Override
            public void onSuccessResult(List<RecruitFragmentListBean> recruitFragmentListBeen) {
                getView().bindListData(recruitFragmentListBeen);
            }
        });

    }

    /**
     * 点击前往招聘列表并开启输入框
     */
    @Override
    public void onClickGoRecruitListWithSearch() {
        RecruitJobListActivity.actionStart(getView().getContext(),true);
    }

    @Override
    public void onClickGoRecruitList() {
        RecruitJobListActivity.actionStart(getView().getContext(),false);
    }

    @Override
    public void onItemClick() {
        int position = getView().getClickItemPosition();
        int clickItemType = getView().getClickItemType();
        List<RecruitFragmentListBean.SubBean> listData = getView().getAdapterListData();
        RecruitFragmentListBean.SubBean subBean = listData.get(position);
        switch (clickItemType) {
            case RecruitFragmentAdapter.ITEM_TYPE_TITLE:
                if (subBean.getType().equals(RecruitFragmentAdapter.ITEM_TYPE_ONE_TAG)) {
                    getView().actionStartActivity(JobGuideListActivity.class);
                } else {
                    getView().actionStartActivity(SchoolListActivity.class);
                }
                break;
            case RecruitFragmentAdapter.ITEM_TYPE_ONE:
                HomeInfoActivity.actionStart(getView().getContext(), subBean.getId(), HomeInfoContract.INFO_TYPE_EMPLOYMENT);
                break;
            case RecruitFragmentAdapter.ITEM_TYPE_TWO:
                SchoolInfoActivity.actionStart(getView().getContext(), subBean.getId());
                break;
        }

    }
}
