package com.pkyapp.jinzaizhijianmerchant.merchant.order;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.pkyapp.jinzaizhijianmerchant.R;
import com.pkyapp.jinzaizhijianmerchant.data.entity.TabEntity;
import com.pkyapp.jinzaizhijianmerchant.util.LogUtil;
import com.shizhefei.view.indicator.IndicatorViewPager;
import com.shizhefei.view.indicator.ScrollIndicatorView;
import com.shizhefei.view.indicator.transition.OnTransitionTextListener;

import java.util.Date;
import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * @author shaomiao
 * @Date 2017/3/22
 * @Time 15:38
 */

public class OrderFragment extends Fragment implements View.OnClickListener,OrderContract.View {

    private TextView mshopName;

    private ViewPager mViewPager;

//    private TabLayout mTabLayout;
    private ImageView mImageView;

    private IndicatorViewPager mIndicatorViewPager;
    private ScrollIndicatorView mScrollIndicatorView;

    private static int topPosition = 0;

    private OrderContract.Presenter mPresenter;
    private int unSelectTextColor;
    private LayoutInflater inflate;
    // 订单页实例
    public static OrderFragment newInstance() {
        return new OrderFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_order,container,false);


        mshopName = (TextView) root.findViewById(R.id.shop_name);

//        mTabLayout = (TabLayout) root.findViewById(R.id.tabLayout);
        mImageView = (ImageView) root.findViewById(R.id.search_iv);
        mViewPager = (ViewPager) root.findViewById(R.id.viewPager);
        mScrollIndicatorView = (ScrollIndicatorView) root.findViewById(R.id.moretab_indicator);

        // 设置滚动监听
        mScrollIndicatorView.setOnTransitionListener(new OnTransitionTextListener().setColor(getResources().getColor(R.color.black),getResources().getColor(R.color.fontColorGray)));

        mViewPager.setOffscreenPageLimit(2);
        mIndicatorViewPager = new IndicatorViewPager(mScrollIndicatorView, mViewPager);
        inflate = LayoutInflater.from(getActivity());


        mScrollIndicatorView.isSplitAuto();

        mImageView.setOnClickListener(this);
        mPresenter = new OrderPresenter(this);
        mPresenter.start();
        return root;
    }

    @Override
    public void onResume() {
        super.onResume();
        // 处理跳转fragment之后回到此fragment viewpager还在跳转之前页面
//            mViewPager.setCurrentItem(topPosition);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.search_iv:
                Toast.makeText(getActivity(), "我是搜索", Toast.LENGTH_SHORT).show();

                break;
        }
    }

    private List<TabEntity> tabLists;
    @Override
    public void loadTabLayout(List<TabEntity> tabs) {
        tabLists = tabs;
        LogUtil.e("偏口鱼",tabLists.toString());
        mIndicatorViewPager.setAdapter(new MyAdapter(getActivity().getSupportFragmentManager()));

//        TabPagerAdapter adapter = new TabPagerAdapter(getChildFragmentManager(),tabs);
//        mViewPager.setAdapter(adapter);
//        mTabLayout.setupWithViewPager(mViewPager);
//
//
//        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
//            @Override
//            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//            }
//
//            @Override
//            public void onPageSelected(int position) {
//                topPosition = position;
//            }
//
//            @Override
//            public void onPageScrollStateChanged(int state) {
//            }
//        });
    }

    @Override
    public void showShowName(String shopname) {
        mshopName.setText(shopname);
    }

    @Override
    public void setPresenter(OrderContract.Presenter presenter) {
        mPresenter = checkNotNull(presenter);
    }




    private class MyAdapter extends IndicatorViewPager.IndicatorFragmentPagerAdapter {

        public MyAdapter(FragmentManager fragmentManager) {
            super(fragmentManager);
        }

        @Override
        public int getCount() {
            return tabLists.size();
        }

        @Override
        public View getViewForTab(int position, View convertView, ViewGroup container) {
            if (convertView == null) {
                convertView = inflate.inflate(R.layout.tab_top, container, false);
            }
            TextView textView = (TextView) convertView;
            textView.setText(tabLists.get(position).getName());
            int padding = dipToPix(10);
            textView.setPadding(padding, 0, padding, 0);
            return convertView;
        }

        @Override
        public android.support.v4.app.Fragment getFragmentForPage(int position) {
            Bundle bundle = new Bundle();
            OrderListFragment fragment = new OrderListFragment();
            bundle.putInt(OrderListFragment.INTENT_INT_TAB_ID, tabLists.get(position).getId());
            fragment.setArguments(bundle);
            return fragment;
        }

        @Override
        public int getItemPosition(Object object) {
            //这是ViewPager适配器的特点,有两个值 POSITION_NONE，POSITION_UNCHANGED，默认就是POSITION_UNCHANGED,
            // 表示数据没变化不用更新.notifyDataChange的时候重新调用getViewForPage
            return PagerAdapter.POSITION_NONE;
        }
    }
    /**
     * 根据dip值转化成px值
     *
     * @param dip
     * @return
     */
    private int dipToPix(float dip) {
        int size = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dip, getResources().getDisplayMetrics());
        return size;
    }






//    class TabPagerAdapter extends FragmentPagerAdapter {
//
//        private List<TabEntity> DATA;
//
//        public TabPagerAdapter(FragmentManager fm, List<TabEntity> data) {
//            super(fm);
//            DATA = data;
//        }
//        @Override
//        public Fragment getItem(int position) {
//            Bundle bundle = new Bundle();
//            OrderListFragment fragment = new OrderListFragment();
//            bundle.putInt(OrderListFragment.INTENT_INT_TAB_ID, topPosition);
//            fragment.setArguments(bundle);
//            return fragment;
//        }
//
//        @Override
//        public Object instantiateItem(ViewGroup container, int position) {
//            OrderListFragment f = (OrderListFragment) super.instantiateItem(container, position);
//            LogUtil.e("偏口鱼zz", String.valueOf(position));
//            f.setTabid(DATA.get(position).getId());
//            return super.instantiateItem(container, position);
//        }
//
//
//
//        @Override
//        public int getCount() {
//            return DATA.size();
//        }
//        //重写这个方法，将设置每个Tab的标题
//        @Override
//        public CharSequence getPageTitle(int position) {
//            return DATA.get(position).getName();
//        }
//    }
}
