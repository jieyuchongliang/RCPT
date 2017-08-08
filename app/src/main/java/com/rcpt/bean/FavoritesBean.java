package com.rcpt.bean;

import java.util.List;

/**
 * Created by lvzp on 2017/3/7.
 * 类描述：收藏夹的实体类
 */

public class FavoritesBean {

    private String totalPage;
    private boolean isNext;
    private List<FavoritesListBean> favoriteslist;

    public String getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(String totalPage) {
        this.totalPage = totalPage;
    }

    public boolean isNext() {
        return isNext;
    }

    public void setNext(boolean next) {
        isNext = next;
    }

    public List<FavoritesListBean> getFavoriteslist() {
        return favoriteslist;
    }

    public void setFavoriteslist(List<FavoritesListBean> favoriteslist) {
        this.favoriteslist = favoriteslist;
    }

    public static class FavoritesListBean {


        /**
         * recordId : 4
         * positionName : 大家好（职位邀请测试）
         * salaryVal : 4001-6000元/月
         * companyId : eed4c1381d6b5d46
         * education : 0
         * workCity : 长沙市
         * companyName : QQQQQQ有限公司
         * publishDate : 2017-03-28
         * salary : 4
         * educationVal : 高中及以下
         * recruitmentInfo : 6
         */
        private int delFlg;
        private String recordId;
        private String positionName;
        private String salaryVal;
        private String companyId;
        private String education;
        private String workCity;
        private String companyName;
        private String publishDate;
        private String salary;
        private String educationVal;
        private String recruitmentInfo;

        public int getDelFlg() {
            return delFlg;
        }

        public void setDelFlg(int delFlg) {
            this.delFlg = delFlg;
        }

        public String getPositionName() {
            return positionName;
        }

        public void setPositionName(String positionName) {
            this.positionName = positionName;
        }

        public String getSalaryVal() {
            return salaryVal;
        }

        public void setSalaryVal(String salaryVal) {
            this.salaryVal = salaryVal;
        }

        public String getCompanyId() {
            return companyId;
        }

        public void setCompanyId(String companyId) {
            this.companyId = companyId;
        }

        public String getEducation() {
            return education;
        }

        public void setEducation(String education) {
            this.education = education;
        }

        public String getWorkCity() {
            return workCity;
        }

        public void setWorkCity(String workCity) {
            this.workCity = workCity;
        }

        public String getCompanyName() {
            return companyName;
        }

        public void setCompanyName(String companyName) {
            this.companyName = companyName;
        }

        public String getPublishDate() {
            return publishDate;
        }

        public void setPublishDate(String publishDate) {
            this.publishDate = publishDate;
        }

        public String getSalary() {
            return salary;
        }

        public void setSalary(String salary) {
            this.salary = salary;
        }

        public String getEducationVal() {
            return educationVal;
        }

        public void setEducationVal(String educationVal) {
            this.educationVal = educationVal;
        }

        public String getRecordId() {
            return recordId;
        }

        public void setRecordId(String recordId) {
            this.recordId = recordId;
        }

        public String getRecruitmentInfo() {
            return recruitmentInfo;
        }

        public void setRecruitmentInfo(String recruitmentInfo) {
            this.recruitmentInfo = recruitmentInfo;
        }
    }

}
