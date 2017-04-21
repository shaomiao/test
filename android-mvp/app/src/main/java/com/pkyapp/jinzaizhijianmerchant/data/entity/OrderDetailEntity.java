package com.pkyapp.jinzaizhijianmerchant.data.entity;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * @author shaomiao
 * @Date 2017/3/31
 * @Time 11:43
 */

public class OrderDetailEntity {

    @JSONField(name = "name")
    private String productName;

    @JSONField(name = "num")
    private String productNum;

    @JSONField(name = "price")
    private String productPrice;

    @JSONField(name = "spec_id")
    private String productSpec;

    public OrderDetailEntity(){}

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductNum() {
        return productNum;
    }

    public void setProductNum(String productNum) {
        this.productNum = productNum;
    }

    public String getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(String productPrice) {
        this.productPrice = productPrice;
    }

    public String getProductSpec() {
        return productSpec;
    }

    public void setProductSpec(String productSpec) {
        this.productSpec = productSpec;
    }
}
