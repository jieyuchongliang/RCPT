package com.rcpt.mvp.module;

import android.content.Context;

import com.rcpt.base.HttpResult;
import com.rcpt.base.mvp.OnDataGetCallback;
import com.rcpt.bean.JobGuideListBean;
import com.rcpt.bean.RecruitFragmentListBean;
import com.rcpt.http.ProgressSubscriber;
import com.rcpt.http.RequestImpl;
import com.rcpt.http.RetrofitManager;
import com.rcpt.http.api.ApiClient;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lvzp on 2017/3/29.
 * 类描述：
 */

public class JobGuideFragmentModule {

    private List<RecruitFragmentListBean.SubBean> mJobGuideList;
    private boolean isEnd;

    public void requestJobGuideList(Context context, String id,int pageNum, final OnDataGetCallback<List<RecruitFragmentListBean.SubBean>> callback) {
        RetrofitManager.toSubscribe(
                ApiClient.getApiService().getJobGuideList(id,pageNum)
                , new ProgressSubscriber<HttpResult<JobGuideListBean>>(context, new RequestImpl<HttpResult<JobGuideListBean>>() {
                    @Override
                    public void onNext(HttpResult<JobGuideListBean> result) {
                        initData(result.getData());
                        callback.onSuccessResult(mJobGuideList);
                    }
                })
        );
    }

    private void initData(JobGuideListBean data) {
        isEnd = !data.isNext();
        List<RecruitFragmentListBean.SubBean> currentPageData = new ArrayList<>();
        List<JobGuideListBean.RecruitlistBean> recruitlist = data.getRecruitlist();
        if (recruitlist != null && recruitlist.size() > 0) {
            for (JobGuideListBean.RecruitlistBean bean : recruitlist) {
                RecruitFragmentListBean.SubBean subBean = new RecruitFragmentListBean.SubBean();
                subBean.setId(bean.getEmploymentId());
                subBean.setImage(bean.getGuideThumbnail());
                subBean.setTitle(bean.getTitle());
                subBean.setSubTitle(bean.getGuideContent());
                subBean.setUpdateTimeStamp(bean.getUpdateTimeStamp());
                currentPageData.add(subBean);
            }
        }
        if (mJobGuideList == null) {
            mJobGuideList = currentPageData;
        } else {
            mJobGuideList.addAll(currentPageData);
        }
    }

    public boolean isEnd() {
        return isEnd;
    }

    public List<RecruitFragmentListBean.SubBean> getJobGuideList() {
        return mJobGuideList;
    }
}
