package com.rcpt.bean;

import java.util.List;

/**
 * Created by lvzp on 2017/4/11.
 * 类描述：
 */

public class RecruitPositionFilterBean {

    private List<JobChilCateListBean> jobChilCateList;
    private List<JobPareCateListBean> jobPareCateList;
    private List<AttrSelectBean> educationList;
    private List<AttrSelectBean> jobTimeList;
    private List<AttrSelectBean> sexTypeList;
    private List<AttrSelectBean> salRangeList;

    public List<JobChilCateListBean> getJobChilCateList() {
        return jobChilCateList;
    }

    public void setJobChilCateList(List<JobChilCateListBean> jobChilCateList) {
        this.jobChilCateList = jobChilCateList;
    }

    public List<JobPareCateListBean> getJobPareCateList() {
        return jobPareCateList;
    }

    public void setJobPareCateList(List<JobPareCateListBean> jobPareCateList) {
        this.jobPareCateList = jobPareCateList;
    }

    public List<AttrSelectBean> getEducationList() {
        return educationList;
    }

    public void setEducationList(List<AttrSelectBean> educationList) {
        this.educationList = educationList;
    }

    public List<AttrSelectBean> getJobTimeList() {
        return jobTimeList;
    }

    public void setJobTimeList(List<AttrSelectBean> jobTimeList) {
        this.jobTimeList = jobTimeList;
    }

    public List<AttrSelectBean> getSexTypeList() {
        return sexTypeList;
    }

    public void setSexTypeList(List<AttrSelectBean> sexTypeList) {
        this.sexTypeList = sexTypeList;
    }

    public List<AttrSelectBean> getSalRangeList() {
        return salRangeList;
    }

    public void setSalRangeList(List<AttrSelectBean> salRangeList) {
        this.salRangeList = salRangeList;
    }

    public static class JobChilCateListBean {
        /**
         * positionName : 销售代表
         * positionId : 3
         * parentId : 2
         */

        private String positionName;
        private String positionId;
        private String parentId;

        public String getPositionName() {
            return positionName;
        }

        public void setPositionName(String positionName) {
            this.positionName = positionName;
        }

        public String getPositionId() {
            return positionId;
        }

        public void setPositionId(String positionId) {
            this.positionId = positionId;
        }

        public String getParentId() {
            return parentId;
        }

        public void setParentId(String parentId) {
            this.parentId = parentId;
        }
    }

    public static class JobPareCateListBean {
        /**
         * positionName : 销售业务>>
         * parentId : 2
         */

        private String positionName;
        private String parentId;

        public String getPositionName() {
            return positionName;
        }

        public void setPositionName(String positionName) {
            this.positionName = positionName;
        }

        public String getParentId() {
            return parentId;
        }

        public void setParentId(String parentId) {
            this.parentId = parentId;
        }
    }

}
