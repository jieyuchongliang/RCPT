package com.rcpt.mvp.contract;

import com.rcpt.base.mvp.BaseView;
import com.rcpt.base.mvp.MenuLoadContract;
import com.rcpt.base.mvp.RecyclerViewContract;
import com.rcpt.bean.MenuBean;

/**
 * Created by lvzp on 2017/4/18.
 * 类描述：
 */

public interface CompanyRecruitInfoContract {

    interface View extends BaseView, RecyclerViewContract.View<MenuBean>, MenuLoadContract.View {
        /**
         * 获取招聘的id
         *
         * @return
         */
        String getRecruitmentInfoId();

        /**
         * 获取点击的Item条目
         *
         * @return
         */
        int getClickPosition();

        /**
         * 进入应聘人数列表的Activity
         * @param jobName
         */
        void actionStartGoAcceptNumActivity(String jobName);
    }

    interface Presenter extends RecyclerViewContract.Presenter, MenuLoadContract.Presenter {
        void onItemClick();
    }

}
