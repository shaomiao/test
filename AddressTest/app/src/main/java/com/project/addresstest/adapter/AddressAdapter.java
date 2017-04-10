package com.project.addresstest.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.project.addresstest.R;
import com.project.addresstest.entity.Address;

import java.util.List;
import java.util.zip.Inflater;

/**
 * @author shaomiao
 * @Date 2017/4/7
 * @Time 10:58
 */

public class AddressAdapter extends RecyclerView.Adapter<AddressAdapter.MyViewHolder> {

    private Context mContext;

    private List<Object> mData;

    public AddressAdapter(Context context, List<Object> data) {
        this.mContext = context;
        this.mData = data;
    }

    @Override
    public AddressAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_address, parent, false);
        MyViewHolder viewHolder = new MyViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(AddressAdapter.MyViewHolder holder, int position) {
        Address entity = (Address) mData.get(position);
        holder.nameTv.setText(entity.getName());
        holder.phoneTv.setText(entity.getPhone());
        holder.addressTv.setText(entity.getAddress() + entity.getDetailedAddress());
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        View itemView;
        TextView nameTv, phoneTv, addressTv;

        public MyViewHolder(View itemView) {
            super(itemView);
            this.itemView = itemView;
            nameTv = (TextView) itemView.findViewById(R.id.name_tv);
            phoneTv = (TextView) itemView.findViewById(R.id.phone_tv);
            addressTv = (TextView) itemView.findViewById(R.id.address_tv);
        }
    }
}
