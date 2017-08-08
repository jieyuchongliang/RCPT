package com.rcpt.mvp.contract;

import com.rcpt.base.mvp.BaseView;
import com.rcpt.base.mvp.PullToRefeshContract;
import com.rcpt.base.mvp.RecyclerViewContract;
import com.rcpt.bean.CompanyRecruitListBean;

/**
 * Created by lvzp on 2017/4/17.
 * 类描述：
 */

public interface CompanyRecruitContract {

    interface View extends BaseView, RecyclerViewContract.View<CompanyRecruitListBean.RecruitlistBean>, PullToRefeshContract.View {
        /**
         * 获取点击的条目
         *
         * @return
         */
        int getClickPosition();

        /**
         * 获取来自于哪里
         *
         * @return
         */
        String getFromWhere();

        /**
         * 获取简历的id
         *
         * @return
         */
        String getCVId();

        /**
         * 开始进入到招聘详情
         */
        void startGoRecruitInfo(String id);

        /**
         * 进入到发布职位邀请界面
         */
        void startGoSendJobInvitation(CompanyRecruitListBean.RecruitlistBean bean, String cvId);

        String getCVUserId();

        void showSnackbar(String msg);
    }

    interface Presenter extends RecyclerViewContract.Presenter, PullToRefeshContract.Presenter {
        /**
         * 列表点击事件
         */
        void onItemClick();
    }

}
