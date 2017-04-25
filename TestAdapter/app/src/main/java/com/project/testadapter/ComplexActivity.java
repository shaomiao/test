package com.project.testadapter;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

/**
 * @author shaomiao
 * @Date 2017/4/25
 * @Time 15:05
 * <p>
 * recyclerView 复杂布局
 */

public class ComplexActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);


        mRecyclerView.setLayoutManager(new GridLayoutManager(mRecyclerView.getContext(), 6, GridLayoutManager.VERTICAL, false));

    }


    class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

        private static final int TYPE_SLIDER = 1;
        private static final int TYPE_TYPE2_HEAD = 2;
        private static final int TYPE_TYPE3_HEAD = 3;
        private static final int TYPE_TYPE2 = 4;
        private static final int TYPE_TYPE3 = 5;

        public MyAdapter() {

        }

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return null;
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, int position) {

        }

        @Override
        public int getItemCount() {
            return 0;
        }

        @Override
        public void onAttachedToRecyclerView(RecyclerView recyclerView) {
            super.onAttachedToRecyclerView(recyclerView);

            RecyclerView.LayoutManager manager = recyclerView.getLayoutManager();
            if (manager instanceof GridLayoutManager) {
                GridLayoutManager gridLayoutManager = (GridLayoutManager) manager;
                gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                    @Override
                    public int getSpanSize(int position) {
                        int type = getItemViewType(position);
                        switch (type) {
                            case TYPE_SLIDER:
                            case TYPE_TYPE2_HEAD:
                            case TYPE_TYPE3_HEAD:
                                return 6;
                            case TYPE_TYPE2:
                                return 3;
                            case TYPE_TYPE3:
                                return 2;
                            default:
                                return 3;
                        }
                    }
                });
            }

        }

        class MyViewHolder extends RecyclerView.ViewHolder {

            public MyViewHolder(View itemView) {
                super(itemView);
            }
        }
    }
}
