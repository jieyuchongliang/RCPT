package com.rcpt.mvp.presenter;

import com.rcpt.base.mvp.BasePresenter;
import com.rcpt.base.mvp.OnDataGetCallback;
import com.rcpt.bean.TestMajorListBean;
import com.rcpt.bean.TestMajorTypeBean;
import com.rcpt.mvp.contract.TestMajorListContract;
import com.rcpt.mvp.module.TestMajorModule;

import java.util.List;

/**
 * Created by lvzp on 2017/4/5.
 * 类描述：
 */

public class TestMajorListPresenter extends BasePresenter<TestMajorListContract.View> implements TestMajorListContract.Presenter {

    private TestMajorModule mModule;

    private String mId = "";
    private int mPageNum = 1;

    @Override
    public void attach(TestMajorListContract.View view) {
        super.attach(view);
        mModule = new TestMajorModule();
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
        mPageNum = page;
        loadPageListData(mPageNum, mId);
    }

    @Override
    public void loadListData() {
        mModule.requestTestMajorType(new OnDataGetCallback<List<TestMajorTypeBean>>() {
            @Override
            public void onSuccessResult(List<TestMajorTypeBean> data) {
                getView().bindMajorTypeData(data);
                getView().selectMajorTypeItemChecked(0);
            }
        });
    }

    @Override
    public void onMajorTypeClick() {
        getView().closeMajorTypeDrawer();
        TestMajorTypeBean bean = mModule.getTestMajorTypeList().get(getView().getMajorTypeClickPosition());
        mId = bean.getPointId();
        mPageNum = 1;
        if (mModule.getMajorList() != null)
            mModule.getMajorList().clear();
        loadPageListData(mPageNum, mId);
    }

    private void loadPageListData(int pageNum, String id) {

        mModule.requestTestMajorList(getView().getContext(), pageNum, id, "", new OnDataGetCallback<List<TestMajorListBean.TestlistBean>>() {
            @Override
            public void onSuccessResult(List<TestMajorListBean.TestlistBean> data) {
                getView().bindListData(data);
                getView().updateIsEnd(mModule.isEnd());
            }
        });
    }
}
