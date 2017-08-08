package com.rcpt.http.bean;

/**
 * 接发包,人员竞标的数据bean
 * Created by 860617010 on 2017/5/19.
 */

public class RequestPersonBidSendBean {
    private String companyId;              //①：公司id（String companyId   必须）
    private String personnelId;     //②：人员接发包id（String personnelId   必须）
    private String name;            //③：联系人名字（String name   必须）
    private String phone;           //④：联系电话（String phone   必须）
    private String email;           //⑤：联系邮箱(String email 必须）
    private String bidCompanyName;  //⑥：竞标公司名字(String bidCompanyName 必须）
    private String Introduction;    //⑦：备注（String Introduction   ）

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String id) {
        this.companyId = id;
    }

    public String getPersonnelId() {
        return personnelId;
    }

    public void setPersonnelId(String personnelId) {
        this.personnelId = personnelId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBidCompanyName() {
        return bidCompanyName;
    }

    public void setBidCompanyName(String bidCompanyName) {
        this.bidCompanyName = bidCompanyName;
    }

    public String getIntroduction() {
        return Introduction;
    }

    public void setIntroduction(String introduction) {
        Introduction = introduction;
    }

}
