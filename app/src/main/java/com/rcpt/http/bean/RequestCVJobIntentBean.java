package com.rcpt.http.bean;

/**
 * Created by lvzp on 2017/3/24.
 * 类描述：
 */

public class RequestCVJobIntentBean {

    private String userId;// "①：用户Id（
    private String cvId;//    ②：简历Id（
    private String state;//  ③：工作状态（
    private String province;//        ④：期望工作地点省份（
    private String city;//   ⑤：期望工作地点城市（
    private String workType;//    ⑥：期望工作性质（
    private String hopeSalaryId;//  ⑬：期望工资（
    private String expectedIndustry;//期望行业
    private String expectedPosition;//期望职位
    private String cvName;//简历名称

    public String getCvName() {
        return cvName;
    }

    public void setCvName(String cvName) {
        this.cvName = cvName;
    }

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

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getWorkType() {
        return workType;
    }

    public void setWorkType(String workType) {
        this.workType = workType;
    }

    public String getHopeSalaryId() {
        return hopeSalaryId;
    }

    public void setHopeSalaryId(String hopeSalaryId) {
        this.hopeSalaryId = hopeSalaryId;
    }

    public String getExpectedIndustry() {
        return expectedIndustry;
    }

    public void setExpectedIndustry(String expectedIndustry) {
        this.expectedIndustry = expectedIndustry;
    }

    public String getExpectedPosition() {
        return expectedPosition;
    }

    public void setExpectedPosition(String expectedPosition) {
        this.expectedPosition = expectedPosition;
    }
}
