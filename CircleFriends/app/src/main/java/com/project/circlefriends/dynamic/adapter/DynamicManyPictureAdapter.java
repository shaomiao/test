package com.project.circlefriends.dynamic.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.project.circlefriends.R;

import java.util.ArrayList;
import java.util.List;

/**
 * @author shaomiao
 * @Date 2017/4/12
 * @Time 10:23
 */

public class DynamicManyPictureAdapter extends RecyclerView.Adapter<DynamicManyPictureAdapter.MyViewHolder> {

    private DisplayMetrics dm;
    private List mData;
    private Context mContext;

    public DynamicManyPictureAdapter(Context context, List data) {
        dm = new DisplayMetrics();
        ((Activity) context).getWindowManager().getDefaultDisplay().getMetrics(dm);
        this.mData = data != null ? data : new ArrayList();
        this.mContext = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_dynamic_manypicture_list_item, parent, false);
        MyViewHolder viewHolder = new MyViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Glide
                .with(mContext)
                .load(mData.get(position))
                .centerCrop()
                .placeholder(R.mipmap.ic_launcher)
                .crossFade()
                .into(holder.mImageView);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView mImageView;

        public MyViewHolder(View itemView) {
            super(itemView);
            mImageView = (ImageView) itemView.findViewById(R.id.image_iv);
        }
    }

    int dip2px(float dpValue) {
        final float scale = mContext.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }
}
