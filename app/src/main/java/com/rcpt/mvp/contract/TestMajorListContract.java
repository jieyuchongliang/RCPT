package com.rcpt.mvp.contract;

import com.rcpt.base.mvp.BaseView;
import com.rcpt.base.mvp.PullToRefeshContract;
import com.rcpt.base.mvp.RecyclerViewContract;
import com.rcpt.bean.TestMajorListBean;
import com.rcpt.bean.TestMajorTypeBean;

import java.util.List;

/**
 * Created by lvzp on 2017/4/5.
 * 类描述：
 */

public interface TestMajorListContract {

    interface View extends BaseView, RecyclerViewContract.View<TestMajorListBean.TestlistBean>, PullToRefeshContract.View {
        /**
         * 绑定专业测试的类型数据
         *
         * @param list
         */
        void bindMajorTypeData(List<TestMajorTypeBean> list);

        /**
         * 关闭类型选择的抽屉布局
         */
        void closeMajorTypeDrawer();

        /**
         * 获取知识类型的点击条目
         *
         * @return
         */
        int getMajorTypeClickPosition();

        void selectMajorTypeItemChecked(int position);
    }

    interface Presenter extends RecyclerViewContract.Presenter, PullToRefeshContract.Presenter {

        /**
         * 知识类型的触发点击
         */
        void onMajorTypeClick();
    }

}
