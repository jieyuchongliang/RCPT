package com.rcpt.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by 860116021 on 2017/3/15.
 */

public class ChooseIndustryBean implements Parcelable {


    private String id;    //编码
    private String value;    //名称

    private boolean isSelect;


    public boolean isSelect() {
        return isSelect;
    }

    public void setSelect(boolean select) {
        isSelect = select;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.value);
        dest.writeByte(this.isSelect ? (byte) 1 : (byte) 0);
    }

    public ChooseIndustryBean() {
    }

    protected ChooseIndustryBean(Parcel in) {
        this.id = in.readString();
        this.value = in.readString();
        this.isSelect = in.readByte() != 0;
    }

    public static final Parcelable.Creator<ChooseIndustryBean> CREATOR = new Parcelable.Creator<ChooseIndustryBean>() {
        @Override
        public ChooseIndustryBean createFromParcel(Parcel source) {
            return new ChooseIndustryBean(source);
        }

        @Override
        public ChooseIndustryBean[] newArray(int size) {
            return new ChooseIndustryBean[size];
        }
    };
}
