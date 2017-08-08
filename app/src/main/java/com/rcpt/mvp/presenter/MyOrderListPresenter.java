package com.rcpt.mvp.presenter;

import android.util.Log;

import com.rcpt.LoginHelper;
import com.rcpt.base.mvp.BasePresenter;
import com.rcpt.base.mvp.OnDataGetCallback;
import com.rcpt.base.ui.BaseFragment;
import com.rcpt.bean.MyOrderListBean;
import com.rcpt.mvp.contract.MyOrderListFragmentContract;
import com.rcpt.mvp.module.MyOrderListModule;
import com.rcpt.mvp.module.MyOrderModule;

import java.util.List;

/**
 * Created by 860116021 on 2017/5/10.
 */

public class MyOrderListPresenter extends BasePresenter<MyOrderListFragmentContract.View> implements MyOrderListFragmentContract.Presenter {

    private MyOrderListModule mModule;

    @Override
    public void attach(MyOrderListFragmentContract.View view) {
        super.attach(view);
        mModule = new MyOrderListModule();
    }

    @Override
    public void onLoadMore(int page) {
        loadListData(page);
    }

    @Override
    public void loadListData() {
        getView().initRecyclerView();
        onLoadMore(1);
    }

    private void loadListData(int page) {
        mModule.requestPayOrderList(getView().getContext(), LoginHelper.getInstance().getUserToken(), getView().getOrderType(), page, new OnDataGetCallback<List<MyOrderListBean.OrderListBean>>() {
            @Override
            public void onSuccessResult(List<MyOrderListBean.OrderListBean> data) {
                getView().updateIsEnd(mModule.isEnd());
                getView().bindListData(mModule.getOrderList());
            }
        });
    }

    /**
     * 删除订单
     */
    @Override
    public void onClickDelOrder() {
        final int clickItemPosition = getView().getClickItemPosition();
        MyOrderListBean.OrderListBean orderListBean = mModule.getOrderList().get(clickItemPosition);
        mModule.delOrder(getView().getContext(), LoginHelper.getInstance().getUserToken(), orderListBean.getOrderNum(), new OnDataGetCallback<String>() {
            @Override
            public void onSuccessResult(String data) {
                getView().showToast(data);
                mModule.getOrderList().remove(clickItemPosition);
                getView().removeListItem(clickItemPosition);
            }
        });
    }
}
