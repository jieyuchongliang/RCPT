package com.rcpt.ui.me.company;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.view.menu.MenuItemImpl;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.DatePicker;
import android.widget.PopupWindow;

import com.rcpt.Constant;
import com.rcpt.LoginHelper;
import com.rcpt.R;
import com.rcpt.adapter.PersonInfoAdapter;
import com.rcpt.base.ui.BaseActivity;
import com.rcpt.bean.ChooseIndustryBean;
import com.rcpt.bean.ChooseProvinceBean;
import com.rcpt.bean.InputMenuBean;
import com.rcpt.databinding.ActivityCompanyInfoBinding;
import com.rcpt.mvp.contract.CompanyInfoContract;
import com.rcpt.mvp.presenter.CompanyInfoPresenter;
import com.rcpt.recycler_listener.OnRecyclerItemClickListener;
import com.rcpt.ui.register.group.ChooseIndustryActivity;
import com.rcpt.ui.register.group.ChooseProvinceActivity;
import com.rcpt.utils.DateUtils;
import com.rcpt.utils.DialogUtils;
import com.rcpt.utils.MenuBuildUtils;
import com.rcpt.utils.SpaceItemDecoration;
import com.rcpt.widget.InputDataPopWindow;
import com.rcpt.widget.InputSingleDataPopWindow;
import com.rcpt.widget.ListSelectPopWindow;
import com.rcpt.widget.SimpleListSelectPopWindow;
import com.rcpt.widget.SinglePictureSelectPopWindow;
import com.rcpt.widget.SingleSelectDialog;
import com.rcpt.widget.TextTitlePopWindow;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class CompanyInfoActivity extends BaseActivity<ActivityCompanyInfoBinding, CompanyInfoContract.View, CompanyInfoPresenter>
        implements CompanyInfoContract.View, TextTitlePopWindow.OnClickSaveCallback, DialogInterface.OnClickListener,
        ListSelectPopWindow.OnListSelectDataCallback, SingleSelectDialog.OnClickSelectCallback, DatePickerDialog.OnDateSetListener {

    private static final int REQUEST_CODE_INDUSTRY_SELECT = 0x000011;
    private static final int REQUEST_CODE_CHOOSE_PROVINCE = 0x000012;

    private PersonInfoAdapter mAdapter;

    private DatePickerDialog mDateDialog;
    private InputSingleDataPopWindow mInputSinglePopWindow;
    private InputDataPopWindow mInputPopWindow;
    private SingleSelectDialog mSingSelectDialog;

    private Calendar mCalendar;
    private OnItemClickListener mItemClickListener;//点击事件的对象
    private int mItemClickPosition;//点击的具体条目的Position
    private String mInputData;//输入框输入的内容
    private String mDateSelect;//存储时间选择器选择后的的数据

    private SinglePictureSelectPopWindow mPictureSelectPopWindow;
    private String mCompanyLogo;//公司Logo

    private ArrayList<ChooseIndustryBean> mSelectIndustryList;

    private ListSelectPopWindow<String> mListSelectPop;
    private int mListSelectClickPosition;
    private String mUpdateValue;
    private boolean isChange;

    private String mAddressProvinceId;
    private String mAddressCityId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void finish() {
        if (isChange) {
            DialogUtils.builderAlertDialog(mContext, "温馨提示", "您的数据有更改，是否立即保存")
                    .setNegativeButton("暂不保存", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            isChange = false;
                            finish();
                        }
                    })
                    .setPositiveButton("立即保存", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            mPresenter.onClickSave();
                        }
                    }).show();
        } else
            super.finish();
    }

    /**
     * 未实现的方法
     */
    @Override
    protected void setupTitle() {
        if (LoginHelper.getInstance().getUserBean().getUserType().equals(Constant.UserType.ENTERPRISE.getValue())){
            setTitleText("企业信息");
        }else {
            setTitleText("机构信息");
        }
        openTitleLeftView(true);
        setTitleRightText("保存");
        getTitleRightView().setVisibility(View.GONE);
        getTitleRightView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.onClickSave();
            }
        });
    }

    @Override
    protected void initViews() {
        mCalendar = Calendar.getInstance();
        mPresenter.initMenu();
        mPresenter.loadListData();
        initPopWindow();
        initDialog();
    }

    /**
     * 初始化时间选择的Dialog
     */
    private void initDialog() {
        mSingSelectDialog = new SingleSelectDialog(this);
        mSingSelectDialog.setTitleText("企业是否上市");
        mSingSelectDialog.setSelectData("是", "否");
        mSingSelectDialog.setOnClickSelectCallback(this);
    }

    /**
     * 初始化PopWindow对象
     */
    private void initPopWindow() {
        mPictureSelectPopWindow = new SinglePictureSelectPopWindow(this);
        mPictureSelectPopWindow.createHelper();
        mPictureSelectPopWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                lightOn();
            }
        });
        mInputPopWindow = new InputDataPopWindow(this);
        mListSelectPop = new SimpleListSelectPopWindow(this);
        mInputSinglePopWindow = new InputSingleDataPopWindow(this);
        mInputPopWindow.setOnClickSaveCallback(this);
        mInputSinglePopWindow.setOnClickSaveCallback(this);
        mListSelectPop.setOnListSelectCallback(this);
    }

    @Override
    public void initRecyclerView() {
        mItemClickListener = new OnItemClickListener(getRecyclerView());
        mAdapter = new PersonInfoAdapter();
        mDataBinding.recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mDataBinding.recyclerView.addOnItemTouchListener(mItemClickListener);
        mDataBinding.recyclerView.setAdapter(mAdapter);
    }

    @Override
    public void bindListData(List<InputMenuBean> beanList) {
        mDataBinding.recyclerView.addItemDecoration(new SpaceItemDecoration(mContext, beanList));
        mAdapter.setupData(beanList);
    }

    @Override
    public List<MenuItemImpl> getMenuList() {
        return MenuBuildUtils.buildMenuItemList(mContext, getMenuRes());

    }

    /**
     * 通过id获取对应的MenuItem的实例
     *
     * @param idRes
     * @return
     */
    @Override
    public MenuItemImpl getMenuItemForId(int idRes) {
        return MenuBuildUtils.getMenuItemForId(mContext, getMenuRes(), idRes);
    }

    @Override
    public void insertSingleItem(int position) {
        mAdapter.notifyItemInserted(position);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void removeSingleItem(int position) {
        mAdapter.notifyItemRemoved(position);
        mAdapter.notifyDataSetChanged();
    }

    private int getMenuRes() {
        int menuRes;
        String userType = LoginHelper.getInstance().getUserBean().getUserType();
        if (Constant.UserType.ENTERPRISE.getValue().equals(userType))
            menuRes = R.menu.company_info_menu;
        else
            menuRes = R.menu.institution_info_menu;
        return menuRes;
    }

    @Override
    public RecyclerView getRecyclerView() {
        return mDataBinding.recyclerView;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_company_info;
    }

    @Override
    protected CompanyInfoPresenter createPresenter() {
        return new CompanyInfoPresenter();
    }

    @Override
    public void onClick(@NonNull DialogInterface dialog, int which) {
        switch (which) {
            case DialogInterface.BUTTON_POSITIVE:

                DatePicker datePicker = mDateDialog.getDatePicker();
                mDateSelect = datePicker.getYear() + "-" + (datePicker.getMonth() + 1) + "-" + datePicker.getDayOfMonth();
                mPresenter.onDateSelectCallback();
                break;
            case DialogInterface.BUTTON_NEGATIVE:
                mDateDialog.cancel();
                break;
        }
    }

    /**
     * @param view       the picker associated with the dialog
     * @param year       the selected year
     * @param month      the selected month (0-11 for compatibility with
     *                   {@link Calendar#MONTH})
     * @param dayOfMonth th selected day of the month (1-31, depending on
     */
    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

    }

    /**
     * 输入框中的数据点击保存时的回调
     *
     * @param content 输入的具体内容
     */
    @Override
    public void onSaveCallback(String content) {
        mInputData = content;
        mPresenter.onInputPopClickSave();
    }

    /**
     * 显示单行输入的PopWindow
     *
     * @param inputData
     */
    @Override
    public void showInputSinglePop(String inputData) {
        mInputSinglePopWindow.showPopupWindow(inputData, mDataBinding.getRoot());
    }

    /**
     * 显示单行输入的PopWindow并设置好输入的类型
     *
     * @param inputData
     * @param inputType
     */
    @Override
    public void showInputSinglePopWithType(String inputData, @InputDataPopWindow.InputType int inputType) {
        mInputSinglePopWindow.showPopupWindow(inputData, inputType, mDataBinding.getRoot());
    }

    /**
     * 开启图片选择的PopWindow
     */
    @Override
    public void showPictureSelectPop() {
        lightOff();
        mPictureSelectPopWindow.showPopupWindow(mDataBinding.getRoot());
    }

    /**
     * 显示多行输入的PopWindow
     *
     * @param inputData
     */
    @Override
    public void showInputPop(String inputData) {
        mInputPopWindow.showPopupWindow(inputData, mDataBinding.getRoot());
    }

    /**
     * 显示时间选择器
     */
    @Override
    public void showDateDialog(String time) {
        mCalendar.setTime(new Date(DateUtils.stringToDate(time)));
        mDateDialog = new DatePickerDialog(mContext, this, mCalendar.get(Calendar.YEAR), mCalendar.get(Calendar.MONTH), mCalendar.get(Calendar.DAY_OF_MONTH));
        mDateDialog.setButton(DialogInterface.BUTTON_POSITIVE, "确认", this);
        mDateDialog.setButton(DialogInterface.BUTTON_NEGATIVE, "取消", this);
        mDateDialog.show();
    }

    @Override
    public String getInputData() {
        return mInputData;
    }

    /**
     * 获取点击条目的Position
     *
     * @return
     */
    public int getClickItemPosition() {
        return mItemClickPosition;
    }

    /**
     * 获取时间选择器选择后的数据
     *
     * @return
     */
    @Override
    public String getDateSelectData() {
        return mDateSelect;
    }

    /**
     * 获取公司Logo的Url
     *
     * @return
     */
    @Override
    public String getCompanyLogoUrl() {
        return mCompanyLogo;
    }

    /**
     * 更新单行显示的数据内容
     *
     * @param position
     * @param value
     */
    @Override
    public void updateSingleItem(int position, String value) {
        isChange = mAdapter.updateSingleItem(position, value);
        if (isChange && getTitleRightView().getVisibility() == View.GONE) {
            getTitleRightView().setVisibility(View.VISIBLE);
        }
    }

    /**
     * 设置输入框PopWindow的标题
     *
     * @param titleName
     */
    @Override
    public void setInputDataTitle(String titleName) {
        mInputPopWindow.setTitleText(titleName);
    }

    /**
     * 设置单行输入框的标题
     *
     * @param titleName
     */
    @Override
    public void setInputSingleDataTitle(String titleName) {
        mInputSinglePopWindow.setTitleText(titleName);
    }

    /**
     * 初始化已选择的行业类别
     *
     * @param beans
     */
    @Override
    public void initIndustrySelectList(ChooseIndustryBean... beans) {
        mSelectIndustryList = new ArrayList<>(Arrays.asList(beans));
    }

    /**
     * 开始进入到行业列表选择列表
     */
    @Override
    public void startGoIndustry() {
        ChooseIndustryActivity.actionStart(this, mSelectIndustryList, ChooseIndustryActivity.SELECT_MODE_INFINITE, REQUEST_CODE_INDUSTRY_SELECT);
    }

    /**
     * 显示学历列表筛选的PopWindow
     */
    @Override
    public void showListSelectPop() {
        mListSelectPop.showPopupWindow(mDataBinding.getRoot());
    }

    /**
     * 限定popupwindow的输入字符数量
     * @param max
     */
    @Override
    public void setInputDataMax(int max,boolean isSingle) {
        if (isSingle){
            mInputSinglePopWindow.setCheckInputMaxLength(max);
        }else
        mInputPopWindow.setCheckInputMaxLength(max);
    }

    @Override
    public void updateAddressId(String provinceId, String cityId) {
        mAddressProvinceId = provinceId;
        mAddressCityId = cityId;
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

    @Override
    public void onSelectCallback(String data, int position) {
        mListSelectClickPosition = position;
        mPresenter.onListSelectItemClick();
    }

    /**
     * 获取列表选择的点击条目
     *
     * @return
     */
    @Override
    public int getListSelectClickPosition() {
        return mListSelectClickPosition;
    }


    /**
     * 获取需要跟新的数据
     *
     * @return
     */
    @Override
    public String getUpdateValue() {
        return mUpdateValue;
    }

    /**
     * 显示是否上市的选择Dialog
     */
    @Override
    public void showSingleSelectDialog(String selectName) {
        mSingSelectDialog.setSelectItemName(selectName);
        mSingSelectDialog.show();
    }

    @Override
    public void onDataCallback(String data) {
        mInputData = data;
        mUpdateValue = data.equals("是") ? "0" : "1";
        mPresenter.onSingleDataSelectCallback();
    }

    /**
     * 开启城市选择的Activity
     */
    @Override
    public void startGoCitySelect() {
        ChooseProvinceActivity.actionStart(this, false,false, mAddressProvinceId, mAddressCityId, REQUEST_CODE_CHOOSE_PROVINCE);
    }

    /**
     * 改变修改的状态
     *
     * @param isChange
     */
    @Override
    public void changeSaveState(boolean isChange) {
        this.isChange = isChange;
        if (isChange) {
            getTitleRightView().setVisibility(View.VISIBLE);
        } else {
            getTitleRightView().setVisibility(View.GONE);
        }
    }

    /**
     * 获取省id
     *
     * @return
     */
    @Override
    public String getAddressProvinceId() {
        return mAddressProvinceId;
    }

    /**
     * 获取市id
     *
     * @return
     */
    @Override
    public String getAddressCityId() {
        return mAddressCityId;
    }


    /**
     * 判断当前选择的城市是否是济南市
     *
     * @param provinceId
     * @param cityId
     * @return
     */
    @Override
    public boolean isJinan(String provinceId, String cityId) {
        return Constant.SHANDONG_PROVINCE_CODE.equals(provinceId) && Constant.JINAN_CITY_CODE.equals(cityId);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case REQUEST_CODE_INDUSTRY_SELECT:
                    if (data != null) {
                        String industryTxt = "";
                        String industryId = "";
                        mSelectIndustryList = data.getParcelableArrayListExtra("result_data");
                        for (int i = 0; i < mSelectIndustryList.size(); i++) {
                            industryTxt += mSelectIndustryList.get(i).getValue() + "、";
                            industryId += mSelectIndustryList.get(i).getId() + ",";
                        }
                        mInputData = industryTxt.substring(0, industryTxt.length() - 1);
                        mUpdateValue = industryId.substring(0, industryId.length() - 1);
                    }
                    mPresenter.onSingleDataSelectCallback();
                    break;
                case REQUEST_CODE_CHOOSE_PROVINCE:
                    if (data != null) {
                        ChooseProvinceBean provinceBean = data.getParcelableExtra("provinceBean");
                        ChooseProvinceBean cityBean = data.getParcelableExtra("cityBean");
                        String provinceId = provinceBean.getRegionId();
                        String cityId = cityBean.getRegionId();
                        //判断当前选择的城市，是否是已经做选择过的，如果是则直接退出
                        if (provinceId.equals(mAddressProvinceId) && cityId.equals(mAddressCityId))
                            return;
                        mAddressProvinceId = provinceId;
                        mAddressCityId = cityId;


                        String addressTxt = provinceBean.getRegionName();
                        addressTxt += "-";
                        addressTxt += cityBean.getRegionName();

                        mInputData = addressTxt;

                        if (isJinan(provinceBean.getRegionId(), cityBean.getRegionId())) {
                            mPresenter.onSelectJinan();
                        } else {
                            mPresenter.onUnSelectJinan();
                        }

                        mPresenter.onInputPopClickSave();
                    }
                    break;
                default:
                    mCompanyLogo = mPictureSelectPopWindow.getSelectHelper().onActivityResult(requestCode, resultCode, data, null, false);
                    mPresenter.onPictureSelectCallback();
                    break;
            }
        }
    }

    private class OnItemClickListener extends OnRecyclerItemClickListener {

        public OnItemClickListener(RecyclerView recyclerView) {
            super(recyclerView);
        }

        @Override
        public void onItemClick(RecyclerView.ViewHolder vh) {
            mItemClickPosition = vh.getLayoutPosition();
            mPresenter.onItemClick();
        }
    }


}
