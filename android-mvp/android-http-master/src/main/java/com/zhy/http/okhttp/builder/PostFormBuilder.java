package com.zhy.http.okhttp.builder;

import android.util.Log;

import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.request.PostFormRequest;
import com.zhy.http.okhttp.request.RequestCall;
import com.zhy.http.okhttp.utils.Base64Util;
import com.zhy.http.okhttp.utils.MD5Util;

import java.io.File;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zhy on 15/12/14.  ssy修改
 */
public class PostFormBuilder extends OkHttpRequestBuilder<PostFormBuilder> implements HasParamsable {
    private List<FileInput> files = new ArrayList<>();

    @Override
    public RequestCall build() {
        return new PostFormRequest(url, tag, params, headers, files, id).build();
    }

    public PostFormBuilder files(String key, Map<String, File> files) {
        for (String filename : files.keySet()) {
            this.files.add(new FileInput(key, filename, files.get(filename)));
        }
        return this;
    }

    public PostFormBuilder addFile(String name, String filename, File file) {
        files.add(new FileInput(name, filename, file));
        return this;
    }

    public static class FileInput {
        public String key;
        public String filename;
        public File file;

        public FileInput(String name, String filename, File file) {
            this.key = name;
            this.filename = filename;
            this.file = file;
        }

        @Override
        public String toString() {
            return "FileInput{" +
                    "key='" + key + '\'' +
                    ", filename='" + filename + '\'' +
                    ", file=" + file +
                    '}';
        }
    }


    @Override
    public PostFormBuilder params(Map<String, String> params) {
        this.params = params;
        for (Map.Entry<String, String> entry : params.entrySet()) {
            if (OkHttpUtils.is_debug) {
                Log.i("偏口鱼app", entry.getKey() + "-------" + entry.getValue());//打印参数名
            }
            entry.setValue(Base64Util.encryptBASE64(entry.getValue()));//Base64加密
        }
        return this;
    }

    @Override
    public PostFormBuilder addParams(String key, String value) {
        if (this.params == null) {
            params = new LinkedHashMap<>();
        }
        if (OkHttpUtils.is_debug) {
            Log.i("偏口鱼app", key + "-------" + value);//打印参数名
        }
        value = Base64Util.encryptBASE64(value);//Base64加密

        params.put(key, value);

        return this;
    }


}
