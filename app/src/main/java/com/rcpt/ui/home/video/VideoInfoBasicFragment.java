package com.rcpt.ui.home.video;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rcpt.R;
import com.rcpt.base.ui.BaseFragment;
import com.rcpt.base.ui.LazyFragment;
import com.rcpt.bean.VideoInfoBaseBean;
import com.rcpt.databinding.FragmentVideoInfoBasicBinding;
import com.rcpt.mvp.contract.VideoInfoBaseContract;
import com.rcpt.mvp.presenter.VideoInfoBasePresenter;

/**
 * 视频页面的视频基本信息
 */
public class VideoInfoBasicFragment extends BaseFragment<FragmentVideoInfoBasicBinding, VideoInfoBaseContract.View, VideoInfoBasePresenter>
        implements VideoInfoBaseContract.View {

    private static final String CLASS_TYPE_ID = "type_id";

    public static VideoInfoBasicFragment newInstance(String classTypeId) {
        VideoInfoBasicFragment fragment = new VideoInfoBasicFragment();
        Bundle args = new Bundle();
        args.putString(CLASS_TYPE_ID, classTypeId);
        fragment.setArguments(args);
        return fragment;
    }

    private OnDataLoadCallback mOnDataLoadCallback;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mOnDataLoadCallback = (OnDataLoadCallback) context;
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
    protected int getLayoutRes() {
        return R.layout.fragment_video_info_basic;
    }

    @Override
    protected void initViews() {
        mDataBinding.setPresenter(mPresenter);
        VideoInfoActivity activity = (VideoInfoActivity) getActivity();
        activity.pageGone();
        mPresenter.loadPageData();
    }

    @Override
    public String getClassTypeId() {
        return getArguments().getString(CLASS_TYPE_ID);
    }

    @Override
    public void bindPageData(VideoInfoBaseBean baseBean) {
        mOnDataLoadCallback.onDataCallback(baseBean);
        mDataBinding.setBean(baseBean);
    }

    @Override
    protected VideoInfoBasePresenter createPresenter() {
        return new VideoInfoBasePresenter();
    }

    public interface OnDataLoadCallback {
        void onDataCallback(VideoInfoBaseBean baseBean);
    }

}
