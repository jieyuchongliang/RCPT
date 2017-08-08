package com.rcpt.mvp.module;

import android.content.Context;
import android.support.annotation.ColorRes;
import android.support.annotation.IdRes;

import com.rcpt.R;
import com.rcpt.base.HttpResult;
import com.rcpt.base.mvp.OnDataGetCallback;
import com.rcpt.bean.InstituteBean;
import com.rcpt.http.ProgressSubscriber;
import com.rcpt.http.RequestImpl;
import com.rcpt.http.RetrofitManager;
import com.rcpt.http.api.ApiClient;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by lvzp on 2017/2/22.
 * 类描述：
 */

public class HomeModule {

    private List<InstituteBean.NoticeBean> mAfficheList;
    private List<InstituteBean.PicBean> mPicBean;
    private List<String> mBannerImages;

    private List<ItemRes> mItemResList;

    private List<InstituteBean.InstitutionListBean> mInstituteList;

    public List<String> getBannerImages() {
        mBannerImages = new ArrayList<>();
        if(mPicBean != null && mPicBean.size() > 0){
            for (int i = 0; i < mPicBean.size(); i++) {
                mBannerImages.add(i,mPicBean.get(i).getFilePath());
            }
        }
        return mBannerImages;
    }

    public List<InstituteBean.NoticeBean> getAfficheList() {
        return mAfficheList;
    }

    public List<InstituteBean.InstitutionListBean> getInstituteList(){
        return mInstituteList;
    }


    public List<ItemRes> getItemResList() {
        if (mItemResList != null) {
            mItemResList.clear();
        } else {
            mItemResList = new ArrayList<>();
        }
        //bg_light_blue_round_corners;bg_red_round_corners;bg_yellow_round_corners;bg_green_round_corners
        mItemResList.add(new ItemRes(R.id.include_news, R.drawable.bg_light_blue_round_corners, R.drawable.ic_vector_home_news, "新闻"));
        mItemResList.add(new ItemRes(R.id.include_training, R.drawable.bg_red_round_corners, R.drawable.ic_vector_home_training, "培训晋升"));
        mItemResList.add(new ItemRes(R.id.include_consult, R.drawable.bg_yellow_round_corners, R.drawable.ic_vector_home_consult, "咨询服务"));
        mItemResList.add(new ItemRes(R.id.include_test, R.drawable.bg_green_round_corners, R.drawable.ic_vector_home_assess, "测评"));

        return mItemResList;
    }

    public class ItemRes {
        @IdRes
        public int id;
        @ColorRes
        public int itemBackground;
        @ColorRes
        public int itemSrc;
        public String title;

        public ItemRes(int id, int itemBackground, int itemSrc, String title) {
            this.id = id;
            this.itemBackground = itemBackground;
            this.itemSrc = itemSrc;
            this.title = title;
        }
    }

    public void getHomeIndustryList(final Context context,final OnDataGetCallback<List<InstituteBean.InstitutionListBean>> callback){
        RetrofitManager.toSubscribe(ApiClient.getApiService().getHomeInstitutionList(),new ProgressSubscriber<>
                (context, new RequestImpl<HttpResult<InstituteBean>>() {

            @Override
            public void onNext(HttpResult<InstituteBean> result) {
                InstituteBean bean = result.getData();
                mInstituteList = bean.getInstitutionList();
                mPicBean = bean.getPic();
                mAfficheList = bean.getNotice();
                callback.onSuccessResult(mInstituteList);
            }
        }));
    }

    public List<InstituteBean.InstitutionListBean> getmInstituteList(){
        return mInstituteList;
    }
}
