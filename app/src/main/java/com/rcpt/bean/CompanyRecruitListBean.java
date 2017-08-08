package com.rcpt.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by lvzp on 2017/4/17.
 * 类描述：
 */

public class CompanyRecruitListBean {


    /**
     * recruitlist : [{"positionName":"PHP程序员","total":1,"userId":"d787bd11cb6b41a4b3dfbcd961bddb6d","recruitmentInfoId":5,"updateTimestamp":"2017-04-17"},{"positionName":"Java工程师","total":2,"userId":"d787bd11cb6b41a4b3dfbcd961bddb6d","recruitmentInfoId":4,"updateTimestamp":"2017-04-14"},{"positionName":"Android 开发工程师","total":5,"userId":"d787bd11cb6b41a4b3dfbcd961bddb6d","recruitmentInfoId":3,"updateTimestamp":"2017-04-14"},{"positionName":"Android 软件开发工程师","total":6,"userId":"d787bd11cb6b41a4b3dfbcd961bddb6d","recruitmentInfoId":1,"updateTimestamp":"2017-04-14"}]
     * totalPage : 1
     * isNext : false
     */

    private String totalPage;
    private boolean isNext;
    private List<RecruitlistBean> recruitlist;

    public String getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(String totalPage) {
        this.totalPage = totalPage;
    }

    public boolean isIsNext() {
        return isNext;
    }

    public void setIsNext(boolean isNext) {
        this.isNext = isNext;
    }

    public List<RecruitlistBean> getRecruitlist() {
        return recruitlist;
    }

    public void setRecruitlist(List<RecruitlistBean> recruitlist) {
        this.recruitlist = recruitlist;
    }

    public static class RecruitlistBean implements Parcelable {
        /**
         * positionName : PHP程序员
         * total : 1
         * userId : d787bd11cb6b41a4b3dfbcd961bddb6d
         * recruitmentInfoId : 5
         * updateTimestamp : 2017-04-17
         */

        private String positionName;
        private String total = "0";
        private String userId;
        private String recruitmentInfoId;
        private String updateTimestamp;
        private int Invitation;

        public int getInvitation() {
            return Invitation;
        }

        public void setInvitation(int invitation) {
            Invitation = invitation;
        }

        public String getTotal() {
            return total;
        }

        public void setTotal(String total) {
            this.total = total;
        }

        public String getRecruitmentInfoId() {
            return recruitmentInfoId;
        }

        public void setRecruitmentInfoId(String recruitmentInfoId) {
            this.recruitmentInfoId = recruitmentInfoId;
        }

        public String getPositionName() {
            return positionName;
        }

        public void setPositionName(String positionName) {
            this.positionName = positionName;
        }


        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getUpdateTimestamp() {
            return updateTimestamp;
        }

        public void setUpdateTimestamp(String updateTimestamp) {
            this.updateTimestamp = updateTimestamp;
        }


        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.positionName);
            dest.writeString(this.total);
            dest.writeString(this.userId);
            dest.writeString(this.recruitmentInfoId);
            dest.writeString(this.updateTimestamp);
        }

        public RecruitlistBean() {
        }

        protected RecruitlistBean(Parcel in) {
            this.positionName = in.readString();
            this.total = in.readString();
            this.userId = in.readString();
            this.recruitmentInfoId = in.readString();
            this.updateTimestamp = in.readString();
        }

        public static final Parcelable.Creator<RecruitlistBean> CREATOR = new Parcelable.Creator<RecruitlistBean>() {
            @Override
            public RecruitlistBean createFromParcel(Parcel source) {
                return new RecruitlistBean(source);
            }

            @Override
            public RecruitlistBean[] newArray(int size) {
                return new RecruitlistBean[size];
            }
        };
    }
}
