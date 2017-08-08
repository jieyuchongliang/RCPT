package com.rcpt.bean;

/**
 * Created by lvzp on 2017/3/20.
 * 类描述：
 */

public class CVInfoBean {


    /**
     * cvId : 1
     * userPicture : uploadFiles/userPicure/f91c05d38c754d13a95e2db385d7dc3f.jpg
     * publicSet : 公开
     * sex : 女
     * upTimestamp : 2017-03-20
     * userName : lv123
     * residence : 山东省济南市高新区东城逸家
     * cvName : 我的首个简历
     * startWorkDate : 2015-10-01
     */

    private String cvId;
    private String userPicture;
    private String publicSet;
    private String sex;
    private String upTimestamp;
    private String userName;
    private String residence;
    private String cvName;
    private String startWorkDate;
    private String workExp;


    public String getWorkExp() {
        return workExp;
    }

    public void setWorkExp(String workExp) {
        this.workExp = workExp;
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

    public String getPublicSet() {
        return publicSet;
    }

    public void setPublicSet(String publicSet) {
        this.publicSet = publicSet;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getUpTimestamp() {
        return upTimestamp;
    }

    public void setUpTimestamp(String upTimestamp) {
        this.upTimestamp = upTimestamp;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getResidence() {
        return residence;
    }

    public void setResidence(String residence) {
        this.residence = residence;
    }

    public String getCvName() {
        return cvName;
    }

    public void setCvName(String cvName) {
        this.cvName = cvName;
    }

    public String getStartWorkDate() {
        return startWorkDate;
    }

    public void setStartWorkDate(String startWorkDate) {
        this.startWorkDate = startWorkDate;
    }
}
