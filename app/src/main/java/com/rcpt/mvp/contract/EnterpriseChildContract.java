package com.rcpt.mvp.contract;

import com.rcpt.base.mvp.BaseView;
import com.rcpt.base.mvp.PullToRefeshContract;
import com.rcpt.base.mvp.RecyclerViewContract;
import com.rcpt.bean.EnterpriseListBean;

/**
 * Created by lvzp on 2017/3/27.
 * 类描述：
 */

public interface EnterpriseChildContract {

    interface View extends BaseView, RecyclerViewContract.View<EnterpriseListBean.BusinesslistBean>, PullToRefeshContract.View {
        String getEnterpriseId();
        String getEnterpriseKeyWord();
    }

    interface Presenter extends RecyclerViewContract.Presenter, PullToRefeshContract.Presenter {

    }

}
