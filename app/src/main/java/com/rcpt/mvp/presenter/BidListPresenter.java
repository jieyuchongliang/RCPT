package com.rcpt.mvp.presenter;

import android.support.v7.widget.RecyclerView;

import com.rcpt.base.mvp.BasePresenter;
import com.rcpt.base.mvp.OnDataCallback;
import com.rcpt.base.mvp.OnDataGetCallback;
import com.rcpt.bean.BidListBean;
import com.rcpt.mvp.contract.BidListContract;
import com.rcpt.mvp.contract.BidPersonInfoContract;
import com.rcpt.mvp.module.BidListModule;
import com.rcpt.ui.home.activity.BidInfoActivity;
import com.rcpt.ui.home.activity.BidPersonInfoActivity;

import java.util.List;

/**
 * Created by lvzp on 2017/2/24.
 * 类描述：
 */

public class BidListPresenter extends BasePresenter<BidListContract.View> implements BidListContract.Presenter {

    private BidListModule mModule;
    private String bidType;

    public static final String BID_TYPE_PROJECT = "project";
    public static final String BID_TYPE_PERSON = "person";

    @Override
    public void attach(BidListContract.View view) {
        super.attach(view);
        mModule = new BidListModule();
    }

    @Override
    public void loadListData() {
        getView().initRecyclerView();
//        getView().bindListData(mModule.getBidBeanList());
        loadListData(1);
    }

    @Override
    public void onLoadMore(int page) {
        loadListData(page);
    }

    private void loadListData(int page){
        bidType = getView().getBidType();
        if(BID_TYPE_PROJECT.equals(bidType)){
            mModule.listData(getView().getContext(), page, new OnDataGetCallback<List<BidListBean.ProjectlistBean>>() {
                @Override
                public void onSuccessResult(List<BidListBean.ProjectlistBean> data) {
                    getView().updateIsEnd(mModule.isEnd());
                    getView().bindListData(data);
                }
            });
        }else if(BID_TYPE_PERSON.equals(bidType)){
            mModule.listPerson(getView().getContext(), page, new OnDataGetCallback<List<BidListBean.personnelProlistBean>>() {
                @Override
                public void onSuccessResult(List<BidListBean.personnelProlistBean> data) {
                    getView().updateIsEnd(mModule.isEnd());
                    getView().bindBidPersonData(data);
                }
            });
        }

    }

    @Override
    public void onItemClick(RecyclerView.ViewHolder vh, int position) {
        if(BID_TYPE_PROJECT.equals(bidType)){
            BidInfoActivity.actionStart(getView().getContext(), mModule.getBidBeanList().get(position).getProjectId());
        }else if(BID_TYPE_PERSON.equals(bidType)){
            BidPersonInfoActivity.actionStart(getView().getContext(), mModule.getBidPersonList().get(position).getPersonnelId());
        }
    }
}
