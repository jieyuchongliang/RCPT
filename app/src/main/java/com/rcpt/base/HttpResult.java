package com.rcpt.base;

/**
 * 作者：吕振鹏
 * 创建时间：11月07日
 * 时间：16:07
 * 版本：v1.0.0
 * 类描述：
 * 修改时间：
 */

public class HttpResult<T> {

    private boolean result;
    private int flag = Integer.MIN_VALUE;
    private String msg;
    private T data;

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
