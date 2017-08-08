package com.rcpt.mvp.presenter;

import android.support.v7.widget.RecyclerView;

import com.rcpt.LoginHelper;
import com.rcpt.R;
import com.rcpt.adapter.VideoCatalogAdapter;
import com.rcpt.base.mvp.BasePresenter;
import com.rcpt.base.mvp.OnDataGetCallback;
import com.rcpt.bean.VideoInfoBean;
import com.rcpt.bean.VideoInfoCatalogBean;
import com.rcpt.mvp.contract.VideoInfoCatalogContract;
import com.rcpt.mvp.module.VideoInfoCatalogModule;
import com.rcpt.ui.login.LoginActivity;
import com.rcpt.utils.AnimationUtils;

import java.util.List;

/**
 * Created by lvzp on 2017/5/9.
 * 类描述：
 */

public class VideoInfoCatalogPresenter extends BasePresenter<VideoInfoCatalogContract.View> implements VideoInfoCatalogContract.Presenter {

    private VideoInfoCatalogModule mModule;

    @Override
    public void attach(VideoInfoCatalogContract.View view) {
        super.attach(view);
        mModule = new VideoInfoCatalogModule();
        getView().initRecyclerView();
    }

    @Override
    public void loadListData() {
        mModule.requestVideoCatalogList(getView().getContext(), LoginHelper.getInstance().getUserToken(), getView().getClassTypeId(), new OnDataGetCallback<List<VideoInfoCatalogBean>>() {
            @Override
            public void onSuccessResult(List<VideoInfoCatalogBean> data) {
                getView().bindListData(data);
            }
        });

    }

    @Override
    public void onItemClick(RecyclerView.ViewHolder holder, int groupPos, int childPos, boolean isGroup) {
        VideoInfoCatalogBean videoInfoCatalogBean = mModule.getListData().get(groupPos);
        if (!isGroup) {
            final VideoInfoCatalogBean.LetvBean letv = videoInfoCatalogBean.getLetv();
            final VideoInfoCatalogBean.VideoInfoContentBean bean = videoInfoCatalogBean.getLectures().get(childPos);
            //判断是否登陆
            if (!LoginHelper.getInstance().isOnline()) {
                if (bean.getChargeStatus() == 0 || bean.getChargeStatus() == 1) {
                    getView().actionStartActivity(LoginActivity.class);
                    getView().showToast(getView().getContext().getString(R.string.logo_message));
                    return;
                }
            }
            mModule.checkVideoPermission(getView().getContext(), LoginHelper.getInstance().getUserToken()
                    , getView().getClassTypeId()
                    , videoInfoCatalogBean.getModuleId()
                    , bean.getId()
                    , new OnDataGetCallback<Integer>() {
                        @Override
                        public void onSuccessResult(Integer data) {
                            int needBuy = 0;
                            if (data == 1) {
                                if (bean.getChargeStatus() == 0) {//判断如果是收费的视频，就去判断
                                    getView().showDialog();
                                    return;
                                } else if (bean.getChargeStatus() == 2) {
                                    needBuy = 1;
                                } else if (bean.getChargeStatus() == 1) {
                                    needBuy = 0;
                                }
                                startPlayVideo(letv, bean, needBuy);
                            } else {
                                startPlayVideo(letv, bean, needBuy);
                            }
                        }
                    });
        }

    }

    /**
     * 开始播放视频
     *
     * @param letv
     * @param bean
     * @param needBuy 是否需要购买，1是需要，0是不需要
     */
    private void startPlayVideo(VideoInfoCatalogBean.LetvBean letv, VideoInfoCatalogBean.VideoInfoContentBean bean, int needBuy) {
        getView().onClickVideoCatalogItemCallback(new VideoInfoBean(
                letv.getLetv_uuid()
                , bean.getWebVideoId()
                , letv.getLetv_user_id()
                , letv.getLetv_api_key()
                , needBuy
                , bean.getOverFlowTime()));
    }

    /**
     * 当折叠状态改变时的回调方法
     *
     * @param holder
     * @param isExpand        折叠状态
     * @param groupPosition   group的位置
     * @param adapterPosition group在Adapter中的位置
     */
    @Override
    public void onExpandChange(RecyclerView.ViewHolder holder, boolean isExpand, int groupPosition, int adapterPosition) {
        VideoCatalogAdapter.TitleViewHolder titleViewHolder = (VideoCatalogAdapter.TitleViewHolder) holder;
        if (isExpand) {
            AnimationUtils.rotationExpandIcon(titleViewHolder.getBinding().ivExpand, 0, 180);
        } else {
            AnimationUtils.rotationExpandIcon(titleViewHolder.getBinding().ivExpand, 180, 0);
        }
    }
}
