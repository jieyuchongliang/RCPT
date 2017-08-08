package com.rcpt.ui.register.group;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.lzp.statusbar.utils.AndroidBug5497Workaround;
import com.rcpt.Constant;
import com.rcpt.R;
import com.rcpt.base.ui.BaseActivity;
import com.rcpt.bean.ChooseIndustryBean;
import com.rcpt.bean.ChooseProvinceBean;
import com.rcpt.databinding.ActivityRegisterGroupBinding;
import com.rcpt.mvp.contract.RegisterGroupContract;
import com.rcpt.mvp.presenter.RegisterGroupPresenter;
import com.rcpt.widget.ListSelectPopWindow;
import com.rcpt.widget.SimpleListSelectPopWindow;
import com.rcpt.widget.SinglePictureSelectPopWindow;

import java.util.ArrayList;
import java.util.List;

/**
 * 企业注册
 */
public class RegisterGroupActivity extends BaseActivity<ActivityRegisterGroupBinding,
        RegisterGroupContract.View, RegisterGroupPresenter> implements RegisterGroupContract.View, ListSelectPopWindow.OnListSelectDataCallback {

    private static final int REQUEST_CODE_CHOOSE_INDUSTRY = 1;
    private static final int REQUEST_CODE_CHOOSE_PROVINCE = 2;
    ArrayList<ChooseIndustryBean> selectBeanList;
    ArrayList<ChooseProvinceBean> selectedProvinceList;
    private ChooseProvinceBean provinceBean;
    private ChooseProvinceBean cityBean;
    private SinglePictureSelectPopWindow mSinglePictureSelectPopWindow;
    private String filePath;//营业执照路径
    private String industryId;
    private String provinceId;
    private String cityId;
    private ListSelectPopWindow<String> mListSelectPop;
    private int mSelectItemPosition;

    @Override
    protected void setupTitle() {
        openTitleLeftView(true);
        setTitleText("企业注册", DEFAULT_TITLE_TEXT_COLOR);
    }

    @Override
    protected void initViews() {
        //解决输入内容被软禁盘遮挡问题
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        mDataBinding.setPresenter(mPresenter);
        mListSelectPop = new SimpleListSelectPopWindow(this);
        mListSelectPop.setOnListSelectCallback(this);
        mSinglePictureSelectPopWindow = new SinglePictureSelectPopWindow(this);
        mSinglePictureSelectPopWindow.createHelper();
        mSinglePictureSelectPopWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                lightOn();
            }
        });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_register_group;
    }

    @Override
    protected RegisterGroupPresenter createPresenter() {
        return new RegisterGroupPresenter();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public void chooseIndustryIn() {
        ChooseIndustryActivity.actionStart(this, selectBeanList, ChooseIndustryActivity.SELECT_MODE_INFINITE, REQUEST_CODE_CHOOSE_INDUSTRY);
    }

    @Override
    public void chooseProvinceIn() {
        ChooseProvinceActivity.actionStart(this, false, REQUEST_CODE_CHOOSE_PROVINCE);
    }

    @Override
    public void chooseImg() {
        mSinglePictureSelectPopWindow.showPopupWindow(mDataBinding.getRoot());
        lightOff();
    }

    @Override
    public String getInputEnterpriseName() {
        return mDataBinding.inputEnterpriseName.getInputText().toString().trim();
    }

    @Override
    public String getPhoneNum() {
        return mDataBinding.inputPhone.getInputText().toString().trim();
    }

    @Override
    public String getEmail() {
        return mDataBinding.inputEmail.getInputText().toString().trim();
    }

    @Override
    public String getUsername() {
        return mDataBinding.inputUsername.getInputText().toString().trim();
    }

    @Override
    public String getPassword() {
        return mDataBinding.inputPassword.getInputText().toString().trim();
    }

    @Override
    public String getIndustryId() {
        return industryId;
    }

    @Override
    public String getProvinceId() {
        return provinceId;
    }

    @Override
    public String getCityId() {
        return cityId;
    }

    @Override
    public String getLicenseUrl() {
        return filePath;
    }

    @Override
    public String getAddressDetail() {
        return mDataBinding.inputDetailAddress.getInputText().toString().trim();
    }

    @Override
    public String getContact() {
        return mDataBinding.inputContacts.getInputText().toString().trim();
    }

    @Override
    public String getOrganizeCode() {
        return mDataBinding.inputInstitutionCode.getInputText().toString().trim();
    }

    @Override
    public boolean checkAddressDetailInputEmpty() {
        return checkInputEmpty(mDataBinding.inputDetailAddress.getInputEditView());
    }

    @Override
    public boolean checkContactInputEmpty() {
        return checkInputEmpty(mDataBinding.inputContacts.getInputEditView());
    }

    @Override
    public boolean checkOrganizeCodeInputEmpty() {
        return checkInputEmpty(mDataBinding.inputInstitutionCode.getInputEditView());
    }

    @Override
    public boolean checkEnterpriseNameInputEmpty() {
        return checkInputEmpty(mDataBinding.inputEnterpriseName.getInputEditView());
    }

    @Override
    public boolean checkIndustryTextEmpty() {
        return checkInputEmpty(mDataBinding.textIndustry);
    }

    @Override
    public boolean checkAddressTextEmpty() {
        return checkInputEmpty(mDataBinding.textAddress);
    }

    @Override
    public boolean checkPhoneInputEmpty() {
        return checkInputEmpty(mDataBinding.inputPhone.getInputEditView());
    }

    @Override
    public boolean checkEmailInputEmpty() {
        return checkInputEmpty(mDataBinding.inputEmail.getInputEditView());
    }

    @Override
    public boolean checkUsernameInputEmpty() {
        return checkInputEmpty(mDataBinding.inputUsername.getInputEditView());
    }

    @Override
    public boolean checkPasswordInputEmpty() {
        return checkInputEmpty(mDataBinding.inputPassword.getInputEditView());
    }

    /**
     * 企业注册判断确认密码是否输入(wang)
     *
     * @return 空返回true, 否则返回false
     */
    @Override
    public boolean checkRPasswordInputEmpty() {
        return checkInputEmpty(mDataBinding.inputRPassword.getInputEditView());
    }

    @Override
    public boolean checkRPassword() {
        return mDataBinding.inputPassword.getInputText().toString().equals(mDataBinding.inputRPassword.getInputText().toString());
    }


    private boolean checkInputEmpty(TextView textView) {
        String text = textView.getText().toString();
        return TextUtils.isEmpty(text);
    }

    /**
     * 判断所选城市是不是济南
     *
     * @param provinceId
     * @param cityId
     * @return
     */
    @Override
    public boolean isJinan(String provinceId, String cityId) {
        return Constant.SHANDONG_PROVINCE_CODE.equals(provinceId) && Constant.JINAN_CITY_CODE.equals(cityId);
    }

    /**
     * 设置列表选择的标题
     *
     * @param titleText
     */
    @Override
    public void setListSelectPopTitle(String titleText) {
        mListSelectPop.setTitleText(titleText);
    }

    @Override
    public void showListSelectPop() {
        mListSelectPop.showPopupWindow(mDataBinding.getRoot());
    }

    /**
     * 绑定List列表显示的数据
     *
     * @param listData
     */
    @Override
    public void bindAttrListData(List<String> listData, String selectItemName) {
        mListSelectPop.setSelectItemName(selectItemName);
        mListSelectPop.bindListData(listData);
    }

    /**
     * 当条目被点击时的回调方法
     *
     * @param data     被选中的Item名称
     * @param position 被选中Item的Position
     */
    @Override
    public void onSelectCallback(String data, int position) {
        mSelectItemPosition = position;
        mPresenter.onListSelectItemClick();
    }

    @Override
    public int getListSelectClickPosition() {
        return mSelectItemPosition;
    }

    @Override
    public void updateValue(String value) {
        mDataBinding.textAuditingGroup.setText(value);
    }

    /**
     * 返回注册成功的消息
     */
    @Override
    public void resetRegisterOk() {
        setResult(RESULT_OK);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            filePath = mSinglePictureSelectPopWindow.getSelectHelper().onActivityResult(requestCode, resultCode, data, mDataBinding.ivPhoto, false);
            switch (requestCode) {
                case REQUEST_CODE_CHOOSE_INDUSTRY:
                    if (data != null) {
                        String industryTxt = "";
                        industryId = "";
                        selectBeanList = data.getParcelableArrayListExtra("result_data");
                        for (int i = 0; i < selectBeanList.size(); i++) {
                            industryTxt += selectBeanList.get(i).getValue() + "、";
                            industryId += selectBeanList.get(i).getId() + ",";
                        }
                        industryId = industryId.substring(0, industryId.length() - 1);
                        mDataBinding.textIndustry.setText(industryTxt.substring(0, industryTxt.length() - 1));
                    }
                    break;
                case REQUEST_CODE_CHOOSE_PROVINCE:
                    if (data != null) {
                        provinceBean = data.getParcelableExtra("provinceBean");
                        cityBean = data.getParcelableExtra("cityBean");
                        String addressTxt = provinceBean.getRegionName();
                        addressTxt += "-";
                        addressTxt += cityBean.getRegionName();
                        provinceId = provinceBean.getRegionId();
                        cityId = cityBean.getRegionId();
                        mDataBinding.textAddress.setText(addressTxt);
                        if (isJinan(provinceBean.getRegionId(), cityBean.getRegionId())) {
                            mDataBinding.llAuditingGroup.setVisibility(View.VISIBLE);
                        } else {
                            mDataBinding.llAuditingGroup.setVisibility(View.GONE);
                        }

                    }
                    break;
            }
        }
    }

}
