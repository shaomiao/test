package com.pkyapp.jinzaizhijianmerchant.data.entity;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * @author shaomiao
 * @Date 2017/3/28
 * @Time 11:50
 */

public class ShopEntity {

    @JSONField(name = "shop_id")
    private String shopId;

    @JSONField(name = "shop_name")
    private String shopName;

    @JSONField(name = "shop_tel")
    private String shopTel;

    @JSONField(name = "shop_token")
    private String shopToken;

    @JSONField(name = "pic")
    private String shopPic;

    public ShopEntity() {}

    public String getShopId() {
        return shopId;
    }

    public void setShopId(String shopId) {
        this.shopId = shopId;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getShopTel() {
        return shopTel;
    }

    public void setShopTel(String shopTel) {
        this.shopTel = shopTel;
    }

    public String getShopToken() {
        return shopToken;
    }

    public void setShopToken(String shopToken) {
        this.shopToken = shopToken;
    }

    public String getShopPic() {
        return shopPic;
    }

    public void setShopPic(String shopPic) {
        this.shopPic = shopPic;
    }
}
