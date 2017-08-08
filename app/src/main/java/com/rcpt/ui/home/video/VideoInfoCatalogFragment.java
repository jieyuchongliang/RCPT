package com.rcpt.ui.home.video;


import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rcpt.LoginHelper;
import com.rcpt.R;
import com.rcpt.adapter.VideoCatalogAdapter;
import com.rcpt.base.ui.LazyFragment;
import com.rcpt.bean.VideoInfoBaseBean;
import com.rcpt.bean.VideoInfoBean;
import com.rcpt.bean.VideoInfoCatalogBean;
import com.rcpt.bean.VideoPaymentBean;
import com.rcpt.databinding.FragmentVideoInfoCatalogBinding;
import com.rcpt.mvp.contract.VideoInfoCatalogContract;
import com.rcpt.mvp.presenter.VideoInfoCatalogPresenter;
import com.rcpt.ui.login.LoginActivity;
import com.rcpt.utils.DialogUtils;

import java.util.List;

/**
 * 视频详情的目录页面
 */
public class VideoInfoCatalogFragment extends LazyFragment<FragmentVideoInfoCatalogBinding, VideoInfoCatalogContract.View, VideoInfoCatalogPresenter>
        implements VideoInfoCatalogContract.View {

    private static final String CLASS_TYPE_ID = "type_id";

    public static VideoInfoCatalogFragment newInstance(String classTypeId) {
        VideoInfoCatalogFragment fragment = new VideoInfoCatalogFragment();
        Bundle args = new Bundle();
        args.putString(CLASS_TYPE_ID, classTypeId);
        fragment.setArguments(args);
        return fragment;
    }


    private VideoCatalogAdapter mAdapter;
    private OnClickVideoCatalogItemCallback mCallback;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mCallback = (OnClickVideoCatalogItemCallback) context;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return super.onCreateView(inflater, container, savedInstanceState);
    }


    @Override
    public void initRecyclerView() {
        mDataBinding.recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mAdapter = new VideoCatalogAdapter();
        mAdapter.setOnItemGroupExpandChangeListener(mPresenter);
        mAdapter.setOnItemClickListener(mPresenter);
        mAdapter.setNeedExpand(true);
        mDataBinding.recyclerView.setAdapter(mAdapter);
    }

    @Override
    public void bindListData(List<VideoInfoCatalogBean> beanList) {
        mAdapter.setListData(beanList);
        mAdapter.setBuy(isAlreadyBuy());
        mAdapter.groupExpand(0);
    }

    /**
     * 更新列表Group的状态
     *
     * @param groupPosition
     */
    @Override
    public void updateListGroup(int groupPosition) {
        mAdapter.notifyDataChangeGroupItem(groupPosition);
    }

    @Override
    public RecyclerView getRecyclerView() {
        return mDataBinding.recyclerView;
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_video_info_catalog;
    }

    @Override
    protected void initViews() {

    }

    @Override
    public void onClickVideoCatalogItemCallback(VideoInfoBean bean) {
        mCallback.onVideoCatalogItemClick(bean);
    }

    @Override
    public String getClassTypeId() {
        return getArguments().getString(CLASS_TYPE_ID);
    }

    @Override
    protected void loadData() {
        mPresenter.loadListData();
    }


    /**
     * 是否已经购买
     *
     * @return
     */
    @Override
    public boolean isAlreadyBuy() {
        VideoInfoBaseBean videoInfoBaseBean = getVideoInfoBaseBean();
        if (videoInfoBaseBean != null) {
            return videoInfoBaseBean.getBuyFlag() == 1;
        }
        return false;
    }

    @Override
    public void showDialog() {
        AlertDialog.Builder dialog = DialogUtils.buildAlertDialogWithCancel(mContext, "温馨提示", "当前课程为收费视频，是否立即购买");
        dialog.setPositiveButton("立即购买", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //判断是否登陆
                if (!LoginHelper.getInstance().isOnline()) {
                    actionStartActivity(LoginActivity.class);
                    showToast(getContext().getString(R.string.logo_message));
                    return;
                }
                VideoInfoBaseBean videoInfoBaseBean = getVideoInfoBaseBean();
                if (videoInfoBaseBean == null) return;
                //将视频的信息封装到支付的对象中
                VideoPaymentBean bean = new VideoPaymentBean();
                bean.setCourseId(videoInfoBaseBean.getClassTypeId());
                bean.setCourseInfo(videoInfoBaseBean.getDetailDesc());
                bean.setCourseName(videoInfoBaseBean.getName());
                bean.setCoursePrice(videoInfoBaseBean.getRealPrice());
                bean.setCommodityId(videoInfoBaseBean.getId());
                VideoPaymentActivity.actionStart(mContext, bean, null);
            }
        });
        dialog.show();
    }

    @Nullable
    private VideoInfoBaseBean getVideoInfoBaseBean() {
        VideoInfoActivity activity = (VideoInfoActivity) getActivity();
        VideoInfoBaseBean videoInfoBaseBean = activity.mVideoInfoBaseBean;
        if (videoInfoBaseBean == null) {
            showToast("课程信息获取异常");
            return null;
        }
        return videoInfoBaseBean;
    }

    @Override
    protected VideoInfoCatalogPresenter createPresenter() {
        return new VideoInfoCatalogPresenter();
    }


    public interface OnClickVideoCatalogItemCallback {
        /**
         * 当条目点击时触发的回调
         *
         * @param infoBean 视频信息的实体类
         */
        void onVideoCatalogItemClick(VideoInfoBean infoBean);
    }

}
