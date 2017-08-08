package com.rcpt.bean;

import com.rcpt.http.ParamNames;

import java.util.List;

/**
 * Created by lvzp on 2017/3/6.
 * 类描述：职位邀请的实体类
 */
public class JobInviteListBean {
    private String totalPage;
    private boolean isNext;
    private List<JobInviteBean> posInvitationList;

    public List<JobInviteBean> getPosInvitationList() {
        return posInvitationList;
    }

    public void setPosInvitationList(List<JobInviteBean> posInvitationList) {
        this.posInvitationList = posInvitationList;
    }

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

    public static class JobInviteBean {
        private String companyId;
        private String companyName;//公司名称
        @ParamNames("positionName")
        private String jobName;//职位名称
        @ParamNames("applicationTime")
        private String time;//面试时间
        private String logo;//公司logo
        @ParamNames("tel")
        private String phone;//手机号
        @ParamNames("salary")
        private String money;//工资范围
        @ParamNames("contact")
        private String contacts = "";//联系人名称
        private String remarks;//信息备注
        private String recruitmentInfoId;//招聘信息id
        private String workProvince;
        private String workCity;
        private String address_detail;

        public String getAddress_detail() {
            return address_detail;
        }

        public void setAddress_detail(String address_detail) {
            this.address_detail = address_detail;
        }

        public String getWorkProvince() {
            return workProvince;
        }

        public void setWorkProvince(String workProvince) {
            this.workProvince = workProvince;
        }

        public String getWorkCity() {
            return workCity;
        }

        public void setWorkCity(String workCity) {
            this.workCity = workCity;
        }

        public String getRecruitmentInfoId() {
            return recruitmentInfoId;
        }

        public void setRecruitmentInfoId(String recruitmentInfoId) {
            this.recruitmentInfoId = recruitmentInfoId;
        }

        public String getRemarks() {
            return "备注：" + remarks;
        }

        public void setRemarks(String remarks) {
            this.remarks = remarks;
        }

        public String getMoney() {
            return money;
        }

        public void setMoney(String money) {
            this.money = money;
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

        public String getCompanyId() {
            return companyId;
        }

        public void setCompanyId(String companyId) {
            this.companyId = companyId;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public String getLogo() {
            return logo;
        }

        public void setLogo(String logo) {
            this.logo = logo;
        }


        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getContacts() {
            return contacts;
        }

        public void setContacts(String contacts) {
            this.contacts = contacts;
        }

        public String getAddress() {
            return workProvince + " · " + workCity;
        }

    }


}
