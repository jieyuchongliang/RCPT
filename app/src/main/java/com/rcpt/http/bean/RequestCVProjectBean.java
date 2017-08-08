package com.rcpt.http.bean;

/**
 * Created by lvzp on 2017/3/21.
 * 类描述：用于上传到网络中的参数实体类
 * 项目经验
 */

public class RequestCVProjectBean {

    private String userId;//"①：用户Id（
    private String cvId;// ②：简历Id（
    private String infoId;//   ③：项目经验信息Id（
    private String proName;//      ④：项目名称（
    private String startTime;//    ⑤：开始时间（
    private String endTime;//     ⑥：结束时间（
    private String tools;//    ⑦：开发工具（
    private String technology;//    ⑧：涉及技术（
    private String proDesc;//     ⑨：项目描述（
    private String jobDesc;//   ⑩：职责描述（

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

    public String getProName() {
        return proName;
    }

    public void setProName(String proName) {
        this.proName = proName;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getTools() {
        return tools;
    }

    public void setTools(String tools) {
        this.tools = tools;
    }

    public String getTechnology() {
        return technology;
    }

    public void setTechnology(String technology) {
        this.technology = technology;
    }

    public String getProDesc() {
        return proDesc;
    }

    public void setProDesc(String proDesc) {
        this.proDesc = proDesc;
    }

    public String getJobDesc() {
        return jobDesc;
    }

    public void setJobDesc(String jobDesc) {
        this.jobDesc = jobDesc;
    }
}
