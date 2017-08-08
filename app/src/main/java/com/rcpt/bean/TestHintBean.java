package com.rcpt.bean;

import android.support.annotation.DrawableRes;

/**
 * Created by lvzp on 2017/4/1.
 * 类描述：
 */

public class TestHintBean {

    @DrawableRes
    private int showRes;
    private String name;
    private String hint;

    @DrawableRes
    public int getShowRes() {
        return showRes;
    }

    public void setShowRes(@DrawableRes int showRes) {
        this.showRes = showRes;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHint() {
        return hint;
    }

    public void setHint(String hint) {
        this.hint = hint;
    }
}
