package com.rcpt.mvp.contract;

import com.rcpt.base.mvp.BaseView;
import com.rcpt.bean.AcademyInfoBean;

import java.util.List;

/**
 * Created by lvzp on 2017/3/29.
 * 类描述：
 */

public interface SchoolInfoContract {

    interface View extends BaseView {
        String getAcademyId();
        void setupPageData(AcademyInfoBean.SchoolInfoBean data);
        void setupPagePic(List<AcademyInfoBean.GraduateinfoBean.SchoolpitcureBean> bean);
        void showNoPicEx();
    }

    interface Presenter {
        void loadInfo();
    }

}
