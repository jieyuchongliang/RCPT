package com.rcpt.mvp.contract;

import com.rcpt.base.mvp.BaseView;
import com.rcpt.bean.CVPreviewInfoBean;

import java.util.List;

/**
 * Created by lvzp on 2017/3/31.
 * 类描述：
 */

public interface RecruitCVPreviewInfoContract {

    interface View extends BaseView {
        /**
         * 获取简历的Id
         *
         * @return
         */
        String getCVId();

        /**
         * 获取简历用户ID
         *
         * @return
         */
        String getCVUserId();

        /**
         * 绑定页面基本数据
         */
        void bindPageData(CVPreviewInfoBean infoBean);

        /**
         * 绑定教育背景列表数据
         *
         * @param educationList
         */
        void bindEducationListData(List<CVPreviewInfoBean.EdiucationpdListBean> educationList);

        /**
         * 绑定工作经验列表数据
         *
         * @param workExList
         */
        void bindWorkExListData(List<CVPreviewInfoBean.ExperiencepdListBean> workExList);

        /**
         * 绑定项目经验数据列表
         *
         * @param projectExList
         */
        void bindProjectExListData(List<CVPreviewInfoBean.ProjectpdListBean> projectExList);

        /**
         * 显示教育背景未添加的空布局
         */
        void showEducationEmptyView();

        /**
         * 显示工作经历未添加的空布局
         */
        void showWorkExEmptyView();

        /**
         * 显示工作经验未添加的空布局
         */
        void showProjectExEmptyView();

        /**
         * 用户类型
         *
         * @return
         */
        String getUserType();

        /**
         * 改变收藏的状态
         *
         * @param isCollect 是否为收藏
         */
        void changeCollectStatus(boolean isCollect);


    }

    interface Presenter {
        /**
         * 开始加载数据
         */
        void loadPageData();

        /**
         * 发送职位邀请
         */
        void onClickSendPositionInvite();

        /**
         * 收藏简历信息
         */
        void onClickCollectCV();
    }

}
