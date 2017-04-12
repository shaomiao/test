package com.project.circlefriends.dynamic.entity;

import java.util.List;

/**
 * @author shaomiao
 * @Date 2017/4/11
 * @Time 15:23
 *
 * 动态实体类
 */

public class Dynamic {

    // 名称
    private String name;

    // 头像
    private String headPortrait;

    // 时间
    private String date;

    // 赞数
    private String fabulousNum;

    // 评论数
    private String commentNum;

    // 标题
    private String title;

    // 链接
    private String link = null;

    private String linkName;

    private String linkImage;

    // 图片
    private String picture = null;

    // 图片集合
    private List<String> pictures = null;

    // 样式类型  在赋值的时候判断
    private int type;

    public static enum ITEM_TYPE {
        ITEM_MANY_PICTURE,
        ITEM_SINGLE_PICTURE,
        ITEM_LINK,
        ITEM_STRING
    }

    public Dynamic() {}

    public Dynamic(String name, String headPortrait, String date, String fabulousNum, String commentNum, String title, String link, String linkName, String linkImage, String picture, List<String> pictures, int type) {
        this.name = name;
        this.headPortrait = headPortrait;
        this.date = date;
        this.fabulousNum = fabulousNum;
        this.commentNum = commentNum;
        this.title = title;
        this.link = link;
        this.linkName = linkName;
        this.linkImage = linkImage;
        this.picture = picture;
        this.pictures = pictures;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHeadPortrait() {
        return headPortrait;
    }

    public void setHeadPortrait(String headPortrait) {
        this.headPortrait = headPortrait;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getFabulousNum() {
        return fabulousNum;
    }

    public void setFabulousNum(String fabulousNum) {
        this.fabulousNum = fabulousNum;
    }

    public String getCommentNum() {
        return commentNum;
    }

    public void setCommentNum(String commentNum) {
        this.commentNum = commentNum;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public List<String> getPictures() {
        return pictures;
    }

    public void setPictures(List<String> pictures) {
        this.pictures = pictures;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getLinkName() {
        return linkName;
    }

    public void setLinkName(String linkName) {
        this.linkName = linkName;
    }

    public String getLinkImage() {
        return linkImage;
    }

    public void setLinkImage(String linkImage) {
        this.linkImage = linkImage;
    }
}
