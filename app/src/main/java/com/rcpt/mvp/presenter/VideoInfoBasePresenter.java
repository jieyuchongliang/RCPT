package com.rcpt.mvp.presenter;

import com.rcpt.LoginHelper;
import com.rcpt.base.mvp.BasePresenter;
import com.rcpt.base.mvp.OnDataGetCallback;
import com.rcpt.bean.VideoInfoBaseBean;
import com.rcpt.mvp.contract.VideoInfoBaseContract;
import com.rcpt.mvp.module.VideoInfoBaseModule;
import com.rcpt.ui.home.video.VideoTeacherInfoActivity;

/**
 * Created by 860617003 on 2017/5/15.
 */

public class VideoInfoBasePresenter extends BasePresenter<VideoInfoBaseContract.View> implements VideoInfoBaseContract.Presenter {

    private VideoInfoBaseModule mModule;

    @Override
    public void attach(VideoInfoBaseContract.View view) {
        super.attach(view);
        mModule = new VideoInfoBaseModule();
    }

    @Override
    public void onClickTeacherInfo() {
        //getView().actionStartActivity(VideoTeacherInfoActivity.class);
    }

    @Override
    public void loadPageData() {
        mModule.requestVideoInfo(getView().getContext(), LoginHelper.getInstance().getUserToken(), getView().getClassTypeId(), new OnDataGetCallback<VideoInfoBaseBean>() {
            @Override
            public void onSuccessResult(VideoInfoBaseBean data) {
                getView().bindPageData(data);
            }
        });
    }
}
