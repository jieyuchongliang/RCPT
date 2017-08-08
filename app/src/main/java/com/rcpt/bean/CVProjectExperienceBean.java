package com.rcpt.bean;

/**
 * Created by lvzp on 2017/3/8.
 * 类描述：项目经验的实体类
 */

public class CVProjectExperienceBean {

    private String id;
    private String name;//项目名称
    private String startTime;//开始时间
    private String endTime;//结束时间
    private String describe;//项目描述

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

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }
}
