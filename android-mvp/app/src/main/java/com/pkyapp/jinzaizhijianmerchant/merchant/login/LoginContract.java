package com.pkyapp.jinzaizhijianmerchant.merchant.login;

import com.pkyapp.jinzaizhijianmerchant.BasePresenter;
import com.pkyapp.jinzaizhijianmerchant.BaseView;
import com.pkyapp.jinzaizhijianmerchant.data.entity.TabEntity;
import com.pkyapp.jinzaizhijianmerchant.data.entity.ShopEntity;

import java.util.List;

/**
 * @author shaomiao
 * @Date 2017/3/28
 * @Time 15:40
 */

public interface LoginContract {

    interface View extends BaseView<LoginContract.Presenter> {

        void showLoading();

        void hideLoading();

        void showFailMsg(String msg);

        void showSuccessMsg();

        void showMerchantUi();
    }

    interface Presenter extends BasePresenter {
        // 登录方法
        void login(String name,String password);
        // 保存用户信息
        void saveUser(ShopEntity user);
        // 保存tablist
        void saveTabList(List<TabEntity> tabs);

        boolean isDataMissing();
    }
}
