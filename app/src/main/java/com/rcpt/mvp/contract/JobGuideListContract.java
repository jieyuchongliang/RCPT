package com.rcpt.mvp.contract;

import android.support.v4.app.Fragment;

import com.rcpt.base.mvp.BaseView;

import java.util.List;

/**
 * Created by lvzp on 2017/3/29.
 * 类描述：
 */

public interface JobGuideListContract {

    interface View extends BaseView {
        void bindPageData(List<Fragment> fragmentList, List<String> titleList);
    }

    interface Presenter {
        void initFragments();
    }

}
