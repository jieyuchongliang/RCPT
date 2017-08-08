package com.rcpt.mvp.presenter;

import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.rcpt.adapter.holder.LocalImageHolderView;
import com.rcpt.base.mvp.BasePresenter;
import com.rcpt.base.mvp.OnDataGetCallback;
import com.rcpt.bean.InstitutionDetailBean;
import com.rcpt.mvp.contract.InstitutionInfoContract;
import com.rcpt.mvp.module.InstitutionInfoModule;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 860116021 on 2017/4/14.
 */

public class InstitutionInfoPresenter extends BasePresenter<InstitutionInfoContract.View> implements InstitutionInfoContract.Presenter{
    private List<InstitutionDetailBean.EnviromentlistBean> mPicBean;
    private List<String> mBannerImages;//机构环境图片集合
    private InstitutionInfoModule mModule;

    @Override
    public void attach(InstitutionInfoContract.View view) {
        super.attach(view);
        mModule = new InstitutionInfoModule();
    }

    public void loadInfo(){
        mModule.getInstitutionInfo(getView().getContext(), getView().getInstitutionId(), new OnDataGetCallback<InstitutionDetailBean>() {
            @Override
            public void onSuccessResult(InstitutionDetailBean data) {
                initResultData(data);
            }
        });
    }

    public void initResultData(InstitutionDetailBean data){
        getView().setUpData(data);
        if(data.getEnviromentlist() == null || data.getEnviromentlist().isEmpty()){
            bindBanner(null);
        }else{
            mPicBean = data.getEnviromentlist();
            bindBanner(getBannerImages());
        }
        if (data.getCourselist() == null || data.getCourselist().isEmpty()) {
            getView().showEmptyCourse();
        } else {
            getView().setUpCourse(data.getCourselist());
        }
        if (data.getTeacherlist() == null || data.getTeacherlist().isEmpty()) {
            getView().showEmptyTeacher();
        } else {
            getView().setUpTeacher(data.getTeacherlist());
        }

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
}
