package com.rcpt.bean;

import java.util.List;

/**
 * Created by lvzp on 2017/4/14.
 * 类描述：
 */

public class CompanyFavoritesListBean {


    /**
     * totalPage : 1
     * companyFavorites : [{"recordId":7,"cvId":1,"name":"闫志芳","cvUserId":"41e46ba41a703d0c","cvName":"闫简历一","updateTimestamp":"2017-03-03"},{"recordId":7,"cvId":2,"name":"闫志芳","cvUserId":"41e46ba41a703d0c","cvName":"闫简历二","updateTimestamp":"2017-03-03"},{"recordId":7,"cvId":3,"name":"闫志芳","cvUserId":"41e46ba41a703d0c","cvName":"颜志芳求职简历","updateTimestamp":"2017-03-03"},{"recordId":7,"cvId":4,"name":"闫志芳","cvUserId":"41e46ba41a703d0c","cvName":"简历二","updateTimestamp":"2017-03-03"},{"recordId":6,"cvId":1,"name":"栾庆专03","cvUserId":"3d8a8068844b53e6","cvName":"万能简历","updateTimestamp":"2017-03-14"},{"recordId":6,"cvId":2,"name":"栾庆专03","cvUserId":"3d8a8068844b53e6","cvName":"简历1","updateTimestamp":"2017-03-14"},{"recordId":6,"cvId":3,"name":"栾庆专03","cvUserId":"3d8a8068844b53e6","cvName":"lalala","updateTimestamp":"2017-03-01"},{"recordId":6,"cvId":4,"name":"栾庆专03","cvUserId":"3d8a8068844b53e6","updateTimestamp":"2017-04-13"},{"recordId":5,"cvId":1,"name":"张张","cvUserId":"23b7cd842f7d8389","cvName":"aaa","updateTimestamp":"2017-03-30"},{"recordId":5,"cvId":2,"name":"张张","cvUserId":"23b7cd842f7d8389","cvName":"bbb","updateTimestamp":"2017-03-30"}]
     * isNext : false
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
         * recordId : 7
         * cvId : 1
         * name : 闫志芳
         * cvUserId : 41e46ba41a703d0c
         * cvName : 闫简历一
         * updateTimestamp : 2017-03-03
         */
        private int delFlg;
        private String recordId;
        private String cvId;
        private String name;
        private String cvUserId;
        private String cvName;
        private String updateTimestamp;

        public int getDelFlg() {
            return delFlg;
        }

        public void setDelFlg(int delFlg) {
            this.delFlg = delFlg;
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

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getCvUserId() {
            return cvUserId;
        }

        public void setCvUserId(String cvUserId) {
            this.cvUserId = cvUserId;
        }

        public String getCvName() {
            return cvName;
        }

        public void setCvName(String cvName) {
            this.cvName = cvName;
        }

        public String getUpdateTimestamp() {
            return updateTimestamp;
        }

        public void setUpdateTimestamp(String updateTimestamp) {
            this.updateTimestamp = updateTimestamp;
        }
    }
}
