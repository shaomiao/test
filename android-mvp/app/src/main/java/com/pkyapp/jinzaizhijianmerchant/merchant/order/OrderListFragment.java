package com.pkyapp.jinzaizhijianmerchant.merchant.order;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.pkyapp.jinzaizhijianmerchant.R;
import com.pkyapp.jinzaizhijianmerchant.app.WXApplication;
import com.pkyapp.jinzaizhijianmerchant.data.entity.OrderEntity;
import com.pkyapp.jinzaizhijianmerchant.data.entity.TabEntity;
import com.pkyapp.jinzaizhijianmerchant.merchant.order.detail.OrderDetailActivity;
import com.pkyapp.jinzaizhijianmerchant.ui.CustomProgressDialog;
import com.pkyapp.jinzaizhijianmerchant.util.LogUtil;
import com.shizhefei.fragment.LazyFragment;

import java.util.ArrayList;
import java.util.List;


import cn.bingoogolapple.refreshlayout.BGANormalRefreshViewHolder;
import cn.bingoogolapple.refreshlayout.BGARefreshLayout;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * @author shaomiao
 * @Date 2017/3/23
 * @Time 15:38
 */

public class OrderListFragment extends LazyFragment implements OrderContract.View.OrderView,BGARefreshLayout.BGARefreshLayoutDelegate {

    private BGARefreshLayout mRefreshLayout;

    private OrderContract.Presenter.OrderListPresenter mPresenter;

    private CustomProgressDialog mDialog;

    private Context mContext;

//    private SwipeRefreshLayout mRefresh;

    private RecyclerView mRecyclerview;

    private OrderListAdapter mAdapter;

    private List<OrderEntity> orders;

    private View mNullLayout;

    private int tabid;


    public static final String INTENT_INT_TAB_ID = "intent_int_tab_id";

    public OrderListFragment() {}

    @Override
    protected void onCreateViewLazy(Bundle savedInstanceState) {
        setContentView(R.layout.fragment_order_list);
        tabid = getArguments().getInt(INTENT_INT_TAB_ID);
        mContext = getContext();
        mDialog = new CustomProgressDialog(getActivity());
        mPresenter = new OrderPresenterListPresenter(this);
//        mRefresh = (SwipeRefreshLayout) findViewById(R.id.swiperefresh_layout);
        mRecyclerview = (RecyclerView) findViewById(R.id.orderListRv);
        mNullLayout = findViewById(R.id.nullLayout);
        orders = new ArrayList<>();
        mAdapter = new OrderListAdapter(getActivity(),orders);


        mRecyclerview.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        mRecyclerview.setLayoutManager(layoutManager);
        mRecyclerview.setAdapter(mAdapter);

        mRefreshLayout = (BGARefreshLayout) findViewById(R.id.rl_modulename_refresh);
        // 为BGARefreshLayout设置代理
        mRefreshLayout.setDelegate(this);
        // 设置下拉刷新和上拉加载更多的风格     参数1：应用程序上下文，参数2：是否具有上拉加载更多功能
//        BGAMoocStyleRefreshViewHolder moocStyleRefreshViewHolder = new BGAMoocStyleRefreshViewHolder(getContext(), true);
//        moocStyleRefreshViewHolder.setUltimateColor(R.color.black);
//        moocStyleRefreshViewHolder.setOriginalImage(R.mipmap.custom_mooc_icon);
//
//        BGAStickinessRefreshViewHolder stickinessRefreshViewHolder = new BGAStickinessRefreshViewHolder(getContext(), true);
//        stickinessRefreshViewHolder.setStickinessColor(R.color.colorPrimary);
//        stickinessRefreshViewHolder.setRotateImage(R.mipmap.ic_launcher);
//        stickinessRefreshViewHolder.setLoadingMoreText("xxx");
//        stickinessRefreshViewHolder.setLoadMoreBackgroundColorRes(R.color.black);
        mRefreshLayout.setRefreshViewHolder(new BGANormalRefreshViewHolder(getContext(), true));

        mPresenter.loadOrderListData(tabid);

//        mRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
//            @Override
//            public void onRefresh() {
//                mPresenter.loadOrderListData(tabid);
//            }
//        });

        mAdapter.setOnRecyclerViewListener(new OrderListAdapter.OnRecyclerViewListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(mContext,OrderDetailActivity.class);
                intent.putExtra(OrderDetailActivity.ORDER_ID,orders.get(position).getId());
                intent.putExtra(OrderDetailActivity.STATE_ID,orders.get(position).getOrderStat());
                startActivity(intent);
            }
        });

        Button button = (Button) findViewById(R.id.button);
//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
////                mRefreshLayout.beginLoadingMore();
//            }
//        });

