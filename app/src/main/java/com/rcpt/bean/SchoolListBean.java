package com.rcpt.bean;

import java.util.List;

/**
 * Created by lvzp on 2017/3/29.
 * 类描述：
 */

public class SchoolListBean {


    /**
     * totalPage : 1
     * isNext : false
     * schooLlist : [{"schoolLog":"uploadFiles/registerImg/a1c21fad1d5c42c1a3c1551869db441f.jpg","creationTime":"1924年02月23日","schoolId":"065fe60ff9e6e345","batch":"一批","schoolType":"综合类","schoolName":"新疆大学","addressCity":"乌鲁木齐市"},{"schoolLog":"uploadFiles/registerImg/03da55b8276545309d6932ab4ea45a0d.jpg","creationTime":"2002年02月23日","schoolId":"067456168476c8be","batch":"提前批","schoolType":"综合类","schoolName":"淄博职业学院","addressCity":"淄博市"},{"schoolLog":"uploadFiles/registerImg/a3ca8fb331044b5db2a62da9be17932d.jpg","creationTime":"1950年10月29日","schoolId":"1e0f24d34d7ce363","batch":"一批","schoolType":"综合类","schoolName":"山东师范大学","addressCity":"济南市"},{"schoolLog":"uploadFiles/registerImg/7e54f36c853140e08b003509de467fc4.jpg","creationTime":"1901年02月01日","schoolId":"29e341b37679d9eb","batch":"提前批","schoolType":"综合类","schoolName":"山东大学","addressCity":"济南市"},{"schoolLog":"uploadFiles/registerImg/04acfeec64ed496cbc1ed51bbb5a83b8.jpg","creationTime":"2011年03月09日","schoolId":"323b453885f5181f","batch":"提前批","schoolType":"管理类","schoolName":"123456789","addressCity":"济南市"},{"schoolLog":"uploadFiles/registerImg/ef0a3dcbb279407a8381771cf6482f8c.jpg","creationTime":"1974年02月23日","schoolId":"7532fc6521bfd130","batch":"二批","schoolType":"综合类","schoolName":"泰山医学院","addressCity":"济南市"},{"schoolLog":"uploadFiles/registerImg/1bf4adb93c5041d1a5543ff31eaefb4e.jpg","creationTime":"1955年01月01日","schoolId":"85aa04f94b0c2828","batch":"二批","schoolType":"综合类","schoolName":"曲阜师范大学","addressCity":"济宁市"},{"schoolLog":"uploadFiles/registerImg/a0fb9d94c3d348ed851f4558701f21ba.jpg","creationTime":"1951年02月23日","schoolId":"9237ce9a3b50875e","batch":"二批","schoolType":"医学类","schoolName":"潍坊医学院","addressCity":"济南市"},{"schoolLog":"uploadFiles/registerImg/e2c4bb39d53f44f7840221670a3aba54.jpg","creationTime":"1909年02月23日","schoolId":"9eb56e66f7b9f3de","batch":"提前批","schoolType":"综合类","schoolName":"兰州大学","addressCity":"兰州市"},{"schoolLog":"uploadFiles/registerImg/05e322f85cdc4297ad1771b0e90d263d.jpg","creationTime":"1924年02月23日","schoolId":"ecd6192818cb1232","batch":"一批","schoolType":"综合类","schoolName":"中国海洋大学","addressCity":"济南市"}]
     */

    private String totalPage;
    private boolean isNext;
    private List<SchooLlistBean> schooLlist;

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

    public List<SchooLlistBean> getSchooLlist() {
        return schooLlist;
    }

    public void setSchooLlist(List<SchooLlistBean> schooLlist) {
        this.schooLlist = schooLlist;
    }

    public static class SchooLlistBean {
        /**
         * schoolLog : uploadFiles/registerImg/a1c21fad1d5c42c1a3c1551869db441f.jpg
         * creationTime : 1924年02月23日
         * schoolId : 065fe60ff9e6e345
         * batch : 一批
         * schoolType : 综合类
         * schoolName : 新疆大学
         * addressCity : 乌鲁木齐市
         */

        private String schoolLog;
        private String creationTime;
        private String schoolId;
        private String batch;
        private String schoolType;
        private String schoolName;
        private String addressCity;

        public String getSchoolLog() {
            return schoolLog;
        }

        public void setSchoolLog(String schoolLog) {
            this.schoolLog = schoolLog;
        }

        public String getCreationTime() {
            return creationTime;
        }

        public void setCreationTime(String creationTime) {
            this.creationTime = creationTime;
        }

        public String getSchoolId() {
            return schoolId;
        }

        public void setSchoolId(String schoolId) {
            this.schoolId = schoolId;
        }

        public String getBatch() {
            return batch;
        }

        public void setBatch(String batch) {
            this.batch = batch;
        }

        public String getSchoolType() {
            return schoolType;
        }

        public void setSchoolType(String schoolType) {
            this.schoolType = schoolType;
        }

        public String getSchoolName() {
            return schoolName;
        }

        public void setSchoolName(String schoolName) {
            this.schoolName = schoolName;
        }

        public String getAddressCity() {
            return addressCity;
        }

        public void setAddressCity(String addressCity) {
            this.addressCity = addressCity;
        }
    }
}
