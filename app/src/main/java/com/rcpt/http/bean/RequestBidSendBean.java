package com.rcpt.http.bean;

/**
 * Created by lvzp on 2017/4/19.
 * 类描述：
 */

public class RequestBidSendBean {

    private String companyId;//①：公司id（   必须）
    private String projectId;//   ②：项目接发包id（  必须）
    private String name;//    ③：联系人名字（必须）
    private String phone;//   ④：联系电话（ 必须）
    private String email;// ⑤：联系邮箱(必须）
    private String bidCompanyName;// ⑥：竞标公司名字(必须）
    private String Introduction;// ⑦：备注（   ）

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
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
