package com.pkyapp.jinzaizhijianmerchant.data.source;

import com.pkyapp.jinzaizhijianmerchant.data.entity.OrderDetailEntity;
import com.pkyapp.jinzaizhijianmerchant.data.entity.OrderEntity;
import com.pkyapp.jinzaizhijianmerchant.data.entity.ShopEntity;
import com.pkyapp.jinzaizhijianmerchant.data.entity.TabEntity;

import java.util.List;

/**
 * @author shaomiao
 * @Date 2017/3/29
 * @Time 9:10
 */

public interface OrderDataSource {

    interface GetOrderCallback {

        void onOrderList(List<OrderEntity> orders);

        void onDataNotAvailable(String msg);
    }

    interface GetOrderDetailCallback{

        void onOrder(OrderEntity order);

        void onOrderDetailList(List<OrderDetailEntity> details);

        void onDataNotAvailable(String msg);
    }

    // 获取订单列表
    void getOrderList(String shopid, String shoptoken, String typeid,String page, GetOrderCallback callback);

    // 获取订单详情
    void getOrderDetail(String shopid, String shoptoken,String orderId, final GetOrderDetailCallback callback);
}
