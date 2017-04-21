package com.pkyapp.jinzaizhijianmerchant.merchant;

import com.pkyapp.jinzaizhijianmerchant.BasePresenter;
import com.pkyapp.jinzaizhijianmerchant.BaseView;

/**
 * @author shaomiao
 * @Date 2017/3/22
 * @Time 15:27
 */

public interface MerchantContract {

    interface View extends BaseView<Presenter> {
        // 显示订单页面
        // 显示销售统计页面
        // 显示我的页面
    }

    interface Presenter extends BasePresenter {
        // 加载
    }
}
