package com.rcpt.mvp.presenter;

import com.rcpt.Constant;
import com.rcpt.LoginHelper;
import com.rcpt.base.mvp.BasePresenter;
import com.rcpt.base.mvp.OnDataGetCallback;
import com.rcpt.bean.CVInfoListBean;
import com.rcpt.mvp.contract.CVInfoListContract;
import com.rcpt.mvp.module.CVInfoListModule;

import java.util.List;

/**
 * Created by lvzp on 2017/3/8.
 * 类描述：
 */

public class CVInfoListPresenter extends BasePresenter<CVInfoListContract.View> implements CVInfoListContract.Presenter {

    private CVInfoListModule mModule;

    @Override
    public void attach(CVInfoListContract.View view) {
        super.attach(view);
        mModule = new CVInfoListModule();
        getView().initRecyclerView();
    }

    @Override
    public void onClickCreateNewCV() {
        getView().actionGoCreateCVInfo();
    }

    @Override
    public void loadListData() {
        String createCVType = getView().getCreateCVType();
        String userId = LoginHelper.getInstance().getUserToken();
        switch (createCVType) {
            case Constant.CREATE_CV_EDUCATION://教育背景列表
                mModule.requestCVEducationList(getView().getContext(), userId, getView().getCVId(), new OnDataGetCallback<List<CVInfoListBean>>() {
                    @Override
                    public void onSuccessResult(List<CVInfoListBean> cvInfoListBeen) {
                        getView().bindListData(cvInfoListBeen);
                    }
                });
                break;
            case Constant.CREATE_CV_PROJECT_EXPERIENCE://项目经验列表
                mModule.requestCVProjectExList(getView().getContext(), userId, getView().getCVId(), new OnDataGetCallback<List<CVInfoListBean>>() {
                    @Override
                    public void onSuccessResult(List<CVInfoListBean> cvInfoListBeen) {
                        getView().bindListData(cvInfoListBeen);
                    }
                });
                break;
            case Constant.CREATE_CV_WORK_HISTORY://工作经历列表
                mModule.requestCVWorkExList(getView().getContext(), userId, getView().getCVId(), new OnDataGetCallback<List<CVInfoListBean>>() {
                    @Override
                    public void onSuccessResult(List<CVInfoListBean> cvInfoListBeen) {
                        getView().bindListData(cvInfoListBeen);
                    }
                });
                break;
        }
    }

    @Override
    public void onItemClick() {
        int position = getView().getClickPosition();
        switch (getView().getCreateCVType()) {
            case Constant.CREATE_CV_EDUCATION:
                getView().actionGoEditCVInfo(mModule.getEducationListBean().get(position));
                break;
            case Constant.CREATE_CV_PROJECT_EXPERIENCE://项目经验列表
                getView().actionGoEditCVInfo(mModule.getProjectExListBean().get(position));
                break;
            case Constant.CREATE_CV_WORK_HISTORY://工作经历列表
                getView().actionGoEditCVInfo(mModule.getWorkExListBean().get(position));
                break;
        }
    }

    /**
     * 条目的长按事件
     */
    @Override
    public void onItemLongClick() {
        final int position = getView().getClickPosition();
        switch (getView().getCreateCVType()) {
            case Constant.CREATE_CV_EDUCATION://教育背景列表
                if (position > mModule.getCVInfoEducationList().size() || position < 0)
                    return;
                final CVInfoListBean eduBean = mModule.getCVInfoEducationList().get(position);
                mModule.deleteEdu(getView().getContext(), LoginHelper.getInstance().getUserToken(), eduBean.getId(), getView().getCVId(), new OnDataGetCallback<String>() {
                    @Override
                    public void onSuccessResult(String data) {
                        getView().showToast(data);
                        mModule.getCVInfoEducationList().remove(eduBean);
                        mModule.getEducationListBean().remove(position);
                        getView().deleteListItem(position);
                    }
                });
                break;
            case Constant.CREATE_CV_PROJECT_EXPERIENCE://项目经验
                if (position > mModule.getCVInfoProjectExperienceList().size() || position < 0)
                    return;
                final CVInfoListBean projectExpBean = mModule.getCVInfoProjectExperienceList().get(position);
                mModule.deletePro(getView().getContext(), LoginHelper.getInstance().getUserToken(), projectExpBean.getId(), getView().getCVId(), new OnDataGetCallback<String>() {
                    @Override
                    public void onSuccessResult(String data) {
                        getView().showToast(data);
                        mModule.getCVInfoProjectExperienceList().remove(projectExpBean);
                        mModule.getProjectExListBean().remove(position);
                        getView().deleteListItem(position);
                    }
                });
                break;
            case Constant.CREATE_CV_WORK_HISTORY://工作经历列表
                if (position > mModule.getCVInfoWorkHistoryList().size() || position < 0)
                    return;
                final CVInfoListBean workHisBean = mModule.getCVInfoWorkHistoryList().get(position);
                mModule.deleteWork(getView().getContext(), LoginHelper.getInstance().getUserToken(), workHisBean.getId(), getView().getCVId(), new OnDataGetCallback<String>() {
                    @Override
                    public void onSuccessResult(String data) {
                        getView().showToast(data);
                        mModule.getCVInfoWorkHistoryList().remove(workHisBean);
                        mModule.getWorkExListBean().remove(position);
                        getView().deleteListItem(position);
                    }
                });
                break;
        }
    }
}
