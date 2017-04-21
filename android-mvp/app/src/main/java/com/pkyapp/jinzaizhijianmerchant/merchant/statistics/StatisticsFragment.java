package com.pkyapp.jinzaizhijianmerchant.merchant.statistics;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.pkyapp.jinzaizhijianmerchant.R;
import com.pkyapp.jinzaizhijianmerchant.data.entity.OrderEntity;
import com.pkyapp.jinzaizhijianmerchant.data.entity.StatisticsEntity;
import com.pkyapp.jinzaizhijianmerchant.merchant.order.OrderListFragment;
import com.pkyapp.jinzaizhijianmerchant.merchant.order.detail.OrderDetailActivity;
import com.pkyapp.jinzaizhijianmerchant.ui.CustomProgressDialog;
import com.pkyapp.jinzaizhijianmerchant.util.LogUtil;
import com.squareup.timessquare.CalendarPickerView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * @author shaomiao
 * @Date 2017/3/24
 * @Time 13:42
 */

public class StatisticsFragment extends Fragment implements View.OnClickListener, StatisticsContract.View {

    private TextView mBeginTime, mEndTime, mOrderCount, mOrderAmount, mShopName;

    private StatisticsContract.Presenter mPresenter;
    private RecyclerView mOrdersRv;
    private SwipeRefreshLayout refresh;
    private TextView nullLayout;
    private CustomProgressDialog mDialog;
    private StatisticsAdapter mAdapter;
    private List<OrderEntity> mOrderList;

    public static StatisticsFragment newInstance() {
        return new StatisticsFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_statistics, container, false);
        mPresenter = new StatisticsPresenter(this);
        mShopName = (TextView) root.findViewById(R.id.shop_name);
        mBeginTime = (TextView) root.findViewById(R.id.beginTimeTv);
        mEndTime = (TextView) root.findViewById(R.id.endTimeTv);
        mOrderCount = (TextView) root.findViewById(R.id.orderCountTv);
        mOrderAmount = (TextView) root.findViewById(R.id.orderAmountTv);
        mOrdersRv = (RecyclerView) root.findViewById(R.id.ordersRv);
        refresh = (SwipeRefreshLayout) root.findViewById(R.id.refresh);
        nullLayout = (TextView) root.findViewById(R.id.nullLayout);
        mDialog = new CustomProgressDialog(getContext());
        ImageView mCalendarIv = (ImageView) root.findViewById(R.id.calendar_iv);
        mCalendarIv.setOnClickListener(this);
        mOrderList = new ArrayList<>();
        mAdapter = new StatisticsAdapter(getActivity(),mOrderList);

        mAdapter.setOnRecyclerViewListener(new StatisticsAdapter.OnRecyclerViewListener() {
            @Override
            public void onItemClick(View view, int position) {
                startActivity(new Intent(getContext(),OrderDetailActivity.class));
            }
        });
        mOrdersRv.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        mOrdersRv.setLayoutManager(layoutManager);
        mOrdersRv.setAdapter(mAdapter);
        mPresenter.start();
        refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mPresenter.start();
            }
        });
        return root;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.calendar_iv:
                Date beforeYear = new Date(114, 0, 1);
                LinearLayout layout = (LinearLayout) LayoutInflater.from(getActivity()).inflate(R.layout.dialog_calendar, null);
                final CalendarPickerView dialogView = (CalendarPickerView) layout.findViewById(R.id.calendar_view);
                dialogView.init(beforeYear, new Date()).inMode(CalendarPickerView.SelectionMode.RANGE);
                dialogView.setSelection(dialogView.getCount());

                new AlertDialog.Builder(getActivity()).setTitle("请选择时区").setView(layout)
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                                SimpleDateFormat dateFormatShow = new SimpleDateFormat("yy/MM/dd");
                                List<Date> list = dialogView.getSelectedDates();
                                if (list.isEmpty()) {
                                    Toast.makeText(getActivity(), "请选择时间区间", Toast.LENGTH_SHORT).show();
                                    return;
                                }
                                mPresenter.dataDetermine(list);
//                                if (list.size() == 1) {// 如果只选择了一天，那么查询数据的起始时间和结尾时间为一个
                                // 例：2015/2/3-2015/2/3
