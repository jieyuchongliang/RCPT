package com.rcpt.base.mvp;

/**
 * Created by lvzp on 2017/3/14.
 * 类描述：
 */

public interface OnDataGetCallback<T> {
    /**
     * 当数据执行成功时的回调方法
     *
     * @param data
     */
    void onSuccessResult(T data);
}
