package com.rcpt.http.bean;

/**
 * Created by lvzp on 2017/4/17.
 * 类描述：用于上传时发送职位邀请的实体类
 */

public class RequestSendJobInvitationBean {

    private String cvId;//简历Id（  必须）
    private String cvUserId;//   ②：用户Id（ 必须）
    private String ids;//   ③：招聘信息Id（必须）
    private String companyId;//  ④：招聘公司Id（ 必须）
    private String applicationTime;// ⑤：应聘开始时间(必须）
    private String applicationEndTime;//  ⑤：应聘结束时间(必须）
    private String remarks;//  ⑥：备注(必须）

    public String getCvId() {
        return cvId;
    }

    public void setCvId(String cvId) {
        this.cvId = cvId;
    }

    public String getCvUserId() {
        return cvUserId;
    }

    public void setCvUserId(String cvUserId) {
        this.cvUserId = cvUserId;
    }

    public String getIds() {
        return ids;
    }

    public void setIds(String ids) {
        this.ids = ids;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getApplicationTime() {
        return applicationTime;
    }

    public void setApplicationTime(String applicationTime) {
        this.applicationTime = applicationTime;
    }

    public String getApplicationEndTime() {
        return applicationEndTime;
    }

    public void setApplicationEndTime(String applicationEndTime) {
        this.applicationEndTime = applicationEndTime;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}
