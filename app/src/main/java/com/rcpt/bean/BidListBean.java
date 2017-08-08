package com.rcpt.bean;

import java.util.List;

/**
 * Created by lvzp on 2017/2/24.
 * 类描述：
 */

public class BidListBean {

    private String totalPage;
    private boolean isNext;
    private List<ProjectlistBean> Projectlist;
    private List<personnelProlistBean> personnelProlist;

    public String getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(String totalPage) {
        this.totalPage = totalPage;
    }

    public boolean isIsNext() {
        return isNext;
    }

    public void setIsNext(boolean isNext) {
        this.isNext = isNext;
    }

    public List<ProjectlistBean> getProjectlist() {
        return Projectlist;
    }

    public void setProjectlist(List<ProjectlistBean> Projectlist) {
        this.Projectlist = Projectlist;
    }

    public List<personnelProlistBean> getPersonnelProlist() {
        return personnelProlist;
    }

    public void setPersonnelProlist(List<personnelProlistBean> personnelProlist) {
        this.personnelProlist = personnelProlist;
    }

    public static class ProjectlistBean {
        /**
         * validityPeriod : 2018-03-13
         * bidedCount : 3
         * rateProject : 30
         * price : 5
         * projectMonth : 12
         * publishCompanyId : 44203df462f07a78
         * publishDate : 17-03-13
         * bidCount : 10
         * publishCompanyName : 政府申报二
         * projectId : 265
         * projectTitle : 人员需求标题一
         * remainingTime : 331
         */

        private String validityPeriod;
        private String bidedCount;
        private double rateProject;
        private String price;
        private String projectMonth;
        private String publishCompanyId;
        private String publishDate;
        private String bidCount;
        private String publishCompanyName;
        private String projectId;
        private String projectTitle;
        private String remainingTime;

        public String getValidityPeriod() {
            return validityPeriod;
        }

        public void setValidityPeriod(String validityPeriod) {
            this.validityPeriod = validityPeriod;
        }

        public String getBidedCount() {
            return bidedCount;
        }

        public void setBidedCount(String bidedCount) {
            this.bidedCount = bidedCount;
        }

        public double getRateProject() {
            return rateProject;
        }

        public void setRateProject(double rateProject) {
            this.rateProject = rateProject;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getProjectMonth() {
            return projectMonth;
        }

        public void setProjectMonth(String projectMonth) {
            this.projectMonth = projectMonth;
        }

        public String getPublishCompanyId() {
            return publishCompanyId;
        }

        public void setPublishCompanyId(String publishCompanyId) {
            this.publishCompanyId = publishCompanyId;
        }

        public String getPublishDate() {
            return publishDate;
        }

        public void setPublishDate(String publishDate) {
            this.publishDate = publishDate;
        }

        public String getBidCount() {
            return bidCount;
        }

        public void setBidCount(String bidCount) {
            this.bidCount = bidCount;
        }

        public String getPublishCompanyName() {
            return publishCompanyName;
        }

        public void setPublishCompanyName(String publishCompanyName) {
            this.publishCompanyName = publishCompanyName;
        }

        public String getProjectId() {
            return projectId;
        }

        public void setProjectId(String projectId) {
            this.projectId = projectId;
        }

        public String getProjectTitle() {
            return projectTitle;
        }

        public void setProjectTitle(String projectTitle) {
            this.projectTitle = projectTitle;
        }

        public String getRemainingTime() {
            return remainingTime;
        }

        public void setRemainingTime(String remainingTime) {
            this.remainingTime = remainingTime;
        }
    }

    public static class personnelProlistBean{

        private String validityPeriod;
        private String bidedCount;
        private double rateProject;
        private String personnelId;
        private String projectMonth;
        private String publishCompanyId;
        private String publishDate;
        private String requirementPersonelCount;
        private String bidCount;
        private String personnelTitle;
        private String publishCompanyName;
        private String remainingTime;

        public String getValidityPeriod() {
            return validityPeriod;
        }

        public void setValidityPeriod(String validityPeriod) {
            this.validityPeriod = validityPeriod;
        }

        public String getBidedCount() {
            return bidedCount;
        }

        public void setBidedCount(String bidedCount) {
            this.bidedCount = bidedCount;
        }

        public double getRateProject() {
            return rateProject;
        }

        public void setRateProject(double rateProject) {
            this.rateProject = rateProject;
        }

        public String getPersonnelId() {
            return personnelId;
        }

        public void setPersonnelId(String personnelId) {
            this.personnelId = personnelId;
        }

        public String getProjectMonth() {
            return projectMonth;
        }

        public void setProjectMonth(String projectMonth) {
            this.projectMonth = projectMonth;
        }

        public String getPublishCompanyId() {
            return publishCompanyId;
        }

        public void setPublishCompanyId(String publishCompanyId) {
            this.publishCompanyId = publishCompanyId;
        }

        public String getPublishDate() {
            return publishDate;
        }

        public void setPublishDate(String publishDate) {
            this.publishDate = publishDate;
        }

        public String getRequirementPersonelCount() {
            return requirementPersonelCount;
        }

        public void setRequirementPersonelCount(String requirementPersonelCount) {
            this.requirementPersonelCount = requirementPersonelCount;
        }

        public String getBidCount() {
            return bidCount;
        }

        public void setBidCount(String bidCount) {
            this.bidCount = bidCount;
        }

        public String getPersonnelTitle() {
            return personnelTitle;
        }

        public void setPersonnelTitle(String personnelTitle) {
            this.personnelTitle = personnelTitle;
        }

        public String getPublishCompanyName() {
            return publishCompanyName;
        }

        public void setPublishCompanyName(String publishCompanyName) {
            this.publishCompanyName = publishCompanyName;
        }

        public String getRemainingTime() {
            return remainingTime;
        }

        public void setRemainingTime(String remainingTime) {
            this.remainingTime = remainingTime;
        }
    }
}
