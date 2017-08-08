package com.rcpt.mvp.presenter;

import android.support.v7.widget.RecyclerView;

import com.rcpt.base.mvp.BasePresenter;
import com.rcpt.base.mvp.OnDataGetCallback;
import com.rcpt.bean.VideoListBean;
import com.rcpt.mvp.contract.MyCourseContract;
import com.rcpt.mvp.module.VideoTeacherInfoModule;
import com.rcpt.ui.home.video.VideoInfoActivity;

import java.util.List;

/**
 * Created by 860617003 on 2017/5/10.
 */

public class MyCoursePresenter extends BasePresenter<MyCourseContract.View> implements MyCourseContract.Presenter {

    private VideoTeacherInfoModule mModule;

    @Override
    public void attach(MyCourseContract.View view) {
        super.attach(view);
        mModule = new VideoTeacherInfoModule();
        getView().initRecyclerView();
    }

    @Override
    public void onLoadMore(int page) {
        mModule.requestVideoList(getView().getContext(), new OnDataGetCallback<List<VideoListBean.VideoItemBean>>() {
            @Override
            public void onSuccessResult(List<VideoListBean.VideoItemBean> data) {
                getView().bindListData(data);
            }
        });
    }

    @Override
    public void onItemClick(RecyclerView.ViewHolder vh, int position) {
        getView().actionStartActivity(VideoInfoActivity.class);
    }

    @Override
    public void loadListData() {
        onLoadMore(1);
    }
}
