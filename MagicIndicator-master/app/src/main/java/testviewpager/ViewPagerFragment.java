package testviewpager;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.JsonCallback;
import com.zhy.http.okhttp.entity.BaseJsonEntity;
import com.zhy.http.okhttp.network.ApiUrl;
import com.zhy.http.okhttp.utils.FastJsonUtils;
import com.zhy.http.okhttp.utils.MD5Util;

import net.lucode.hackware.magicindicatordemo.R;
import net.lucode.hackware.magicindicatordemo.test.BaseLazyFragment;
import net.lucode.hackware.magicindicatordemo.test.ShoppingClassifyGridEntity;

import java.util.ArrayList;
import java.util.List;

import cn.bingoogolapple.refreshlayout.BGANormalRefreshViewHolder;
import cn.bingoogolapple.refreshlayout.BGARefreshLayout;
import okhttp3.Call;

/**
 * @author shaomiao
 * @Date 2017/4/14
 * @Time 10:33
 */

public class ViewPagerFragment extends BaseLazyFragment implements BGARefreshLayout.BGARefreshLayoutDelegate {

    private RecyclerView mRecyclerView;

    private List<ShoppingClassifyGridEntity> mData;

    private RecyclerViewAdapter mAdapter;

    private BGARefreshLayout mRefreshLayout;

    private int page = 1;

    private static ViewPagerFragment mFragment;

    public static ViewPagerFragment newInstance() {
        if (mFragment == null) {
            mFragment = new ViewPagerFragment();
        }
        return mFragment;
    }

    @Override
    protected void initPrepare() {

    }

    @Override
    protected void onInvisible() {

    }

    @Override
    protected void initData() {
        mData = new ArrayList<>();
        mAdapter = new RecyclerViewAdapter(mData);
        mRecyclerView.setAdapter(mAdapter);
//        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(),2));
        getProduct();
    }

    @Override
    protected View initView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle saveInstanceState) {
        View view = inflater.inflate(R.layout.fragment_test, container, false);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerview);

        mRefreshLayout = (BGARefreshLayout) view.findViewById(R.id.m_refreshLayout);
        mRefreshLayout.setDelegate(this);
        mRefreshLayout.setRefreshViewHolder(new BGANormalRefreshViewHolder(getContext(),true));
        return view;
    }

    @Override
    public void onBGARefreshLayoutBeginRefreshing(BGARefreshLayout refreshLayout) {
        page = 1;
        getProduct();
    }

    @Override
    public boolean onBGARefreshLayoutBeginLoadingMore(BGARefreshLayout refreshLayout) {

        new AsyncTask<Void, Void, Void>() {

            @Override
            protected Void doInBackground(Void... params) {
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                // 加载完毕后在UI线程结束加载更多
                page++;
                if (page > 3) {
                    mRefreshLayout.endLoadingMore();
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
                    mAdapter.notifyItemInserted(mData.size()-1);
                    mRefreshLayout.endLoadingMore();
                }
            }
        }.execute();

        return true;
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
                        mAdapter.notifyDataSetChanged();
                        mRefreshLayout.endRefreshing();

                    }
                });
    }
}
