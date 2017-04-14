package testviewpager;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import net.lucode.hackware.magicindicatordemo.R;
import net.lucode.hackware.magicindicatordemo.test.ShoppingClassifyGridEntity;

import java.util.List;

/**
 * @author shaomiao
 * @Date 2017/4/14
 * @Time 10:55
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {

    private List<ShoppingClassifyGridEntity> mData;

    private Context mContext;

    public RecyclerViewAdapter(List<ShoppingClassifyGridEntity> data) {
        this.mData = data;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view_item,parent,false);
        MyViewHolder viewHolder = new MyViewHolder(view);
        mContext = parent.getContext();
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        ShoppingClassifyGridEntity entity = mData.get(position);

        holder.goods_name.setText(entity.getGoods_name());
//        ImageLoader.getInstance().displayImage(entity.getGoods_pic(), holder.goods_img);
        Glide.with(mContext).load("http://pic4.nipic.com/20091121/3764872_215617048242_2.jpg").into(holder.goods_img);
        holder.goods_sell.setText("销量：" + entity.getGoods_sell());
        holder.goods_price.setText(entity.getGoods_price());

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView goods_name;
        ImageView goods_img;
        TextView goods_sell;
        TextView goods_price;
        LinearLayout all_lin;

        public MyViewHolder(View view) {
            super(view);
            goods_name = (TextView) view.findViewById(R.id.goods_name);
            goods_img = (ImageView) view.findViewById(R.id.goods_img);
            goods_sell = (TextView) view.findViewById(R.id.goods_sell);
            goods_price = (TextView) view.findViewById(R.id.goods_price);
            all_lin = (LinearLayout) view.findViewById(R.id.all_lin);
        }
    }
}
