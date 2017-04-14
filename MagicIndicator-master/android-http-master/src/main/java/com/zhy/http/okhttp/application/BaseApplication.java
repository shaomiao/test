package com.zhy.http.okhttp.application;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.widget.Toast;

import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.JsonCallback;
import com.zhy.http.okhttp.callback.StringCallback;
import com.zhy.http.okhttp.entity.BaseJsonEntity;
import com.zhy.http.okhttp.network.BaseSharePreference;
import com.zhy.http.okhttp.utils.FastJsonUtils;
import com.zhy.http.okhttp.utils.MD5Util;
import com.zhy.http.okhttp.utils.SystemUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import okhttp3.Call;

/**
 * Created by ssy on 2017/3/15.
 * 程序基础入口
 */

public abstract class BaseApplication extends Application {

    private static final String SYS_TYPE = "android";
    private static final String DEVICE_TYPE = "android";
    public static Map<String, String> RequestMap;// 保存所有请求的地址，每次打开应用更新，如果为空则认为未获得请求连接
    public static Map<String, String> MainJsonObjMap;// main.json的obj内容
    private static final String MAIN_JSON_API = "api";// 请求地址json键

    public static BaseApplication INSTANCE;
    public static String BaseUrl = "";
    public static boolean IS_DEBUG = false;
    public static final BaseSharePreference BASE_SHAREPREFERENCE = new BaseSharePreference();


    @Override
    public void onCreate() {
        super.onCreate();
        INSTANCE = this;
        BASE_SHAREPREFERENCE.init(this);

        BaseUrl = getBaseUrl();
        IS_DEBUG = getIsDebug();

        if (BASE_SHAREPREFERENCE.readLastGetUrlsTime().equals("")) {
            goURL_All();
        } else {
            if (timeDifferent() >= 1) {
                goURL_All();
            }
        }
    }

    /**
     * 获取主请求地址
     *
     * @return
     */
    public abstract String getBaseUrl();

    /**
     * 是否开启调试
     * 开启则不启动base64加密
     *
     * @return
     */
    public abstract boolean getIsDebug();

    private void goURL_All() {

        TelephonyManager tm = (TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE);

        String ANDROID_ID = Settings.System.getString(getContentResolver(), Settings.System.ANDROID_ID);


//        String DEVICE_ID = tm.getDeviceId();//唯一设备号
        String DEVICE_ID = ANDROID_ID;//唯一设备号

        OkHttpUtils
                .post()
                .url(BaseUrl)
                .addParams("app_key", MD5Util.getMD5Key(BaseUrl))
                .addParams("sys_type", "android")
                .addParams("uuid",DEVICE_ID)
                .addParams("sys_version", SystemUtils.getSystemVersion())
                .addParams("device_type", "android")
                .addParams("brand", SystemUtils.getPhoneBrand())
                .addParams("model", SystemUtils.getPhoneModel())
                .addParams("lat", "0.0")
                .addParams("lng", "0.0")
                .addParams("u_id", "0")
                .build()
                .execute(new JsonCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Toast.makeText(getApplicationContext(), "请检查网络设置", Toast.LENGTH_SHORT).show();
                    }
                    @Override
                    public void onResponse(BaseJsonEntity response, int id) {

                        if (response.getCode().equals("200")) {
                            BASE_SHAREPREFERENCE.saveLastGetUrlsTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new java.util.Date()));
                            MainJsonObjMap = FastJsonUtils.getJsonMap(response.getObj());//全部信息
                            //  RequestMap = FastJsonUtils.getJsonMap(MainJsonObjMap.get(MAIN_JSON_API));//所有接口
                            List<Map<String, String>> host_all_json_info = new ArrayList<Map<String, String>>();
                            host_all_json_info.add(MainJsonObjMap);
                            saveInfo(host_all_json_info);
                        }
                    }
                });
    }

    /**
     * 打开应用时得到与上次获取接口的时间差 多于一天时重新获取，否则读取本地数据
     */

    private long timeDifferent() {

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date = df.format(new java.util.Date());

        long days = 0;

        try {
            Date d_past = df.parse(new BaseSharePreference().readLastGetUrlsTime());
            Date d_now = df.parse(date);
            long diff = d_now.getTime() - d_past.getTime();//这样得到的差值是微秒级别
            days = diff / (1000 * 60 * 60 * 24);
//            long hours = (diff - days * (1000 * 60 * 60 * 24)) / (1000 * 60 * 60);
//            long minutes = (diff - days * (1000 * 60 * 60 * 24) - hours * (1000 * 60 * 60)) / (1000 * 60);
//            System.out.println("" + days + "天" + hours + "小时" + minutes + "分");

        } catch (Exception e) {
        }

        return days;
    }

    /**
     * 保存主接口数据到本地
     *
     * @param datas
     */
    public void saveInfo(List<Map<String, String>> datas) {
        JSONArray mJsonArray = new JSONArray();
        for (int i = 0; i < datas.size(); i++) {
            Map<String, String> itemMap = datas.get(i);
            Iterator<Map.Entry<String, String>> iterator = itemMap.entrySet().iterator();

            JSONObject object = new JSONObject();

            while (iterator.hasNext()) {
                Map.Entry<String, String> entry = iterator.next();
                try {
                    object.put(entry.getKey(), entry.getValue());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            mJsonArray.put(object);
        }

        BASE_SHAREPREFERENCE.saveBaseHostApiInfo(mJsonArray.toString());

    }




}
