package com.rcpt.mvp.module;

import android.content.Context;

import com.rcpt.base.HttpResult;
import com.rcpt.base.mvp.OnDataGetCallback;
import com.rcpt.bean.CVEducationListBean;
import com.rcpt.bean.CVInfoListBean;
import com.rcpt.bean.CVProjectExListBean;
import com.rcpt.bean.CVWorkExListBean;
import com.rcpt.http.ProgressSubscriber;
import com.rcpt.http.RequestImpl;
import com.rcpt.http.RetrofitManager;
import com.rcpt.http.api.ApiClient;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lvzp on 2017/3/9.
 * 类描述：
 */

public class CVInfoListModule {

    private List<CVInfoListBean> mCVInfoEducationList;//教育背景的列表
    private List<CVInfoListBean> mCVInfoWorkHistoryList;//工作/实习经历的数据列表
    private List<CVInfoListBean> mCVInfoProjectExperienceList;//项目经验

    private List<CVEducationListBean> mEducationListBean;
    private List<CVWorkExListBean> mWorkExListBean;
    private List<CVProjectExListBean> mProjectExListBean;

    /**
     * 获取教育背景列表
     *
     * @param context
     * @param userId
     * @param cvId
     * @param callback
     */
    public void requestCVEducationList(Context context, String userId, String cvId, final OnDataGetCallback<List<CVInfoListBean>> callback) {
        RetrofitManager.toSubscribe(
                ApiClient.getApiService().getCVEducationList(userId, cvId)
                , new ProgressSubscriber<>(context, new RequestImpl<HttpResult<List<CVEducationListBean>>>() {
                    @Override
                    public void onNext(HttpResult<List<CVEducationListBean>> cvEducationListBeanHttpResult) {
                        List<CVEducationListBean> data = cvEducationListBeanHttpResult.getData();
                        mEducationListBean = data;
                        initEducationList(data);
                        callback.onSuccessResult(mCVInfoEducationList);
                    }
                })
        );
    }

    /**
     * 获取工作经历列表
     *
     * @param context
     * @param userId
     * @param cvId
     * @param callback
     */
    public void requestCVWorkExList(Context context, String userId, String cvId, final OnDataGetCallback<List<CVInfoListBean>> callback) {
        RetrofitManager.toSubscribe(
                ApiClient.getApiService().getCVExperienceList(userId, cvId)
                , new ProgressSubscriber<>(context, new RequestImpl<HttpResult<List<CVWorkExListBean>>>() {
                    @Override
                    public void onNext(HttpResult<List<CVWorkExListBean>> cvEducationListBeanHttpResult) {
                        List<CVWorkExListBean> data = cvEducationListBeanHttpResult.getData();
                        mWorkExListBean = data;
                        initWorkHistoryList(data);
                        callback.onSuccessResult(mCVInfoWorkHistoryList);
                    }
                })
        );
    }

    /**
     * 获取项目经验列表
     *
     * @param context
     * @param userId
     * @param cvId
     * @param callback
     */
    public void requestCVProjectExList(Context context, String userId, String cvId, final OnDataGetCallback<List<CVInfoListBean>> callback) {
        RetrofitManager.toSubscribe(
                ApiClient.getApiService().getCVProjectExList(userId, cvId)
                , new ProgressSubscriber<>(context, new RequestImpl<HttpResult<List<CVProjectExListBean>>>() {
                    @Override
                    public void onNext(HttpResult<List<CVProjectExListBean>> listHttpResult) {
                        List<CVProjectExListBean> data = listHttpResult.getData();
                        mProjectExListBean = data;
                        initProjectExperienceList(data);
                        callback.onSuccessResult(mCVInfoProjectExperienceList);
                    }
                })
        );
    }

