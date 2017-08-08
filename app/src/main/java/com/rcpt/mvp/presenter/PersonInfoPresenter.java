package com.rcpt.mvp.presenter;

import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.widget.RecyclerView;

import com.rcpt.Constant;
import com.rcpt.LoginHelper;
import com.rcpt.R;
import com.rcpt.base.mvp.BasePresenter;
import com.rcpt.base.mvp.OnDataGetCallback;
import com.rcpt.bean.AttrSelectBean;
import com.rcpt.bean.MenuBean;
import com.rcpt.bean.PersonInfoBean;
import com.rcpt.bean.User;
import com.rcpt.http.api.ApiClient;
import com.rcpt.mvp.contract.PersonInfoContract;
import com.rcpt.mvp.module.CreateCVInfoMenuModule;
import com.rcpt.mvp.module.PersonInfoModule;
import com.rcpt.utils.DateUtils;
import com.rcpt.utils.RECheckUtils;
import com.rcpt.utils.UserUtils;
import com.rcpt.widget.InputDataPopWindow;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import top.zibin.luban.OnCompressListener;

/**
 * Created by lvzp on 2017/3/2.
 * 类描述：
 */

public class PersonInfoPresenter extends BasePresenter<PersonInfoContract.View> implements PersonInfoContract.Presenter {

    private PersonInfoModule mModule;
    private boolean isChangeName;
    private CreateCVInfoMenuModule mCVInfoModule;

    @Override
    public void attach(PersonInfoContract.View view) {
        super.attach(view);
        mModule = new PersonInfoModule();
        mCVInfoModule = new CreateCVInfoMenuModule();
    }

    /**
     * 开始上传头像
     *
     * @param filePath
     */
    @Override
    public void startUploadUserAvatar(String filePath) {
        final File uploadFile = new File(filePath);
        if (uploadFile.length() >= 1024 * 1024 * 2) {
            ApiClient.pictureCompression(getView().getContext(), uploadFile, new OnCompressListener() {
                @Override
                public void onStart() {
                    getView().showProgressDialog("图片上传排队中...");
                }

                @Override
                public void onSuccess(File file) {
                    getView().hindProgressDialog();
                    uploadUserAvatar(file);
                }

                @Override
                public void onError(Throwable e) {
                    getView().hindProgressDialog();
                    uploadUserAvatar(uploadFile);
                }
            });
        } else {
            uploadUserAvatar(uploadFile);
        }
    }

    private void uploadUserAvatar(File avatarFile) {
        mModule.uploadUserAvatar(getView().getContext(), LoginHelper.getInstance().getUserToken(), avatarFile, new OnDataGetCallback<String>() {
            @Override
            public void onSuccessResult(String data) {
                getView().showToast("修改头像成功");
                updateUserData(data, false);
            }
        });
    }

    private void updateUserData(String value, boolean isName) {
        User userBean = LoginHelper.getInstance().getUserBean();
        if (isName)
            userBean.setUserName(value);
        else
            userBean.setAvatar(value);
        UserUtils.saveUser(getView().getContext(), userBean);
        Intent intent = new Intent();
        if (isName)
            intent.setAction(Constant.LOCAL_BROADCAST_USER_NAME_FLAG);
        else
            intent.setAction(Constant.LOCAL_BROADCAST_USER_AVATAR_FLAG);
        LocalBroadcastManager.getInstance(getView().getContext()).sendBroadcast(intent);
    }

    @Override
    public void loadListData() {
        mModule.requestPersonInfo(getView().getContext(), LoginHelper.getInstance().getUserToken(), new OnDataGetCallback<PersonInfoBean>() {
            @Override
            public void onSuccessResult(PersonInfoBean personInfoBean) {
                ApiClient.menuDataToList(personInfoBean, mModule.getMenuBeanList());
                if (personInfoBean != null) {
                    String userPicture = personInfoBean.getUserPicture();
                    String avatar = LoginHelper.getInstance().getUserBean().getAvatar();
                    if (avatar != null && !userPicture.equals(avatar)) {
                        updateUserData(userPicture, false);
                    }
                }
                getView().bindListData(mModule.getMenuBeanList());
                getView().initRightView();
            }
        });
    }

