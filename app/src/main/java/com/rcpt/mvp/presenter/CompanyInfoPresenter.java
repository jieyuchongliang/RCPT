package com.rcpt.mvp.presenter;

import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import android.text.TextUtils;

import com.rcpt.Constant;
import com.rcpt.LoginHelper;
import com.rcpt.R;
import com.rcpt.base.mvp.BasePresenter;
import com.rcpt.base.mvp.OnDataGetCallback;
import com.rcpt.bean.AttrSelectBean;
import com.rcpt.bean.ChooseIndustryBean;
import com.rcpt.bean.ChooseProvinceBean;
import com.rcpt.bean.CompanyInfoBean;
import com.rcpt.bean.InputMenuBean;
import com.rcpt.bean.User;
import com.rcpt.http.api.ApiClient;
import com.rcpt.mvp.contract.CompanyInfoContract;
import com.rcpt.mvp.module.ChooseProvinceModule;
import com.rcpt.mvp.module.CompanyInfoModule;
import com.rcpt.mvp.module.CreateCVInfoMenuModule;
import com.rcpt.utils.RECheckUtils;
import com.rcpt.utils.UserUtils;
import com.rcpt.widget.InputDataPopWindow;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import top.zibin.luban.OnCompressListener;

/**
 * Created by lvzp on 2017/4/10.
 * 类描述：
 */

public class CompanyInfoPresenter extends BasePresenter<CompanyInfoContract.View> implements CompanyInfoContract.Presenter {


    private int mBusinessBureauPosition = 9;
    private CompanyInfoModule mModule;
    private CreateCVInfoMenuModule mCVInfoModule;
    private ChooseProvinceModule mProvinceModule;
    private CompanyInfoBean mCompanyInfoBean;
    private boolean isChangeName;

    private String mTempCommerceBureau;//临时的商务局变量

    private boolean isAddSuc;

    private int mClickItemId;

    @Override
    public void attach(CompanyInfoContract.View view) {
        super.attach(view);
        mProvinceModule = new ChooseProvinceModule();
        mModule = new CompanyInfoModule();
        mCVInfoModule = new CreateCVInfoMenuModule();
    }

    @Override
    public void initMenu() {
        getView().initRecyclerView();
        mModule.attachMenuList(getView().getMenuList());
    }

    @Override
    public void onItemClick() {
        int position = getView().getClickItemPosition();
        final InputMenuBean bean = mModule.getItemBeanForPosition(position);
        String menuValue = bean.getMenuValue();
        String menuName = bean.getMenuName();
        mClickItemId = bean.getId();
        switch (mClickItemId) {
            case R.id.company_menu_item_logo:
                getView().showPictureSelectPop();
                break;
           /* case R.id.company_menu_item_name:
                showInputSingleDataPop(menuValue, "公司名称");
                break;*/
            case R.id.company_menu_item_service_distribution:
                showInputSingleDataPop(menuValue, InputDataPopWindow.INPUT_TYPE_TEXT, menuName, 20);
                break;
            case R.id.company_menu_item_legal_representative:
                showInputSingleDataPop(menuValue, InputDataPopWindow.INPUT_TYPE_TEXT, menuName, 50);
                break;
            case R.id.company_menu_item_address_detail:
                showInputDtaPop(menuValue, menuName, 255);
                break;
            case R.id.company_menu_item_contact:
                showInputSingleDataPop(menuValue, InputDataPopWindow.INPUT_TYPE_TEXT, menuName, 20);
                break;
            case R.id.company_menu_item_tel:
                showInputSingleDataPop(menuValue, InputDataPopWindow.INPUT_TYPE_PHONE, menuName, 20);
                break;
            case R.id.company_menu_item_mail:
                showInputSingleDataPop(menuValue, InputDataPopWindow.INPUT_TYPE_EMAIL, menuName, 50);
                break;
            case R.id.company_menu_item_website:
                showInputDtaPop(menuValue,menuName, 200);
                break;
            case R.id.company_menu_item_zip_code:
                showInputSingleDataPop(menuValue, InputDataPopWindow.INPUT_TYPE_ZIP_CODE, menuName, 6);
                break;
            case R.id.company_menu_item_registered_capital:
                showInputSingleDataPop(menuValue, InputDataPopWindow.INPUT_TYPE_TEXT, menuName, 20);
                break;
            case R.id.company_menu_item_scale:
                showInputSingleDataPop(menuValue, InputDataPopWindow.INPUT_TYPE_TEXT, menuName, 6);
                break;
            case R.id.company_menu_item_company_introduction:
                showInputDtaPop(menuValue, menuName, 65535);
                break;
            case R.id.company_menu_item_register_time://注册时间
                getView().showDateDialog(mCompanyInfoBean.getRegistTime());
                break;
            case R.id.company_menu_item_industry://进入到公司行业
                getView().startGoIndustry();
                break;
            case R.id.company_menu_item_address:
                getView().startGoCitySelect();
                break;
            case R.id.company_menu_item_enterprise_nature://公司性质
                getView().setListSelectPopTitle(menuName);
                getView().showListSelectPop();
                mCVInfoModule.requestCompanyTypeList(getView().getContext(), new OnDataGetCallback<List<AttrSelectBean>>() {
                    @Override
                    public void onSuccessResult(List<AttrSelectBean> data) {
                        List<String> list = new ArrayList<>();
                        for (AttrSelectBean educationBean : data) {
                            list.add(educationBean.getValue());
                        }
                        getView().bindAttrListData(list, bean.getMenuValue());
                    }
                });
                break;
            case R.id.company_menu_item_is_quotedCompany://是否上市
                getView().showSingleSelectDialog(bean.getMenuValue());
                break;
            case R.id.company_menu_item_business_bureau://所属机构
                getView().setListSelectPopTitle("所属机构选择列表");
                getView().showListSelectPop();
                mProvinceModule.requestProvinceList(getView().getContext(), Constant.JINAN_CITY_CODE, new OnDataGetCallback<List<ChooseProvinceBean>>() {
                    @Override
                    public void onSuccessResult(List<ChooseProvinceBean> data) {
                        List<String> list = new ArrayList<>();
                        for (ChooseProvinceBean bean : data) {
                            list.add(bean.getRegionName() + "商务局");
                        }
                        getView().bindAttrListData(list, bean.getMenuValue());
                    }
                });
                break;
        }
    }

