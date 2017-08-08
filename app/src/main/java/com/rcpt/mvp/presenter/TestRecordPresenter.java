package com.rcpt.mvp.presenter;

import com.rcpt.LoginHelper;
import com.rcpt.base.mvp.BasePresenter;
import com.rcpt.base.mvp.OnDataGetCallback;
import com.rcpt.bean.TestRecordListBean;
import com.rcpt.mvp.contract.TestRecordContract;
import com.rcpt.mvp.module.TestRecordModule;

import java.util.List;

/**
 * 测评记录的控制器
 */

public class TestRecordPresenter extends BasePresenter<TestRecordContract.View> implements TestRecordContract.Presenter {

    private TestRecordModule mModule;

    @Override
    public void attach(TestRecordContract.View view) {
        super.attach(view);
        mModule = new TestRecordModule();
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
        mModule.requestTestRecordList(getView().getContext(), LoginHelper.getInstance().getUserToken(), page, new OnDataGetCallback<List<TestRecordListBean.CompanyFavoritesBean>>() {
            @Override
            public void onSuccessResult(List<TestRecordListBean.CompanyFavoritesBean> data) {
                getView().updateIsEnd(mModule.isEnd());
                getView().bindListData(data);
            }
        });
    }

    @Override
    public void loadListData() {
        onLoadMore(1);
    }
}
