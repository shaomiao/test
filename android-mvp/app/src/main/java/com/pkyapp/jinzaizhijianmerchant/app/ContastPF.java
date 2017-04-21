package com.pkyapp.jinzaizhijianmerchant.app;

import android.content.Context;
import android.content.SharedPreferences;
import android.provider.Settings;

import com.pkyapp.jinzaizhijianmerchant.data.entity.TabEntity;
import com.pkyapp.jinzaizhijianmerchant.data.source.remote.UserRemoteDataSource;
import com.zhy.http.okhttp.utils.FastJsonUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ssy on 2017/2/9.
 * <p>
 * 本地信息存储类
 */

public class ContastPF {

    private SharedPreferences sharedPreferences;

    private static final String DATABASE_NAME = "ANTI-POWERTY PROJECT SHAREPREFERENCE";//存储文件名

    private String SESSION_KEY = "SESSION_KEY";
    // 第一次启动app
    private String IS_FIRST_COME_APP = "IS_FIRST_COME_APP";
    private String IS_LOGIN = "IS_LOGIN";
    private String UUID = "UUID";

    private String SHOP_ID = "SHOP_ID";
    private String SHOP_NAME = "SHOP_NAME";
    private String SHOP_TEL = "SHOP_TEL";
    private String SHOP_TOKEN = "SHOP_TOKEN";
    private String SHOP_PIC = "SHOP_PIC";

    private String TIME_START = "TIME_START";
    private String TIME_END = "TIME_END";

    private String ORDER_TOTAL = "ORDER_TOTAL";
    private String TOTAL_AMOUNT = "TOTAL_AMOUNT";


    private static final String TAB_LIST = "TAB_LIST";

    private static ContastPF INSTANCE;

    public static ContastPF getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ContastPF();
        }
        return INSTANCE;
    }

    /**
     * 初始化本地存储
     *
     * @param context
     */
    public void init(Context context) {
        sharedPreferences = context.getSharedPreferences(DATABASE_NAME, Context.MODE_PRIVATE);
    }

    /**
     * 保存tablist信息
     * @param tabs
     */
    public void saveTabList(List<TabEntity> tabs) {
        sharedPreferences.edit().putString(TAB_LIST, FastJsonUtils.toJSONString(tabs)).commit();
    }

    /**
     * 保存tablsit信息
     * @param tabs
     */
    public void saveTabList(String tabs) {
        sharedPreferences.edit().putString(TAB_LIST, tabs).commit();
    }


    /**
     * 读取tablist信息
     * @return
     */
    public List<TabEntity> readTabList() {
        String str = sharedPreferences.getString(TAB_LIST, "");
        List<TabEntity> tabs = new ArrayList<>();
        if (!str.equals("")) {
            tabs = FastJsonUtils.getObjectsList(str,TabEntity.class);
        }
        return tabs;
    }

    /**
     * 保存销售统计开始时间
     * @param timeStart
     */
    public void saveTimeStart(String timeStart) {
        sharedPreferences.edit().putString(TIME_START, timeStart).commit();
    }

    /**
     * 读取销售统计开始时间
     * @return
     */
    public String readTimeStart() {
        return sharedPreferences.getString(TIME_START, "");
    }

    /**
     * 保存销售统计结束时间
     * @param timeStart
     */
    public void saveTimeEnd(String timeStart) {
        sharedPreferences.edit().putString(TIME_END, timeStart).commit();
    }

    /**
     * 读取销售统计结束时间
     * @return
     */
    public String readTimeEnd() {
        return sharedPreferences.getString(TIME_END, "");
    }

    /**
     * 保存销售统计订单总数
     * @param orderTotal
     */
    public void saveOrderTotal(String orderTotal) {
        sharedPreferences.edit().putString(ORDER_TOTAL, orderTotal).commit();
    }

    /**
     * 读取订单总金额
     * @return
     */
    public String readOrderTotalAmount() {
        return sharedPreferences.getString(TOTAL_AMOUNT, "");
    }

    /**
     * 保存订单总金额
     * @param totalAmount
     */
    public void saveOrderTotalAmount(String totalAmount) {
        sharedPreferences.edit().putString(TOTAL_AMOUNT, totalAmount).commit();
    }

    /**
     * 读取订单总数
     * @return
     */
    public String readOrderTotal() {
        return sharedPreferences.getString(ORDER_TOTAL, "");
    }


    /**
     * session
     *
     * @param session_key
     */
    public void saveSessionKey(String session_key) {
        sharedPreferences.edit().putString(SESSION_KEY, session_key).commit();
    }

    public String readSessionKey() {
        return sharedPreferences.getString(SESSION_KEY, "");
    }

    /**
     * 是否第一次进入app
     *
     * @param is_first_come_app
     */
    public void saveIsFirstComeApp(Boolean is_first_come_app) {
        sharedPreferences.edit().putBoolean(IS_FIRST_COME_APP, is_first_come_app).commit();
    }

    public Boolean readIsFirstComeApp() {
        return sharedPreferences.getBoolean(IS_FIRST_COME_APP, true);
    }

    /**
     * 是否登录
     *
     * @param is_login
     */
    public void saveLogin(boolean is_login) {
        sharedPreferences.edit().putBoolean(IS_LOGIN, is_login).commit();
    }

    public boolean readLogin() {
        return sharedPreferences.getBoolean(IS_LOGIN, false);
    }


    /**
     * 读取ANDROID_ID
     *
     * @return
     */
    public String readUUid() {
        String ANDROID_ID = Settings.System.getString(WXApplication.context.getContentResolver(), Settings.System.ANDROID_ID);
        return sharedPreferences.getString(UUID, ANDROID_ID);
    }

    /**
     * 保存商家id
     * @param shopid
     */
    public void saveShopId(String shopid) {
        sharedPreferences.edit().putString(SHOP_ID, shopid).commit();
    }

    /**
     * 读取商家id
     * @return
     */
    public String readShopId() {
        return sharedPreferences.getString(SHOP_ID, "");
    }

    /**
     * 保存商家名称
     * @param shopname
     */
    public void saveShopName(String shopname) {
        sharedPreferences.edit().putString(SHOP_NAME, shopname).commit();
    }

    /**
     * 读取商家名称
     * @return
     */
    public String readShopName() {
        return sharedPreferences.getString(SHOP_NAME, "");
    }

    /**
     * 保存商家电话
     * @param shoptel
     */
    public void saveShopTel(String shoptel) {
        sharedPreferences.edit().putString(SHOP_TEL, shoptel).commit();
    }

    /**
     * 读取商家电话
     * @return
     */
    public String readShopTel() {
        return sharedPreferences.getString(SHOP_TEL, "");
    }

    /**
     * 保存商家图片
     * @param shopPic
     */
    public void saveShopPic(String shopPic) {
        sharedPreferences.edit().putString(SHOP_PIC, shopPic).commit();
    }

    /**
     * 读取商家图片
     * @return
     */
    public String readShopPic() {
        return sharedPreferences.getString(SHOP_PIC, "");
    }

    /**
     * 保存商家令牌
     * @param shoptoken
     */
    public void saveShopToken(String shoptoken) {
        sharedPreferences.edit().putString(SHOP_TOKEN, shoptoken).commit();
    }

    /**
     * 读取商家令牌
     * @return
     */
    public String readShopToken() {
        return sharedPreferences.getString(SHOP_TOKEN, "");
    }

    /**
     * 清空所有数据
     */
    public void clearData() {
        sharedPreferences.edit().clear().commit();
    }

}
