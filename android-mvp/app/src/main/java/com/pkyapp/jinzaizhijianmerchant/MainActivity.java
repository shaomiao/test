package com.pkyapp.jinzaizhijianmerchant;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.pkyapp.jinzaizhijianmerchant.data.AppAction;
import com.pkyapp.jinzaizhijianmerchant.data.ObjectCallback;
import com.pkyapp.jinzaizhijianmerchant.merchant.MerchantActivity;
import com.pkyapp.jinzaizhijianmerchant.util.ConstantUtil;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.JsonCallback;
import com.zhy.http.okhttp.entity.BaseJsonEntity;
import com.zhy.http.okhttp.network.ApiUrl;
import com.zhy.http.okhttp.utils.MD5Util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import okhttp3.Call;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void xxz(View view) {
        AppAction.getInstance().login("13674633874", "asdfgh", new JsonCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                Toast.makeText(MainActivity.this, "E", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onResponse(BaseJsonEntity response, int id) {
                Toast.makeText(MainActivity.this, response.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }

    public void xxhhh(View view) {
        AppAction.getInstance().orderList("28", "6fd69a98943ebb3fcb3363d8da148f79","0","1", new ObjectCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                Toast.makeText(MainActivity.this, "E", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onResponse(BaseJsonEntity response, int id) {
                Toast.makeText(MainActivity.this, response.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }
    public void xdfdfx(View view) {

        GregorianCalendar gcNew=new GregorianCalendar();
        gcNew.set(Calendar.MONTH, gcNew.get(Calendar.MONTH)-1);
        Date dtFrom=gcNew.getTime();

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String begin_time = dateFormat.format(new Date());
        String end_time = dateFormat.format(dtFrom);
        System.out.print(begin_time);
        System.out.print(end_time);
        AppAction.getInstance().statistics("28", "3857c9cc591415a5ba6a7ee306b15afe","2017-02-29","2017-03-30", new ObjectCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                Toast.makeText(MainActivity.this, "E", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onResponse(BaseJsonEntity response, int id) {
                Toast.makeText(MainActivity.this, response.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }

    public void xpx(View view) {

        AppAction.getInstance().shopOrderAdd("28", "e1a63acd7a1b0cac6eb3750f76fc88ce","3456","1", new ObjectCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                Toast.makeText(MainActivity.this, "E", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onResponse(BaseJsonEntity response, int id) {
                Toast.makeText(MainActivity.this, response.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }

    public void xxlkl(View view) {
        AppAction.getInstance().shopOrderDetal("28", "8265f2213166f7557b3d0f7a7c626da4","3455", new ObjectCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                Toast.makeText(MainActivity.this, "E", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onResponse(BaseJsonEntity response, int id) {
                Toast.makeText(MainActivity.this, response.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }

    // 获取物流信息
    public void xxll(View view) {
        AppAction.getInstance().logistrcsSeach("28", "3857c9cc591415a5ba6a7ee306b15afe", new ObjectCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                Toast.makeText(MainActivity.this, "E", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onResponse(BaseJsonEntity response, int id) {
                Toast.makeText(MainActivity.this, response.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }

    // 获取专题列表
    public void zz(View view) {
        Log.e("jj", "xx: ");
        AppAction.getInstance().xx(new ObjectCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                Toast.makeText(MainActivity.this, "E", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onResponse(BaseJsonEntity response, int id) {
                Toast.makeText(MainActivity.this, response.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    // 商品列表
    public void afxx(View view) {
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
                        Toast.makeText(MainActivity.this, "E", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onResponse(BaseJsonEntity response, int id) {
                        Toast.makeText(MainActivity.this, response.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }

    // 一级分类
    public void ggg(View view) {
        String url = ApiUrl.getInstance().getApi("yz_top_class");
        OkHttpUtils
                .post()
                .url(url)
                .addParams("app_key", MD5Util.getMD5Key(url))
                .build()
                .execute(new JsonCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Toast.makeText(MainActivity.this, "E", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onResponse(BaseJsonEntity response, int id) {
                        Toast.makeText(MainActivity.this, response.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }

    // 二级分类
    public void xxjkjk(View view) {
        String url = ApiUrl.getInstance().getApi("yz_top_class_thrre");
        OkHttpUtils
                .post()
                .url(url)
                .addParams("app_key", MD5Util.getMD5Key(url))
                .addParams("pid","2")
                .build()
                .execute(new JsonCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Toast.makeText(MainActivity.this, "E", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onResponse(BaseJsonEntity response, int id) {
                        Toast.makeText(MainActivity.this, response.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }

    // 商品详情
    public void ll(View view) {
        String url = ApiUrl.getInstance().getApi("good_desc");
        OkHttpUtils
                .post()
                .url(url)
                .addParams("app_key", MD5Util.getMD5Key(url))
                .addParams("good_id","27")
                .addParams("uid","0")
                .build()
                .execute(new JsonCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Toast.makeText(MainActivity.this, "E", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onResponse(BaseJsonEntity response, int id) {
                        Toast.makeText(MainActivity.this, response.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }

    // 动态
    public void kkkk(View view) {
        String url = ApiUrl.getInstance().getApi("dynamic_list");
        OkHttpUtils
                .post()
                .url(url)
                .addParams("app_key", MD5Util.getMD5Key(url))
                .addParams("duid","0")
                .addParams("uid","ab6bc976fb8ebf5a6432d7f2dc0e69f3")
                .build()
                .execute(new ObjectCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Toast.makeText(MainActivity.this, "E", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onResponse(BaseJsonEntity response, int id) {
                        Toast.makeText(MainActivity.this, response.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }
    // 地址{code='200', message='登陆成功', obj='{"list":[{"id":0,"name":"全部"},{"id":1,"name":"未确认"},{"id":2,"name":"代发货"},{"id":3,"name":"运输中"},{"id":4,"name":"已完成"}],"shop_order":[{"allprice":"30.00","freight":"0.00","id":"3455","message":" ","order_id":"13067070231919","order_stat":"2","time":"2017-03-30 10:42:33"},{"allprice":"3.00","freight":"10.00","id":"3456","message":" ","order_id":"130670702313","order_stat":"2","time":"2017-03-30 10:42:33"},{"allprice":"30.00","freight":"0.00","id":"3453","message":" ","order_id":"13067070150424","order_stat":"3","time":"2017-03-30 10:41:44"},{"allprice":"3.00","freight":"10.00","id":"3454","message":" ","order_id":"13067070150901","order_stat":"2","time":"2017-03-30 10:41:44"},{"allprice":"30.00","freight":"0.00","id":"3451","message":" ","order_id":"13067067514238","order_stat":"2","time":"2017-03-30 10:37:00"},{"allprice":"3.00","freight":"0.00","id":"3452","message":" ","order_id":"13067067514592","order_stat":"2","time":"2017-03-30 10:37:00"}],"user":{"is_stop":"1","pic":"http://192.168.1.10/jinzaizhijian/uploads/banner/shangdiancaocao.jpg","shop_id":"28","shop_name":"草草商店","shop_tel":"13112345678","shop_token":"70ea46b4f54935d12f518f52f87e6634"}}'}

    public void xx(View view) {
        String url = ApiUrl.getInstance().getApi("dynamic_list");
        OkHttpUtils
                .post()
                .url(url)
                .addParams("app_key", MD5Util.getMD5Key(url))
//                .addParams("duid","0")
                .addParams("uid","28")
                .addParams("session_key","70ea46b4f54935d12f518f52f87e6634")
                .addParams("type","4")
                .addParams("name","1")
                .addParams("tel","1")
                .addParams("address","1")
                .addParams("addressdesc","1")
                .addParams("lat","1")
                .addParams("lng","1")
                .build()
                .execute(new ObjectCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Toast.makeText(MainActivity.this, "E", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onResponse(BaseJsonEntity response, int id) {
                        Toast.makeText(MainActivity.this, response.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }



}
