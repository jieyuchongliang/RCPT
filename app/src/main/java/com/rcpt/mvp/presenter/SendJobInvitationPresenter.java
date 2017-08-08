package com.rcpt.mvp.presenter;

import android.text.TextUtils;
import android.widget.TextView;

import com.rcpt.LoginHelper;
import com.rcpt.base.mvp.BasePresenter;
import com.rcpt.base.mvp.OnDataGetCallback;
import com.rcpt.http.api.ApiClient;
import com.rcpt.http.bean.RequestSendJobInvitationBean;
import com.rcpt.mvp.contract.SendJobInvitationContract;
import com.rcpt.mvp.module.SendJobInvitationModule;
import com.rcpt.utils.DateUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Map;

/**
 * Created by lvzp on 2017/4/17.
 * 类描述：
 */

public class SendJobInvitationPresenter extends BasePresenter<SendJobInvitationContract.View> implements SendJobInvitationContract.Presenter {

    private TextView mTvCurrentShowDate;
    private boolean isStartTime;
    private String mStartTime;
    private String mEndTime;
    private SendJobInvitationModule mModule;

    @Override
    public void attach(SendJobInvitationContract.View view) {
        super.attach(view);
        mModule = new SendJobInvitationModule();
    }

    /**
     * 点击显示开始时间的Dialog
     */
    @Override
    public void onClickShowStartTimeDialog(TextView textView) {
        isStartTime = true;
        mTvCurrentShowDate = textView;
        getView().showDateTimeSelectDialog(getDate(textView.getText().toString()));
    }

    /**
     * 点击显示结束时间的Dialog
     */
    @Override
    public void onClickShowEndTimeDialog(TextView tv) {
        isStartTime = false;
        mTvCurrentShowDate = tv;
        getView().showDateTimeSelectDialog(getDate(tv.getText().toString()));
    }

    /**
     * 点击发送职位邀请的按钮
     */
    @Override
    public void onClickSendJobInvitation() {
        if (TextUtils.isEmpty(mStartTime)) {
            getView().showToast("请选择面试的开始时间");
            return;
        }
        if (TextUtils.isEmpty(mEndTime)) {
            getView().showToast("请选择面试的结束时间");
            return;
        }
        if (DateUtils.isLessCurrentTime(getDate(mStartTime))) {
            getView().showToast("开始时间不能小于当前时间");
            return;
        }

        if (getDate(mStartTime) > getDate(mEndTime)) {
            getView().showToast("结束时间不能小于开始时间");
            return;
        }

        RequestSendJobInvitationBean bean = new RequestSendJobInvitationBean();
        bean.setCvUserId(getView().getCVUserId());
        bean.setCvId(getView().getCVId());
        bean.setApplicationEndTime(mEndTime);
        bean.setApplicationTime(mStartTime);
        bean.setIds(getView().getRecruitBean().getRecruitmentInfoId());
        bean.setCompanyId(LoginHelper.getInstance().getUserToken());
        bean.setRemarks(getView().getRemarks());
        Map<String, String> bodyMap = ApiClient.createBodyMap(bean);
        mModule.sendJobInvitation(getView().getContext(), bodyMap, new OnDataGetCallback<String>() {
            @Override
            public void onSuccessResult(String data) {
                getView().showToast(data);
                getView().onSendSuccessCloseActivity();
            }
        });
    }

    /**
     * Dialog中的设置按钮点击时执行的方法
     */
    @Override
    public void onDialogClickSetting() {
        long dateTimeSelectData = getView().getDateTimeSelectData();
        String stringDate = getStringDate(dateTimeSelectData);
        mTvCurrentShowDate.setText(stringDate);
        if (isStartTime) {
            mStartTime = stringDate;
        } else {
            mEndTime = stringDate;
        }
    }

    /**
     * 将长时间格式字符串转换为时间 yyyy-MM-dd HH:mm:ss
     */
    private String getStringDate(Long date) {
        SimpleDateFormat formatter = getDateFormat();
        return formatter.format(date);
    }

    private long getDate(String stringDate) {
        SimpleDateFormat formatter = getDateFormat();
        try {
            return formatter.parse(stringDate).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return -1;
    }

    private SimpleDateFormat getDateFormat() {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm");
    }
}
