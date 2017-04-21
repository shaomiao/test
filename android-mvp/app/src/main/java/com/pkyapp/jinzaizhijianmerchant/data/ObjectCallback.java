package com.pkyapp.jinzaizhijianmerchant.data;

import android.widget.Toast;

import com.pkyapp.jinzaizhijianmerchant.app.WXApplication;
import com.pkyapp.jinzaizhijianmerchant.util.ActivityCollector;
import com.pkyapp.jinzaizhijianmerchant.util.ActivityUtils;
import com.pkyapp.jinzaizhijianmerchant.util.ConstantUtil;
import com.pkyapp.jinzaizhijianmerchant.util.LogUtil;
import com.zhy.http.okhttp.callback.Callback;
import com.zhy.http.okhttp.entity.BaseJsonEntity;
import com.zhy.http.okhttp.utils.Base64Util;
import com.zhy.http.okhttp.utils.FastJsonUtils;

import okhttp3.Call;
import okhttp3.Response;

/**
 * @author shaomiao
 * @Date 2017/3/30
 * @Time 9:30
 */

public abstract class ObjectCallback extends Callback<BaseJsonEntity> {
    @Override
    public BaseJsonEntity parseNetworkResponse(Response response, int id) throws Exception {
        String json= Base64Util.decryptBASE64(response.body().string());
        BaseJsonEntity entity=new BaseJsonEntity();
        entity.setCode(FastJsonUtils.getStr(json,"code"));
        entity.setMessage(FastJsonUtils.getStr(json,"message"));
        entity.setObj(FastJsonUtils.getStr(json,"obj"));
        if(entity.getCode().equals(ConstantUtil.LOGIN_TIMEOUT_CODE)) {
            // 清空所有activity
            ActivityCollector.finishAllActivity();
            // 跳转login页面
            WXApplication.getInstance().startLogin();
        }
        return entity;
    }

}
