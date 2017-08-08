package com.rcpt.mvp.presenter;

import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.rcpt.adapter.holder.LocalImageHolderView;
import com.rcpt.base.mvp.BasePresenter;
import com.rcpt.base.mvp.OnDataGetCallback;
import com.rcpt.bean.EnterpriseInfoBean;
import com.rcpt.mvp.contract.EnterpriseInfoContract;
import com.rcpt.mvp.module.EnterpriseInfoModule;
import com.rcpt.ui.recruit.RecruitJobInfoActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lvzp on 2017/2/27.
 * 类描述：
 */

public class EnterpriseInfoPresenter extends BasePresenter<EnterpriseInfoContract.View> implements EnterpriseInfoContract.Presenter {

    private EnterpriseInfoModule mModule;
    private List<EnterpriseInfoBean.ComPicUpLoadList> mPicBean;
    private List<String> mBannerImages;

    @Override
    public void attach(EnterpriseInfoContract.View view) {
        super.attach(view);
        mModule = new EnterpriseInfoModule();
    }

    @Override
    public void loadInfo() {
        mModule.requestEnterpriseInfo(getView().getContext(), getView().getEnterpriseId(), new OnDataGetCallback<EnterpriseInfoBean>() {
            @Override
            public void onSuccessResult(EnterpriseInfoBean data) {
                getView().bindData(data);
                if (data.getComPictUploadList() != null && !data.getComPictUploadList().isEmpty()) {
                    mPicBean = data.getComPictUploadList();
                    bindBanner(getBannerImages());
                }else {
                    bindBanner(null);
                }
            }
        });
    }

    /**
     * 绑定轮播图的数据
     */
    @Override
    public void bindBanner(List<String> mBannerImages) {
        getView().setConvenientBannerHolder(new CBViewHolderCreator<LocalImageHolderView>() {
            @Override
            public LocalImageHolderView createHolder() {
                return new LocalImageHolderView();
            }
        }, mBannerImages);
    }

    public List<String> getBannerImages() {
        mBannerImages = new ArrayList<>();
        if(mPicBean != null && mPicBean.size() > 0){
            for (int i = 0; i < mPicBean.size(); i++) {
                mBannerImages.add(i,mPicBean.get(i).getEnvironmentPicture());
            }
        }
        return mBannerImages;
    }

    @Override
    public void onClickGoFind(int position) {
        EnterpriseInfoBean.JobListBean jobListBean = mModule.getJobListData().get(position);
        RecruitJobInfoActivity.actionStart(getView().getContext(),getView().getEnterpriseId(),jobListBean.getRecruitmentInfoId());
    }
}
