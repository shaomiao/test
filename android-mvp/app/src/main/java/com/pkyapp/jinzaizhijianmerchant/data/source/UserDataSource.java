package com.pkyapp.jinzaizhijianmerchant.data.source;

import com.pkyapp.jinzaizhijianmerchant.data.entity.TabEntity;
import com.pkyapp.jinzaizhijianmerchant.data.entity.ShopEntity;

import java.util.List;

/**
 * @author shaomiao
 * @Date 2017/3/28
 * @Time 11:48
 */

public interface UserDataSource {


    interface GetUserCallback {

        void onUserLoaded(ShopEntity user);

        void onTabListLoaded(List<TabEntity> tabs);

        void onDataNotAvailable(String msg);
    }

    // 登录接口
    void login(String username,String password,GetUserCallback callback);
}
