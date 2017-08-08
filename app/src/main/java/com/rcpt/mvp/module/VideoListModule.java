package com.rcpt.mvp.module;

import android.content.Context;
import android.text.TextUtils;

import com.rcpt.base.HttpResult;
import com.rcpt.base.mvp.OnDataCallback;
import com.rcpt.base.mvp.OnDataGetCallback;
import com.rcpt.bean.FilterSelectBean;
import com.rcpt.bean.VideoCourseTypeBean;
import com.rcpt.bean.VideoListBean;
import com.rcpt.http.ProgressSubscriber;
import com.rcpt.http.RequestImpl;
import com.rcpt.http.RetrofitManager;
import com.rcpt.http.api.ApiClient;
import com.rcpt.http.api.ApiService;
import com.rcpt.http.utils.NetworkUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import rx.Subscriber;

/**
 * Created by lvzp on 2017/5/8.
 * 类描述：
 */

public class VideoListModule {

    private boolean isEnd;

    private List<FilterSelectBean> mCourseTypeList;//课程类型的筛选列表
    private List<FilterSelectBean> mSubjectTypeList;//课程类型子类型的筛选列表
    private List<FilterSelectBean> mSortModeList;//排序方式的筛选列表
    private List<FilterSelectBean> mTeachModeList;//授课方式的筛选列表

    private List<VideoListBean.VideoItemBean> mListData;
    private Map<String, VideoCourseTypeBean> mVideoCourseData;

    public VideoListModule() {
        mListData = new ArrayList<>();
        mCourseTypeList = new ArrayList<>();
        mSubjectTypeList = new ArrayList<>();
        mSortModeList = new ArrayList<>();
        mTeachModeList = new ArrayList<>();
        addItem(mSortModeList, "sort");
        addItem(mTeachModeList, "teach");

    }

