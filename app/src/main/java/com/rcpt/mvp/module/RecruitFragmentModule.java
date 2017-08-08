package com.rcpt.mvp.module;

import com.rcpt.base.HttpResult;
import com.rcpt.base.mvp.OnDataGetCallback;
import com.rcpt.bean.RecruitFragmentDataBean;
import com.rcpt.bean.RecruitFragmentListBean;
import com.rcpt.http.RetrofitManager;
import com.rcpt.http.api.ApiClient;

import java.util.ArrayList;
import java.util.List;

import rx.Subscriber;

/**
 * Created by lvzp on 2017/3/10.
 * 类描述：
 */

public class RecruitFragmentModule {

    private List<RecruitFragmentListBean> mListData;
    private RecruitFragmentDataBean mDataBean;

    public void requestRecruitList(final OnDataGetCallback<List<RecruitFragmentListBean>> callback) {
        RetrofitManager.toSubscribe(
                ApiClient.getApiService().getRecruitIndex()
                , new Subscriber<HttpResult<RecruitFragmentDataBean>>() {

                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(HttpResult<RecruitFragmentDataBean> recruitFragmentDataBeanHttpResult) {
                        if (recruitFragmentDataBeanHttpResult.isResult()) {
                            mDataBean = recruitFragmentDataBeanHttpResult.getData();
                            if (mDataBean != null) {
                                initList();
                                callback.onSuccessResult(mListData);
                            }
                        }
                    }
                }
        );
    }

    private void initList() {
        if (mListData == null)
            mListData = new ArrayList<>();
        else
            mListData.clear();
        List<RecruitFragmentDataBean.RecruitBean> recruitListBean = mDataBean.getRecruit();
        List<RecruitFragmentDataBean.SchoolListBean> schoolListBean = mDataBean.getSchoolList();
        if (recruitListBean != null) {
            RecruitFragmentListBean oneBean = new RecruitFragmentListBean();
            oneBean.setTitle("求职指南");
            List<RecruitFragmentListBean.SubBean> oneSubList = new ArrayList<>();
            for (RecruitFragmentDataBean.RecruitBean recruitBean : recruitListBean) {
                RecruitFragmentListBean.SubBean subBean = new RecruitFragmentListBean.SubBean();
                subBean.setType("one");
                subBean.setTitle(recruitBean.getTITLE());
                subBean.setSubTitle(recruitBean.getGuideContent());
                subBean.setId(recruitBean.getEmploymentId());
                subBean.setImage(recruitBean.getGuideThumbnail());
                oneSubList.add(subBean);
            }
            oneBean.setSubList(oneSubList);
            mListData.add(oneBean);
        }

        if (schoolListBean != null) {
            RecruitFragmentListBean twoBean = new RecruitFragmentListBean();
            twoBean.setTitle("校园展示");
            List<RecruitFragmentListBean.SubBean> twoSubList = new ArrayList<>();
            for (RecruitFragmentDataBean.SchoolListBean listBean : schoolListBean) {
                RecruitFragmentListBean.SubBean subBean = new RecruitFragmentListBean.SubBean();
                subBean.setType("two");
                subBean.setTitle(listBean.getSchoolName());
                subBean.setSubTitle(listBean.getCreationTime());
                subBean.setId(listBean.getSchoolId());
                subBean.setImage(listBean.getSchoolLog());
                twoSubList.add(subBean);
            }
            twoBean.setSubList(twoSubList);

            mListData.add(twoBean);
        }

    }

    public List<RecruitFragmentListBean> getListData() {
        return mListData;
    }
}
