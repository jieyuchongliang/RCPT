package com.rcpt.bean;

import java.util.List;

/**
 * Created by lvzp on 2017/3/14.
 * 类描述：
 */

public class PolicyListBean {


    /**
     * policylist : [{"policiesSources":"山东省服务外包网","sortKey":1487734575000,"policiesId":79,"category":"省级","title":"山东省出台全省服务外包产业发展规划","updateTimeStamp":"2017-02-22"},{"policiesSources":"山东省服务外包网","sortKey":1487734553000,"policiesId":80,"category":"省级","title":"山东省人民政府关于加快服务外包产业发展的意见（鲁政发〔2007〕78号）","updateTimeStamp":"2017-02-22"},{"policiesSources":"山东省服务外包网","sortKey":1487734485000,"policiesId":78,"category":"省级","title":"山东省引进海外创新创业人才公告","updateTimeStamp":"2017-02-22"},{"policiesSources":"无","sortKey":1487734439000,"policiesId":77,"category":"省级","title":"山东省人民政府关于加快培育和发展战略性新兴产业的实施意见","updateTimeStamp":"2017-02-22"},{"policiesSources":"无","sortKey":1487734386000,"policiesId":76,"category":"省级","title":"关于印发《山东省服务外包人才培训机构认定管理办法（试行）》的通知","updateTimeStamp":"2017-02-22"},{"policiesSources":"省商务厅","sortKey":1487733241000,"policiesId":54,"category":"省级","title":"山东省人民政府关于加快电子商务发展的意见","updateTimeStamp":"2017-02-22"},{"policiesSources":"政府网","sortKey":1487733088000,"policiesId":53,"category":"省级","title":"山东省发布《关于促进大数据发展的意见》","updateTimeStamp":"2017-02-22"}]
     * totalPage : 1
     * isNext : false
     */

    private String totalPage;
    private boolean isNext;
    private List<PolicylistBean> policylist;

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

    public List<PolicylistBean> getPolicylist() {
        return policylist;
    }

    public void setPolicylist(List<PolicylistBean> policylist) {
        this.policylist = policylist;
    }

    public static class PolicylistBean {
        /**
         * policiesSources : 山东省服务外包网
         * sortKey : 1487734575000
         * policiesId : 79
         * category : 省级
         * title : 山东省出台全省服务外包产业发展规划
         * updateTimeStamp : 2017-02-22
         */

        private String policiesSources;
        private long sortKey;
        private String policiesId;
        private String category;
        private String title;
        private String updateTimeStamp;
        private String image;

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getPoliciesSources() {
            return policiesSources;
        }

        public void setPoliciesSources(String policiesSources) {
            this.policiesSources = policiesSources;
        }

        public long getSortKey() {
            return sortKey;
        }

        public void setSortKey(long sortKey) {
            this.sortKey = sortKey;
        }

        public String getPoliciesId() {
            return policiesId;
        }

        public void setPoliciesId(String policiesId) {
            this.policiesId = policiesId;
        }

        public String getCategory() {
            return category;
        }

        public void setCategory(String category) {
            this.category = category;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getUpdateTimeStamp() {
            return updateTimeStamp;
        }

        public void setUpdateTimeStamp(String updateTimeStamp) {
            this.updateTimeStamp = updateTimeStamp;
        }
    }
}
