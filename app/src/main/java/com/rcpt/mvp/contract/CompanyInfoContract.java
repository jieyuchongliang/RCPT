package com.rcpt.mvp.contract;

import android.support.annotation.IdRes;
import android.support.v7.view.menu.MenuItemImpl;

import com.rcpt.base.mvp.BaseView;
import com.rcpt.base.mvp.MenuLoadContract;
import com.rcpt.base.mvp.RecyclerViewContract;
import com.rcpt.bean.ChooseIndustryBean;
import com.rcpt.bean.InputMenuBean;
import com.rcpt.widget.InputDataPopWindow;

import java.util.List;

/**
 * Created by lvzp on 2017/4/10.
 * 类描述：
 */

public interface CompanyInfoContract {

    interface View extends BaseView, RecyclerViewContract.View<InputMenuBean>, MenuLoadContract.View {
        /**
         * 获取输入的数据
         *
         * @return
         */
        String getInputData();

        /**
         * 获取点击条目的Position
         *
         * @return
         */
        int getClickItemPosition();

        /**
         * 显示单行输入的PopWindow
         *
         * @param inputData
         */
        void showInputSinglePop(String inputData);

        /**
         * 显示单行输入的PopWindow并设置好输入的类型
         *
         * @param inputData
         * @param inputType
         */
        void showInputSinglePopWithType(String inputData, @InputDataPopWindow.InputType int inputType);

        /**
         * 显示多行输入的PopWindow
         *
         * @param inputData
         */
        void showInputPop(String inputData);

        /**
         * 显示时间选择器
         */
        void showDateDialog(String time);

        /**
         * 开启图片选择的PopWindow
         */
        void showPictureSelectPop();

        /**
         * 获取时间选择器选择后的数据
         *
         * @return
         */
        String getDateSelectData();

        /**
         * 获取公司Logo的Url
         *
         * @return
         */
        String getCompanyLogoUrl();

        /**
         * 更新指定单行条目的内容
         *
         * @param position
         * @param value
         */
        void updateSingleItem(int position, String value);

        /**
         * 设置多行输入框PopWindow的标题
         *
         * @param titleName
         */
        void setInputDataTitle(String titleName);

        /**
         * 设置单行输入框的标题
         *
         * @param titleName
         */
        void setInputSingleDataTitle(String titleName);

        /**
         * 初始化已选择的行业类别
         */
        void initIndustrySelectList(ChooseIndustryBean... beans);

        /**
         * 开始进入到行业列表选择列表
         */
        void startGoIndustry();


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
        void bindAttrListData(List<String> listData,String selectItemName);

        /**
         * 获取列表选择的点击条目
         *
         * @return
         */
        int getListSelectClickPosition();

        /**
         * 获取需要跟新的数据
         *
         * @return
         */
        String getUpdateValue();

        /**
         * 显示单选的Dialog
         */
        void showSingleSelectDialog(String selectName);

        /**
         * 改变修改的状态
         *
         * @param isChange
         */
        void changeSaveState(boolean isChange);

        /**
         * 获取省id
         *
         * @return
         */
        String getAddressProvinceId();

        /**
         * 获取市id
         *
         * @return
         */
        String getAddressCityId();

        /**
         * 开启城市选择的Activity
         */
        void startGoCitySelect();

        /**
         * 通过id获取对应的MenuItem的实例
         *
         * @param idRes
         * @return
         */
        MenuItemImpl getMenuItemForId(@IdRes int idRes);

        /**
         * 插入一个条目
         *
         * @param position
         */
        void insertSingleItem(int position);

        /**
         * 删除一个条目
         *
         * @param position
         */
        void removeSingleItem(int position);

        /**
         * 判断
         *
         * @param provinceId
         * @param cityId
         * @return
         */
        boolean isJinan(String provinceId, String cityId);

        void showListSelectPop();
        /**
         * 设置输入的内容最大值
         */
        void setInputDataMax(int max,boolean isSingle);

        void updateAddressId(String provinceId, String cityId);
    }

    interface Presenter extends RecyclerViewContract.Presenter, MenuLoadContract.Presenter {
        /**
         * 输入框点击保存时的回调
         */
        void onInputPopClickSave();

        /**
         * 列表Item的点击回调
         */
        void onItemClick();

        /**
         * 当时间选择器选择完成后的回调
         */
        void onDateSelectCallback();

        /**
         * 当图片选择完成后的回调
         */
        void onPictureSelectCallback();

        /**
         * 当列表选择的条目被点击
         */
        void onListSelectItemClick();

        /**
         * 当单选的数据返回是的调用方法
         */
        void onSingleDataSelectCallback();

        /**
         * 当保存按钮的点击事件
         */
        void onClickSave();

        /**
         * 当城市选择完成后的回调
         */
        void onCitySelectOverCallback();

        /**
         * 选择济南时增加商务局
         */
        void onSelectJinan();

        /**
         * 不选择济南时取消商务局
         */
        void onUnSelectJinan();
    }
}
