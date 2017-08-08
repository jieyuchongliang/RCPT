package com.rcpt.bean;

/**
 * Created by 860617003 on 2017/6/7.
 */

public class SystemUserInfoBean {

    private String USER_ID;//用户id（string 必须）
    private String USERNAME;// 必须）   ②：用户名（string
    private String PASSWORD;//必须）密码（string
    private String NAME;// 必须）  ③：姓名（string
    private String ROLE_ID;// 必须）     ④：权限id（string
    private String BZ;// 必须）   ⑤：备注（string
    private String EMAIL;//必须） ⑥：邮箱（string
    private String NUMBER;// 必须） ⑦：编号（string
    private String PHONE;// 必须）  ⑧：手机号（string
    private String AUTHORITIES;//必须） ⑨：商务局id（string

    private String roleName;
    private String authoritiesName;

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getAuthoritiesName() {
        return authoritiesName;
    }

    public void setAuthoritiesName(String authoritiesName) {
        this.authoritiesName = authoritiesName;
    }

    public String getUSER_ID() {
        return USER_ID;
    }

    public void setUSER_ID(String USER_ID) {
        this.USER_ID = USER_ID;
    }

    public String getUSERNAME() {
        return USERNAME;
    }

    public void setUSERNAME(String USERNAME) {
        this.USERNAME = USERNAME;
    }

    public String getPASSWORD() {
        return PASSWORD;
    }

    public void setPASSWORD(String PASSWORD) {
        this.PASSWORD = PASSWORD;
    }

    public String getNAME() {
        return NAME;
    }

    public void setNAME(String NAME) {
        this.NAME = NAME;
    }

    public String getROLE_ID() {
        return ROLE_ID;
    }

    public void setROLE_ID(String ROLE_ID) {
        this.ROLE_ID = ROLE_ID;
    }

    public String getBZ() {
        return BZ;
    }

    public void setBZ(String BZ) {
        this.BZ = BZ;
    }

    public String getEMAIL() {
        return EMAIL;
    }

    public void setEMAIL(String EMAIL) {
        this.EMAIL = EMAIL;
    }

    public String getNUMBER() {
        return NUMBER;
    }

    public void setNUMBER(String NUMBER) {
        this.NUMBER = NUMBER;
    }

    public String getPHONE() {
        return PHONE;
    }

    public void setPHONE(String PHONE) {
        this.PHONE = PHONE;
    }

    public String getAUTHORITIES() {
        return AUTHORITIES;
    }

    public void setAUTHORITIES(String AUTHORITIES) {
        this.AUTHORITIES = AUTHORITIES;
    }
}
