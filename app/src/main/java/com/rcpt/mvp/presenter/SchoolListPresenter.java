package com.rcpt.mvp.presenter;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.rcpt.R;
import com.rcpt.base.mvp.OnDataGetCallback;
import com.rcpt.bean.FilterSelectBean;
import com.rcpt.bean.RecruitFragmentListBean;
import com.rcpt.mvp.contract.SchoolListContract;
import com.rcpt.mvp.module.SchoolListModule;
import com.rcpt.ui.recruit.SchoolInfoActivity;

import java.util.List;

/**
 * Created by lvzp on 2017/3/29.
 * 类描述：
 */

public class SchoolListPresenter extends ListFilterPresenter<SchoolListContract.View>
        implements SchoolListContract.Presenter {

    private SchoolListModule mModule;
    private boolean isLoadingFilterSuc;//用来记录筛选条件器是否成功

    private String mBatchId;
    private String mSchoolTypeId;

    @Override
    public void attach(SchoolListContract.View view) {
        super.attach(view);
        mModule = new SchoolListModule();
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
        loadListData(page);
    }

    @Override
    public void loadListData() {
        loadListData(1);
    }

    private void loadListData(int page) {
        mModule.requestSchoolList(getView().getContext(), mBatchId, mSchoolTypeId, page, new OnDataGetCallback<List<RecruitFragmentListBean.SubBean>>() {
            @Override
            public void onSuccessResult(List<RecruitFragmentListBean.SubBean> data) {
                getView().updateIsEnd(mModule.isEnd());
                getView().bindListData(data);
            }
        });
    }

    /**
     * 加载筛选器列表
     */
    @Override
    public void loadFilterList() {
        mModule.requestSchoolFilterList(new OnDataGetCallback<Boolean>() {
            @Override
            public void onSuccessResult(Boolean data) {
                isLoadingFilterSuc = data;
            }
        });
    }

    @Override
    public void onItemClick(RecyclerView.ViewHolder vh, int position) {
        SchoolInfoActivity.actionStart(getView().getContext(), mModule.getSchoolListData().get(position).getId());
    }


    /**
     * 当条目再次选中后的回调
     *
     * @param bean
     * @param position
     */
    @Override
    public void onReselectedCallback(FilterSelectBean bean, int position) {
        getView().layoutStartOut();
        getView().closeGrayBg();
    }

    /**
     * 清空列表重新加载数据
     */
    @Override
    protected void loadListDataWithClean() {
        if (mModule.getSchoolListData() != null)
            mModule.getSchoolListData().clear();
        loadListData(1);
    }

    /**
     * 点击事件
     *
     * @param v
     */
    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.bg_gray) {
            mOldSelectFilterItem.setChecked(false);
            getView().layoutStartOut();
            getView().closeGrayBg();
            return;
        }
        super.onClick(v);
    }

    /**
     * 当筛选类型的View处于选中状态时的回调
     *
     * @param view 当前处于选中状态的筛选类型
     * @return 是否要跳过后面方法的执行, true为跳过，false不跳过
     */
    @Override
    protected boolean onFilterItemCheckClick(View view) {
        switch (view.getId()) {
            case R.id.cbx_school_filter_batch:
                getView().bindFilterListData(mModule.getBatchFilterList());
                break;
            case R.id.cbx_school_filter_type:
                getView().bindFilterListData(mModule.getTypeFilterList());
                break;
        }
        return false;
    }

    /**
     * 当筛选列表的Item被点击时进行时进行的回调
     *
     * @param filterItem 筛选的类型View
     * @param id         选择列表的属性id
     */
    @Override
    protected void onFilterListItemClick(View filterItem, String id) {
        switch (filterItem.getId()) {
            case R.id.cbx_school_filter_batch:
                mBatchId = id;
                break;
            case R.id.cbx_school_filter_type:
                mSchoolTypeId = id;
                break;
        }
    }

    /**
     * 清除所有选中的筛选标志
     */
    @Override
    protected void cleanAllFlag() {
        mBatchId = null;
        mSchoolTypeId = null;
    }


}
