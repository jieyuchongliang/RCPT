package com.rcpt.ui.recruit;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;

import com.rcpt.R;
import com.rcpt.base.ui.BaseActivity;
import com.rcpt.bean.CompanyRecruitListBean;
import com.rcpt.databinding.ActivitySendJobInvitationBinding;
import com.rcpt.mvp.contract.SendJobInvitationContract;
import com.rcpt.mvp.presenter.SendJobInvitationPresenter;
import com.rcpt.widget.DateTimePickerDialog;

public class SendJobInvitationActivity extends BaseActivity<ActivitySendJobInvitationBinding, SendJobInvitationContract.View, SendJobInvitationPresenter>
        implements SendJobInvitationContract.View, DateTimePickerDialog.OnDateTimeSetListener {

    private String mCvId;
    private CompanyRecruitListBean.RecruitlistBean mRecruitBean;
    private long mDateTimeSelectData;
    private String mCVUserId;

    public static void actionStart(Activity activity, CompanyRecruitListBean.RecruitlistBean bean, String cvId, String cvUserId, int requestCode) {
        Intent intent = new Intent(activity, SendJobInvitationActivity.class);
        intent.putExtra("bean", bean);
        intent.putExtra("cv_user_id", cvUserId);
        intent.putExtra("cv_id", cvId);
        activity.startActivityForResult(intent, requestCode);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    /**
     * 未实现的方法
     */
    @Override
    protected void setupTitle() {
        setTitleText("职位邀请");
        openTitleLeftView(true);
    }

    @Override
    protected void initFieldBeforeMethods() {
        Intent intent = getIntent();
        mCvId = intent.getStringExtra("cv_id");
        mRecruitBean = intent.getParcelableExtra("bean");
        mCVUserId = intent.getStringExtra("cv_user_id");
    }

    @Override
    protected void initViews() {
        mDataBinding.setBean(mRecruitBean);
        mDataBinding.setPresenter(mPresenter);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_send_job_invitation;
    }

    @Override
    protected SendJobInvitationPresenter createPresenter() {
        return new SendJobInvitationPresenter();
    }

    public CompanyRecruitListBean.RecruitlistBean getRecruitBean() {
        return mRecruitBean;
    }

    /**
     * 显示日期时间选择器
     */
    @Override
    public void showDateTimeSelectDialog(long dateTime) {
        if (dateTime == -1) {
            dateTime = System.currentTimeMillis();
        }
        DateTimePickerDialog dialog = new DateTimePickerDialog(this, dateTime);
        dialog.setOnDateTimeSetListener(this);
        dialog.show();
    }
    /**
     * 获取时间选择器选择后的数据
     *
     * @return
     */
    @Override
    public long getDateTimeSelectData() {
        return mDateTimeSelectData;
    }


    @Override
    public void OnDateTimeSet(AlertDialog dialog, long date) {
        mDateTimeSelectData = date;
        mPresenter.onDialogClickSetting();
    }
    /**
     * 获取备注信息
     *
     * @return
     */
    @Override
    public String getRemarks() {
        return mDataBinding.editRemarks.getText().toString();
    }


    /**
     * 获取简历Id
     *
     * @return
     */
    @Override
    public String getCVId() {
        return mCvId;
    }

    @Override
    public String getCVUserId() {
        return mCVUserId;
    }



    /**
     * 当发送成功后，关闭页面
     */
    @Override
    public void onSendSuccessCloseActivity() {
        setResult(RESULT_OK);
        finish();
    }
}
