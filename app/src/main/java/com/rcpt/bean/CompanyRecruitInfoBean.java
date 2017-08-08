package com.rcpt.bean;

/**
 * Created by lvzp on 2017/4/18.
 * 类描述：
 */

public class CompanyRecruitInfoBean {


    /**
     * recruitmentNumber : 10
     * orientedGroup : 0
     * workPlaceCityName : 济南市
     * education : 4
     * positionType : 224
     * jobTypeName : 全职
     * salaryName : 10001-15000元/月
     * workingLifeName : 3年以上
     * workPlaceProvince : 16
     * positionTypeName : 手机软件开发工程师
     * receiveEmail : 984006336@qq.com
     * salary : 7
     * userId : d787bd11cb6b41a4b3dfbcd961bddb6d
     * positionName : Android 软件开发工程师
     * workPlaceProvinceName : 山东省
     * workPlaceCity : 170
     * educationName : 硕士
     * jobDescription : Android 开发工程师
     * <p>
     * 岗位职责
     * 1. 参与产品需求沟通, 负责Android项目开发;
     * 2. 负责产品的架构设计和性能优化;
     * 3. 负责软件开发过程中的问题分析和总结，提供建议帮助改善研发流程。
     * <p>
     * 任职资格
     * 1. 熟练掌握JAVA, 对Android整体架构有较为深入的了解, 熟悉Android的UI/网络/数据库开发；
     * 2. 熟悉内存和性能优化的方法
     * 3. 有良好的编程习惯, 代码结构清晰, 命名规范;
     * 4. 具备良好的分析、解决问题的能力; 具备良好的沟通能力和优秀的团队协作能力;
     * 5. 有音视频多媒体开发经验者优先; 有 IOS 开发经验者优先。
     * jobType : 0
     * workingLife : 3
     * recruitmentInfoId : 1
     */

    private String recruitmentNumber;
    private String orientedGroup;
    private String workPlaceCityName;
    private String education;
    private String positionType;
    private String jobTypeName;
    private String salaryName;
    private String workingLifeName;
    private String workPlaceProvince;
    private String positionTypeName;
    private String receiveEmail;
    private String salary;
    private String userId;
    private String positionName;
    private String workPlaceProvinceName;
    private String workPlaceCity;
    private String educationName;
    private String jobDescription;
    private String jobType;
    private String workingLife;
    private String recruitmentInfoId;
    private String workAddress;
    private String total;

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public void setWorkAddress(String workAddress) {
        this.workAddress = workAddress;
    }

    public String getRecruitmentNumber() {
        return recruitmentNumber;
    }

    public void setRecruitmentNumber(String recruitmentNumber) {
        this.recruitmentNumber = recruitmentNumber;
    }

    public String getWorkAddress() {
        return workAddress;
    }

    public String getOrientedGroup() {
        return orientedGroup.equals("0") ? "社会招聘" : "校园招聘";
    }

    public void setOrientedGroup(String orientedGroup) {
        this.orientedGroup = orientedGroup;
    }

    public String getWorkPlaceCityName() {
        return workPlaceCityName;
    }

    public void setWorkPlaceCityName(String workPlaceCityName) {
        this.workPlaceCityName = workPlaceCityName;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getPositionType() {
        return positionType;
    }

    public void setPositionType(String positionType) {
        this.positionType = positionType;
    }

    public String getJobTypeName() {
        return jobTypeName;
    }

    public void setJobTypeName(String jobTypeName) {
        this.jobTypeName = jobTypeName;
    }

    public String getSalaryName() {
        return salaryName;
    }

    public void setSalaryName(String salaryName) {
        this.salaryName = salaryName;
    }

    public String getWorkingLifeName() {
        return workingLifeName;
    }

    public void setWorkingLifeName(String workingLifeName) {
        this.workingLifeName = workingLifeName;
    }

    public String getWorkPlaceProvince() {
        return workPlaceProvince;
    }

    public void setWorkPlaceProvince(String workPlaceProvince) {
        this.workPlaceProvince = workPlaceProvince;
    }

    public String getPositionTypeName() {
        return positionTypeName;
    }

    public void setPositionTypeName(String positionTypeName) {
        this.positionTypeName = positionTypeName;
    }

    public String getReceiveEmail() {
        return receiveEmail;
    }

    public void setReceiveEmail(String receiveEmail) {
        this.receiveEmail = receiveEmail;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPositionName() {
        return positionName;
    }

    public void setPositionName(String positionName) {
        this.positionName = positionName;
    }

    public String getWorkPlaceProvinceName() {
        return workPlaceProvinceName;
    }

    public void setWorkPlaceProvinceName(String workPlaceProvinceName) {
        this.workPlaceProvinceName = workPlaceProvinceName;
    }

    public String getWorkPlaceCity() {
        return workPlaceCity;
    }

    public void setWorkPlaceCity(String workPlaceCity) {
        this.workPlaceCity = workPlaceCity;
    }

    public String getEducationName() {
        return educationName;
    }

    public void setEducationName(String educationName) {
        this.educationName = educationName;
    }

    public String getJobDescription() {
        return jobDescription;
    }

    public void setJobDescription(String jobDescription) {
        this.jobDescription = jobDescription;
    }

    public String getJobType() {
        return jobType;
    }

    public void setJobType(String jobType) {
        this.jobType = jobType;
    }

    public String getWorkingLife() {
        return workingLife;
    }

    public void setWorkingLife(String workingLife) {
        this.workingLife = workingLife;
    }

    public String getRecruitmentInfoId() {
        return recruitmentInfoId;
    }

    public void setRecruitmentInfoId(String recruitmentInfoId) {
        this.recruitmentInfoId = recruitmentInfoId;
    }
}
