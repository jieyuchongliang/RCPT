package com.rcpt.bean;

import java.util.List;

/**
 * Created by 860116021 on 2017/4/14.
 */

public class InstitutionDetailBean {

    /**
     * institutionInfo : {"businessLicense":"uploadFiles/registerImg/e9d9e79297e34b7780a1e703e29b1ca0.jpg","registrationTime":"2017-03-15","zipCode":"","website":"","mail":"dsd45@163.com","provinceCode":16,"cityCode":170,"cityVal":"济南市","industry":"01,05,06,09,08","employment":"","institutionsName":"机构","provinceVal":"山东省","areaCode":1726,"legalRepresentative":"wsf","addressDetail":"长清区","registeredCapital":"100","areaVal":"济南市","organizeCode":"123456789012345678","contact":"","logo":"uploadFiles/registerImg/fb23457324724ccc97d9b5aa23d1f655.jpg","industryValue":"计算机软件,计算机硬件,通信/电信/网络设备,网络通信,信息安全","tel":"15205555555","institutionsId":"73a2b3265bf4d65c","introduction":""}
     * teacherlist : [{"teacherId":93,"teacherName":"王","graduate":"山东大学","teacherLevel":"老师","teacherPicture":"uploadFiles/registerImg/f5756b6efbbe41b4a22f5918f42bdc31.jpg","institutionsId":"73a2b3265bf4d65c","experience":"sdfasd"}]
     * enviromentlist : [{"environment_id":"1"}]
     * courselist : [{"period":"10","courseName":"日语","teacher":"王","coursePath":"uploadFiles/registerImg/640c5f593e6841c4bed3c4eaa391541c.jpg","institutionsId":"73a2b3265bf4d65c","courseId":119,"courseTime":"90"}]
     */

    private InstitutionInfoBean institutionInfo;
    private List<TeacherlistBean> teacherlist;
    private List<EnviromentlistBean> enviromentlist;
    private List<CourselistBean> courselist;

    public InstitutionInfoBean getInstitutionInfo() {
        return institutionInfo;
    }

    public void setInstitutionInfo(InstitutionInfoBean institutionInfo) {
        this.institutionInfo = institutionInfo;
    }

    public List<TeacherlistBean> getTeacherlist() {
        return teacherlist;
    }

    public void setTeacherlist(List<TeacherlistBean> teacherlist) {
        this.teacherlist = teacherlist;
    }

    public List<EnviromentlistBean> getEnviromentlist() {
        return enviromentlist;
    }

    public void setEnviromentlist(List<EnviromentlistBean> enviromentlist) {
        this.enviromentlist = enviromentlist;
    }

    public List<CourselistBean> getCourselist() {
        return courselist;
    }

    public void setCourselist(List<CourselistBean> courselist) {
        this.courselist = courselist;
    }

    public static class InstitutionInfoBean {
        /**
         * businessLicense : uploadFiles/registerImg/e9d9e79297e34b7780a1e703e29b1ca0.jpg
         * registrationTime : 2017-03-15
         * zipCode :
         * website :
         * mail : dsd45@163.com
         * provinceCode : 16
         * cityCode : 170
         * cityVal : 济南市
         * industry : 01,05,06,09,08
         * employment :
         * institutionsName : 机构
         * provinceVal : 山东省
         * areaCode : 1726
         * legalRepresentative : wsf
         * addressDetail : 长清区
         * registeredCapital : 100
         * areaVal : 济南市
         * organizeCode : 123456789012345678
         * contact :
         * logo : uploadFiles/registerImg/fb23457324724ccc97d9b5aa23d1f655.jpg
         * industryValue : 计算机软件,计算机硬件,通信/电信/网络设备,网络通信,信息安全
         * tel : 15205555555
         * institutionsId : 73a2b3265bf4d65c
         * introduction :
         */

        private String businessLicense;
        private String registrationTime;
        private String zipCode;
        private String website;
        private String mail;
        private String provinceCode;
        private String cityCode;
        private String cityVal;
        private String industry;
        private String employment;
        private String institutionsName;
        private String provinceVal;
        private String areaCode;
        private String legalRepresentative;
        private String addressDetail;
        private String registeredCapital;
        private String areaVal;
        private String organizeCode;
        private String contact;
        private String logo;
        private String industryValue;
        private String tel;
        private String institutionsId;
        private String introduction;

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

        public String getProvinceCode() {
            return provinceCode;
        }

        public void setProvinceCode(String provinceCode) {
            this.provinceCode = provinceCode;
        }

        public String getCityCode() {
            return cityCode;
        }

        public void setCityCode(String cityCode) {
            this.cityCode = cityCode;
        }

        public String getCityVal() {
            return cityVal;
        }

        public void setCityVal(String cityVal) {
            this.cityVal = cityVal;
        }

        public String getIndustry() {
            return industry;
        }

        public void setIndustry(String industry) {
            this.industry = industry;
        }

        public String getEmployment() {
            return employment;
        }

        public void setEmployment(String employment) {
            this.employment = employment;
        }

        public String getInstitutionsName() {
            return institutionsName;
        }

        public void setInstitutionsName(String institutionsName) {
            this.institutionsName = institutionsName;
        }

