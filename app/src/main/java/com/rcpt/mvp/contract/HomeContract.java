package com.rcpt.mvp.contract;

import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.rcpt.adapter.holder.LocalImageHolderView;
import com.rcpt.base.mvp.BaseView;
import com.rcpt.base.mvp.RecyclerViewContract;
import com.rcpt.bean.InstituteBean;
import com.rcpt.mvp.module.HomeModule;

import java.util.List;

/**
 * Created by lvzp on 2017/2/22.
 * 类描述：
 */

public interface HomeContract {

    interface View extends BaseView, RecyclerViewContract.View<InstituteBean.InstitutionListBean> {

        void changeMainPage(int position);

        void bindAfficheViews(android.view.View... views);

        android.view.View getAfficheView(String affiche,String afficheId);

        void setConvenientBannerHolder(CBViewHolderCreator<LocalImageHolderView> holderCreator, List<String> images);

        void bindItemRes(HomeModule.ItemRes itemRes);

        void setViewClickListener(android.view.View.OnClickListener listener);

    }

    interface Presenter extends RecyclerViewContract.Presenter {
        /**
         * 进如入到测试选择页面
         */
        void onClickGoTestSelect();

        /**
         * 点击进入政策法规界面
         */
        void onClickGoPolicy();

        /**
         * 点击进入人才通道
         */
        void onClickGoTalented();

        /**
         * 点击进入新闻
         */
        void onClickGoNews();

        /**
         * 点击进入接发包
         */
        void onClickGoJieFaBao();

        /**
         * 点击进入咨询服务
         */
        void onClickGoConsultService();

        /**
         * 点击进入培训机构列表
         */
        void onClickGoInstituteList();

        /**
         * 绑定并创建公告的数据和View
         */
        void bindAfficheViews();

        /**
         * 绑定轮播图的数据
         */
        void bindBanner();

        /**
         * 设置菜单Item的资源文件（文字和图片）
         */
        void setItemRes();

    }

}