//                                    begin_time = dateFormat.format(list.get(0));
//                                    end_time = dateFormat.format(list.get(0));
//                                    time = "time";
//                                    onTabRefresh();// 刷新数据
//                                    shop_coun_time.setText("时间区间:" + begin_time + " 到 " + end_time);
//                                    return;
//                                }
//                                if (list.size() >= 2) {// 如果只选择了多天，那么查询数据的起始时间和结尾时间为一个
                                // 例：2015/2/3-2015/3/15
//                                    begin_time = dateFormat.format(list.get(0));
//                                    end_time = dateFormat.format(list.get(list.size() - 1));// 结尾时间为最后一天
//                                    time = "time";
//                                    onTabRefresh();// 刷新数据
//                                    shop_coun_time.setText("时间区间:" + begin_time + " 到 " + end_time);
//                                    return;
//                                }
                            }
                        })
                        .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();
                            }
                        }).create().show();
                break;
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mDialog.dismiss();
    }

    public void showDialog() {
        mDialog.show();
    }

    public void hideDialog() {
        mDialog.hide();
    }

    @Override
    public void showrefresh(boolean flag) {
        refresh.setRefreshing(flag);
    }


    @Override
    public void loadShopName(String name) {
        mShopName.setText(name);
    }

    @Override
    public void loadBeginDate(String time) {
        mBeginTime.setText(time);
    }

    @Override
    public void loadEndDate(String time) {
        mEndTime.setText(time);
    }

    @Override
    public void loadOrderCount(String orderCount) {
        mOrderCount.setText(orderCount);
    }

    @Override
    public void loadOrderTotalAmount(String amount) {
        mOrderAmount.setText(amount);
    }

    @Override
    public void refreshOrderList(List<OrderEntity> orders) {
        mOrderList.clear();
        mOrderList.addAll(orders);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void showNullLayout(boolean flag) {
        if (flag) {
            nullLayout.setVisibility(View.VISIBLE);
        } else {
            nullLayout.setVisibility(View.GONE);
        }
    }

    @Override
    public void setPresenter(StatisticsContract.Presenter presenter) {
        mPresenter = checkNotNull(presenter);
    }

    public static class StatisticsAdapter extends RecyclerView.Adapter<StatisticsAdapter.ViewHolder> implements View.OnClickListener {


        public interface OnRecyclerViewListener {
            void onItemClick(View view, int position);
        }

        private OnRecyclerViewListener onRecyclerViewListener;

        public Context mContext;
        private List<OrderEntity> mOrderList;

        public StatisticsAdapter(Context context, List<OrderEntity> orderlist) {
            this.mContext = context;
            this.mOrderList = orderlist;
        }

        public void setOnRecyclerViewListener(OnRecyclerViewListener mItemListener) {
            this.onRecyclerViewListener = mItemListener;

        }


        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.fragment_order_item, parent, false);
            final StatisticsAdapter.ViewHolder holder = new StatisticsAdapter.ViewHolder(view);
            return holder;
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, final int position) {
            OrderEntity order = mOrderList.get(position);
            LogUtil.e("偏口", order.getAllPrice());
            holder.mOrderId.setText(order.getOrderId());
            holder.mOrderAmount.setText(order.getAllPrice());
            holder.mOrderDate.setText(order.getTime());
            holder.mOrderState.setText(order.getOrderStat());
//            holder.productView.setOnClickListener(this);
            if (onRecyclerViewListener != null) {
                holder.productView.setOnClickListener(new View.OnClickListener() {
                                                          @Override
                                                          public void onClick(View view) {
                                                              onRecyclerViewListener.onItemClick(view, position);
                                                          }
                                                      }
                );
            }
        }

        @Override
        public int getItemCount() {
            return mOrderList.size();
        }

        @Override
        public void onClick(View view) {

        }

        class ViewHolder extends RecyclerView.ViewHolder {

            View productView;
            TextView mOrderId, mOrderState, mOrderDate, mOrderAmount;

            public ViewHolder(View itemView) {
                super(itemView);
                productView = itemView;
                mOrderId = (TextView) itemView.findViewById(R.id.orderIdTv);
                mOrderState = (TextView) itemView.findViewById(R.id.orderStateTv);
                mOrderDate = (TextView) itemView.findViewById(R.id.orderDateTv);
                mOrderAmount = (TextView) itemView.findViewById(R.id.orderAmountTv);
            }
        }

    }
}
