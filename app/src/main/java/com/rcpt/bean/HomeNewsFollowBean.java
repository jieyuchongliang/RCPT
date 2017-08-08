package com.rcpt.bean;

/**
 * Created by lvzp on 2017/2/22.
 * 类描述：
 */

public class HomeNewsFollowBean {
    private String id;
    private String imageUrl;
    private String title;
    private String fromWhere;
    private long time;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getFromWhere() {
        return fromWhere;
    }

    public void setFromWhere(String fromWhere) {
        this.fromWhere = fromWhere;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }
}
