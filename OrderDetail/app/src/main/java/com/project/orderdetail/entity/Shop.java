package com.project.orderdetail.entity;

import java.util.List;

/**
 * @author shaomiao
 * @Date 2017/4/10
 * @Time 15:49
 */

public class Shop {

    private String shopName;

    private List<Product> products;

    public Shop() {}

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
