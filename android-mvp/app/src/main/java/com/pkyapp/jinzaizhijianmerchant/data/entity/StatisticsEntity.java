package com.pkyapp.jinzaizhijianmerchant.data.entity;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * @author shaomiao
 * @Date 2017/3/29
 * @Time 17:02
 */

public class StatisticsEntity {

    private String id;

    private String totalOrder;

    @JSONField(name="price")
    private String totalAmount;

    public StatisticsEntity() {}

    public String getTotalOrder() {
        return totalOrder;
    }

    public void setTotalOrder(String totalOrder) {
        this.totalOrder = totalOrder;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(String totalAmount) {
        this.totalAmount = totalAmount;
    }
}
