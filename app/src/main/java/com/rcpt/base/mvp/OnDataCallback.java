package com.rcpt.base.mvp;

/**
 * Created by lvzp on 2017/4/12.
 * 类描述：
 */

public interface OnDataCallback<T> {

    /**
     * 当数据执行成功时的回调方法
     *
     * @param data
     */
    void onSuccessResult(T data);

    /**
     * 当错误时执行的方法
     */
    void onError(String errMsg);

}
