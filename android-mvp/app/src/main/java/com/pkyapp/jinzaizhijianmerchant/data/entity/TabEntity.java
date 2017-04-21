package com.pkyapp.jinzaizhijianmerchant.data.entity;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * @author shaomiao
 * @Date 2017/3/28
 * @Time 11:10
 */

public class TabEntity {

    @JSONField(name="id")
    private int id;

    @JSONField(name="name")
    private String name;

    public TabEntity() {}
    @Override
    public String toString() {
        return "TabEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
