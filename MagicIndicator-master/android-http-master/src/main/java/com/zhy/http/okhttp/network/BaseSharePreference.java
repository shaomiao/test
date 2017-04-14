package com.zhy.http.okhttp.network;

import android.content.Context;
import android.content.SharedPreferences;

import com.zhy.http.okhttp.application.BaseApplication;

/**
 * Created by pc002 on 2017/3/15.
 */

public class BaseSharePreference {


    private SharedPreferences sharedPreferences;
    private String LAST_GET_URLS_TIME = "LAST_GET_URLS_TIME";
    private String BASE_HOST_API_INFO = "BASE_HOST_API_INFO";


    public void init(Context context) {
        sharedPreferences = context.getSharedPreferences("URL_DATABASE", Context.MODE_PRIVATE);
    }

    /**
     * 上次获取子接口时间
     *
     * @param last_get_urls_time
     */
    public void saveLastGetUrlsTime(String last_get_urls_time) {
        sharedPreferences.edit().putString(LAST_GET_URLS_TIME, last_get_urls_time).commit();
    }

    public String readLastGetUrlsTime() {
        return sharedPreferences.getString(LAST_GET_URLS_TIME, "");
    }

    /**
     * 主接口所有信息存储到本地
     *
     * @param base_host_api_info
     */
    public void saveBaseHostApiInfo(String base_host_api_info) {
        sharedPreferences.edit().putString(BASE_HOST_API_INFO, base_host_api_info).commit();
    }

    public String readBaseHostApiInfo() {
        return sharedPreferences.getString(BASE_HOST_API_INFO, "");
    }


}
