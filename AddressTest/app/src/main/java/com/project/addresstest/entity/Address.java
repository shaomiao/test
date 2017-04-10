package com.project.addresstest.entity;

/**
 * @author shaomiao
 * @Date 2017/4/7
 * @Time 10:58
 * 地址实体类
 */

public class Address {

    private String name;

    private String phone;

    // 收货地址
    private String address;

    // 详细地址
    private String detailedAddress;

    public Address(){}

    public Address(String name, String phone, String address, String detailedAddress) {
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.detailedAddress = detailedAddress;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDetailedAddress() {
        return detailedAddress;
    }

    public void setDetailedAddress(String detailedAddress) {
        this.detailedAddress = detailedAddress;
    }
}
