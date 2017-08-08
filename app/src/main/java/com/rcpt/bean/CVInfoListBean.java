package com.rcpt.bean;

/**
 * Created by lvzp on 2017/3/8.
 * 类描述：
 */

public class CVInfoListBean {

    private String id;
    private String time;//时间
    private String primary;//主要信息
    private String additional;//附加信息

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getPrimary() {
        return primary;
    }

    public void setPrimary(String primary) {
        this.primary = primary;
    }

    public String getAdditional() {
        return additional;
    }

    public void setAdditional(String additional) {
        this.additional = additional;
    }
}
