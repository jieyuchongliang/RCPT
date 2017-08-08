package com.rcpt.bean;

/**
 * Created by lvzp on 2017/3/17.
 * 类描述：
 */

public class PersonInfoBean {


    /**
     * workDate : 2015-11-11
     * contactInfo : 18306431704
     * mail : 984006336@qq.com
     * sex : 1
     * name : 用户名
     * graduteSchool : 淄博职业学院
     * birthDate : 2014-03-11
     * overseasWork :
     * maritalStatus :
     */

    private String workDate = "";//参加工作日期
    private String contactInfo = "";//联系电话
    private String mail = "";//联系邮箱
    private String sex = "";//性别
    private String name = "";//名字
    private String birthDate = "";//出生日期
    private String overseasWork = "";//是否有海外工作经历
    private String maritalStatus = "";//婚姻状态
    private String userPicture;//头像地址
    private String educationVal;//学历
    private String education;

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getEducationVal() {
        return educationVal;
    }

    public void setEducationVal(String educationVal) {
        this.educationVal = educationVal;
    }

    public String getUserPicture() {
        return userPicture;
    }

    public void setUserPicture(String userPicture) {
        this.userPicture = userPicture;
    }

    public String getWorkDate() {
        return workDate;
    }

    public void setWorkDate(String workDate) {
        this.workDate = workDate;
    }

    public String getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(String contactInfo) {
        this.contactInfo = contactInfo;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getOverseasWork() {
        return overseasWork;
    }

    public void setOverseasWork(String overseasWork) {
        this.overseasWork = overseasWork;
    }

    public String getMaritalStatus() {
        return maritalStatus;
    }

    public void setMaritalStatus(String maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    public void dataToShow() {
        sexCodeToString();
        maritalCodeToString();
        overseasWorkCodeToString();
    }

    public void dataToUpload() {
        sexStringToCode();
        maritalStringToCode();
        overseasWorkStringToCode();
    }

    private void sexCodeToString() {
        if (sex == null) return;
        switch (sex) {
            case "0":
                sex = "男";
                break;
            case "1":
                sex = "女";
                break;
            default:
                sex = "";
                break;
        }
    }

    private void maritalCodeToString() {
        if (maritalStatus == null) return;
        switch (maritalStatus) {
            case "0":
                maritalStatus = "未婚";
                break;
            case "1":
                maritalStatus = "已婚";
                break;
            case "2":
                maritalStatus = "离异";
                break;
            case "3":
                maritalStatus = "丧偶";
                break;
            default:
                maritalStatus = "";
                break;
        }
    }

    private void overseasWorkCodeToString() {
        if (overseasWork == null) return;
        switch (overseasWork) {
            case "0":
                overseasWork = "有";
                break;
            case "1":
                overseasWork = "无";
                break;
            default:
                overseasWork = "";
                break;
        }
    }

    private void sexStringToCode() {
        if (sex == null) return;
        switch (sex) {
            case "男":
                sex = "0";
                break;
            case "女":
                sex = "1";
                break;
            default:
                sex = "";
                break;
        }
    }

    private void maritalStringToCode() {
        if (maritalStatus == null) return;
        switch (maritalStatus) {
            case "未婚":
                maritalStatus = "0";
                break;
            case "已婚":
                maritalStatus = "1";
                break;
            case "离异":
                maritalStatus = "2";
                break;
            case "丧偶":
                maritalStatus = "3";
                break;
            default:
                maritalStatus = "";
                break;
        }
    }

    private void overseasWorkStringToCode() {
        if (overseasWork == null) return;
        switch (overseasWork) {
            case "有":
                overseasWork = "0";
                break;
            case "无":
                overseasWork = "1";
                break;
            default:
                overseasWork = "";
                break;
        }
    }


}
