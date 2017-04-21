package com.pkyapp.jinzaizhijianmerchant.merchant.login;

import android.support.annotation.NonNull;

import com.pkyapp.jinzaizhijianmerchant.app.WXApplication;
import com.pkyapp.jinzaizhijianmerchant.data.entity.TabEntity;
import com.pkyapp.jinzaizhijianmerchant.data.entity.ShopEntity;
import com.pkyapp.jinzaizhijianmerchant.data.source.UserDataSource;
import com.pkyapp.jinzaizhijianmerchant.data.source.remote.UserRemoteDataSource;

import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * @author shaomiao
 * @Date 2017/3/28
 * @Time 17:19
 */

public class LoginPresenter implements LoginContract.Presenter {

    private final UserDataSource mUserRepository;

    private final LoginContract.View mLoginView;

    public LoginPresenter(@NonNull LoginContract.View loginView) {
        mUserRepository = checkNotNull(UserRemoteDataSource.getInstance(), "tasksRepository cannot be null");
        mLoginView = checkNotNull(loginView, "tasksView cannot be null!");

        mLoginView.setPresenter(this);
    }

    @Override
    public void login(String name, String password) {
        mLoginView.showLoading();
        mUserRepository.login(name, password, new UserDataSource.GetUserCallback() {
            @Override
            public void onUserLoaded(ShopEntity user) {
                // 登录状态 修改已登录
                WXApplication.login_state = 1;
                // 保存用户信息
                saveUser(user);
                mLoginView.hideLoading();
                mLoginView.showSuccessMsg();
                mLoginView.showMerchantUi();
            }

            @Override
            public void onTabListLoaded(List<TabEntity> tabs) {
                // 保存tablist信息
                saveTabList(tabs);

                mLoginView.hideLoading();
            }

            @Override
            public void onDataNotAvailable(String msg) {
                mLoginView.hideLoading();
                mLoginView.showFailMsg(msg);
            }
        });
    }

    @Override
    public void saveUser(ShopEntity shop) {

        WXApplication.ContastPF.saveShopId(shop.getShopId());
        WXApplication.ContastPF.saveShopName(shop.getShopName());
        WXApplication.ContastPF.saveShopTel(shop.getShopTel());
        WXApplication.ContastPF.saveShopPic(shop.getShopPic());
        WXApplication.ContastPF.saveShopToken(shop.getShopToken());
        WXApplication.ContastPF.saveLogin(true);

    }

    @Override
    public void saveTabList(List<TabEntity> tabs) {
        WXApplication.ContastPF.saveTabList(tabs);
    }

    @Override
    public boolean isDataMissing() {
        return false;
    }

    @Override
    public void start() {

    }
}
