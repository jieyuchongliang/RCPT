package com.rcpt.mvp.presenter;

import com.rcpt.LoginHelper;
import com.rcpt.R;
import com.rcpt.base.mvp.BasePresenter;
import com.rcpt.bean.VideoInfoBaseBean;
import com.rcpt.bean.VideoPaymentBean;
import com.rcpt.mvp.contract.VideoInfoContract;
import com.rcpt.mvp.module.VideoInfoModule;
import com.rcpt.ui.home.video.VideoPaymentActivity;
import com.rcpt.ui.login.LoginActivity;

/**
 * Created by lvzp on 2017/5/8.
 * 类描述：
 */

public class VideoInfoPresenter extends BasePresenter<VideoInfoContract.View> implements VideoInfoContract.Presenter {

    private VideoInfoModule mModule;

    @Override
    public void attach(VideoInfoContract.View view) {
        super.attach(view);
        mModule = new VideoInfoModule();
        mModule.initPageFragment(getView().getClassTypeId());
    }


    @Override
    public void loadPageData() {
        getView().bindPageFragment(mModule.getFragmentList(), mModule.getTitleList());
    }

    @Override
    public void onClickCloseActivity() {
        getView().onBackClick();
    }

    /**
     * 购买课程
     */
    @Override
    public void onClickBuyVideo() {
        //判断是否登陆
        if (!LoginHelper.getInstance().isOnline()) {
            getView().actionStartActivity(LoginActivity.class);
            getView().showToast(getView().getContext().getString(R.string.logo_message));
            return;
        }
        VideoInfoBaseBean videoInfoBaseBean = getView().getVideoInfoBaseBean();
        if (videoInfoBaseBean == null) {
            getView().showToast("数据获取异常");
            return;
        }
        VideoPaymentBean bean = new VideoPaymentBean();
        bean.setCourseId(videoInfoBaseBean.getClassTypeId());
        bean.setCourseInfo(videoInfoBaseBean.getDetailDesc());
        bean.setCourseName(videoInfoBaseBean.getName());
        bean.setCoursePrice(videoInfoBaseBean.getRealPrice());
        bean.setCommodityId(videoInfoBaseBean.getId());
        bean.setSubject_id(getView().getCourseTypeId());
        bean.setSubject(getView().getCourseTypeName());
        bean.setTeaching_style(getView().getTeachingStyle());
        VideoPaymentActivity.actionStart(getView().getContext(), bean, null);
    }
}
