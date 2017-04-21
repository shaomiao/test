package com.pkyapp.jinzaizhijianmerchant.merchant.order.detail;

import android.app.AlertDialog;
import android.app.Instrumentation;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.pkyapp.jinzaizhijianmerchant.BaseActivity;
import com.pkyapp.jinzaizhijianmerchant.MainActivity;
import com.pkyapp.jinzaizhijianmerchant.R;
import com.pkyapp.jinzaizhijianmerchant.app.WXApplication;
import com.pkyapp.jinzaizhijianmerchant.data.AppAction;
import com.pkyapp.jinzaizhijianmerchant.data.ObjectCallback;
import com.pkyapp.jinzaizhijianmerchant.data.entity.OrderDetailEntity;
import com.pkyapp.jinzaizhijianmerchant.data.entity.OrderEntity;
import com.pkyapp.jinzaizhijianmerchant.data.entity.TabEntity;
import com.pkyapp.jinzaizhijianmerchant.data.source.OrderDataSource;
import com.pkyapp.jinzaizhijianmerchant.data.source.remote.OrderRemoteDataSource;
import com.pkyapp.jinzaizhijianmerchant.merchant.MerchantActivity;
import com.pkyapp.jinzaizhijianmerchant.merchant.statistics.StatisticsFragment;
import com.pkyapp.jinzaizhijianmerchant.util.ConstantUtil;
import com.pkyapp.jinzaizhijianmerchant.util.LogUtil;
import com.zhy.http.okhttp.entity.BaseJsonEntity;
import com.zhy.http.okhttp.utils.FastJsonUtils;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;

/**
 * @author shaomiao
 * @Date 2017/3/30
 * @Time 15:00
 */

public class OrderDetailActivity extends BaseActivity implements View.OnClickListener {

    public static final String ORDER_ID = "ORDER_ID";
    public static final String STATE_ID = "STATE_ID";

    // 确认订单
    private static final String CONFIRM_ORDER = "1";
    // 确认发货
    private static final String CONFIRM_GOODS = "2";

    private TextView mOrderStateView, mOrderIdView, mOrderTimeView, mAddressView,
            mAmountView, mFreightView;
    private RecyclerView mProductDetailView;

    private Button mButton;

    private String mOrderId, mStateId;

