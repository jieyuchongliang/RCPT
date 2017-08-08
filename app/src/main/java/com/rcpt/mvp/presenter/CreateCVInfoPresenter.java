package com.rcpt.mvp.presenter;

import android.support.v4.app.Fragment;

import com.rcpt.Constant;
import com.rcpt.base.mvp.BasePresenter;
import com.rcpt.mvp.contract.CreateCVInfoContract;
import com.rcpt.mvp.module.CreateCVInfoModule;
import com.rcpt.ui.me.cv.CreateCVInfoFragment;

/**
 * Created by lvzp on 2017/3/3.
 * 类描述：
 */

public class CreateCVInfoPresenter extends BasePresenter<CreateCVInfoContract.View> implements CreateCVInfoContract.Presenter {

    private CreateCVInfoModule mModule;
    private Fragment mCurrentFragment;

    private int mCurrentTaskPosition = 0;

    @Override
    public void attach(CreateCVInfoContract.View view) {
        super.attach(view);
        mModule = new CreateCVInfoModule();
    }


    @Override
    public void initFragments(String createCVType) {
        switch (createCVType) {
            case Constant.CREATE_CV_EDUCATION://教育背景
                mModule.addFragmentToTask(getView().createEducationAddToTask(), "教育背景");
                break;
            case Constant.CREATE_CV_WORK_HISTORY://工作经历
                mModule.addFragmentToTask(getView().createWorkHistoryAddToTask(), "工作经历");
                break;
            case Constant.CREATE_CV_PROJECT_EXPERIENCE://项目经验
                mModule.addFragmentToTask(getView().createProjectExperienceAddToTask(), "项目经验");
                break;
            case Constant.CREATE_CV_JOB_INTENT://求职意向
                mModule.addFragmentToTask(getView().createJobIntentAddToTask(), "求职意向");
                break;
            case Constant.CREATE_CV_ALL:
                mModule.addFragmentToTask(getView().createEducationAddToTask(), "教育背景");
                mModule.addFragmentToTask(getView().createWorkHistoryAddToTask(), "工作经历");
                mModule.addFragmentToTask(getView().createProjectExperienceAddToTask(), "项目经验");
                mModule.addFragmentToTask(getView().createJobIntentAddToTask(), "求职意向");
                break;
        }
        //mCurrentFragment = mModule.getTaskFragmentList().get(mCurrentTaskPosition);
        showFragment();
    }

    @Override
    public void onClickSave() {
        ((CreateCVInfoFragment) mCurrentFragment).uploadCreateCV();
    }

    @Override
    public void goNextFragment() {
        if (mCurrentTaskPosition < mModule.getTaskFragmentList().size() - 1) {
            mCurrentTaskPosition++;
            showFragment();
        } else {
            getView().resultOk();
            getView().closeActivity();
        }
    }

    @Override
    public void onClickBack() {
        if (mCurrentTaskPosition > 0) {
            mCurrentTaskPosition--;
            showFragment();
        } else {
            getView().closeActivity();
        }
    }

    @Override
    public void showFragment() {
        Fragment showFragment = mModule.getTaskFragmentList().get(mCurrentTaskPosition);
        String titleName = mModule.getTaskFragmentTitleList().get(mCurrentTaskPosition);
        getView().replaceFragment(showFragment, mCurrentFragment, titleName, mCurrentTaskPosition == mModule.getTaskFragmentList().size() - 1);
        mCurrentFragment = showFragment;
    }
}
