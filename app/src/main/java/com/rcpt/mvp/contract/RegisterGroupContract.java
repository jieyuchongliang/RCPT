package com.rcpt.mvp.contract;

import com.rcpt.base.mvp.BaseView;

import java.util.List;

/**
 * Created by lvzp on 2017/2/21.
 * 类描述：
 */

public interface RegisterGroupContract {

    interface View extends BaseView {


        /**
         * 判断所选城市是不是济南
         *
         * @param provinceId
         * @param cityId
         * @return
         */
        boolean isJinan(String provinceId, String cityId);

        void chooseIndustryIn();

        void chooseProvinceIn();

        void chooseImg();

        String getInputEnterpriseName();

        String getPhoneNum();

        String getEmail();

        String getUsername();

        String getPassword();

        String getIndustryId();

        String getProvinceId();

        String getCityId();

        String getLicenseUrl();


        /**
         * 设置列表选择的标题
         *
         * @param titleText
         */
        void setListSelectPopTitle(String titleText);

        /**
         * 绑定List列表显示的数据
         *
         * @param listData
         */
        void bindAttrListData(List<String> listData, String selectItemName);

        void showListSelectPop();

        String getAddressDetail();

        String getContact();

        String getOrganizeCode();

        boolean checkAddressDetailInputEmpty();

        boolean checkContactInputEmpty();

        boolean checkOrganizeCodeInputEmpty();

        boolean checkEnterpriseNameInputEmpty();

        boolean checkIndustryTextEmpty();

        boolean checkAddressTextEmpty();

        boolean checkPhoneInputEmpty();

        boolean checkEmailInputEmpty();

        boolean checkUsernameInputEmpty();

        boolean checkPasswordInputEmpty();

        boolean checkRPasswordInputEmpty();

        boolean checkRPassword();

        /**
         * 获取列表选择的点击条目
         *
         * @return
         */
        int getListSelectClickPosition();

        void updateValue(String value);

        /**
         * 返回注册成功的消息
         */
        void resetRegisterOk();
    }

    interface Presenter {

        void onListSelectItemClick();

        void onClickChooseIndustry();

        void onClickChooseAddress();

        void onClickChooseImg();

        void onClickRegister();

        void onClickGoRegisterClause();

        void onClickChooseAuditing();
    }

}
