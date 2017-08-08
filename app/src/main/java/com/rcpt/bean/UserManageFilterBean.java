package com.rcpt.bean;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;

/**
 * Created by 860617003 on 2017/6/8.
 */

public class UserManageFilterBean implements Parcelable {

    private OnDataChangeListener mDataChangeListener;
    private String groupName;
    private String userName;
    private String address;
    private String userStatus;
    private String userType;

    private String userStatusId;
    private String userTypeId;
    private String provinceId;
    private String cityId;


    public void setOnDataChangeListener(OnDataChangeListener dataChangeListener) {
        this.mDataChangeListener = dataChangeListener;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
        if (mDataChangeListener != null)
            mDataChangeListener.onDataChange(this);
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
        if (mDataChangeListener != null)
            mDataChangeListener.onDataChange(this);
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
        if (mDataChangeListener != null)
            mDataChangeListener.onDataChange(this);
    }

    public String getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(String userStatus) {
        this.userStatus = userStatus;
        if (mDataChangeListener != null)
            mDataChangeListener.onDataChange(this);
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
        if (mDataChangeListener != null)
            mDataChangeListener.onDataChange(this);
    }

    public String getUserStatusId() {
        return userStatusId;
    }

    public void setUserStatusId(String userStatusId) {
        this.userStatusId = userStatusId;
        if (mDataChangeListener != null)
            mDataChangeListener.onDataChange(this);
    }

    public String getUserTypeId() {
        return userTypeId;
    }

    public void setUserTypeId(String userTypeId) {
        this.userTypeId = userTypeId;
        if (mDataChangeListener != null)
            mDataChangeListener.onDataChange(this);
    }

    public String getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(String provinceId) {
        this.provinceId = provinceId;
        if (mDataChangeListener != null)
            mDataChangeListener.onDataChange(this);
    }

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
        if (mDataChangeListener != null)
            mDataChangeListener.onDataChange(this);
    }

    public boolean isEmpty() {
        return TextUtils.isEmpty(groupName)
                && TextUtils.isEmpty(userName)
                && TextUtils.isEmpty(address)
                && TextUtils.isEmpty(userStatus)
                && TextUtils.isEmpty(userType);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.groupName);
        dest.writeString(this.userName);
        dest.writeString(this.address);
        dest.writeString(this.userStatus);
        dest.writeString(this.userType);
        dest.writeString(this.userStatusId);
        dest.writeString(this.userTypeId);
        dest.writeString(this.provinceId);
        dest.writeString(this.cityId);
    }

    public UserManageFilterBean() {
    }

    protected UserManageFilterBean(Parcel in) {
        this.groupName = in.readString();
        this.userName = in.readString();
        this.address = in.readString();
        this.userStatus = in.readString();
        this.userType = in.readString();
        this.userStatusId = in.readString();
        this.userTypeId = in.readString();
        this.provinceId = in.readString();
        this.cityId = in.readString();
    }

    public static final Parcelable.Creator<UserManageFilterBean> CREATOR = new Parcelable.Creator<UserManageFilterBean>() {
        @Override
        public UserManageFilterBean createFromParcel(Parcel source) {
            return new UserManageFilterBean(source);
        }

        @Override
        public UserManageFilterBean[] newArray(int size) {
            return new UserManageFilterBean[size];
        }
    };

    public interface OnDataChangeListener {
        /**
         * 当数据发生改变时的回调
         *
         * @param bean
         */
        void onDataChange(UserManageFilterBean bean);
    }
}
