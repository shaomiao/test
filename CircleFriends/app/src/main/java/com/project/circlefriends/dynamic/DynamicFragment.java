package com.project.circlefriends.dynamic;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.project.circlefriends.R;
import com.project.circlefriends.dynamic.adapter.DynamicAdapter;
import com.project.circlefriends.dynamic.entity.Dynamic;

import java.util.ArrayList;
import java.util.List;

/**
 * @author shaomiao
 * @Date 2017/4/12
 * @Time 9:47
 */

public class DynamicFragment extends Fragment {

    private View mView;
    private Context mContext;

    private RecyclerView mRecyclerView;
    private List<Dynamic> mData;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_dynamic, container, false);
        mContext = getActivity();
        initData();
        initView();
        return mView;
    }

    private void initView() {
        mRecyclerView = (RecyclerView) mView.findViewById(R.id.recyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mRecyclerView.addItemDecoration(new DividerItemDecoration(mContext, DividerItemDecoration.VERTICAL));
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
//        mData.add(dynamic);

        List<String> images = new ArrayList<>();
        images.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1491978343800&di=99907a10dcbe5d24a30bc0b6f942c9b9&imgtype=0&src=http%3A%2F%2Fpic19.nipic.com%2F20120313%2F9499529_100139187133_2.jpg");
//        images.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1491978343800&di=6380566184ea034e981b5ccc8a804b71&imgtype=0&src=http%3A%2F%2Fpic21.nipic.com%2F20120511%2F9859719_234724489111_2.jpg");
        images.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1491978343800&di=836c0657695e4a2e15e44ab058be8c96&imgtype=0&src=http%3A%2F%2Fpic30.nipic.com%2F20130615%2F12251844_141425397124_2.jpg");
        images.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1491978343799&di=b89a4934cabf2274872078f9ee7d9237&imgtype=0&src=http%3A%2F%2Fpic.58pic.com%2F58pic%2F15%2F23%2F09%2F74T58PICZjg_1024.jpg");

        mData.add(new Dynamic("shaojkdjfmiao","http://www.qqjay.com/uploads/allimg/160306/1_0PP4Q19.jpg","2017-45-33","33","45","我胡汉三回来了",
                "http://www.baidu.com","T恤男。。。。。","http://www.ucicq.com/uploads/allimg/170406/15145W360_0.jpg","https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1491976243324&di=2c620e234fdf657a9133e7b1af5b84b0&imgtype=0&src=http%3A%2F%2Fs4.album.sina.com.cn%2Fpic%2F4a795a9102001csr",images,Dynamic.ITEM_TYPE.ITEM_MANY_PICTURE.ordinal()));
        mData.add(new Dynamic("shaomiao","http://img.woyaogexing.com/touxiang/katong/20140110/864ea8353fe3edd3.jpg%21200X200.jpg","2017-45-33","33","45","上班了 朋友们我回来了",
                "http://www.baidu.com","T恤男。。。。。","http://www.ucicq.com/uploads/allimg/170406/15145W360_0.jpg","https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1491976243324&di=2c620e234fdf657a9133e7b1af5b84b0&imgtype=0&src=http%3A%2F%2Fs4.album.sina.com.cn%2Fpic%2F4a795a9102001csr",null,Dynamic.ITEM_TYPE.ITEM_LINK.ordinal()));
        mData.add(new Dynamic("jkjk","http://img.woyaogexing.com/touxiang/katong/20140110/864ea8353fe3edd3.jpg%21200X200.jpg","2017-45-33","33","45","上班了 朋友们我回来了",
                "http://www.baidu.com","T恤男。。。。。","http://www.ucicq.com/uploads/allimg/170406/15145W360_0.jpg","https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1491976243324&di=2c620e234fdf657a9133e7b1af5b84b0&imgtype=0&src=http%3A%2F%2Fs4.album.sina.com.cn%2Fpic%2F4a795a9102001csr",null,Dynamic.ITEM_TYPE.ITEM_LINK.ordinal()));
        mData.add(new Dynamic("shaomiao","http://img.woyaogexing.com/touxiang/katong/20140110/864ea8353fe3edd3.jpg%21200X200.jpg","2017-45-33","33","45","上班了 朋友们我回来了",
                "http://www.baidu.com","T恤男。。。。。","http://www.ucicq.com/uploads/allimg/170406/15145W360_0.jpg","https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1491976243324&di=2c620e234fdf657a9133e7b1af5b84b0&imgtype=0&src=http%3A%2F%2Fs4.album.sina.com.cn%2Fpic%2F4a795a9102001csr",null,Dynamic.ITEM_TYPE.ITEM_STRING.ordinal()));
        mData.add(new Dynamic("kjkjkj","http://img.woyaogexing.com/touxiang/katong/20140110/864ea8353fe3edd3.jpg%21200X200.jpg","2017-45-33","33","45","上班了 朋友们我回来了",
                "http://www.baidu.com","dfdsfdsfdddddd。。。。。","http://www.ucicq.com/uploads/allimg/170406/15145W360_0.jpg","https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1491976243324&di=2c620e234fdf657a9133e7b1af5b84b0&imgtype=0&src=http%3A%2F%2Fs4.album.sina.com.cn%2Fpic%2F4a795a9102001csr",null,Dynamic.ITEM_TYPE.ITEM_LINK.ordinal()));
        mData.add(new Dynamic("shaomiao","http://img.woyaogexing.com/touxiang/katong/20140110/864ea8353fe3edd3.jpg%21200X200.jpg","2017-45-33","33","45","上班了 朋友们我回来了",
                "http://www.baidu.com","dfdsfsdf。。。。。","http://www.ucicq.com/uploads/allimg/170406/15145W360_0.jpg","https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1491976243324&di=2c620e234fdf657a9133e7b1af5b84b0&imgtype=0&src=http%3A%2F%2Fs4.album.sina.com.cn%2Fpic%2F4a795a9102001csr",null,Dynamic.ITEM_TYPE.ITEM_LINK.ordinal())); mData.add(new Dynamic("shaomiao","http://img.woyaogexing.com/touxiang/katong/20140110/864ea8353fe3edd3.jpg%21200X200.jpg","2017-45-33","33","45","上班了 朋友们我回来了",
                "http://www.baidu.com","T恤男。。。。。","http://www.ucicq.com/uploads/allimg/170406/15145W360_0.jpg","https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1491976243324&di=2c620e234fdf657a9133e7b1af5b84b0&imgtype=0&src=http%3A%2F%2Fs4.album.sina.com.cn%2Fpic%2F4a795a9102001csr",null,Dynamic.ITEM_TYPE.ITEM_SINGLE_PICTURE.ordinal()));
        mData.add(new Dynamic("nkknkn","http://img.woyaogexing.com/touxiang/katong/20140110/864ea8353fe3edd3.jpg%21200X200.jpg","2017-45-33","33","45","上班了 朋友们我回来了",
                "http://www.baidu.com","T恤男。。。。。","http://www.ucicq.com/uploads/allimg/170406/15145W360_0.jpg","https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1491976243324&di=2c620e234fdf657a9133e7b1af5b84b0&imgtype=0&src=http%3A%2F%2Fs4.album.sina.com.cn%2Fpic%2F4a795a9102001csr",null,Dynamic.ITEM_TYPE.ITEM_LINK.ordinal()));
        mData.add(new Dynamic("shaomiao","http://img.woyaogexing.com/touxiang/katong/20140110/864ea8353fe3edd3.jpg%21200X200.jpg","2017-45-33","33","45","上班了 朋友们我回来了",
                "http://www.baidu.com","T恤男。。。。。","http://www.ucicq.com/uploads/allimg/170406/15145W360_0.jpg","https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1491976243324&di=2c620e234fdf657a9133e7b1af5b84b0&imgtype=0&src=http%3A%2F%2Fs4.album.sina.com.cn%2Fpic%2F4a795a9102001csr",null,Dynamic.ITEM_TYPE.ITEM_LINK.ordinal()));
        mData.add(new Dynamic("shaomiao","http://img.woyaogexing.com/touxiang/katong/20140110/864ea8353fe3edd3.jpg%21200X200.jpg","2017-45-33","33","45","上班了 朋友们我回来了",
                "http://www.baidu.com","T恤男。。。。。","http://www.ucicq.com/uploads/allimg/170406/15145W360_0.jpg","https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1491976243324&di=2c620e234fdf657a9133e7b1af5b84b0&imgtype=0&src=http%3A%2F%2Fs4.album.sina.com.cn%2Fpic%2F4a795a9102001csr",null,Dynamic.ITEM_TYPE.ITEM_LINK.ordinal()));
        mData.add(new Dynamic("shaomiao","http://img.woyaogexing.com/touxiang/katong/20140110/864ea8353fe3edd3.jpg%21200X200.jpg","2017-45-33","33","45","上班了 朋友们我回来了",
                "http://www.baidu.com","T恤男。。。。。","http://www.ucicq.com/uploads/allimg/170406/15145W360_0.jpg","https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1491976243324&di=2c620e234fdf657a9133e7b1af5b84b0&imgtype=0&src=http%3A%2F%2Fs4.album.sina.com.cn%2Fpic%2F4a795a9102001csr",null,Dynamic.ITEM_TYPE.ITEM_LINK.ordinal()));
        mData.add(new Dynamic("shaomiao","http://img.woyaogexing.com/touxiang/katong/20140110/864ea8353fe3edd3.jpg%21200X200.jpg","2017-45-33","33","45","上班了 朋友们我回来了",
                "http://www.baidu.com","T恤男。。。。。","http://www.ucicq.com/uploads/allimg/170406/15145W360_0.jpg","https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1491976243324&di=2c620e234fdf657a9133e7b1af5b84b0&imgtype=0&src=http%3A%2F%2Fs4.album.sina.com.cn%2Fpic%2F4a795a9102001csr",null,Dynamic.ITEM_TYPE.ITEM_LINK.ordinal()));
        mData.add(new Dynamic("shaomiao","http://img.woyaogexing.com/touxiang/katong/20140110/864ea8353fe3edd3.jpg%21200X200.jpg","2017-45-33","33","45","上班了 朋友们我回来了",
                "http://www.baidu.com","T恤男。。。。。","http://www.ucicq.com/uploads/allimg/170406/15145W360_0.jpg","https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1491976243324&di=2c620e234fdf657a9133e7b1af5b84b0&imgtype=0&src=http%3A%2F%2Fs4.album.sina.com.cn%2Fpic%2F4a795a9102001csr",null,Dynamic.ITEM_TYPE.ITEM_LINK.ordinal()));
        mData.add(new Dynamic("shaomiao","http://img.woyaogexing.com/touxiang/katong/20140110/864ea8353fe3edd3.jpg%21200X200.jpg","2017-45-33","33","45","上班了 朋友们我回来了",
                "http://www.baidu.com","T恤男。。。。。","http://www.ucicq.com/uploads/allimg/170406/15145W360_0.jpg","https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1491976243324&di=2c620e234fdf657a9133e7b1af5b84b0&imgtype=0&src=http%3A%2F%2Fs4.album.sina.com.cn%2Fpic%2F4a795a9102001csr",null,Dynamic.ITEM_TYPE.ITEM_LINK.ordinal()));
        mData.add(new Dynamic("shaomiao","http://img.woyaogexing.com/touxiang/katong/20140110/864ea8353fe3edd3.jpg%21200X200.jpg","2017-45-33","33","45","上班了 朋友们我回来了",
                "http://www.baidu.com","sdfdsfds。。。。。","http://www.ucicq.com/uploads/allimg/170406/15145W360_0.jpg","https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1491976243324&di=2c620e234fdf657a9133e7b1af5b84b0&imgtype=0&src=http%3A%2F%2Fs4.album.sina.com.cn%2Fpic%2F4a795a9102001csr",null,Dynamic.ITEM_TYPE.ITEM_LINK.ordinal()));
        mData.add(new Dynamic("shaomiao","http://img.woyaogexing.com/touxiang/katong/20140110/864ea8353fe3edd3.jpg%21200X200.jpg","2017-45-33","33","45","上班了 朋友们我回来了",
                "http://www.baidu.com","T恤男。。。。。","http://www.ucicq.com/uploads/allimg/170406/15145W360_0.jpg","https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1491976243324&di=2c620e234fdf657a9133e7b1af5b84b0&imgtype=0&src=http%3A%2F%2Fs4.album.sina.com.cn%2Fpic%2F4a795a9102001csr",null,Dynamic.ITEM_TYPE.ITEM_STRING.ordinal()));

        for (int i = 1 ; i < 100 ; i ++) {
            int type = Dynamic.ITEM_TYPE.ITEM_STRING.ordinal();
            if ((i%2) == 0) {
                type = Dynamic.ITEM_TYPE.ITEM_SINGLE_PICTURE.ordinal();
            }
            if (i == 5 || i == 7) {
                type = Dynamic.ITEM_TYPE.ITEM_SINGLE_PICTURE.ordinal();
            }

            if (i == 4 || i == 6) {
                type = Dynamic.ITEM_TYPE.ITEM_LINK.ordinal();
            }

            mData.add(new Dynamic("shaomiao"+i,"http://img.woyaogexing.com/touxiang/katong/20140110/864ea8353fe3edd3.jpg%21200X200.jpg","2017-45-33","33","45","上班了你好好哈哈哦 朋友们我回来了"+i,
                    "http://www.baidu.com","T恤男。。。。。"+i,"http://www.ucicq.com/uploads/allimg/170406/15145W360_0.jpg","https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1491976243324&di=2c620e234fdf657a9133e7b1af5b84b0&imgtype=0&src=http%3A%2F%2Fs4.album.sina.com.cn%2Fpic%2F4a795a9102001csr",null,type));

        }

    }
}
