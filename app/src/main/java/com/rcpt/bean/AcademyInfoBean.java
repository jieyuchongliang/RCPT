package com.rcpt.bean;

import java.util.List;

/**
 * Created by lvzp on 2017/3/29.
 * 类描述：
 */

public class AcademyInfoBean {


    /**
     * addressProvince : 山东省
     * schoolLog : uploadFiles/registerImg/04acfeec64ed496cbc1ed51bbb5a83b8.jpg
     * website : www.baidu.com
     * creationTime : 2011-03-09
     * mail : 123456789@qq.com
     * academicianNum : 1
     * keySubjects : 1
     * schoolType : 管理类
     * doctorNum : 1
     * contactTel : 18865507331
     * schoolNameEn : ss
     * belonging : 中央部委所属高校
     * representativeBuilding : uploadFiles/registerImg/0ae92a1e8c844b569397364fed5a6b36.jpg
     * addressDetail : 123456789
     * contact : a
     * schoolDetail : 测试
     * schoolName : 123456789
     * masterNum : 1
     * studentNum : 111
     * addressCity : 济南市
     */
    private SchoolInfoBean schoolinfo;
    /**
     * graduateinfo : {"yixue":[],"yishuxue":[],"lishixue":[],"faxue":[],"guanlixue":[],"lixue":[],"jingjixue":[],"jiaoyuxue":[],"zhexue":[],"wenxue":[],"gongxue":[],"schoolpitcure":[{"file_path":"uploadFiles/schoolShowImg/f2ec892c10934756a941a066eb28f38b.jpg"},{"file_path":"uploadFiles/schoolShowImg/c1abef788cd5477aa6bb9aa4734f7705.jpg"},{"file_path":"uploadFiles/schoolShowImg/f917e185b48d4404a61b01965a7983f5.jpg"},{"file_path":"uploadFiles/schoolShowImg/2dd6e4d064514b8e8bdd7e8e6b15d3d1.jpg"}]}
     */

    private GraduateinfoBean graduateinfo;

    public SchoolInfoBean getSchoolinfo() {
        return schoolinfo;
    }

    public void setSchoolinfo(SchoolInfoBean schoolinfo) {
        this.schoolinfo = schoolinfo;
    }

    public GraduateinfoBean getGraduateinfo() {
        return graduateinfo;
    }

    public void setGraduateinfo(GraduateinfoBean graduateinfo) {
        this.graduateinfo = graduateinfo;
    }

    public static class SchoolInfoBean {
        private String addressProvince;
        private String schoolLog;
        private String website;
        private String creationTime;
        private String mail;
        private String academicianNum;
        private String keySubjects;
        private String schoolType;
        private String doctorNum;
        private String contactTel;
        private String schoolNameEn;
        private String belonging;
        private String representativeBuilding;
        private String addressDetail;
        private String contact;
        private String schoolDetail;
        private String schoolName;
        private String masterNum;
        private String studentNum;
        private String addressCity;
        private String schoolSituation;

        public String getSchoolSituation() {
            return schoolSituation;
        }

        public void setSchoolSituation(String schoolSituation) {
            this.schoolSituation = schoolSituation;
        }

        public String getAddressProvince() {
            return addressProvince;
        }

        public void setAddressProvince(String addressProvince) {
            this.addressProvince = addressProvince;
        }

        public String getSchoolLog() {
            return schoolLog;
        }

        public void setSchoolLog(String schoolLog) {
            this.schoolLog = schoolLog;
        }

        public String getWebsite() {
            return website;
        }

        public void setWebsite(String website) {
            this.website = website;
        }

        public String getCreationTime() {
            return creationTime;
        }

        public void setCreationTime(String creationTime) {
            this.creationTime = creationTime;
        }

        public String getMail() {
            return mail;
        }

        public void setMail(String mail) {
            this.mail = mail;
        }

        public String getAcademicianNum() {
            return academicianNum;
        }

        public void setAcademicianNum(String academicianNum) {
            this.academicianNum = academicianNum;
        }

        public String getKeySubjects() {
            return keySubjects;
        }

        public void setKeySubjects(String keySubjects) {
            this.keySubjects = keySubjects;
        }

        public String getSchoolType() {
            return schoolType;
        }

        public void setSchoolType(String schoolType) {
            this.schoolType = schoolType;
        }

        public String getDoctorNum() {
            return doctorNum;
        }

        public void setDoctorNum(String doctorNum) {
            this.doctorNum = doctorNum;
        }

        public String getContactTel() {
            return contactTel;
        }

        public void setContactTel(String contactTel) {
            this.contactTel = contactTel;
        }

        public String getSchoolNameEn() {
            return schoolNameEn;
        }

        public void setSchoolNameEn(String schoolNameEn) {
            this.schoolNameEn = schoolNameEn;
        }

        public String getBelonging() {
            return belonging;
        }

        public void setBelonging(String belonging) {
            this.belonging = belonging;
        }

        public String getRepresentativeBuilding() {
            return representativeBuilding;
        }

        public void setRepresentativeBuilding(String representativeBuilding) {
            this.representativeBuilding = representativeBuilding;
        }

        public String getAddressDetail() {
            return addressDetail;
        }

        public void setAddressDetail(String addressDetail) {
            this.addressDetail = addressDetail;
        }

        public String getContact() {
            return contact;
        }

        public void setContact(String contact) {
            this.contact = contact;
        }

        public String getSchoolDetail() {
            return schoolDetail;
        }

        public void setSchoolDetail(String schoolDetail) {
            this.schoolDetail = schoolDetail;
        }

        public String getSchoolName() {
            return schoolName;
        }

        public void setSchoolName(String schoolName) {
            this.schoolName = schoolName;
        }

        public String getMasterNum() {
            return masterNum;
        }

        public void setMasterNum(String masterNum) {
            this.masterNum = masterNum;
        }

        public String getStudentNum() {
            return studentNum;
        }

        public void setStudentNum(String studentNum) {
            this.studentNum = studentNum;
        }

        public String getAddressCity() {
            return addressCity;
        }

        public void setAddressCity(String addressCity) {
            this.addressCity = addressCity;
        }
    }


    public static class GraduateinfoBean {
        private List<SchoolpitcureBean> schoolpitcure;

        public List<SchoolpitcureBean> getSchoolpitcure() {
            return schoolpitcure;
        }

        public void setSchoolpitcure(List<SchoolpitcureBean> schoolpitcure) {
            this.schoolpitcure = schoolpitcure;
        }

        public static class SchoolpitcureBean {
            /**
             * file_path : uploadFiles/schoolShowImg/f2ec892c10934756a941a066eb28f38b.jpg
             */

            private String file_path;

            public String getFile_path() {
                return file_path;
            }

            public void setFile_path(String file_path) {
                this.file_path = file_path;
            }
        }
    }
}
