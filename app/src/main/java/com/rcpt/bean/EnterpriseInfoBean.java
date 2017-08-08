package com.rcpt.bean;

import java.util.List;

/**
 * Created by lvzp on 2017/3/27.
 * 类描述：
 */

public class EnterpriseInfoBean {


    /**
     * jobList : [{"positionName":"生产助理","workPlaceCity":"济南市","recruitmentInfoId":1,"userId":"e93db86b574da0cb","updateTimestamp":"2017-02-23"}]
     * businessinfo : {"businessLicense":"uploadFiles/registerImg/74d2c7776dee4d32aa6d73e18a68c676.png","registrationTime":"2017-02-23","addressProvince":"山东省","companyIntroduction":"济南东泰兴工艺品有限公司是一家专业生产、出口各种工艺镜框、相框的工厂。公司产品主要出口欧盟、南非、中东、北美及日本、韩国。年产值200多万美元。","isQuotedCompany":"1","zipCode":"250108","website":"http://dongtaixing.cn.china.cn/","mail":"david@jndongtaixing.com","companyName":"济南东泰兴工艺品有限公司","scale":40,"userId":"e93db86b574da0cb","contactTel":"18769785311","enterpriseNature":"民营企业","addressDetail":"济南市历城区荷花路街道孟家庄村东","registeredCapital":50,"organizeCode":"91370112589911623T","contact":"潘东升","logo":"uploadFiles/registerImg/d73412318cce420790a58a219d74c162.png","serviceDistribution":"","addressCity":"济南市"}
     */

    private BusinessinfoBean businessinfo;
    private List<JobListBean> jobList;
    private List<ComPicUpLoadList> comPictUploadList;
    public List<ComPicUpLoadList> getComPictUploadList() {
        return comPictUploadList;
    }

    public void setComPictUploadList(List<ComPicUpLoadList> comPictUploadList) {
        this.comPictUploadList = comPictUploadList;
    }
    public BusinessinfoBean getBusinessinfo() {
        return businessinfo;
    }

    public void setBusinessinfo(BusinessinfoBean businessinfo) {
        this.businessinfo = businessinfo;
    }

    public List<JobListBean> getJobList() {
        return jobList;
    }

    public void setJobList(List<JobListBean> jobList) {
        this.jobList = jobList;
    }

    public static class BusinessinfoBean {
        /**
         * businessLicense : uploadFiles/registerImg/74d2c7776dee4d32aa6d73e18a68c676.png
         * registrationTime : 2017-02-23
         * addressProvince : 山东省
         * companyIntroduction : 济南东泰兴工艺品有限公司是一家专业生产、出口各种工艺镜框、相框的工厂。公司产品主要出口欧盟、南非、中东、北美及日本、韩国。年产值200多万美元。
         * isQuotedCompany : 1
         * zipCode : 250108
         * website : http://dongtaixing.cn.china.cn/
         * mail : david@jndongtaixing.com
         * companyName : 济南东泰兴工艺品有限公司
         * scale : 40
         * userId : e93db86b574da0cb
         * contactTel : 18769785311
         * enterpriseNature : 民营企业
         * addressDetail : 济南市历城区荷花路街道孟家庄村东
         * registeredCapital : 50
         * organizeCode : 91370112589911623T
         * contact : 潘东升
         * logo : uploadFiles/registerImg/d73412318cce420790a58a219d74c162.png
         * serviceDistribution :
         * addressCity : 济南市
         * industry: "计算机软件,互联网/电子商务,IT服务（系统/数据/维护）,电子技术/半导体/集成电路,网络通信"
         */

        private String businessLicense;
        private String registrationTime;
        private String addressProvince;
        private String companyIntroduction;
        private String isQuotedCompany;
        private String zipCode;
        private String website;
        private String mail;
        private String companyName;
        private String scale;
        private String userId;
        private String contactTel;
        private String enterpriseNature;
        private String addressDetail;
        private String registeredCapital;
        private String organizeCode;
        private String contact;
        private String logo;
        private String serviceDistribution;
        private String addressCity;
        private String industry;

        public String getIndustry() {
            return industry;
        }

        public void setIndustry(String industry) {
            this.industry = industry;
        }