    @Override
    public void onClickSave() {
        final PersonInfoBean updateInfoBean = new PersonInfoBean();
        updateInfoBean.setEducationVal(mModule.getPersonInfoBean().getEducation());

        List<String> strings = ApiClient.checkMenuListIsEmpty(mModule.getMenuBeanList());

        if (!strings.isEmpty()) {
            getView().showToast("请完善您的" + strings.get(0));
            return;
        }
        ApiClient.menuListToData(mModule.getMenuBeanList(), updateInfoBean);
        if (!RECheckUtils.checkTimeWithCurrent(updateInfoBean.getBirthDate())){
            getView().showToast("生日不能大于当前时间");
            return;
        }
        if (updateInfoBean.getBirthDate() != "" && updateInfoBean.getWorkDate() != ""
                && !RECheckUtils.checkTwoTime(updateInfoBean.getBirthDate(),updateInfoBean.getWorkDate())){
            getView().showToast("参加工作日期不能早于出生日期");
            return;
        }
        System.out.println("----开始保存上传-----");
        User userBean = LoginHelper.getInstance().getUserBean();

        if (userBean.getUserName() != null && !userBean.getUserName().equals(updateInfoBean.getName())) {
            isChangeName = true;
        }

        mModule.updatePersonInfo(getView().getContext(), userBean.getToken(), updateInfoBean, new OnDataGetCallback<Boolean>() {
            @Override
            public void onSuccessResult(Boolean s) {
                if (isChangeName) {
                    updateUserData(updateInfoBean.getName(), true);
                }
                getView().changeSaveState(!s);
                if (s) {
                    getView().showToast("修改成功");
                } else {
                    getView().showToast("修改失败");
                }

            }
        });
    }

    @Override
    public void initMenu() {
        getView().initRecyclerView();
        mModule.attachMenuList(getView().getMenuList());
    }

    /**
     * 修改学历信息
     *
     * @param value
     * @param position
     */
    @Override
    public void updateEducationItem(String value, int position) {
        mModule.getCurrentClickMenuBean().setUpdateValue(mCVInfoModule.getAttrSelectList().get(position).getDistinguishId());
        itemDataChange(value);
    }

    /**
     * 当Item的数据更新时执行这个方法
     *
     * @param inputContent
     */
    @Override
    public void itemDataChange(String inputContent) {
        //更改单行item的数据
        getView().updateSingleItem(mModule.getCurrentClickPosition(), inputContent);
    }

    @Override
    public void onItemClick(RecyclerView.ViewHolder vh, int position) {

        final MenuBean bean = mModule.getItemBeanForPosition(position);
        String hintText = "";
        String titleText = "";
        int inputType = -1;
        switch (bean.getId()) {
            case R.id.person_menu_item_avatar:
                getView().openSelectPhotoPop();
                break;
            case R.id.person_menu_item_name:
            case R.id.person_menu_item_phone:
            case R.id.person_menu_item_emil:
                if (bean.getId() == R.id.person_menu_item_phone) {
                    hintText = "请输入您的手机号";
                    titleText = "联系电话";
                    inputType = InputDataPopWindow.INPUT_TYPE_PHONE;
                    getView().setInputDataMax(14);
                } else if (bean.getId() == R.id.person_menu_item_emil) {
                    hintText = "请输入您的邮箱";
                    titleText = "邮箱";
                    inputType = InputDataPopWindow.INPUT_TYPE_EMAIL;
                    getView().setInputDataMax(50);
                } else if (bean.getId() == R.id.person_menu_item_name) {
                    hintText = "请输入您的姓名";
                    titleText = "姓名";
                    getView().setInputDataMax(50);
                }
                getView().setPopInputType(inputType);
                getView().setPopInputHintText(hintText);
                getView().setPopTitleText(titleText);
                getView().openInputSinglePop(bean.getMenuValue());
                break;
            case R.id.person_menu_item_birthday:
            case R.id.person_menu_item_job_day:
                getView().showDateSelectDialog(DateUtils.stringToDate(bean.getMenuValue()));
                break;
            case R.id.person_menu_item_sex:
                getView().showSexSelectDialog(bean.getMenuValue());
                break;
            case R.id.person_menu_item_overseas:
                getView().showOverseasDialog(bean.getMenuValue());
                break;
            case R.id.person_menu_item_marital_status:
                getView().initMaritalSelectPop();
                getView().showSelectPop(mModule.getMaritalStatusList(),bean.getMenuValue());
                break;
            case R.id.person_menu_item_education:
                getView().initEducationSelectPop();
                mCVInfoModule.requestEducationList(getView().getContext(), new OnDataGetCallback<List<AttrSelectBean>>() {
                    @Override
                    public void onSuccessResult(List<AttrSelectBean> data) {
                        List<String> dataSource = new ArrayList<>();
                        for (AttrSelectBean datum : data) {
                            dataSource.add(datum.getValue());
                        }
                        getView().showSelectPop(dataSource,bean.getMenuValue());
                    }
                });
                break;
        }
    }
}
