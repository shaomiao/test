package com.pkyapp.jinzaizhijianmerchant.util;

import android.app.Activity;
import android.content.Intent;

import com.pkyapp.jinzaizhijianmerchant.merchant.login.LoginActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * @author shaomiao
 * @Date 2017/3/23
 * @Time 16:52
 */

public class ActivityCollector {

    public static List<Activity> activities = new ArrayList<Activity>();

    /**
     * 添加Activity
     *
     * @param activity
     */
    public static void addActivity(Activity activity) {
        activities.add(activity);
    }

    /**
     * 移除Activity
     *
     * @param activity
     */
    public static void removeActivity(Activity activity) {
        activities.remove(activity);
    }

    /**
     * 移除所有Activity
     *
     */
    public static void finishAllActivity() {
        for (Activity activity : activities) {
            if (!activity.isFinishing()) {
                activity.finish();
            }
        }
    }

}
