package com.pkyapp.jinzaizhijianmerchant.merchant.order;

import android.support.annotation.NonNull;

import com.pkyapp.jinzaizhijianmerchant.app.WXApplication;
import com.pkyapp.jinzaizhijianmerchant.data.entity.OrderEntity;
import com.pkyapp.jinzaizhijianmerchant.data.entity.TabEntity;
import com.pkyapp.jinzaizhijianmerchant.data.source.OrderDataSource;
import com.pkyapp.jinzaizhijianmerchant.data.source.remote.OrderRemoteDataSource;
import com.pkyapp.jinzaizhijianmerchant.merchant.login.LoginContract;
import com.pkyapp.jinzaizhijianmerchant.util.LogUtil;

import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * @author shaomiao
 * @Date 2017/3/29
 * @Time 13:54
 */

public class OrderPresenter implements OrderContract.Presenter {



    private final OrderContract.View mOrderview;



    public OrderPresenter(@NonNull OrderContract.View orderview) {
//        mOrderDataSource = checkNotNull(OrderRemoteDataSource.getInstance(), "tasksRepository cannot be null");
        mOrderview = checkNotNull(orderview, "tasksView cannot be null!");
        mOrderview.setPresenter(this);
    }


    @Override
    public void loadTabLayout() {
        List<TabEntity> tabs = WXApplication.ContastPF.readTabList();
        mOrderview.loadTabLayout(checkNotNull(tabs));
    }

    @Override
    public void refreshOrderListData() {

    }

    @Override
    public void start() {
        mOrderview.showShowName(WXApplication.ContastPF.readShopName());
        loadTabLayout();
    }

}
