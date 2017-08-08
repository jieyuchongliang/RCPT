package com.rcpt.mvp.presenter;

import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.rcpt.R;
import com.rcpt.base.mvp.BasePresenter;
import com.rcpt.base.mvp.OnDataCallback;
import com.rcpt.base.mvp.OnDataGetCallback;
import com.rcpt.bean.FilterSelectBean;
import com.rcpt.bean.RecruitListBean;
import com.rcpt.mvp.contract.RecruitListContract;
import com.rcpt.mvp.module.RecruitListModule;
import com.rcpt.ui.recruit.RecruitCVPreviewInfoActivity;
import com.rcpt.ui.recruit.RecruitJobInfoActivity;
import com.rcpt.widget.FilterItemView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lvzp on 2017/2/24.
 * 类描述：人才通道的列表控制器
 */

public class RecruitListPresenter extends BasePresenter<RecruitListContract.View> implements RecruitListContract.Presenter {

    private RecruitListModule mModule;
    private boolean isLoadFilterSuccess;
    private boolean isLoadDataSuccess;

    private String mHopeJobId;//期望职位id
    private String mHopeJobAddressId;//期望工作地点id
    private String mEducationId;//学历id
    private String mWorkYearId;//工作年限id
    private String mHopeSalaryId;//期望薪资

    private String mIndustryId;//行业列表id
    private String mJobTypeId;//职位类型
    private String mOrientedGroupId;//招聘范围id

    private String mUserName;
    private String mJobName;

    private int mFilterSelectCount;

    @Override
    public void attach(RecruitListContract.View view) {
        super.attach(view);
        mModule = new RecruitListModule();
        getView().initRecyclerView();
    }

    @Override
    public void loadListData() {
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
        getView().showProgressDialog("数据加载中...");
        String userType = getView().isPersonType() ? "1" : "2";
        mModule.requestJobList(userType
                , page
                , mHopeJobId
                , mHopeJobAddressId
                , mEducationId
                , mWorkYearId
                , mHopeSalaryId
                , mIndustryId
                , mHopeSalaryId
                , mJobTypeId
                , mOrientedGroupId
                , mUserName
                , mJobName
                , new OnDataCallback<RecruitListBean>() {

                    @Override
                    public void onError(String errMsg) {
                        getView().showToast(errMsg);
                        closeDialog();
                    }

                    @Override
                    public void onSuccessResult(RecruitListBean data) {
                        isLoadDataSuccess = true;
                        getView().updateIsEnd(mModule.isEnd());
                        if (mModule.getRecruitList() == null) {
                            getView().bindCVListData(mModule.getRecruitCvList());
                        } else {
                            getView().bindJobListData(mModule.getRecruitList());
                        }
                        closeDialog();
                    }
                });
        if (isLoadFilterSuccess)
            return;
        if (getView().isPersonType()) {
            requestJobFilter(false);
        } else {
            requestPositionFilter(false);
        }
    }

    @Override
    public void onItemClick(RecyclerView.ViewHolder vh, int position) {

        if (getView().isPersonType()) {
            RecruitListBean.JobListBean bean = mModule.getRecruitList().get(position);
            RecruitJobInfoActivity.actionStart(getView().getContext(), bean.getCompanyId(), bean.getRecruitmentInfoId());
        } else {
            RecruitListBean.CVListBean bean = mModule.getRecruitCvList().get(position);
            RecruitCVPreviewInfoActivity.actionStart(getView().getContext(), bean.getCvId(), bean.getUserId());
        }
    }

    /**
     * 当筛选条件中职位父类被点击时调用的方法
     */
    @Override
    public void onFilterJobParentClick() {
        final FilterSelectBean filterSelectBean = getView().getFilterSelectBean();
        mModule.requestRecruitChildPosition(getView().getContext(), filterSelectBean.getId(), new OnDataGetCallback<List<FilterSelectBean>>() {
            @Override
            public void onSuccessResult(List<FilterSelectBean> data) {
                getView().bindRightFilterData(data, filterSelectBean.getValue());
            }
        });
    }

