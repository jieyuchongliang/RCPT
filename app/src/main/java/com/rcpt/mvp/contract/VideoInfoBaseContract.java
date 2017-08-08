package com.rcpt.mvp.contract;

import com.rcpt.base.mvp.BaseView;
import com.rcpt.bean.VideoInfoBaseBean;

/**
 * Created by 860617003 on 2017/5/15.
 */

public interface VideoInfoBaseContract {

    interface View extends BaseView {
        /**
         * 获取课程Id
         *
         * @return
         */
        String getClassTypeId();

        /**
         * 绑定页面数据
         *
         * @param baseBean
         */
        void bindPageData(VideoInfoBaseBean baseBean);
    }

    interface Presenter {
        /**
         * 点击教师详情的条目
         */
        void onClickTeacherInfo();

        /**
         * 加载页面的数据
         */
        void loadPageData();
    }

}
