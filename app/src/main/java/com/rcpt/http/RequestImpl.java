package com.rcpt.http;


/**
 * Created by jingbin on 2017/1/17.
 * 用于数据请求的回调
 */

public interface RequestImpl<T> {
    void onNext(T result);
}