    /**
     * 当筛选的点击改变时执行方法
     */
    @Override
    public void onFilterItemClick() {
        int checkId = getView().getCheckId();
        boolean isChecked = getView().isChecked();
        if (!isLoadFilterSuccess) {
            requestPositionFilter(true);
            return;
        }
        if (isChecked) {
            switch (checkId) {
                case R.id.position_filter_address://地点的点击事件
                    getView().startGoAddress();
                    getView().layoutStartOut();
                    getView().closeGrayBg();
                    return;
                case R.id.position_filter_education://学历
                    getView().bindLeftFilterData(mModule.getEducationFilterList(), true);
                    break;
                case R.id.position_filter_job://职位
                    getView().bindLeftFilterData(mModule.getJobPareCateFilterList(), false);
                    String parentName = mModule.getJobPareCateFilterList().get(0).getValue();
                    for (FilterSelectBean filterSelectBean : mModule.getJobPareCateFilterList()) {
                        if (filterSelectBean.isSelect()) {
                            parentName = filterSelectBean.getValue();
                            break;
                        }
                    }
                    getView().bindRightFilterData(mModule.getFirstJobChildCateFilterList(), parentName);
                    break;
                case R.id.position_filter_job_time://工作年限
                    getView().bindLeftFilterData(mModule.getJobTimeFilterList(), true);
                    break;
                case R.id.job_filter_industry:
                    getView().bindLeftFilterData(mModule.getIndustryFilterList(), true);
                    break;
                case R.id.job_filter_job_type:
                    getView().bindLeftFilterData(mModule.getJobTypeFilterList(), true);
                    break;
                case R.id.job_filter_sal_range://薪资
                case R.id.position_filter_sal_range://薪资
                    getView().bindLeftFilterData(mModule.getSalRangeFilterList(), true);
                    break;
                case R.id.job_filter_range:
                    getView().bindLeftFilterData(mModule.getJobOrientedList(), true);
                    break;
            }
            getView().showGrayBg();
            getView().layoutStartIn();
        } else {
            getView().closeGrayBg();
            getView().layoutStartOut();
        }

    }

    /**
     * 当筛选条件的属性选择完成后调用的方法
     */
    @Override
    public void onFilterSelectOver() {

        FilterSelectBean filterSelectBean = getView().getFilterSelectBean();
        int checkId = getView().getCheckId();
        String filterSelectId = filterSelectBean.getId();
        View filterItemView = getView().findViewById(checkId);
        String filterName = "";
        getView().setupFilterName(filterSelectBean.getValue());
        switch (checkId) {
            case R.id.position_filter_address://工作地点
                mHopeJobAddressId = filterSelectId;
                filterName = "地点";
                break;
            case R.id.position_filter_education://学历
                mEducationId = filterSelectId;
                filterName = "学历";
                break;
            case R.id.position_filter_job_time://工作年限
                mWorkYearId = filterSelectId;
                filterName = "年限";
                break;
            case R.id.job_filter_sal_range:
            case R.id.position_filter_sal_range://薪资
                mHopeSalaryId = filterSelectId;
                filterName = "薪资";
                break;
            case R.id.job_filter_industry:
                mIndustryId = filterSelectId;
                filterName = "行业";
                break;
            case R.id.job_filter_job_type:
                mJobTypeId = filterSelectId;
                filterName = "工作类型";
                break;
            case R.id.job_filter_range:
                mOrientedGroupId = filterSelectId;
                filterName = "招聘范围";
                break;
        }
        FilterItemView.DataHolder holder = FilterItemView.DataHolder.obtain(filterItemView, filterName, filterSelectBean.getValue());
        mFilterSelectCount = getView().addFilterItemToPop(holder, true);
        loadListDataWithClean();
        getView().layoutStartOut();
        getView().closeGrayBg();
        getView().showTitleRightView(mFilterSelectCount);
    }

    /**
     * 当PopWindow中点击搜索时执行的方法
     */
    @Override
    public void onPopLayoutClickSearch() {
        String inputSearchText = getView().getInputSearchText();
        if (getView().isPersonType()) {
            mJobName = inputSearchText;
        } else {
            mUserName = inputSearchText;
        }
        if (TextUtils.isEmpty(inputSearchText))
            getView().resetTitleName();
        else
            getView().setTitleName(inputSearchText);
        loadListDataWithClean();
    }

