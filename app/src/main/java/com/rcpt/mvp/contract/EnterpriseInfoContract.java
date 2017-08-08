package com.rcpt.mvp.contract;

import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.rcpt.adapter.holder.LocalImageHolderView;
import com.rcpt.base.mvp.BaseView;
import com.rcpt.bean.EnterpriseInfoBean;

import java.util.List;

/**
 * Created by lvzp on 2017/2/27.
 * 类描述：
 */

public interface EnterpriseInfoContract {

    interface View extends BaseView {
        void bindData(EnterpriseInfoBean infoBean);

        String getEnterpriseId();
        //企业详情里机构环境的轮播
        void setConvenientBannerHolder(CBViewHolderCreator<LocalImageHolderView> holderCreator, List<String> images);

    }

    interface Presenter {
        void loadInfo();

        void onClickGoFind(int position);
        //绑定轮播图的数据
        void bindBanner(List<String> mBannerImages);
    }

}
