package com.rcpt.mvp.contract;


import android.support.v4.app.Fragment;

import com.rcpt.base.mvp.BaseView;
import com.rcpt.bean.VideoInfoBaseBean;

import java.util.List;

/**
 * Created by lvzp on 2017/5/8.
 * 类描述：
 */

public interface VideoInfoContract {

    interface View extends BaseView  {
        String getClassTypeId();

        void bindPageFragment(List<Fragment> fragmentList, List<String> titleList);

        VideoInfoBaseBean getVideoInfoBaseBean();

        void onBackClick();

        /**
         * 获取课程类型id
         *
         * @return
         */
        String getCourseTypeId();

        /**
         * 获取课程类型名称
         *
         * @return
         */
        String getCourseTypeName();

        /**
         * 获取授课类型
         *
         * @return
         */
        String getTeachingStyle();
    }

    interface Presenter {

        /**
         * 点击关闭按钮的点击事件
         */
        void onClickCloseActivity();

        void loadPageData();

        /**
         * 购买课程
         */
        void onClickBuyVideo();
    }

}
