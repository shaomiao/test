package net.lucode.hackware.magicindicatordemo.test;

/**
 * Created by ssy on 2017/2/8.
 */

import android.content.Context;
import android.content.Intent;

import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.application.BaseApplication;

import java.util.Map;

/**
 * 程序入口
 */
public class WXApplication extends BaseApplication {

    public static Map<String, String> RequestMap;// 保存所有请求的地址，每次打开应用更新，如果为空则认为未获得请求连接
    public static Map<String, String> MainJsonObjMap;// main.json的obj内容
    private static final String MAIN_JSON_API = "api";// 请求地址json键
    private static WXApplication sInstance;

    public static Context context;
//    public static final UserPF UserPF = new UserPF();



    private static final String Main_Url = "http://192.168.1.10/jinzaizhijian/";
    private static final String DEBUG_URL = Main_Url + "action/Ac_base";
    private static final String SEVER_URL = Main_Url + "action/ac_base";

    //TODO 阿里百川 即时通讯appkey
    // public static final String APP_KEY = "23588894";//正式
    public static final String APP_KEY = "23015524";//测试
    // 1是正常 0是登录超时
    public static int login_state = 1;


    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        sInstance = this;

    }

    public static WXApplication getInstance() {
        return sInstance;
    }

    @Override
    public String getBaseUrl() {
        if (OkHttpUtils.is_debug) {
            return DEBUG_URL;
        } else {
            return SEVER_URL;
        }
    }

    @Override
    public boolean getIsDebug() {
        return OkHttpUtils.is_debug;
    }



}
