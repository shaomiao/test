package com.pkyapp.jinzaizhijianmerchant.data.source.remote;

import com.pkyapp.jinzaizhijianmerchant.data.AppAction;
import com.pkyapp.jinzaizhijianmerchant.data.ObjectCallback;
import com.pkyapp.jinzaizhijianmerchant.data.entity.OrderEntity;
import com.pkyapp.jinzaizhijianmerchant.data.entity.StatisticsEntity;
import com.pkyapp.jinzaizhijianmerchant.data.source.StatisticsDataSource;
import com.pkyapp.jinzaizhijianmerchant.util.ConstantUtil;
import com.zhy.http.okhttp.entity.BaseJsonEntity;
import com.zhy.http.okhttp.utils.FastJsonUtils;

import java.util.List;

import okhttp3.Call;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * @author shaomiao
 * @Date 2017/3/30
 * @Time 10:46
 */

public class StatisticsRemoteDataSource implements StatisticsDataSource {

    private static StatisticsRemoteDataSource INSTANCE;

    public static StatisticsRemoteDataSource getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new StatisticsRemoteDataSource();
        }
        return INSTANCE;
    }


    @Override
    public void getStatisticsList(String shopId, String shopToken, String beginTime, String endTime, final GetStatisticsCallback callback) {
        AppAction.getInstance().statistics(shopId, shopToken, beginTime, endTime, new ObjectCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                callback.onDataNotAvailable(ConstantUtil.NETWORK_ERR);
            }

            @Override
            public void onResponse(BaseJsonEntity response, int id) {
                if (response.getCode().equals(ConstantUtil.NETWORK_SUCCESS_CODE)) {
                    callback.onOrderList(FastJsonUtils.getObjectsList(FastJsonUtils.getStr(response.getObj(), "list"), OrderEntity.class));
                    List<StatisticsEntity> statisticsEntityList = FastJsonUtils.getObjectsList(FastJsonUtils.getStr(response.getObj(),"order"),StatisticsEntity.class);
                    callback.onStatisticsList(statisticsEntityList!=null && statisticsEntityList.size()>0?statisticsEntityList.get(0):null);
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
