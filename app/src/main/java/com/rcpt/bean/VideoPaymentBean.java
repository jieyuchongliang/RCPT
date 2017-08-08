package com.rcpt.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by 860617003 on 2017/5/18.
 */

public class VideoPaymentBean implements Parcelable {

    private String commodityId;//商品id
    private String courseName;
    private String courseInfo;//课程详情
    private String courseId;//课程id
    private String coursePrice;
    private String subject_id;//所属类别id
    private String teaching_style;//授课类型
    private String subject;//所属类别

    public String getSubject_id() {
        return subject_id;
    }

    public void setSubject_id(String subject_id) {
        this.subject_id = subject_id;
    }

    public String getTeaching_style() {
        return teaching_style;
    }

    public void setTeaching_style(String teaching_style) {
        this.teaching_style = teaching_style;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getCommodityId() {
        return commodityId;
    }

    public void setCommodityId(String commodityId) {
        this.commodityId = commodityId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCourseInfo() {
        return courseInfo;
    }

    public void setCourseInfo(String courseInfo) {
        this.courseInfo = courseInfo;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String getCoursePrice() {
        return coursePrice;
    }

    public void setCoursePrice(String coursePrice) {
        this.coursePrice = coursePrice;

    }

    public VideoPaymentBean() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.commodityId);
        dest.writeString(this.courseName);
        dest.writeString(this.courseInfo);
        dest.writeString(this.courseId);
        dest.writeString(this.coursePrice);
        dest.writeString(this.subject_id);
        dest.writeString(this.teaching_style);
        dest.writeString(this.subject);
    }

    private VideoPaymentBean(Parcel in) {
        this.commodityId = in.readString();
        this.courseName = in.readString();
        this.courseInfo = in.readString();
        this.courseId = in.readString();
        this.coursePrice = in.readString();
        this.subject_id = in.readString();
        this.teaching_style = in.readString();
        this.subject = in.readString();
    }

    public static final Creator<VideoPaymentBean> CREATOR = new Creator<VideoPaymentBean>() {
        @Override
        public VideoPaymentBean createFromParcel(Parcel source) {
            return new VideoPaymentBean(source);
        }

        @Override
        public VideoPaymentBean[] newArray(int size) {
            return new VideoPaymentBean[size];
        }
    };
}
