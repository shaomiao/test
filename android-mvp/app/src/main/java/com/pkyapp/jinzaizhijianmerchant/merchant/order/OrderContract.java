package com.pkyapp.jinzaizhijianmerchant.merchant.order;

import com.pkyapp.jinzaizhijianmerchant.BasePresenter;
import com.pkyapp.jinzaizhijianmerchant.BaseView;
import com.pkyapp.jinzaizhijianmerchant.data.entity.OrderEntity;
import com.pkyapp.jinzaizhijianmerchant.data.entity.TabEntity;

import java.util.List;

/**
 * @author shaomiao
 * @Date 2017/3/29
 * @Time 11:12
 */

public interface OrderContract {

    interface View extends BaseView<OrderContract.Presenter> {
       

        void loadTabLayout(List<TabEntity> tabs);



        void showShowName(String shopname);
        
        interface OrderView extends BaseView<OrderContract.Presenter.OrderListPresenter>{
            // 显示订单列表等待框
            void showOrderListLoading();

            // 隐藏订单列表等待框
            void hideOrderListLoading();

            // 显示订单列表
            void showOrderListView(List<OrderEntity> orders);

            // 显示更多数据
            void showloadMoreOrdeListView(List<OrderEntity> orders);

            // 刷新订单列表
            void refreshOrderList();

            // 是否显示空的视图
            void showOrderNullView(boolean flag);

            // 是否显示刷新
            void showrefresh(boolean flag);

            // 是否显示加载更多
            void showMore(boolean flag);

            // 没有更多数据
            void showNotMore(boolean flag);
        }

    }

    interface Presenter extends BasePresenter {

        void loadTabLayout();

        void refreshOrderListData();

        interface OrderListPresenter extends BasePresenter{
//            void showOrderListLoading();
//
//            void hideOrderListLoading();
//
//            void showOrderListView();

            void refreshOrderList(int position);

            void loadMoreOrderList(int position);

            void loadOrderListData(int position);

        }

    }
}
