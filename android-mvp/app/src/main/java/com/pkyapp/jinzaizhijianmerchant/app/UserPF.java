package com.pkyapp.jinzaizhijianmerchant.app;

import android.content.Context;
import android.content.SharedPreferences;
import android.provider.Settings;

import com.pkyapp.jinzaizhijianmerchant.data.entity.TabEntity;
import com.zhy.http.okhttp.utils.FastJsonUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ssy on 2017/2/9.
 * <p>
 * 本地信息存储类
 */

public class UserPF {

    private SharedPreferences sharedPreferences;

    private static final String DATABASE_NAME = "ANTI-POWERTY PROJECT SHAREPREFERENCE";//存储文件名

    private String SESSION_KEY = "SESSION_KEY";
    private String IS_FIRST_COME_APP = "IS_FIRST_COME_APP";
    private String USER_ID = "USER_ID";
    private String USER_NAME = "USER_NAME";
    private String USER_PASSWORD = "USER_PASSWORD";
    private String IS_LOGIN = "IS_LOGIN";
    private String UUID = "UUID";
    private String USER_ICON_PIC = "USER_ICON_PIC";
    private String USER_NICK_NAME = "USER_NICK_NAME";
    private String USER_BALANCE = "USER_BALANCE";
    private String USER_INTERGRAL = "USER_INTERGRAL";
    private String USER_SEX = "USER_SEX";
    private String USER_BIRTHDAY = "USER_BIRTHDAY";
    private String USER_ADDRESS = "USER_ADDRESS";
    private String USER_VIP_LEVEEL = "USER_VIP_LEVEEL";

    private String SHOP_ID = "USER_VIP_LEVEEL";
    private String SHOP_NAME = "USER_VIP_LEVEEL";

    private static final String TAB_LIST = "TAB_LIST";



    /**
     * 初始化本地存储
     *
     * @param context
     */
    public void init(Context context) {
        sharedPreferences = context.getSharedPreferences(DATABASE_NAME, Context.MODE_PRIVATE);
    }

    public void saveTabList(List<TabEntity> tabs) {
        sharedPreferences.edit().putString(TAB_LIST, FastJsonUtils.toJSONString(tabs)).commit();
    }

    public void saveTabList(String tabs) {
        sharedPreferences.edit().putString(TAB_LIST, tabs).commit();
    }


    public List<TabEntity> readTabList() {
        String str = sharedPreferences.getString(TAB_LIST, "");
        List<TabEntity> tabs = new ArrayList<>();
        if (!str.equals("")) {
            tabs = FastJsonUtils.getObjectsList(str,TabEntity.class);
        }
        return tabs;
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
     * 用户Id
     *
     * @param user_id
     */
    public void saveUserId(String user_id) {
        sharedPreferences.edit().putString(USER_ID, user_id).commit();
    }

    public String readUserId() {
        return sharedPreferences.getString(USER_ID, "");
    }

    /**
     * 用户名称
     *
     * @param user_name
     */
    public void saveUserName(String user_name) {
        sharedPreferences.edit().putString(USER_NAME, user_name).commit();
    }

    public String readUserName() {
        return sharedPreferences.getString(USER_NAME, "");
    }



    /**
     * 用户密码
     *
     * @param user_pass
     */
    public void saveUserPassword(String user_pass) {
        sharedPreferences.edit().putString(USER_PASSWORD, user_pass).commit();
    }

    public String readUserPassword() {
        return sharedPreferences.getString(USER_PASSWORD, "");
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
     * 用户头像路径
     *
     * @param user_icon_pic
     */
    public void saveUserIcon(String user_icon_pic) {
        sharedPreferences.edit().putString(USER_ICON_PIC, user_icon_pic).commit();
    }

    public String readUserIcon() {
        return sharedPreferences.getString(USER_ICON_PIC, "");
    }

    /**
     * 用户昵称
     *
     * @param user_nickname
     */
    public void saveUserNickName(String user_nickname) {
        sharedPreferences.edit().putString(USER_NICK_NAME, user_nickname).commit();
    }

    public String readUserNickName() {
        return sharedPreferences.getString(USER_NICK_NAME, "");
    }

    /**
     * 用户积分
     *
     * @param user_integral
     */
    public void saveUserIntegral(String user_integral) {
        sharedPreferences.edit().putString(USER_INTERGRAL, user_integral).commit();
    }

    public String readUserIntegral() {
        return sharedPreferences.getString(USER_INTERGRAL, "");
    }

    /**
     * 用户余额
     *
     * @param user_balance
     */
    public void saveUserBalance(String user_balance) {
        sharedPreferences.edit().putString(USER_BALANCE, user_balance).commit();
    }

    public String readUserBalance() {
        return sharedPreferences.getString(USER_BALANCE, "");
    }

    /**
     * 用户性别
     *
     * @param user_sex
     */
    public void saveUserSex(String user_sex) {
        sharedPreferences.edit().putString(USER_SEX, user_sex).commit();
    }

    public String readUserSex() {
        return sharedPreferences.getString(USER_SEX, "0");
    }

    /**
     * 用户出生日期
     *
     * @param user_birthday
     */
    public void saveUserBirth(String user_birthday) {
        sharedPreferences.edit().putString(USER_BIRTHDAY, user_birthday).commit();
    }

    public String readUserBirth() {
        return sharedPreferences.getString(USER_BIRTHDAY, "");
    }

    /**
     * 用户默认地址
     *
     * @param user_address
     */
    public void saveUserAddress(String user_address) {
        sharedPreferences.edit().putString(USER_ADDRESS, user_address).commit();
    }

    public String readUserAddress() {
        return sharedPreferences.getString(USER_ADDRESS, "");
    }

    /**
     * 用户VIP等级
     *
     * @param user_viplevel
     */
    public void saveUserVipLevel(String user_viplevel) {
        sharedPreferences.edit().putString(USER_VIP_LEVEEL, user_viplevel).commit();
    }

    public String readUserVipLevel() {
        return sharedPreferences.getString(USER_VIP_LEVEEL, "");
    }


}
