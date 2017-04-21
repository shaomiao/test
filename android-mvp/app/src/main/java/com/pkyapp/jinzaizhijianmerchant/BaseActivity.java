package com.pkyapp.jinzaizhijianmerchant;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.EventLogTags;
import android.util.Log;
import android.widget.Toast;

import com.pkyapp.jinzaizhijianmerchant.ui.CustomProgressDialog;
import com.pkyapp.jinzaizhijianmerchant.util.ActivityCollector;
import com.pkyapp.jinzaizhijianmerchant.util.LogUtil;

/**
 * @author shaomiao
 * @Date 2017/3/23
 * @Time 16:52
 */

public class BaseActivity extends AppCompatActivity {


    private static final String TAG = "BaseActivity";

    public CustomProgressDialog mDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LogUtil.d(TAG, getClass().getSimpleName());
        ActivityCollector.addActivity(this);
        mDialog = new CustomProgressDialog(this);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityCollector.removeActivity(this);
        if (mDialog != null) {
            mDialog.dismiss();
        }
    }

}
