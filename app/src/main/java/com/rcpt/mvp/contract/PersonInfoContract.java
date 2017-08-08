package com.rcpt.mvp.contract;

import com.rcpt.base.mvp.BaseView;
import com.rcpt.base.mvp.MenuLoadContract;
import com.rcpt.base.mvp.RecyclerViewContract;
import com.rcpt.bean.InputMenuBean;

import java.util.List;

/**
 * Created by lvzp on 2017/3/2.
 * 类描述：
 */

public interface PersonInfoContract {

    interface View extends BaseView, RecyclerViewContract.View<InputMenuBean>, MenuLoadContract.View {

        /**
         * 修改单行数据
         *
         * @param position
         * @param value
         */
        void updateSingleItem(int position, String value);

        /**
         * 设置PopWindow中提示的文字
         *
         * @param inputHintText
         */
        void setPopInputHintText(String inputHintText);

        /**
         * 设置PopWindow输入框中的输入类型
         *
         * @param inputType
         */
        void setPopInputType(int inputType);

        /**
         * 设置输入的内容最大值
         */
        void setInputDataMax(int max);
        /**
         * 设置PopWindow中的标题
         *
         * @param titleText
         */
        void setPopTitleText(String titleText);

        /**
         * 打开选择图片的PopWindow
         */
        void openSelectPhotoPop();

        /**
         * 打开单行输入的PopWindow
         */
        void openInputSinglePop(String value);

        /**
         * 显示性别选择的提示框
         */
        void showSexSelectDialog(String selectName);

        /**
         * 显示时间选择器的提示框
         */
        void showDateSelectDialog(long date);

        /**
         * 显示是否有过海外工作经历Dialog
         */
        void showOverseasDialog(String selectName);

        /**
         * 初始化婚姻状态的PopWindow
         */
        void initMaritalSelectPop();

        /**
         * 显示婚姻状态的PopWindow
         */
        void showSelectPop(List<String> dataSource, String selectItemName);

        /**
         * 初始化学历选择的PopWindow
         */
        void initEducationSelectPop();

        /**
         * 初始化块右侧的控件
         */
        void initRightView();

        void changeSaveState(boolean isChange);
    }

    interface Presenter extends RecyclerViewContract.Presenter, MenuLoadContract.Presenter {

        void onClickSave();

        /**
         * 当输入的数据点击保存后回调的数据
         *
         * @param inputContent
         */
        void itemDataChange(String inputContent);

        void startUploadUserAvatar(String filePat);

        /**
         * 修改学历信息
         *
         * @param value
         * @param position
         */
        void updateEducationItem(String value, int position);
    }

}
