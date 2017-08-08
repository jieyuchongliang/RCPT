package com.rcpt.observer_design;

/**
 * Created by 860617010 on 2017/6/6.
 */

public interface Subject {
    /**
     * 增加订阅者
     * @param observer
     */
    void attach(Observer observer);
    /**
     * 删除订阅者
     * @param observer
     */
    void detach(Observer observer);
    /**
     * 通知订阅者更新消息
     */
    void notify(String message,boolean isResetting);
}
