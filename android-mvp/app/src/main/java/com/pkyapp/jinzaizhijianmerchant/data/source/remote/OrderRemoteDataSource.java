package com.pkyapp.jinzaizhijianmerchant.data.source.remote;

import android.widget.Toast;

import com.pkyapp.jinzaizhijianmerchant.MainActivity;
import com.pkyapp.jinzaizhijianmerchant.app.WXApplication;
import com.pkyapp.jinzaizhijianmerchant.data.AppAction;
import com.pkyapp.jinzaizhijianmerchant.data.ObjectCallback;
import com.pkyapp.jinzaizhijianmerchant.data.entity.OrderDetailEntity;
import com.pkyapp.jinzaizhijianmerchant.data.entity.OrderEntity;
import com.pkyapp.jinzaizhijianmerchant.data.entity.ShopEntity;
import com.pkyapp.jinzaizhijianmerchant.data.entity.TabEntity;
import com.pkyapp.jinzaizhijianmerchant.data.source.OrderDataSource;
import com.pkyapp.jinzaizhijianmerchant.merchant.StartActivity;
import com.pkyapp.jinzaizhijianmerchant.util.ActivityCollector;
import com.pkyapp.jinzaizhijianmerchant.util.ConstantUtil;
import com.zhy.http.okhttp.callback.JsonCallback;
import com.zhy.http.okhttp.entity.BaseJsonEntity;
import com.zhy.http.okhttp.utils.FastJsonUtils;

import okhttp3.Call;

import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.base.Strings.isNullOrEmpty;

/**
 * @author shaomiao
 * @Date 2017/3/29
 * @Time 9:09
 */

public class OrderRemoteDataSource implements OrderDataSource {

    private static OrderRemoteDataSource INSTANCE;


    public static OrderRemoteDataSource getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new OrderRemoteDataSource();
        }
        return INSTANCE;
    }

    @Override
    public void getOrderList(String shopid, String shoptoken, String typeid,String page, final GetOrderCallback callback) {
        AppAction.getInstance().orderList(shopid, shoptoken,typeid,page, new ObjectCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                callback.onDataNotAvailable(ConstantUtil.NETWORK_ERR);
            }

            @Override
            public void onResponse(BaseJsonEntity response, int id) {
                if (response.getCode().equals(ConstantUtil.NETWORK_SUCCESS_CODE)) {
                    callback.onOrderList(checkNotNull(FastJsonUtils.getObjectsList(response.getObj(), OrderEntity.class)));
                }
                else if (response.getCode().equals(ConstantUtil.NETWORK_FAIL_CODE)) {
                    callback.onDataNotAvailable(response.getMessage());
                }
                else {
                    callback.onDataNotAvailable(ConstantUtil.NETWORK_ERR);
                }
            }
        });
    }

    @Override
    public void getOrderDetail(String shopid, String shoptoken,String orderId, final GetOrderDetailCallback callback) {
        AppAction.getInstance().shopOrderDetal(shopid, shoptoken,orderId, new ObjectCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                callback.onDataNotAvailable(ConstantUtil.NETWORK_ERR);
            }

            @Override
            public void onResponse(BaseJsonEntity response, int id) {
                if (response.getCode().equals(ConstantUtil.NETWORK_SUCCESS_CODE)) {

                    callback.onOrder(FastJsonUtils.parseObject(FastJsonUtils.getStr(response.getObj(),"order"),OrderEntity.class));
                    callback.onOrderDetailList(FastJsonUtils.getObjectsList(FastJsonUtils.getStr(response.getObj(), "order_desc"), OrderDetailEntity.class));

                }
                else if (response.getCode().equals(ConstantUtil.NETWORK_FAIL_CODE)) {
                    callback.onDataNotAvailable(response.getMessage());
                }
                else {
                    callback.onDataNotAvailable(ConstantUtil.NETWORK_ERR);
                }

            }
        });
    }
}
