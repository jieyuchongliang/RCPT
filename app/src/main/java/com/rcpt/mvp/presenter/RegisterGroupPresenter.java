package com.rcpt.mvp.presenter;

import android.content.Context;
import android.content.DialogInterface;
import android.text.TextUtils;

import com.rcpt.Constant;
import com.rcpt.base.mvp.BasePresenter;
import com.rcpt.base.mvp.OnDataGetCallback;
import com.rcpt.bean.ChooseProvinceBean;
import com.rcpt.http.api.ApiClient;
import com.rcpt.http.bean.EnterpriseRegisterBean;
import com.rcpt.mvp.contract.RegisterGroupContract;
import com.rcpt.mvp.module.ChooseProvinceModule;
import com.rcpt.mvp.module.RegisterGroupModule;
import com.rcpt.mvp.module.RequestModule;
import com.rcpt.ui.register.RegisterClauseActivity;
import com.rcpt.utils.DialogUtils;
import com.rcpt.utils.RECheckUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import top.zibin.luban.OnCompressListener;

/**
 * Created by lvzp on 2017/2/21.
 * 类描述：
 */

public class RegisterGroupPresenter extends BasePresenter<RegisterGroupContract.View> implements RegisterGroupContract.Presenter {

    private RegisterGroupModule mRegisterGroupModule;
    private RequestModule mRequestModule;
    private EnterpriseRegisterBean mEnterpriseRegisterBean;
    private ChooseProvinceModule mProvinceModule;
    private String mTempCommerceBureau;//临时的商务局变量
    private String mCommerceBureauId;//选择商务局的id

    @Override
    public void attach(RegisterGroupContract.View view) {
        super.attach(view);
        mProvinceModule = new ChooseProvinceModule();
        mRegisterGroupModule = new RegisterGroupModule();
        mRequestModule = new RequestModule();
    }

    /**
     * 所属机关列表的条目被点击后的业务方法
     */
    @Override
    public void onListSelectItemClick() {
        int position = getView().getListSelectClickPosition();
        ChooseProvinceBean provinceBean = mProvinceModule.getListData().get(position);
        mCommerceBureauId = provinceBean.getRegionId();
        mTempCommerceBureau = provinceBean.getRegionName() + "商务局";
        getView().updateValue(mTempCommerceBureau);
    }

    @Override
    public void onClickChooseIndustry() {
        getView().chooseIndustryIn();  //进入选择行业列表
    }

    @Override
    public void onClickChooseAddress() {
        getView().chooseProvinceIn();  //进入选择省份列表
    }

    /**
     * 选择机关的方法
     */
    @Override
    public void onClickChooseAuditing() {

        getView().setListSelectPopTitle("所属机构选择列表");
        getView().showListSelectPop();
        mProvinceModule.requestProvinceList(getView().getContext(), Constant.JINAN_CITY_CODE, new OnDataGetCallback<List<ChooseProvinceBean>>() {
            @Override
            public void onSuccessResult(List<ChooseProvinceBean> data) {
                List<String> list = new ArrayList<>();
                for (ChooseProvinceBean bean : data) {
                    list.add(bean.getRegionName() + "商务局");
                }
                getView().bindAttrListData(list, mTempCommerceBureau);
            }
        });
    }

    @Override
    public void onClickChooseImg() {
        getView().chooseImg();
    }

    @Override
    public void onClickGoRegisterClause() {
        getView().actionStartActivity(RegisterClauseActivity.class);
    }


