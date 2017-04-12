package com.project.circlefriends.dynamic;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.project.circlefriends.R;
import com.project.circlefriends.dynamic.adapter.DynamicAdapter;
import com.project.circlefriends.dynamic.entity.Dynamic;

import java.util.ArrayList;
import java.util.List;

/**
 * @author shaomiao
 * @Date 2017/4/11
 * @Time 10:41
 */

public class DynamicActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;

    private List<Dynamic> mData;

//    List<String> mData;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_dynamic);
//        mData = new ArrayList<>();
//        mData.add("fdsfsdfsdf");
//        mData.add("fffffff");
//        mData.add("jkkkkkkkkk");
//        mData.add("jkkkkkkkkk");
//        mData.add("lllllllllll");
//        mData.add("lllllllllll");
//        mData.add("lllllllllll");
//        mData.add("lllllllllll");
//        mData.add("lllllllllll");
//        mData.add("lllllllllll");
//        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
//        mRecyclerView.setAdapter(new RecyclerViewAdapter(null));
//        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        initData();
        initView();
    }

    private void initView() {
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        mRecyclerView.setAdapter(new DynamicAdapter(mData));
    }

    private void initData() {
        mData = new ArrayList<>();
        Dynamic dynamic = new Dynamic();
        dynamic.setName("白卡斯");
        dynamic.setDate("2017/03/02 10:00");
        dynamic.setTitle("天气真好 今天就适合出去旅游哇哈哈哈或或或或");
        dynamic.setCommentNum("2000");
        dynamic.setFabulousNum("4999");
        dynamic.setType(Dynamic.ITEM_TYPE.ITEM_SINGLE_PICTURE.ordinal());
        mData.add(dynamic);
        mData.add(new Dynamic("shaomiao","touxiang","2017-45-33","33","45","上班了 朋友们我回来了",
                "链接","T恤男。。。。。","链接图片","图片",null,Dynamic.ITEM_TYPE.ITEM_LINK.ordinal()));
        mData.add(new Dynamic("shaomiao","touxiang","2017-45-33","33","45","上班了 朋友们我回来了",
                "链接","T恤男。。。。。","链接图片","图片",null,Dynamic.ITEM_TYPE.ITEM_LINK.ordinal()));
        mData.add(new Dynamic("shaomiao","touxiang","2017-45-33","33","45","上班了 朋友们我回来了",
                "链接","T恤男。。。。。","链接图片","图片",null,Dynamic.ITEM_TYPE.ITEM_LINK.ordinal()));
        mData.add(new Dynamic("shaomiao","touxiang","2017-45-33","33","45","上班了 朋友们我回来了",
                "链接","T恤男。。。。。","链接图片","图片",null,Dynamic.ITEM_TYPE.ITEM_STRING.ordinal()));
        mData.add(new Dynamic("shaomiao","touxiang","2017-45-33","33","45","上班了 朋友们我回来了",
                "链接","T恤男。。。。。","链接图片","图片",null,Dynamic.ITEM_TYPE.ITEM_LINK.ordinal()));
        mData.add(new Dynamic("shaomiao","touxiang","2017-45-33","33","45","上班了 朋友们我回来了",
                "链接","T恤男。。。。。","链接图片","图片",null,Dynamic.ITEM_TYPE.ITEM_LINK.ordinal())); mData.add(new Dynamic("shaomiao","touxiang","2017-45-33","33","45","上班了 朋友们我回来了",
                "链接","T恤男。。。。。","链接图片","图片",null,Dynamic.ITEM_TYPE.ITEM_SINGLE_PICTURE.ordinal()));
        mData.add(new Dynamic("shaomiao","touxiang","2017-45-33","33","45","上班了 朋友们我回来了",
                "链接","T恤男。。。。。","链接图片","图片",null,Dynamic.ITEM_TYPE.ITEM_LINK.ordinal()));
        mData.add(new Dynamic("shaomiao","touxiang","2017-45-33","33","45","上班了 朋友们我回来了",
                "链接","T恤男。。。。。","链接图片","图片",null,Dynamic.ITEM_TYPE.ITEM_LINK.ordinal()));
        mData.add(new Dynamic("shaomiao","touxiang","2017-45-33","33","45","上班了 朋友们我回来了",
                "链接","T恤男。。。。。","链接图片","图片",null,Dynamic.ITEM_TYPE.ITEM_LINK.ordinal()));
        mData.add(new Dynamic("shaomiao","touxiang","2017-45-33","33","45","上班了 朋友们我回来了",
                "链接","T恤男。。。。。","链接图片","图片",null,Dynamic.ITEM_TYPE.ITEM_LINK.ordinal()));
        mData.add(new Dynamic("shaomiao","touxiang","2017-45-33","33","45","上班了 朋友们我回来了",
                "链接","T恤男。。。。。","链接图片","图片",null,Dynamic.ITEM_TYPE.ITEM_LINK.ordinal()));
        mData.add(new Dynamic("shaomiao","touxiang","2017-45-33","33","45","上班了 朋友们我回来了",
                "链接","T恤男。。。。。","链接图片","图片",null,Dynamic.ITEM_TYPE.ITEM_LINK.ordinal()));
        mData.add(new Dynamic("shaomiao","touxiang","2017-45-33","33","45","上班了 朋友们我回来了",
                "链接","T恤男。。。。。","链接图片","图片",null,Dynamic.ITEM_TYPE.ITEM_LINK.ordinal()));
        mData.add(new Dynamic("shaomiao","touxiang","2017-45-33","33","45","上班了 朋友们我回来了",
                "链接","T恤男。。。。。","链接图片","图片",null,Dynamic.ITEM_TYPE.ITEM_LINK.ordinal()));
        mData.add(new Dynamic("shaomiao","touxiang","2017-45-33","33","45","上班了 朋友们我回来了",
                "链接","T恤男。。。。。","链接图片","图片",null,Dynamic.ITEM_TYPE.ITEM_STRING.ordinal()));


    }
}
