package com.rcpt.bean;

/**
 * Created by lvzp on 2017/3/29.
 * 类描述：
 */

public class CVSelectBean {


    /**
     * cvId : 1
     * defaultCv : 0
     * cvName : 简历简历，上线了
     */

    private String cvId;
    private String defaultCv;
    private String cvName;

    public String getCvId() {
        return cvId;
    }

    public void setCvId(String cvId) {
        this.cvId = cvId;
    }

    public String getDefaultCv() {
        return defaultCv;
    }

    public void setDefaultCv(String defaultCv) {
        this.defaultCv = defaultCv;
    }

    public String getCvName() {
        return cvName;
    }

    public void setCvName(String cvName) {
        this.cvName = cvName;
    }
}
