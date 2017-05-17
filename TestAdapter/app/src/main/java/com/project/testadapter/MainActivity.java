package com.project.testadapter;

import android.graphics.Color;
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
import com.project.testadapter.entity.FriendsEntity;
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
    private List<FriendsEntity> mDataList;
    private CommonAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        mDataList = new ArrayList<>();

//        mDataList.add(new FriendsEntity());
//        public FriendsEntity(String dynamicId, String dynamicNickName, String dynamicHeadPortrait, String dynamicDate, String dynamicFabulousNum, String dynamicCommentNum,
// String dynamicTitle, String dynamicLink, String dynamicLinkName, String dynamicLinkImage, String dynamicImage, String dynamicLinkId, String[] dynamicImages, boolean isFabulousCheck, String dynamicType, String type, String praise) {

        mDataList.add(new FriendsEntity("id", "名称", "头像", "2017-7-8", "20", "40", "标题", "链接", "商品名", "商品图片", "动态图片", "链接id", null, false, "xx", "xx", FriendsEntity.ITEM_TYPE.ITEM_LINK.ordinal()));
        mDataList.add(new FriendsEntity("id", "名称", "头像", "2017-7-8", "20", "40", "标题", "链接", "商品名", "商品图片", "动态图片", "链接id", null, false, "xx", "xx", FriendsEntity.ITEM_TYPE.ITEM_LINK.ordinal()));
        mDataList.add(new FriendsEntity("id", "名称", "头像", "2017-7-8", "20", "40", "标题", "链接", "商品名", "商品图片", "动态图片", "链接id", null, false, "xx", "xx", FriendsEntity.ITEM_TYPE.ITEM_MANY_PICTURE.ordinal()));
        mDataList.add(new FriendsEntity("id", "名称", "头像", "2017-7-8", "20", "40", "标题", "链接", "商品名", "商品图片", "动态图片", "链接id", null, false, "xx", "xx", FriendsEntity.ITEM_TYPE.ITEM_SINGLE_PICTURE.ordinal()));
        mDataList.add(new FriendsEntity("id", "名称", "头像", "2017-7-8", "20", "40", "标题", "链接", "商品名", "商品图片", "动态图片", "链接id", null, false, "xx", "xx", FriendsEntity.ITEM_TYPE.ITEM_LINK.ordinal()));
        mDataList.add(new FriendsEntity("id", "名称", "头像", "2017-7-8", "20", "40", "标题", "链接", "商品名", "商品图片", "动态图片", "链接id", null, false, "xx", "xx", FriendsEntity.ITEM_TYPE.ITEM_STRING.ordinal()));
        mDataList.add(new FriendsEntity("id", "名称", "头像", "2017-7-8", "20", "40", "标题", "链接", "商品名", "商品图片", "动态图片", "链接id", null, false, "xx", "xx", FriendsEntity.ITEM_TYPE.ITEM_LINK.ordinal()));
        mDataList.add(new FriendsEntity("id", "名称", "头像", "2017-7-8", "20", "40", "标题", "链接", "商品名", "商品图片", "动态图片", "链接id", null, false, "xx", "xx", FriendsEntity.ITEM_TYPE.ITEM_LINK.ordinal()));
        mDataList.add(new FriendsEntity("id", "名称", "头像", "2017-7-8", "20", "40", "标题", "链接", "商品名", "商品图片", "动态图片", "链接id", null, false, "xx", "xx", FriendsEntity.ITEM_TYPE.ITEM_LINK.ordinal()));
        mDataList.add(new FriendsEntity("id", "名称", "头像", "2017-7-8", "20", "40", "标题", "链接", "商品名", "商品图片", "动态图片", "链接id", null, false, "xx", "xx", FriendsEntity.ITEM_TYPE.ITEM_LINK.ordinal()));

        MultiItemTypeAdapter adapter = new MultiItemTypeAdapter(this, mDataList);
        adapter.addItemViewDelegate(new ManyPictureDelegate());
        adapter.addItemViewDelegate(new SinglePictureDelegate());
        adapter.addItemViewDelegate(new LinkDelegate());
        adapter.addItemViewDelegate(new StringDelegate());
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

    }

    public class ManyPictureDelegate implements ItemViewDelegate<FriendsEntity> {
        @Override
        public int getItemViewLayoutId() {
            return R.layout.fragment_friends_manypicture_item;
        }

        @Override
        public boolean isForViewType(FriendsEntity item, int position) {
            return item.getDynamicType() == FriendsEntity.ITEM_TYPE.ITEM_MANY_PICTURE.ordinal();
        }

        @Override
        public void convert(ViewHolder holder, FriendsEntity friendsEntity, int position) {


            bindHolder(holder, friendsEntity);
//            holder.setText(R.id.image_list_rv,friendsEntity.getGoodsName());
        }
    }

    private void bindHolder(ViewHolder holder, FriendsEntity friendsEntity) {
        holder.setText(R.id.name_tv, friendsEntity.getDynamicNickName());
        holder.setText(R.id.date_tv, friendsEntity.getDynamicDate());
//            holder.setText(R.id.title_tv,Base64Util.decryptBASE64(friendsEntity.getTitle()));
            holder.setText(R.id.title_tv,friendsEntity.getDynamicTitle());
        holder.setText(R.id.comment_tv, friendsEntity.getDynamicCommentNum());
        holder.setText(R.id.fabulous_tv, friendsEntity.getDynamicFabulousNum());

//            holder.setText(R.id.head_portrait_iv,friendsEntity.getGoodsName());
        holder.setOnClickListener(R.id.comment_ll, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "评论", Toast.LENGTH_SHORT).show();
            }
        });
        holder.setOnClickListener(R.id.fabulous_ll, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "点赞", Toast.LENGTH_SHORT).show();
            }
        });
        holder.setOnClickListener(R.id.head_portrait_iv, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "头像", Toast.LENGTH_SHORT).show();
            }
        });
        if (friendsEntity.isFabulousCheck()) {
            holder.setTextColor(R.id.fabulous_tv, Color.parseColor("#cbab78"));
            holder.setImageResource(R.id.fabulous_iv, R.mipmap.dynamic_fabulous_sel);
        } else {
            holder.setTextColor(R.id.fabulous_tv, Color.parseColor("#000000"));
            holder.setImageResource(R.id.fabulous_iv, R.mipmap.dynamic_fabulous_no);
        }
    }


    public class SinglePictureDelegate implements ItemViewDelegate<FriendsEntity> {

        @Override
        public int getItemViewLayoutId() {
            return R.layout.fragment_friends_singlepicture_item;
        }

        @Override
        public boolean isForViewType(FriendsEntity item, int position) {
            return item.getDynamicType() == FriendsEntity.ITEM_TYPE.ITEM_SINGLE_PICTURE.ordinal();
        }

        @Override
        public void convert(ViewHolder holder, FriendsEntity friendsEntity, int position) {
            bindHolder(holder, friendsEntity);
        }
    }

    public class LinkDelegate implements ItemViewDelegate<FriendsEntity> {
        @Override
        public int getItemViewLayoutId() {
            return R.layout.fragment_friends_link_item;
        }

        @Override
        public boolean isForViewType(FriendsEntity item, int position) {
            return item.getDynamicType() == FriendsEntity.ITEM_TYPE.ITEM_LINK.ordinal();
        }

        @Override
        public void convert(ViewHolder holder, FriendsEntity friendsEntity, int position) {
            bindHolder(holder, friendsEntity);
        }
    }

    public class StringDelegate implements ItemViewDelegate<FriendsEntity> {

        @Override
        public int getItemViewLayoutId() {
            return R.layout.fragment_friends_string_item;
        }

        @Override
        public boolean isForViewType(FriendsEntity item, int position) {
            return item.getDynamicType() == FriendsEntity.ITEM_TYPE.ITEM_STRING.ordinal();
        }

        @Override
        public void convert(ViewHolder holder, FriendsEntity friendsEntity, int position) {
            bindHolder(holder, friendsEntity);
        }
    }


}