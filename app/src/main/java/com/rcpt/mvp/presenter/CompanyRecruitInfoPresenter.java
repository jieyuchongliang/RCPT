package com.rcpt.mvp.presenter;

import android.support.v7.view.menu.MenuItemImpl;

import com.rcpt.LoginHelper;
import com.rcpt.R;
import com.rcpt.base.mvp.BasePresenter;
import com.rcpt.base.mvp.MenuModuleIml;
import com.rcpt.base.mvp.OnDataGetCallback;
import com.rcpt.bean.CompanyRecruitInfoBean;
import com.rcpt.bean.MenuBean;
import com.rcpt.http.api.ApiClient;
import com.rcpt.mvp.contract.CompanyRecruitInfoContract;
import com.rcpt.mvp.module.CompanyRecruitInfoModule;

/**
 * Created by lvzp on 2017/4/18.
 * 类描述：
 */

public class CompanyRecruitInfoPresenter extends BasePresenter<CompanyRecruitInfoContract.View> implements CompanyRecruitInfoContract.Presenter {

    private MenuModuleIml<MenuBean> mMenuModule = new MenuModuleIml<MenuBean>() {
        @Override
        public MenuBean getMenuBean(MenuItemImpl menuItem) {
            return new MenuBean();
        }
    };

    private CompanyRecruitInfoModule mModule;

    @Override
    public void attach(CompanyRecruitInfoContract.View view) {
        super.attach(view);
        mModule = new CompanyRecruitInfoModule();
        getView().initRecyclerView();
    }

    @Override
    public void loadListData() {
        mModule.requestCompanyRecruitInfo(getView().getContext(), LoginHelper.getInstance().getUserToken(), getView().getRecruitmentInfoId(), new OnDataGetCallback<CompanyRecruitInfoBean>() {
            @Override
            public void onSuccessResult(CompanyRecruitInfoBean data) {
                data.setEducationName(data.getEducationName() == null ? "不限" : data.getEducationName());
                data.setJobTypeName(data.getJobTypeName() == null ? "不限" : data.getJobTypeName());
                data.setWorkingLifeName(data.getWorkingLifeName() == null ? "不限" : data.getWorkingLifeName());
                data.setTotal(data.getTotal() == null ? "0" : data.getTotal());
                data.setWorkAddress(data.getWorkPlaceProvinceName() + data.getWorkPlaceCityName());
                data.setOrientedGroup(data.getOrientedGroup());
                ApiClient.menuDataToList(data, mMenuModule.getMenuBeanList());
                getView().bindListData(mMenuModule.getMenuBeanList());
            }
        });
    }

    @Override
    public void onItemClick() {
        int position = getView().getClickPosition();
        if (mMenuModule.getMenuBeanList().get(position).getId() == R.id.company_recruit_menu_item_accept_num) {
            if ("0".equals(mModule.getRecruitInfoBean().getTotal())) {
                getView().showToast("暂无应聘人员");
            } else {
                getView().actionStartGoAcceptNumActivity(mModule.getRecruitInfoBean().getPositionName());
            }
        }
    }

    @Override
    public void initMenu() {
        mMenuModule.attachMenuList(getView().getMenuList());
    }
}