    /**
     * 当普通的控件点击时调用的方法
     */
    @Override
    public void onViewClick() {
        int clickId = getView().getClickId();
        if (clickId == R.id.iv_app_title_layout_search) {
            getView().showSearchPop(getView().getInputSearchText());
            return;
        }
        switch (clickId) {
            case R.id.cbx_app_title_layout_right:
                getView().showSelectFilterPop();
                break;
            case R.id.tv_filter_cancel://取消的点击事件
            case R.id.tv_filter_confirm://确认的点击事件
                if (clickId == R.id.tv_filter_confirm) {
                    onFilterJobSelect(getView().getSelectFilterList(), true);
                }
                break;
        }
        getView().layoutStartOut();
        getView().closeGrayBg();
    }

    private void onFilterJobSelect(List<FilterSelectBean> selectFilterList, boolean isNeedAdd) {
        mHopeJobId = "";
//                    if (selectFilterList.isEmpty()) {
//                        getView().showToast("请选择内容后点击确认");
//                        return;
//                    }
        if (!selectFilterList.isEmpty()) {
            String filterName = "";
            String[] items = new String[selectFilterList.size()];
            int i = 0;
            for (FilterSelectBean selectBean : selectFilterList) {
                mHopeJobId += selectBean.getId() + ",";
                filterName += selectBean.getValue() + "、";
                items[i] = selectBean.getValue();
                i++;
            }
            mHopeJobId = mHopeJobId.replace("null", "");
            mHopeJobId = mHopeJobId.substring(0, mHopeJobId.length() - 1);
            getView().setupFilterName(filterName);
            if (isNeedAdd) {
                FilterSelectBean filterSelectBean = getView().getFilterSelectBean();
                if (filterSelectBean == null) {
                    filterSelectBean = mModule.getJobPareCateFilterList().get(0);
                }
                FilterItemView.DataHolder holder = FilterItemView.DataHolder.obtain(getView().findViewById(getView().getCheckId()), "职位", filterSelectBean.getValue(), items);
                mFilterSelectCount = getView().addFilterItemToPop(holder, false);
                getView().showTitleRightView(mFilterSelectCount);
            }
            loadListDataWithClean();
        } else {
            getView().setupFilterName("职位");
        }
    }

    @Override
    public void onFilterItemChildClose() {
        String filterRemoveChildName = getView().getFilterRemoveChildName();
        int filterViewId = getView().getFilterRemoveDataHolder().getFilterViewId();
        switch (filterViewId) {
            case R.id.position_filter_job:
                List<FilterSelectBean> childCateFilterList = mModule.getFirstJobChildCateFilterList();
                List<FilterSelectBean> selectBeanList = new ArrayList<>();
                for (FilterSelectBean filterSelectBean : childCateFilterList) {
                    if (filterSelectBean.getValue().equals(filterRemoveChildName)) {
                        filterSelectBean.setSelect(false);
                    }
                    if (filterSelectBean.isSelect()) {
                        selectBeanList.add(filterSelectBean);
                    }
                }
                onFilterJobSelect(selectBeanList, false);
                break;
        }
    }

    @Override
    public void onFilterItemClose() {
        mFilterSelectCount--;
        FilterItemView.DataHolder holder = getView().getFilterRemoveDataHolder();
        View filterView = getView().findViewById(holder.getFilterViewId());
        if (filterView == null || !(filterView instanceof TextView))
            return;
        switch (holder.getFilterViewId()) {
            case R.id.job_filter_industry://行业类别
                mIndustryId = null;
                resetAllFilterItemSelect(mModule.getIndustryFilterList());
                ((TextView) filterView).setText("行业");
                break;
            case R.id.job_filter_job_type://工作类型
                mJobTypeId = null;
                resetAllFilterItemSelect(mModule.getJobTypeFilterList());
                ((TextView) filterView).setText("工作类型");
                break;
            case R.id.job_filter_range://招聘范围
                mOrientedGroupId = null;
                resetAllFilterItemSelect(mModule.getJobOrientedList());
                ((TextView) filterView).setText("招聘范围");
                break;
            case R.id.job_filter_sal_range://薪资范围
                mHopeSalaryId = null;
                resetAllFilterItemSelect(mModule.getSalRangeFilterList());
                ((TextView) filterView).setText("薪资");
                break;
            case R.id.position_filter_address://工作地点
                mHopeJobAddressId = null;
                ((TextView) filterView).setText("地点");
                getView().onFilterAddressClean();
                break;
            case R.id.position_filter_education://学历
                mEducationId = null;
                resetAllFilterItemSelect(mModule.getEducationFilterList());
                ((TextView) filterView).setText("学历");
                break;
            case R.id.position_filter_job://职位类型
                mHopeJobId = null;
                resetAllFilterItemSelect(mModule.getJobPareCateFilterList(), mModule.getFirstJobChildCateFilterList());
                ((TextView) filterView).setText("职位");
                break;
            case R.id.position_filter_sal_range://薪资范围
                mHopeSalaryId = null;
                resetAllFilterItemSelect(mModule.getSalRangeFilterList());
                ((TextView) filterView).setText("薪资");
                break;
            case R.id.position_filter_job_time://工作年限
                mWorkYearId = null;
                resetAllFilterItemSelect(mModule.getJobTimeFilterList());
                ((TextView) filterView).setText("年限");
                break;
        }
        loadListDataWithClean();
        if (mFilterSelectCount == 0) {
            getView().hintTitleRightView();
        } else {
            getView().showTitleRightView(mFilterSelectCount);
        }
    }

