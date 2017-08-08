package com.rcpt.mvp.module;

import android.content.Context;
import android.support.v7.view.menu.MenuItemImpl;

import com.rcpt.R;
import com.rcpt.base.HttpResult;
import com.rcpt.base.mvp.MenuModuleIml;
import com.rcpt.base.mvp.OnDataGetCallback;
import com.rcpt.bean.AttrSelectBean;
import com.rcpt.bean.CVJobIntentInfoBean;
import com.rcpt.bean.InputMenuBean;
import com.rcpt.bean.JobCategoryBean;
import com.rcpt.http.ProgressSubscriber;
import com.rcpt.http.RequestImpl;
import com.rcpt.http.RetrofitManager;
import com.rcpt.http.api.ApiClient;

import java.util.List;
import java.util.Map;

/**
 * Created by lvzp on 2017/3/8.
 * 类描述：创建简历列表的Module
 */

public class CreateCVInfoMenuModule extends MenuModuleIml<InputMenuBean> {

    private List<AttrSelectBean> mAttrSelectList;
    private List<JobCategoryBean> mJobCategoryList;

    @Override
    public InputMenuBean getMenuBean(MenuItemImpl menuItem) {
        InputMenuBean bean = new InputMenuBean();
        if (menuItem.getItemId() == R.id.cv_menu_item_work_time_end || menuItem.getItemId() == R.id.cv_menu_item_work_describe){
            bean.setEdit(true);
        }
        bean.setNeedInput(menuItem.isEnabled());
        return bean;
    }

    /**
     * 获取求职意向信息
     *
     * @param context
     * @param callback
     */
    public void requestCVJboIntentInfo(Context context, String userId, String cvId, final OnDataGetCallback<CVJobIntentInfoBean> callback) {
        RetrofitManager.toSubscribe(
                ApiClient.getApiService().getCVJobIntentInfo(userId, cvId)
                , new ProgressSubscriber<HttpResult<CVJobIntentInfoBean>>(context, new RequestImpl<HttpResult<CVJobIntentInfoBean>>() {
                    @Override
                    public void onNext(HttpResult<CVJobIntentInfoBean> result) {
                        callback.onSuccessResult(result.getData());
                    }
                })
        );
    }

    /**
     * 创建新的简历
     *
     * @param context
     * @param params
     * @param callback
     */
    public void requestCreateNewCV(Context context,Map<String,String> params, final OnDataGetCallback<String> callback) {
        RetrofitManager.toSubscribe(
                ApiClient.getApiService().createNewCv(params)
                , new ProgressSubscriber<HttpResult<String>>(context, new RequestImpl<HttpResult<String>>() {
                    @Override
                    public void onNext(HttpResult<String> stringHttpResult) {
                       callback.onSuccessResult(stringHttpResult.getMsg());
                    }
                })
        );
    }
    /**
     * 上传求职意向信息
     *
     * @param context
     * @param params
     * @param callback
     */
    public void updateJobIntentInfo(Context context, Map<String, String> params, final OnDataGetCallback<String> callback) {
        RetrofitManager.toSubscribe(
                ApiClient.getApiService().updateCVJobIntent(params)
                , new ProgressSubscriber<HttpResult<String>>(context, new RequestImpl<HttpResult<String>>() {
                    @Override
                    public void onNext(HttpResult<String> result) {
                        callback.onSuccessResult(result.getMsg());
                    }
                })
        );
    }

    /**
     * 上传保存教育背景
     *
     * @param context
     * @param params
     * @param callback
     */
    public void uploadEducationInfo(Context context, Map<String, String> params, final OnDataGetCallback<String> callback) {
        RetrofitManager.toSubscribe(
                ApiClient.getApiService().saveEducationInfo(params)
                , new ProgressSubscriber<HttpResult<String>>(context, new RequestImpl<HttpResult<String>>() {
                    @Override
                    public void onNext(HttpResult<String> stringHttpResult) {
                        callback.onSuccessResult(stringHttpResult.getMsg());
                    }
                })
        );
    }

    /**
     * 上传保存项目经验
     *
     * @param context
     * @param params
     * @param callback
     */
    public void uploadProjectInfo(Context context, Map<String, String> params, final OnDataGetCallback<String> callback) {
        RetrofitManager.toSubscribe(
                ApiClient.getApiService().saveCvProject(params)
                , new ProgressSubscriber<HttpResult<String>>(context, new RequestImpl<HttpResult<String>>() {
                    @Override
                    public void onNext(HttpResult<String> stringHttpResult) {
                        callback.onSuccessResult(stringHttpResult.getMsg());
                    }
                })
        );
    }

    /**
     * 上传保存工作经历
     *
     * @param context
     * @param params
     * @param callback
     */
    public void uploadWorExInfo(Context context, Map<String, String> params, final OnDataGetCallback<String> callback) {
        RetrofitManager.toSubscribe(
                ApiClient.getApiService().saveWorkExperience(params)
                , new ProgressSubscriber<HttpResult<String>>(context, new RequestImpl<HttpResult<String>>() {
                    @Override
                    public void onNext(HttpResult<String> stringHttpResult) {
                        callback.onSuccessResult(stringHttpResult.getMsg());
                    }
                })
        );
    }

    /**
     * 上传保存教育背景
     *
     * @param context
     * @param params
     * @param callback
     */
    public void editEducationInfo(Context context, Map<String, String> params, final OnDataGetCallback<String> callback) {
        RetrofitManager.toSubscribe(
                ApiClient.getApiService().editCVEducationInfo(params)
                , new ProgressSubscriber<HttpResult<String>>(context, new RequestImpl<HttpResult<String>>() {
                    @Override
                    public void onNext(HttpResult<String> stringHttpResult) {
                        callback.onSuccessResult(stringHttpResult.getMsg());
                    }
                })
        );
    }

