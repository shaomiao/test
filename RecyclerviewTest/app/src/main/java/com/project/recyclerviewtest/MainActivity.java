package com.project.recyclerviewtest;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.Toast;
import android.view.LayoutInflater;
import android.view.ViewGroup.LayoutParams;

import com.project.recyclerviewtest.adapter.BusinessAdapter;
import com.project.recyclerviewtest.entity.BusinessEntity;
import com.project.recyclerviewtest.entity.ProductEntity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private RecyclerView recyclerView;

    private BusinessAdapter businessAdapter;

    private List<BusinessEntity> mDatas;


    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initData();
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        businessAdapter = new BusinessAdapter(this, mDatas);
//        businessAdapter.setOnRecyclerViewListener(this);

        recyclerView.setAdapter(businessAdapter);
        imageView = (ImageView) findViewById(R.id.image);
        imageView.setOnClickListener(this);
        businessAdapter.setOnRecyclerViewListener(new BusinessAdapter.OnRecyclerViewListener() {
            @Override
            public void onBusinessImgClick(View view, int position) {
                Toast.makeText(MainActivity.this, "dddd", Toast.LENGTH_SHORT).show();
//                for (int i=0;i<mDatas.get(position).getProducts().size();i++) {
                mDatas.get(position).getProducts().get(position).setProductPrice("121");
//                }
                businessAdapter.notifyDataSetChanged();

            }

            @Override
            public void onBusinessNameClick(View view, int position) {

            }
        });
    }

    // 数据
    protected void initData() {
        mDatas = new ArrayList<>();
        List<ProductEntity> products = new ArrayList<>();
        for (int i = 1; i < 10; i++) {
            ProductEntity productEntity = new ProductEntity();
            productEntity.setProductName("我是一个商品哈哈哈哈或或或" + i);
            productEntity.setProductImage("https://ss0.baidu.com/7Po3dSag_xI4khGko9WTAnF6hhy/image/h%3D200/sign=71cd4229be014a909e3e41bd99763971/472309f7905298221dd4c458d0ca7bcb0b46d442.jpg");
            productEntity.setProductPrice("99." + i);
            products.add(productEntity);
        }
//        for (int i = 1; i < 30; i++) {
//            BusinessEntity entity = new BusinessEntity();
//            entity.setBusinessName("ddddfsdfdsfsdfsdfzzzz" + i);
//            entity.setBusinessImage("https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=1564533037,3918553373&fm=116&gp=0.jpg");
//            entity.setProducts(products);
//            mDatas.add(entity);
//        }
        List<ProductEntity> list = new ArrayList<>();
        list.add(new ProductEntity("https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=1532593936,3492168681&fm=23&gp=0.jpg","dfdff","23.00"));
        list.add(new ProductEntity("https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=1532593936,3492168681&fm=23&gp=0.jpg","ffff","23.00"));

        BusinessEntity entity = new BusinessEntity();
        entity.setBusinessName("ddddfsdfdsfsdfsdfzzzz");
        entity.setBusinessImage("https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=1564533037,3918553373&fm=116&gp=0.jpg");
        entity.setProducts(products);
        mDatas.add(entity);
//
        BusinessEntity entity2 = new BusinessEntity();
        entity2.setBusinessName("ddddfsdfdsfsdfsdfzzzz");
        entity2.setBusinessImage("https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=1564533037,3918553373&fm=116&gp=0.jpg");
        entity2.setProducts(list);
        mDatas.add(entity2);

    }


    // 点击显示popwindow
    private void showFiltratePopWindow(View view) {

        // 一个自定义的布局，作为显示的内容
        View contentView = LayoutInflater.from(this).inflate(
                R.layout.pop_window, null);
//
//        final PopupWindow popupWindow = new PopupWindow(contentView,
//                LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, true);

        final PopupWindow popupWindow = new PopupWindow(contentView, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);

        // 设置按钮的点击事件
        ImageView button = (ImageView) contentView.findViewById(R.id.closeIv);
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });

        popupWindow.setOutsideTouchable(true);
        popupWindow.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#55000000")));


        popupWindow.setTouchable(true);

        popupWindow.setTouchInterceptor(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return false;
            }
        });

        popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.image:
                showFiltratePopWindow(view);
                break;
        }
    }
}
