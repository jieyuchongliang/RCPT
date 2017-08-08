package com.rcpt.bean;

import java.util.List;

/**
 * Created by lvzp on 2017/4/5.
 * 类描述：
 */

public class TestMajorListBean {


    /**
     * testlist : [{"duration":60,"paperType":"1","pointId":"13","pointName":"能力测试","name":"能力测试题1","totalPeoples":11,"id":253,"totalTestNum":13,"createTime1":"2017-03-09"},{"duration":31,"paperType":"2","pointId":"7","pointName":"Java3","name":"1","totalPeoples":6,"id":205,"totalTestNum":1,"createTime1":"2017-03-03"},{"duration":60,"paperType":"1","pointId":"1","pointName":"道路交通安全法律、法规和规章","name":"to ml","totalPeoples":5,"id":247,"totalTestNum":2,"createTime1":"2017-03-03"},{"duration":66,"paperType":"2","pointId":"1","pointName":"道路交通安全法律、法规和规章","name":"To ML","totalPeoples":1,"id":246,"totalTestNum":2,"createTime1":"2017-03-02"},{"duration":111,"paperType":"2","pointId":"1","pointName":"道路交通安全法律、法规和规章","name":"111","totalPeoples":1,"id":242,"totalTestNum":2,"createTime1":"2017-03-02"},{"duration":52,"paperType":"2","pointId":"3","pointName":"安全行车、文明驾驶基础知识","name":"分手大师的","totalPeoples":2,"id":245,"totalTestNum":1,"createTime1":"2017-03-02"},{"duration":52,"paperType":"2","pointId":"3","pointName":"安全行车、文明驾驶基础知识","name":"分手大师的","totalPeoples":0,"id":244,"totalTestNum":1,"createTime1":"2017-03-02"},{"duration":120,"paperType":"2","pointId":"7","pointName":"Java3","name":"三条数据","totalPeoples":3,"id":241,"totalTestNum":1,"createTime1":"2017-03-01"},{"duration":35,"paperType":"2","pointId":"1","pointName":"道路交通安全法律、法规和规章","name":"3332","totalPeoples":1,"id":239,"totalTestNum":2,"createTime1":"2017-03-01"},{"duration":222,"paperType":"2","pointId":"7","pointName":"Java3","name":"3333","totalPeoples":1,"id":238,"totalTestNum":1,"createTime1":"2017-03-01"}]
     * totalPage : 4
     * isNext : true
     */

    private String totalPage;
    private boolean isNext;
    private List<TestlistBean> testlist;

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

    public List<TestlistBean> getTestlist() {
        return testlist;
    }

    public void setTestlist(List<TestlistBean> testlist) {
        this.testlist = testlist;
    }

    public static class TestlistBean {
        /**
         * duration : 60
         * paperType : 1
         * pointId : 13
         * pointName : 能力测试
         * name : 能力测试题1
         * totalPeoples : 11
         * id : 253
         * totalTestNum : 13
         * createTime1 : 2017-03-09
         */

        private String duration;
        private String paperType;
        private String pointId;
        private String pointName;
        private String name;
        private int totalPeoples;
        private String id;
        private String totalTestNum;
        private String createTime1;

        public String getDuration() {
            return duration;
        }

        public void setDuration(String duration) {
            this.duration = duration;
        }

        public String getPaperType() {
            return paperType;
        }

        public void setPaperType(String paperType) {
            this.paperType = paperType;
        }

        public String getPointId() {
            return pointId;
        }

        public void setPointId(String pointId) {
            this.pointId = pointId;
        }

        public String getPointName() {
            return pointName;
        }

        public void setPointName(String pointName) {
            this.pointName = pointName;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getTotalPeoples() {
            return totalPeoples;
        }

        public void setTotalPeoples(int totalPeoples) {
            this.totalPeoples = totalPeoples;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getTotalTestNum() {
            return totalTestNum;
        }

        public void setTotalTestNum(String totalTestNum) {
            this.totalTestNum = totalTestNum;
        }

        public String getCreateTime1() {
            return createTime1;
        }

        public void setCreateTime1(String createTime1) {
            this.createTime1 = createTime1;
        }
    }
}
