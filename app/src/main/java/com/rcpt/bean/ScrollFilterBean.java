package com.rcpt.bean;

import java.util.List;

/**
 * Created by lvzp on 2017/4/28.
 * 类描述：
 */

public class ScrollFilterBean {


    private List<TypeListBean> typeList;
    private List<BatchListBean> batchList;

    public List<TypeListBean> getTypeList() {
        return typeList;
    }

    public void setTypeList(List<TypeListBean> typeList) {
        this.typeList = typeList;
    }

    public List<BatchListBean> getBatchList() {
        return batchList;
    }

    public void setBatchList(List<BatchListBean> batchList) {
        this.batchList = batchList;
    }

    public static class TypeListBean {
        /**
         * type : 综合类
         * distinguish_id : 1
         */

        private String type;
        private String distinguish_id;

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getDistinguish_id() {
            return distinguish_id;
        }

        public void setDistinguish_id(String distinguish_id) {
            this.distinguish_id = distinguish_id;
        }
    }

    public static class BatchListBean {
        /**
         * batch : 提前批
         * distinguish_id : 1
         */

        private String batch;
        private String distinguish_id;

        public String getBatch() {
            return batch;
        }

        public void setBatch(String batch) {
            this.batch = batch;
        }

        public String getDistinguish_id() {
            return distinguish_id;
        }

        public void setDistinguish_id(String distinguish_id) {
            this.distinguish_id = distinguish_id;
        }
    }
}
