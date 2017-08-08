package com.rcpt.ui.me.cv;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.View;

import com.rcpt.Constant;
import com.rcpt.R;
import com.rcpt.base.ui.BaseActivity;
import com.rcpt.databinding.ActivityCreateCvInfoBinding;
import com.rcpt.mvp.contract.CreateCVInfoContract;
import com.rcpt.mvp.presenter.CreateCVInfoPresenter;

/**
 * 创建简历详情的页面，可以单独创建某一项简历的内容
 * 也可以统一全部创建
 * 具体的创建流程可以参见{@link Constant.CreateCVType}
 */
public class CreateCVInfoActivity extends BaseActivity<ActivityCreateCvInfoBinding, CreateCVInfoContract.View, CreateCVInfoPresenter>
        implements CreateCVInfoContract.View, CreateCVInfoFragment.OnUpdateCVInfoCallback {


    private static final String CREATE_CV_TYPE = "type";
    private static final String CV_ID_TAG = "id_tag";
    private static final String CV_IS_EDIT = "is_edit";
    private static final String CV_EDIT_BEAN_TAG = "edit_bean_tag";
    private static final String CV_IS_CREATE = "is_create";
    private static final String CV_NAME = "cv_name";

    private String mCreateCVType;
    private String mCVId;
    private boolean isEdit;
    private Parcelable mEditBean;

    private FragmentManager mFragmentManager;

    public static void actionStart(Activity activity, String createCVType, String cvName, boolean isCreate, int requestCode) {
        Intent intent = new Intent(activity, CreateCVInfoActivity.class);
        intent.putExtra(CREATE_CV_TYPE, createCVType);
        intent.putExtra(CV_IS_CREATE, isCreate);
        intent.putExtra(CV_NAME, cvName);
        activity.startActivityForResult(intent, requestCode);
    }

    public static void actionStart(Activity activity, String createCVType, String cvId, int requestCode) {
        actionStart(activity, createCVType, cvId, false, null, requestCode);
    }

    public static void actionStart(Activity activity, String createCVType, String cvId, boolean isEdit, Parcelable bean, int requestCode) {
        Intent intent = new Intent(activity, CreateCVInfoActivity.class);
        intent.putExtra(CREATE_CV_TYPE, createCVType);
        intent.putExtra(CV_ID_TAG, cvId);
        intent.putExtra(CV_IS_EDIT, isEdit);
        intent.putExtra(CV_EDIT_BEAN_TAG, bean);
        activity.startActivityForResult(intent, requestCode);
    }

    @Override
    protected void initFieldBeforeMethods() {
        mFragmentManager = getSupportFragmentManager();
        Intent intent = getIntent();
        mCreateCVType = intent.getStringExtra(CREATE_CV_TYPE);
        mCVId = intent.getStringExtra(CV_ID_TAG);
        isEdit = intent.getBooleanExtra(CV_IS_EDIT, false);
        mEditBean = intent.getParcelableExtra(CV_EDIT_BEAN_TAG);
    }

    @Override
    protected void setupTitle() {
        setTitleText(Constant.getCreateCVInfoTitile(mCreateCVType));
        openTitleLeftView(true).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        String rightTitleText;
        if (Constant.CREATE_CV_ALL.equals(mCreateCVType)) {
            rightTitleText = "保存,下一步";
        } else {
            rightTitleText = "保存";
        }
        setTitleRightText(rightTitleText).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.onClickSave();
            }
        });
    }

    @Override
    protected void initViews() {
        mPresenter.initFragments(mCreateCVType);

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_create_cv_info;
    }

    @Override
    protected CreateCVInfoPresenter createPresenter() {
        return new CreateCVInfoPresenter();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    /**
     * 获取是否为创建新的简历
     *
     * @return
     */
    public boolean isCreateNewCV() {
        return getIntent().getBooleanExtra(CV_IS_CREATE, false);
    }

    /**
     * 获取简历名称
     *
     * @return
     */
    public String getCVName() {
        return getIntent().getStringExtra(CV_NAME);
    }

    /**
     * 创建教育背景的列表，并加入到FragmentManager中
     *
     * @return
     */
    @Override
    public Fragment createEducationAddToTask() {
        CreateCVInfoFragment fragment = CreateCVInfoFragment.newInstance(Constant.CREATE_CV_EDUCATION, mCVId, isEdit, mEditBean);
        mFragmentManager.beginTransaction()
                .add(R.id.content_view, fragment, Constant.CREATE_CV_EDUCATION)
                .hide(fragment)
                .commit();
        return fragment;
    }

    /**
     * 创建求职意向，并加入到FragmentManager中
     *
     * @return
     */
    @Override
    public Fragment createJobIntentAddToTask() {
        CreateCVInfoFragment fragment;
        if (isCreateNewCV()) {
            fragment = CreateCVInfoFragment.newInstance(Constant.CREATE_CV_JOB_INTENT, mCVId, isCreateNewCV(), getCVName());
        } else {
            fragment = CreateCVInfoFragment.newInstance(Constant.CREATE_CV_JOB_INTENT, mCVId, isEdit, mEditBean);
        }
        mFragmentManager.beginTransaction()
                .add(R.id.content_view, fragment, Constant.CREATE_CV_JOB_INTENT)
                .hide(fragment)
                .commit();
        return fragment;
    }

    /**
     * 创建项目经验，并加入到FragmentManager中
     *
     * @return
     */
    @Override
    public Fragment createProjectExperienceAddToTask() {
        CreateCVInfoFragment fragment = CreateCVInfoFragment.newInstance(Constant.CREATE_CV_PROJECT_EXPERIENCE, mCVId, isEdit, mEditBean);
        mFragmentManager.beginTransaction()
                .add(R.id.content_view, fragment, Constant.CREATE_CV_PROJECT_EXPERIENCE)
                .hide(fragment)
                .commit();
        return fragment;
    }

    /**
     * 创建工作经验，并加入到FragmentManager中
     *
     * @return
     */
    @Override
    public Fragment createWorkHistoryAddToTask() {
        CreateCVInfoFragment fragment = CreateCVInfoFragment.newInstance(Constant.CREATE_CV_WORK_HISTORY, mCVId, isEdit, mEditBean);
        mFragmentManager.beginTransaction()
                .add(R.id.content_view, fragment, Constant.CREATE_CV_WORK_HISTORY)
                .hide(fragment)
                .commit();
        return fragment;
    }

    @Override
    public void replaceFragment(Fragment showFragment, Fragment hideFragment, String title, boolean isLast) {
        if (isLast) {
            setTitleRightText("保存");
        } else {
            setTitleRightText("保存,下一步");
        }
        setTitleText(title);
        if (hideFragment == null)
            mFragmentManager.beginTransaction()
                    .show(showFragment)
                    .commit();
        else
            mFragmentManager.beginTransaction()
                    .hide(hideFragment)
                    .show(showFragment)
                    .commit();
    }

    @Override
    public void onBackPressed() {
        mPresenter.onClickBack();
    }

    @Override
    public void onSucceed() {
        mPresenter.goNextFragment();
    }



    @Override
    public void resultOk() {
        setResult(RESULT_OK);
    }
}
