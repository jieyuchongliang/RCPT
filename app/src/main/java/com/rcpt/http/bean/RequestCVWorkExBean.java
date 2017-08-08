package com.rcpt.http.bean;

/**
 * Created by lvzp on 2017/3/21.
 * 类描述：用于上传到网络中的参数实体类
 * 工作经验
 */

public class RequestCVWorkExBean {

    private String userId;//①：用户Id
    private String cvId;// ②：简历Id（
    private String infoId;// ③：工作经历信息Id（
    private String comName;//   ④：企业名称（
    private String comNatureId;//    ⑤：企业性质（
    private String comIndId;//⑥：行业类别（
    private String department;// ⑦：部门（
    private String job;//      ⑧：职位（
    private String workBeginDate;//  ⑨：开始时间（
    private String workEndDate;//
    private String jobDesc;//   ⑫：职责描述（
    private String underling;   //下属人数

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

    public String getComName() {
        return comName;
    }

    public void setComName(String comName) {
        this.comName = comName;
    }

    public String getComNatureId() {
        return comNatureId;
    }

    public void setComNatureId(String comNatureId) {
        this.comNatureId = comNatureId;
    }

    public String getComIndId() {
        return comIndId;
    }

    public void setComIndId(String comIndId) {
        this.comIndId = comIndId;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getWorkBeginDate() {
        return workBeginDate;
    }

    public void setWorkBeginDate(String workBeginDate) {
        this.workBeginDate = workBeginDate;
    }

    public String getWorkEndDate() {
        return workEndDate;
    }

    public void setWorkEndDate(String workEndDate) {
        this.workEndDate = workEndDate;
    }

    public String getJobDesc() {
        return jobDesc;
    }

    public void setJobDesc(String jobDesc) {
        this.jobDesc = jobDesc;
    }

    public String getUnderling() {
        return underling;
    }

    public void setUnderling(String underling) {
        this.underling = underling;
    }
}