    public void deleteEdu(Context context, String userId, String infoId, String cvId, final OnDataGetCallback<String> callback) {
        RetrofitManager.toSubscribe(
                ApiClient.getApiService().deleteEdu(userId, infoId, cvId)
                , new ProgressSubscriber<HttpResult<String>>(context, new RequestImpl<HttpResult<String>>() {
                    @Override
                    public void onNext(HttpResult<String> result) {
                        callback.onSuccessResult(result.getMsg());
                    }
                })
        );
    }

    public void deletePro(Context context, String userId, String infoId, String cvId, final OnDataGetCallback<String> callback) {
        RetrofitManager.toSubscribe(
                ApiClient.getApiService().deletePro(userId, infoId, cvId)
                , new ProgressSubscriber<HttpResult<String>>(context, new RequestImpl<HttpResult<String>>() {
                    @Override
                    public void onNext(HttpResult<String> result) {
                        callback.onSuccessResult(result.getMsg());
                    }
                })
        );
    }

    public void deleteWork(Context context, String userId, String infoId, String cvId, final OnDataGetCallback<String> callback) {
        RetrofitManager.toSubscribe(
                ApiClient.getApiService().deleteWork(userId, infoId, cvId)
                , new ProgressSubscriber<HttpResult<String>>(context, new RequestImpl<HttpResult<String>>() {
                    @Override
                    public void onNext(HttpResult<String> result) {
                        callback.onSuccessResult(result.getMsg());
                    }
                })
        );
    }

    public List<CVEducationListBean> getEducationListBean() {
        return mEducationListBean;
    }

    public List<CVWorkExListBean> getWorkExListBean() {
        return mWorkExListBean;
    }

    public List<CVProjectExListBean> getProjectExListBean() {
        return mProjectExListBean;
    }

    public List<CVInfoListBean> getCVInfoEducationList() {
        return mCVInfoEducationList;
    }


    public List<CVInfoListBean> getCVInfoWorkHistoryList() {
        return mCVInfoWorkHistoryList;
    }


    public List<CVInfoListBean> getCVInfoProjectExperienceList() {
        return mCVInfoProjectExperienceList;
    }

    private void initProjectExperienceList(List<CVProjectExListBean> list) {
        if (mCVInfoProjectExperienceList == null)
            mCVInfoProjectExperienceList = new ArrayList<>();
        else
            mCVInfoProjectExperienceList.clear();
        for (CVProjectExListBean projectExBean : list) {
            CVInfoListBean bean = new CVInfoListBean();
            bean.setId(projectExBean.getProjectInfoId());
            bean.setPrimary(projectExBean.getProName());
            bean.setTime(projectExBean.getStartTime() + " - " + projectExBean.getEndTime());
            bean.setAdditional("开发工具：" + projectExBean.getTools());
            mCVInfoProjectExperienceList.add(bean);
        }
    }

    private void initWorkHistoryList(List<CVWorkExListBean> list) {
        if (mCVInfoWorkHistoryList == null)
            mCVInfoWorkHistoryList = new ArrayList<>();
        else
            mCVInfoWorkHistoryList.clear();
        for (CVWorkExListBean listBean : list) {
            CVInfoListBean bean = new CVInfoListBean();
            bean.setId(listBean.getWorkInfoId());
            bean.setTime(listBean.getWorkBeginDate() + " - " + listBean.getWorkEndDate());
            bean.setPrimary(listBean.getComName());
            bean.setAdditional(listBean.getJob());
            mCVInfoWorkHistoryList.add(bean);
        }
    }

    private void initEducationList(List<CVEducationListBean> listBeen) {
        if (mCVInfoEducationList == null)
            mCVInfoEducationList = new ArrayList<>();
        else
            mCVInfoEducationList.clear();
        for (CVEducationListBean listBean : listBeen) {
            CVInfoListBean bean = new CVInfoListBean();
            bean.setId(listBean.getEduInfoId());
            bean.setTime(listBean.getStartT() + " -- " + listBean.getEndT());
            bean.setPrimary(listBean.getSchName());
            bean.setAdditional(listBean.getEducation() + "|" + listBean.getSpecialty());
            mCVInfoEducationList.add(bean);
        }
    }
}
