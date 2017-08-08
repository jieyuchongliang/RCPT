package com.rcpt.bean;

import com.rcpt.Constant;
import com.rcpt.http.ParamNames;

import java.io.Serializable;

/**
 * 作者：吕振鹏
 * 创建时间：10月21日
 * 时间：15:19
 * 版本：v1.0.0
 * 类描述：
 * 修改时间：
 */

public class User implements Serializable {

    @ParamNames("user_name")
    private String userName;
    @ParamNames("userType")
    private String userType = Constant.UserType.PERSON.getValue();
    @ParamNames("picture")
    private String avatar;
    @ParamNames("user_id")
    private String token;

    private boolean groupRole;
    private boolean personalRole;
    private boolean sysUserRole;

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isGroupRole() {
        return groupRole;
    }

    public void setGroupRole(boolean groupRole) {
        this.groupRole = groupRole;
    }

    public boolean isPersonalRole() {
        return personalRole;
    }

    public void setPersonalRole(boolean personalRole) {
        this.personalRole = personalRole;
    }

    public boolean isSysUserRole() {
        return sysUserRole;
    }

    public void setSysUserRole(boolean sysUserRole) {
        this.sysUserRole = sysUserRole;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
