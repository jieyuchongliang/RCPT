package com.rcpt.bean;

import java.util.List;

/**
 * Created by 860617003 on 2017/6/2.
 */

public class UserManageListBean {

    private String totalPage;
    private boolean isNext;
    private List<UserInfoBean> varList;

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

    public List<UserInfoBean> getVarList() {
        return varList;
    }

    public void setVarList(List<UserInfoBean> varList) {
        this.varList = varList;
    }

    public static class UserInfoBean {

        //通用部分
        private int user_type;//用户类型(id)
        private String user_name;//用户名
        private String types;//用户类型(名称)
        private String user_id;//用户id
        private boolean isExpand;//是否是展开状态
        private String login_time = "";
        /**
         * user_type : 1
         * mail : user01@163.com
         * update_timestamp : 1493287891000
         * user_id : e35e7014521a46b5
         * insert_timestamp : 1493287891000
         * registe_time : 2017-04-24 17:11
         * user_name : user01
         * login_time : 2017-06-05 13:11:12
         * name : 颜志芳12
         * contact_info : 18363075033
         * type : 个人
         * status : 正常
         */
        //个人的用户

        private String mail = "";
        private long update_timestamp;
        private long insert_timestamp;
        private String registe_time = "";
        private String name = "";
        private String contact_info;
        private String status;
        /**
         * city : 济南市
         * del_flg : 0
         * insert_user : dazd
         * zip_code : 250101
         * address_detail : 高新区颖秀路
         * update_user : dazd
         * address_province : 16
         * province : 山东省
         * review_time : 2017-05-04 14:41:00
         * types_val : 1
         * user_status : 2
         * website : www.fushi.cn
         * types : 企业会员
         * reviewer : admin
         * userId : dazd
         * address_city : 170
         * delFlg : 0
         * insertUser : dazd
         * company_name : 山东富士
         */
        //团体的用户
        private String city;
        private String del_flg;
        private String insert_user;
        private String zip_code;
        private String address_detail;
        private String update_user;
        private int address_province;
        private String province;
        private String review_time = "";
        private String types_val;
        private int user_status;
        private String website;
        private String reviewer = "";
        private String userId;
        private int address_city;
        private String delFlg;
        private String insertUser;
        private String company_name = "";
        private String registration_time = "";

        public String getRegistration_time() {
            return registration_time;
        }

        public void setRegistration_time(String registration_time) {
            this.registration_time = registration_time;
        }

        public boolean isExpand() {
            return isExpand;
        }

        public void setExpand(boolean expand) {
            isExpand = expand;
        }

        public int getUser_type() {
            return user_type;
        }

        public void setUser_type(int user_type) {
            this.user_type = user_type;
        }

        public String getMail() {
            return mail;
        }

        public void setMail(String mail) {
            this.mail = mail;
        }

        public long getUpdate_timestamp() {
            return update_timestamp;
        }

        public void setUpdate_timestamp(long update_timestamp) {
            this.update_timestamp = update_timestamp;
        }

        public String getUser_id() {
            return user_id;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
        }

        public long getInsert_timestamp() {
            return insert_timestamp;
        }

        public void setInsert_timestamp(long insert_timestamp) {
            this.insert_timestamp = insert_timestamp;
        }

        public String getRegiste_time() {
            return registe_time;
        }

        public void setRegiste_time(String registe_time) {
            this.registe_time = registe_time;
        }

        public String getUser_name() {
            return user_name;
        }

        public void setUser_name(String user_name) {
            this.user_name = user_name;
        }

        public String getLogin_time() {
            return login_time;
        }

        public void setLogin_time(String login_time) {
            this.login_time = login_time;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getContact_info() {
            return contact_info;
        }

        public void setContact_info(String contact_info) {
            this.contact_info = contact_info;
        }


        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getDel_flg() {
            return del_flg;
        }

        public void setDel_flg(String del_flg) {
            this.del_flg = del_flg;
        }

        public String getInsert_user() {
            return insert_user;
        }

        public void setInsert_user(String insert_user) {
            this.insert_user = insert_user;
        }

        public String getZip_code() {
            return zip_code;
        }

        public void setZip_code(String zip_code) {
            this.zip_code = zip_code;
        }

        public String getAddress_detail() {
            return address_detail;
        }

        public void setAddress_detail(String address_detail) {
            this.address_detail = address_detail;
        }

        public String getUpdate_user() {
            return update_user;
        }

        public void setUpdate_user(String update_user) {
            this.update_user = update_user;
        }

        public int getAddress_province() {
            return address_province;
        }

        public void setAddress_province(int address_province) {
            this.address_province = address_province;
        }

        public String getProvince() {
            return province;
        }

        public void setProvince(String province) {
            this.province = province;
        }

        public String getReview_time() {
            return review_time;
        }

        public void setReview_time(String review_time) {
            this.review_time = review_time;
        }

        public String getTypes_val() {
            return types_val;
        }

        public void setTypes_val(String types_val) {
            this.types_val = types_val;
        }

        public int getUser_status() {
            return user_status;
        }

        public void setUser_status(int user_status) {
            this.user_status = user_status;
        }

        public String getWebsite() {
            return website;
        }

        public void setWebsite(String website) {
            this.website = website;
        }

        public String getTypes() {
            return types;
        }

        public void setTypes(String types) {
            this.types = types;
        }

        public String getReviewer() {
            return reviewer;
        }

        public void setReviewer(String reviewer) {
            this.reviewer = reviewer;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public int getAddress_city() {
            return address_city;
        }

        public void setAddress_city(int address_city) {
            this.address_city = address_city;
        }

        public String getDelFlg() {
            return delFlg;
        }

        public void setDelFlg(String delFlg) {
            this.delFlg = delFlg;
        }

        public String getInsertUser() {
            return insertUser;
        }

        public void setInsertUser(String insertUser) {
            this.insertUser = insertUser;
        }

        public String getCompany_name() {
            return company_name;
        }

        public void setCompany_name(String company_name) {
            this.company_name = company_name;
        }
    }

}
