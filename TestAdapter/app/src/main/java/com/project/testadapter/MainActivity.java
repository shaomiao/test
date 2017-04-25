package com.project.testadapter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.project.testadapter.adapter.BaseRecyclerAdapter;
import com.project.testadapter.adapter.RecyclerViewHolder;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;
import com.zhy.adapter.recyclerview.base.ItemViewDelegate;
import com.zhy.adapter.recyclerview.base.ViewHolder;
import com.zhy.adapter.recyclerview.wrapper.EmptyWrapper;
import com.zhy.adapter.recyclerview.wrapper.HeaderAndFooterWrapper;
import com.zhy.adapter.recyclerview.wrapper.LoadMoreWrapper;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private List<String> mDataList;
    private CommonAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        mDataList = new ArrayList<>();
        for(int i = 0; i <= 100; i++ ) {
            mDataList.add(String.valueOf(i));
        }

        initzhy( );

        // 设置item动画
//        recyclerView.setItemAnimator(new DefaultItemAnimator());
//        init();
//        MultiItemTypeAdapter adapter = new MultiItemTypeAdapter(this,mDataList);
//        adapter.addItemViewDelegate(new MsgComingItemDelagate());
//        adapter.addItemViewDelegate(new MsgComingItemDelagate2());
//        recyclerView.setAdapter(adapter);
//        mAdapter = new BaseRecyclerAdapter() {
//            @Override
//            public int getItemLayoutId(int viewType) {
//                return 0;
//            }
//
//            @Override
//            public void bindData(RecyclerViewHolder holder, int position, Object item) {
//
//            }
//
//            @Override
//            public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
//
//            }
//        }

        // 头尾布局
//        HeaderAndFooterWrapper mHeaderAndFooterWrapper = new HeaderAndFooterWrapper(mAdapter);
//
//        TextView t1 = new TextView(this);
//        t1.setText("header 1");
//        TextView t2 = new TextView(this);
//        t2.setText("Header 2");
//        mHeaderAndFooterWrapper.addHeaderView(t1);
//        mHeaderAndFooterWrapper.addFootView(t2);
////        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//        recyclerView.setAdapter(mHeaderAndFooterWrapper);
//        mHeaderAndFooterWrapper.notifyDataSetChanged();

//        final LoadMoreWrapper mLoadMoreWrapper = new LoadMoreWrapper(mAdapter);
//        mLoadMoreWrapper.setLoadMoreView(R.layout.default_loading);
//        mLoadMoreWrapper.setOnLoadMoreListener(new LoadMoreWrapper.OnLoadMoreListener() {
//            @Override
//            public void onLoadMoreRequested() {
//
//                Toast.makeText(MainActivity.this, "您正在加载更多", Toast.LENGTH_SHORT).show();
//            }
//        });
//        recyclerView.setAdapter(mLoadMoreWrapper);

        mDataList.clear();
        EmptyWrapper mEmptWrapper = new EmptyWrapper(mAdapter);
        mEmptWrapper.setEmptyView(R.layout.default_loading);
        recyclerView.setAdapter(mEmptWrapper);
//        mEmptWrapper.notifyDataSetChanged();
    }

    public class MsgComingItemDelagate implements ItemViewDelegate<String>{

        @Override
        public int getItemViewLayoutId() {
            return R.layout.item;
        }


        @Override
        public boolean isForViewType(String item, int position) {
            if (position%2==0) {
                return true;
            } else {
                return false;
            }
        }

        @Override
        public void convert(ViewHolder holder, String s, int position) {
            holder.setText(R.id.tv_num,s);
        }
    }

    public class MsgComingItemDelagate2 implements ItemViewDelegate<String>{

        @Override
        public int getItemViewLayoutId() {
            return R.layout.item1;
        }


        @Override
        public boolean isForViewType(String item, int position) {
            if (position%2!=0) {
                return true;
            } else {
                return false;
            }
        }

        @Override
        public void convert(ViewHolder holder, String s, final int position) {
            holder.setText(R.id.tv_title,s);
            holder.setOnClickListener(R.id.tv_title, new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(MainActivity.this, ""+position, Toast.LENGTH_SHORT).show();
                }
            });
        }
    }


    private void initzhy() {
        mAdapter = new CommonAdapter<String>(this,R.layout.item,mDataList) {
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
        };
//        recyclerView.setAdapter(mAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void init() {

        BaseRecyclerAdapter mAdapter = new BaseRecyclerAdapter<String>(this,mDataList) {

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
