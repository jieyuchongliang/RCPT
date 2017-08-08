package com.rcpt.bean;

import com.rcpt.http.ParamNames;

import java.util.List;

/**
 * Created by lvzp on 2017/3/2.
 * 类描述：投递简历的实体类
 */

public class SendRecordBean {

    private String totalPage;
    private boolean isNext;
    private List<DeliveryRecordListBean> deliveryRecordList;

    public String getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(String totalPage) {
        this.totalPage = totalPage;
    }

    public boolean isNext() {
        return isNext;
    }

    public void setNext(boolean next) {
        isNext = next;
    }

    public List<DeliveryRecordListBean> getDeliveryRecordList() {
        return deliveryRecordList;
    }

    public void setDeliveryRecordList(List<DeliveryRecordListBean> deliveryRecordList) {
        this.deliveryRecordList = deliveryRecordList;
    }

    public static class DeliveryRecordListBean {


        private int delFlg;//5.11新增删除标记
        private String companyName;
        @ParamNames("positionName")
        private String jobName;
        @ParamNames("updateTimestamp")
        private String time;
        private String city;
        private String province;
        private String recordId;
        private String companyId;
        private String recruitmentInfoId;
        private int beenViewed;

        public int getDelFlg() {
            return delFlg;
        }

        public void setDelFlg(int delFlg) {
            this.delFlg = delFlg;
        }

        public int getBeenViewed() {
            return beenViewed;
        }

        public void setBeenViewed(int beenViewed) {
            this.beenViewed = beenViewed;
        }

        public String getRecruitmentInfoId() {
            return recruitmentInfoId;
        }

        public void setRecruitmentInfoId(String recruitmentInfoId) {
            this.recruitmentInfoId = recruitmentInfoId;
        }

        public String getRecordId() {
            return recordId;
        }

        public void setRecordId(String recordId) {
            this.recordId = recordId;
        }

        public String getCompanyId() {
            return companyId;
        }

        public void setCompanyId(String companyId) {
            this.companyId = companyId;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getProvince() {
            return province;
        }

        public void setProvince(String province) {
            this.province = province;
        }

        public String getCompanyName() {
            return companyName;
        }

        public void setCompanyName(String companyName) {
            this.companyName = companyName;
        }

        public String getJobName() {
            return jobName;
        }

        public void setJobName(String jobName) {
            this.jobName = jobName;
        }

        public String getAddress() {
            return province + " - " + city;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }
    }
}