    @Override
    public void onClickRegister() {
        mEnterpriseRegisterBean = new EnterpriseRegisterBean();
        if (getView().checkEnterpriseNameInputEmpty()) {
            getView().showToast("企业名不能为空");
            return;
        }
        if (getView().checkIndustryTextEmpty()) {
            getView().showToast("请选择行业");
            return;
        }
        if (getView().checkAddressTextEmpty()) {
            getView().showToast("请选择企业地址");
            return;
        }
        if (getView().isJinan(getView().getProvinceId(), getView().getCityId())) {
            if (mCommerceBureauId == null) {
                getView().showToast("审核机关不能为空");
                return;
            } else {
                mEnterpriseRegisterBean.setAddress_area(mCommerceBureauId);
            }
        } else {
            //address_area是必要字段。如果判断不是济南市的企业，将address_area字段赋值为“-1”传给后台。否则报错。
            mEnterpriseRegisterBean.setAddress_area("-1");
        }
        if (getView().checkAddressDetailInputEmpty()) {
            getView().showToast("详细地址不能为空");
            return;
        }
        if (getView().checkPhoneInputEmpty()) {
            getView().showToast("手机号不能为空");
            return;
        } else if (!RECheckUtils.checkPhoneNum(getView().getPhoneNum())
                && !RECheckUtils.checkLandline(getView().getPhoneNum())) {
            getView().showToast("号码不合法，座机请在区号后加“-”");
            return;
        }
        if (getView().checkContactInputEmpty()) {
            getView().showToast("联系人不能为空");
            return;
        }
        if (getView().checkEmailInputEmpty()) {
            getView().showToast("邮箱不能为空");
            return;
        } else if (!RECheckUtils.checkEmail(getView().getEmail())) {
            getView().showToast("邮箱不合法,请检查");
            return;
        }
        if (getView().checkUsernameInputEmpty()) {
            getView().showToast("用户名不能为空");
            return;
        } else if (getView().getUsername().length() < 4) {
            getView().showToast("用户名至少要有四位");
            return;
        }
        if (getView().checkPasswordInputEmpty()) {
            getView().showToast("密码不能为空");
            return;
        } else if (RECheckUtils.checkPassword(getView().getPassword()) == 1) {
            getView().showToast("输入密码不合法");
            return;
        } else if (RECheckUtils.checkPassword(getView().getPassword()) == 2) {
            getView().showToast("密码中不能包含中文字符");
            return;
        }
        if (getView().checkRPasswordInputEmpty()) {
            getView().showToast("请确认输入密码");
            return;
        }
        if (!getView().checkRPassword()) {
            getView().showToast("两次输入密码不一致");
            return;
        }

        if (getView().checkOrganizeCodeInputEmpty()) {
            getView().showToast("组织机构代码不能为空");
            return;
        } else if (!RECheckUtils.checkGroupCode(getView().getOrganizeCode())) {
            getView().showToast("组织机构代码不合法");
            return;
        }

        if (TextUtils.isEmpty(getView().getLicenseUrl())) {
            getView().showToast("请选择营业执照图片");
            return;
        }
        mEnterpriseRegisterBean.setAddress_detail(getView().getAddressDetail());
        mEnterpriseRegisterBean.setContact(getView().getContact());
        mEnterpriseRegisterBean.setOrganize_code(getView().getOrganizeCode());
        mEnterpriseRegisterBean.setComName(getView().getInputEnterpriseName());
        mEnterpriseRegisterBean.setIndustry(getView().getIndustryId());
        mEnterpriseRegisterBean.setProvince(getView().getProvinceId());
        mEnterpriseRegisterBean.setCity(getView().getCityId());
        mEnterpriseRegisterBean.setPhone(getView().getPhoneNum());
        mEnterpriseRegisterBean.setEmail(getView().getEmail());
        mEnterpriseRegisterBean.setAccount(getView().getUsername());
        mEnterpriseRegisterBean.setPwd(getView().getPassword());
        mEnterpriseRegisterBean.setLicenseUrl(getView().getLicenseUrl());

        final Map<String, String> params = ApiClient.createBodyMap(mEnterpriseRegisterBean);
        if (RECheckUtils.checkLandline(getView().getPhoneNum())) {
            DialogUtils.builderAlertDialog((Context) getView(),
                    "警告", "尽量使用手机号注册，以便找回密码。" +
                            "\n点击确定，继续操作。点击取消，填写手机号")
                    .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            saveEnterpriseRegister(getView().getContext(),
                                    params, new OnDataGetCallback<String>() {
                                        @Override
                                        public void onSuccessResult(String o) {
                                            getView().showToast(o);
                                            getView().resetRegisterOk();
                                            getView().closeActivity();
                                        }
                                    });
                        }
                    })
                    .setNegativeButton("取消", null)
                    .show();
        } else {
            saveEnterpriseRegister(getView().getContext(),
                    params, new OnDataGetCallback<String>() {
                        @Override
                        public void onSuccessResult(String o) {
                            getView().showToast(o);
                            getView().resetRegisterOk();
                            getView().closeActivity();
                        }
                    });
        }
    }

    private void saveEnterpriseRegister(final Context context, final Map<String, String> params, final
    OnDataGetCallback<String> callback) {
        File license = new File(params.get("licenseUrl"));
        if (license.length() > 1024 * 1024) {
            ApiClient.pictureCompression(context, license, new OnCompressListener() {
                @Override
                public void onStart() {
                    getView().showProgressDialog("提交中...");
                }

                @Override
                public void onSuccess(File file) {
                    getView().hindProgressDialog();
                    mRegisterGroupModule.registerStart(context, params, callback, file);
                }

                @Override
                public void onError(Throwable e) {
                    getView().hindProgressDialog();
                }
            });
        } else {
            mRegisterGroupModule.registerStart(context, params, callback, license);
        }
    }
}
