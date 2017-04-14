package net.lucode.hackware.magicindicatordemo.test;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.JsonCallback;
import com.zhy.http.okhttp.entity.BaseJsonEntity;
import com.zhy.http.okhttp.network.ApiUrl;
import com.zhy.http.okhttp.utils.FastJsonUtils;
import com.zhy.http.okhttp.utils.MD5Util;

import net.lucode.hackware.magicindicatordemo.R;

import java.util.ArrayList;
import java.util.List;

import cn.bingoogolapple.refreshlayout.BGANormalRefreshViewHolder;
import cn.bingoogolapple.refreshlayout.BGARefreshLayout;
import okhttp3.Call;

/**
 * @author shaomiao
 * @Date 2017/4/13
 * @Time 17:29
 */

public class TestFragment1 extends Fragment implements SwipeRefreshLayout.OnRefreshListener, BGARefreshLayout.BGARefreshLayoutDelegate {


    public static TestFragment1 newInstance(String s) {
        TestFragment1 newFragment = new TestFragment1();
        Bundle bundle = new Bundle();
        bundle.putString("hello", s);
        newFragment.setArguments(bundle);

        //bundle还可以在每个标签里传送数据


        return newFragment;

    }

    private RecyclerView mRecyclerView;


    public static final String EXTRA_TEXT = "extra_text";

    private List<ShoppingClassifyGridEntity> mData;

    private RecyclerViewAdapter adapter;

    private BGARefreshLayout mrefreshLayout;

    private int page = 1 ;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_test, container, false);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerview);
//        mRefresh = (SwipeRefreshLayout) view.findViewById(R.id.refresh);

        mrefreshLayout = (BGARefreshLayout) view.findViewById(R.id.m_refreshLayout);
        mrefreshLayout.setDelegate(this);
        mrefreshLayout.setRefreshViewHolder(new BGANormalRefreshViewHolder(getContext(), true));

        mData = new ArrayList<>();
        adapter = new RecyclerViewAdapter(mData);
        mRecyclerView.setAdapter(adapter);
        getProduct();
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
//        mRefresh.setOnRefreshListener(this);
//        mRefresh.setRefreshing(true);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        TextView textView = (TextView) view.findViewById(R.id.text_view);
        Bundle bundle = getArguments();
        if (bundle != null) {
            textView.setText(bundle.getString(EXTRA_TEXT));
        }
    }

    public void getProduct() {
        String url = ApiUrl.getInstance().getApi("yz_top_good");

        OkHttpUtils
                .post()
                .url(url)
                .addParams("app_key", MD5Util.getMD5Key(url))
                .addParams("cate_one","0")
                .addParams("cate_two","0")
                .addParams("cate_three","0")
                .addParams("cate_four","0")
                .addParams("price2","999999999999999")
                .addParams("price","0")
                .addParams("order","0")
                .addParams("uid","0")
                .build()
                .execute(new JsonCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
//                        Toast.makeText(MainActivity.this, "E", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onResponse(BaseJsonEntity response, int id) {
                        Toast.makeText(getActivity(), "操作成功", Toast.LENGTH_SHORT).show();
                        mData.clear();
                        List<ShoppingClassifyGridEntity> datas = FastJsonUtils.getObjectsList(FastJsonUtils.getStr(response.getObj(), "list"), ShoppingClassifyGridEntity.class);
                        mData.addAll(datas);
                        adapter.notifyDataSetChanged();
                        mrefreshLayout.endRefreshing();

                    }
                });
    }

    @Override
    public void onRefresh() {
        getProduct();
//        mRefresh.setRefreshing(false);
    }

    @Override
    public void onBGARefreshLayoutBeginRefreshing(BGARefreshLayout refreshLayout) {
        page =1;
        getProduct();
    }

    @Override
    public boolean onBGARefreshLayoutBeginLoadingMore(BGARefreshLayout refreshLayout) {
        new AsyncTask<Void, Void, Void>() {

            @Override
            protected Void doInBackground(Void... params) {
//                try {
////                    Thread.sleep(250);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                // 加载完毕后在UI线程结束加载更多
                page++;
                if (page > 3) {
                    mrefreshLayout.endLoadingMore();
//                    ToastUtils.show(context, "没有最新数据了");

                } else {
                    ShoppingClassifyGridEntity entity = new ShoppingClassifyGridEntity();
                    entity.setGoods_name("时尚T恤时尚时尚最时尚灰常时髦");
                    entity.setGoods_sell("6");
                    entity.setGoods_price("87.00");
                    for (int i = 0; i < 2; i++) {
                        mData.add(entity);
                    }
//                    adapter.notifyDataSetChanged();
                    adapter.notifyItemInserted(mData.size()-1);
                    mrefreshLayout.endLoadingMore();
                }
            }
        }.execute();
        return true;
    }
}
