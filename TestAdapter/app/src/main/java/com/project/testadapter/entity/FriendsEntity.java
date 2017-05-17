package com.project.testadapter.entity;


import java.io.Serializable;

/**
 * @author shaomiao
 * @Date 2017/4/11
 * @Time 15:23
 * <p>
 * 动态实体类
 */

public class FriendsEntity implements Serializable {



    private String dynamicId;
    private String dynamicNickName;
    private String dynamicHeadPortrait;
    private String dynamicDate;
    private String dynamicFabulousNum;
    private String dynamicCommentNum;
    private String dynamicTitle;
    private String dynamicLink;
    private String dynamicLinkName;
    private String dynamicLinkImage;
    private String dynamicImage;
    private String dynamicLinkId;
    private String[] dynamicImages;
    private boolean isFabulousCheck = false;
    private int dynamicType;
    private String type;
    private String praise;





//    // 动态id
//    private String dynamicId;
//
//    // 昵称
//    private String nickname;
//
//    // 头像
//    private String headPortrait;
//
//    // 时间
//    private String date;
//
//    // 赞数
//    private String fabulousNum;
//
//    // 评论数
//    private String commentNum;
//
//    // 标题
//    private String title;
//
//    // 链接
//    private String link = null;
//
//    // 商品名
//    private String goodsName;
//
//    private String linkImage;
//
//    // 图片
//    private String picture = null;
//
//    private String good_id;
//
//    // 图片集合
////    private List<String> pictures = null;
//
//    private String [] pictures = null;
//
//    private String zh_pic;
//
//    // 好友的uid
//    private String friendsUid;
//
//    private boolean isFabulousCheck = false;
//
//    // 样式类型  在赋值的时候判断
//    private String type;
//
//    private String praise;
//
//    private int friendsType;

    public static enum ITEM_TYPE {
        // 多图
        ITEM_MANY_PICTURE,
        // 单图
        ITEM_SINGLE_PICTURE,
        // 链接 分享商品
        ITEM_LINK,
        // 字符串
        ITEM_STRING
    }

    public FriendsEntity() {
    }

    public FriendsEntity(String dynamicId, String dynamicNickName, String dynamicHeadPortrait, String dynamicDate, String dynamicFabulousNum, String dynamicCommentNum, String dynamicTitle, String dynamicLink, String dynamicLinkName, String dynamicLinkImage, String dynamicImage, String dynamicLinkId, String[] dynamicImages, boolean isFabulousCheck, String type, String praise, int dynamicType) {
        this.dynamicId = dynamicId;
        this.dynamicNickName = dynamicNickName;
        this.dynamicHeadPortrait = dynamicHeadPortrait;
        this.dynamicDate = dynamicDate;
        this.dynamicFabulousNum = dynamicFabulousNum;
        this.dynamicCommentNum = dynamicCommentNum;
        this.dynamicTitle = dynamicTitle;
        this.dynamicLink = dynamicLink;
        this.dynamicLinkName = dynamicLinkName;
        this.dynamicLinkImage = dynamicLinkImage;
        this.dynamicImage = dynamicImage;
        this.dynamicLinkId = dynamicLinkId;
        this.dynamicImages = dynamicImages;
        this.isFabulousCheck = isFabulousCheck;
        this.dynamicType = dynamicType;
        this.type = type;
        this.praise = praise;
    }

    public String getDynamicId() {
        return dynamicId;
    }

    public void setDynamicId(String dynamicId) {
        this.dynamicId = dynamicId;
    }

    public String getDynamicNickName() {
        return dynamicNickName;
    }

    public void setDynamicNickName(String dynamicNickName) {
        this.dynamicNickName = dynamicNickName;
    }

    public String getDynamicHeadPortrait() {
        return dynamicHeadPortrait;
    }

    public void setDynamicHeadPortrait(String dynamicHeadPortrait) {
        this.dynamicHeadPortrait = dynamicHeadPortrait;
    }

    public String getDynamicDate() {
        return dynamicDate;
    }

    public void setDynamicDate(String dynamicDate) {
        this.dynamicDate = dynamicDate;
    }

    public String getDynamicFabulousNum() {
        return dynamicFabulousNum;
    }

    public void setDynamicFabulousNum(String dynamicFabulousNum) {
        this.dynamicFabulousNum = dynamicFabulousNum;
    }

    public String getDynamicCommentNum() {
        return dynamicCommentNum;
    }

    public void setDynamicCommentNum(String dynamicCommentNum) {
        this.dynamicCommentNum = dynamicCommentNum;
    }

    public String getDynamicTitle() {
        return dynamicTitle;
    }

    public void setDynamicTitle(String dynamicTitle) {
        this.dynamicTitle = dynamicTitle;
    }

    public String getDynamicLink() {
        return dynamicLink;
    }

    public void setDynamicLink(String dynamicLink) {
        this.dynamicLink = dynamicLink;
    }

    public String getDynamicLinkName() {
        return dynamicLinkName;
    }

    public void setDynamicLinkName(String dynamicLinkName) {
        this.dynamicLinkName = dynamicLinkName;
    }

    public String getDynamicLinkImage() {
        return dynamicLinkImage;
    }

    public void setDynamicLinkImage(String dynamicLinkImage) {
        this.dynamicLinkImage = dynamicLinkImage;
    }

    public String getDynamicImage() {
        return dynamicImage;
    }

    public void setDynamicImage(String dynamicImage) {
        this.dynamicImage = dynamicImage;
    }

    public String getDynamicLinkId() {
        return dynamicLinkId;
    }

    public void setDynamicLinkId(String dynamicLinkId) {
        this.dynamicLinkId = dynamicLinkId;
    }