    /**
     * 当城市选择完成后的回调
     */
    @Override
    public void onCitySelectOverCallback() {
        String inputData = getView().getInputData();
        getView().updateSingleItem(mModule.getCurrentClickPosition(), inputData);
    }

    @Override
    public void onSelectJinan() {
        InputMenuBean menuBean = mModule.buildMenuBean(getView().getMenuItemForId(R.id.company_menu_item_business_bureau));
        mModule.getMenuBeanList().add(mBusinessBureauPosition, menuBean);
        getView().insertSingleItem(mBusinessBureauPosition);
        getView().updateSingleItem(mBusinessBureauPosition, mTempCommerceBureau);
        isAddSuc = true;
    }

    @Override
    public void onUnSelectJinan() {
        if (isAddSuc) {
            mModule.getMenuBeanList().remove(mBusinessBureauPosition);
            getView().removeSingleItem(mBusinessBureauPosition);
            isAddSuc = false;
        }

    }

    /**
     * 当时间选择器选择完成后的回调
     */
    @Override
    public void onDateSelectCallback() {
        String dateSelectData = getView().getDateSelectData();
        getView().updateSingleItem(mModule.getCurrentClickPosition(), dateSelectData);
    }

    /**
     * 当图片选择完成后的回调
     */
    @Override
    public void onPictureSelectCallback() {
        String logoUrl = getView().getCompanyLogoUrl();
        getView().updateSingleItem(mModule.getCurrentClickPosition(), logoUrl);
        ApiClient.pictureCompressionWhiteCheck(getView().getContext(), new File(logoUrl), new OnCompressListener() {
            @Override
            public void onStart() {
                getView().showProgressDialog("图片上传排队中...");
            }

            @Override
            public void onSuccess(File file) {
                getView().hindProgressDialog();
                mModule.updateCompanyLogo(getView().getContext(), file, LoginHelper.getInstance().getUserToken(), new OnDataGetCallback<String>() {
                    @Override
                    public void onSuccessResult(String data) {
                        getView().showToast("Logo修改成功");
                        updateUserData(data, false);
                        getView().changeSaveState(false);
                    }
                });
            }

            @Override
            public void onError(Throwable e) {
                getView().hindProgressDialog();
            }
        });

    }


    /**
     * 当列表选择的条目被点击
     */
    @Override
    public void onListSelectItemClick() {
        int position = getView().getListSelectClickPosition();
        switch (mClickItemId) {
            case R.id.company_menu_item_enterprise_nature://公司性质
                AttrSelectBean bean = mCVInfoModule.getAttrSelectList().get(position);
                updateValue(bean.getValue(), bean.getDistinguishId());
                break;
            case R.id.company_menu_item_business_bureau:
                ChooseProvinceBean provinceBean = mProvinceModule.getListData().get(position);
                updateValue(provinceBean.getRegionName() + "商务局", provinceBean.getRegionId());
                break;
        }

    }


    /**
     * 当单选的数据返回是的调用方法
     */
    @Override
    public void onSingleDataSelectCallback() {
        String inputData = getView().getInputData();
        String updateValue = getView().getUpdateValue();
        updateValue(inputData, updateValue);
    }

    @Override
    public void onInputPopClickSave() {
        String inputData = getView().getInputData();
        //更改单行item的数据
        getView().updateSingleItem(mModule.getCurrentClickPosition(), inputData);
    }


