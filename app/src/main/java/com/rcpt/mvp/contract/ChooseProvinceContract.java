package com.rcpt.mvp.contract;

import com.rcpt.base.mvp.BaseView;
import com.rcpt.base.mvp.RecyclerViewContract;
import com.rcpt.bean.ChooseProvinceBean;

/**
 * Created by 860116021 on 2017/3/16.
 */

public interface ChooseProvinceContract {

    interface View extends BaseView, RecyclerViewContract.View<ChooseProvinceBean> {

        /**
         * 获取是否进行三级联动
         *
         * @return
         */
        boolean isThree();

        /**
         * 当城市选择结束后回调的数据
         * @param provinceBean
         * @param cityBean
         * @param areaBean
         */
        void onCitySelectOver(ChooseProvinceBean provinceBean,ChooseProvinceBean cityBean,ChooseProvinceBean areaBean);
    }

    interface Presenter extends RecyclerViewContract.Presenter {

    }
}
