package com.rcpt.bean;

/**
 * Created by lvzp on 2017/3/17.
 * 类描述：
 */

public class AttrSelectBean {


    public AttrSelectBean(String distinguishId, String value) {
        this.distinguishId = distinguishId;
        this.value = value;
    }

    /**
     * distinguishId : 0
     * value : 高中及以下
     */

    private String distinguishId;
    private String value;

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
