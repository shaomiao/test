package com.pkyapp.jinzaizhijianmerchant.util;

/**
 * @author shaomiao
 * @Date 2017/3/28
 * @Time 10:27
 */

public class ConstantUtil {

    //  [order_stat] => 1是已支付（等待商家确认）2 商家以确认（待收货）3是以发货 4已经确认收货(订单完成) 5退款中 6退款完成

    // 未确认
    public static final String ORDER_STATE_NOT_CONFIRMED_CODE = "1";

    // 代发货
    public static final String ORDER_STATE_WAITING_DELIVERY_CODE = "2";

    // 运输中
    public static final String ORDER_STATE_IN_TRANSIT_CODE = "3";

    // 已完成
    public static final String ORDER_STATE_ALREADY_CODE = "4";



    public static final String NETWORK_SUCCESS_CODE = "200";
    public static final String NETWORK_FAIL_CODE = "400";

    public static final String LOGIN_TIMEOUT_CODE = "600";

    public static final String NETWORK_ERR = "网络不给力";

    public static final String LOGIN_FAIL = "登录失败";
    public static final String LOGIN_SUCCESS = "登录成功";
    public static final String LOGIN_TIMEOUT = "登录超时，请重新登录";

    public static final String ORDER_NULL = "暂无订单";
    public static final String AMOUNT_NULL = "暂无金额";

    public static final String CONFIRM_ORDER = "确认订单";
    public static final String DELIVER_GOODS = "发货";
    public static final String VIEW_COMMENTS = "查看评论";


    public class Interface {
        // 登录
        public static final String login = "shopLogin";

        // 订单数据
        public static final String shop_list = "shop_list";

        // 销售统计
        public static final String shop_order_statistical = "shop_order_statistical";

        // 确认订单
        public static final String shop_oredr_add = "shop_oredr_add";

        // 订单详情
        public static final String shop_list_desc = "shop_list_desc";

        public static final String logistics_seach = "logistics_seach";

    }
}
