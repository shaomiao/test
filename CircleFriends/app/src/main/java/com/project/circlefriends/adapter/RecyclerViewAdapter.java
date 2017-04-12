package com.project.circlefriends.adapter;

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
 * @Time 11:19
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public static enum ITEM_TYPE {
        ITEM_TYPE_THEME,
        ITEM_TYPE_VIDEO
    }

    private List<Object> mData;

    public RecyclerViewAdapter (List<Object> data) {
//        this.mData = data;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // 加载itemview的时候根据不同TYPE加载不同的布局
        if (viewType == ITEM_TYPE.ITEM_TYPE_THEME.ordinal()) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item1, parent, false);
            return new Item1ViewHolder(view);
        } else if (viewType == ITEM_TYPE.ITEM_TYPE_VIDEO.ordinal()) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item2, parent, false);
            return new Item2ViewHolder(view);
        } else {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item2, parent, false);
            return new Item2ViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof Item1ViewHolder) {
            ((Item1ViewHolder) holder).textView.setText("xx");
        } else if (holder instanceof Item2ViewHolder) {
            ((Item2ViewHolder) holder).textView.setText("dfdfdfds");
        } else {
            ((Item1ViewHolder) holder).textView.setText("xx");
        }

    }

    @Override
    public int getItemCount() {
        return 25;
    }

    // 设置ITEM类型 可以自由发挥 这里设置item position单数显示item1 偶数显示item2
    @Override
    public int getItemViewType(int position) {
        // Enum类提供了一个ordinal方法 返回枚举类型的序数 这里ITEM_TYPE.ITEM1.ordinal()代表0， ITEM_TYPE.ITEM2.ordinal()代表1
        return position % 2 == 0 ? ITEM_TYPE.ITEM_TYPE_THEME.ordinal() : ITEM_TYPE.ITEM_TYPE_VIDEO.ordinal();

    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    class Item1ViewHolder extends RecyclerView.ViewHolder {

        TextView textView;

        public Item1ViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.text);
        }
    }

    class Item2ViewHolder extends RecyclerView.ViewHolder {

        TextView textView;

        public Item2ViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.textView);
        }
    }
}
