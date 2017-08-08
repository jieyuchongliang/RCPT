package com.rcpt.http.bean;

/**
 * Created by 860116021 on 2017/3/23.
 */

public class PhoneRegisterBean {
    private String account;
    private String pwd;
    private String phone;
    private String regType;
    private String random;

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getRegType() {
        return regType;
    }

    public void setRegType(String regType) {
        this.regType = regType;
    }

    public String getRandom() {
        return random;
    }

    public void setRandom(String random) {
        this.random = random;
    }
}
