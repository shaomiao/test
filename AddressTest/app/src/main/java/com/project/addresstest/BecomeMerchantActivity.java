package com.project.addresstest;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * @author shaomiao
 * @Date 2017/4/22
 * @Time 11:20
 * 成为商家页面
 */

public class BecomeMerchantActivity extends AppCompatActivity implements View.OnClickListener {

    private LinearLayout mShoppingMerchantsLL, mTakeawayLL;
    private ImageView mShoppingMerchantsIv, mTakeawayIv;
    private TextView mShoppingMerchantsTv, mTakeawayTv;
    private EditText mNameEt, mIdCardEt, mPhoneEt;
    private ImageView mUploadIdIv, mUploadBusinessLicenseIv;
    private Button mSubmitBt;

    // 选中状态 0是购物商家 1是外卖商家
    private int checkState = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_become_merchant);
        initView();
        initData();
    }

    private void initView() {
        mShoppingMerchantsLL = (LinearLayout) findViewById(R.id.shopping_merchants_ll);
        mShoppingMerchantsIv = (ImageView) findViewById(R.id.shopping_merchants_iv);
        mShoppingMerchantsTv = (TextView) findViewById(R.id.shopping_merchants_tv);
        mTakeawayLL = (LinearLayout) findViewById(R.id.takeaway_ll);
        mTakeawayIv = (ImageView) findViewById(R.id.takeaway_iv);
        mTakeawayTv = (TextView) findViewById(R.id.takeaway_tv);
        mNameEt = (EditText) findViewById(R.id.name_et);
        mIdCardEt = (EditText) findViewById(R.id.id_card_et);
        mPhoneEt = (EditText) findViewById(R.id.phone_et);
        mUploadIdIv = (ImageView) findViewById(R.id.upload_id_iv);
        mUploadBusinessLicenseIv = (ImageView) findViewById(R.id.upload_business_license_iv);
        mSubmitBt = (Button) findViewById(R.id.submit_bt);
        mShoppingMerchantsLL.setOnClickListener(this);
        mTakeawayLL.setOnClickListener(this);
        mSubmitBt.setOnClickListener(this);
    }

    private void initData() {
        check();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            // 选中购物商家
            case R.id.shopping_merchants_ll:
                checkState = 0;
                check();
                break;
            // 选中外卖商家
            case R.id.takeaway_ll:
                checkState = 1;
                check();
                break;
            // 提交
            case R.id.submit_bt:
                submit();
                break;
        }
    }

    private void submit() {
        if (mNameEt.getText().toString()==null||mNameEt.getText().toString().equals("")){
            mNameEt.setError("请输入姓名");
            // 可获得焦点状态
            mNameEt.setFocusable(true);
            mNameEt.requestFocus();
        } else {
            //TODO 走接口


        }
    }

    private void check() {
        if (checkState == 0) {
            mShoppingMerchantsIv.setImageResource(R.mipmap.merchant_choice_sel);
            mShoppingMerchantsTv.setTextColor(ContextCompat.getColor(this, R.color.font_yellow));
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                mShoppingMerchantsLL.setBackground(ContextCompat.getDrawable(this, R.drawable.shape_become_merchant_sel));
            } else {
                mShoppingMerchantsLL.setBackgroundDrawable(ContextCompat.getDrawable(this, R.drawable.shape_become_merchant_sel));
            }
            mTakeawayIv.setImageResource(R.mipmap.merchant_choice_no);
            mTakeawayTv.setTextColor(ContextCompat.getColor(this, R.color.font));
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                mTakeawayLL.setBackground(ContextCompat.getDrawable(this, R.drawable.shape_become_merchant_no));
            } else {
                mTakeawayLL.setBackgroundDrawable(ContextCompat.getDrawable(this, R.drawable.shape_become_merchant_no));
            }
        } else {
            mShoppingMerchantsIv.setImageResource(R.mipmap.merchant_choice_no);
            mShoppingMerchantsTv.setTextColor(ContextCompat.getColor(this, R.color.font));
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                mShoppingMerchantsLL.setBackground(ContextCompat.getDrawable(this, R.drawable.shape_become_merchant_no));
            } else {
                mShoppingMerchantsLL.setBackgroundDrawable(ContextCompat.getDrawable(this, R.drawable.shape_become_merchant_no));
            }
            mTakeawayIv.setImageResource(R.mipmap.merchant_choice_sel);
            mTakeawayTv.setTextColor(ContextCompat.getColor(this, R.color.font_yellow));
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                mTakeawayLL.setBackground(ContextCompat.getDrawable(this, R.drawable.shape_become_merchant_sel));
            } else {
                mTakeawayLL.setBackgroundDrawable(ContextCompat.getDrawable(this, R.drawable.shape_become_merchant_sel));
            }
        }
    }
}
