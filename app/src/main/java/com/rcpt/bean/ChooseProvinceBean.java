package com.rcpt.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by 860116021 on 2017/3/16.
 */

public class ChooseProvinceBean implements Parcelable {
    private String code;
    private String regionId;
    private String regionName;
    private boolean isSelect;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getRegionId() {
        return regionId;
    }

    public void setRegionId(String regionId) {
        this.regionId = regionId;
    }

    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }

    public boolean isSelect() {
        return isSelect;
    }

    public void setSelect(boolean select) {
        isSelect = select;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.code);
        dest.writeString(this.regionId);
        dest.writeString(this.regionName);
        dest.writeByte(this.isSelect ? (byte) 1 : (byte) 0);
    }

    public ChooseProvinceBean() {
    }

    protected ChooseProvinceBean(Parcel in) {
        this.code = in.readString();
        this.regionId = in.readString();
        this.regionName = in.readString();
        this.isSelect = in.readByte() != 0;
    }

    public static final Creator<ChooseProvinceBean> CREATOR = new Creator<ChooseProvinceBean>() {
        @Override
        public ChooseProvinceBean createFromParcel(Parcel source) {
            return new ChooseProvinceBean(source);
        }

        @Override
        public ChooseProvinceBean[] newArray(int size) {
            return new ChooseProvinceBean[size];
        }
    };
}
