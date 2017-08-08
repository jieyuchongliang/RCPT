package com.rcpt.bean;

import java.util.List;

/**
 * Created by lvzp on 2017/2/24.
 * 类描述：
 */

public class RecruitListBean {


    /**
     * totalPage : 3
     * isNext : true
     * personlist : [{"positionName":"美工","companyId":"8f8a21b09fd2adec","education":"大专","workPlaceCity":"济南市","companyName":"富士软件科技（山东）有限公司","salary":"2001-4000元/月","updateTimeStamp":"17-03-10","recruitmentInfoId":10},{"positionName":"对日android软件工程师","companyId":"8f8a21b09fd2adec","workPlaceCity":"济南市","companyName":"富士软件科技（山东）有限公司","salary":"面议","updateTimeStamp":"17-03-10","recruitmentInfoId":5},{"positionName":"美工设计","companyId":"8f8a21b09fd2adec","education":"大专","workPlaceCity":"济南市","companyName":"富士软件科技（山东）有限公司","salary":"2001-4000元/月","updateTimeStamp":"17-02-23","recruitmentInfoId":9},{"positionName":"对日软件工程师2016应届毕业生","companyId":"8f8a21b09fd2adec","education":"本科","workPlaceCity":"济南市","companyName":"富士软件科技（山东）有限公司","salary":"2001-4000元/月","updateTimeStamp":"17-02-23","recruitmentInfoId":8},{"positionName":"电子智能工程师 (计算机软件)","companyId":"8f8a21b09fd2adec","workPlaceCity":"济南市","companyName":"富士软件科技（山东）有限公司","salary":"面议","updateTimeStamp":"17-02-23","recruitmentInfoId":7},{"positionName":"系统分析员、架构设计师","companyId":"8f8a21b09fd2adec","education":"大专","workPlaceCity":"济南市","companyName":"富士软件科技（山东）有限公司","salary":"面议","updateTimeStamp":"17-02-23","recruitmentInfoId":6},{"positionName":"人事专员","companyId":"8f8a21b09fd2adec","workPlaceCity":"济南市","companyName":"富士软件科技（山东）有限公司","salary":"2001-4000元/月","updateTimeStamp":"17-02-23","recruitmentInfoId":4},{"positionName":"对日C#/C++软件工程师","companyId":"8f8a21b09fd2adec","workPlaceCity":"济南市","companyName":"富士软件科技（山东）有限公司","salary":"面议","updateTimeStamp":"17-02-23","recruitmentInfoId":3},{"positionName":"对日.NET软件工程师","companyId":"8f8a21b09fd2adec","education":"本科","workPlaceCity":"济南市","companyName":"富士软件科技（山东）有限公司","salary":"2001-4000元/月","updateTimeStamp":"17-02-23","recruitmentInfoId":2},{"positionName":"对日JAVA软件工程师（SE/PG）","companyId":"8f8a21b09fd2adec","education":"本科","workPlaceCity":"济南市","companyName":"富士软件科技（山东）有限公司","salary":"面议","updateTimeStamp":"17-02-23","recruitmentInfoId":1},{"positionName":"济南市钣金工，电焊工招聘","companyId":"cbd7285d166e5ff3","education":"高中及以下","workPlaceCity":"济南市","companyName":"济南威力机器有限公司","salary":"面议","updateTimeStamp":"17-02-23","recruitmentInfoId":1},{"positionName":"济南市汽修工招聘","companyId":"83c0b2cdd14a276e","education":"中专","workPlaceCity":"济南市","companyName":"山东绿环动力设备有限公司","salary":"面议","updateTimeStamp":"17-02-23","recruitmentInfoId":3},{"positionName":"济南市场采购","companyId":"83c0b2cdd14a276e","education":"大专","workPlaceCity":"济南市","companyName":"山东绿环动力设备有限公司","salary":"2001-4000元/月","updateTimeStamp":"17-02-23","recruitmentInfoId":2},{"positionName":"外贸销售 (大型设备/机电设备/重工业)","companyId":"83c0b2cdd14a276e","workPlaceCity":"济南市","companyName":"山东绿环动力设备有限公司","salary":"4001-6000元/月","updateTimeStamp":"17-02-23","recruitmentInfoId":1},{"positionName":"病理医生","companyId":"6358ed82974d20e5","education":"本科","workPlaceCity":"济南市","companyName":"济南迪安医学检验中心有限公司","salary":"4001-6000元/月","updateTimeStamp":"17-02-23","recruitmentInfoId":3},{"positionName":"市场专员","companyId":"6358ed82974d20e5","education":"本科","workPlaceCity":"济南市","companyName":"济南迪安医学检验中心有限公司","salary":"4001-6000元/月","updateTimeStamp":"17-02-23","recruitmentInfoId":2},{"positionName":"济南销售代表","companyId":"6358ed82974d20e5","education":"大专","workPlaceCity":"济南市","companyName":"济南迪安医学检验中心有限公司","salary":"4001-6000元/月","updateTimeStamp":"17-02-23","recruitmentInfoId":1},{"positionName":"生产助理","companyId":"e93db86b574da0cb","workPlaceCity":"济南市","companyName":"济南东泰兴工艺品有限公司","updateTimeStamp":"17-02-23","recruitmentInfoId":1}]
     */

    private String totalPage;
    private boolean isNext;
    private List<JobListBean> personlist;
    private List<CVListBean> talentPoolList;

    public boolean isNext() {
        return isNext;
    }

    public void setNext(boolean next) {
        isNext = next;
    }

    public List<CVListBean> getTalentPoolList() {
        return talentPoolList;
    }

    public void setTalentPoolList(List<CVListBean> talentPoolList) {
        this.talentPoolList = talentPoolList;
    }

