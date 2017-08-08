package com.rcpt.mvp.contract;

import com.rcpt.base.mvp.BaseView;
import com.rcpt.base.mvp.PullToRefeshContract;
import com.rcpt.base.mvp.RecyclerViewContract;
import com.rcpt.bean.VideoListBean;

/**
 * Created by 860617003 on 2017/5/10.
 */

public interface MyCourseContract {

    interface View extends BaseView, RecyclerViewContract.View<VideoListBean.VideoItemBean>, PullToRefeshContract.View {

    }

    interface Presenter extends RecyclerViewContract.Presenter, PullToRefeshContract.Presenter {

    }

}
