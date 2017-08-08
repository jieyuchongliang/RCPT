package com.rcpt.bean;

/**
 * Created by 860617003 on 2017/5/23.
 */

public class VideoPermissionBean {


    /**
     * reason :
     * hasPermission : 0
     */

    private String reason;
    private int hasPermission;//0结果正常，1需要购买

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public int getHasPermission() {
        return hasPermission;
    }

    public void setHasPermission(int hasPermission) {
        this.hasPermission = hasPermission;
    }
}
