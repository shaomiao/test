package com.pkyapp.jinzaizhijianmerchant.data.entity;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * @author shaomiao
 * @Date 2017/3/29
 * @Time 10:08
 */

public class OrderEntity {

    /**
     *    [order_stat] => 1是已支付（等待商家确认）2 商家以确认（待收货）3是以发货 4已经确认收货(订单完成) 5退款中 6退款完成
     */
    private String id;

    @JSONField(name = "allprice")
    private String allPrice;

    @JSONField(name = "freight")
    private String freight;

    @JSONField(name = "message")
    private String message;

    @JSONField(name = "order_id")
    private String orderId;

    @JSONField(name = "order_stat")
    private String orderStat;

    @JSONField(name = "time")
    private String time;

    @JSONField(name = "address")
    private String address;



    public OrderEntity() {}

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAllPrice() {
        return allPrice;
    }

    public void setAllPrice(String allPrice) {
        this.allPrice = allPrice;
    }

    public String getFreight() {
        return freight;
    }

    public void setFreight(String freight) {
        this.freight = freight;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getOrderStat() {
        return orderStat;
    }

    public void setOrderStat(String orderStat) {
        this.orderStat = orderStat;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
