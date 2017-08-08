package com.rcpt.mvp.module;

import android.content.Context;

import com.rcpt.base.HttpResult;
import com.rcpt.base.mvp.OnDataGetCallback;
import com.rcpt.bean.FilterSelectBean;
import com.rcpt.bean.RecruitFragmentListBean;
import com.rcpt.bean.SchoolListBean;
import com.rcpt.bean.ScrollFilterBean;
import com.rcpt.http.ProgressSubscriber;
import com.rcpt.http.RequestImpl;
import com.rcpt.http.RetrofitManager;
import com.rcpt.http.api.ApiClient;

import java.util.ArrayList;
import java.util.List;

import rx.Subscriber;

/**
 * Created by lvzp on 2017/3/29.
 * 类描述：
 */

public class SchoolListModule {

    private List<RecruitFragmentListBean.SubBean> mSchoolListData;
    private boolean isEnd;
    private List<FilterSelectBean> mTypeFilterList;
    private List<FilterSelectBean> mBatchFilterList;

    /**
     * 请求获取院校的筛选过滤器
     *
     * @param callback
     */
    public void requestSchoolFilterList(final OnDataGetCallback<Boolean> callback) {
        RetrofitManager.toSubscribe(
                ApiClient.getApiService().getScrollFilter(),
                new Subscriber<HttpResult<ScrollFilterBean>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.onSuccessResult(false);
                    }

                    @Override
                    public void onNext(HttpResult<ScrollFilterBean> scrollFilterBeanHttpResult) {
                        ScrollFilterBean data = scrollFilterBeanHttpResult.getData();
                        mBatchFilterList = createBatchFilterList(data.getBatchList());
                        mTypeFilterList = createTypeFilterList(data.getTypeList());
                        callback.onSuccessResult(true);
                    }
                }
        );
    }

    /**
     * 请求获取院校的列表
     *
     * @param context
     * @param batch
     * @param schoolType
     * @param pageNum
     * @param callback
     */
    public void requestSchoolList(Context context, String batch, String schoolType, int pageNum, final OnDataGetCallback<List<RecruitFragmentListBean.SubBean>> callback) {
        RetrofitManager.toSubscribe(
                ApiClient.getApiService().getSchoolList(batch, schoolType, pageNum)
                , new ProgressSubscriber<HttpResult<SchoolListBean>>(context, new RequestImpl<HttpResult<SchoolListBean>>() {
                    @Override
                    public void onNext(HttpResult<SchoolListBean> result) {
                        initPageData(result.getData());
                        callback.onSuccessResult(mSchoolListData);
                    }
                })
        );
    }

    private void initPageData(SchoolListBean data) {
        isEnd = !data.isNext();
        List<RecruitFragmentListBean.SubBean> currentPageData = new ArrayList<>();
        List<SchoolListBean.SchooLlistBean> schooLlist = data.getSchooLlist();
        if (schooLlist != null && schooLlist.size() > 0) {
            for (SchoolListBean.SchooLlistBean bean : schooLlist) {
                RecruitFragmentListBean.SubBean subBean = new RecruitFragmentListBean.SubBean();
                subBean.setId(bean.getSchoolId());
                subBean.setImage(bean.getSchoolLog());
                subBean.setTitle(bean.getSchoolName());
                subBean.setSubTitle(bean.getCreationTime());
                currentPageData.add(subBean);
            }
        }
        if (mSchoolListData == null) {
            mSchoolListData = currentPageData;
        } else {
            mSchoolListData.addAll(currentPageData);
        }

    }

    //////////创建类型的筛选器////////////
    private List<FilterSelectBean> createTypeFilterList(List<ScrollFilterBean.TypeListBean> sourceList) {
        List<FilterSelectBean> list = new ArrayList<>();
        if (sourceList != null && !sourceList.isEmpty()) {
            for (ScrollFilterBean.TypeListBean bean : sourceList) {
                list.add(new FilterSelectBean(bean.getType(), bean.getDistinguish_id(), false));
            }
        }
        return list;
    }

    ///////创建批次的筛选器//////
    private List<FilterSelectBean> createBatchFilterList(List<ScrollFilterBean.BatchListBean> sourceList) {
        List<FilterSelectBean> list = new ArrayList<>();
        if (sourceList != null && !sourceList.isEmpty()) {
            for (ScrollFilterBean.BatchListBean bean : sourceList) {
                list.add(new FilterSelectBean(bean.getBatch(), bean.getDistinguish_id(), false));
            }
        }
        return list;
    }

    /**
     * 获取类型的筛选列表
     *
     * @return
     */
    public List<FilterSelectBean> getTypeFilterList() {
        return mTypeFilterList;
    }

    /**
     * 获取批次的筛选列表
     *
     * @return
     */
    public List<FilterSelectBean> getBatchFilterList() {
        return mBatchFilterList;
    }

    public List<RecruitFragmentListBean.SubBean> getSchoolListData() {
        return mSchoolListData;
    }

    public boolean isEnd() {
        return isEnd;
    }
}
