package com.rcpt.mvp.presenter;

import com.rcpt.Constant;
import com.rcpt.LoginHelper;
import com.rcpt.base.mvp.BasePresenter;
import com.rcpt.base.mvp.OnDataGetCallback;
import com.rcpt.bean.CVInfoBean;
import com.rcpt.mvp.contract.CVManagementContract;
import com.rcpt.mvp.module.CVManagementModule;
import com.rcpt.ui.recruit.RecruitCVPreviewInfoActivity;

/**
 * Created by lvzp on 2017/3/3.
 * 类描述：
 */

public class CVManagementPresenter extends BasePresenter<CVManagementContract.View> implements CVManagementContract.Presenter {


    private CVManagementModule mModule;
    private boolean isCreateNewsCV;

    @Override
    public void attach(CVManagementContract.View view) {
        super.attach(view);
        mModule = new CVManagementModule();
    }

    /**
     * 点击简历预览
     */
    @Override
    public void onClickCVPreview() {
        RecruitCVPreviewInfoActivity.actionStart(getView().getContext(), mModule.getCVId(), LoginHelper.getInstance().getUserToken());
    }

    /**
     * 公开状态的改变
     */
    @Override
    public void onClickOpenStatusChange() {
        String publicSet = getView().showCVPublicStatus() ? "1" : "0";
        mModule.updateCVPublicSet(getView().getContext(), LoginHelper.getInstance().getUserToken(), mModule.getCVId(), publicSet, new OnDataGetCallback<String>() {
            @Override
            public void onSuccessResult(String result) {
                getView().showToast(result);
                getView().changePublicStatusText(getView().showCVPublicStatus() ? "保密" : "公开");
            }
        });
    }

    /**
     * 刷新按钮的点击事件
     */
    @Override
    public void onClickCVRefresh() {
        loadCVInfo();
    }

    /**
     * 当点击创建新的简历时的处理
     */
    @Override
    public void onClickCreateNewCV() {
        mModule.checkPersonInfo(getView().getContext(), LoginHelper.getInstance().getUserToken(), new OnDataGetCallback<String>() {
            @Override
            public void onSuccessResult(String data) {
                isCreateNewsCV = true;
                getView().showChangeCVNamePop("");
            }
        });
    }

    @Override
    public void onClickCreateCVEducation() {
        getView().actionGoCVInfoList(Constant.CREATE_CV_EDUCATION, mModule.getCVId());
    }

    @Override
    public void onClickCreateCVWorkHistory() {
        getView().actionGoCVInfoList(Constant.CREATE_CV_WORK_HISTORY, mModule.getCVId());
    }

    @Override
    public void onClickCreateCVJobIntent() {
        getView().actionGoEditCVInfo(Constant.CREATE_CV_JOB_INTENT, mModule.getCVId());
    }

    @Override
    public void onClickCreateCVProjectExperience() {
        getView().actionGoCVInfoList(Constant.CREATE_CV_PROJECT_EXPERIENCE, mModule.getCVId());
    }

    @Override
    public void onClickChangeCVName() {
        isCreateNewsCV = false;
        getView().showChangeCVNamePop(mModule.getInfoBean().getCvName());
    }

    /**
     * 更新简历名称
     *
     * @param name
     */
    @Override
    public void updateCVName(final String name) {
        if (isCreateNewsCV) {
            getView().actionGoCreateNewCV(Constant.CREATE_CV_JOB_INTENT, name);
            /*mModule.requestCreateCVId(getView().getContext(), LoginHelper.getInstance().getUserToken(), name, new OnDataGetCallback<String>() {
                @Override
                public void onSuccessResult(String s) {
                    getView().actionGoCreateCVInfo(Constant.CREATE_CV_ALL, mModule.getCVId());
                    *//*getView().closeActivity();*//*
                    loadCVInfo();
                }
            });*/
        } else
            mModule.updateCVName(getView().getContext(), LoginHelper.getInstance().getUserToken(), mModule.getCVId(), name, new OnDataGetCallback<String>() {
                @Override
                public void onSuccessResult(String s) {
                    getView().showToast(s);
                    getView().changeCVNameText(name);
                }
            });
    }

    /**
     * 提交自我评价的内容
     *
     * @param selfAppr
     */
    @Override
    public void uploadSelfAppraisal(String selfAppr) {
        mModule.updateSelfAppraisalInfo(getView().getContext(), LoginHelper.getInstance().getUserToken(), mModule.getCVId(), selfAppr, new OnDataGetCallback<String>() {
            @Override
            public void onSuccessResult(String s) {
                getView().showToast(s);
            }
        });
    }

    /**
     * 进入到自我评价的页面
     */
    @Override
    public void onClickGoSelfAppraisal() {
        mModule.requestSelfAppraisalInfo(getView().getContext(), LoginHelper.getInstance().getUserToken(), mModule.getCVId(), new OnDataGetCallback<String>() {
            @Override
            public void onSuccessResult(String s) {
                getView().showSelfAppraisalInfoPop(s);
            }
        });
    }

    @Override
    public void loadCVInfo() {
        mModule.requestCVInfo(getView().getContext(), LoginHelper.getInstance().getUserToken(), new OnDataGetCallback<CVInfoBean>() {
            @Override
            public void onSuccessResult(CVInfoBean cvInfoBean) {
                if (cvInfoBean == null) {
                    getView().showCreateCV();
                } else {
                    getView().showCVInfo(cvInfoBean);
                }
            }
        });
    }
}
