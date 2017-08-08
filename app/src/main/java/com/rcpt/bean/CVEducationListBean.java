package com.rcpt.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by lvzp on 2017/3/21.
 * 类描述：
 */

public class CVEducationListBean implements Parcelable {


    /**
     * cvId : 1
     * eduInfoId : 1
     * specialty : 物联网应用技术
     * education : 大专
     * overseasStudy : 有
     * startTime : 2017-03-14
     * endTime : 2017-03-23
     * schoolName : 淄博职业学院
     */

    private String cvId;
    private String eduInfoId;
    private String specialty;
    private String education;
    private String osStudy;
    private String startT;
    private String endT;
    private String schName;
    /**
     * cvId : 1
     * eduInfoId : 5
     * osStudyCode : 1
     * updateTime : 1490254103000
     * educationCode : 3
     */

    private String osStudyCode;
    private long updateTime;
    private String educationCode;

    public String getCvId() {
        return cvId;
    }

    public void setCvId(String cvId) {
        this.cvId = cvId;
    }

    public String getEduInfoId() {
        return eduInfoId;
    }

    public void setEduInfoId(String eduInfoId) {
        this.eduInfoId = eduInfoId;
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

    public String getSchName() {
        return schName;
    }

    public void setSchName(String schName) {
        this.schName = schName;
    }

    public String getOsStudyCode() {
        return osStudyCode;
    }

    public void setOsStudyCode(String osStudyCode) {
        this.osStudyCode = osStudyCode;
    }

    public long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(long updateTime) {
        this.updateTime = updateTime;
    }

    public String getEducationCode() {
        return educationCode;
    }

    public void setEducationCode(String educationCode) {
        this.educationCode = educationCode;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.cvId);
        dest.writeString(this.eduInfoId);
        dest.writeString(this.specialty);
        dest.writeString(this.education);
        dest.writeString(this.osStudy);
        dest.writeString(this.startT);
        dest.writeString(this.endT);
        dest.writeString(this.schName);
        dest.writeString(this.osStudyCode);
        dest.writeLong(this.updateTime);
        dest.writeString(this.educationCode);
    }

    public CVEducationListBean() {
    }

    protected CVEducationListBean(Parcel in) {
        this.cvId = in.readString();
        this.eduInfoId = in.readString();
        this.specialty = in.readString();
        this.education = in.readString();
        this.osStudy = in.readString();
        this.startT = in.readString();
        this.endT = in.readString();
        this.schName = in.readString();
        this.osStudyCode = in.readString();
        this.updateTime = in.readLong();
        this.educationCode = in.readString();
    }

    public static final Creator<CVEducationListBean> CREATOR = new Creator<CVEducationListBean>() {
        @Override
        public CVEducationListBean createFromParcel(Parcel source) {
            return new CVEducationListBean(source);
        }

        @Override
        public CVEducationListBean[] newArray(int size) {
            return new CVEducationListBean[size];
        }
    };
}
