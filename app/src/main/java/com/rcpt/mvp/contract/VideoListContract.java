package com.rcpt.mvp.contract;

import android.view.View;

import com.rcpt.base.mvp.PullToRefeshContract;
import com.rcpt.base.mvp.RecyclerViewContract;
import com.rcpt.bean.VideoListBean;

/**
 * Created by lvzp on 2017/5/8.
 * 类描述：
 */

public interface VideoListContract {

    interface View extends ListFilterContract.View, RecyclerViewContract.View<VideoListBean.VideoItemBean>, PullToRefeshContract.View {

        /**
         * 重置课程子类型的筛选
         */
        void resetSubjectTypeFilterName();
    }

    interface Presenter extends RecyclerViewContract.Presenter, PullToRefeshContract.Presenter {
        void onItemClick(android.view.View imageView, int position);
    }

}
