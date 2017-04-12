package com.project.orderdetail;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.project.orderdetail.adapter.OrderDetalAdapter;
import com.project.orderdetail.entity.OrderDetail;
import com.project.orderdetail.entity.Product;
import com.project.orderdetail.entity.Shop;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private TextView mOrderCodeTv, mOrderStateTv, mOrderDate;

    private TextView mShopNameTv;

    private TextView mFreightTv, mTotal, mMessage;

    private LinearLayout mProcess;

    private Button mButton;

    private RecyclerView mRecyclerView;

    private OrderDetalAdapter adapter;

    private String mOrderState;

    private OrderDetail orderDetail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        initData();


    }

    private void initView() {
        mOrderCodeTv = (TextView) findViewById(R.id.order_code_tv);
        mOrderStateTv = (TextView) findViewById(R.id.order_state_tv);
        mOrderDate = (TextView) findViewById(R.id.order_date_tv);
        mShopNameTv = (TextView) findViewById(R.id.shop_name_tv);
        mFreightTv = (TextView) findViewById(R.id.freight_tv);
        mTotal = (TextView) findViewById(R.id.total_tv);
        mMessage = (TextView) findViewById(R.id.message_tv);
        mProcess = (LinearLayout) findViewById(R.id.process_ll);
        mButton = (Button) findViewById(R.id.button);
        mRecyclerView = (RecyclerView) findViewById(R.id.product_list_rv);
        // TODO 根据订单状态修改按钮显示
    }

    private void initData() {
        initOrderDetal();

        adapter = new OrderDetalAdapter(orderDetail.getProducts());
        mRecyclerView.setAdapter(adapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        mRecyclerView.setFocusable(false);

        mOrderCodeTv.setText(orderDetail.getOrderCode());
        mOrderDate.setText(orderDetail.getOrderDate());
        mOrderStateTv.setText(orderDetail.getOrderState());
        mFreightTv.setText(orderDetail.getFreight());
        mShopNameTv.setText(orderDetail.getShop().getShopName());
    }

    private void initOrderDetal() {
        orderDetail = new OrderDetail();
        orderDetail.setOrderCode("263598546223");
        orderDetail.setOrderState("已发货");
        orderDetail.setOrderDate("2017-03-03 12:20");
        orderDetail.setFreight("20.00");
        orderDetail.setMessage("我喜欢白色的静版");
        orderDetail.setOrderTotal("200.00");

        Shop shop = new Shop();
        shop.setShopName("花花公子专卖店");
        orderDetail.setShop(shop);

        Product product = new Product();
        product.setProductName("花花公子 T恤 男 2017春装新款衣服男装fdfdjlfkdjlfdl");
        product.setProductNum("1");
        product.setProductPrice("90.00");
        product.setProductTag("花花公子男装");

        Product product1 = new Product();
        product1.setProductName("花花公子 T恤 男 2017春装新款衣服男装fdfdjlfkdjlfdl");
        product1.setProductNum("2");
        product1.setProductPrice("90.00");
        product1.setProductTag("花花公子男装");
        List<Product> products = new ArrayList<>();
        products.add(product);
        products.add(product1);

        Product product2 = new Product();
        product2.setProductName("花花公子 T恤 男 2017春装新款衣服男装fdfdjlfkdjlfdl");
        product2.setProductNum("2");
        product2.setProductPrice("90.00");
        product2.setProductTag("花花公子男装");
        products.add(product2);
        products.add(new Product("dfdsfsd","dfdsfds","sdfdsfsd","dfdsfsd","dffgfdgfd"));
        products.add(new Product("dfdsfsd2","dfdsfds","sdfdsfsd","dfdsfsd","dffgfdgfd"));
        products.add(new Product("dfdsfsd3","dfdsfds","sdfdsfsd","dfdsfsd","dffgfdgfd"));
        products.add(new Product("dfdsfsd4","dfdsfds","sdfdsfsd","dfdsfsd","dffgfdgfd"));

        orderDetail.setProducts(products);
    }
}
