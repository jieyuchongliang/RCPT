package com.rcpt.mvp.presenter;

import com.rcpt.LoginHelper;
import com.rcpt.base.mvp.BasePresenter;
import com.rcpt.base.mvp.OnDataGetCallback;
import com.rcpt.bean.SendRecordBean;
import com.rcpt.mvp.contract.SendRecordContract;
import com.rcpt.mvp.module.SendRecordModule;
import com.rcpt.ui.recruit.RecruitJobInfoActivity;

import java.util.List;

/**
 * Created by lvzp on 2017/3/2.
 * 类描述：查看投递简历的控制器
 */

public class SendRecordPresenter extends BasePresenter<SendRecordContract.View> implements SendRecordContract.Presenter {

    private SendRecordModule mDataModule;
    private String mUserId;

    @Override
    public void attach(SendRecordContract.View view) {
        super.attach(view);
        mDataModule = new SendRecordModule();
        getView().initRecyclerView();
        mUserId = LoginHelper.getInstance().getUserToken();
    }

    @Override
    public void loadListData() {
        loadListData(1);
    }

    @Override
    public void onLoadMore(int page) {
        loadListData(page);
    }

    private void loadListData(int page) {
        mDataModule.requestSendRecordList(getView().getContext(), mUserId, page, new OnDataGetCallback<List<SendRecordBean.DeliveryRecordListBean>>() {
            @Override
            public void onSuccessResult(List<SendRecordBean.DeliveryRecordListBean> data) {
                getView().updateIsEnd(mDataModule.isEnd());
                getView().bindListData(data);
            }
        });
    }

    @Override
    public void onItemClick() {
        final int position = getView().getDeleteClickPosition();
        SendRecordBean.DeliveryRecordListBean bean = mDataModule.getListData().get(position);

        if (0 == bean.getDelFlg()) {
            RecruitJobInfoActivity.actionStart(getView().getContext(), bean.getCompanyId(), bean.getRecruitmentInfoId());
        } else {
            getView().showToast("该职位已删除。");
        }

    }


}
