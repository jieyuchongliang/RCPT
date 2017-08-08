package com.rcpt.ui.me;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.rcpt.R;
import com.rcpt.base.ui.BaseActivity;
import com.rcpt.bean.CVInfoBean;
import com.rcpt.databinding.ActivityCvManagementBinding;
import com.rcpt.mvp.contract.CVManagementContract;
import com.rcpt.mvp.presenter.CVManagementPresenter;
import com.rcpt.ui.me.cv.CVInfoListActivity;
import com.rcpt.ui.me.cv.CreateCVInfoActivity;
import com.rcpt.widget.InputDataPopWindow;
import com.rcpt.widget.InputSingleDataPopWindow;

public class CVManagementActivity extends BaseActivity<ActivityCvManagementBinding, CVManagementContract.View, CVManagementPresenter>
        implements CVManagementContract.View {

    private static final int REQUEST_CODE_CREATE_NEW_CV = 0x000011;

    private InputDataPopWindow mInputDataPop;
    private InputSingleDataPopWindow mInputSingleDataPop;

    @Override
    protected void setupTitle() {
        setTitleText("简历管理");
        openTitleLeftView(true);
    }

    @Override
    protected void initViews() {
        mDataBinding.setPresenter(mPresenter);
        mDataBinding.emptyCv.setPresenter(mPresenter);
        mPresenter.loadCVInfo();
        initInputPop();
    }

    /**
     * 初始化输入框的PopWindow
     */
    private void initInputPop() {
        mInputSingleDataPop = new InputSingleDataPopWindow(this);
        mInputSingleDataPop.setTitleText("简历名称");
        mInputSingleDataPop.setInputHintText("请输入您的简历名称（必填不可为空）");
        mInputSingleDataPop.setCheckInputMaxLength(50);
        mInputSingleDataPop.setOnClickSaveCallback(new InputDataPopWindow.OnClickSaveCallback() {
            @Override
            public void onSaveCallback(String content) {
                mPresenter.updateCVName(content);
            }
        });

        mInputDataPop = new InputDataPopWindow(this);
        mInputDataPop.setTitleText("自我评价");
        mInputDataPop.setCheckInputMaxLength(210);
        mInputDataPop.setOnClickSaveCallback(new InputDataPopWindow.OnClickSaveCallback() {
            @Override
            public void onSaveCallback(String content) {
                mPresenter.uploadSelfAppraisal(content);
            }
        });
    }

    @Override
    public void changeCVNameText(String cvName) {
        mDataBinding.tvCvName.setText(cvName);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_cv_management;
    }

    @Override
    protected CVManagementPresenter createPresenter() {
        return new CVManagementPresenter();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    /**
     * 显示简历信息的详情界面
     */
    @Override
    public void showCVInfo(CVInfoBean bean) {
        mDataBinding.emptyCv.getRoot().setVisibility(View.GONE);
        mDataBinding.slCvInfo.setVisibility(View.VISIBLE);
        mDataBinding.setCvInfoBean(bean);
        if (bean.getPublicSet().equals("公开")) {
            mDataBinding.cbxCvPublicSet.setChecked(false);
        } else {
            mDataBinding.cbxCvPublicSet.setChecked(true);
        }
    }

    @Override
    public void changePublicStatusText(String publicText) {
        mDataBinding.cbxCvPublicSet.setText(publicText);
    }

    @Override
    public boolean showCVPublicStatus() {
        return mDataBinding.cbxCvPublicSet.isChecked();
    }

    /**
     * 显示创建简历的界面
     */
    @Override
    public void showCreateCV() {
        mDataBinding.emptyCv.getRoot().setVisibility(View.VISIBLE);
        mDataBinding.slCvInfo.setVisibility(View.GONE);
    }

    @Override
    public void actionGoCreateNewCV(String createCVType,String cvName) {
        CreateCVInfoActivity.actionStart(mActivity,createCVType,cvName,true,REQUEST_CODE_CREATE_NEW_CV);
    }

    @Override
    public void actionGoCreateCVInfo(String createCVType, String cvId) {
        CreateCVInfoActivity.actionStart(mActivity, createCVType, cvId, REQUEST_CODE_CREATE_NEW_CV);
    }

    @Override
    public void actionGoEditCVInfo(String createCVType, String cvId) {
        CreateCVInfoActivity.actionStart(mActivity, createCVType, cvId, true, null, REQUEST_CODE_CREATE_NEW_CV);
    }

    @Override
    public void actionGoCVInfoList(String createCVType, String cvId) {
        CVInfoListActivity.actionStart(mContext, createCVType, cvId);
    }

    @Override
    public void showChangeCVNamePop(String cvName) {
        mInputSingleDataPop.showPopupWindow(cvName, mDataBinding.getRoot());
    }

    @Override
    public void showSelfAppraisalInfoPop(String selfAppraisal) {
        mInputDataPop.showPopupWindow(selfAppraisal, mDataBinding.getRoot());
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case REQUEST_CODE_CREATE_NEW_CV:
                    mPresenter.loadCVInfo();
                    break;
            }
        }
    }
}
