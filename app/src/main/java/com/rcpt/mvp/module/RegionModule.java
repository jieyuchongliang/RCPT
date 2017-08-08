package com.rcpt.mvp.module;

/**
 * Created by 860116021 on 2017/3/15.
 */

public class RegionModule {
    private String code;        //地区编码
    private String name;        //地区名称
    private String level;       //地区级别
    private String enName;      //地区英文名
    private String enShotName;  //地区英文简称

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getEnName() {
        return enName;
    }

    public void setEnName(String enName) {
        this.enName = enName;
    }

    public String getEnShotName() {
        return enShotName;
    }

    public void setEnShotName(String enShotName) {
        this.enShotName = enShotName;
    }
}
