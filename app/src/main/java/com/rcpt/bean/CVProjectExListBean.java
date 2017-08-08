package com.rcpt.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by lvzp on 2017/3/22.
 * 类描述：
 */

public class CVProjectExListBean implements Parcelable {


    /**
     * cvId : 1
     * proDesc : 项目的描述非常合理
     * projectInfoId : 2
     * updateTime : 1490161234000
     * startTime : 2013-03-01
     * proName : 项目名称
     * endTime : 2016-03-01
     * technology : 涉及的绿树家里
     * tools : 开发工具使用的是Android Studio
     * jobDesc : 项目的职责非常明确
     */

    private String cvId;
    private String proDesc;
    private String projectInfoId;
    private String updateTime;
    private String startTime;
    private String proName;
    private String endTime;
    private String technology;
    private String tools;
    private String jobDesc;

    public String getCvId() {
        return cvId;
    }

    public void setCvId(String cvId) {
        this.cvId = cvId;
    }

    public String getProDesc() {
        return proDesc;
    }

    public void setProDesc(String proDesc) {
        this.proDesc = proDesc;
    }

    public String getProjectInfoId() {
        return projectInfoId;
    }

    public void setProjectInfoId(String projectInfoId) {
        this.projectInfoId = projectInfoId;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getProName() {
        return proName;
    }

    public void setProName(String proName) {
        this.proName = proName;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getTechnology() {
        return technology;
    }

    public void setTechnology(String technology) {
        this.technology = technology;
    }

    public String getTools() {
        return tools;
    }

    public void setTools(String tools) {
        this.tools = tools;
    }

    public String getJobDesc() {
        return jobDesc;
    }

    public void setJobDesc(String jobDesc) {
        this.jobDesc = jobDesc;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.cvId);
        dest.writeString(this.proDesc);
        dest.writeString(this.projectInfoId);
        dest.writeString(this.updateTime);
        dest.writeString(this.startTime);
        dest.writeString(this.proName);
        dest.writeString(this.endTime);
        dest.writeString(this.technology);
        dest.writeString(this.tools);
        dest.writeString(this.jobDesc);
    }

    public CVProjectExListBean() {
    }

    protected CVProjectExListBean(Parcel in) {
        this.cvId = in.readString();
        this.proDesc = in.readString();
        this.projectInfoId = in.readString();
        this.updateTime = in.readString();
        this.startTime = in.readString();
        this.proName = in.readString();
        this.endTime = in.readString();
        this.technology = in.readString();
        this.tools = in.readString();
        this.jobDesc = in.readString();
    }

    public static final Creator<CVProjectExListBean> CREATOR = new Creator<CVProjectExListBean>() {
        @Override
        public CVProjectExListBean createFromParcel(Parcel source) {
            return new CVProjectExListBean(source);
        }

        @Override
        public CVProjectExListBean[] newArray(int size) {
            return new CVProjectExListBean[size];
        }
    };
}
