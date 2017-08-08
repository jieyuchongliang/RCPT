package com.rcpt.mvp.contract;

import android.os.Parcelable;

import com.rcpt.base.mvp.BaseView;
import com.rcpt.base.mvp.RecyclerViewContract;
import com.rcpt.bean.CVInfoListBean;

/**
 * Created by lvzp on 2017/3/8.
 * 类描述：
 */

public interface CVInfoListContract {

    interface View extends BaseView, RecyclerViewContract.View<CVInfoListBean> {
        void actionGoCreateCVInfo();

        String getCreateCVType();

        String getCVId();

        void actionGoEditCVInfo(Parcelable editBean);

        /**
         * 获取点击条目的Position
         *
         * @return
         */
        int getClickPosition();

        void deleteListItem(int position);
    }

    interface Presenter extends RecyclerViewContract.Presenter {
        void onClickCreateNewCV();

        /**
         * 列表条目的点击事件
         */
        void onItemClick();

        /**
         * 条目的长按事件
         */
        void onItemLongClick();
    }

}
