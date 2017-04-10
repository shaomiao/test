package com.project.addresstest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.project.addresstest.adapter.AddressAdapter;
import com.project.addresstest.entity.Address;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    private RecyclerView mAddressListRv;

    private TextView mAddAddressTv;

    private AddressAdapter mAdapter;

    private List<Object> mData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAddAddressTv = (TextView) findViewById(R.id.add_address_tv);
        mAddressListRv = (RecyclerView) findViewById(R.id.address_rv);


        mAddressListRv.setLayoutManager(new LinearLayoutManager(this));
        mData = new ArrayList<>();
        for (int i = 1; i <10; i ++) {
            mData.add(new Address("王先生"+i, "13637366869", "黑龙江哈尔滨道外区", "vgfghfhgfhfhffytftyftf景阳街2单元bhgjhgjgjhg2808"));
        }
        mAdapter = new AddressAdapter(this, mData);
        mAddressListRv.setAdapter(mAdapter);

        mAddAddressTv.setOnClickListener(this);
    }



    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            // 添加地址
            case R.id.add_address_tv:
                startActivity(new Intent(this,AddAddressActivity.class));
                break;
        }
    }
}
