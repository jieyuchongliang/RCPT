package com.rcpt.bean;

/**
 * Created by lvzp on 2017/3/8.
 * 类描述：工作/实习经验
 */

public class CVWorkHistoryBean {

    private String id;
    private String name;//公司名称
    private String startTime;//开始时间
    private String endTime;//结束时间
    private String tradeMode;//所在行业
    private String jobName;//岗位名称
    private String money;//职位薪资
    private String describe;//工作描述

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getTradeMode() {
        return tradeMode;
    }

    public void setTradeMode(String tradeMode) {
        this.tradeMode = tradeMode;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }
}
