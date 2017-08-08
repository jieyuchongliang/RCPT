package com.rcpt.mvp.contract;

import com.rcpt.base.mvp.BaseView;
import com.rcpt.base.mvp.PullToRefeshContract;
import com.rcpt.base.mvp.RecyclerViewContract;
import com.rcpt.bean.MyOrderListBean;

/**
 * Created by 860116021 on 2017/5/10.
 */

public interface MyOrderListFragmentContract {

    interface View extends BaseView, RecyclerViewContract.View<MyOrderListBean.OrderListBean>, PullToRefeshContract.View {
        /**
         * 获取订单类型
         *
         * @return
         */
        String getOrderType();

        /**
         * 获取点击的条目position
         *
         * @return
         */
        int getClickItemPosition();

        /**
         * 删除列表中的指定条目
         *
         * @param position
         */
        void removeListItem(int position);
    }

    interface Presenter extends RecyclerViewContract.Presenter, PullToRefeshContract.Presenter {
        void onClickDelOrder();
    }
}
