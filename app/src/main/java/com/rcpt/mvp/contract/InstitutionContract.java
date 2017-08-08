package com.rcpt.mvp.contract;

import com.rcpt.base.mvp.BaseView;
import com.rcpt.base.mvp.PullToRefeshContract;
import com.rcpt.base.mvp.RecyclerViewContract;
import com.rcpt.bean.InstituteBean;

/**
 * Created by 860116021 on 2017/4/13.
 */

public interface InstitutionContract {

    interface View extends BaseView, RecyclerViewContract.View<InstituteBean.InstitutionListBean>, PullToRefeshContract.View {

    }

    interface Presenter extends RecyclerViewContract.Presenter, PullToRefeshContract.Presenter {
        
    }
}
