package com.rcpt.bean;

import java.util.List;

/**
 * Created by 860617003 on 2017/5/16.
 */

public class VideoCourseTypeBean {

    /**
     * itemName : 默认学科
     * id : 1692
     * parentId : null
     */

    private String itemName;
    private String id;
    private String parentId;
    private List<VideoCourseTypeBean> childList;

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public List<VideoCourseTypeBean> getChildList() {
        return childList;
    }

    public void setChildList(List<VideoCourseTypeBean> childList) {
        this.childList = childList;
    }
}
