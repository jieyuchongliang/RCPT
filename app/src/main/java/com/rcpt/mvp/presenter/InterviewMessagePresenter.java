package com.rcpt.mvp.presenter;

import com.rcpt.LoginHelper;
import com.rcpt.base.mvp.BasePresenter;
import com.rcpt.base.mvp.OnDataGetCallback;
import com.rcpt.bean.InterviewMessageListBean;
import com.rcpt.mvp.contract.InterviewMessageContract;
import com.rcpt.mvp.module.InterviewMessageModule;
import com.rcpt.ui.recruit.RecruitJobInfoActivity;

import java.util.List;

/**
 * Created by lvzp on 2017/3/6.
 * 类描述：
 */

public class InterviewMessagePresenter extends BasePresenter<InterviewMessageContract.View> implements InterviewMessageContract.Presenter {

    private InterviewMessageModule mModule;
    private String mClickItemId;

    @Override
    public void attach(InterviewMessageContract.View view) {
        super.attach(view);
        mModule = new InterviewMessageModule();
        getView().initRecyclerView();
    }

    @Override
    public void loadListData() {
        loadListData(1);
    }

    @Override
    public void onItemClick() {
        int position = getView().getClickPosition();
        InterviewMessageListBean.GetInterviewBean bean = mModule.getListData().get(position);
        if (0 == bean.getDelFlg()) {
            RecruitJobInfoActivity.actionStart(getView().getContext(), bean.getCompany_id(), bean.getRecruitment_info_id());
        } else {
            getView().showToast("该职位已删除");
        }
    }

    /**
     * 点击面试邀请处理
     */
    @Override
    public void onClickDisposeInvitation() {
        int position = getView().getClickPosition();
        InterviewMessageListBean.GetInterviewBean bean = mModule.getListData().get(position);
        mClickItemId = bean.getId();
        getView().showInfoDialog(bean);
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

    /**
     * Dialog点击关闭
     */
    @Override
    public void onDialogClickClose() {
        getView().infoDialogClose();
    }

    /**
     * Dialog点击同意
     */
    @Override
    public void onDialogClickAgree() {
        updateInterviewStatus("1", null);
    }

    /**
     * Dialog点击拒绝
     */
    @Override
    public void onDialogClickDisagree() {
        getView().hintDialogSettingLayout();
        getView().showDialogConfirm();
        getView().showInputDisagreeReasonLayout();
    }

    /**
     * Dialog中点击拒绝后显示的确认的按钮
     */
    @Override
    public void onDialogClickConfirm() {
        String disagreeContent = getView().getDisagreeContent();
        updateInterviewStatus("2", disagreeContent);
    }

    private void updateInterviewStatus(final String status, String reason) {
        mModule.updateInterviewStatus(getView().getContext(), mClickItemId, status, reason, new OnDataGetCallback<String>() {
            @Override
            public void onSuccessResult(String data) {
                getView().showToast(data);
                getView().infoDialogClose();
                int position = getView().getClickPosition();
                InterviewMessageListBean.GetInterviewBean bean = mModule.getListData().get(position);
                bean.setStatus(Integer.decode(status));
                getView().updateListItem(position);
            }
        });
    }

    private void loadListData(int page) {
        mModule.requestInterviewMessageList(getView().getContext(), LoginHelper.getInstance().getUserToken(), page, new OnDataGetCallback<List<InterviewMessageListBean.GetInterviewBean>>() {
            @Override
            public void onSuccessResult(List<InterviewMessageListBean.GetInterviewBean> data) {
                getView().updateIsEnd(mModule.isEnd());
                getView().bindListData(data);
            }
        });

    }

}
