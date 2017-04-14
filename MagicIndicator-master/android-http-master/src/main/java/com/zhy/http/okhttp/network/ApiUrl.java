package com.zhy.http.okhttp.network;

import android.util.Log;
import android.widget.Toast;

import com.zhy.http.okhttp.application.BaseApplication;
import com.zhy.http.okhttp.utils.FastJsonUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import static com.zhy.http.okhttp.application.BaseApplication.BASE_SHAREPREFERENCE;

/**
 * Created by ssy on 2017/3/15.
 * <p>
 * 根据关键字获取api的url
 */

public class ApiUrl {

    /**
     * 唯一实例化
     */
    private volatile static ApiUrl INSTANCE = null;

    public static ApiUrl getInstance() {
        if (INSTANCE == null) {
            synchronized (ApiUrl.class) {
                if (INSTANCE == null) {
                    INSTANCE = new ApiUrl();
                }
            }
        }
        return INSTANCE;
    }

    /**
     * 获取保存的主接口本地数据
     *
     * @return
     */
    public List<Map<String, String>> getInfo() {
        List<Map<String, String>> datas = new ArrayList<Map<String, String>>();

        String result = BASE_SHAREPREFERENCE.readBaseHostApiInfo();
        try {
            JSONArray array = new JSONArray(result);
            for (int i = 0; i < array.length(); i++) {
                JSONObject itemObject = array.getJSONObject(i);
                Map<String, String> itemMap = new HashMap<String, String>();
                JSONArray names = itemObject.names();
                if (names != null) {
                    for (int j = 0; j < names.length(); j++) {
                        String name = names.getString(j);
                        String value = itemObject.getString(name);
                        itemMap.put(name, value);
                    }
                }
                datas.add(itemMap);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return datas;
    }


    public String getApi(String key) {

        if (BaseApplication.RequestMap == null) {
            if (getInfo().size() == 0) {
                Toast.makeText(BaseApplication.INSTANCE.getApplicationContext(),"请检查网络设置",Toast.LENGTH_SHORT).show();
                return "";
            } else {
                Map<String, String> info = getInfo().get(0);
                BaseApplication.RequestMap = FastJsonUtils.getJsonMap(info.get("api"));
                if (BaseApplication.RequestMap.get(key) == null) {
                    Log.e("偏口鱼app", "该路径不存在");
                    return "";
                } else {
                    return BaseApplication.RequestMap.get(key);
                }
            }
        } else {
            if (BaseApplication.RequestMap.get(key) == null) {
                Log.e("偏口鱼app", "该路径不存在");
                return "";
            } else {
                return BaseApplication.RequestMap.get(key);
            }
        }
    }


}
