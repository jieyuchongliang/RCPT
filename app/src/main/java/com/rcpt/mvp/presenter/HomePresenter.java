package com.rcpt.mvp.presenter;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.rcpt.Constant;
import com.rcpt.LoginHelper;
import com.rcpt.R;
import com.rcpt.adapter.holder.LocalImageHolderView;
import com.rcpt.base.mvp.BasePresenter;
import com.rcpt.base.mvp.OnDataGetCallback;
import com.rcpt.bean.InstituteBean;
import com.rcpt.mvp.contract.HomeContract;
import com.rcpt.mvp.module.HomeModule;
import com.rcpt.ui.home.activity.ConsultServiceActivity;
import com.rcpt.ui.home.activity.InstitutionActivity;
import com.rcpt.ui.home.activity.InstitutionInfoActivity;
import com.rcpt.ui.home.activity.JieFaBaoActivity;
import com.rcpt.ui.home.activity.NewsActivity;
import com.rcpt.ui.home.activity.PolicyActivity;
import com.rcpt.ui.home.test.TestSelectActivity;
import com.rcpt.ui.home.video.VideoListActivity;
import com.rcpt.ui.recruit.RecruitJobListActivity;

import java.util.List;

/**
 * Created by lvzp on 2017/2/22.
 * 类描述：
 */

public class HomePresenter extends BasePresenter<HomeContract.View> implements HomeContract.Presenter, View.OnClickListener {

    private HomeModule mHomeModule;

    @Override
    public void attach(HomeContract.View view) {
        super.attach(view);
        mHomeModule = new HomeModule();
        createItemClickListener(getView().getRecyclerView());
        getView().setViewClickListener(this);
        getView().initRecyclerView();
    }

    /**
     * 进如入到测试选择页面
     */
    @Override
    public void onClickGoTestSelect() {
        if (LoginHelper.getInstance().getUserBean().getUserType().equals(Constant.UserType.PERSON.getValue()))
            getView().actionStartActivity(TestSelectActivity.class);
        else
            getView().showToast("仅支持个人用户测试");
    }

    /**
     * 点击进入政策法规界面
     */
    @Override
    public void onClickGoPolicy() {
        getView().actionStartActivity(PolicyActivity.class);
    }

    /**
     * 点击进入人才通道
     */
    @Override
    public void onClickGoTalented() {
        RecruitJobListActivity.actionStart(getView().getContext(),false);
    }

    /**
     * 点击进入新闻
     */
    @Override
    public void onClickGoNews() {
        getView().actionStartActivity(NewsActivity.class);
    }

    /**
     * 点击进入接发包
     */
    @Override
    public void onClickGoJieFaBao() {
        getView().actionStartActivity(JieFaBaoActivity.class);
    }

    /**
     * 点击进入咨询服务
     */
    @Override
    public void onClickGoConsultService() {
        getView().actionStartActivity(ConsultServiceActivity.class);
    }

    @Override
    public void onClickGoInstituteList() {
        getView().actionStartActivity(InstitutionActivity.class);
    }

    /**
     * 绑定并创建公告的数据和View
     */
    @Override
    public void bindAfficheViews() {
        if (mHomeModule.getAfficheList() != null && mHomeModule.getAfficheList().size() > 0) {
            int afficheListSize = mHomeModule.getAfficheList().size();
            android.view.View[] afficheViews = new View[afficheListSize];
            for (int i = 0; i < afficheListSize; i++) {
                InstituteBean.NoticeBean noticeBean = mHomeModule.getAfficheList().get(i);
                String affiche = noticeBean.getTitle();
                String afficheId = noticeBean.getNewsId();
                afficheViews[i] = getView().getAfficheView(affiche, afficheId);
            }
            getView().bindAfficheViews(afficheViews);
        }
    }

    /**
     * 绑定轮播图的数据
     */
    @Override
    public void bindBanner() {
        getView().setConvenientBannerHolder(new CBViewHolderCreator<LocalImageHolderView>() {
            @Override
            public LocalImageHolderView createHolder() {
                return new LocalImageHolderView();
            }
        }, mHomeModule.getBannerImages());
    }

    /**
     * 设置菜单Item的资源文件（文字和图片）
     */
    @Override
    public void setItemRes() {
        List<HomeModule.ItemRes> itemResList = mHomeModule.getItemResList();
        for (HomeModule.ItemRes itemRes : itemResList) {
            getView().bindItemRes(itemRes);
        }
    }

    @Override
    public void loadListData() {

        mHomeModule.getHomeIndustryList(getView().getContext(), new OnDataGetCallback<List<InstituteBean.InstitutionListBean>>() {

            @Override
            public void onSuccessResult(List<InstituteBean.InstitutionListBean> data) {
                getView().bindListData(mHomeModule.getInstituteList());
                bindAfficheViews();
                bindBanner();
            }
        });
    }

    @Override
    public void onItemClick(RecyclerView.ViewHolder vh, int position) {
        InstituteBean.InstitutionListBean bean = mHomeModule.getmInstituteList().get(position);
        InstitutionInfoActivity.actionStartActivity(getView().getContext(), bean.getInstitutionId());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.include_news://新闻的点击事件处理
                onClickGoNews();
                break;
            case R.id.include_consult://咨询服务的点击事件处理
                onClickGoConsultService();
                break;
            case R.id.include_training://培训晋升的点击事件处理
                if (LoginHelper.getInstance().getUserBean().getUserType().equals(Constant.UserType.PERSON.getValue()))
                    getView().actionStartActivity(VideoListActivity.class);
                else
                    getView().showToast("仅支持个人用户培训晋升");
                break;
            case R.id.include_test:
                onClickGoTestSelect();
                break;
        }
    }
}
