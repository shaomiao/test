package com.pkyapp.jinzaizhijianmerchant.merchant.statistics;

import com.pkyapp.jinzaizhijianmerchant.BasePresenter;
import com.pkyapp.jinzaizhijianmerchant.BaseView;
import com.pkyapp.jinzaizhijianmerchant.data.entity.OrderEntity;

import java.util.Date;
import java.util.List;

/**
 * @author shaomiao
 * @Date 2017/3/29
 * @Time 11:40
 */

public class StatisticsContract {

    interface View extends BaseView<StatisticsContract.Presenter> {

        void loadShopName(String name);

        void loadBeginDate(String time);

        void loadEndDate(String time);

        void loadOrderCount(String orderCount);

        void loadOrderTotalAmount(String amount);

        void refreshOrderList(List<OrderEntity> orders);

        void showNullLayout(boolean flag);

        void showDialog();

        void hideDialog();

        void showrefresh(boolean flag);

    }

    interface Presenter extends BasePresenter {

        void dataCancel();

        void dataDetermine(List<Date> list);

    }
}