    private OrderDetailAdapter mAdapter;
    private List<OrderDetailEntity> orderdetails;
    private ImageView mBackView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_detail);
        mOrderId = getIntent().getStringExtra(ORDER_ID) != null && !getIntent().getStringExtra(ORDER_ID).equals("") ?
                getIntent().getStringExtra(ORDER_ID) : "0";

        mStateId = getIntent().getStringExtra(STATE_ID) != null && !getIntent().getStringExtra(STATE_ID).equals("") ?
                getIntent().getStringExtra(STATE_ID) : "0";
        initView();
        initData();
        orderdetails = new ArrayList<>();
        mAdapter = new OrderDetailAdapter(this,orderdetails);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mProductDetailView.setLayoutManager(layoutManager);
        mProductDetailView.setAdapter(mAdapter);
    }

    private void initView() {
        mOrderStateView = (TextView) findViewById(R.id.orderStateTv);
        mOrderIdView = (TextView) findViewById(R.id.orderIdTv);
        mOrderTimeView = (TextView) findViewById(R.id.orderTimeTv);
        mProductDetailView = (RecyclerView) findViewById(R.id.productDetailsRv);
        mAddressView = (TextView) findViewById(R.id.addressTv);
        mAmountView = (TextView) findViewById(R.id.amountTv);
        mFreightView = (TextView) findViewById(R.id.freightTv);
        mButton = (Button) findViewById(R.id.button);
        mBackView = (ImageView) findViewById(R.id.backIv);
        mButton.setOnClickListener(this);
        mBackView.setOnClickListener(this);
        // 显示按钮
        if (mStateId.equals(ConstantUtil.ORDER_STATE_NOT_CONFIRMED_CODE)) {
            // 未确认
            mButton.setVisibility(View.VISIBLE);
            mButton.setText(ConstantUtil.CONFIRM_ORDER);
        } else if(mStateId.equals(ConstantUtil.ORDER_STATE_WAITING_DELIVERY_CODE)) {
            // 代发货
            mButton.setVisibility(View.VISIBLE);
            mButton.setText(ConstantUtil.DELIVER_GOODS);
        } else if (mStateId.equals(ConstantUtil.ORDER_STATE_ALREADY_CODE)) {
            // 已完成
            mButton.setVisibility(View.VISIBLE);
            mButton.setText(ConstantUtil.VIEW_COMMENTS);
        }
    }

    private void initData() {
        mOrderStateView.setText(getStateName(mStateId));
        getOrderDetal();
    }

    private String getStateName(String stateid) {
        List<TabEntity> tabs = WXApplication.ContastPF.readTabList();
        for (TabEntity tab :
                tabs) {
            if (Integer.parseInt(stateid) == tab.getId()) {
                return tab.getName();
            }
        }
        return "";
    }

    private void getOrderDetal() {
        OrderRemoteDataSource.getInstance().getOrderDetail(WXApplication.ContastPF.readShopId(), WXApplication.ContastPF.readShopToken(),
                mOrderId, new OrderDataSource.GetOrderDetailCallback() {
                    @Override
                    public void onOrder(OrderEntity order) {
                        if (order != null) {
                            mOrderIdView.setText(order.getOrderId());
                            mAddressView.setText(order.getAddress());
                            mAmountView.setText(order.getAllPrice());
                            mFreightView.setText(order.getFreight());
                        }
                    }

                    @Override
                    public void onOrderDetailList(List<OrderDetailEntity> details) {
                        if (details != null && details.size() > 0) {
                            orderdetails.clear();
                            orderdetails.addAll(details);
                            mAdapter.notifyDataSetChanged();
                        }
                    }

                    @Override
                    public void onDataNotAvailable(String msg) {
                        Toast.makeText(OrderDetailActivity.this, ConstantUtil.NETWORK_ERR, Toast.LENGTH_SHORT).show();
                    }
                });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.button:
                if (mStateId.equals(ConstantUtil.ORDER_STATE_NOT_CONFIRMED_CODE)) {
                    // 未确认 接受订单
                    confirmOrder(CONFIRM_ORDER);

                } else if(mStateId.equals(ConstantUtil.ORDER_STATE_WAITING_DELIVERY_CODE)) {
                    // 代发货 发货


                } else if (mStateId.equals(ConstantUtil.ORDER_STATE_ALREADY_CODE)) {
                    // 已完成 查看评论

                }
                break;
            case R.id.backIv:
                new Thread() {
                    public void run() {
                        try {
                            Instrumentation inst = new Instrumentation();
                            inst.sendKeyDownUpSync(KeyEvent.KEYCODE_BACK);
                        } catch (Exception e) {

                        }
                    }
                }.start();
                break;
        }

    }

    private void confirmOrder(String type) {
        // 	1确认订单2确认发货
            AppAction.getInstance().shopOrderAdd(WXApplication.ContastPF.readShopId(),
                    WXApplication.ContastPF.readShopToken(),mOrderId,type, new ObjectCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                Toast.makeText(OrderDetailActivity.this, ConstantUtil.NETWORK_ERR, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onResponse(BaseJsonEntity response, int id) {
                if (response.getCode().equals(ConstantUtil.NETWORK_SUCCESS_CODE)) {
                    // TODO 确认成功
                    new AlertDialog.Builder(OrderDetailActivity.this)
                            .setMessage("确认成功")
                            .setPositiveButton("确定",
                                    new DialogInterface.OnClickListener(){
                                        public void onClick(DialogInterface dialoginterface, int i){
                                        }
                                    }).show();

                }
                else if (response.getCode().equals(ConstantUtil.NETWORK_FAIL_CODE)) {
                    Toast.makeText(OrderDetailActivity.this, response.getMessage(), Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(OrderDetailActivity.this, ConstantUtil.NETWORK_ERR, Toast.LENGTH_SHORT).show();
                }

            }
        });
    }


    public static class OrderDetailAdapter extends RecyclerView.Adapter<OrderDetailAdapter.ViewHolder> {

        public Context mContext;
        private List<OrderDetailEntity> mDetailList;

        public OrderDetailAdapter(Context context, List<OrderDetailEntity> detalList) {
            this.mContext = context;
            this.mDetailList = detalList;
        }


        @Override
        public OrderDetailAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.activity_order_detail_item, parent, false);
            final OrderDetailAdapter.ViewHolder holder = new OrderDetailAdapter.ViewHolder(view);
            return holder;
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            OrderDetailEntity detail = mDetailList.get(position);
            holder.mProductNumView.setText(detail.getProductNum());
            holder.mProductNameView.setText(detail.getProductName());
            holder.mTotalView.setText(detail.getProductPrice());
            holder.mSpecView.setText(detail.getProductSpec());
        }

        @Override
        public int getItemCount() {
            return mDetailList.size();
        }


        class ViewHolder extends RecyclerView.ViewHolder {

            View productView;
            TextView mProductNameView, mSpecView, mProductNumView, mTotalView;

            public ViewHolder(View itemView) {
                super(itemView);
                productView = itemView;
                mProductNameView = (TextView) itemView.findViewById(R.id.productNameTv);
                mSpecView = (TextView) itemView.findViewById(R.id.specTv);
                mProductNumView = (TextView) itemView.findViewById(R.id.productNumTv);
                mTotalView = (TextView) itemView.findViewById(R.id.totalTv);
            }
        }

    }

}
