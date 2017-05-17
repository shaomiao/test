package com.project.testadapter;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.project.testadapter.entity.Cheese;
import com.project.testadapter.view.SlideShowView;

import java.util.List;

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
        setContentView(R.layout.activity_complex);
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);


        mRecyclerView.setLayoutManager(new GridLayoutManager(mRecyclerView.getContext(), 6, GridLayoutManager.VERTICAL, false));

    }


    class MyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

        private Context context;
        private List<Cheese> results;

        private static final int TYPE_SLIDER = 0xff01;
        private static final int TYPE_TYPE2_HEAD = 0xff02;
        private static final int TYPE_TYPE3_HEAD = 0xff03;
        private static final int TYPE_TYPE2 = 0xff04;
        private static final int TYPE_TYPE3 = 0xff05;
        private static final int TYPE_TYPE4 = 0xff06;

        public MyAdapter(Context context) {
            this.context = context;
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            switch (viewType) {
                case TYPE_SLIDER:
                    return new HolderSlider(LayoutInflater.from(parent.getContext()).inflate(R.layout.onerecycle_item_slider,parent,false));
                case TYPE_TYPE2_HEAD:
                case TYPE_TYPE3_HEAD:
//                    return new HolderType2(LayoutInflater.from(parent.getContext()).inflate(R.layout.xx,parent,false));
                case TYPE_TYPE2:
                case TYPE_TYPE3:
                case TYPE_TYPE4:
//                    return new HolderType2(LayoutInflater.from(parent.getContext()).inflate(R.layout.xx,parent,false));
                default:
                    Log.d("error", "onCreateViewHolder: ");
                return null;
            }
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            if (holder instanceof HolderSlider) {
//                bindTypeSlider();
            } else if (holder instanceof HolderType2Head) {

            } else if (holder instanceof HolderType2) {

            }
        }

        @Override
        public int getItemCount() {
            return 40;
        }

        @Override
        public int getItemViewType(int position) {
            if (position == 0) {
                return TYPE_SLIDER;
            } else if (position == 1) {
                return TYPE_TYPE2_HEAD;
            } else if (2 <= position && position <= 7) {
                return TYPE_TYPE2;
            } else if (position == 8) {
                return TYPE_TYPE3_HEAD;
            } else if (9 <= position && position <= 14) {
                return TYPE_TYPE3;
            } else if (15 <= position) {
                return TYPE_TYPE4;
            } else {
                return TYPE_TYPE2;
            }
        }

        @Override
        public void onAttachedToRecyclerView(RecyclerView recyclerView) {
            super.onAttachedToRecyclerView(recyclerView);

            RecyclerView.LayoutManager manager = recyclerView.getLayoutManager();
            if (manager instanceof GridLayoutManager) {
                final GridLayoutManager gridLayoutManager = (GridLayoutManager) manager;
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
                            case TYPE_TYPE4:
                                // 把当前item的宽度设为full（填满），也
                                return gridLayoutManager.getSpanCount();
                            default:
                                return 3;
                        }
                    }
                });
            }

        }

        private void bindTypeSlider(HolderSlider holder, int position) {
            String img = "";
//            String [] imgs = new String[]
        }

        public class HolderSlider extends RecyclerView.ViewHolder {

            public SlideShowView slideShowView;

            public HolderSlider(View itemView) {
                super(itemView);
                slideShowView = (SlideShowView) itemView.findViewById(R.id.slideShowView);
            }
        }

        public class HolderType2Head extends RecyclerView.ViewHolder {

            public HolderType2Head(View itemView) {
                super(itemView);
            }
        }

        public class HolderType2 extends RecyclerView.ViewHolder {

            public ImageView item_img_type2;

            public HolderType2(View itemView) {
                super(itemView);
//                item_img_type2 = (ImageView) itemView.findViewById(R.id.item_img_type2);
            }
        }
    }
}
