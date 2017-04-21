package com.project.testadapter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.project.testadapter.adapter.BaseRecyclerAdapter;
import com.project.testadapter.adapter.RecyclerViewHolder;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;
import com.zhy.adapter.recyclerview.base.ItemViewDelegate;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private List<String> mDataList;
    private BaseRecyclerAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        mDataList = new ArrayList<>();
        for(int i = 0; i <= 100; i++ ) {
            mDataList.add(String.valueOf(i));
        }
        // 设置item动画
//        recyclerView.setItemAnimator(new DefaultItemAnimator());
//        init();
//        initzhy();
        MultiItemTypeAdapter adapter = new MultiItemTypeAdapter(this,mDataList);
        adapter.addItemViewDelegate(new MsgComingItemDelagate());
        adapter.addItemViewDelegate(new MsgComingItemDelagate());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }

    public class MsgComingItemDelagate implements ItemViewDelegate<String>{

        @Override
        public int getItemViewLayoutId() {
            return R.layout.item;
        }


        @Override
        public boolean isForViewType(String item, int position) {
            return position%3==0;
        }

        @Override
        public void convert(ViewHolder holder, String s, int position) {
            holder.setText(R.id.tv_num,s);
        }
    }


    private void initzhy() {
        recyclerView.setAdapter(new CommonAdapter<String>(this,R.layout.item,mDataList) {
            @Override
            protected void convert(ViewHolder holder, String s, final int position) {
                holder.setText(R.id.tv_num,s);
                holder.setOnClickListener(R.id.tv_num, new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(mContext, "x"+position, Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void init() {

        mAdapter = new BaseRecyclerAdapter<String>(this,mDataList) {

            @Override
            public int getItemLayoutId(int viewType) {
                return R.layout.item;
            }

            @Override
            public void bindData(RecyclerViewHolder holder, final int position, String item) {
                // 调用holder.getView(),getXXX()方法根据id得到控件实现, 进行数据绑定即可
                holder.setText(R.id.tv_num,item);
//                holder.getTextView(R.id.tv_title).setText(item);
                holder.setClickListener(R.id.tv_num, new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(mContext, position, Toast.LENGTH_SHORT).show();
                    }
                });

            }
        };
        recyclerView.setAdapter(mAdapter);
//        mAdapter.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener() {
//            @Override
//            public void onItemClick(View itemView, int position) {
//                Toast.makeText(MainActivity.this, "click", Toast.LENGTH_SHORT).show();
//            }
//        });
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

}
