package com.rcpt.mvp.contract;

import com.rcpt.base.mvp.BaseView;
import com.rcpt.base.mvp.MenuLoadContract;
import com.rcpt.base.mvp.RecyclerViewContract;
import com.rcpt.bean.MenuBean;

/**
 * Created by lvzp on 2017/3/1.
 * 类描述：
 */

public interface MeContract {

    interface View extends BaseView, RecyclerViewContract.View<MenuBean>, MenuLoadContract.View {

    }

    interface Presenter extends RecyclerViewContract.Presenter, MenuLoadContract.Presenter {


        /**
         * 点击进入个人详情
         */
        void onClickGoProjectInfo();
    }

}
