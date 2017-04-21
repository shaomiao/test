package com.pkyapp.jinzaizhijianmerchant.ui;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.WindowManager;

import com.pkyapp.jinzaizhijianmerchant.R;

/**
 * @author shaomiao
 * @Date 2017/3/13
 * @Time 17:05
 */

public class CustomProgressDialog extends ProgressDialog {
    public CustomProgressDialog(Context context) {
        super(context, R.style.CustomDialog);
    }

    public CustomProgressDialog(Context context, int theme) {
        super(context, theme);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init(getContext());
    }

    private void init(Context context) {
        // 设置不可取消 点击其他区域不能取消
//        setCancelable(false);
        setCanceledOnTouchOutside(false);

        setContentView(R.layout.progress_dialog_layout);
        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.width = WindowManager.LayoutParams.WRAP_CONTENT;
        params.height = WindowManager.LayoutParams.WRAP_CONTENT;
        getWindow().setAttributes(params);
    }

    @Override
    public void show() {
        super.show();
    }
}
