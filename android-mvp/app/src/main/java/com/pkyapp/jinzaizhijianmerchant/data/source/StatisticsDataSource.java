package com.pkyapp.jinzaizhijianmerchant.data.source;

import com.pkyapp.jinzaizhijianmerchant.data.entity.OrderEntity;
import com.pkyapp.jinzaizhijianmerchant.data.entity.StatisticsEntity;

import java.util.List;

/**
 * @author shaomiao
 * @Date 2017/3/30
 * @Time 10:47
 */

public interface StatisticsDataSource {

    interface GetStatisticsCallback {

        void onStatisticsList(StatisticsEntity statistics);

        void onOrderList(List<OrderEntity> orders);

        void onDataNotAvailable(String msg);
    }

    // 获取销售统计信息
    void getStatisticsList(String shopId,String shopToken,String beginTime,String endTime,GetStatisticsCallback callback);
}
