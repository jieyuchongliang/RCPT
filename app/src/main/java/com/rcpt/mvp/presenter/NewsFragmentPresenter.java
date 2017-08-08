package com.rcpt.mvp.presenter;

import android.support.v7.widget.RecyclerView;

import com.rcpt.base.mvp.BasePresenter;
import com.rcpt.base.mvp.OnDataGetCallback;
import com.rcpt.bean.NewsListBean;
import com.rcpt.mvp.contract.HomeInfoContract;
import com.rcpt.mvp.contract.NewsFragmentContract;
import com.rcpt.mvp.module.NewsListModule;
import com.rcpt.ui.home.activity.HomeInfoActivity;

import java.util.List;

/**
 * Created by lvzp on 2017/2/23.
 * 类描述：
 */

public class NewsFragmentPresenter extends BasePresenter<NewsFragmentContract.View> implements NewsFragmentContract.Presenter {


    private NewsListModule mModule;

    @Override
    public void attach(NewsFragmentContract.View view) {
        super.attach(view);
        mModule = new NewsListModule();
        createItemClickListener(getView().getRecyclerView());
    }

    @Override
    public void loadListData() {
        getView().initRecyclerView();
        loadListData(1);
    }

    /**
     * 加载更多的数据
     * 只需要根据相应的页码加载相应的数据，无需关心刷新和加载更多
     *
     * @param page
     */
    @Override
    public void onLoadMore(int page) {
        loadListData(page);
    }

    private void loadListData(int page) {
        mModule.requestNewsList(getView().getNewsId(), page, new OnDataGetCallback<List<NewsListBean.GetlistNewsBean>>() {
            @Override
            public void onSuccessResult(List<NewsListBean.GetlistNewsBean> getlistNewsBeen) {
                getView().updateIsEnd(mModule.isEnd());
                getView().bindListData(getlistNewsBeen);
            }
        });
    }

    @Override
    public void onItemClick(RecyclerView.ViewHolder vh, int position) {
        String newsId = mModule.getNewsList().get(position).getNewsId();
        if (getView().getNewsType() != 0) {
            HomeInfoActivity.actionStart(getView().getContext(), newsId, HomeInfoContract.INFO_TYPE_COMPANY_NEWS);
        } else {
            HomeInfoActivity.actionStart(getView().getContext(), newsId, HomeInfoContract.INFO_TYPE_NEWS);
        }
    }

}
