package com.project.addresstest.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.project.addresstest.R;
import com.project.addresstest.entity.Record;

import java.util.List;

/**
 * @author shaomiao
 * @Date 2017/4/7
 * @Time 15:39
 */

public class RecordsConsumptionAdapter extends RecyclerView.Adapter<RecordsConsumptionAdapter.MyViewHolder> {

    private Context mContext;
    private List<Object> mData;

    public RecordsConsumptionAdapter(Context context, List<Object> data) {
        this.mContext = context;
        this.mData = data;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_record,parent,false);
        MyViewHolder viewHolder = new MyViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Record record = (Record) mData.get(position);
        holder.dateTv.setText(record.getDate());
        holder.priceTv.setText(record.getPrice());
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        TextView priceTv, dateTv;

        public MyViewHolder(View itemView) {
            super(itemView);
            priceTv = (TextView) itemView.findViewById(R.id.price_tv);
            dateTv = (TextView) itemView.findViewById(R.id.date_tv);
        }
    }
}
