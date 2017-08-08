package com.rcpt.mvp.module;

import android.content.Context;

import com.rcpt.base.HttpResult;
import com.rcpt.base.mvp.OnDataCallback;
import com.rcpt.base.mvp.OnDataGetCallback;
import com.rcpt.bean.AttrSelectBean;
import com.rcpt.bean.FilterSelectBean;
import com.rcpt.bean.RecruitJobFilterBean;
import com.rcpt.bean.RecruitListBean;
import com.rcpt.bean.RecruitPositionFilterBean;
import com.rcpt.http.ProgressSubscriber;
import com.rcpt.http.RequestImpl;
import com.rcpt.http.RetrofitManager;
import com.rcpt.http.api.ApiClient;
import com.rcpt.http.utils.NetworkUtils;

import java.util.ArrayList;
import java.util.List;

import rx.Subscriber;

/**
 * Created by lvzp on 2017/2/24.
 * 类描述：
 */

public class RecruitListModule {

    private List<RecruitListBean.JobListBean> mRecruitJobList;
    private List<RecruitListBean.CVListBean> mRecruitCVList;
    private boolean isEnd;

    private RecruitPositionFilterBean mPositionFilterBean;
    private RecruitJobFilterBean mJobFilterBean;
    private List<RecruitPositionFilterBean.JobChilCateListBean> mPositionChildList;

    private List<FilterSelectBean> mEducationFilterList;//学历
    private List<FilterSelectBean> mJobTimeFilterList;//工作年限
    private List<FilterSelectBean> mSalRangeFilterList;//薪资范围
    private List<FilterSelectBean> mJobPareCateFilterList;//职业父类别
    private List<FilterSelectBean> mJobChildCateFilterList;//职业子类别

    private List<FilterSelectBean> mIndustryFilterList;//行业
    private List<FilterSelectBean> mJobTypeFilterList;//工作类型
    private List<FilterSelectBean> mJobOrientedList;//招聘范围

