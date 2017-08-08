package com.rcpt.mvp.contract;

import com.rcpt.base.mvp.BaseView;
import com.rcpt.base.mvp.RecyclerViewContract;
import com.rcpt.bean.RecruitFragmentListBean;

import java.util.List;

/**
 * Created by lvzp on 2017/2/24.
 * 类描述：
 */

public interface RecruitContract {

    interface View extends BaseView, RecyclerViewContract.View<RecruitFragmentListBean> {
        /**
         * 获取当前点击的条目位置
         *
         * @return
         */
        int getClickItemPosition();

        /**
         * 获取点击条目的类型
         *
         * @return
         */
        int getClickItemType();

        /**
         * 获取被Adapter处理过的数据源
         *
         * @return
         */
        List<RecruitFragmentListBean.SubBean> getAdapterListData();
    }

    interface Presenter extends RecyclerViewContract.Presenter {
        void onClickGoRecruitList();

        void onItemClick();

        /**
         * 点击前往招聘列表并开启输入框
         */
        void onClickGoRecruitListWithSearch();
    }

}
