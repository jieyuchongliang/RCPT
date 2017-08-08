package com.rcpt.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by lvzp on 2017/3/21.
 * 类描述：
 */

public class CVWorkExListBean implements Parcelable {


    /**
     * cvId : 1
     * underling : 1
     * jobDesc : 发撒发撒旦发啊阿斯顿萨大幅
     * comNatureCode : 02
     * workBeginDate : 2017-02-28
     * comIndId : 计算机软件
     * workInfoId : 1
     * workEndDate : 2017-03-20
     * comName : 济南北海软件有限公司
     * comIndCode : 01
     * department : 技术部
     * job : Android开发工程师
     * comNatureId : 民营企业
     */

    private String cvId;
    private String underling;
    private String jobDesc;
    private String comNatureCode;
    private String workBeginDate;
    private String comIndId;
    private String workInfoId;
    private String workEndDate;
    private String comName;
    private String comIndCode;
    private String department;
    private String job;
    private String comNatureId;

    public String getCvId() {
        return cvId;
    }

    public void setCvId(String cvId) {
        this.cvId = cvId;
    }

    public String getUnderling() {
        return underling;
    }

    public void setUnderling(String underling) {
        this.underling = underling;
    }

    public String getJobDesc() {
        return jobDesc;
    }

    public void setJobDesc(String jobDesc) {
        this.jobDesc = jobDesc;
    }

    public String getComNatureCode() {
        return comNatureCode;
    }

    public void setComNatureCode(String comNatureCode) {
        this.comNatureCode = comNatureCode;
    }

    public String getWorkBeginDate() {
        return workBeginDate;
    }

    public void setWorkBeginDate(String workBeginDate) {
        this.workBeginDate = workBeginDate;
    }

    public String getComIndId() {
        return comIndId;
    }

    public void setComIndId(String comIndId) {
        this.comIndId = comIndId;
    }

    public String getWorkInfoId() {
        return workInfoId;
    }

    public void setWorkInfoId(String workInfoId) {
        this.workInfoId = workInfoId;
    }

    public String getWorkEndDate() {
        return workEndDate;
    }

    public void setWorkEndDate(String workEndDate) {
        this.workEndDate = workEndDate;
    }

    public String getComName() {
        return comName;
    }

    public void setComName(String comName) {
        this.comName = comName;
    }

    public String getComIndCode() {
        return comIndCode;
    }

    public void setComIndCode(String comIndCode) {
        this.comIndCode = comIndCode;
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

    public String getComNatureId() {
        return comNatureId;
    }

    public void setComNatureId(String comNatureId) {
        this.comNatureId = comNatureId;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.cvId);
        dest.writeString(this.underling);
        dest.writeString(this.jobDesc);
        dest.writeString(this.comNatureCode);
        dest.writeString(this.workBeginDate);
        dest.writeString(this.comIndId);
        dest.writeString(this.workInfoId);
        dest.writeString(this.workEndDate);
        dest.writeString(this.comName);
        dest.writeString(this.comIndCode);
        dest.writeString(this.department);
        dest.writeString(this.job);
        dest.writeString(this.comNatureId);
    }

    public CVWorkExListBean() {
    }

    protected CVWorkExListBean(Parcel in) {
        this.cvId = in.readString();
        this.underling = in.readString();
        this.jobDesc = in.readString();
        this.comNatureCode = in.readString();
        this.workBeginDate = in.readString();
        this.comIndId = in.readString();
        this.workInfoId = in.readString();
        this.workEndDate = in.readString();
        this.comName = in.readString();
        this.comIndCode = in.readString();
        this.department = in.readString();
        this.job = in.readString();
        this.comNatureId = in.readString();
    }

    public static final Creator<CVWorkExListBean> CREATOR = new Creator<CVWorkExListBean>() {
        @Override
        public CVWorkExListBean createFromParcel(Parcel source) {
            return new CVWorkExListBean(source);
        }

        @Override
        public CVWorkExListBean[] newArray(int size) {
            return new CVWorkExListBean[size];
        }
    };
}
