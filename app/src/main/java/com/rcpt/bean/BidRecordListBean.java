package com.rcpt.bean;

import java.util.List;

/**
 * Created by 860617003 on 2017/5/25.
 */

public class BidRecordListBean {

    /**
     * totalPage : 2
     * isNext : true
     * projectProlist : [{"project_id":304,"insert_timestamp":"2017-05-17","bid_status":"竞标中","project_title":"rew","publish_company_name":"讯和","insert_user":"guser01"},{"project_id":291,"insert_timestamp":"2017-05-05","bid_status":"竞标中","project_title":"ASas","publish_company_name":"中国科技大学","insert_user":"664397ec4f0ac359"},{"project_id":287,"insert_timestamp":"2017-05-02","bid_status":"竞标中","project_title":"人员需求050201","publish_company_name":"中国科技大学","insert_user":"664397ec4f0ac359"},{"project_id":290,"insert_timestamp":"2017-05-05","bid_status":"竞标中","project_title":"中国科学院工程竞标","publish_company_name":"中国科学院122345","insert_user":"d787bd11cb6b41a4b3dfbcd961bddb6d"},{"project_id":265,"insert_timestamp":"2017-03-13","bid_status":"竞标中","project_title":"人员需求标题一","publish_company_name":"政府申报二","insert_user":"44203df462f07a78"},{"project_id":284,"insert_timestamp":"2017-04-26","bid_status":"竞标中","project_title":"测试","publish_company_name":"济南市讯和公司讯和公司讯和公司讯和公司","insert_user":"user002"},{"project_id":273,"insert_timestamp":"2017-04-19","bid_status":"未中标","project_title":"web项目","publish_company_name":"济南企业","insert_user":"3f62873cd716d3fc"},{"project_id":271,"insert_timestamp":"2017-04-17","bid_status":"已中标","project_title":"web开发","publish_company_name":"济南企业","insert_user":"3f62873cd716d3fc"},{"project_id":263,"insert_timestamp":"2017-03-08","bid_status":"竞标中","project_title":"dzy","publish_company_name":"讯和","insert_user":"guser01"},{"project_id":247,"insert_timestamp":"2017-02-15","bid_status":"竞标中","project_title":"测试有附件","publish_company_name":"国泰租赁有限公司","insert_user":"91548b70924203a9"},{"project_id":240,"insert_timestamp":"2017-02-03","bid_status":"竞标中","project_title":"Android项目3","publish_company_name":"讯和","insert_user":"guser01"},{"project_id":261,"insert_timestamp":"2017-03-08","bid_status":"竞标中","project_title":"1122","publish_company_name":"讯和","insert_user":"guser01"},{"project_id":239,"insert_timestamp":"2017-02-03","bid_status":"竞标中","project_title":"Android项目2","publish_company_name":"讯和","insert_user":"guser01"}]
     */

    private String totalPage;
    private boolean isNext;
    private List<ProjectProlistBean> list;

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

    public List<ProjectProlistBean> getList() {
        return list;
    }

    public void setList(List<ProjectProlistBean> list) {
        this.list = list;
    }

    public static class ProjectProlistBean {
        /**
         * project_id : 304
         * insert_timestamp : 2017-05-17
         * bid_status : 竞标中
         * project_title : rew
         * publish_company_name : 讯和
         * insert_user : guser01
         */

        private int id;
        private String insert_timestamp;
        private String bid_status;
        private String title;
        private String companyName;
        private String insert_user;
        private String bid_company_name;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getInsert_timestamp() {
            return insert_timestamp;
        }

        public void setInsert_timestamp(String insert_timestamp) {
            this.insert_timestamp = insert_timestamp;
        }

        public String getBid_status() {
            return bid_status;
        }

        public void setBid_status(String bid_status) {
            this.bid_status = bid_status;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getCompanyName() {
            return companyName;
        }

        public void setCompanyName(String companyName) {
            this.companyName = companyName;
        }

        public String getInsert_user() {
            return insert_user;
        }

        public void setInsert_user(String insert_user) {
            this.insert_user = insert_user;
        }

        public String getBid_company_name() {
            return bid_company_name;
        }

        public void setBid_company_name(String bid_company_name) {
            this.bid_company_name = bid_company_name;
        }
    }
}
