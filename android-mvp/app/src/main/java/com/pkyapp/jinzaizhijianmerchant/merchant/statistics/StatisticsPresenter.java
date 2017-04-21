package com.pkyapp.jinzaizhijianmerchant.merchant.statistics;

import android.support.annotation.NonNull;

import com.pkyapp.jinzaizhijianmerchant.app.WXApplication;
import com.pkyapp.jinzaizhijianmerchant.data.entity.OrderEntity;
import com.pkyapp.jinzaizhijianmerchant.data.entity.StatisticsEntity;
import com.pkyapp.jinzaizhijianmerchant.data.source.StatisticsDataSource;
import com.pkyapp.jinzaizhijianmerchant.data.source.remote.StatisticsRemoteDataSource;
import com.pkyapp.jinzaizhijianmerchant.util.ConstantUtil;
import com.pkyapp.jinzaizhijianmerchant.util.LogUtil;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * @author shaomiao
 * @Date 2017/3/30
 * @Time 10:33
 */

public class StatisticsPresenter implements StatisticsContract.Presenter {

    private final StatisticsContract.View mStatisticeView;
    private final StatisticsDataSource mStatisticsDataSource;



    public StatisticsPresenter(@NonNull StatisticsContract.View view) {
        mStatisticsDataSource = checkNotNull(StatisticsRemoteDataSource.getInstance(), "tasksRepository cannot be null");
        mStatisticeView = checkNotNull(view, "tasksView cannot be null!");

        mStatisticeView.setPresenter(this);
    }

    @Override
    public void dataCancel() {

    }

    @Override
    public void dataDetermine(List<Date> list) {
        // 点击确定
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        if (list.size() == 1) {// 如果只选择了一天，那么查询数据的起始时间和结尾时间为一个
            // 例：2015/2/3-2015/2/3

                initStatistics(WXApplication.ContastPF.readShopId(),WXApplication.ContastPF.readShopToken(),
                        dateFormat.format(list.get(0)),dateFormat.format(list.get(0)));
                return;
            }
            if (list.size() >= 2) {// 如果只选择了多天，那么查询数据的起始时间和结尾时间为一个
                //例：2015/2/3-2015/3/15
                initStatistics(WXApplication.ContastPF.readShopId(),WXApplication.ContastPF.readShopToken(),
                        dateFormat.format(list.get(0)), dateFormat.format(list.get(list.size() - 1)));
                return;
            }
    }

    @Override
    public void start() {
        String beginTime = "2017-02-29";
        String endTime = "2017-03-30";
        mStatisticeView.loadShopName(WXApplication.ContastPF.readShopName());
        initStatistics(WXApplication.ContastPF.readShopId(),WXApplication.ContastPF.readShopToken(),
                beginTime,endTime);
        // 如果不是空就显示本地的信息

    }
    private void initStatistics(String shopid, String shopToken, final String timeStart, final String timeEnd) {
        mStatisticeView.showDialog();
        // 获取数据
        mStatisticsDataSource.getStatisticsList(shopid, shopToken, timeStart, timeEnd, new StatisticsDataSource.GetStatisticsCallback() {
            @Override
            public void onStatisticsList(StatisticsEntity statistics) {
                mStatisticeView.hideDialog();
                // 保存到本地
                if (statistics!=null) {
                    LogUtil.e("偏口鱼statistics",
                            statistics.getTotalAmount()!=null ?statistics.getTotalAmount():"dd");
                    String totalAmount = statistics.getTotalAmount()!=null ?statistics.getTotalAmount(): ConstantUtil.AMOUNT_NULL;
                    String totalOrder = statistics.getTotalOrder()!=null ?statistics.getTotalOrder():ConstantUtil.ORDER_NULL;
                    WXApplication.ContastPF.saveTimeStart(timeStart);
                    WXApplication.ContastPF.saveTimeStart(timeEnd);
                    WXApplication.ContastPF.saveOrderTotal(totalOrder);
                    WXApplication.ContastPF.saveOrderTotalAmount(totalAmount);

                    mStatisticeView.loadBeginDate(timeStart);
                    mStatisticeView.loadEndDate(timeEnd);
                    mStatisticeView.loadOrderCount(totalOrder);
                    mStatisticeView.loadOrderTotalAmount(totalAmount);
                }

            }

            @Override
            public void onOrderList(List<OrderEntity> orders) {
                mStatisticeView.hideDialog();
                mStatisticeView.showrefresh(false);
                if (orders!=null && orders.size()>0) {
                    LogUtil.e("偏口鱼", orders.get(0).getId());
                    // 绑定数据
                    mStatisticeView.refreshOrderList(orders);

                } else {
                    mStatisticeView.showNullLayout(true);
                }
            }
            @Override
            public void onDataNotAvailable(String msg) {
                mStatisticeView.hideDialog();
                mStatisticeView.showrefresh(false);
                mStatisticeView.loadBeginDate(timeStart);
                mStatisticeView.loadEndDate(timeEnd);
                mStatisticeView.showNullLayout(true);
            }
        });
    }
}
