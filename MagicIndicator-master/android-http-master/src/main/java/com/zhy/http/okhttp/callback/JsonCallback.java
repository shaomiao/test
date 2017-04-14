package com.zhy.http.okhttp.callback;


import com.zhy.http.okhttp.entity.BaseJsonEntity;
import com.zhy.http.okhttp.utils.Base64Util;
import com.zhy.http.okhttp.utils.FastJsonUtils;

import java.io.IOException;

import okhttp3.Response;

/**
 * Created by ssy on 15/12/14.
 *
 * 解析json基础实体类
 */
public abstract class JsonCallback extends Callback<BaseJsonEntity>
{
    @Override
    public BaseJsonEntity parseNetworkResponse(Response response, int id) throws IOException
    {
        String json= Base64Util.decryptBASE64(response.body().string());
        BaseJsonEntity entity=new BaseJsonEntity();
        entity.setCode(FastJsonUtils.getStr(json,"code"));
        entity.setMessage(FastJsonUtils.getStr(json,"message"));
        entity.setObj(FastJsonUtils.getStr(json,"obj"));
        return entity;
    }

}
