package com.rcpt.mvp.presenter;

import com.rcpt.base.mvp.BasePresenter;
import com.rcpt.base.mvp.OnDataGetCallback;
import com.rcpt.bean.AcademyInfoBean;
import com.rcpt.mvp.contract.SchoolInfoContract;
import com.rcpt.mvp.module.SchoolInfoModule;

/**
 * Created by lvzp on 2017/3/29.
 * 类描述：
 */

public class SchoolInfoPresenter extends BasePresenter<SchoolInfoContract.View> implements SchoolInfoContract.Presenter {

    private SchoolInfoModule mModule;

    @Override
    public void attach(SchoolInfoContract.View view) {
        super.attach(view);
        mModule = new SchoolInfoModule();
    }

    @Override
    public void loadInfo() {
        mModule.requestSchoolInfo(getView().getContext(), getView().getAcademyId(), new OnDataGetCallback<AcademyInfoBean>() {
            @Override
            public void onSuccessResult(AcademyInfoBean data) {
                getView().setupPageData(data.getSchoolinfo());
                if(data.getGraduateinfo().getSchoolpitcure() == null || data.getGraduateinfo().getSchoolpitcure().isEmpty()){
                    getView().showNoPicEx();
                }else{
//                    getView().showNoPicEx();
                    getView().setupPagePic(data.getGraduateinfo().getSchoolpitcure());
                }

            }
        });
    }
}