    /**
     * 上传保存项目经验
     *
     * @param context
     * @param params
     * @param callback
     */
    public void editProjectInfo(Context context, Map<String, String> params, final OnDataGetCallback<String> callback) {
        RetrofitManager.toSubscribe(
                ApiClient.getApiService().editCvProject(params)
                , new ProgressSubscriber<HttpResult<String>>(context, new RequestImpl<HttpResult<String>>() {
                    @Override
                    public void onNext(HttpResult<String> stringHttpResult) {
                        callback.onSuccessResult(stringHttpResult.getMsg());
                    }
                })
        );
    }

    /**
     * 上传保存工作经历
     *
     * @param context
     * @param params
     * @param callback
     */
    public void editWorExInfo(Context context, Map<String, String> params, final OnDataGetCallback<String> callback) {
        RetrofitManager.toSubscribe(
                ApiClient.getApiService().editWorkExperience(params)
                , new ProgressSubscriber<HttpResult<String>>(context, new RequestImpl<HttpResult<String>>() {
                    @Override
                    public void onNext(HttpResult<String> stringHttpResult) {
                        callback.onSuccessResult(stringHttpResult.getMsg());
                    }
                })
        );
    }

    /**
     * 获取学历列表
     *
     * @param callback
     */
    public void requestEducationList(Context context, final OnDataGetCallback<List<AttrSelectBean>> callback) {
        RetrofitManager.toSubscribe(
                ApiClient.getApiService().getEducation(),
                new ProgressSubscriber<HttpResult<List<AttrSelectBean>>>(context, new RequestImpl<HttpResult<List<AttrSelectBean>>>() {
                    @Override
                    public void onNext(HttpResult<List<AttrSelectBean>> listHttpResult) {
                        mAttrSelectList = listHttpResult.getData();
                        callback.onSuccessResult(mAttrSelectList);
                    }
                })
        );
    }

    /**
     * 获取企业类别
     *
     * @param context
     * @param callback
     */
    public void requestCompanyTypeList(Context context, final OnDataGetCallback<List<AttrSelectBean>> callback) {
        RetrofitManager.toSubscribe(
                ApiClient.getApiService().getBusinessType()
                , new ProgressSubscriber<HttpResult<List<AttrSelectBean>>>(context, new RequestImpl<HttpResult<List<AttrSelectBean>>>() {
                    @Override
                    public void onNext(HttpResult<List<AttrSelectBean>> listHttpResult) {
                        mAttrSelectList = listHttpResult.getData();
                        callback.onSuccessResult(mAttrSelectList);
                    }
                })
        );
    }

    /**
     * 请求薪资列表
     *
     * @param callback
     */
    public void requestSalaryList(Context context, final OnDataGetCallback<List<AttrSelectBean>> callback) {
        RetrofitManager.toSubscribe(
                ApiClient.getApiService().getSalaryList()
                , new ProgressSubscriber<>(context, new RequestImpl<HttpResult<List<AttrSelectBean>>>() {
                    @Override
                    public void onNext(HttpResult<List<AttrSelectBean>> stringHttpResult) {
                        mAttrSelectList = stringHttpResult.getData();
                        callback.onSuccessResult(mAttrSelectList);
                    }
                })
        );
    }

    /**
     * 请求求职状态列表
     *
     * @param callback
     */
    public void requestWorkStatusList(Context context, final OnDataGetCallback<List<AttrSelectBean>> callback) {
        RetrofitManager.toSubscribe(
                ApiClient.getApiService().getWorkStatusList()
                , new ProgressSubscriber<>(context, new RequestImpl<HttpResult<List<AttrSelectBean>>>() {
                    @Override
                    public void onNext(HttpResult<List<AttrSelectBean>> stringHttpResult) {
                        mAttrSelectList = stringHttpResult.getData();
                        callback.onSuccessResult(mAttrSelectList);
                    }
                })
        );
    }

    /**
     * 请求工作类型列表
     *
     * @param callback
     */
    public void requestWorkTypeList(Context context, final OnDataGetCallback<List<AttrSelectBean>> callback) {
        RetrofitManager.toSubscribe(
                ApiClient.getApiService().getWorkTypeList()
                , new ProgressSubscriber<>(context, new RequestImpl<HttpResult<List<AttrSelectBean>>>() {
                    @Override
                    public void onNext(HttpResult<List<AttrSelectBean>> stringHttpResult) {
                        mAttrSelectList = stringHttpResult.getData();
                        callback.onSuccessResult(mAttrSelectList);
                    }
                })
        );
    }

    /**
     * 请求职位类别列表
     *
     * @param context
     * @param callback
     */
    public void requestJobCategoryList(Context context, final OnDataGetCallback<List<JobCategoryBean>> callback) {
        if (mJobCategoryList != null) {
            callback.onSuccessResult(mJobCategoryList);
        } else
            RetrofitManager.toSubscribe(
                    ApiClient.getApiService().getJobCatrgoryList()
                    , new ProgressSubscriber<HttpResult<List<JobCategoryBean>>>(context, new RequestImpl<HttpResult<List<JobCategoryBean>>>() {
                        @Override
                        public void onNext(HttpResult<List<JobCategoryBean>> listHttpResult) {
                            mJobCategoryList = listHttpResult.getData();
                            callback.onSuccessResult(mJobCategoryList);
                        }
                    })
            );
    }

    public List<AttrSelectBean> getAttrSelectList() {
        return mAttrSelectList;
    }

    public List<JobCategoryBean> getJobCategoryList() {
        return mJobCategoryList;
    }
}
