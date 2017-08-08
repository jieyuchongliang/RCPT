package com.rcpt.mvp.presenter;

import android.text.TextUtils;
import android.view.View;

import com.rcpt.R;
import com.rcpt.base.mvp.OnDataCallback;
import com.rcpt.base.mvp.OnDataGetCallback;
import com.rcpt.bean.FilterSelectBean;
import com.rcpt.bean.VideoListBean;
import com.rcpt.mvp.contract.VideoListContract;
import com.rcpt.mvp.module.VideoListModule;
import com.rcpt.ui.home.video.VideoInfoActivity;

import java.util.List;

/**
 * Created by lvzp on 2017/5/8.
 * 类描述：
 */

public class VideoListPresenter extends ListFilterPresenter<VideoListContract.View> implements VideoListContract.Presenter {

    private VideoListModule mModule;

    private String mCourseTypeId;//课程一级标题id
    private String mSubjectTypeId;//课程子类型id
    private String mSortModeId;//排序的id
    private String mTeachModeId;//授课方式的id

    private boolean isSuccess;//是否加载成功

    @Override
    public void attach(VideoListContract.View view) {
        super.attach(view);
        mModule = new VideoListModule();
        getView().initRecyclerView();
    }

    /**
     * 加载筛选器列表
     */
    @Override
    public void loadFilterList() {
        mModule.requestVideoTypeList(new OnDataCallback<Boolean>() {
            @Override
            public void onSuccessResult(Boolean data) {
                isSuccess = true;
            }

            @Override
            public void onError(String errMsg) {
                getView().showToast(errMsg);
            }
        });
    }


    /**
     * 加载更多的数据
     * 只需要根据相应的页码加载相应的数据，无需关心刷新和加载更多
     *
     * @param page
     */
    @Override
    public void onLoadMore(int page) {
        mModule.requestVideoList(getView().getContext(), mCourseTypeId, mSortModeId, mSubjectTypeId, mTeachModeId, page, new OnDataGetCallback<List<VideoListBean.VideoItemBean>>() {
            @Override
            public void onSuccessResult(List<VideoListBean.VideoItemBean> data) {
                if (!isSuccess)
                    loadFilterList();
                getView().updateIsEnd(mModule.isEnd());
                getView().bindListData(data);
            }
        });
    }

    @Override
    public void loadListData() {
        onLoadMore(1);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.bg_gray) {
            mOldSelectFilterItem.setChecked(false);
            getView().layoutStartOut();
            getView().closeGrayBg();
            return;
        }
        super.onClick(v);
    }

    @Override
    public void onItemClick(View imageView, int position) {
        VideoListBean.VideoItemBean videoItemBean = mModule.getListData().get(position);
        VideoInfoActivity.actionStart(getView().getContext(), videoItemBean.getClassTypeId(), mModule.getCourseTypeName(videoItemBean.getItemOneId()), videoItemBean.getItemOneId(), mModule.getTeachingStyle(videoItemBean));
    }

    /**
     * 当筛选类型的View处于选中状态时的回调
     *
     * @param view 当前处于选中状态的筛选类型
     * @return 是否要跳过后面方法的执行, true为跳过，false不跳过
     */
    @Override
    protected boolean onFilterItemCheckClick(View view) {
        List<FilterSelectBean> filterList = null;
        switch (view.getId()) {
            case R.id.cbx_video_course_type://课程类型
                if (isSuccess)
                    filterList = mModule.getCourseTypeList();
                else
                    return true;
                break;
            case R.id.cbx_video_subject_type://子课程类型
                if (TextUtils.isEmpty(mCourseTypeId)) {
                    getView().showToast("请点击全部课程选择课程类型");
                    mOldSelectFilterItem.setChecked(false);
                    return true;
                }
                filterList = mModule.getSubjectTypeList();
                break;
            case R.id.cbx_video_sort_mode://排序方式
                filterList = mModule.getSortModeList();
                break;
            case R.id.cbx_video_teach_mode://授课方式
                filterList = mModule.getTeachModeList();
                break;
        }
        getView().bindFilterListData(filterList);
        return false;
    }

    /**
     * 清空列表重新加载数据
     */
    @Override
    protected void loadListDataWithClean() {
        if (mModule.getListData() != null)
            mModule.getListData().clear();
        onLoadMore(1);
    }

    /**
     * 当筛选列表的Item被点击时进行时进行的回调
     *
     * @param filterItem 筛选的类型View
     * @param id         选择列表的属性id
     */
    @Override
    protected void onFilterListItemClick(View filterItem, String id) {
        switch (filterItem.getId()) {
            case R.id.cbx_video_course_type://课程类型
                mCourseTypeId = id;
                mModule.changeCourseChildList(mCourseTypeId);
                mSubjectTypeId = null;//当课程类型改变后，需要将子类型重置
                getView().resetSubjectTypeFilterName();
                return;
            case R.id.cbx_video_subject_type://子课程类型
                mSubjectTypeId = id;
                return;
            case R.id.cbx_video_sort_mode://排序方式
                mSortModeId = id;
                break;
            case R.id.cbx_video_teach_mode://授课方式
                mTeachModeId = id;
                break;
        }
    }

    /**
     * 清除所有选中的筛选标志
     */
    @Override
    protected void cleanAllFlag() {
        mCourseTypeId = null;
        mSubjectTypeId = null;
        mSortModeId = null;
        mTeachModeId = null;
    }
}
