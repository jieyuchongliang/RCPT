package com.rcpt.mvp.contract;

import com.rcpt.base.mvp.BaseView;
import com.rcpt.base.mvp.MenuLoadContract;
import com.rcpt.base.mvp.RecyclerViewContract;
import com.rcpt.bean.InputMenuBean;
import com.rcpt.bean.MenuBean;

/**
 * Created by 860617003 on 2017/6/1.
 */

public interface SystemMainContract {

    interface View extends BaseView, RecyclerViewContract.View<MenuBean>, MenuLoadContract.View {
        int getClickPosition();
    }

    interface Presenter extends RecyclerViewContract.Presenter, MenuLoadContract.Presenter {
        void onItemClick();
    }

}
