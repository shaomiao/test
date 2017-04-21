package com.pkyapp.jinzaizhijianmerchant.merchant.login;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.pkyapp.jinzaizhijianmerchant.BaseActivity;
import com.pkyapp.jinzaizhijianmerchant.R;
import com.pkyapp.jinzaizhijianmerchant.app.WXApplication;
import com.pkyapp.jinzaizhijianmerchant.merchant.MerchantActivity;
import com.pkyapp.jinzaizhijianmerchant.util.ActivityCollector;
import com.pkyapp.jinzaizhijianmerchant.util.ConstantUtil;
import com.pkyapp.jinzaizhijianmerchant.util.ValidateUtil;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * @author shaomiao
 * @Date 2017/3/28
 * @Time 10:00
 */

public class LoginActivity extends BaseActivity implements View.OnClickListener,LoginContract.View {

    private Button mLoginBtn;
    private EditText mTelEt, mPasswordEt;
    private Context mContext;
    private LoginContract.Presenter mPresenter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        // 登录超时
        if (WXApplication.login_state == 0) {
            // TODO 超时Alert
            new AlertDialog.Builder(this).setMessage(ConstantUtil.LOGIN_TIMEOUT)//设置显示的内容
                    .setPositiveButton("确定",new DialogInterface.OnClickListener() {//添加确定按钮
                        @Override
                        public void onClick(DialogInterface dialog, int which) {//确定按钮的响应事件
                            // TODO Auto-generated method stub
//                            finish();
                        }

                    }).show();//在按键响应事件中显示此对话框
        }
        setContentView(R.layout.activity_login);
        initView();
        initInfo();
        mPresenter = new LoginPresenter(this);

    }

    private void initView() {
        mLoginBtn = (Button) findViewById(R.id.login_btn);
        mTelEt = (EditText) findViewById(R.id.user_tel_edit);
        mPasswordEt = (EditText) findViewById(R.id.user_passwd_edit);

    }

    private void initInfo() {
        mLoginBtn.setOnClickListener(this);
    }

    private boolean finishFlag = true;
    private long oldTime;
    @Override
    public void onBackPressed() {
        if (finishFlag) {
            finishFlag = !finishFlag;
            Toast.makeText(this, "再按一次退出程序！", Toast.LENGTH_SHORT).show();
            oldTime = System.currentTimeMillis() / 1000;
        } else {
            finishFlag = !finishFlag;
            long newTime = System.currentTimeMillis() / 1000;
            if ((newTime - oldTime) < 2) {
                ActivityCollector.finishAllActivity();
                // 强制杀死当前进程
                //android.os.Process.killProcess(android.os.Process.myPid());
            }
        }

    }
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.login_btn:
                if (ValidateUtil.isPhoneNum(mTelEt) && (ValidateUtil.inNotNull(mPasswordEt, "密码"))) {
                    mPresenter.login(mTelEt.getText().toString().trim(),mPasswordEt.getText().toString().trim());
                }
                break;
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.start();
    }

    @Override
    public void showLoading() {
        mDialog.show();
    }

    @Override
    public void hideLoading() {
        mDialog.hide();
    }

    @Override
    public void showFailMsg(String msg) {
        showMessage(msg);
    }

    @Override
    public void showSuccessMsg() {
        showMessage(ConstantUtil.LOGIN_SUCCESS);
    }

    @Override
    public void showMerchantUi() {
        startActivity(new Intent(this,MerchantActivity.class));
    }

    @Override
    public void setPresenter(LoginContract.Presenter presenter) {
        mPresenter = checkNotNull(presenter);
    }

    private void showMessage(String message) {
        Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
    }
}
