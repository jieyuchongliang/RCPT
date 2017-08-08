package com.rcpt.mvp.contract;

import com.rcpt.base.mvp.BaseView;
import com.rcpt.base.mvp.PullToRefeshContract;
import com.rcpt.base.mvp.RecyclerViewContract;
import com.rcpt.bean.SendRecordBean;

/**
 * Created by lvzp on 2017/3/2.
 * 类描述：
 */

public interface SendRecordContract {

    interface View extends BaseView, RecyclerViewContract.View<SendRecordBean.DeliveryRecordListBean>, PullToRefeshContract.View {
        /**
         * 获取删除的点击条目
         * @return
         */
        int getDeleteClickPosition();

        /**
         * 列表中条目删除后，进行更新
         * @param position
         */
        void listItemDeleteUpdate(int position);

    }

    interface Presenter extends RecyclerViewContract.Presenter, PullToRefeshContract.Presenter {
        void onItemClick();
    }

}
