package com.rcpt.bean;

/**
 * Created by lvzp on 2017/4/12.
 * 类描述：
 */

public class FilterSelectBean {

    private String value;
    private String id;
    private boolean isSelect;

    public FilterSelectBean(String value, String id, boolean isSelect) {
        this.value = value;
        this.id = id;
        this.isSelect = isSelect;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isSelect() {
        return isSelect;
    }

    public void setSelect(boolean select) {
        isSelect = select;
    }
}
