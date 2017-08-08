package com.rcpt.ui.system;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import com.rcpt.R;
import com.rcpt.base.ui.BaseActivity;
import com.rcpt.bean.ChooseProvinceBean;
import com.rcpt.bean.UserManageFilterBean;
import com.rcpt.databinding.ActivityUserManageFilterBinding;
import com.rcpt.mvp.contract.UserManageFilterContract;
import com.rcpt.mvp.presenter.UserManageFilterPresenter;
import com.rcpt.ui.register.group.ChooseProvinceActivity;

@SuppressLint("WrongConstant")
public class UserManageFilterActivity extends BaseActivity<ActivityUserManageFilterBinding, UserManageFilterContract.View, UserManageFilterPresenter>
        implements UserManageFilterContract.View, AdapterView.OnItemSelectedListener, View.OnClickListener, UserManageFilterBean.OnDataChangeListener {

    public static final String RESULT_BEAN = "bean";
    private static final String IS_GROUP = "type";
    private static final String FILTER_BEAN = "filter_bean";
    private static final int SELECT_ADDRESS_REQUEST_CODE = 0x00000132e;

    public static void actionStart(Fragment fragment, boolean isGroup, UserManageFilterBean filterBean, int requestCode) {
        Intent intent = new Intent(fragment.getContext(), UserManageFilterActivity.class);
        intent.putExtra(IS_GROUP, isGroup);
        intent.putExtra(FILTER_BEAN, filterBean);
        fragment.startActivityForResult(intent, requestCode);
    }

    private UserManageFilterBean mFilterBean;
    private boolean isGroup;
    private String mProvinceId;
    private String mCityId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    /**
     * 未实现的方法
     */
    @Override
    protected void setupTitle() {
        openTitleLeftView(true);
        setTitleRightText("搜索");
        CheckBox rightView = getTitleRightView();
        rightView.setTextSize(15);
        rightView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra(RESULT_BEAN, mFilterBean);
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }

    @Override
    protected void initFieldBeforeMethods() {
        Intent intent = getIntent();
        isGroup = intent.getBooleanExtra(IS_GROUP, false);
        mFilterBean = intent.getParcelableExtra(FILTER_BEAN);
    }

    @Override
    protected void initViews() {

        mPresenter.initSelectData();
        mDataBinding.setIsGroup(isGroup);

        mDataBinding.setPresenter(mPresenter);
        //由于前面会有对UserManageFilterBean初始化值的为空判断，进行的一些处理，所以在这里进行附加初始值
        if (mFilterBean == null) {
            mDataBinding.setIsNeedClean(false);
        } else {
            mDataBinding.setIsNeedClean(!getFilterBean().isEmpty());
            mProvinceId = mFilterBean.getProvinceId();
            mCityId = mFilterBean.getCityId();
        }
        mDataBinding.setFilterBean(getFilterBean());

        //根据不同的用户类型，加载不同的数据列表
        String[] userTypes;
        if (isGroup) {
            userTypes = getResources().getStringArray(R.array.group_user_status);
        } else {
            userTypes = getResources().getStringArray(R.array.person_user_status);
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                mContext, android.R.layout.simple_spinner_item, userTypes);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mDataBinding.spUserStatus.setAdapter(adapter);

        mDataBinding.spUserType.setOnItemSelectedListener(this);
        mDataBinding.spUserStatus.setOnItemSelectedListener(this);
        mPresenter.checkInitData();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_user_manage_filter;
    }

    @Override
    protected UserManageFilterPresenter createPresenter() {
        return new UserManageFilterPresenter();
    }


    /**
     * 开启地址选择
     */
    @Override
    public void startGoAddressSelect() {
        ChooseProvinceActivity.actionStart(this, false, true, mProvinceId, mCityId, SELECT_ADDRESS_REQUEST_CODE);
    }


    /**
     * 获取筛选条件的实体类
     *
     * @return
     */
    @Override
    public UserManageFilterBean getFilterBean() {
        if (mFilterBean == null) {
            mFilterBean = new UserManageFilterBean();
            mFilterBean.setOnDataChangeListener(this);
        }
        return mFilterBean;
    }

    /**
     * 更新用户状态选择的Position
     *
     * @param position
     */
    @Override
    public void setupUserStatusSelectPosition(int position) {
        mDataBinding.spUserStatus.setSelection(position, true);
    }

    /**
     * 更新用户类型选择的Position
     *
     * @param position
     */
    @Override
    public void setupUserTypeSelectPosition(int position) {
        mDataBinding.spUserType.setSelection(position, true);
    }

    /**
     * 是否为团体类型用户
     *
     * @return
     */
    @Override
    public boolean isGroup() {
        return isGroup;
    }

    /**
     * 获取用户类型的值
     *
     * @return
     */
    @Override
    public String getUserTypeValue() {
        return mDataBinding.tvUserType.getText().toString();
    }

    /**
     * 获取用户状态的值
     *
     * @return
     */
    @Override
    public String getUserStatusValue() {
        return mDataBinding.tvUserStatus.getText().toString();
    }

    /**
     * 更新筛选用户状态的id
     *
     * @param id
     */
    @Override
    public void updateUserStatusId(String id) {
        getFilterBean().setUserStatusId(id);
    }

    /**
     * 更新用户类型的状态id
     *
     * @param id
     */
    @Override
    public void updateUserTypeId(String id) {
        getFilterBean().setUserTypeId(id);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        switch (parent.getId()) {
            case R.id.sp_user_status:
                if (position == 0) {
                    mDataBinding.tvUserStatus.setText("");
                    getFilterBean().setUserStatusId("");
                } else {
                    mDataBinding.tvUserStatus.setText(((TextView) view).getText());
                    mPresenter.onUserStatusSelect();
                }
                break;
            case R.id.sp_user_type:
                if (position == 0) {
                    mDataBinding.tvUserType.setText("");
                    getFilterBean().setUserTypeId("");
                } else {
                    mDataBinding.tvUserType.setText(((TextView) view).getText());
                    mPresenter.onUserTypeStatus();
                }
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    /**
     * 当数据发生改变时的回调
     *
     * @param bean
     */
    @Override
    public void onDataChange(UserManageFilterBean bean) {
        if (bean != null && !bean.isEmpty()) {
            if (mDataBinding.btnCleanAll.getVisibility() == View.GONE)
                mDataBinding.btnCleanAll.setVisibility(View.VISIBLE);
        } else {
            if (mDataBinding.btnCleanAll.getVisibility() == View.VISIBLE)
                mDataBinding.btnCleanAll.setVisibility(View.GONE);
        }
    }

    /**
     * 清空所有筛选条件
     */
    @Override
    public void cleanFilterAll() {
        //将对象初始化，然后重置所有显示的内容
        mFilterBean = null;
        mDataBinding.setFilterBean(getFilterBean());
        mDataBinding.executePendingBindings();
        mProvinceId = null;
        mCityId = null;
        setupUserStatusSelectPosition(0);
        setupUserTypeSelectPosition(0);
     /*   mDataBinding.tvAddress.setText("");
        mDataBinding.tvUserType.setText("");
        mDataBinding.tvUserStatus.setText("");
        mDataBinding.editGroupName.setText("");
        mDataBinding.editUsername.setText("");*/
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_clean_all:
                cleanFilterAll();
                v.setVisibility(View.GONE);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case SELECT_ADDRESS_REQUEST_CODE:
                    if (data != null) {
                        ChooseProvinceBean provinceBean = data.getParcelableExtra("provinceBean");
                        ChooseProvinceBean cityBean = data.getParcelableExtra("cityBean");
                        if (provinceBean == null || cityBean == null) {
                            mDataBinding.tvAddress.setText("");
                            mProvinceId = null;
                            mCityId = null;
                        } else {
                            String addressTxt = provinceBean.getRegionName();
                            addressTxt += "-";
                            addressTxt += cityBean.getRegionName();
                            mProvinceId = provinceBean.getRegionId();
                            mCityId = cityBean.getRegionId();
                            mDataBinding.tvAddress.setText(addressTxt);
                        }
                        getFilterBean().setProvinceId(mProvinceId);
                        getFilterBean().setCityId(mCityId);
                    }
                    break;
            }
        }
    }

}
