package com.pkyapp.jinzaizhijianmerchant.data;

import android.util.Log;

import com.pkyapp.jinzaizhijianmerchant.util.ConstantUtil;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.JsonCallback;
import com.zhy.http.okhttp.network.ApiUrl;
import com.zhy.http.okhttp.utils.MD5Util;


/**
 * Created by ssy on 2017/3/13.
 * <p>
 * 接口类
 */

public class AppAction {




    /**
     * 唯一实例化
     */
    private volatile static AppAction INSTANCE = null;

    public static AppAction getInstance() {
        if (INSTANCE == null) {
            synchronized (AppAction.class) {
                if (INSTANCE == null) {
                    INSTANCE = new AppAction();
                }
            }
        }
        return INSTANCE;
    }

    private static String url = "";

    private boolean url_is_exit(String key) {
        url = ApiUrl.getInstance().getApi(key);
        if (url == null || url.equals("")) {
            Log.e("偏口鱼app", "api路径不存在");
            return false;
        }
        return true;
    }

    /**
     * 用户登录
     *
     * @param user_name    用户名
     * @param user_pass    用户密码
     * @param jsonCallback
     */
    public void login(String user_name, String user_pass, JsonCallback jsonCallback) {
        if (url_is_exit(ConstantUtil.Interface.login)) {
            OkHttpUtils
                    .post()
                    .url(url)
                    .addParams("app_key", MD5Util.getMD5Key(ApiUrl.getInstance().getApi(ConstantUtil.Interface.login)))
                    .addParams("user_name", user_name)
                    .addParams("user_pass", user_pass)
                    .build()
                    .execute(jsonCallback);
        }
    }

    /**
     * 订单列表
     * @param shopid
     * @param shoptoken
     * @param typeid
     * @param jsonCallback
     */
    public void orderList(String shopid, String shoptoken, String typeid,String page,ObjectCallback jsonCallback) {
        if (url_is_exit(ConstantUtil.Interface.shop_list)) {
            OkHttpUtils
                    .post()
                    .url(url)
                    .addParams("app_key", MD5Util.getMD5Key(ApiUrl.getInstance().getApi(ConstantUtil.Interface.shop_list)))
                    .addParams("shop_id", shopid)
                    .addParams("shop_token", shoptoken)
                    .addParams("type", typeid)
                    .addParams("page", page)
                    .build()
                    .execute(jsonCallback);
        }

    }

    /**
     * 销售统计
     * @param shopid
     * @param shoptoken
     * @param timeStart
     * @param timeEnd
     * @param jsonCallback
     */
    public void statistics(String shopid, String shoptoken, String timeStart,String timeEnd,ObjectCallback jsonCallback) {
        if (url_is_exit(ConstantUtil.Interface.shop_order_statistical)) {
            OkHttpUtils
                    .post()
                    .url(url)
                    .addParams("app_key", MD5Util.getMD5Key(ApiUrl.getInstance().getApi(ConstantUtil.Interface.shop_order_statistical)))
                    .addParams("shop_id", shopid)
                    .addParams("shop_token", shoptoken)
                    .addParams("time_start", timeStart)
                    .addParams("time_end", timeEnd)
                    .build()
                    .execute(jsonCallback);
        }

    }

    /**
     * 确认订单
     * @param shopid
     * @param shoptoken
     * @param orderId
     * @param type
     * @param jsonCallback
     */
    public void shopOrderAdd(String shopid,String shoptoken,String orderId,String type,ObjectCallback jsonCallback) {
        if (url_is_exit(ConstantUtil.Interface.shop_oredr_add)) {
            OkHttpUtils
                    .post()
                    .url(url)
                    .addParams("app_key", MD5Util.getMD5Key(ApiUrl.getInstance().getApi(ConstantUtil.Interface.shop_oredr_add)))
                    .addParams("shop_id", shopid)
                    .addParams("shop_token", shoptoken)
                    .addParams("order_id", orderId)
                    .addParams("type", type)
                    .build()
                    .execute(jsonCallback);
        }
    }

    /**
     * 获取订单详情
     * @param shopid
     * @param shoptoken
     * @param orderId
     * @param jsonCallback
     */
    public void shopOrderDetal(String shopid,String shoptoken,String orderId,ObjectCallback jsonCallback) {
        if (url_is_exit(ConstantUtil.Interface.shop_list_desc)) {
            OkHttpUtils
                    .post()
                    .url(url)
                    .addParams("app_key", MD5Util.getMD5Key(ApiUrl.getInstance().getApi(ConstantUtil.Interface.shop_list_desc)))
                    .addParams("shop_id", shopid)
                    .addParams("shop_token", shoptoken)
                    .addParams("order_id", orderId)
                    .build()
                    .execute(jsonCallback);
        }
    }

    /**
     * 获取物流信息
     * @param shopid
     * @param shoptoken
     * @param jsonCallback
     */
    public void logistrcsSeach(String shopid,String shoptoken,ObjectCallback jsonCallback) {
        if (url_is_exit(ConstantUtil.Interface.logistics_seach)) {
            OkHttpUtils
                    .post()
                    .url(url)
                    .addParams("app_key", MD5Util.getMD5Key(ApiUrl.getInstance().getApi(ConstantUtil.Interface.logistics_seach)))
                    .addParams("shop_id", shopid)
                    .addParams("shop_token", shoptoken)
                    .build()
                    .execute(jsonCallback);
        }
    }


    public void xx(ObjectCallback jsonCallback) {
        if (url_is_exit("project_list")) {
            OkHttpUtils
                    .post()
                    .url(url)
                    .addParams("app_key", MD5Util.getMD5Key(ApiUrl.getInstance().getApi("project_list")))
                    .build()
                    .execute(jsonCallback);
        }
    }


}
