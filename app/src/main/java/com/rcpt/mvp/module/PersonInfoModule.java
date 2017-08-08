package com.rcpt.mvp.module;


import android.content.Context;
import android.support.v7.view.menu.MenuItemImpl;
import android.util.Log;

import com.rcpt.R;
import com.rcpt.base.HttpResult;
import com.rcpt.base.mvp.MenuModuleIml;
import com.rcpt.base.mvp.OnDataGetCallback;
import com.rcpt.bean.InputMenuBean;
import com.rcpt.bean.PersonInfoBean;
import com.rcpt.http.ProgressSubscriber;
import com.rcpt.http.RequestImpl;
import com.rcpt.http.RetrofitManager;
import com.rcpt.http.api.ApiClient;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import okhttp3.RequestBody;

/**
 * Created by lvzp on 2017/3/2.
 * 类描述：
 */

public class PersonInfoModule extends MenuModuleIml<InputMenuBean> {

    private PersonInfoBean mPersonInfoBean;

    public List<String> getMaritalStatusList() {
        List<String> list = new ArrayList<>();
        list.add("未婚");
        list.add("已婚");
        list.add("离异");
        list.add("丧偶");
        return list;
    }

    public void uploadUserAvatar(Context context, String id, File avatar, final OnDataGetCallback<String> callback) {
        Log.d("upload", "-----开始上传头像-------");
        RetrofitManager.toSubscribe(
                ApiClient.getApiService().uploadAvatar(ApiClient.getFileBody("userPicture", avatar), RequestBody.create(null, id))
                , new ProgressSubscriber<HttpResult<String>>(context, new RequestImpl<HttpResult<String>>() {
                    @Override
                    public void onNext(HttpResult<String> result) {
                        callback.onSuccessResult(result.getData());
                    }
                })
        );
    }

    /**
     * @param userId
     * @param callback
     */
    public void updatePersonInfo(Context context, String userId, PersonInfoBean bean, final OnDataGetCallback<Boolean> callback) {
        bean.dataToUpload();
        RetrofitManager.toSubscribe(
                ApiClient.getApiService().updatePersonInfo(
                        userId
                        , bean.getName()
                        , bean.getSex()
                        , bean.getBirthDate()
                        , bean.getContactInfo()
                        , bean.getMail()
                        , bean.getWorkDate()
                        , bean.getOverseasWork()
                        , bean.getMaritalStatus()
                        , bean.getEducationVal()
                )
                , new ProgressSubscriber<HttpResult<String>>(context, new RequestImpl<HttpResult<String>>() {
                    @Override
                    public void onNext(HttpResult<String> stringHttpResult) {
                        if (stringHttpResult.isResult()) {
                            callback.onSuccessResult(stringHttpResult.isResult());
                        } else {
                            //callback.onError(stringHttpResult.getMsg());
                        }
                    }
                })
        );
    }

    public void requestPersonInfo(Context context, String userId, final OnDataGetCallback<PersonInfoBean> callback) {
        RetrofitManager.toSubscribe(
                ApiClient.getApiService().getPersonInfo(userId)
                , new ProgressSubscriber<HttpResult<PersonInfoBean>>(context, new RequestImpl<HttpResult<PersonInfoBean>>() {
                    @Override
                    public void onNext(HttpResult<PersonInfoBean> result) {
                        mPersonInfoBean = result.getData();
                        if (mPersonInfoBean != null)
                            mPersonInfoBean.dataToShow();
                        callback.onSuccessResult(mPersonInfoBean);
                    }
                })
        );
    }

    public PersonInfoBean getPersonInfoBean() {
        return mPersonInfoBean;
    }

    @Override
    public void attachMenuList(List<MenuItemImpl> menuItemList) {
        super.attachMenuList(menuItemList);
        InputMenuBean bean = mMenuBeanList.get(0);
        bean.setImage(true);
    }

    @Override
    public InputMenuBean getMenuBean(MenuItemImpl menuItem) {
        InputMenuBean bean = new InputMenuBean();
        if (menuItem.getItemId() == R.id.person_menu_item_avatar || menuItem.getItemId() == R.id.person_menu_item_job_day) {
            bean.setEdit(true);
        }
        bean.setNeedInput(menuItem.isEnabled());
        return bean;
    }


}
