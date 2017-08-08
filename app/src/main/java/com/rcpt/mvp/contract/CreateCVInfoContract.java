package com.rcpt.mvp.contract;

import android.support.v4.app.Fragment;

import com.rcpt.base.mvp.BaseView;

/**
 * Created by lvzp on 2017/3/3.
 * 类描述：创建简历信息的契约类
 */

public interface CreateCVInfoContract {

    interface View extends BaseView {


        Fragment createEducationAddToTask();

        Fragment createJobIntentAddToTask();

        Fragment createProjectExperienceAddToTask();

        Fragment createWorkHistoryAddToTask();

        void replaceFragment(Fragment showFragment, Fragment hideFragment, String title, boolean isLast);

        void resultOk();

    }

    interface Presenter {
        void initFragments(String createCVType);

        void onClickSave();

        void onClickBack();

        void showFragment();

        void goNextFragment();
    }

}
