package com.project.orderdetail.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.project.orderdetail.R;
import com.project.orderdetail.entity.Product;

import java.util.List;

/**
 * @author shaomiao
 * @Date 2017/4/10
 * @Time 15:23
 */

public class OrderDetalAdapter extends RecyclerView.Adapter<OrderDetalAdapter.MyViewHolder> {

    private List<Product> mData;

    public OrderDetalAdapter(List<Product> data) {
        mData = data;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_order_detail_item, parent, false);
        MyViewHolder viewHolder = new MyViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        Product product = (Product) mData.get(position);

        holder.mProductNameTv.setText(product.getProductName());
        holder.mProductNumTv.setText(product.getProductNum());
        holder.mProductTagTv.setText(product.getProductTag());
        holder.mProductPriceTv.setText(product.getProductPrice());
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView mProductNameTv, mProductTagTv, mProductNumTv, mProductPriceTv;

        ImageView mProductImageTv;

        public MyViewHolder(View itemView) {
            super(itemView);
            mProductNameTv = (TextView) itemView.findViewById(R.id.product_name_tv);
            mProductTagTv = (TextView) itemView.findViewById(R.id.tag_tv);
            mProductNumTv = (TextView) itemView.findViewById(R.id.num_tv);
            mProductPriceTv = (TextView) itemView.findViewById(R.id.price_tv);
            mProductImageTv = (ImageView) itemView.findViewById(R.id.product_image_iv);

        }
    }
}
