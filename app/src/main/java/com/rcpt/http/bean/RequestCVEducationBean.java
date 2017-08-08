package com.rcpt.http.bean;

/**
 * Created by lvzp on 2017/3/21.
 * 类描述：用于上传到网络中的参数实体类
 * 教育背景
 */

public class RequestCVEducationBean {

    private String userId;//用户Id
    private String cvId;//简历Id
    private String infoId;//教育信息Id
    private String schName;//       ④：学校名称
    private String startT;// ⑤：开始时间
    private String endT;//  ⑥：结束时间（
    private String specialty;//  ⑦：专业（
    private String education;// ⑧：学历（
    private String osStudy;//  ⑨：海外教育经历（

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getCvId() {
        return cvId;
    }

    public void setCvId(String cvId) {
        this.cvId = cvId;
    }

    public String getInfoId() {
        return infoId;
    }

    public void setInfoId(String infoId) {
        this.infoId = infoId;
    }

    public String getSchName() {
        return schName;
    }

    public void setSchName(String schName) {
        this.schName = schName;
    }

    public String getStartT() {
        return startT;
    }

    public void setStartT(String startT) {
        this.startT = startT;
    }

    public String getEndT() {
        return endT;
    }

    public void setEndT(String endT) {
        this.endT = endT;
    }

    public String getSpecialty() {
        return specialty;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getOsStudy() {
        return osStudy;
    }

    public void setOsStudy(String osStudy) {
        this.osStudy = osStudy;
    }
}
