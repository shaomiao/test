package com.project.addresstest.entity;

/**
 * @author shaomiao
 * @Date 2017/4/7
 * @Time 15:59
 */

public class Record {

    private String price;

    private String date;

    // 是否是收入
    private boolean income;

    public Record() {}

    public Record(String price, String date, boolean income) {
        this.price = price;
        this.date = date;
        this.income = income;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public boolean isIncome() {
        return income;
    }

    public void setIncome(boolean income) {
        this.income = income;
    }
}
