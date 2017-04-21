package com.pkyapp.jinzaizhijianmerchant;

import com.pkyapp.jinzaizhijianmerchant.app.WXApplication;
import com.pkyapp.jinzaizhijianmerchant.data.AppAction;
import com.zhy.http.okhttp.callback.JsonCallback;
import com.zhy.http.okhttp.entity.BaseJsonEntity;

import org.junit.Test;

import okhttp3.Call;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void xx (){
        final boolean[] flag = {false};
        AppAction.getInstance().orderList("28","9ab0725c558e04f84d90f8c149a02ebb","0",new JsonCallback(){

            @Override
            public void onError(Call call, Exception e, int id) {
                System.out.print("cuowu");

                assertEquals(true, flag[0]);
            }

            @Override
            public void onResponse(BaseJsonEntity response, int id) {
                System.out.print("成功");
                flag[0] = true;
                assertEquals(true, flag[0]);
            }
        });
    }


}