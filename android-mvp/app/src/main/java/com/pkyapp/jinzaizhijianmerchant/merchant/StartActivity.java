package com.pkyapp.jinzaizhijianmerchant.merchant;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.pkyapp.jinzaizhijianmerchant.app.WXApplication;
import com.pkyapp.jinzaizhijianmerchant.merchant.login.LoginActivity;

/**
 * @author shaomiao
 * @Date 2017/3/28
 * @Time 9:38
 */

public class StartActivity extends AppCompatActivity {

    private final int MSG = 1000;
    private final int TIME = 1000;

    public Handler handler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case MSG:
                    if (WXApplication.ContastPF.readLogin()) {
                        // 跳转首页
                        startActivity(new Intent(StartActivity.this,MerchantActivity.class));
                    } else {
                        // 跳转登录页
                        startActivity(new Intent(StartActivity.this,LoginActivity.class));
                    }
                    finish();
                    break;
                default:
                    break;
            }
            super.handleMessage(msg);
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_start);
        new Thread() {
            @Override
            public void run() {
                handler.sendEmptyMessageDelayed(MSG, TIME);
                super.run();
            }
        }.run();
    }
}
