package com.rcpt.bean;

import java.util.List;

/**
 * Created by 860116021 on 2017/4/7.
 * 企业新闻实体类
 */

public class CompanyNewsListBean {

    private String totalPage;
    private boolean isNext;
    private List<CompanyNewsBean> companyNews;

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

    public List<CompanyNewsBean> getCompanyNews() {
        return companyNews;
    }

    public void setCompanyNews(List<CompanyNewsBean> companyNews) {
        this.companyNews = companyNews;
    }

    public static class CompanyNewsBean {

        private String delFlg;          //删除标记
        private String companyId;       //公司id
        private String updateUser;      //更新用户
        private String id;              //新闻id
        private String title;           //新闻标题
        private String content;         //新闻内容
        private String updateTimestamp; //更新时间

        public String getDelFlg() {
            return delFlg;
        }

        public void setDelFlg(String delFlg) {
            this.delFlg = delFlg;
        }

        public String getCompanyId() {
            return companyId;
        }

        public void setCompanyId(String companyId) {
            this.companyId = companyId;
        }

        public String getUpdateUser() {
            return updateUser;
        }

        public void setUpdateUser(String updateUser) {
            this.updateUser = updateUser;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getUpdateTimestamp() {
            return updateTimestamp;
        }

        public void setUpdateTimestamp(String updateTimestamp) {
            this.updateTimestamp = updateTimestamp;
        }
    }
}