    public void requestVideoTypeList(final OnDataCallback<Boolean> callback) {
        RetrofitManager.toSubscribe(
                ApiClient.getApiService().getVideoCourseTypeList()
                , new Subscriber<HttpResult<List<VideoCourseTypeBean>>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable throwable) {
                        callback.onError(NetworkUtils.getErrorMsg(throwable));
                        callback.onSuccessResult(false);
                    }

                    @Override
                    public void onNext(HttpResult<List<VideoCourseTypeBean>> listHttpResult) {
                        List<VideoCourseTypeBean> data = listHttpResult.getData();
                        mVideoCourseData = changeListData(data);
                        addDataToCourseType(mVideoCourseData);
                        callback.onSuccessResult(true);
                    }
                }
        );
    }

    public void changeCourseChildList(String parentId) {
        VideoCourseTypeBean bean = mVideoCourseData.get(parentId);
        mSubjectTypeList.clear();
        List<VideoCourseTypeBean> childList = bean.getChildList();
        if (childList != null && !childList.isEmpty()) {
            for (VideoCourseTypeBean videoCourseTypeBean : childList) {
                mSubjectTypeList.add(new FilterSelectBean(videoCourseTypeBean.getItemName(), videoCourseTypeBean.getId(), false));
            }
        }
    }

    public void requestVideoList(Context context, String courseTypeId, String sortModeId, String subjectTypeId, String teachModeId, int pageNum, final OnDataGetCallback<List<VideoListBean.VideoItemBean>> callback) {

        RetrofitManager.toSubscribe(
                ApiClient.getApiService().getVideoList(courseTypeId, subjectTypeId, teachModeId, sortModeId, pageNum)
                , new ProgressSubscriber<HttpResult<VideoListBean>>(context, new RequestImpl<HttpResult<VideoListBean>>() {
                    @Override
                    public void onNext(HttpResult<VideoListBean> result) {
                        VideoListBean data = result.getData();
                        if (data != null) {
                            if (mListData == null) {
                                mListData = data.getItemBeanList();
                            } else {
                                mListData.addAll(data.getItemBeanList());
                            }
                            isEnd = data.getPageNo() == data.getPageCount();
                        }
                        callback.onSuccessResult(mListData);
                    }
                })
        );

      /*   mListData = new ArrayList<>();
        for (int i = 0; i < 11; i++) {
            VideoListBean.VideoItemBean bean = new VideoListBean.VideoItemBean();
            bean.setPrice("213");
            bean.setOldPrice("233");
            bean.setTitle("视频标题内容显示" + i);
            bean.setSubTitle("视频简介内容显示" + i);
            bean.setWatchNum("113");
            mListData.add(bean);
        }
        callback.onSuccessResult(mListData);*/
    }

    private void addItem(List<FilterSelectBean> list, String type) {
        if (type != null) {
            switch (type) {
                case "sort":
                    FilterSelectBean newBean = new FilterSelectBean("最新", "new", false);
                    FilterSelectBean hotBean = new FilterSelectBean("最热", "hot", false);
                    FilterSelectBean generalBean = new FilterSelectBean("综合", "general", false);
                    list.add(newBean);
                    list.add(hotBean);
                    list.add(generalBean);
                    break;
                case "teach":
                    FilterSelectBean liveBean = new FilterSelectBean("直播", "live", false);
                    FilterSelectBean videoBean = new FilterSelectBean("录播", "video", false);
                    FilterSelectBean faceBean = new FilterSelectBean("面授", "face", false);
                    FilterSelectBean remoteBean = new FilterSelectBean("其他", "remote", false);
                    list.add(liveBean);
                    list.add(videoBean);
                    list.add(faceBean);
                    list.add(remoteBean);
                    break;
            }
        }
    }

    /**
     * 将平面的一维列表数据，转换为父子关系的列表数据
     *
     * @param data
     */
    private Map<String, VideoCourseTypeBean> changeListData(List<VideoCourseTypeBean> data) {
        List<VideoCourseTypeBean> listData = new ArrayList<>();
        Map<String, VideoCourseTypeBean> parentMap = new TreeMap<>();
        if (data == null || data.isEmpty())
            return parentMap;
        for (VideoCourseTypeBean datum : data) {
            if (TextUtils.isEmpty(datum.getParentId())) {
                parentMap.put(datum.getId(), datum);
            } else {
                listData.add(datum);
            }
        }
        if (listData.isEmpty())
            return parentMap;
        for (VideoCourseTypeBean bean : listData) {
            VideoCourseTypeBean videoCourseTypeBean = parentMap.get(bean.getParentId());
            List<VideoCourseTypeBean> childList = videoCourseTypeBean.getChildList();
            if (childList == null) {
                childList = new ArrayList<>();
                childList.add(bean);
                videoCourseTypeBean.setChildList(childList);
            } else {
                childList.add(bean);
            }
        }
        return parentMap;
    }

    private void addDataToCourseType(Map<String, VideoCourseTypeBean> data) {
        Set<String> keySet = data.keySet();
        for (String s : keySet) {
            VideoCourseTypeBean datum = data.get(s);
            mCourseTypeList.add(new FilterSelectBean(datum.getItemName(), datum.getId(), false));
        }
    }

    public List<VideoListBean.VideoItemBean> getListData() {
        return mListData;
    }

    //课程类型的列表
    public List<FilterSelectBean> getCourseTypeList() {
        return mCourseTypeList;
    }

    //课程子类型的列表
    public List<FilterSelectBean> getSubjectTypeList() {
        return mSubjectTypeList;
    }

    //排序类型列表
    public List<FilterSelectBean> getSortModeList() {
        return mSortModeList;
    }

    //授课方式列表
    public List<FilterSelectBean> getTeachModeList() {
        return mTeachModeList;
    }

    public boolean isEnd() {
        return isEnd;
    }


    public String getTeachingStyle(VideoListBean.VideoItemBean bean) {
        StringBuilder sb = new StringBuilder();
        if (bean.getLiveFlag() == 1) {
            sb.append("直播 ").append(" | ");
        }
        if (bean.getVideoFlag() == 1) {
            sb.append("录播").append(" | ");
        }
        if (bean.getFaceFlag() == 1) {
            sb.append("面试").append(" | ");
        }
        if (bean.getRemoteFlag() == 1) {
            sb.append("其他").append(" | ");
        }
        //将拼接好的文件，去除最后一个|后返回
        return sb.substring(0, sb.length() - 3);
    }

    public String getCourseTypeName(String courseTypeId) {
        if (mCourseTypeList != null && !mCourseTypeList.isEmpty()) {
            for (FilterSelectBean filterSelectBean : mCourseTypeList) {
                if (filterSelectBean.getId().equals(courseTypeId))
                    return filterSelectBean.getValue();
            }
        }
        return "全部课程";
    }

}
