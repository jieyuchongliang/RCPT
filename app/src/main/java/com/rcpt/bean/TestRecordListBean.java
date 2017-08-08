package com.rcpt.bean;

import java.util.List;

/**
 * Created by 860617003 on 2017/5/25.
 */

public class TestRecordListBean {


    /**
     * totalPage : 2
     * companyFavorites : [{"field_id":8,"exam_paper_id":336,"exam_paper_name":"sdds","update_user":"e1fb8a908e0b43c0aa1bb35c17aa12a8","test_result":"0.0","update_timestamp":"2017-05-17","user_id":"e1fb8a908e0b43c0aa1bb35c17aa12a8","insert_date":"2017-05-17","del_flg":"0","id":366,"insert_user":"e1fb8a908e0b43c0aa1bb35c17aa12a8"},{"field_id":8,"exam_paper_id":350,"exam_paper_name":"测试","update_user":"e1fb8a908e0b43c0aa1bb35c17aa12a8","test_result":"0.0","update_timestamp":"2017-05-12","user_id":"e1fb8a908e0b43c0aa1bb35c17aa12a8","insert_date":"2017-05-12","del_flg":"0","id":350,"insert_user":"e1fb8a908e0b43c0aa1bb35c17aa12a8"},{"field_id":8,"exam_paper_id":349,"exam_paper_name":"倒计时测试","update_user":"e1fb8a908e0b43c0aa1bb35c17aa12a8","test_result":"0.0","update_timestamp":"2017-05-12","user_id":"e1fb8a908e0b43c0aa1bb35c17aa12a8","insert_date":"2017-05-12","del_flg":"0","id":352,"insert_user":"e1fb8a908e0b43c0aa1bb35c17aa12a8"},{"field_id":8,"exam_paper_id":252,"exam_paper_name":"2015十八大全面推进依法治国进程(仅适用于2015年度)","update_user":"e1fb8a908e0b43c0aa1bb35c17aa12a8","test_result":"0.0","update_timestamp":"2017-05-08","user_id":"e1fb8a908e0b43c0aa1bb35c17aa12a8","insert_date":"2017-05-05","del_flg":"0","id":291,"insert_user":"e1fb8a908e0b43c0aa1bb35c17aa12a8"},{"field_id":8,"exam_paper_id":293,"exam_paper_name":"22222","update_user":"e1fb8a908e0b43c0aa1bb35c17aa12a8","test_result":"0.0","update_timestamp":"2017-05-08","user_id":"e1fb8a908e0b43c0aa1bb35c17aa12a8","insert_date":"2017-04-27","del_flg":"0","id":257,"insert_user":"e1fb8a908e0b43c0aa1bb35c17aa12a8"},{"field_id":8,"exam_paper_id":290,"exam_paper_name":"213213213","update_user":"e1fb8a908e0b43c0aa1bb35c17aa12a8","test_result":"0.0","update_timestamp":"2017-05-08","user_id":"e1fb8a908e0b43c0aa1bb35c17aa12a8","insert_date":"2017-05-08","del_flg":"0","id":301,"insert_user":"e1fb8a908e0b43c0aa1bb35c17aa12a8"},{"field_id":13,"exam_paper_id":253,"exam_paper_name":"能力测试体系","update_user":"e1fb8a908e0b43c0aa1bb35c17aa12a8","test_result":"0.0","update_timestamp":"2017-05-05","user_id":"e1fb8a908e0b43c0aa1bb35c17aa12a8","insert_date":"2017-04-18","del_flg":"0","id":223,"insert_user":"e1fb8a908e0b43c0aa1bb35c17aa12a8"},{"field_id":12,"exam_paper_id":255,"exam_paper_name":"职业倾向测试","update_user":"e1fb8a908e0b43c0aa1bb35c17aa12a8","test_result":"0.0","update_timestamp":"2017-05-05","user_id":"e1fb8a908e0b43c0aa1bb35c17aa12a8","insert_date":"2017-04-19","del_flg":"0","id":236,"insert_user":"e1fb8a908e0b43c0aa1bb35c17aa12a8"},{"field_id":1,"exam_paper_id":155,"exam_paper_name":"ky","update_user":"e1fb8a908e0b43c0aa1bb35c17aa12a8","test_result":"0.0","update_timestamp":"2017-05-05","user_id":"e1fb8a908e0b43c0aa1bb35c17aa12a8","insert_date":"2017-05-05","del_flg":"0","id":293,"insert_user":"e1fb8a908e0b43c0aa1bb35c17aa12a8"},{"field_id":8,"exam_paper_id":282,"exam_paper_name":"静态测评测试","update_user":"e1fb8a908e0b43c0aa1bb35c17aa12a8","test_result":"0.0","update_timestamp":"2017-04-27","user_id":"e1fb8a908e0b43c0aa1bb35c17aa12a8","insert_date":"2017-04-27","del_flg":"0","id":258,"insert_user":"e1fb8a908e0b43c0aa1bb35c17aa12a8"}]
     * isNext : true
     */

    private String totalPage;
    private boolean isNext;
    private List<CompanyFavoritesBean> companyFavorites;

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

    public List<CompanyFavoritesBean> getCompanyFavorites() {
        return companyFavorites;
    }

    public void setCompanyFavorites(List<CompanyFavoritesBean> companyFavorites) {
        this.companyFavorites = companyFavorites;
    }

    public static class CompanyFavoritesBean {
        /**
         * field_id : 8
         * exam_paper_id : 336
         * exam_paper_name : sdds
         * update_user : e1fb8a908e0b43c0aa1bb35c17aa12a8
         * test_result : 0.0
         * update_timestamp : 2017-05-17
         * user_id : e1fb8a908e0b43c0aa1bb35c17aa12a8
         * insert_date : 2017-05-17
         * del_flg : 0
         * id : 366
         * insert_user : e1fb8a908e0b43c0aa1bb35c17aa12a8
         */

        private String field_id;
        private String exam_paper_id;
        private String exam_paper_name;
        private String update_user;
        private float test_result;
        private String update_timestamp;
        private String user_id;
        private String insert_date;
        private String del_flg;
        private String id;
        private String insert_user;

        public String getField_id() {
            return field_id;
        }

        public void setField_id(String field_id) {
            this.field_id = field_id;
        }

        public String getExam_paper_id() {
            return exam_paper_id;
        }

        public void setExam_paper_id(String exam_paper_id) {
            this.exam_paper_id = exam_paper_id;
        }

        public String getExam_paper_name() {
            return exam_paper_name;
        }

        public void setExam_paper_name(String exam_paper_name) {
            this.exam_paper_name = exam_paper_name;
        }

        public String getUpdate_user() {
            return update_user;
        }

        public void setUpdate_user(String update_user) {
            this.update_user = update_user;
        }

        public int getTest_result() {
            return (int) test_result;
        }

        public void setTest_result(float test_result) {
            this.test_result = test_result;
        }

        public String getUpdate_timestamp() {
            return update_timestamp;
        }

        public void setUpdate_timestamp(String update_timestamp) {
            this.update_timestamp = update_timestamp;
        }

        public String getUser_id() {
            return user_id;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
        }

        public String getInsert_date() {
            return insert_date;
        }

        public void setInsert_date(String insert_date) {
            this.insert_date = insert_date;
        }

        public String getDel_flg() {
            return del_flg;
        }

        public void setDel_flg(String del_flg) {
            this.del_flg = del_flg;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getInsert_user() {
            return insert_user;
        }

        public void setInsert_user(String insert_user) {
            this.insert_user = insert_user;
        }
    }
}
