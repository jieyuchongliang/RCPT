package com.rcpt.mvp.module;

import android.content.Context;

import com.rcpt.base.mvp.OnDataGetCallback;
import com.rcpt.bean.VideoListBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 860617003 on 2017/5/10.
 */

public class VideoTeacherInfoModule {

    private List<VideoListBean.VideoItemBean> mListData;

    public void requestVideoList(Context context, OnDataGetCallback<List<VideoListBean.VideoItemBean>> callback) {
        mListData = new ArrayList<>();
        for (int i = 0; i < 11; i++) {
            VideoListBean.VideoItemBean bean = new VideoListBean.VideoItemBean();
            bean.setPrice("213");
            bean.setOldPrice("233");
            bean.setCreateTime("2014-3-11");
            bean.setTitle("视频标题内容显示" + i);
            bean.setSubTitle("视频简介内容显示" + i);
            bean.setWatchNum("113");
            mListData.add(bean);
        }
        callback.onSuccessResult(mListData);
    }

}
