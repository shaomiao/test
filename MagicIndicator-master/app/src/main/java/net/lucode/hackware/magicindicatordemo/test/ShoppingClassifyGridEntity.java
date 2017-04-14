package net.lucode.hackware.magicindicatordemo.test;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * Created by pc002 on 2017/3/30.
 * 商品详情实体类
 */

public class ShoppingClassifyGridEntity {

    @JSONField(name = "id")
    private String goods_id;
    @JSONField(name = "pic")
    private String goods_pic;
    @JSONField(name = "name")
    private String goods_name;
    @JSONField(name = "price")
    private String goods_price;
    @JSONField(name = "sales")
    private String goods_sell;
    @JSONField(name = "good_url")
    private String goods_url;
    @JSONField(name = "shop_id")
    private String goods_shop_id;
    private String cellent;

    public ShoppingClassifyGridEntity() {
    }

    public String getGoods_sell() {
        return goods_sell;
    }

    public void setGoods_sell(String goods_sell) {
        this.goods_sell = goods_sell;
    }

    public String getGoods_id() {
        return goods_id;
    }

    public void setGoods_id(String goods_id) {
        this.goods_id = goods_id;
    }

    public String getGoods_pic() {
        return goods_pic;
    }

    public void setGoods_pic(String goods_pic) {
        this.goods_pic = goods_pic;
    }

    public String getGoods_name() {
        return goods_name;
    }

    public void setGoods_name(String goods_name) {
        this.goods_name = goods_name;
    }

    public String getGoods_price() {
        return goods_price;
    }

    public void setGoods_price(String goods_price) {
        this.goods_price = goods_price;
    }

    public String getGoods_url() {
        return goods_url;
    }

    public void setGoods_url(String goods_url) {
        this.goods_url = goods_url;
    }

    public String getGoods_shop_id() {
        return goods_shop_id;
    }

    public void setGoods_shop_id(String goods_shop_id) {
        this.goods_shop_id = goods_shop_id;
    }

    public String getCellent() {
        return cellent;
    }

    public void setCellent(String cellent) {
        this.cellent = cellent;
    }
}
