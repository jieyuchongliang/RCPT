package com.rcpt.mvp.contract;

import com.rcpt.adapter.FilterListAdapter;
import com.rcpt.base.mvp.BaseView;
import com.rcpt.bean.FilterSelectBean;

import java.util.List;

/**
 * Created by lvzp on 2017/5/8.
 * 类描述：
 */

public interface ListFilterContract {

    interface View extends BaseView {
        /**
         * 绑定筛选器列表数据
         *
         * @param listData
         */
        void bindFilterListData(List<FilterSelectBean> listData);

        /**
         * 显示背景
         */
        void showGrayBg();

        /**
         * 关闭背景
         */
        void closeGrayBg();

        /**
         * 筛选条件布局开始进入
         */
        void layoutStartIn();

        /**
         * 筛选条件的布局出来
         */
        void layoutStartOut();

        /**
         * 显示右侧的按钮
         */
        void showTitleRightView();

        /**
         * 隐藏标题栏右侧的按钮
         */
        void hintTitleRightView();

        /**
         * 重置筛选器Item 的显示内容
         */
        void resetFilterAllName();

    }

    interface Presenter extends FilterListAdapter.OnAutoSelectDataCallback, android.view.View.OnClickListener {
        /**
         * 加载筛选器列表
         */
        void loadFilterList();
    }

}
