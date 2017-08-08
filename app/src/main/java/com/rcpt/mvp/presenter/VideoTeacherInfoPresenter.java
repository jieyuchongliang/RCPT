package com.rcpt.mvp.presenter;

import com.rcpt.base.mvp.BasePresenter;
import com.rcpt.base.mvp.OnDataGetCallback;
import com.rcpt.bean.VideoListBean;
import com.rcpt.mvp.contract.VideoTeacherInfoContract;
import com.rcpt.mvp.module.VideoTeacherInfoModule;

import java.util.List;

/**
 * Created by lvzp on 2017/5/10.
 * 类描述：
 */

public class VideoTeacherInfoPresenter extends BasePresenter<VideoTeacherInfoContract.View> implements VideoTeacherInfoContract.Presenter {

    private VideoTeacherInfoModule mModule;

    @Override
    public void attach(VideoTeacherInfoContract.View view) {
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
    public void loadListData() {
        onLoadMore(1);
    }
}