        public String getBusinessLicense() {
            return businessLicense;
        }

        public void setBusinessLicense(String businessLicense) {
            this.businessLicense = businessLicense;
        }

        public String getRegistrationTime() {
            return registrationTime;
        }

        public void setRegistrationTime(String registrationTime) {
            this.registrationTime = registrationTime;
        }

        public String getAddressProvince() {
            return addressProvince;
        }

        public void setAddressProvince(String addressProvince) {
            this.addressProvince = addressProvince;
        }

        public String getCompanyIntroduction() {
            return companyIntroduction;
        }

        public void setCompanyIntroduction(String companyIntroduction) {
            this.companyIntroduction = companyIntroduction;
        }

        public String getIsQuotedCompany() {
            return isQuotedCompany;
        }

        public void setIsQuotedCompany(String isQuotedCompany) {
            this.isQuotedCompany = isQuotedCompany;
        }

        public String getZipCode() {
            return zipCode;
        }

        public void setZipCode(String zipCode) {
            this.zipCode = zipCode;
        }

        public String getWebsite() {
            return website;
        }

        public void setWebsite(String website) {
            this.website = website;
        }

        public String getMail() {
            return mail;
        }

        public void setMail(String mail) {
            this.mail = mail;
        }

        public String getCompanyName() {
            return companyName;
        }

        public void setCompanyName(String companyName) {
            this.companyName = companyName;
        }

        public String getScale() {
            return scale;
        }

        public void setScale(String scale) {
            this.scale = scale;
        }

        public String getRegisteredCapital() {
            return registeredCapital;
        }

        public void setRegisteredCapital(String registeredCapital) {
            this.registeredCapital = registeredCapital;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getContactTel() {
            return contactTel;
        }

        public void setContactTel(String contactTel) {
            this.contactTel = contactTel;
        }

        public String getEnterpriseNature() {
            return enterpriseNature;
        }

        public void setEnterpriseNature(String enterpriseNature) {
            this.enterpriseNature = enterpriseNature;
        }

        public String getAddressDetail() {
            return addressDetail;
        }

        public void setAddressDetail(String addressDetail) {
            this.addressDetail = addressDetail;
        }


        public String getOrganizeCode() {
            return organizeCode;
        }

        public void setOrganizeCode(String organizeCode) {
            this.organizeCode = organizeCode;
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

        public String getServiceDistribution() {
            return serviceDistribution;
        }

        public void setServiceDistribution(String serviceDistribution) {
            this.serviceDistribution = serviceDistribution;
        }

        public String getAddressCity() {
            return addressCity;
        }

        public void setAddressCity(String addressCity) {
            this.addressCity = addressCity;
        }
    }

    public static class ComPicUpLoadList{
        private String environmentPicture;
        private String companyId;
        private String name;
        private int id;

        public String getEnvironmentPicture() {
            return environmentPicture;
        }

        public void setEnvironmentPicture(String environmentPicture) {
            this.environmentPicture = environmentPicture;
        }

        public String getCompanyId() {
            return companyId;
        }

        public void setCompanyId(String companyId) {
            this.companyId = companyId;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }
    }
    public static class JobListBean {
        /**
         * positionName : 生产助理
         * workPlaceCity : 济南市
         * recruitmentInfoId : 1
         * userId : e93db86b574da0cb
         * updateTimestamp : 2017-02-23
         */

        private String positionName;
        private String workPlaceCity;
        private String recruitmentInfoId;
        private String userId;
        private String updateTimestamp;

        public String getPositionName() {
            return positionName;
        }

        public void setPositionName(String positionName) {
            this.positionName = positionName;
        }

        public String getWorkPlaceCity() {
            return workPlaceCity;
        }

        public void setWorkPlaceCity(String workPlaceCity) {
            this.workPlaceCity = workPlaceCity;
        }

        public String getRecruitmentInfoId() {
            return recruitmentInfoId;
        }

        public void setRecruitmentInfoId(String recruitmentInfoId) {
            this.recruitmentInfoId = recruitmentInfoId;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getUpdateTimestamp() {
            return updateTimestamp;
        }

        public void setUpdateTimestamp(String updateTimestamp) {
            this.updateTimestamp = updateTimestamp;
        }
    }
}
