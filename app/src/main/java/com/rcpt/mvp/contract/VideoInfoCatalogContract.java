package com.rcpt.mvp.contract;

import com.rcpt.base.adapter.BaseFoldAdapter;
import com.rcpt.base.mvp.BaseView;
import com.rcpt.base.mvp.RecyclerViewContract;
import com.rcpt.bean.VideoInfoBean;
import com.rcpt.bean.VideoInfoCatalogBean;

/**
 * Created by lvzp on 2017/5/9.
 * 类描述：
 */

public interface VideoInfoCatalogContract {

    interface View extends BaseView, RecyclerViewContract.View<VideoInfoCatalogBean> {
        /**
         * 更新列表Group的状态
         *
         * @param groupPosition
         */
        void updateListGroup(int groupPosition);

        /**
         * 获取课程的id
         *
         * @return
         */
        String getClassTypeId();

        /**
         * 当点击视频目录中某个Item的处理回调
         *
         * @param bean
         */
        void onClickVideoCatalogItemCallback(VideoInfoBean bean);

        /**
         * 显示提示框
         */
        void showDialog();

        /**
         * 是否已经购买
         *
         * @return
         */
        boolean isAlreadyBuy();
    }

    interface Presenter extends RecyclerViewContract.Presenter, BaseFoldAdapter.OnItemGroupExpandChangeListener, BaseFoldAdapter.OnItemClickListener {

    }

}
