package com.rcpt.mvp.module;

import android.content.Context;
import android.support.v7.view.menu.MenuItemImpl;

import com.rcpt.R;
import com.rcpt.base.HttpResult;
import com.rcpt.base.mvp.MenuModuleIml;
import com.rcpt.base.mvp.OnDataGetCallback;
import com.rcpt.bean.CompanyInfoBean;
import com.rcpt.bean.InputMenuBean;
import com.rcpt.http.ProgressSubscriber;
import com.rcpt.http.RequestImpl;
import com.rcpt.http.RetrofitManager;
import com.rcpt.http.api.ApiClient;

import java.io.File;
import java.util.List;
import java.util.Map;

import okhttp3.RequestBody;

/**
 * Created by lvzp on 2017/4/10.
 * 类描述：
 */

public class CompanyInfoModule extends MenuModuleIml<InputMenuBean> {


    @Override
    public void attachMenuList(List<MenuItemImpl> menuItemList) {
        super.attachMenuList(menuItemList);
        mMenuBeanList.get(0).setImage(true);
    }

    @Override
    public InputMenuBean getMenuBean(MenuItemImpl menuItem) {
        InputMenuBean bean = new InputMenuBean();
        bean.setNeedInput(menuItem.isEnabled());
        bean.setImage(false);
        int itemId = menuItem.getItemId();
        switch (itemId) {
            case R.id.company_menu_item_logo:
                bean.setEdit(true);
                break;
            case R.id.company_menu_item_industry:
                bean.setEdit(false);
                break;
            case R.id.company_menu_item_contact:
                bean.setEdit(true);
                break;
            case R.id.company_menu_item_tel:
                bean.setEdit(false);
                break;
            case R.id.company_menu_item_mail:
                bean.setEdit(false);
                break;
            case R.id.company_menu_item_website:
                bean.setEdit(true);
                break;
            case R.id.company_menu_item_zip_code:
                bean.setEdit(true);
                break;
            case R.id.company_menu_item_address:
                bean.setEdit(false);
                break;
            case R.id.company_menu_item_address_detail:
                bean.setEdit(true);
                break;
            case R.id.company_menu_item_business_bureau:
                bean.setEdit(true);
                break;
            case R.id.company_menu_item_service_distribution:
                bean.setEdit(true);
                break;
            case R.id.company_menu_item_is_quotedCompany:
                bean.setEdit(true);
                break;
            case R.id.company_menu_item_registered_capital:
                bean.setEdit(false);
                break;
            case R.id.company_menu_item_scale:
                bean.setEdit(false);
                break;
            case R.id.company_menu_item_enterprise_nature:
                bean.setEdit(true);
                break;
            case R.id.company_menu_item_legal_representative:
                bean.setEdit(false);
                break;
            case R.id.company_menu_item_register_time:
                bean.setEdit(false);
                break;
            case R.id.company_menu_item_company_introduction:
                bean.setEdit(true);
                break;
        }
        return bean;
    }

    /**
     * 请求获取企业详情
     *
     * @param context
     * @param companyId
     * @param callback
     */
    public void requestCompanyInfo(Context context, String companyId, final OnDataGetCallback<CompanyInfoBean> callback) {
        RetrofitManager.toSubscribe(
                ApiClient.getApiService().getCompanyInfo(companyId),
                new ProgressSubscriber<HttpResult<CompanyInfoBean>>(context, new RequestImpl<HttpResult<CompanyInfoBean>>() {
                    @Override
                    public void onNext(HttpResult<CompanyInfoBean> result) {
                        callback.onSuccessResult(result.getData());
                    }
                })
        );
    }

    /**
     * 更新企业详情
     *
     * @param context
     * @param params
     * @param callback
     */
    public void updateCompanyInfo(Context context, Map<String, String> params, final OnDataGetCallback<String> callback) {
        if (params.containsKey("comLogo"))
            params.remove("comLogo");
        RetrofitManager.toSubscribe(
                ApiClient.getApiService().updateCompanyInfo(params)
                , new ProgressSubscriber<HttpResult<String>>(context, new RequestImpl<HttpResult<String>>() {
                    @Override
                    public void onNext(HttpResult<String> result) {
                        callback.onSuccessResult(result.getMsg());
                    }
                })
        );
    }

    /**
     * 更新企业Logo
     *
     * @param context
     * @param logoFile
     * @param id
     * @param callback
     */
    public void updateCompanyLogo(Context context, File logoFile, String id, final OnDataGetCallback<String> callback) {
        RetrofitManager.toSubscribe(
                ApiClient.getApiService().updateCompanyLogo(ApiClient.getFileBody("comLogo", logoFile), RequestBody.create(null, id))
                , new ProgressSubscriber<HttpResult<String>>(context, new RequestImpl<HttpResult<String>>() {
                    @Override
                    public void onNext(HttpResult<String> result) {
                        callback.onSuccessResult(result.getData());
                    }
                })
        );
    }
}
