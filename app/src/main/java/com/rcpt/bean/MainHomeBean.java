package com.rcpt.bean;

import java.util.List;

/**
 * Created by lvzp on 2017/3/13.
 * 类描述：
 */

public class MainHomeBean {


    private List<PicBean> pic;
    private List<NoticeBean> notice;
    private List<CompanyNewsBean> companyNews;

    public List<PicBean> getPic() {
        return pic;
    }

    public void setPic(List<PicBean> pic) {
        this.pic = pic;
    }

    public List<NoticeBean> getNotice() {
        return notice;
    }

    public void setNotice(List<NoticeBean> notice) {
        this.notice = notice;
    }

    public List<CompanyNewsBean> getCompanyNews() {
        return companyNews;
    }

    public void setCompanyNews(List<CompanyNewsBean> companyNews) {
        this.companyNews = companyNews;
    }

    public static class PicBean {
        /**
         * filePath : uploadFiles/organizationImg//dcd7d92f90904db4952ae84edaa82202.jpg
         * picId : 40
         */

        private String filePath;
        private String picId;

        public String getFilePath() {
            return filePath;
        }

        public void setFilePath(String filePath) {
            this.filePath = filePath;
        }

        public String getPicId() {
            return picId;
        }

        public void setPicId(String picId) {
            this.picId = picId;
        }
    }

    public static class NoticeBean {
        /**
         * newsId : 197
         * title : testereeeee
         */

        private String newsId;
        private String title;

        public String getNewsId() {
            return newsId;
        }

        public void setNewsId(String newsId) {
            this.newsId = newsId;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }

    public static class CompanyNewsBean {
        /**
         * companyId : fcbed17968931bc9
         * id : 77
         * title : fd
         * updateTimeStamp : 2017-03-13
         * newsSources : sadfsadf
         */

        private String companyId;
        private String id;
        private String title;
        private String updateTimeStamp;
        private String newsSources;

        public String getCompanyId() {
            return companyId;
        }

        public void setCompanyId(String companyId) {
            this.companyId = companyId;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getUpdateTimeStamp() {
            return updateTimeStamp;
        }

        public void setUpdateTimeStamp(String updateTimeStamp) {
            this.updateTimeStamp = updateTimeStamp;
        }

        public String getNewsSources() {
            return newsSources;
        }

        public void setNewsSources(String newsSources) {
            this.newsSources = newsSources;
        }
    }
}
