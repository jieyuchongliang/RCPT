package com.rcpt.bean;

/**
 * Created by lvzp on 2017/3/15.
 * 类描述：
 */

public class HomeInfoBean {

    /**
     * updateDate : 2017-02-23
     * person : 李律师
     * tel : 0531-88812348
     * id : 84
     * title : 济南法律咨询 房产/合同/婚姻/继承/公司法律顾问
     * value : 法律
     * content :
     */

    private String updateDate;
    private String person;
    private String tel;
    private String id;
    private String title;
    private String value;
    private String content;
    private String insertTimestamp;
    private String sources;
    private String category;
    private String updateTimestamp;

    public String getUpdateTimestamp() {
        return updateTimestamp;
    }

    public void setUpdateTimestamp(String updateTimestamp) {
        this.updateTimestamp = updateTimestamp;
    }

    public String getSources() {
        return sources;
    }

    public void setSources(String sources) {
        this.sources = sources;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }

    public String getPerson() {
        return person;
    }

    public void setPerson(String person) {
        this.person = person;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getInsertTimestamp() {
        return insertTimestamp;
    }

    public void setInsertTimestamp(String insertTimestamp) {
        this.insertTimestamp = insertTimestamp;
    }
}