    public String getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(String totalPage) {
        this.totalPage = totalPage;
    }


    public List<JobListBean> getPersonlist() {
        return personlist;
    }

    public void setPersonlist(List<JobListBean> personlist) {
        this.personlist = personlist;
    }

    public static class CVListBean {

        /**
         * cvId : 1
         * userPicture : uploadFiles/userPicure/b2d6490091cc4fcc8c0c091fe642b9bd.jpg
         * selfAppraisal :
         * sex : 0
         * position1 : 销售工程师
         * position2 : 渠道/分销专员
         * position3 : 业务拓展专员/助理
         * position4 : 区域销售专员/助理
         * position5 : 大客户销售代表
         * userId : 5d87fe7c656c4f8fb8d3b51c9c311cee
         * updateTimeStamp : 17-03-27
         * expectedAalary : 6001-8000元/月
         * name : 逸致
         * workYear : 7
         * expectedWorkCity : 秦皇岛市
         * age : 23
         */
        private String education = "";

        public String getEducation() {
            return education;
        }

        public void setEducation(String education) {
            this.education = education;
        }

        private String cvId;
        private String userPicture;
        private String selfAppraisal;
        private int sex;
        private String position1;
        private String position2;
        private String position3;
        private String position4;
        private String position5;
        private String userId;
        private String updateTimeStamp;
        private String expectedAalary;
        private String name;
        private int workYear;
        private String expectedWorkCity;
        private String age;

        public String getPosition() {
            return position1 + position2 + position3 + position4 + position5;
        }

        public String getCvId() {
            return cvId;
        }

        public void setCvId(String cvId) {
            this.cvId = cvId;
        }

        public String getUserPicture() {
            return userPicture;
        }

        public void setUserPicture(String userPicture) {
            this.userPicture = userPicture;
        }

        public String getSelfAppraisal() {
            return selfAppraisal;
        }

        public void setSelfAppraisal(String selfAppraisal) {
            this.selfAppraisal = selfAppraisal;
        }

        public int getSex() {
            return sex;
        }

        public void setSex(int sex) {
            this.sex = sex;
        }

        public String getPosition1() {
            return position1;
        }

        public void setPosition1(String position1) {
            this.position1 = position1;
        }

        public String getPosition2() {
            return position2;
        }

        public void setPosition2(String position2) {
            this.position2 = position2;
        }

        public String getPosition3() {
            return position3;
        }

        public void setPosition3(String position3) {
            this.position3 = position3;
        }

        public String getPosition4() {
            return position4;
        }

        public void setPosition4(String position4) {
            this.position4 = position4;
        }

        public String getPosition5() {
            return position5;
        }

        public void setPosition5(String position5) {
            this.position5 = position5;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getUpdateTimeStamp() {
            return updateTimeStamp;
        }

        public void setUpdateTimeStamp(String updateTimeStamp) {
            this.updateTimeStamp = updateTimeStamp;
        }

        public String getExpectedAalary() {
            return expectedAalary;
        }

        public void setExpectedAalary(String expectedAalary) {
            this.expectedAalary = expectedAalary;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getWorkYear() {
            return workYear;
        }

        public void setWorkYear(int workYear) {
            this.workYear = workYear;
        }

        public String getExpectedWorkCity() {
            return expectedWorkCity;
        }

        public void setExpectedWorkCity(String expectedWorkCity) {
            this.expectedWorkCity = expectedWorkCity;
        }

        public String getAge() {
            return age;
        }

        public void setAge(String age) {
            this.age = age;
        }
    }

    public static class JobListBean {
        /**
         * positionName : 美工
         * companyId : 8f8a21b09fd2adec
         * education : 大专
         * workPlaceCity : 济南市
         * companyName : 富士软件科技（山东）有限公司
         * salary : 2001-4000元/月
         * updateTimeStamp : 17-03-10
         * recruitmentInfoId : 10
         */

        private String positionName;
        private String companyId;
        private String education;
        private String workPlaceProvince;
        private String workPlaceCity;
        private String companyName;
        private String salary;
        private String jobType;
        private String updateTimeStamp;
        private String recruitmentInfoId;


        public String getWorkPlaceProvince() {
            return workPlaceProvince;
        }

        public void setWorkPlaceProvince(String workPlaceProvince) {
            this.workPlaceProvince = workPlaceProvince;
        }

        public String getJobType() {
            return jobType;
        }

        public void setJobType(String jobType) {
            this.jobType = jobType;
        }

        public String getPositionName() {
            return positionName;
        }

        public void setPositionName(String positionName) {
            this.positionName = positionName;
        }

        public String getCompanyId() {
            return companyId;
        }

        public void setCompanyId(String companyId) {
            this.companyId = companyId;
        }

        public String getEducation() {
            return education;
        }

        public void setEducation(String education) {
            this.education = education;
        }

        public String getWorkPlaceCity() {
            return workPlaceCity;
        }

        public void setWorkPlaceCity(String workPlaceCity) {
            this.workPlaceCity = workPlaceCity;
        }

        public String getCompanyName() {
            return companyName;
        }

        public void setCompanyName(String companyName) {
            this.companyName = companyName;
        }

        public String getSalary() {
            return salary;
        }

        public void setSalary(String salary) {
            this.salary = salary;
        }

        public String getUpdateTimeStamp() {
            return updateTimeStamp;
        }

        public void setUpdateTimeStamp(String updateTimeStamp) {
            this.updateTimeStamp = updateTimeStamp;
        }

        public String getRecruitmentInfoId() {
            return recruitmentInfoId;
        }

        public void setRecruitmentInfoId(String recruitmentInfoId) {
            this.recruitmentInfoId = recruitmentInfoId;
        }
    }
}
