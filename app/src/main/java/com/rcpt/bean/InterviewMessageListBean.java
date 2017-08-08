package com.rcpt.bean;

import java.util.List;

/**
 * Created by lvzp on 2017/3/6.
 * 类描述：
 */

public class InterviewMessageListBean {


    /**
     * getInterview : [{"start_time":"2017-04-14 11:52","apply_date":"2017-04-14","company_id":"d787bd11cb6b41a4b3dfbcd961bddb6d","company_name":"中国科学院122345","end_time":"2017-04-14 11:52","position_name":"Android 软件开发工程师","description":"赶紧来，带上你的简历","id":71,"recruitment_info_id":1,"status":"1"},{"start_time":"2017-04-15 12:00","apply_date":"2017-04-14","company_id":"d787bd11cb6b41a4b3dfbcd961bddb6d","company_name":"中国科学院122345","end_time":"2017-04-16 12:01","position_name":"Android 开发工程师","description":"简历，一定要简历","id":72,"recruitment_info_id":3,"status":"0"}]
     * totalPage : 1
     * isNext : false
     */

    private String totalPage;
    private boolean isNext;
    private List<GetInterviewBean> getInterview;

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

    public List<GetInterviewBean> getGetInterview() {
        return getInterview;
    }

    public void setGetInterview(List<GetInterviewBean> getInterview) {
        this.getInterview = getInterview;
    }

    public static class GetInterviewBean {


        /**
         * start_time : 2017-04-14 11:52
         * apply_date : 2017-04-14
         * company_id : d787bd11cb6b41a4b3dfbcd961bddb6d
         * company_name : 中国科学院122345
         * end_time : 2017-04-14 11:52
         * position_name : Android 软件开发工程师
         * description : 赶紧来，带上你的简历
         * id : 71
         * recruitment_info_id : 1
         * status : 1
         */
        private int delFlg;
        private String start_time;
        private String apply_date;
        private String company_id;
        private String company_name;
        private String end_time;
        private String position_name;
        private String descriptions;
        private String id;
        private String recruitment_info_id;
        private int status;
        private String reason;

        public int getDelFlg() {
            return delFlg;
        }

        public void setDelFlg(int delFlg) {
            this.delFlg = delFlg;
        }

        public String getReason() {
            return reason;
        }

        public void setReason(String reason) {
            this.reason = reason;
        }

        public String getStart_time() {
            return start_time;
        }

        public void setStart_time(String start_time) {
            this.start_time = start_time;
        }

        public String getApply_date() {
            return apply_date;
        }

        public void setApply_date(String apply_date) {
            this.apply_date = apply_date;
        }

        public String getCompany_id() {
            return company_id;
        }

        public void setCompany_id(String company_id) {
            this.company_id = company_id;
        }

        public String getCompany_name() {
            return company_name;
        }

        public void setCompany_name(String company_name) {
            this.company_name = company_name;
        }

        public String getEnd_time() {
            return end_time;
        }

        public void setEnd_time(String end_time) {
            this.end_time = end_time;
        }

        public String getPosition_name() {
            return position_name;
        }

        public void setPosition_name(String position_name) {
            this.position_name = position_name;
        }

        public String getDescription() {
            return descriptions;
        }

        public void setDescription(String description) {
            this.descriptions = description;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getRecruitment_info_id() {
            return recruitment_info_id;
        }

        public void setRecruitment_info_id(String recruitment_info_id) {
            this.recruitment_info_id = recruitment_info_id;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }
    }
}
