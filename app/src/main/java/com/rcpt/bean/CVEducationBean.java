package com.rcpt.bean;

/**
 * Created by lvzp on 2017/3/8.
 * 类描述：
 */

public class CVEducationBean {

    private String id;
    private String schoolName;//学校名称
    private String diploma;//学历/学位
    private String enrollTime;//入学时间
    private String graduationTime;//毕业时间
    private String majorName;//专业名称

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public String getDiploma() {
        return diploma;
    }

    public void setDiploma(String diploma) {
        this.diploma = diploma;
    }

    public String getEnrollTime() {
        return enrollTime;
    }

    public void setEnrollTime(String enrollTime) {
        this.enrollTime = enrollTime;
    }

    public String getGraduationTime() {
        return graduationTime;
    }

    public void setGraduationTime(String graduationTime) {
        this.graduationTime = graduationTime;
    }

    public String getMajorName() {
        return majorName;
    }

    public void setMajorName(String majorName) {
        this.majorName = majorName;
    }
}
