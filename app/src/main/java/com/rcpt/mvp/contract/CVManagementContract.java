package com.rcpt.mvp.contract;

import com.rcpt.base.mvp.BaseView;
import com.rcpt.bean.CVInfoBean;

/**
 * Created by lvzp on 2017/3/3.
 * 类描述：
 */

public interface CVManagementContract {

    interface View extends BaseView {

        void actionGoCreateNewCV(String createCVType,String cvName);

        void actionGoCreateCVInfo(String createCVType, String cvId);

        void actionGoEditCVInfo(String createCVType, String cvId);

        void actionGoCVInfoList(String createCVType, String cvId);

        /**
         * 显示简历信息的详情界面
         */
        void showCVInfo(CVInfoBean infoBean);

        /**
         * 显示创建简历的界面
         */
        void showCreateCV();

        void showSelfAppraisalInfoPop(String selfAppraisal);

        void showChangeCVNamePop(String cvName);

        void changeCVNameText(String cvName);

        boolean showCVPublicStatus();

        void changePublicStatusText(String publicText);
    }

    interface Presenter {

        void loadCVInfo();

        /**
         * 点击简历预览
         */
        void onClickCVPreview();

        /**
         * 公开状态的改变
         */
        void onClickOpenStatusChange();

        /**
         * 刷新按钮的点击事件
         */
        void onClickCVRefresh();

        /**
         * 当点击创建新的简历时的处理
         */
        void onClickCreateNewCV();

        void onClickCreateCVEducation();

        void onClickCreateCVWorkHistory();

        void onClickCreateCVJobIntent();

        void onClickCreateCVProjectExperience();

        void onClickChangeCVName();

        /**
         * 进入到自我评价的页面
         */
        void onClickGoSelfAppraisal();

        /**
         * 提交自我评价的内容
         *
         * @param selfAppr
         */
        void uploadSelfAppraisal(String selfAppr);

        /**
         * 更新简历名称
         *
         * @param name
         */
        void updateCVName(String name);
    }

}