        public String getProvinceVal() {
            return provinceVal;
        }

        public void setProvinceVal(String provinceVal) {
            this.provinceVal = provinceVal;
        }

        public String getAreaCode() {
            return areaCode;
        }

        public void setAreaCode(String areaCode) {
            this.areaCode = areaCode;
        }

        public String getLegalRepresentative() {
            return legalRepresentative;
        }

        public void setLegalRepresentative(String legalRepresentative) {
            this.legalRepresentative = legalRepresentative;
        }

        public String getAddressDetail() {
            return addressDetail;
        }

        public void setAddressDetail(String addressDetail) {
            this.addressDetail = addressDetail;
        }

        public String getRegisteredCapital() {
            return registeredCapital;
        }

        public void setRegisteredCapital(String registeredCapital) {
            this.registeredCapital = registeredCapital;
        }

        public String getAreaVal() {
            return areaVal;
        }

        public void setAreaVal(String areaVal) {
            this.areaVal = areaVal;
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

        public String getIndustryValue() {
            return industryValue;
        }

        public void setIndustryValue(String industryValue) {
            this.industryValue = industryValue;
        }

        public String getTel() {
            return tel;
        }

        public void setTel(String tel) {
            this.tel = tel;
        }

        public String getInstitutionsId() {
            return institutionsId;
        }

        public void setInstitutionsId(String institutionsId) {
            this.institutionsId = institutionsId;
        }

        public String getIntroduction() {
            return introduction;
        }

        public void setIntroduction(String introduction) {
            this.introduction = introduction;
        }
    }

    public static class TeacherlistBean {
        /**
         * teacherId : 93
         * teacherName : 王
         * graduate : 山东大学
         * teacherLevel : 老师
         * teacherPicture : uploadFiles/registerImg/f5756b6efbbe41b4a22f5918f42bdc31.jpg
         * institutionsId : 73a2b3265bf4d65c
         * experience : sdfasd
         */

        private String teacherId;
        private String teacherName;
        private String graduate;
        private String teacherLevel;
        private String teacherPicture;
        private String institutionsId;
        private String experience;

        public String getTeacherId() {
            return teacherId;
        }

        public void setTeacherId(String teacherId) {
            this.teacherId = teacherId;
        }

        public String getTeacherName() {
            return teacherName;
        }

        public void setTeacherName(String teacherName) {
            this.teacherName = teacherName;
        }

        public String getGraduate() {
            return graduate;
        }

        public void setGraduate(String graduate) {
            this.graduate = graduate;
        }

        public String getTeacherLevel() {
            return teacherLevel;
        }

        public void setTeacherLevel(String teacherLevel) {
            this.teacherLevel = teacherLevel;
        }

        public String getTeacherPicture() {
            return teacherPicture;
        }

        public void setTeacherPicture(String teacherPicture) {
            this.teacherPicture = teacherPicture;
        }

        public String getInstitutionsId() {
            return institutionsId;
        }

        public void setInstitutionsId(String institutionsId) {
            this.institutionsId = institutionsId;
        }

        public String getExperience() {
            return experience;
        }

        public void setExperience(String experience) {
            this.experience = experience;
        }
    }

    public static class EnviromentlistBean {

        private String environmentId;
        private String institutionId;
        private String environmentPicture;

        public String getEnvironmentId() {
            return environmentId;
        }

        public void setEnvironmentId(String environmentId) {
            this.environmentId = environmentId;
        }

        public String getInstitutionId() {
            return institutionId;
        }

        public void setInstitutionId(String institutionId) {
            this.institutionId = institutionId;
        }

        public String getEnvironmentPicture() {
            return environmentPicture;
        }

        public void setEnvironmentPicture(String environmentPicture) {
            this.environmentPicture = environmentPicture;
        }
    }

    public static class CourselistBean {
        /**
         * period : 10
         * courseName : 日语
         * teacher : 王
         * coursePath : uploadFiles/registerImg/640c5f593e6841c4bed3c4eaa391541c.jpg
         * institutionsId : 73a2b3265bf4d65c
         * courseId : 119
         * courseTime : 90
         */

        private String period;
        private String courseName;
        private String teacher;
        private String coursePath;
        private String institutionsId;
        private String courseId;
        private String courseTime;

        public String getPeriod() {
            return period;
        }

        public void setPeriod(String period) {
            this.period = period;
        }

        public String getCourseName() {
            return courseName;
        }

        public void setCourseName(String courseName) {
            this.courseName = courseName;
        }

        public String getTeacher() {
            return teacher;
        }

        public void setTeacher(String teacher) {
            this.teacher = teacher;
        }

        public String getCoursePath() {
            return coursePath;
        }

        public void setCoursePath(String coursePath) {
            this.coursePath = coursePath;
        }

        public String getInstitutionsId() {
            return institutionsId;
        }

        public void setInstitutionsId(String institutionsId) {
            this.institutionsId = institutionsId;
        }

        public String getCourseId() {
            return courseId;
        }

        public void setCourseId(String courseId) {
            this.courseId = courseId;
        }

        public String getCourseTime() {
            return courseTime;
        }

        public void setCourseTime(String courseTime) {
            this.courseTime = courseTime;
        }
    }
}
