package com.rcpt.bean;

/**
 * 用于播放存储播放视频信息的实体类
 */

public class VideoInfoBean {

    private String uuId;
    private String vuId;
    private String cuid;
    private String utoken;
    private int needBuy;//等于1代表需要购买，0是不需要购买的
    private int tryLookTime;

    public VideoInfoBean(String uuId, String vuId, String cuid, String utoken, int needBuy, int tryLookTime) {
        this.uuId = uuId;
        this.vuId = vuId;
        this.cuid = cuid;
        this.utoken = utoken;
        this.needBuy = needBuy;
        this.tryLookTime = tryLookTime;
    }

    public int getNeedBuy() {
        return needBuy;
    }

    public void setNeedBuy(int needBuy) {
        this.needBuy = needBuy;
    }

    public int getTryLookTime() {
        return tryLookTime;
    }

    public void setTryLookTime(int tryLookTime) {
        this.tryLookTime = tryLookTime;
    }

    public String getUuId() {
        return uuId;
    }

    public void setUuId(String uuId) {
        this.uuId = uuId;
    }

    public String getVuId() {
        return vuId;
    }

    public void setVuId(String vuId) {
        this.vuId = vuId;
    }

    public String getCuid() {
        return cuid;
    }

    public void setCuid(String cuid) {
        this.cuid = cuid;
    }

    public String getUtoken() {
        return utoken;
    }

    public void setUtoken(String utoken) {
        this.utoken = utoken;
    }
}