    public String[] getDynamicImages() {
        return dynamicImages;
    }

    public void setDynamicImages(String[] dynamicImages) {
        this.dynamicImages = dynamicImages;
    }

    public boolean isFabulousCheck() {
        return isFabulousCheck;
    }

    public void setFabulousCheck(boolean fabulousCheck) {
        isFabulousCheck = fabulousCheck;
    }

    public int getDynamicType() {
        return dynamicType;
    }


    public void setDynamicType(int dynamicType) {
        this.dynamicType = dynamicType;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPraise() {
        return praise;
    }

    public void setPraise(String praise) {
        this.praise = praise;
    }

    //    public FriendsEntity(String dynamicId, String nickname, String headPortrait, String date, String fabulousNum, String commentNum, String title, String link, String goodsName, String linkImage, String picture, String good_id, String[] pictures, String zh_pic, String friendsUid, boolean isFabulousCheck, String type, String praise, int friendsType) {
//        this.dynamicId = dynamicId;
//        this.nickname = nickname;
//        this.headPortrait = headPortrait;
//        this.date = date;
//        this.fabulousNum = fabulousNum;
//        this.commentNum = commentNum;
//        this.title = title;
//        this.link = link;
//        this.goodsName = goodsName;
//        this.linkImage = linkImage;
//        this.picture = picture;
//        this.good_id = good_id;
//        this.pictures = pictures;
//        this.zh_pic = zh_pic;
//        this.friendsUid = friendsUid;
//        this.isFabulousCheck = isFabulousCheck;
//        this.type = type;
//        this.praise = praise;
//        this.friendsType = friendsType;
//    }

//    public String getGood_id() {
//        return good_id;
//    }
//
//    public void setGood_id(String good_id) {
//        this.good_id = good_id;
//    }
//
//    public String getPraise() {
//        return praise;
//    }
//
//    public void setPraise(String praise) {
//        this.praise = praise;
//        if (praise.equals("0")) {
//            setFabulousCheck(false);
//        } else {
//            setFabulousCheck(true);
//        }
//    }
//
//    public String getDynamicId() {
//        return dynamicId;
//    }
//
//    public void setDynamicId(String dynamicId) {
//        this.dynamicId = dynamicId;
//    }
//
//    public String getFriendsUid() {
//        return friendsUid;
//    }
//
//    public void setFriendsUid(String friendsUid) {
//        this.friendsUid = friendsUid;
//    }
//
//    public boolean isFabulousCheck() {
//        return isFabulousCheck;
//    }
//
//    public void setFabulousCheck(boolean fabulousCheck) {
//        isFabulousCheck = fabulousCheck;
//
//    }
//
//    public String getNickname() {
//        return nickname;
//    }
//
//    public void setNickname(String nickname) {
//        this.nickname = nickname;
//    }
//
//    public String getHeadPortrait() {
//        return headPortrait;
//    }
//
//    public void setHeadPortrait(String headPortrait) {
//        this.headPortrait = headPortrait;
//    }
//
//    public String getDate() {
//        return date;
//    }
//
//    public void setDate(String date) {
//        this.date = date;
//    }
//
//    public String getFabulousNum() {
//        return fabulousNum;
//    }
//
//    public void setFabulousNum(String fabulousNum) {
//        this.fabulousNum = fabulousNum;
//    }
//
//    public String getCommentNum() {
//        return commentNum;
//    }
//
//    public void setCommentNum(String commentNum) {
//        this.commentNum = commentNum;
//    }
//
//    public String getTitle() {
//        return title;
//    }
//
//    public void setTitle(String title) {
//        this.title = title;
//    }
//
//    public String getLink() {
//        return link;
//    }
//
//    public void setLink(String link) {
//        this.link = link;
//    }
//
//    public String getPicture() {
//        return picture;
//    }
//
//    public void setPicture(String picture) {
//        this.picture = picture;
//
//    }
//
//    public String[] getPictures() {
//        return pictures;
//    }
//
//    public void setPictures(String[] pictures) {
//        this.pictures = pictures;
//    }
//
//    public String getGoodsName() {
//        return goodsName;
//    }
//
//    public void setGoodsName(String goodsName) {
//        this.goodsName = goodsName;
//    }
//
//    public String getLinkImage() {
//        return linkImage;
//    }
//
//    public void setLinkImage(String linkImage) {
//        this.linkImage = linkImage;
//    }
//
//    public int getFriendsType() {
//        return friendsType;
//    }
//
//    public void setFriendsType(int friendsType) {
//        this.friendsType = friendsType;
//    }
//
//    public String getType() {
//        return type;
//    }
//
//    public void setType(String type) {
//        this.type = type;
//        if (type.equals("2")) {
//            setFriendsType(ITEM_TYPE.ITEM_LINK.ordinal());
//
//        }
//    }
//
//    public String getZh_pic() {
//        return zh_pic;
//    }
//
//    public void setZh_pic(String zh_pic) {
//        this.zh_pic = zh_pic;
//        if (friendsType!= ITEM_TYPE.ITEM_LINK.ordinal()) {
//            if (zh_pic.equals("")) {
//                setFriendsType(ITEM_TYPE.ITEM_STRING.ordinal());
//            } else if (zh_pic.indexOf(",")>0) {
//                setFriendsType(ITEM_TYPE.ITEM_MANY_PICTURE.ordinal());
//                setPictures(getZh_pic().split(","));
//            } else {
//                setFriendsType(ITEM_TYPE.ITEM_SINGLE_PICTURE.ordinal());
////                setPicture(WXApplication.Main_Url+getZh_pic());
//            }
//        } else {
//            setLinkImage(getZh_pic());
//        }
//    }
}
