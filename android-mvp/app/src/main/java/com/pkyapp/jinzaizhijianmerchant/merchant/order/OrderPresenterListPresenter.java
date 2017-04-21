package com.pkyapp.jinzaizhijianmerchant.merchant.order;

import android.support.annotation.NonNull;

import com.pkyapp.jinzaizhijianmerchant.app.WXApplication;
import com.pkyapp.jinzaizhijianmerchant.data.entity.OrderEntity;
import com.pkyapp.jinzaizhijianmerchant.data.source.OrderDataSource;
import com.pkyapp.jinzaizhijianmerchant.data.source.remote.OrderRemoteDataSource;

import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * @author shaomiao
 * @Date 2017/3/29
 * @Time 15:48
 */
public class OrderPresenterListPresenter implements OrderContract.Presenter.OrderListPresenter {

    private OrderContract.View.OrderView mOrderListView;

    private static String page = "1";


    private final OrderDataSource mOrderDataSource;

    public OrderPresenterListPresenter(@NonNull OrderContract.View.OrderView orderlistview) {
        mOrderDataSource = checkNotNull(OrderRemoteDataSource.getInstance(), "tasksRepository cannot be null");
        mOrderListView = checkNotNull(orderlistview, "tasksView cannot be null!");

        mOrderListView.setPresenter(this);

    }

    @Override
    public void refreshOrderList(int position) {
//        page = "1";
        loadOrderListData(position);
    }

    @Override
    public void loadMoreOrderList(int position) {
        page = String.valueOf((Integer.parseInt(page) + 1));
        mOrderListView.showOrderListLoading();
        mOrderDataSource.getOrderList(WXApplication.ContastPF.readShopId(),
                WXApplication.ContastPF.readShopToken(), String.valueOf(position),page, new OrderDataSource.GetOrderCallback() {
                    @Override
                    public void onOrderList(List<OrderEntity> orders) {
                        mOrderListView.hideOrderListLoading();
                        mOrderListView.showrefresh(false);
                        mOrderListView.showMore(false);
                        if (orders!=null&&orders.size()>0) {
                            mOrderListView.showloadMoreOrdeListView(orders);
                        } else if (page .equals("1")) {
                            mOrderListView.showOrderNullView(true);
                        } else {
                            // 第二页为空 暂无数据
                            mOrderListView.showNotMore(true);

                        }
                    }

                    @Override
                    public void onDataNotAvailable(String msg) {
                        mOrderListView.showrefresh(false);
                        mOrderListView.showMore(false);
                        mOrderListView.hideOrderListLoading();
                        if (page.equals("1")) {
                            mOrderListView.showOrderNullView(true);
                        } else {
                            // 接口报错了
                        }
                    }
                });

    }

    @Override
    public void loadOrderListData(int postion) {
        page = "1";
        mOrderListView.showOrderListLoading();
        mOrderDataSource.getOrderList(WXApplication.ContastPF.readShopId(),
                WXApplication.ContastPF.readShopToken(), String.valueOf(postion),page, new OrderDataSource.GetOrderCallback() {
                    @Override
                    public void onOrderList(List<OrderEntity> orders) {
                        mOrderListView.hideOrderListLoading();
                        mOrderListView.showrefresh(false);
                        mOrderListView.showMore(false);
                        if (orders!=null&&orders.size()>0) {
                            mOrderListView.showOrderListView(orders);
                        } else {
                            mOrderListView.showOrderNullView(true);
                        }
                    }

                    @Override
                    public void onDataNotAvailable(String msg) {
                        mOrderListView.showrefresh(false);
                        mOrderListView.showMore(false);
                        mOrderListView.hideOrderListLoading();
                        mOrderListView.showOrderNullView(true);
                    }
                });
    }

    @Override
    public void start() {

    }
}
