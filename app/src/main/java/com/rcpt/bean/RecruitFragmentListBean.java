package com.rcpt.bean;

import java.util.List;

/**
 * Created by lvzp on 2017/3/10.
 * 类描述：招聘Fragment中的列表实体类
 */

public class RecruitFragmentListBean {

    private String id;
    private String title;
    private List<SubBean> subList;

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

    public List<SubBean> getSubList() {
        return subList;
    }

    public void setSubList(List<SubBean> subList) {
        this.subList = subList;
    }

    public static class SubBean {
        private String id;
        private String title;
        private String subTitle;
        private String image;
        private String type;
        private boolean isTitle;
        private String updateTimeStamp;

        public boolean isTitle() {
            return isTitle;
        }

        public void setTitle(boolean title) {
            isTitle = title;
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

        public String getSubTitle() {
            return subTitle;
        }

        public void setSubTitle(String subTitle) {
            this.subTitle = subTitle;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getUpdateTimeStamp() {
            return updateTimeStamp;
        }

        public void setUpdateTimeStamp(String updateTimeStamp) {
            this.updateTimeStamp = updateTimeStamp;
        }
    }


}
