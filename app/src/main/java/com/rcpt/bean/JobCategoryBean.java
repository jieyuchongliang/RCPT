package com.rcpt.bean;

import java.util.List;

/**
 * Created by lvzp on 2017/3/23.
 * 类描述：
 */

public class JobCategoryBean {


    /**
     * name : 销售业务>>
     * id : 2
     * child : [{"name":"销售代表","id":3,"parentId":2},{"name":"客户代表","id":4,"parentId":2},{"name":"销售工程师","id":5,"parentId":2},{"name":"渠道/分销专员","id":6,"parentId":2},{"name":"区域销售专员/助理","id":7,"parentId":2},{"name":"业务拓展专员/助理","id":8,"parentId":2},{"name":"大客户销售代表","id":9,"parentId":2},{"name":"电话销售","id":10,"parentId":2},{"name":"网络/在线销售","id":11,"parentId":2},{"name":"团购业务员","id":12,"parentId":2},{"name":"销售业务跟单","id":13,"parentId":2},{"name":"医药代表","id":14,"parentId":2},{"name":"其他","id":15,"parentId":2}]
     */

    private String name;
    private int id;
    private List<ChildBean> child;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<ChildBean> getChild() {
        return child;
    }

    public void setChild(List<ChildBean> child) {
        this.child = child;
    }

    public static class ChildBean {
        /**
         * name : 销售代表
         * id : 3
         * parentId : 2
         */

        private String name;
        private String id;
        private String parentId;

        private boolean isSelected;

        public boolean isSelected() {
            return isSelected;
        }

        public void setSelected(boolean selected) {
            isSelected = selected;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getParentId() {
            return parentId;
        }

        public void setParentId(String parentId) {
            this.parentId = parentId;
        }
    }
}
