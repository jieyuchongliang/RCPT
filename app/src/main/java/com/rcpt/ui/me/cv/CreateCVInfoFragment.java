package com.rcpt.ui.me.cv;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.view.menu.MenuItemImpl;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rcpt.BR;
import com.rcpt.Constant;
import com.rcpt.DateSelectDialog;
import com.rcpt.R;
import com.rcpt.base.adapter.SimpleBindingAdapter;
import com.rcpt.base.ui.BaseFragment;
import com.rcpt.bean.ChooseIndustryBean;
import com.rcpt.bean.ChooseProvinceBean;
import com.rcpt.bean.InputMenuBean;
import com.rcpt.bean.JobCategoryBean;
import com.rcpt.databinding.FragmentCreateCvinfoBinding;
import com.rcpt.mvp.contract.CreateCVInfoFragmentContract;
import com.rcpt.mvp.presenter.CreateCVInfoFragmentPresenter;
import com.rcpt.recycler_listener.OnRecyclerItemClickListener;
import com.rcpt.ui.register.group.ChooseIndustryActivity;
import com.rcpt.ui.register.group.ChooseProvinceActivity;
import com.rcpt.utils.MenuBuildUtils;
import com.rcpt.utils.SpaceItemDecoration;
import com.rcpt.widget.InputDataPopWindow;
import com.rcpt.widget.InputSingleDataPopWindow;
import com.rcpt.widget.SimpleListSelectPopWindow;
import com.rcpt.widget.SingleSelectDialog;
import com.rcpt.widget.TextTitlePopWindow;
import com.rcpt.widget.TitleListSelectPopWindow;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 创建简历信息的页面
 * 当前的页面可以创建和修改以下内容
 * 1.教育背景
 * 2.工作经历
 * 3.项目经验
 * 4.求职意向
 */
