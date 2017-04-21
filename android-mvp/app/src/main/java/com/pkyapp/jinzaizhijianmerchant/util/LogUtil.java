package com.pkyapp.jinzaizhijianmerchant.util;

import android.util.Log;

/**
 * @author shaomiao
 * @Date 2017/3/23
 * @Time 16:56
 */

public class LogUtil {
    public static boolean is_debug = true;//是否为开发环境

    public static void e (String tag,String msg) {
        if (is_debug) {
            Log.e(tag, msg);
        }
    }

    public static void i (String tag,String msg) {
        if (is_debug) {
            Log.i(tag, msg);
        }
    }

    public static void d (String tag,String msg) {
        if (is_debug) {
            Log.d(tag, msg);
        }
    }

    public static void v (String tag,String msg) {
        if (is_debug) {
            Log.v(tag, msg);
        }
    }
}
