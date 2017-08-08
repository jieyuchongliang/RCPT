package com.rcpt.mvp.contract;

import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.rcpt.adapter.holder.LocalImageHolderView;
import com.rcpt.base.mvp.BaseView;
import com.rcpt.bean.InstitutionDetailBean;

import java.util.List;

/**
 * Created by 860116021 on 2017/4/14.
 */

public interface InstitutionInfoContract {
    interface View extends BaseView{
        String getInstitutionId();

        void setUpData(InstitutionDetailBean bean);

        void setUpCourse(List<InstitutionDetailBean.CourselistBean> courseBeanList);

        void setUpTeacher(List<InstitutionDetailBean.TeacherlistBean> teacherBeanList);

        //6月1日，培训机构详情里面的头部icon取消了。直接显示机构环境的轮播图。
        //void showEmptyEnviroment();

        void showEmptyCourse();

        void showEmptyTeacher();

        void setConvenientBannerHolder(CBViewHolderCreator<LocalImageHolderView> holderCreator, List<String> images);
    }

    interface Presenter{
        void bindBanner(List<String> mBannerImages);
    }
}
