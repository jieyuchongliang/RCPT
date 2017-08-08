package com.rcpt.mvp.presenter;

import android.text.TextUtils;
import android.widget.TextView;

import com.rcpt.LoginHelper;
import com.rcpt.base.mvp.BasePresenter;
import com.rcpt.base.mvp.OnDataGetCallback;
import com.rcpt.bean.CompanyRecruitAcceptNumListBean;
import com.rcpt.http.api.ApiClient;
import com.rcpt.http.bean.RequestSendInterviewNoticeBean;
import com.rcpt.mvp.contract.CompanyRecruitAcceptNumListContract;
import com.rcpt.mvp.module.CompanyRecruitAcceptNumListModule;
import com.rcpt.ui.recruit.RecruitCVPreviewInfoActivity;
import com.rcpt.utils.DateUtils;

import java.util.List;
import java.util.Map;

/**
 * Created by lvzp on 2017/5/2.
 * 类描述：
 */

public class CompanyRecruitAcceptNumListPresenter extends BasePresenter<CompanyRecruitAcceptNumListContract.View> implements CompanyRecruitAcceptNumListContract.Presenter {

    private CompanyRecruitAcceptNumListModule mModule;
    private TextView mTvCurrentSelectView;

    @Override
    public void attach(CompanyRecruitAcceptNumListContract.View view) {
        super.attach(view);
        getView().initRecyclerView();
        mModule = new CompanyRecruitAcceptNumListModule();
    }

    /**
     * 加载更多的数据
     * 只需要根据相应的页码加载相应的数据，无需关心刷新和加载更多
     *
     * @param page
     */
    @Override
    public void onLoadMore(int page) {
        loadListData(page);
    }

    @Override
    public void loadListData() {
        loadListData(1);
    }

    private void loadListData(int page) {
        mModule.requestRecruitAcceptList(getView().getContext(), LoginHelper.getInstance().getUserToken(), getView().getRecruitmentInfoId(), page, new OnDataGetCallback<List<CompanyRecruitAcceptNumListBean.JobPeopleListBean>>() {
            @Override
            public void onSuccessResult(List<CompanyRecruitAcceptNumListBean.JobPeopleListBean> data) {
                getView().updateIsEnd(mModule.isEnd());
                getView().bindListData(data);
            }
        });
    }

    /**
     * 更新阅读状态
     *
     * @param cvId     简历id
     * @param cvUserId 简历用户id
     */
    @Override
    public void updateReadingStatus(final String cvId, final String cvUserId) {
        mModule.updateCVPreviewStatus(getView().getContext(), LoginHelper.getInstance().getUserToken(), cvUserId, cvId, new OnDataGetCallback<Boolean>() {
            @Override
            public void onSuccessResult(Boolean data) {
                RecruitCVPreviewInfoActivity.actionStart(getView().getContext(), cvId,cvUserId);
            }
        });
    }

    /**
     * 开启点击打开时间选择器
     */
    @Override
    public void onDialogClickOpenDateSelect(TextView view) {
        mTvCurrentSelectView = view;
        String s = mTvCurrentSelectView.getText().toString();
        long time;
        if (TextUtils.isEmpty(s)) {
            time = System.currentTimeMillis();
        } else {
            time = DateUtils.stringToTime(s);
        }
        getView().showDateTimeSelectDialog(time);
    }


    /**
     * 当时间选择器的点击了设置按钮执行的回调
     */
    @Override
    public void onDialogClickSetting() {
        long dateTimeSelectData = getView().getDateTimeSelectData();
        String s = DateUtils.formatDate(DateUtils.FORMAT, dateTimeSelectData);
        mTvCurrentSelectView.setText(s);
    }

    /**
     * 通知按钮的点击事件
     */
    @Override
    public void onClickSendNotice() {
        getView().showSendNoticeDialog();
    }

    /**
     * 点击取消
     */
    @Override
    public void onDialogClickCancel() {
        getView().hideSendNoticeDialog();
    }

    /**
     * 点击确认
     */
    @Override
    public void onDialogClickConfirm() {

        String selectStartTime = getView().getSelectStartTime();
        String selectEndTime = getView().getSelectEndTime();
        String describeContentText = getView().getDescribeContentText();

        //判断开始时间
        if (TextUtils.isEmpty(selectStartTime)) {
            getView().showToast("请选择开始时间");
            return;
        } else if (DateUtils.isLessCurrentTime(DateUtils.stringToTime(selectStartTime))) {
            getView().showToast("开始时间不能小于当前时间");
            return;
        }
        //判断结束时间的格式
        if (TextUtils.isEmpty(selectEndTime)) {
            getView().showToast("请选择结束时间");
            return;
        } else if (DateUtils.stringToTime(selectStartTime) > DateUtils.stringToTime(selectEndTime)) {
            getView().showToast("结束时间不能小于开始时间");
            return;
        }
        final int position = getView().getClickPosition();
        final CompanyRecruitAcceptNumListBean.JobPeopleListBean jobPeopleListBean = mModule.getListData().get(position);
        RequestSendInterviewNoticeBean bean = new RequestSendInterviewNoticeBean();
        bean.setCompanyId(LoginHelper.getInstance().getUserToken());
        bean.setStartTime(selectStartTime);
        bean.setEndTime(selectEndTime);
        bean.setDescription(describeContentText);
        bean.setRecruiters(jobPeopleListBean.getUserId());
        bean.setInterviewRecordId(jobPeopleListBean.getRecordId());
        Map<String, String> params = ApiClient.createBodyMap(bean);
        mModule.sendJobInterviewNotice(getView().getContext(), params, new OnDataGetCallback<String>() {
            @Override
            public void onSuccessResult(String data) {
                getView().showToast(data);
                jobPeopleListBean.setStatus(0);
                getView().hideSendNoticeDialog();
                getView().updateListItemForPosition(position);
            }
        });

    }
}
