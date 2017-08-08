package com.rcpt.mvp.contract;

import android.support.annotation.IdRes;
import android.view.View;

import com.rcpt.base.mvp.BaseView;
import com.rcpt.base.mvp.PullToRefeshContract;
import com.rcpt.base.mvp.RecyclerViewContract;
import com.rcpt.bean.FilterSelectBean;
import com.rcpt.bean.RecruitListBean;
import com.rcpt.widget.FilterItemView;

import java.util.List;

/**
 * Created by lvzp on 2017/2/24.
 * 类描述：
 */

public interface RecruitListContract {

    interface View extends BaseView, RecyclerViewContract.View<RecruitListBean.JobListBean>, PullToRefeshContract.View {
        void bindJobListData(List<RecruitListBean.JobListBean> listData);

        void bindCVListData(List<RecruitListBean.CVListBean> listData);

        /**
         * 显示灰色的背景层
         */
        void showGrayBg();

        /**
         * 灰色背景关闭
         */
        void closeGrayBg();

        /**
         * 获取是否为普通用户登录的身份
         *
         * @return
         */
        boolean isPersonType();

        /**
         * 获取点击的按钮是否是选中的状态
         *
         * @return
         */
        boolean isChecked();

        /**
         * 获取筛选点击的id
         *
         * @return
         */
        int getCheckId();

        /**
         * 获取点击的Id
         *
         * @return
         */
        int getClickId();

        /**
         * 筛选条件布局开始进入
         */
        void layoutStartIn();

        /**
         * 筛选条件的布局出来
         */
        void layoutStartOut();

        /**
         * 这个只用在企业身份登录的状态时才会调用
         * 绑定左侧的筛选列表数据
         *
         * @param listData
         * @param isMathParent 是否需要填充全屏
         */
        void bindLeftFilterData(List<FilterSelectBean> listData, boolean isMathParent);

        /**
         * 这个只用在企业身份登录的状态时才会调用
         * 绑定右侧的筛选列表数据
         *
         * @param listData
         */
        void bindRightFilterData(List<FilterSelectBean> listData, String parentName);

        /**
         * 获取筛选条件的类型对象
         *
         * @return
         */
        FilterSelectBean getFilterSelectBean();

        /**
         * 绑定筛选选择的名称到View中
         *
         * @param filterName
         */
        void setupFilterName(String filterName);

        /**
         * 获取选择筛选条件的数组
         *
         * @return
         */
        List<FilterSelectBean> getSelectFilterList();

        /**
         * 显示标题栏中右侧的按钮
         */
        void showTitleRightView(int selectNum);

        /**
         * 隐藏标题栏右侧的按钮
         */
        void hintTitleRightView();

        /**
         * 重置所有筛选的条件的名称
         */
        void resetFilterAllName();

        /**
         * 进入到地址选择
         */
        void startGoAddress();

        /**
         * 显示搜索的PopWindow
         *
         * @param searchText
         */
        void showSearchPop(String searchText);

        /**
         * 获取搜索内容
         *
         * @return
         */
        String getInputSearchText();

        /**
         * 重置标题名称
         */
        void resetTitleName();

        /**
         * 设置标题栏的名字
         */
        void setTitleName(String titleName);

        /**
         * 开启已选中的条件PopupWindow
         */
        void showSelectFilterPop();

        /**
         * 关闭已选中的
         */
        void closeSelectFilterPop();

        /**
         * 根据View的Id获取对应的View
         *
         * @param id
         * @return
         */
        android.view.View findViewById(@IdRes int id);

        /**
         * 将Filter的单个条目添加到PopupWindow中
         *
         * @param holder
         */
        int addFilterItemToPop(FilterItemView.DataHolder holder, boolean isNeedClose);

        /**
         * 获取条件筛选删除的DataHolder
         *
         * @return
         */
        FilterItemView.DataHolder getFilterRemoveDataHolder();

        /**
         * 获取删除条件的子类型的名称
         *
         * @return
         */
        String getFilterRemoveChildName();

        /**
         * 当筛选条件的地址被清空的时候进行的回调
         */
        void onFilterAddressClean();
    }

    interface Presenter extends RecyclerViewContract.Presenter, PullToRefeshContract.Presenter {
        /**
         * 当筛选的点击改变的执行方法
         */
        void onFilterItemClick();

        /**
         * 当筛选条件中职位父类被点击时调用的方法
         */
        void onFilterJobParentClick();

        /**
         * 当普通的控件点击时调用的方法
         */
        void onViewClick();

        /**
         * 当筛选条件的属性选择完成后调用的方法
         */
        void onFilterSelectOver();

        /**
         * 当PopWindow中点击搜索时执行的方法
         */
        void onPopLayoutClickSearch();

        void onFilterItemClose();

        void onFilterItemChildClose();

        /**
         * 当筛选条件的PopupWindow点击重置按钮后的回调
         */
        void onFilterReset();
    }

}