    @Override
    public void loadListData() {
        mModule.requestCompanyInfo(getView().getContext(), LoginHelper.getInstance().getUserToken(), new OnDataGetCallback<CompanyInfoBean>() {
            @Override
            public void onSuccessResult(CompanyInfoBean data) {
                mCompanyInfoBean = data;
                mCompanyInfoBean.setAddress(mCompanyInfoBean.getAddressProvince() + mCompanyInfoBean.getAddressCity());
                mCompanyInfoBean.setAddress(mCompanyInfoBean.getAddress().replace("null", ""));
                initIndustryList(mCompanyInfoBean.getIndustry(), mCompanyInfoBean.getIndustryId());
                ApiClient.menuDataToList(mCompanyInfoBean, mModule.getMenuBeanList());
                getView().bindListData(mModule.getMenuBeanList());
                if (getView().isJinan(data.getProvinceId(), data.getCityId())) {
                    onSelectJinan();
                    String value = (mCompanyInfoBean.getAddressArea() + "商务局").replace("null", "");
                    mTempCommerceBureau = value;
                    getView().updateAddressId(mCompanyInfoBean.getProvinceId(), mCompanyInfoBean.getCityId());
                    getView().updateSingleItem(mBusinessBureauPosition, value);
                    getView().changeSaveState(false);
                }
            }
        });
    }

    /**
     * 当保存按钮的点击事件
     */
    @Override
    public void onClickSave() {
        final CompanyInfoBean bean = new CompanyInfoBean();

        List<String> strings = ApiClient.checkMenuListIsEmpty(mModule.getMenuBeanList());

        if (!strings.isEmpty()) {
            getView().showToast("请完善您的" + strings.get(0));
            return;
        }

        bean.setIndustry(mCompanyInfoBean.getIndustryId());
        bean.setEnterpriseNature(mCompanyInfoBean.getEnterpriseNatureId());
        bean.setAddressProvince(mCompanyInfoBean.getProvinceId());
        bean.setAddressCity(mCompanyInfoBean.getCityId());
        bean.setCompanyId(LoginHelper.getInstance().getUserToken());
        bean.setIsQuotedCompany(mCompanyInfoBean.getIsQuotedCompanyId());
        bean.setAddressProvince(getView().getAddressProvinceId());
        bean.setAddressCity(getView().getAddressCityId());

        ApiClient.menuListToData(mModule.getMenuBeanList(), bean);
        if (!bean.getComName().equals(mCompanyInfoBean.getComName())) {
            isChangeName = true;
        }

        if (getView().isJinan(bean.getAddressProvince(), bean.getAddressCity())) {
            //判断如果当前的所属机关没有做选择时进行以下炒作
            if (TextUtils.isEmpty(bean.getAddressArea())) {
                if (TextUtils.isEmpty(mCompanyInfoBean.getAddressAreaId())) {
                    getView().showToast("必须选择所属机关");
                    return;
                } else {
                    bean.setAddressArea(mCompanyInfoBean.getAddressAreaId());
                }
            } else {
                mTempCommerceBureau = mModule.getMenuBeanList().get(mBusinessBureauPosition).getMenuValue();
            }

        }
        if (!"".equals(bean.getWebsite()) && !RECheckUtils.checkUrl(bean.getWebsite())){
            getView().showToast("网址格式不正确，请确认");
            return;
        }

        Map<String, String> params = ApiClient.createBodyMap(bean);

        mModule.updateCompanyInfo(getView().getContext(), params, new OnDataGetCallback<String>() {
            @Override
            public void onSuccessResult(String data) {
                getView().showToast(data);
                getView().changeSaveState(false);
                if (isChangeName) {
                    updateUserData(bean.getComName(), true);
                }

            }
        });
    }

    private void updateValue(String data, String value) {
        mModule.getCurrentClickMenuBean().setUpdateValue(value);
        getView().updateSingleItem(mModule.getCurrentClickPosition(), data);
    }

    private void showInputDtaPop(String menuValue, String titleName, int inputDataMax) {
        getView().setInputDataMax(inputDataMax, false);
        getView().setInputDataTitle(titleName);
        getView().showInputPop(menuValue);

    }

    private void showInputSingleDataPop(String menuValue, @InputDataPopWindow.InputType int inputType, String titleName, int inputDataMax) {
        getView().setInputSingleDataTitle(titleName);
        getView().setInputDataMax(inputDataMax, true);
        getView().showInputSinglePopWithType(menuValue, inputType);
    }

    private void initIndustryList(String industry, String industryId) {
        if (TextUtils.isEmpty(industry) || TextUtils.isEmpty(industryId))
            return;
        String[] industrys = industry.split(",");
        String[] industryIds = industryId.split(",");
        int count = Math.min(industrys.length, industryIds.length);
        ChooseIndustryBean[] beens = new ChooseIndustryBean[count];
        for (int i = 0; i < count; i++) {
            ChooseIndustryBean bean = new ChooseIndustryBean();
            bean.setId(industryIds[i]);
            bean.setValue(industrys[i]);
            bean.setSelect(true);
            beens[i] = bean;
        }
        getView().initIndustrySelectList(beens);
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
}
