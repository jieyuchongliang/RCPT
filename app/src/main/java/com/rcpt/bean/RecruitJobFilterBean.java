package com.rcpt.bean;

import java.util.List;

/**
 * Created by lvzp on 2017/4/11.
 * 类描述：
 */

public class RecruitJobFilterBean {

    private List<AttrSelectBean> industryList;
    private List<AttrSelectBean> jobTypeList;
    private List<AttrSelectBean> salRangeList;
    private List<AttrSelectBean> orientedList;

    public List<AttrSelectBean> getOrientedList() {
        return orientedList;
    }

    public void setOrientedList(List<AttrSelectBean> orientedList) {
        this.orientedList = orientedList;
    }

    public List<AttrSelectBean> getIndustryList() {
        return industryList;
    }

    public void setIndustryList(List<AttrSelectBean> industryList) {
        this.industryList = industryList;
    }

    public List<AttrSelectBean> getJobTypeList() {
        return jobTypeList;
    }

    public void setJobTypeList(List<AttrSelectBean> jobTypeList) {
        this.jobTypeList = jobTypeList;
    }

    public List<AttrSelectBean> getSalRangeList() {
        return salRangeList;
    }

    public void setSalRangeList(List<AttrSelectBean> salRangeList) {
        this.salRangeList = salRangeList;
    }
}
