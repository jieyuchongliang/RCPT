package com.rcpt.mvp.contract;

import com.rcpt.base.mvp.BaseView;
import com.rcpt.bean.RecruitJobInfoBean;

/**
 * Created by lvzp on 2017/2/28.
 * 类描述：
 */

public interface RecruitInfoContract {

    interface View extends BaseView {
        /**
         * 设置投递简历的按钮为不可点击状态
         */
        void setSendResumeBtnClickOff();

        /**
         * 设置收藏按钮为收藏状态
         */
        void setClickFavoriteChecked();

        /**
         * 设置收藏按钮为未收藏状态
         */
        void setClickFavoriteUnChecked();

        String getJobId();

        String getCompanyId();

        void setupData(RecruitJobInfoBean bean);

        void showSnackbar(String message);
    }

    interface Presenter {
        void loadInfo();

        /**
         * 投递简历的点击事件
         */
        void onClickSendResume();

        /**
         * 点击加入收藏夹
         */
        void onClickAddFavorites();
    }

}
