package com.project.circlefriends.dynamic.adapter;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.project.circlefriends.R;
import com.project.circlefriends.dynamic.entity.Dynamic;

import java.util.List;

/**
 * @author shaomiao
 * @Date 2017/4/11
 * @Time 15:07
 */

public class DynamicAdapter extends RecyclerView.Adapter {

    private List<Dynamic> mData;

    private Context mContext;

    public DynamicAdapter(List<Dynamic> data) {
        this.mData = data;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        mContext = parent.getContext();

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        // 多图
        if (viewType == Dynamic.ITEM_TYPE.ITEM_MANY_PICTURE.ordinal()) {
            View view = inflater.inflate(R.layout.fragment_dynamic_manypicture_item, parent, false);
            return new ManyPictureViewHolder(view);
        }
        // 单图
        else if (viewType == Dynamic.ITEM_TYPE.ITEM_SINGLE_PICTURE.ordinal()) {
            View view = inflater.inflate(R.layout.fragment_dynamic_singlepicture_item, parent, false);
            return new SinglePictureViewHolder(view);
        }
        // 链接
        else if (viewType == Dynamic.ITEM_TYPE.ITEM_LINK.ordinal()) {
            View view = inflater.inflate(R.layout.fragment_dynamic_link_item, parent, false);
            return new LinkViewHolder(view);
        }
        // 文字
        else if (viewType == Dynamic.ITEM_TYPE.ITEM_STRING.ordinal()) {
            View view = inflater.inflate(R.layout.fragment_dynamic_string_item, parent, false);
            return new StringViewHolder(view);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        Dynamic dynamic = mData.get(position);
        // 多图
        if (holder instanceof ManyPictureViewHolder) {
            ((ManyPictureViewHolder) holder).mNameTv.setText(dynamic.getName());
            ((ManyPictureViewHolder) holder).mDateTv.setText(dynamic.getDate());
            ((ManyPictureViewHolder) holder).mTitleTv.setText(dynamic.getTitle());
            // 点赞数
            ((ManyPictureViewHolder) holder).mFabulousTv.setText(dynamic.getFabulousNum());
            // 评论数
            ((ManyPictureViewHolder) holder).mCommentTv.setText(dynamic.getCommentNum());
            // 头像
            Glide
                    .with(mContext)
                    .load(dynamic.getHeadPortrait())
                    .centerCrop()
                    .placeholder(R.mipmap.ic_launcher)
                    .crossFade()
                    .into(((ManyPictureViewHolder) holder).mHeadPortraitIv);

            // recyclerview
            DynamicManyPictureAdapter adapter = new DynamicManyPictureAdapter(mContext, dynamic.getPictures());
//            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
//            linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
            ((ManyPictureViewHolder) holder).mImageListRv.setLayoutManager(new GridLayoutManager(mContext,4));
            ((ManyPictureViewHolder) holder).mImageListRv.setAdapter(adapter);
        }
        // 单图
        else if (holder instanceof SinglePictureViewHolder) {
            // 头像
//            ((SinglePictureViewHolder) holder).mHeadPortraitIv.setImageResource();
            ((SinglePictureViewHolder) holder).mNameTv.setText(dynamic.getName());
            ((SinglePictureViewHolder) holder).mDateTv.setText(dynamic.getDate());
            ((SinglePictureViewHolder) holder).mTitleTv.setText(dynamic.getTitle());
            // 图片
            //((SinglePictureViewHolder) holder).mImageIv.setImageResource();
            // 点赞数
            ((SinglePictureViewHolder) holder).mFabulousTv.setText(dynamic.getFabulousNum());
            // 评论数
            ((SinglePictureViewHolder) holder).mCommentTv.setText(dynamic.getCommentNum());

            // 头像
            Glide
                    .with(mContext)
                    .load(dynamic.getHeadPortrait())
                    .centerCrop()
                    .placeholder(R.mipmap.ic_launcher)
                    .crossFade()
                    .into(((SinglePictureViewHolder) holder).mHeadPortraitIv);
            // 图片
            Glide
                    .with(mContext)
                    .load(dynamic.getPicture())
                    .centerCrop()
                    .placeholder(R.mipmap.ic_launcher)
                    .crossFade()
                    .into(((SinglePictureViewHolder) holder).mImageIv);

        }
        // 链接
        else if (holder instanceof LinkViewHolder) {
            ((LinkViewHolder) holder).mNameTv.setText(dynamic.getName());
            ((LinkViewHolder) holder).mDateTv.setText(dynamic.getDate());
            ((LinkViewHolder) holder).mTitleTv.setText(dynamic.getTitle());
            // 点赞数
            ((LinkViewHolder) holder).mFabulousTv.setText(dynamic.getFabulousNum());
            // 评论数
            ((LinkViewHolder) holder).mCommentTv.setText(dynamic.getCommentNum());
            ((LinkViewHolder) holder).mLinkTitleTv.setText(dynamic.getLinkName());
            // 链接图片
//            ((LinkViewHolder) holder).mLinkImageIv.setImageResource();
            Glide
                    .with(mContext)
                    .load(dynamic.getLinkImage())
                    .centerCrop()
                    .placeholder(R.mipmap.ic_launcher)
                    .crossFade()
                    .into(((LinkViewHolder) holder).mLinkImageIv);
            // 头像
            Glide
                    .with(mContext)
                    .load(dynamic.getHeadPortrait())
                    .centerCrop()
                    .placeholder(R.mipmap.ic_launcher)
                    .crossFade()
                    .into(((LinkViewHolder) holder).mHeadPortraitIv);
        }
        // 文字
        else if (holder instanceof StringViewHolder) {
            ((StringViewHolder) holder).mNameTv.setText(dynamic.getName());
            ((StringViewHolder) holder).mDateTv.setText(dynamic.getDate());
            ((StringViewHolder) holder).mTitleTv.setText(dynamic.getTitle());
            // 点赞数
            ((StringViewHolder) holder).mFabulousTv.setText(dynamic.getFabulousNum());
            // 评论数
            ((StringViewHolder) holder).mCommentTv.setText(dynamic.getCommentNum());
            // 头像
            Glide
                    .with(mContext)
                    .load(dynamic.getHeadPortrait())
                    .centerCrop()
                    .placeholder(R.mipmap.ic_launcher)
                    .crossFade()
                    .into(((StringViewHolder) holder).mHeadPortraitIv);
        }
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    @Override
    public int getItemViewType(int position) {
        return mData.get(position).getType();
    }

    // 多图ViewHolder
    class ManyPictureViewHolder extends RecyclerView.ViewHolder {

        TextView mNameTv, mDateTv, mTitleTv, mCommentTv, mFabulousTv;
        ImageView mHeadPortraitIv;
        RecyclerView mImageListRv;

        public ManyPictureViewHolder(View itemView) {
            super(itemView);
            mNameTv = (TextView) itemView.findViewById(R.id.name_tv);
            mDateTv = (TextView) itemView.findViewById(R.id.date_tv);
            mTitleTv = (TextView) itemView.findViewById(R.id.title_tv);
            mCommentTv = (TextView) itemView.findViewById(R.id.comment_tv);
            mFabulousTv = (TextView) itemView.findViewById(R.id.fabulous_tv);
            mHeadPortraitIv = (ImageView) itemView.findViewById(R.id.head_portrait_iv);
            mImageListRv = (RecyclerView) itemView.findViewById(R.id.image_list_rv);
        }
    }

    // 单张图片ViewHolder
    class SinglePictureViewHolder extends RecyclerView.ViewHolder {

        TextView mNameTv, mDateTv, mTitleTv, mCommentTv, mFabulousTv;
        ImageView mHeadPortraitIv, mImageIv;

        public SinglePictureViewHolder(View itemView) {
            super(itemView);
            mNameTv = (TextView) itemView.findViewById(R.id.name_tv);
            mDateTv = (TextView) itemView.findViewById(R.id.date_tv);
            mTitleTv = (TextView) itemView.findViewById(R.id.title_tv);
            mCommentTv = (TextView) itemView.findViewById(R.id.comment_tv);
            mFabulousTv = (TextView) itemView.findViewById(R.id.fabulous_tv);
            mHeadPortraitIv = (ImageView) itemView.findViewById(R.id.head_portrait_iv);
            mImageIv = (ImageView) itemView.findViewById(R.id.image_iv);
        }
    }

    // 链接ViewHolder
    class LinkViewHolder extends RecyclerView.ViewHolder {

        TextView mNameTv, mDateTv, mTitleTv, mCommentTv, mFabulousTv, mLinkTitleTv;
        ImageView mHeadPortraitIv, mLinkImageIv;

        public LinkViewHolder(View itemView) {
            super(itemView);
            mNameTv = (TextView) itemView.findViewById(R.id.name_tv);
            mDateTv = (TextView) itemView.findViewById(R.id.date_tv);
            mTitleTv = (TextView) itemView.findViewById(R.id.title_tv);
            mCommentTv = (TextView) itemView.findViewById(R.id.comment_tv);
            mFabulousTv = (TextView) itemView.findViewById(R.id.fabulous_tv);
            mHeadPortraitIv = (ImageView) itemView.findViewById(R.id.head_portrait_iv);
            mLinkImageIv = (ImageView) itemView.findViewById(R.id.link_image_iv);
            mLinkTitleTv = (TextView) itemView.findViewById(R.id.link_title_tv);
        }
    }


    // 文字ViewHolder
    class StringViewHolder extends RecyclerView.ViewHolder {

        TextView mNameTv, mDateTv, mTitleTv, mCommentTv, mFabulousTv;
        ImageView mHeadPortraitIv;

        public StringViewHolder(View itemView) {
            super(itemView);
            mNameTv = (TextView) itemView.findViewById(R.id.name_tv);
            mDateTv = (TextView) itemView.findViewById(R.id.date_tv);
            mTitleTv = (TextView) itemView.findViewById(R.id.title_tv);
            mCommentTv = (TextView) itemView.findViewById(R.id.comment_tv);
            mFabulousTv = (TextView) itemView.findViewById(R.id.fabulous_tv);
            mHeadPortraitIv = (ImageView) itemView.findViewById(R.id.head_portrait_iv);
        }
    }
}