    public void requestJobList(String userType, int pageNum, String HopeJobId, String cityId, String eduBackGround, String workyear, String hopeSalaryId, String JobType, String Salary, String WorkTypeID, String mOrientedGroupId, String name, String JobName, final OnDataCallback<RecruitListBean> callback) {

        RetrofitManager.toSubscribe(
                ApiClient.getApiService().getRecruitList(userType, pageNum, HopeJobId, cityId, eduBackGround, workyear, hopeSalaryId, JobType, Salary, WorkTypeID, mOrientedGroupId, name, JobName)
                , new Subscriber<HttpResult<RecruitListBean>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.onError(NetworkUtils.getErrorMsg(e));
                    }

                    @Override
                    public void onNext(HttpResult<RecruitListBean> result) {
                        RecruitListBean bean = result.getData();
                        isEnd = !bean.isNext();
                        if (mRecruitJobList == null) {
                            mRecruitJobList = bean.getPersonlist();
                        } else {
                            mRecruitJobList.addAll(bean.getPersonlist());
                        }
                        if (mRecruitCVList == null) {
                            mRecruitCVList = bean.getTalentPoolList();
                        } else {
                            mRecruitCVList.addAll(bean.getTalentPoolList());
                        }
                        callback.onSuccessResult(bean);
                    }
                }
        );
    }

    /**
     * 简历列表筛选请求----简历列表---
     *
     * @param callback
     */
    public void requestPositionFilter(final OnDataCallback<Boolean> callback) {
        RetrofitManager.toSubscribe(
                ApiClient.getApiService().getRecruitPositionFilter()
                , new Subscriber<HttpResult<RecruitPositionFilterBean>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.onError(NetworkUtils.getErrorMsg(e));
                    }

                    @Override
                    public void onNext(HttpResult<RecruitPositionFilterBean> recruitPositionFilterBeanHttpResult) {
                        mPositionFilterBean = recruitPositionFilterBeanHttpResult.getData();
                        mEducationFilterList = createFilterList(mPositionFilterBean.getEducationList());
                        mJobTimeFilterList = createFilterList(mPositionFilterBean.getJobTimeList());
                        mSalRangeFilterList = createFilterList(mPositionFilterBean.getSalRangeList());
                        mJobPareCateFilterList = createJobPareCateFilterList();
                        mJobChildCateFilterList = createFirstJobChildCateFilterList();
                        callback.onSuccessResult(true);
                    }
                }
        );
    }

    /**
     * 简历列表筛选请求---工作职位子类型---
     *
     * @param callback
     */
    public void requestRecruitChildPosition(Context context, String parentId, final OnDataGetCallback<List<FilterSelectBean>> callback) {
        RetrofitManager.toSubscribe(
                ApiClient.getApiService().getRecruitChildPosition(parentId)
                , new ProgressSubscriber<>(context, new RequestImpl<HttpResult<List<RecruitPositionFilterBean.JobChilCateListBean>>>() {
                    @Override
                    public void onNext(HttpResult<List<RecruitPositionFilterBean.JobChilCateListBean>> result) {
                        mPositionChildList = result.getData();
                        mJobChildCateFilterList = createJobChildCateFilterList();
                        callback.onSuccessResult(mJobChildCateFilterList);
                    }
                })
        );
    }


    /**
     * 职位筛选请求---职位列表---
     *
     * @param callback
     */
    public void requestRecruitJobFilter(final OnDataCallback<Boolean> callback) {
        RetrofitManager.toSubscribe(
                ApiClient.getApiService().getRecruitJobFilter()
                , new Subscriber<HttpResult<RecruitJobFilterBean>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.onError(NetworkUtils.getErrorMsg(e));
                    }

                    @Override
                    public void onNext(HttpResult<RecruitJobFilterBean> recruitPositionFilterBeanHttpResult) {
                        mJobFilterBean = recruitPositionFilterBeanHttpResult.getData();
                        mSalRangeFilterList = createFilterList(mJobFilterBean.getSalRangeList());
                        mJobTypeFilterList = createFilterList(mJobFilterBean.getJobTypeList());
                        mIndustryFilterList = createFilterList(mJobFilterBean.getIndustryList());
                        mJobOrientedList = createFilterList(mJobFilterBean.getOrientedList());
                        callback.onSuccessResult(true);
                    }
                }
        );
    }

    public boolean isEnd() {
        return isEnd;
    }

    public List<RecruitListBean.JobListBean> getRecruitList() {
        return mRecruitJobList;
    }

    public List<RecruitListBean.CVListBean> getRecruitCvList() {
        return mRecruitCVList;
    }


    public RecruitPositionFilterBean getPositionFilterBean() {
        return mPositionFilterBean;
    }

    public RecruitJobFilterBean getJobFilterBean() {
        return mJobFilterBean;
    }

    public List<FilterSelectBean> getIndustryFilterList() {
        return mIndustryFilterList;
    }

    public List<FilterSelectBean> getJobTypeFilterList() {
        return mJobTypeFilterList;
    }

    public List<RecruitPositionFilterBean.JobChilCateListBean> getPositionChildList() {
        return mPositionChildList;
    }

    public List<FilterSelectBean> getJobTimeFilterList() {
        return mJobTimeFilterList;
    }

    public List<FilterSelectBean> getSalRangeFilterList() {
        return mSalRangeFilterList;
    }

    public List<FilterSelectBean> getJobPareCateFilterList() {
        return mJobPareCateFilterList;
    }

    public List<FilterSelectBean> getFirstJobChildCateFilterList() {
        return mJobChildCateFilterList;
    }

    public List<FilterSelectBean> getJobOrientedList() {
        return mJobOrientedList;
    }

    public List<FilterSelectBean> getEducationFilterList() {
        return mEducationFilterList;
    }


    //////////私有处理方法////////////
    private List<FilterSelectBean> createFilterList(List<AttrSelectBean> sourceList) {
        List<FilterSelectBean> list = new ArrayList<>();
        if (sourceList != null && !sourceList.isEmpty()) {
            for (AttrSelectBean bean : sourceList) {
                list.add(new FilterSelectBean(bean.getValue(), bean.getDistinguishId(), false));
            }
        }
        return list;
    }

    private List<FilterSelectBean> createJobPareCateFilterList() {
        List<FilterSelectBean> list = new ArrayList<>();
        List<RecruitPositionFilterBean.JobPareCateListBean> educationList = mPositionFilterBean.getJobPareCateList();
        if (educationList != null && !educationList.isEmpty()) {
            for (RecruitPositionFilterBean.JobPareCateListBean bean : educationList) {
                list.add(new FilterSelectBean(bean.getPositionName(), bean.getParentId(), false));
            }
        }
        return list;
    }

    private List<FilterSelectBean> createFirstJobChildCateFilterList() {
        List<FilterSelectBean> list = new ArrayList<>();
        List<RecruitPositionFilterBean.JobChilCateListBean> educationList = mPositionFilterBean.getJobChilCateList();
        if (educationList != null && !educationList.isEmpty()) {
            for (RecruitPositionFilterBean.JobChilCateListBean bean : educationList) {
                list.add(new FilterSelectBean(bean.getPositionName(), bean.getPositionId(), false));
            }
        }
        return list;
    }

    private List<FilterSelectBean> createJobChildCateFilterList() {
        List<FilterSelectBean> list = new ArrayList<>();
        if (mPositionChildList != null && !mPositionChildList.isEmpty()) {
            for (RecruitPositionFilterBean.JobChilCateListBean bean : mPositionChildList) {
                list.add(new FilterSelectBean(bean.getPositionName(), bean.getPositionId(), false));
            }
        }
        return list;
    }


}
