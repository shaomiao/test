package com.project.orderdetail.entity;

/**
 * @author shaomiao
 * @Date 2017/4/10
 * @Time 15:47
 */

public class Product {

    private String productName;

    private String productNum;

    private String productTag;

    private String productPrice;

    private String productImg;

    public Product() {}

    public Product(String productName, String productNum, String productTag, String productPrice, String productImg) {
        this.productName = productName;
        this.productNum = productNum;
        this.productTag = productTag;
        this.productPrice = productPrice;
        this.productImg = productImg;
    }

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

    public String getProductTag() {
        return productTag;
    }

    public void setProductTag(String productTag) {
        this.productTag = productTag;
    }

    public String getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(String productPrice) {
        this.productPrice = productPrice;
    }

    public String getProductImg() {
        return productImg;
    }

    public void setProductImg(String productImg) {
        this.productImg = productImg;
    }
}
