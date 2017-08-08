package com.rcpt.mvp.contract;

import android.os.Parcelable;

import com.rcpt.base.mvp.BaseView;
import com.rcpt.base.mvp.MenuLoadContract;
import com.rcpt.base.mvp.RecyclerViewContract;
import com.rcpt.bean.ChooseIndustryBean;
import com.rcpt.bean.InputMenuBean;
import com.rcpt.bean.JobCategoryBean;

import java.util.List;

/**
 * Created by lvzp on 2017/3/9.
 * 类描述：
 */

public interface CreateCVInfoFragmentContract {

    interface View extends BaseView, MenuLoadContract.View, RecyclerViewContract.View<InputMenuBean> {

        String getCreateType();

        void updateList(int position);

        void showDateSelectDialog(long currentTime);

        /**
         * 设置职位类别的标题
         *
         * @param titleText
         */
        void setJobCategoryListPopTitle(String titleText);

        /**
         * 弹出职位类别的选择框
         */
        void showJobCategoryListPop();

        /**
         * 关闭弹出的职位类别
         */
        void closeJobIntentPop();

        /**
         * 绑定职位类别的数据
         *
         * @param list
         */
        void bindJobCategoryListPopData(List<JobCategoryBean> list,String selectItemName);

        /**
         * 进入到行业类别列表（单选）
         */
        void startGoChooseIndustrySingle();

        void startGoChooseIndustry();

        /**
         * 绑定List列表显示的数据
         *
         * @param listData
         */
        void bindAttrListData(List<String> listData,String selectItemName);

        /**
         * 显示单行内容输入的PopWindow
         */
        void showInputSingleDataPop(String inputValue);

        /**
         * 显示多行内容输入的PopWindow
         */
        void showInputDataPop(String inputValue);

        /**
         * 显示学历列表筛选的PopWindow
         */
        void showListSelectPop();

        /**
         * 设置列表选择的标题
         *
         * @param titleText
         */
        void setListSelectPopTitle(String titleText);

        /**
         * 设置内容输入的PopWindow标题
         *
         * @param titleText
         */
        void setSingleInputPopTitle(String titleText);

        /**
         * 设置内容输入的标题
         *
         * @param titleText
         */
        void setInputPopTitle(String titleText);

        String getCVId();

        void uploadInfoSuccess();


        void showSingleSelectDialog(String selectName);

        /**
         * 进入到选择地址的界面
         */
        void startGoSelectAddress();

        /**
         * 获取省id
         *
         * @return
         */
        String getProvinceId();

        /**
         * 获取市id
         *
         * @return
         */
        String getCityId();

        /**
         * 获取条目的Id
         *
         * @return
         */
        Parcelable getEditBean();

        /**
         * 是否是修改状态
         *
         * @return
         */
        boolean isEdit();

        List<JobCategoryBean.ChildBean> getJobIntentSelectList();

        /**
         * 初始化行业类别的已选行业
         * @param beens
         */
        void initIndustrySelectList(ChooseIndustryBean[] beens);

        /**
         * 获取是否为创建新的简历
         *
         * @return
         */
        boolean isCreateNewCV();

        /**
         * 获取简历名称
         *
         * @return
         */
        String getCVName();

        void updateAddressId(String provinceId, String cityId);

        /**
         * 设置输入的内容最大值
         */
        void setInputDataMax(int max,boolean isSingle);
    }

    interface Presenter extends MenuLoadContract.Presenter, RecyclerViewContract.Presenter {
        void updateListItemValue(String value);

        void uploadCrateCVInfo();

        void updateListItemValue(String value, int position);

        void updateListItemValue(String value, String add);

        /**
         * 职位类别选择保存的回调方法
         */
        void jobIntentSelectClickSave();
    }


}