public class CreateCVInfoFragment extends BaseFragment<FragmentCreateCvinfoBinding, CreateCVInfoFragmentContract.View, CreateCVInfoFragmentPresenter>
        implements CreateCVInfoFragmentContract.View {

    private static final int REQUEST_CODE_CHOOSE_INDUSTRY_SINGLE = 0x0000123;
    private static final int REQUEST_CODE_CHOOSE_INDUSTRY = 0x0000124;
    private static final int REQUEST_CODE_SELECT_ADDRESS = 0x00000134;

    private static final String CREATE_CV_TYPE = "type";
    private static final String CV_ID_TAG = "id_tag";
    private static final String CV_IS_EDIT = "is_edit";
    private static final String CV_EDIT_BEAN_TAG = "edit_bean_tag";
    private static final String CV_IS_CREATE = "is_create";
    private static final String CV_NAME = "cv_name";

    private SimpleBindingAdapter<InputMenuBean> mAdapter;

    private String mCreateCVType;
    private String mCVId;

    private DateSelectDialog mDatePickerDialog;

    private OnItemClickListener mOnItemClickListener;

    private OnUpdateCVInfoCallback mCallback;

    private InputDataPopWindow mInputDataPop;
    private InputSingleDataPopWindow mInputSingleDataPop;
    private SimpleListSelectPopWindow mEducationListSelectPopWindow;

    private TitleListSelectPopWindow mTileListSelectPopwindow;

    private SingleSelectDialog mSingleDialog;
    private String mProvinceId;
    private String mCityId;
    private String mIndustryId;
    private ArrayList<ChooseIndustryBean> mSelectBeanList;

    private boolean isEdit;

    public static CreateCVInfoFragment newInstance(String createCVType, String cvId, boolean isCreate, String cvName) {
        CreateCVInfoFragment fragment = new CreateCVInfoFragment();
        Bundle bundle = new Bundle();
        bundle.putString(CREATE_CV_TYPE, createCVType);
        bundle.putString(CV_ID_TAG, cvId);
        bundle.putBoolean(CV_IS_CREATE, isCreate);
        bundle.putString(CV_NAME, cvName);
        fragment.setArguments(bundle);
        return fragment;
    }

    public static CreateCVInfoFragment newInstance(String createCVType, String cvId, boolean isEdit, Parcelable bean) {
        CreateCVInfoFragment fragment = new CreateCVInfoFragment();
        Bundle bundle = new Bundle();
        bundle.putString(CREATE_CV_TYPE, createCVType);
        bundle.putString(CV_ID_TAG, cvId);
        bundle.putBoolean(CV_IS_EDIT, isEdit);
        bundle.putParcelable(CV_EDIT_BEAN_TAG, bean);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnUpdateCVInfoCallback)
            mCallback = (OnUpdateCVInfoCallback) context;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            Bundle bundle = getArguments();
            mCreateCVType = bundle.getString(CREATE_CV_TYPE);
            mCVId = bundle.getString(CV_ID_TAG);
            isEdit = bundle.getBoolean(CV_IS_EDIT, false);
        }
    }


    public void uploadCreateCV() {
        mPresenter.uploadCrateCVInfo();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public String getCreateType() {
        return mCreateCVType;
    }

    /**
     * 是否是修改状态
     *
     * @return
     */
    @Override
    public boolean isEdit() {
        return isEdit;
    }

    /**
     * 获取是否为创建新的简历
     *
     * @return
     */
    @Override
    public boolean isCreateNewCV() {
        return getArguments().getBoolean(CV_IS_CREATE);
    }

    /**
     * 获取简历名称
     *
     * @return
     */
    @Override
    public String getCVName() {
        return getArguments().getString(CV_NAME);
    }

    @Override
    public void updateAddressId(String provinceId, String cityId) {
        mProvinceId = provinceId;
        mCityId = cityId;
    }

    /**
     * 设置输入的内容最大值
     */
    @Override
    public void setInputDataMax(int max,boolean isSingle) {
        if (isSingle){
            mInputSingleDataPop.setCheckInputMaxLength(max);
        }else
        mInputDataPop.setCheckInputMaxLength(max);
    }

    /**
     * 获取条目的Id
     *
     * @return
     */
    @Override
    public Parcelable getEditBean() {
        return getArguments().getParcelable(CV_EDIT_BEAN_TAG);
    }

    @Override
    public List<MenuItemImpl> getMenuList() {
        switch (mCreateCVType) {
            case Constant.CREATE_CV_EDUCATION:
                return MenuBuildUtils.buildMenuItemList(mContext, R.menu.create_education_menu);
            case Constant.CREATE_CV_JOB_INTENT:
                return MenuBuildUtils.buildMenuItemList(mContext, R.menu.create_job_intent_menu);
            case Constant.CREATE_CV_PROJECT_EXPERIENCE:
                return MenuBuildUtils.buildMenuItemList(mContext, R.menu.create_project_experience_menu);
            case Constant.CREATE_CV_WORK_HISTORY:
                return MenuBuildUtils.buildMenuItemList(mContext, R.menu.create_work_history_menu);
        }
        return null;
    }

    @Override
    public void initRecyclerView() {
        mDataBinding.recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mOnItemClickListener = new OnItemClickListener(mDataBinding.recyclerView);
        mDataBinding.recyclerView.addOnItemTouchListener(mOnItemClickListener);
        mAdapter = new SimpleBindingAdapter<>(R.layout.item_layout_person_info_menu, BR.menuBean);
        mDataBinding.recyclerView.setAdapter(mAdapter);
    }

    @Override
    public String getCVId() {
        return mCVId;
    }


    /**
     * 显示单行内容输入的PopWindow
     */
    @Override
    public void showInputSingleDataPop(String inputValue) {
        mInputSingleDataPop.showPopupWindow(inputValue, mDataBinding.getRoot());
    }

    /**
     * 显示多行内容输入的PopWindow
     */
    @Override
    public void showInputDataPop(String inputValue) {
        mInputDataPop.showPopupWindow(inputValue, mDataBinding.getRoot());
    }

    /**
     * 设置内容输入的PopWindow标题
     *
     * @param titleText
     */
    @Override
    public void setSingleInputPopTitle(String titleText) {
        mInputSingleDataPop.setTitleText(titleText);
    }

    /**
     * 设置内容输入的标题
     *
     * @param titleText
     */
    @Override
    public void setInputPopTitle(String titleText) {
        mInputDataPop.setTitleText(titleText);
    }

    /**
     * 设置学历列表的标题
     *
     * @param titleText
     */
    @Override
    public void setListSelectPopTitle(String titleText) {
        mEducationListSelectPopWindow.setTitleText(titleText);
    }


    @Override
    public void bindListData(List<InputMenuBean> beanList) {
        mDataBinding.recyclerView.addItemDecoration(new SpaceItemDecoration(mContext, beanList));
        mAdapter.setupData(beanList);
    }

    @Override
    public void updateList(int position) {
        mAdapter.notifyItemChanged(position);
    }


    @Override
    public void uploadInfoSuccess() {
        mCallback.onSucceed();
    }

    @Override
    public void showDateSelectDialog(long currentTime) {
        mDatePickerDialog.setCurrentTime(currentTime);
        mDatePickerDialog.show();
    }

    @Override
    public RecyclerView getRecyclerView() {
        return mDataBinding.recyclerView;
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_create_cvinfo;
    }

    @Override
    protected void initViews() {

        initJobIntentPop();

        mInputDataPop = new InputDataPopWindow(getActivity());
        mInputSingleDataPop = new InputSingleDataPopWindow(getActivity());

        mEducationListSelectPopWindow = new SimpleListSelectPopWindow(getActivity());
        mDatePickerDialog = new DateSelectDialog(mContext).setOutnumberCurrentDate(false);

        mSingleDialog = new SingleSelectDialog(mContext);
        mSingleDialog.setSelectData("有", "无");
        mSingleDialog.setTitleText("海外经历选择");
        mSingleDialog.setOnClickSelectCallback(new SingleSelectDialog.OnClickSelectCallback() {
            @Override
            public void onDataCallback(String data) {
                mPresenter.updateListItemValue(data, data.equals("有") ? "1" : "0");
            }
        });

        mDatePickerDialog.setOnClickListener(mPresenter);
        mInputDataPop.setOnClickSaveCallback(mPresenter);
        mInputSingleDataPop.setOnClickSaveCallback(mPresenter);
        mEducationListSelectPopWindow.setOnListSelectCallback(new SimpleListSelectPopWindow.OnListSelectDataCallback() {
            @Override
            public void onSelectCallback(String data, int position) {
                mPresenter.updateListItemValue(data, position);
            }
        });

        if (isEdit) {
            mPresenter.loadListData();
        } else {
            mPresenter.initMenu();
        }
    }

    /**
     * 初始化求职意向的PopWindow
     */
    private void initJobIntentPop() {
        mTileListSelectPopwindow = new TitleListSelectPopWindow(getActivity());
        mTileListSelectPopwindow.setSelectMode(TitleListSelectPopWindow.SELECT_MODE_SINGLE, 1);
        mTileListSelectPopwindow.setOnClickSaveCallback(new TextTitlePopWindow.OnClickSaveCallback() {
            @Override
            public void onSaveCallback(String content) {
                mPresenter.jobIntentSelectClickSave();
            }
        });
    }


    public void closeJobIntentPop() {
        mTileListSelectPopwindow.dismiss();
    }

    /**
     * 绑定学历列表数据
     *
     * @param listData
     */
    @Override
    public void bindAttrListData(List<String> listData, String selectItemName) {
        mEducationListSelectPopWindow.setSelectItemName(selectItemName);
        mEducationListSelectPopWindow.bindListData(listData);
    }

    @Override
    public void showSingleSelectDialog(String selectName) {
        mSingleDialog.setSelectItemName(selectName);
        mSingleDialog.show();
    }

    /**
     * 显示学历列表筛选的PopWindow
     */
    @Override
    public void showListSelectPop() {
        mEducationListSelectPopWindow.showPopupWindow(mDataBinding.getRoot());
    }

    /**
     * 初始化行业类别的已选行业
     *
     * @param beens
     */
    @Override
    public void initIndustrySelectList(ChooseIndustryBean[] beens) {
        mSelectBeanList = new ArrayList<>(Arrays.asList(beens));
    }

    @Override
    public void startGoChooseIndustry() {
        ChooseIndustryActivity.actionStart(this, mSelectBeanList, ChooseIndustryActivity.SELECT_MODE_MORE, REQUEST_CODE_CHOOSE_INDUSTRY);
    }

    @Override
    public void startGoChooseIndustrySingle() {
        ChooseIndustryActivity.actionStart(this, mSelectBeanList, true, REQUEST_CODE_CHOOSE_INDUSTRY_SINGLE);
    }

    @Override
    protected CreateCVInfoFragmentPresenter createPresenter() {
        return new CreateCVInfoFragmentPresenter();
    }

    /**
     * 进入到选择地址的界面
     */
    @Override
    public void startGoSelectAddress() {
        ChooseProvinceActivity.actionStart(this, false,mProvinceId, mCityId, REQUEST_CODE_SELECT_ADDRESS);
    }

    /**
     * 获取省id
     *
     * @return
     */
    @Override
    public String getProvinceId() {
        return mProvinceId;
    }

    /**
     * 获取市id
     *
     * @return
     */
    @Override
    public String getCityId() {
        return mCityId;
    }

    /**
     * 设置职位类别的标题
     *
     * @param titleText
     */
    @Override
    public void setJobCategoryListPopTitle(String titleText) {
        mTileListSelectPopwindow.setTitleText(titleText);
    }

    /**
     * 弹出职位类别的选择框
     */
    @Override
    public void showJobCategoryListPop() {
        mTileListSelectPopwindow.showPopupWindow(mDataBinding.getRoot());
    }

    /**
     * 绑定职位类别的数据
     *
     * @param list
     */
    @Override
    public void bindJobCategoryListPopData(List<JobCategoryBean> list, String selectItemName) {
        mTileListSelectPopwindow.setSelectItemName(selectItemName);
        mTileListSelectPopwindow.bindListData(list);
    }

    @Override
    public List<JobCategoryBean.ChildBean> getJobIntentSelectList() {
        return mTileListSelectPopwindow.getSelectedJobCategoryList();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            switch (requestCode) {
                case REQUEST_CODE_CHOOSE_INDUSTRY_SINGLE:
                    if (data != null && data.hasExtra("result_data")) {
                        ChooseIndustryBean bean = data.getParcelableExtra("result_data");
                        mPresenter.updateListItemValue(bean.getValue(), bean.getId());
                    }
                    break;
                case REQUEST_CODE_CHOOSE_INDUSTRY://期望行业
                    if (data != null) {
                        String industryTxt = "";
                        mIndustryId = "";
                        mSelectBeanList = data.getParcelableArrayListExtra("result_data");
                        for (int i = 0; i < mSelectBeanList.size(); i++) {
                            industryTxt += mSelectBeanList.get(i).getValue() + "、";
                            mIndustryId += mSelectBeanList.get(i).getId() + ",";
                        }
                        industryTxt = industryTxt.substring(0, industryTxt.length() - 1);
                        mIndustryId = mIndustryId.substring(0, mIndustryId.length() - 1);
                        mPresenter.updateListItemValue(industryTxt, mIndustryId);
                    }
                    break;
                case REQUEST_CODE_SELECT_ADDRESS://期望工作地点选择
                    if (data != null) {
                        ChooseProvinceBean provinceBean = data.getParcelableExtra("provinceBean");
                        ChooseProvinceBean cityBean = data.getParcelableExtra("cityBean");
                        String addressTxt = provinceBean.getRegionName();
                        addressTxt += "-";
                        addressTxt += cityBean.getRegionName();
                        mProvinceId = provinceBean.getRegionId();
                        mCityId = cityBean.getRegionId();
                        mPresenter.updateListItemValue(addressTxt);
                    }
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
            mPresenter.onItemClick(vh, vh.getLayoutPosition());
        }
    }


    public interface OnUpdateCVInfoCallback {

        void onSucceed();
    }
}
