package com.pkyapp.jinzaizhijianmerchant.data.source.remote;

import com.pkyapp.jinzaizhijianmerchant.data.AppAction;
import com.pkyapp.jinzaizhijianmerchant.data.entity.TabEntity;
import com.pkyapp.jinzaizhijianmerchant.data.entity.ShopEntity;
import com.pkyapp.jinzaizhijianmerchant.data.source.UserDataSource;
import com.pkyapp.jinzaizhijianmerchant.util.ActivityCollector;
import com.pkyapp.jinzaizhijianmerchant.util.ConstantUtil;
import com.zhy.http.okhttp.callback.JsonCallback;
import com.zhy.http.okhttp.entity.BaseJsonEntity;
import com.zhy.http.okhttp.utils.FastJsonUtils;

import okhttp3.Call;

import static com.google.common.base.Strings.isNullOrEmpty;

/**
 * @author shaomiao
 * @Date 2017/3/28
 * @Time 14:49
 */

public class UserRemoteDataSource implements UserDataSource {

    private static UserRemoteDataSource INSTANCE;

    public static UserRemoteDataSource getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new UserRemoteDataSource();
        }
        return INSTANCE;
    }

    @Override
    public void login(String username, String password, final GetUserCallback callback) {
        AppAction.getInstance().login(username, password, new JsonCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                callback.onDataNotAvailable(ConstantUtil.NETWORK_ERR);
            }

            @Override
            public void onResponse(BaseJsonEntity response, int id) {

                if (response.getCode().equals(ConstantUtil.NETWORK_SUCCESS_CODE)) {
                    if (!isNullOrEmpty(FastJsonUtils.getStr(response.getObj(), "user"))) {

                        callback.onUserLoaded(FastJsonUtils.parseObject(
                                FastJsonUtils.getStr(response.getObj(), "user"), ShopEntity.class));
                    }
                    if (!isNullOrEmpty(FastJsonUtils.getStr(response.getObj(), "list"))) {
                        callback.onTabListLoaded(
                                FastJsonUtils.getObjectsList(
                                        FastJsonUtils.getStr(response.getObj(), "list"), TabEntity.class));
                    }
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
