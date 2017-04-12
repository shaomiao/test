package com.project.circlefriends;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.project.circlefriends.dynamic.DynamicFragment;

public class MainActivity extends AppCompatActivity {

    private DynamicFragment dynamicFragment;
    private FragmentManager fm;
    private FragmentTransaction ft;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void xx(View view) {
        fm = getFragmentManager();
        ft = fm.beginTransaction();

        hideFragments(ft);
        if (dynamicFragment == null) {
            dynamicFragment = new DynamicFragment();
            ft.add(R.id.layFrame, dynamicFragment);
        } else {
            ft.show(dynamicFragment);
        }
        ft.commit();
    }

    /**
     * 将所有的Fragment都置为隐藏状态。
     *
     * @param transaction 用于对Fragment执行操作的事务
     */
    private void hideFragments(FragmentTransaction transaction) {

        if (dynamicFragment != null) {
            transaction.hide(dynamicFragment);
        }
    }

}
