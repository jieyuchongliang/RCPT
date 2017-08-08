package com.rcpt.bean;

/**
 * Created by lvzp on 2017/3/14.
 * 类描述：
 */

public class PolicyTypeBean {

    /**
     * distinguishId : 0
     * value : 国家级
     */

    private String distinguishId;
    private int type;
    private String value;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getDistinguishId() {
        return distinguishId;
    }

    public void setDistinguishId(String distinguishId) {
        this.distinguishId = distinguishId;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
