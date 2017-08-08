package com.rcpt.bean;

import com.rcpt.http.ParamNames;

import java.util.List;

/**
 * Created by 860116021 on 2017/5/10.
 */

public class MyOrderListBean {

    private String totalPage;
    private boolean isNext;
    private List<OrderListBean> orderList;

    public String getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(String totalPage) {
        this.totalPage = totalPage;
    }

    public boolean isNext() {
        return isNext;
    }

    public void setNext(boolean next) {
        isNext = next;
    }

    public List<OrderListBean> getOrderList() {
        return orderList;
    }

    public void setOrderList(List<OrderListBean> orderList) {
        this.orderList = orderList;
    }

    public static class OrderListBean{
        @ParamNames("order_number")
        private String orderNum;
        @ParamNames("update_timestamp")
        private String updateTime;
        @ParamNames("course_name")
        private String title;
        private String price;
        private int pay_flg;
        private String course_id;

        public String getCourse_id() {
            return course_id;
        }

        public void setCourse_id(String course_id) {
            this.course_id = course_id;
        }

        public String getOrderNum() {
            return orderNum;
        }

        public void setOrderNum(String orderNum) {
            this.orderNum = orderNum;
        }

        public String getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(String updateTime) {
            this.updateTime = updateTime;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public int getPay_flg() {
            return pay_flg;
        }

        public void setPay_flg(int pay_flg) {
            this.pay_flg = pay_flg;
        }
    }
}
