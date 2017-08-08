package com.rcpt.mvp.contract;

import com.rcpt.base.mvp.BaseView;
import com.rcpt.bean.RegisterClauseBean;

/**
 * Created by lvzp on 2017/4/25.
 * 类描述：
 */

public interface RegisterClauseContract {


    interface View extends BaseView {
        void bindWebData(RegisterClauseBean bean);

        void showLoadingProgress();

        void hintLoadingProgress();

        void updateLoadingProgress(int progress);
    }

    interface Presenter {
        void loadPageData();
    }

}
