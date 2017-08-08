package com.rcpt.bean;

import com.rcpt.http.ParamNames;

import java.util.List;

/**
 * Created by lvzp on 2017/5/9.
 * 类描述：
 */

public class VideoInfoCatalogBean {
    /**
     * chapterName : API联调课程A
     * lectures : [{"freeFlag":2,"lectureName":"API联调课程A-试听","lastStudy":null,"webVideoDomain":null,"storageType":"VIDEO_STORAGE_TYPE_CC","id":2586,"videoTime":"00:00:30","videoSize":38.417,"webVideoId":null,"studyStatus":1},{"freeFlag":1,"lectureName":"API联调课程A-免费","lastStudy":null,"webVideoDomain":null,"storageType":"VIDEO_STORAGE_TYPE_CC","id":2587,"videoTime":"00:00:30","videoSize":38.417,"webVideoId":null,"studyStatus":1},{"freeFlag":0,"lectureName":"API联调课程A-正常","lastStudy":null,"webVideoDomain":null,"storageType":"VIDEO_STORAGE_TYPE_CC","id":2588,"videoTime":"00:00:30","videoSize":38.417,"webVideoId":null,"studyStatus":1},{"freeFlag":0,"lectureName":"API联调课程A-Flash","lastStudy":null,"webVideoDomain":null,"storageType":null,"id":2589,"videoTime":"00:00:00","videoSize":0,"webVideoId":null,"studyStatus":1},{"freeFlag":0,"lectureName":"API联调课程A-语音","lastStudy":null,"webVideoDomain":null,"storageType":null,"id":2590,"videoTime":"00:00:00","videoSize":0,"webVideoId":null,"studyStatus":1}]
     * letv : {"letv_uuid":"xmlk3ytpne"}
     * moduleId : 4749
     * userId : 835220
     */
    @ParamNames("chapterName")
    private String titleName;
    private LetvBean letv;
    private String moduleId;
    private String userId;
    private List<VideoInfoContentBean> lectures;

    public String getTitleName() {
        return titleName;
    }

    public void setTitleName(String titleName) {
        this.titleName = titleName;
    }

    public LetvBean getLetv() {
        return letv;
    }

    public void setLetv(LetvBean letv) {
        this.letv = letv;
    }

    public String getModuleId() {
        return moduleId;
    }

    public void setModuleId(String moduleId) {
        this.moduleId = moduleId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public List<VideoInfoContentBean> getLectures() {
        return lectures;
    }

    public void setLectures(List<VideoInfoContentBean> lectures) {
        this.lectures = lectures;
    }

    public static class LetvBean {
        /**
         * letv_uuid : xmlk3ytpne
         */

        private String letv_uuid;
        private String letv_user_id;
        private String letv_api_key;

        public String getLetv_uuid() {
            return letv_uuid;
        }

        public void setLetv_uuid(String letv_uuid) {
            this.letv_uuid = letv_uuid;
        }

        public String getLetv_user_id() {
            return letv_user_id;
        }

        public void setLetv_user_id(String letv_user_id) {
            this.letv_user_id = letv_user_id;
        }

        public String getLetv_api_key() {
            return letv_api_key;
        }

        public void setLetv_api_key(String letv_api_key) {
            this.letv_api_key = letv_api_key;
        }
    }

    public static class VideoInfoContentBean {
        /**
         * freeFlag : 2
         * lectureName : API联调课程A-试听
         * lastStudy : null
         * webVideoDomain : null
         * storageType : VIDEO_STORAGE_TYPE_CC
         * id : 2586
         * videoTime : 00:00:30
         * videoSize : 38.417
         * webVideoId : null
         * studyStatus : 1
         */
        @ParamNames("freeFlag")
        private int chargeStatus;//视频免费标记（0或null-不不免费 1-免费 2-试听 ）
        @ParamNames("lectureName")
        private String contentName;
        private Object lastStudy;
        private Object webVideoDomain;
        private String storageType;
        private String id;
        private String videoTime;
        private double videoSize;
        private String webVideoId;
        private int studyStatus;
        private int overFlowTime;

        public int getOverFlowTime() {
            return overFlowTime;
        }

        public void setOverFlowTime(int overFlowTime) {
            this.overFlowTime = overFlowTime;
        }

        public int getChargeStatus() {
            return chargeStatus;
        }

        public void setChargeStatus(int chargeStatus) {
            this.chargeStatus = chargeStatus;
        }

        public String getContentName() {
            return contentName;
        }

        public void setContentName(String contentName) {
            this.contentName = contentName;
        }

        public Object getLastStudy() {
            return lastStudy;
        }

        public void setLastStudy(Object lastStudy) {
            this.lastStudy = lastStudy;
        }

        public Object getWebVideoDomain() {
            return webVideoDomain;
        }

        public void setWebVideoDomain(Object webVideoDomain) {
            this.webVideoDomain = webVideoDomain;
        }

        public String getStorageType() {
            return storageType;
        }

        public void setStorageType(String storageType) {
            this.storageType = storageType;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getVideoTime() {
            return videoTime;
        }

        public void setVideoTime(String videoTime) {
            this.videoTime = videoTime;
        }

        public double getVideoSize() {
            return videoSize;
        }

        public void setVideoSize(double videoSize) {
            this.videoSize = videoSize;
        }

        public String getWebVideoId() {
            return webVideoId;
        }

        public void setWebVideoId(String webVideoId) {
            this.webVideoId = webVideoId;
        }

        public int getStudyStatus() {
            return studyStatus;
        }

        public void setStudyStatus(int studyStatus) {
            this.studyStatus = studyStatus;
        }
    }

   /* private String titleName;
    private List<VideoInfoContentBean> contentList;

    public String getTitleName() {
        return titleName;
    }

    public void setTitleName(String titleName) {
        this.titleName = titleName;
    }

    public List<VideoInfoContentBean> getContentList() {
        return contentList;
    }

    public void setContentList(List<VideoInfoContentBean> contentList) {
        this.contentList = contentList;
    }

    public static class VideoInfoContentBean {

        private String contentName;
        private int chargeStatus;

        public String getContentName() {
            return contentName;
        }

        public void setContentName(String contentName) {
            this.contentName = contentName;
        }

        public int getChargeStatus() {
            return chargeStatus;
        }

        public void setChargeStatus(int chargeStatus) {
            this.chargeStatus = chargeStatus;
        }
    }*/

}