//        mRecyclerview.setOnTouchListener(
//                new View.OnTouchListener() {
//                    @Override
//                    public boolean onTouch(View v, MotionEvent event) {
//                        if (mRefreshLayout.isLoadingMore()) {
//                            return true;
//                        } else {
//                            return false;
//                        }
//                    }
//                }
//        );

//        mNullLayout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                mPresenter.refreshOrderList(tabid);
//            }
//        });
    }


    @Override
    protected void onResumeLazy() {
        super.onResumeLazy();
//        mRecyclerview.setItemViewCacheSize(2);
    }
    @Override
    public void onDestroyViewLazy() {
        super.onDestroyViewLazy();
        if (mDialog != null) {
            mDialog.dismiss();
        }
    }

    @Override
    public void showOrderListLoading() {
        mDialog.show();
    }

    @Override
    public void hideOrderListLoading() {
        mDialog.hide();
    }

    @Override
    public void showOrderListView(List<OrderEntity> orderlist) {
        orders.clear();
        orders.addAll(orderlist);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void showloadMoreOrdeListView(List<OrderEntity> orderlist) {
        orders.addAll(orderlist);
        mAdapter.notifyItemChanged(orderlist.size()-1);
    }

    @Override
    public void refreshOrderList() {

    }

    @Override
    public void showOrderNullView(boolean flag) {
//        if (flag) {
//            mNullLayout.setVisibility(View.VISIBLE);
//        } else {
//            mNullLayout.setVisibility(View.GONE);
//        }
    }

    @Override
    public void showrefresh(boolean flag) {
//        mRefresh.setRefreshing(flag);
        if (flag)
            mRefreshLayout.beginRefreshing();
        else
            mRefreshLayout.endRefreshing();
    }

    @Override
    public void showMore(boolean flag) {
        if (flag) mRefreshLayout.beginLoadingMore(); else mRefreshLayout.endLoadingMore();
    }

    @Override
    public void showNotMore(boolean flag) {
        mRefreshLayout.setIsShowLoadingMoreView(true);
    }

    @Override
    public void setPresenter(OrderContract.Presenter.OrderListPresenter presenter) {
        mPresenter = checkNotNull(presenter);
    }

    @Override
    public void onBGARefreshLayoutBeginRefreshing(BGARefreshLayout refreshLayout) {
        mPresenter.refreshOrderList(tabid);
    }

    @Override
    public boolean onBGARefreshLayoutBeginLoadingMore(BGARefreshLayout refreshLayout) {
        mPresenter.loadMoreOrderList(tabid);
        return true;
    }


    public static class OrderListAdapter extends RecyclerView.Adapter<OrderListAdapter.ViewHolder> implements View.OnClickListener{

        public interface OnRecyclerViewListener {
            void onItemClick(View view, int position);
        }

        private OnRecyclerViewListener onRecyclerViewListener;

        public Context mContext;
        private List<OrderEntity> mOrderList;

        public OrderListAdapter(Context context, List<OrderEntity> orderlist) {
            this.mContext = context;
            this.mOrderList = orderlist;
        }
        public void setOnRecyclerViewListener(OnRecyclerViewListener mItemListener) {
            this.onRecyclerViewListener = mItemListener;

        }


        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(mContext)
                    .inflate(R.layout.fragment_order_item,parent,false);
            final ViewHolder holder = new ViewHolder(view);

            return holder;
        }

        @Override
        public void onBindViewHolder(final ViewHolder holder, final int position) {
            OrderEntity order = mOrderList.get(position);
            holder.mOrderId.setText(order.getOrderId());
            holder.mOrderAmount.setText(order.getAllPrice());
            holder.mOrderDate.setText(order.getTime());
            holder.mOrderState.setText(getStateName(order.getOrderStat()));
            if (onRecyclerViewListener != null) {
                holder.productView.setOnClickListener(new View.OnClickListener() {
                                                       @Override
                                                       public void onClick(View view) {
                                                           onRecyclerViewListener.onItemClick(view,position);
                                                       }
                                                   }
                );
            }
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

        @Override
        public int getItemCount() {
            return mOrderList.size();
        }

        @Override
        public void onClick(View view) {

        }

        class ViewHolder extends RecyclerView.ViewHolder{

            View productView;
            TextView mOrderId, mOrderState, mOrderDate,mOrderAmount;

            public ViewHolder(View itemView) {
                super(itemView);
                productView = itemView;
                mOrderId= (TextView) itemView.findViewById(R.id.orderIdTv);
                mOrderState = (TextView) itemView.findViewById(R.id.orderStateTv);
                mOrderDate = (TextView) itemView.findViewById(R.id.orderDateTv);
                mOrderAmount = (TextView) itemView.findViewById(R.id.orderAmountTv);
            }
        }

    }
}
