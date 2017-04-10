package com.project.addresstest;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.project.addresstest.adapter.RecordsConsumptionAdapter;
import com.project.addresstest.entity.Record;

import net.lucode.hackware.magicindicator.MagicIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.indicators.LinePagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.SimplePagerTitleView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import cn.bingoogolapple.refreshlayout.BGANormalRefreshViewHolder;
import cn.bingoogolapple.refreshlayout.BGARefreshLayout;

/**
 * @author shaomiao
 * @Date 2017/4/7
 * @Time 14:27
 * 消费记录activity
 */

public class RecordsConsumptionActivity extends AppCompatActivity {

    private Context context;

    private MagicIndicator magic_indicator;
    private ViewPager mPager;
    private MyPageAdapter mAdapter;
    private List<String> mDataList = new ArrayList<String>();


    private RecyclerView data_recycleview;
    private BGARefreshLayout mrefreshLayout;
    private RecordsConsumptionAdapter mRecordsConsumptionAdapter;
    private List<Object> recordList = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_records_consumption);
        context = this;
        magic_indicator = (MagicIndicator) findViewById(R.id.magic_indicator);
        mPager = (ViewPager) findViewById(R.id.view_pager);
        initData();
    }

    private void initData() {
        mDataList.add("外卖消费");
        mDataList.add("二手车担保");
        mDataList.add("购物消费");
        mDataList.add("旅游消费");
        final CommonNavigator commonNavigator = new CommonNavigator(this);
        commonNavigator.setAdapter(new CommonNavigatorAdapter() {
            @Override
            public int getCount() {
                return mDataList == null ? 0 : mDataList.size();
            }

            @Override
            public IPagerTitleView getTitleView(Context context, final int index) {
                SimplePagerTitleView simplePagerTitleView = new SimplePagerTitleView(context);
                simplePagerTitleView.setText(mDataList.get(index));
                simplePagerTitleView.setNormalColor(Color.parseColor("#8b8b8b"));
                simplePagerTitleView.setSelectedColor(Color.BLACK);
                simplePagerTitleView.setTextSize(14);
                simplePagerTitleView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mPager.setCurrentItem(index);
                    }
                });
                return simplePagerTitleView;
            }

            @Override
            public IPagerIndicator getIndicator(Context context) {
                LinePagerIndicator indicator = new LinePagerIndicator(context);
                indicator.setMode(LinePagerIndicator.MODE_WRAP_CONTENT);
                List<String> colorList = new ArrayList<String>();
                colorList.add("#000000");//导航条颜色
                indicator.setColorList(colorList);
                return indicator;
            }
        });
        commonNavigator.setAdjustMode(true);
        magic_indicator.setNavigator(commonNavigator);

        mAdapter = new MyPageAdapter(mDataList);
        mPager.setAdapter(mAdapter);

        mPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                magic_indicator.onPageScrolled(position, positionOffset, positionOffsetPixels);
//                mrefreshLayout.beginRefreshing();
            }

            @Override
            public void onPageSelected(int position) {
                magic_indicator.onPageSelected(position);
//                mrefreshLayout.beginRefreshing();

            }

            @Override
            public void onPageScrollStateChanged(int state) {
                magic_indicator.onPageScrollStateChanged(state);

            }
        });
    }

    class MyPageAdapter extends PagerAdapter implements BGARefreshLayout.BGARefreshLayoutDelegate {

        private List<String> list;

        public MyPageAdapter(List<String> list) {
            this.list = list;
        }

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            View view = LayoutInflater.from(context).inflate(R.layout.item_records_consumption, null);
            view.setId(new Random().nextInt(1000));
            mrefreshLayout = (BGARefreshLayout) view.findViewById(R.id.m_refreshLayout);
            data_recycleview = (RecyclerView) view.findViewById(R.id.data_recycleview);
            mrefreshLayout.setDelegate(this);
            mrefreshLayout.setRefreshViewHolder(new BGANormalRefreshViewHolder(context,true));
            data_recycleview.setLayoutManager(new LinearLayoutManager(context));
            // 添加数据
            addRecordData();
            mRecordsConsumptionAdapter = new RecordsConsumptionAdapter(context, recordList);
            data_recycleview.setAdapter(mRecordsConsumptionAdapter);
            data_recycleview.setOnTouchListener(
                    new View.OnTouchListener() {
                        @Override
                        public boolean onTouch(View v, MotionEvent event) {
                            if (mrefreshLayout.isLoadingMore()) {
                                return true;
                            } else {
                                return false;
                            }
                        }
                    }
            );
            container.addView(view);
            return view;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

        @Override
        public int getItemPosition(Object object) {
            return POSITION_NONE;
        }

        @Override
        public void onBGARefreshLayoutBeginRefreshing(BGARefreshLayout refreshLayout) {

        }

        @Override
        public boolean onBGARefreshLayoutBeginLoadingMore(BGARefreshLayout refreshLayout) {
            return false;
        }
    }

    private void addRecordData() {
        for (int i = 1; i < 11 ; i ++) {
            recordList.add(new Record("200.00"+i, "2017年10月21日 10:00:00", true));
        }
    }
}
