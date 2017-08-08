package com.rcpt.mvp.contract;

import com.rcpt.base.mvp.BaseView;
import com.rcpt.base.mvp.RecyclerViewContract;
import com.rcpt.bean.ChooseIndustryBean;

import java.util.List;

/**
 * Created by 860116021 on 2017/3/15.
 */

public interface ChooseIndustryContract {

    interface View extends BaseView, RecyclerViewContract.View<ChooseIndustryBean> {
        void onItemSelectUpdate(int position);

        List<ChooseIndustryBean> getSelectedBeanList();

        boolean getIsSelectOne();

        int getSelectedMode();
    }

    interface Presenter extends RecyclerViewContract.Presenter {
        List<ChooseIndustryBean> getSelectBeanList();
    }
}
