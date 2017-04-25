package com.pkyapp.jinzaizhijianmerchant;

import android.app.Application;

import cn.jpush.android.api.JPushInterface;

/**
 * @author shaomiao
 * @Date 2017/4/25
 * @Time 11:24
 */

public class ExampleApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        JPushInterface.setDebugMode(true);
        JPushInterface.init(this);
    }
}
