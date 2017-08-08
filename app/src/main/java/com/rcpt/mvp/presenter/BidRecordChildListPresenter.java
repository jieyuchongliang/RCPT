package com.rcpt.mvp.presenter;

import com.rcpt.LoginHelper;
import com.rcpt.base.mvp.BasePresenter;
import com.rcpt.base.mvp.OnDataGetCallback;
import com.rcpt.bean.BidRecordListBean;
import com.rcpt.mvp.contract.BidRecordChildListContract;
import com.rcpt.mvp.module.BidRecordChildListModule;
import com.rcpt.ui.me.BidRecordChildFragment;

import java.util.List;

/**
 * Created by 860617003 on 2017/5/25.
 */

public class BidRecordChildListPresenter extends BasePresenter<BidRecordChildListContract.View> implements BidRecordChildListContract.Presenter {

    private BidRecordChildListModule mModule;

    @Override
    public void attach(BidRecordChildListContract.View view) {
        super.attach(view);
        mModule = new BidRecordChildListModule();
        getView().initRecyclerView();
    }

    /**
     * 加载更多的数据
     * 只需要根据相应的页码加载相应的数据，无需关心刷新和加载更多
     *
     * @param page
     */
    @Override
    public void onLoadMore(int page) {
        String bidType = getView().getBidType();
        String userId = LoginHelper.getInstance().getUserToken();
        if (BidRecordChildFragment.BID_PERSON.equals(bidType)) {
            mModule.requestPersonBidRecordList(getView().getContext(), userId, getView().getBidStatus(), page, new OnDataGetCallback<List<BidRecordListBean.ProjectProlistBean>>() {
                @Override
                public void onSuccessResult(List<BidRecordListBean.ProjectProlistBean> data) {
                    getView().updateIsEnd(mModule.isEnd());
                    getView().bindListData(data);
                }
            });
        } else if (BidRecordChildFragment.BID_PROJECT.equals(bidType)) {
            mModule.requestProjectBidRecordList(getView().getContext(), userId, getView().getBidStatus(), page, new OnDataGetCallback<List<BidRecordListBean.ProjectProlistBean>>() {
                @Override
                public void onSuccessResult(List<BidRecordListBean.ProjectProlistBean> data) {
                    getView().updateIsEnd(mModule.isEnd());
                    getView().bindListData(data);
                }
            });
        }
    }

    @Override
    public void loadListData() {
        onLoadMore(1);
    }
}
