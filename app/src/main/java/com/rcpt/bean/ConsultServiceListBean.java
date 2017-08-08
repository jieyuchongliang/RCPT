package com.rcpt.bean;

import com.rcpt.http.ParamNames;

import java.util.List;

/**
 * Created by lvzp on 2017/2/23.
 * 类描述：
 */

public class ConsultServiceListBean {


    /**
     * consultancyList : [{"companyId":"user002","updateDate":"2017-03-13","companyName":"济南市讯和公司讯和公司讯和公司讯和公司","logo":"uploadFiles/registerImg/828d606b920a4b6ea192e30ec7441b5c.jpg","id":96,"category":"人力资源","title":"瑟瑟缩缩三过长会怎么样啊有没有做省略号处理"},{"companyId":"dazd","updateDate":"2017-03-09","companyName":"济南迪安医学检验中心有限公司","logo":"uploadFiles/registerImg/20aeb1035c85456cb35d63f92d6b80c5.jpg","id":41,"category":"人力资源","title":"小筑书屋 心理咨询 "},{"companyId":"dazd","updateDate":"2017-02-13","companyName":"济南迪安医学检验中心有限公司","logo":"uploadFiles/registerImg/ae77affe41ad4407a41bbe7bbc9359df.gif","id":43,"category":"人力资源","title":"补22年养老保费低领取高女45岁50岁男55岁退休 补22年养老保费低领取高女45岁50岁男55岁退"},{"companyId":"guser02","updateDate":"2017-01-24","companyName":"aa讯和公司讯和公司讯和公司讯和公司","logo":"uploadFiles/registerImg/d7f1d3ecd79143bb9f799217a5bd8b2b.jpg","id":76,"category":"人力资源","title":"咨询人006"}]
     * totalPage : 0
     * hasNext : false
     */

    private String totalPage;
    @ParamNames("isNext")
    private boolean hasNext;
    private List<ConsultancyListBean> consultancyList;

    public String getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(String totalPage) {
        this.totalPage = totalPage;
    }

    public boolean hasNext() {
        return hasNext;
    }

    public void setIsNext(boolean isNext) {
        this.hasNext = isNext;
    }

    public List<ConsultancyListBean> getConsultancyList() {
        return consultancyList;
    }

    public void setConsultancyList(List<ConsultancyListBean> consultancyList) {
        this.consultancyList = consultancyList;
    }

    public static class ConsultancyListBean {
        /**
         * companyId : user002
         * updateDate : 2017-03-13
         * companyName : 济南市讯和公司讯和公司讯和公司讯和公司
         * logo : uploadFiles/registerImg/828d606b920a4b6ea192e30ec7441b5c.jpg
         * id : 96
         * category : 人力资源
         * title : 瑟瑟缩缩三过长会怎么样啊有没有做省略号处理
         */

        private String companyId;
        private String updateDate;
        private String companyName;
        private String logo;
        private String id;
        private String category;
        private String title;

        public String getCompanyId() {
            return companyId;
        }

        public void setCompanyId(String companyId) {
            this.companyId = companyId;
        }

        public String getUpdateDate() {
            return updateDate;
        }

        public void setUpdateDate(String updateDate) {
            this.updateDate = updateDate;
        }

        public String getCompanyName() {
            return companyName;
        }

        public void setCompanyName(String companyName) {
            this.companyName = companyName;
        }

        public String getLogo() {
            return logo;
        }

        public void setLogo(String logo) {
            this.logo = logo;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
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
    }
}
