package com.rcpt.bean;

import com.google.gson.annotations.SerializedName;
import com.rcpt.http.ParamNames;

import java.util.List;

/**
 * Created by lvzp on 2017/5/8.
 * 类描述：
 */

public class VideoListBean {

    private int pageCount;
    private int pageNo;
    @ParamNames("result")
    private List<VideoItemBean> itemBeanList;

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public List<VideoItemBean> getItemBeanList() {
        return itemBeanList;
    }

    public void setItemBeanList(List<VideoItemBean> itemBeanList) {
        this.itemBeanList = itemBeanList;
    }

    public static class VideoItemBean {
        @ParamNames("name")
        private String title;//标题
        @ParamNames("realPrice")
        private String price;//价格
        @ParamNames("originalPrice")
        private String oldPrice;//原件
        @ParamNames("buyNum")
        private String watchNum;//观看人数
        @ParamNames("cover")
        private String logo;//图片
        @ParamNames("detailDesc")
        private String subTitle;//附加标题
        private String createTime;
        private String classTypeId;//课程唯一编号
        private String comId;//商品id
        /**
         * liveFlag : 1
         * buyNum : 1003
         * originalPrice : 1000
         * videoFlag : 1
         * courseHour : 11
         * classTypeId : 4861
         * cover : http://image.yunduoketang.com/course/18162/20170515/7bd46fe6-fc96-4f8f-91de-9cbfe00c1049.jpg
         * detailDesc : <p>API联调课程I-混合课API联调课程I-混合课API联调课程I-混合课API联调课程I-混合课API联调课程I-混合课API联调课程I-混合课API联调课程I-混合课API联调课程I-混合课API联调课程I-混合课API联调课程I-混合课API联调课程I-混合课API联调课程I-混合课API联调课程I-混合课API联调课程I-混合课API联调课程I-混合课</p>
         * <p>
         * itemOneId : 1692
         * itemSecondId : 1693
         * remoteFlag : 0
         * name : API联调课程I-混合课
         * comId : 5122
         * faceFlag : 1
         * realPrice : 99.99
         */

        private int liveFlag;
        private int videoFlag;
        private int remoteFlag;
        private int faceFlag;
        private String itemOneId;

        public String getItemOneId() {
            return itemOneId;
        }

        public void setItemOneId(String itemOneId) {
            this.itemOneId = itemOneId;
        }

        public String getClassTypeId() {
            return classTypeId;
        }

        public void setClassTypeId(String classTypeId) {
            this.classTypeId = classTypeId;
        }

        public String getComId() {
            return comId;
        }

        public void setComId(String comId) {
            this.comId = comId;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getOldPrice() {
            return oldPrice;
        }

        public void setOldPrice(String oldPrice) {
            this.oldPrice = oldPrice;
        }

        public String getWatchNum() {
            return watchNum;
        }

        public void setWatchNum(String watchNum) {
            this.watchNum = watchNum;
        }

        public String getLogo() {
            return logo;
        }

        public void setLogo(String logo) {
            this.logo = logo;
        }

        public String getSubTitle() {
            return subTitle;
        }

        public void setSubTitle(String subTitle) {
            this.subTitle = subTitle;
        }

        public int getLiveFlag() {
            return liveFlag;
        }

        public void setLiveFlag(int liveFlag) {
            this.liveFlag = liveFlag;
        }

        public int getVideoFlag() {
            return videoFlag;
        }

        public void setVideoFlag(int videoFlag) {
            this.videoFlag = videoFlag;
        }

        public int getRemoteFlag() {
            return remoteFlag;
        }

        public void setRemoteFlag(int remoteFlag) {
            this.remoteFlag = remoteFlag;
        }

        public int getFaceFlag() {
            return faceFlag;
        }

        public void setFaceFlag(int faceFlag) {
            this.faceFlag = faceFlag;
        }
    }
}
