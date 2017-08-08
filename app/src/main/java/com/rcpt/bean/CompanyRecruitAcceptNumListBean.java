package com.rcpt.bean;

import java.util.List;

/**
 * Created by lvzp on 2017/5/2.
 * 类描述：
 */

public class CompanyRecruitAcceptNumListBean {


    /**
     * totalPage : 1
     * isNext : false
     * JobPeopleList : [{"recordId":1,"cvId":3,"companyId":"d787bd11cb6b41a4b3dfbcd961bddb6d","name":"其实","isAdopt":"审核中","beenViewed":"已阅览","userId":"4d05fda75e4e9fb4","status":"0"},{"recordId":4,"cvId":1,"companyId":"d787bd11cb6b41a4b3dfbcd961bddb6d","name":"马莉莉","isAdopt":"审核中","beenViewed":"已阅览","userId":"0a72e4ae08cc45968a103eccebb0b5f0"},{"recordId":1,"cvId":1,"companyId":"d787bd11cb6b41a4b3dfbcd961bddb6d","name":"玛丽莲梦露玛丽莲梦露玛丽莲梦露玛丽莲梦露玛丽莲梦露","isAdopt":"审核中","beenViewed":"已阅览","userId":"9404501ca31c497da51944c093c116ee"},{"recordId":1,"cvId":1,"companyId":"d787bd11cb6b41a4b3dfbcd961bddb6d","name":"王小二","isAdopt":"审核中","beenViewed":"已阅览","userId":"f265e995e6a44fab92963d9b1b707256","status":"0"},{"recordId":2,"cvId":1,"companyId":"d787bd11cb6b41a4b3dfbcd961bddb6d","name":"ma","isAdopt":"审核中","beenViewed":"已阅览","userId":"5c0d6f285c73478f","status":"0"},{"recordId":45,"cvId":6,"companyId":"d787bd11cb6b41a4b3dfbcd961bddb6d","name":"驴哥00","isAdopt":"审核中","beenViewed":"已阅览","userId":"e1fb8a908e0b43c0aa1bb35c17aa12a8","status":"2"},{"recordId":46,"cvId":8,"companyId":"d787bd11cb6b41a4b3dfbcd961bddb6d","name":"驴哥00","isAdopt":"审核中","beenViewed":"已阅览","userId":"e1fb8a908e0b43c0aa1bb35c17aa12a8","status":"1"},{"recordId":7,"cvId":1,"companyId":"d787bd11cb6b41a4b3dfbcd961bddb6d","name":"凤舞九天","isAdopt":"审核中","beenViewed":"已阅览","userId":"bb68511d4d534af7a25d5c5d7b7404c1","status":"1"}]
     */

    private String totalPage;
    private boolean isNext;
    private List<JobPeopleListBean> JobPeopleList;

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

    public List<JobPeopleListBean> getJobPeopleList() {
        return JobPeopleList;
    }

    public void setJobPeopleList(List<JobPeopleListBean> JobPeopleList) {
        this.JobPeopleList = JobPeopleList;
    }

    public static class JobPeopleListBean {
        /**
         * recordId : 1
         * cvId : 3
         * companyId : d787bd11cb6b41a4b3dfbcd961bddb6d
         * name : 其实
         * isAdopt : 审核中
         * beenViewed : 已阅览
         * userId : 4d05fda75e4e9fb4
         * status : 0
         */

        private String recordId;
        private String cvId;
        private String companyId;
        private String name;
        private String isAdopt;
        private String beenViewed;
        private String applyDate;
        private String userId;
        private int status;
        private int IS_ADOPT;

        public int getIS_ADOPT() {
            return IS_ADOPT;
        }

        public void setIS_ADOPT(int IS_ADOPT) {
            this.IS_ADOPT = IS_ADOPT;
        }

        public String getApplyDate() {
            return applyDate;
        }

        public void setApplyDate(String applyDate) {
            this.applyDate = applyDate;
        }

        public String getRecordId() {
            return recordId;
        }

        public void setRecordId(String recordId) {
            this.recordId = recordId;
        }

        public String getCvId() {
            return cvId;
        }

        public void setCvId(String cvId) {
            this.cvId = cvId;
        }

        public String getCompanyId() {
            return companyId;
        }

        public void setCompanyId(String companyId) {
            this.companyId = companyId;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getIsAdopt() {
            return isAdopt;
        }

        public void setIsAdopt(String isAdopt) {
            this.isAdopt = isAdopt;
        }

        public String getBeenViewed() {
            return beenViewed;
        }

        public void setBeenViewed(String beenViewed) {
            this.beenViewed = beenViewed;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }
    }
}
