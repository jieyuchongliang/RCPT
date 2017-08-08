package com.rcpt.mvp.presenter;

import com.rcpt.Constant;
import com.rcpt.LoginHelper;
import com.rcpt.R;
import com.rcpt.base.mvp.BasePresenter;
import com.rcpt.base.mvp.OnDataGetCallback;
import com.rcpt.bean.CVSelectBean;
import com.rcpt.bean.RecruitJobInfoBean;
import com.rcpt.mvp.contract.RecruitInfoContract;
import com.rcpt.mvp.module.RecruitInfoModule;
import com.rcpt.ui.login.LoginActivity;

import java.util.List;

/**
 * Created by lvzp on 2017/2/28.
 * 类描述：职位详情的控制器
 */

public class RecruitInfoPresenter extends BasePresenter<RecruitInfoContract.View> implements RecruitInfoContract.Presenter {

    private RecruitInfoModule mModule;
    private String mUserId;

    private boolean isFollow;

    @Override
    public void attach(RecruitInfoContract.View view) {
        super.attach(view);
        mModule = new RecruitInfoModule();
        mUserId = LoginHelper.getInstance().getUserToken();
    }

    /**
     * 投递简历的点击事件
     */
    @Override
    public void onClickSendResume() {
        //判断是否登陆
        if (!LoginHelper.getInstance().isOnline()) {
            getView().actionStartActivity(LoginActivity.class);
            getView().showToast(getView().getContext().getString(R.string.logo_message));
            return;
        }
        if (LoginHelper.getInstance().getUserBean().getUserType().equals(Constant.UserType.PERSON.getValue())) {
            mModule.requestGetCVList(getView().getContext(), mUserId, new OnDataGetCallback<List<CVSelectBean>>() {
                @Override
                public void onSuccessResult(List<CVSelectBean> data) {
                    if (data.size() > 0) {
                        for (CVSelectBean cvSelectBean : data) {
                            if (cvSelectBean.getDefaultCv().equals("0")) {
                                startSendResume(cvSelectBean.getCvId());
                            }
                        }
                    }
                }
            });
        }else{
            getView().showToast("对不起，您无权限操作此功能！");
        }

    }

    /**
     * 开始执行投递简历
     */
    private void startSendResume(String cvId) {
        mModule.requestSendResume(getView().getContext(), cvId, mUserId, getView().getJobId(), getView().getCompanyId(), new OnDataGetCallback<String>() {
            @Override
            public void onSuccessResult(String data) {
                getView().showToast(data);
                getView().setSendResumeBtnClickOff();
            }
        });
    }

    /**
     * 点击加入收藏夹
     */
    @Override
    public void onClickAddFavorites() {
        //判断是否登陆
        if (!LoginHelper.getInstance().isOnline()) {
            getView().actionStartActivity(LoginActivity.class);
            getView().showToast(getView().getContext().getString(R.string.logo_message));
            return;
        }
        if (LoginHelper.getInstance().getUserBean().getUserType().equals(Constant.UserType.PERSON.getValue())) {
            //如果是个人用户
            if (!isFollow) {
                mModule.requestFollowJob(getView().getContext(), mUserId, getView().getJobId(), getView().getCompanyId(), new OnDataGetCallback<String>() {
                    @Override
                    public void onSuccessResult(String data) {
                        isFollow = true;
                        getView().showSnackbar(data);
                        getView().setClickFavoriteChecked();
                    }
                });
            } else {
                getView().showSnackbar("该职位已收藏成功，无需多次点击");
            }
        }else{
            //非个人用户
            getView().showToast("对不起，您无权限操作此功能！");
        }

    }

    @Override
    public void loadInfo() {
        mModule.requestJobInfo(getView().getContext(), getView().getCompanyId(), mUserId, getView().getJobId(), new OnDataGetCallback<RecruitJobInfoBean>() {
            @Override
            public void onSuccessResult(RecruitJobInfoBean data) {
                if (data.getCvSend().equals("1")) {
                    getView().setSendResumeBtnClickOff();
                }
                if (data.getCollectTalent().equals("1")) {
                    getView().setClickFavoriteChecked();
                    isFollow = true;
                }
                if (data.getDel_flg() == 1) {
                    getView().showToast("该职位已被删除");
                }
                getView().setupData(data);
            }
        });
    }
}
