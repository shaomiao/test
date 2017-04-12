package com.project.circlefriends.adapter;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.project.circlefriends.R;

import java.util.List;

/**
 * @author shaomiao
 * @Date 2017/4/11
 * @Time 13:59
 */

public class DynamicAdapter2 extends RecyclerView.Adapter<DynamicAdapter2.MyViewHolder> {

    public static final int TYPE_HEADER = 0;
    public static final int TYPE_NORMAL = 1;

    private View mHeaderView;

    private List<String> mData;



    public DynamicAdapter2(List<String> data) {
        this.mData = data;
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item1, parent, false);
        MyViewHolder viewHolder = new MyViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.textView.setText(mData.get(position));
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    // 自定义布局管理器 实际上不需要我们自定义 GridLayoutManager为我们提供了动态改变每个item所占列数的方法
    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        RecyclerView.LayoutManager manager = recyclerView.getLayoutManager();
        if (manager instanceof GridLayoutManager) {
            final GridLayoutManager gridManager = (GridLayoutManager) manager;
            gridManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    return 0;
                }
            });
        }
    }

    @Override
    public int getItemViewType(int position) {
//        if ()
        return position % 2 == 0 ? RecyclerViewAdapter.ITEM_TYPE.ITEM_TYPE_THEME.ordinal() : RecyclerViewAdapter.ITEM_TYPE.ITEM_TYPE_VIDEO.ordinal();
    }


    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        public MyViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.text);
        }
    }
}
