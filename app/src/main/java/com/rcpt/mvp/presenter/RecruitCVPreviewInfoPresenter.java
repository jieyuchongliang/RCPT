package com.rcpt.mvp.presenter;

import com.rcpt.Constant;
import com.rcpt.LoginHelper;
import com.rcpt.base.mvp.BasePresenter;
import com.rcpt.base.mvp.OnDataGetCallback;
import com.rcpt.bean.CVPreviewInfoBean;
import com.rcpt.mvp.contract.RecruitCVPreviewInfoContract;
import com.rcpt.mvp.module.RecruitCVPreviewInfoModule;
import com.rcpt.ui.me.company.CompanyRecruitActivity;

/**
 * Created by lvzp on 2017/3/31.
 * 类描述：招聘栏目中建立预览的控制器
 */

public class RecruitCVPreviewInfoPresenter extends BasePresenter<RecruitCVPreviewInfoContract.View> implements RecruitCVPreviewInfoContract.Presenter {

    private RecruitCVPreviewInfoModule mModule;
    private boolean isCollect;

    @Override
    public void attach(RecruitCVPreviewInfoContract.View view) {
        super.attach(view);
        mModule = new RecruitCVPreviewInfoModule();
    }

    /**
     * 开始加载数据
     */
    @Override
    public void loadPageData() {
        String cvId = getView().getCVId();
        String cvUserId = getView().getCVUserId();
        String userType = getView().getUserType();
        if (userType.equals(Constant.UserType.PERSON.getValue())) {
            mModule.requestCVPreviewInfo(getView().getContext()
                    , cvId
                    , cvUserId
                    , new OnDataGetCallback<CVPreviewInfoBean>() {
                        @Override
                        public void onSuccessResult(CVPreviewInfoBean data) {
                            initResultData(data);
                        }
                    });
        } else {
            mModule.requestCVPreviewInfo(
                    getView().getContext()
                    , cvId
                    , cvUserId
                    , LoginHelper.getInstance().getUserToken()
                    , new OnDataGetCallback<CVPreviewInfoBean>() {
                        @Override
                        public void onSuccessResult(CVPreviewInfoBean data) {
                            initResultData(data);
                        }
                    });
        }

    }

    private void initResultData(CVPreviewInfoBean data) {
        getView().bindPageData(data);
        String collectcheck = data.getCollectcheck();
        isCollect = "1".equals(collectcheck);
        getView().changeCollectStatus(isCollect);
        if (data.getEdiucationpdList() == null || data.getEdiucationpdList().isEmpty()) {
            getView().showEducationEmptyView();
        } else {
            getView().bindEducationListData(data.getEdiucationpdList());
        }
        if (data.getExperiencepdList() == null || data.getExperiencepdList().isEmpty()) {
            getView().showWorkExEmptyView();
        } else {
            getView().bindWorkExListData(data.getExperiencepdList());
        }
        if (data.getProjectpdList() == null || data.getProjectpdList().isEmpty()) {
            getView().showProjectExEmptyView();
        } else getView().bindProjectExListData(data.getProjectpdList());
    }

    /**
     * 发送职位邀请
     */
    @Override
    public void onClickSendPositionInvite() {
        CompanyRecruitActivity.actionStart(getView().getContext(), CompanyRecruitActivity.FROM_WHERE_TAG_CV_INFO, getView().getCVId(),getView().getCVUserId());
    }

    /**
     * 收藏简历信息
     */
    @Override
    public void onClickCollectCV() {
        if (isCollect) {
            getView().showToast("您已收藏过该简历，无需重复点击");
            return;
        }
        mModule.collectResume(getView().getContext(), getView().getCVId(), getView().getCVUserId(), LoginHelper.getInstance().getUserToken(), new OnDataGetCallback<String>() {
            @Override
            public void onSuccessResult(String data) {
                getView().showToast(data);
                getView().changeCollectStatus(true);
            }
        });
    }
}