    /**
     * 当筛选条件的PopupWindow点击重置按钮后的回调
     */
    @Override
    public void onFilterReset() {
        resetSearchFilter();
    }

    /**
     * 重置搜索的条件
     */
    private void resetSearchFilter() {
        if (getView().isPersonType()) {
            mHopeSalaryId = null;
            mJobTypeId = null;
            mIndustryId = null;
            mJobName = null;
            mOrientedGroupId = null;
            resetAllFilterItemSelect(mModule.getIndustryFilterList(), mModule.getJobTypeFilterList(), mModule.getJobOrientedList(), mModule.getSalRangeFilterList());
        } else {
            mUserName = null;
            mEducationId = null;
            mHopeJobId = null;
            mHopeSalaryId = null;
            mHopeJobAddressId = null;
            mWorkYearId = null;
            resetAllFilterItemSelect(mModule.getEducationFilterList(), mModule.getJobTimeFilterList(), mModule.getSalRangeFilterList(), mModule.getJobPareCateFilterList(), mModule.getFirstJobChildCateFilterList());
        }
        getView().resetTitleName();
        getView().resetFilterAllName();
        loadListDataWithClean();
        getView().hintTitleRightView();
    }

    /**
     * 将传入的列表中的数据设置为未选中的状态
     *
     * @param list
     */
    @SafeVarargs
    private final void resetAllFilterItemSelect(List<FilterSelectBean>... list) {
        if (list != null) {
            for (List<FilterSelectBean> filterSelectList : list) {
                for (FilterSelectBean filterSelectBean : filterSelectList) {
                    filterSelectBean.setSelect(false);
                }
            }
        }
    }


    /**
     * 请求简历列表筛选器
     *
     * @param isShowDialog
     */
    private void requestPositionFilter(boolean isShowDialog) {
        if (isShowDialog)
            getView().showProgressDialog("数据加载中....");
        mModule.requestPositionFilter(new OnDataCallback<Boolean>() {

            @Override
            public void onError(String errMsg) {
                closeDialog();
                getView().showToast(errMsg);
            }

            @Override
            public void onSuccessResult(Boolean data) {
                isLoadFilterSuccess = data;
                closeDialog();
            }
        });
    }

    /**
     * 请求求职信息列表筛选器
     *
     * @param isShowDialog
     */
    private void requestJobFilter(boolean isShowDialog) {
        if (isShowDialog)
            getView().showProgressDialog("数据加载中....");
        mModule.requestRecruitJobFilter(new OnDataCallback<Boolean>() {

            @Override
            public void onError(String errMsg) {
                closeDialog();
                getView().showToast(errMsg);
            }

            @Override
            public void onSuccessResult(Boolean data) {
                isLoadFilterSuccess = data;
                closeDialog();
            }
        });
    }

    //加载页面数据，并清空列表中的缓存数据
    private void loadListDataWithClean() {
        if (getView().isPersonType()) {
            if (mModule.getRecruitList() != null)
                mModule.getRecruitList().clear();
        } else {
            if (mModule.getRecruitCvList() != null)
                mModule.getRecruitCvList().clear();
        }
        loadListData(1);
    }

    /**
     * 关闭进度提示的进度条
     */
    private void closeDialog() {
        if (isLoadFilterSuccess && isLoadDataSuccess)
            getView().hindProgressDialog();
    }
}
