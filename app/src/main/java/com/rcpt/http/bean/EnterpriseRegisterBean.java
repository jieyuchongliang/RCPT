package com.rcpt.http.bean;

/**
 * Created by 860116021 on 2017/3/21.
 */

public class EnterpriseRegisterBean {
    private String account;         //账号
    private String pwd;             //密码
    private String phone;           //手机号
    private String email;           //邮箱
    private String comName;         //公司名称
    private String industry;        //行业
    private String province;        //省id
    private String city;            //市id
    private String addressDetail;   //省市地址
    private String licenseUrl;      //本地图片路径
    private String organize_code;   //组织机构代码
    private String contact;         //联系人
    private String address_detail;  //详细地址
    private String address_area;    //审核机关

    public String getAddress_area() {
        return address_area;
    }

    public void setAddress_area(String address_area) {
        this.address_area = address_area;
    }

    public String getOrganize_code() {
        return organize_code;
    }

    public void setOrganize_code(String organize_code) {
        this.organize_code = organize_code;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getAddress_detail() {
        return address_detail;
    }

    public void setAddress_detail(String address_detail) {
        this.address_detail = address_detail;
    }



    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


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

    public String getComName() {
        return comName;
    }

    public void setComName(String comName) {
        this.comName = comName;
    }

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddressDetail() {
        return addressDetail;
    }

    public void setAddressDetail(String addressDetail) {
        this.addressDetail = addressDetail;
    }

    public String getLicenseUrl() {
        return licenseUrl;
    }

    public void setLicenseUrl(String licenseUrl) {
        this.licenseUrl = licenseUrl;
    }
}

