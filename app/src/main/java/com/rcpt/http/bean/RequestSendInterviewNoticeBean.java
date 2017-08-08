package com.rcpt.http.bean;

/**
 * Created by lvzp on 2017/5/3.
 * 类描述：用于上传时发送面试邀请的实体类
 */

public class RequestSendInterviewNoticeBean {

    private String companyId;//①：公司id（必须）
    private String interviewRecordId;// ②：职位id（ 必须）
    private String recruiters;// ③：申请者ID（必须）
    private String startTime;//   ④：开始时间（ 必须）
    private String endTime;//   ⑤：结束时间（必须）
    private String description;//  ⑥：说明描述（必须）

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getInterviewRecordId() {
        return interviewRecordId;
    }

    public void setInterviewRecordId(String interviewRecordId) {
        this.interviewRecordId = interviewRecordId;
    }

    public String getRecruiters() {
        return recruiters;
    }

    public void setRecruiters(String recruiters) {
        this.recruiters = recruiters;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
