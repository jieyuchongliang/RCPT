package com.rcpt.bean;

import java.util.List;

/**
 * Created by 860116021 on 2017/4/11.
 * 培训机构
 */

public class InstituteBean {


    private String totalPage;
    private boolean isNext;
    private List<InstitutionListBean> institutionList;
    private List<PicBean> pic;
    private List<NoticeBean> notice;

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

    public List<InstitutionListBean> getInstitutionList() {
        return institutionList;
    }

    public void setInstitutionList(List<InstitutionListBean> institutionList) {
        this.institutionList = institutionList;
    }

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

    public static class InstitutionListBean {

        private String institutionId;
        private String areaVal;
        private String institutionPicture;
        private String cityVal;
        private String institutionName;
        private String contact;
        private String logo;
        private String tel;
        private String industry;
        private String industryValue;
        private String introduction;
        private String provinceVal;

        public String getInstitutionId() {
            return institutionId;
        }

        public void setInstitutionId(String institutionId) {
            this.institutionId = institutionId;
        }

        public String getAreaVal() {
            return areaVal;
        }

        public void setAreaVal(String areaVal) {
            this.areaVal = areaVal;
        }

        public String getInstitutionPicture() {
            return institutionPicture;
        }

        public void setInstitutionPicture(String institutionPicture) {
            this.institutionPicture = institutionPicture;
        }

        public String getCityVal() {
            return cityVal;
        }

        public void setCityVal(String cityVal) {
            this.cityVal = cityVal;
        }

        public String getInstitutionName() {
            return institutionName;
        }

        public void setInstitutionName(String institutionName) {
            this.institutionName = institutionName;
        }

        public String getContact() {
            return contact;
        }

        public void setContact(String contact) {
            this.contact = contact;
        }

        public String getLogo() {
            return logo;
        }

        public void setLogo(String logo) {
            this.logo = logo;
        }

        public String getTel() {
            return tel;
        }

        public void setTel(String tel) {
            this.tel = tel;
        }

        public String getIndustry() {
            return industry;
        }

        public void setIndustry(String industry) {
            this.industry = industry;
        }

        public String getIndustryValue() {
            return industryValue;
        }

        public void setIndustryValue(String industryValue) {
            this.industryValue = industryValue;
        }

        public String getIntroduction() {
            return introduction;
        }

        public void setIntroduction(String introduction) {
            this.introduction = introduction;
        }

        public String getProvinceVal() {
            return provinceVal;
        }

        public void setProvinceVal(String provinceVal) {
            this.provinceVal = provinceVal;
        }
    }

    public static class PicBean{

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

    public static class NoticeBean{

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
}
