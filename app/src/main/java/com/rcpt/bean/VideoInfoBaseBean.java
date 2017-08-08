package com.rcpt.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by 860617003 on 2017/5/15.
 */

public class VideoInfoBaseBean implements Parcelable {


    /**
     * detailDesc : <p><span style="background-color:rgb(246, 246, 246); font-family:microsoft yahei,stheiti,sans-serif; font-size:18px">API联调课程AAPI联调课程AAPI联调课程AAPI联调课程AAPI联调课程A</span></p>
     * <p>
     * originalPrice : 100
     * teacherName : yuxinapi
     * buyFlag : 0
     * name : API联调课程A
     * user_see_auth : null
     * id : 5110
     * classTypeId : 4849
     * vipFlag : 1
     * realPrice : 0
     */

    private String detailDesc = "";
    private String originalPrice;
    private String teacherName;
    private int buyFlag;
    private String name;
    private String user_see_auth;//免费看权限（ALLUSERS 全部用户,LANDUSERS 登陆用户）
    private String id;
    private String classTypeId;
    private int vipFlag;
    private String realPrice;
    private String cover;

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getDetailDesc() {
        return detailDesc;
    }

    public void setDetailDesc(String detailDesc) {
        this.detailDesc = detailDesc;
    }

    public String getOriginalPrice() {
        return originalPrice;
    }

    public void setOriginalPrice(String originalPrice) {
        this.originalPrice = originalPrice;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public int getBuyFlag() {
        return buyFlag;
    }

    public void setBuyFlag(int buyFlag) {
        this.buyFlag = buyFlag;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUser_see_auth() {
        return user_see_auth;
    }

    public void setUser_see_auth(String user_see_auth) {
        this.user_see_auth = user_see_auth;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getClassTypeId() {
        return classTypeId;
    }

    public void setClassTypeId(String classTypeId) {
        this.classTypeId = classTypeId;
    }

    public int getVipFlag() {
        return vipFlag;
    }

    public void setVipFlag(int vipFlag) {
        this.vipFlag = vipFlag;
    }

    public String getRealPrice() {
        return realPrice;
    }

    public void setRealPrice(String realPrice) {
        this.realPrice = realPrice;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.detailDesc);
        dest.writeString(this.originalPrice);
        dest.writeString(this.teacherName);
        dest.writeInt(this.buyFlag);
        dest.writeString(this.name);
        dest.writeString(this.user_see_auth);
        dest.writeString(this.id);
        dest.writeString(this.classTypeId);
        dest.writeInt(this.vipFlag);
        dest.writeString(this.realPrice);
    }

    public VideoInfoBaseBean() {
    }

    protected VideoInfoBaseBean(Parcel in) {
        this.detailDesc = in.readString();
        this.originalPrice = in.readString();
        this.teacherName = in.readString();
        this.buyFlag = in.readInt();
        this.name = in.readString();
        this.user_see_auth = in.readString();
        this.id = in.readString();
        this.classTypeId = in.readString();
        this.vipFlag = in.readInt();
        this.realPrice = in.readString();
    }

    public static final Creator<VideoInfoBaseBean> CREATOR = new Creator<VideoInfoBaseBean>() {
        @Override
        public VideoInfoBaseBean createFromParcel(Parcel source) {
            return new VideoInfoBaseBean(source);
        }

        @Override
        public VideoInfoBaseBean[] newArray(int size) {
            return new VideoInfoBaseBean[size];
        }
    };
}
