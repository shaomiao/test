package com.pkyapp.jinzaizhijianmerchant.merchant;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.pkyapp.jinzaizhijianmerchant.BaseActivity;
import com.pkyapp.jinzaizhijianmerchant.R;
import com.pkyapp.jinzaizhijianmerchant.merchant.my.MyFragment;
import com.pkyapp.jinzaizhijianmerchant.merchant.order.OrderFragment;
import com.pkyapp.jinzaizhijianmerchant.merchant.statistics.StatisticsFragment;
import com.pkyapp.jinzaizhijianmerchant.util.ActivityCollector;
import com.pkyapp.jinzaizhijianmerchant.util.ActivityUtils;

import java.security.PrivateKey;

/**
 * @author shaomiao
 * @Date 2017/3/22
 * @Time 10:08
 * bottombar 页面
 */

public class MerchantActivity extends BaseActivity implements View.OnClickListener {

    private Fragment fragment;
    private int contentFrame;
    private TextView order,statistics,my;
    // 处理在当前页面重复点击按钮
    private static int bottonPostion = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_merchant);
        initView();
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

    private void initView() {
        contentFrame = R.id.contentFrame;

        order = (TextView) findViewById(R.id.tv_order);
        statistics = (TextView) findViewById(R.id.tv_statistics);
        my = (TextView) findViewById(R.id.tv_my);

        order.setOnClickListener(this);
        statistics.setOnClickListener(this);
        my.setOnClickListener(this);


        fragment = getSupportFragmentManager().findFragmentById(contentFrame);
        // 第一次选中
        if (fragment == null) {
            fragment = OrderFragment.newInstance();
        }
        updateTextStatue(order,ContextCompat.getDrawable(this,R.mipmap.merchant_order_sel),true);
        ActivityUtils.addFragmentToActivity(
                getSupportFragmentManager(), fragment, contentFrame);
        bottonPostion = R.id.tv_order;
    }

    @Override
    public void onClick(View view) {
        if(bottonPostion != view.getId()) {
            cleanColor();
            bottonPostion = view.getId();
            switch (view.getId()) {
                // 订单
                case R.id.tv_order:
                    updateTextStatue(order, ContextCompat.getDrawable(this, R.mipmap.merchant_order_sel), true);
                    fragment = OrderFragment.newInstance();
                    break;
                // 销售统计
                case R.id.tv_statistics:
                    updateTextStatue(statistics, ContextCompat.getDrawable(this, R.mipmap.merchant_statistics_sel), true);
                    fragment = StatisticsFragment.newInstance();
                    break;
                // 我的
                case R.id.tv_my:
                    updateTextStatue(my, ContextCompat.getDrawable(this, R.mipmap.merchant_head_sel), true);
                    fragment = MyFragment.newInstance();
                    break;
            }
            ActivityUtils.replaceFragmentToActivity(
                    getSupportFragmentManager(), fragment, contentFrame);
        }
    }

    /**
     * 修改点击状态
     * @param textview
     * @param drawable 修改的图标
     * @param flag 是否是选中状态 true选中状态 false 原始状态
     */
    private void updateTextStatue(TextView textview, Drawable drawable,boolean flag) {
        if (flag) {
            textview.setCompoundDrawablesWithIntrinsicBounds(null, drawable, null, null);
            textview.setTextColor(ContextCompat.getColor(this, R.color.bottomTextSelected));
        } else {
            textview.setCompoundDrawablesWithIntrinsicBounds(null, drawable, null, null);
            textview.setTextColor(ContextCompat.getColor(this, R.color.titleColor));
        }
    }

    /**
     * 清除选中项颜色
     */
    private void cleanColor() {
        updateTextStatue(order,ContextCompat.getDrawable(this,R.mipmap.merchant_order_no),false);
        updateTextStatue(statistics,ContextCompat.getDrawable(this,R.mipmap.merchant_statistics_no),false);
        updateTextStatue(my,ContextCompat.getDrawable(this,R.mipmap.merchant_head_no),false);
    }
}
