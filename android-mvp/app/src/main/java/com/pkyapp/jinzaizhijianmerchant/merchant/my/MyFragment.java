package com.pkyapp.jinzaizhijianmerchant.merchant.my;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.pkyapp.jinzaizhijianmerchant.R;
import com.pkyapp.jinzaizhijianmerchant.app.WXApplication;
import com.pkyapp.jinzaizhijianmerchant.ui.CustomProgressDialog;

import org.w3c.dom.Text;

import de.hdodenhof.circleimageview.CircleImageView;

import static com.pkyapp.jinzaizhijianmerchant.app.WXApplication.context;

/**
 * @author shaomiao
 * @Date 2017/3/23
 * @Time 16:16
 */

public class MyFragment extends Fragment implements View.OnClickListener {

    private TextView mShopName,mShopNameTitie;

    private CircleImageView mPicImage;

    private Button mLoginButton;

    private CustomProgressDialog mDialog;

    public static MyFragment newInstance() {
        return new MyFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_my,container,false);
        TextView withdrawalsTv = (TextView) root.findViewById(R.id.withdrawals_tv);
        LinearLayout clearCacheLl = (LinearLayout) root.findViewById(R.id.clear_cache_ll);
        mShopName = (TextView) root.findViewById(R.id.shop_name);
        mShopNameTitie = (TextView) root.findViewById(R.id.shopNameTv);

        mPicImage = (CircleImageView) root.findViewById(R.id.profile_image);

        mLoginButton = (Button) root.findViewById(R.id.loginOutBt);
        mDialog = new CustomProgressDialog(getContext());

        mShopName.setText(WXApplication.ContastPF.readShopName());
        mShopNameTitie.setText(WXApplication.ContastPF.readShopName());

        Glide.with(context)
                .load(WXApplication.ContastPF.readShopPic())
                .error(R.mipmap.indphoto)
                .placeholder(R.mipmap.indphoto)
                .into(mPicImage);

        withdrawalsTv.setOnClickListener(this);
        clearCacheLl.setOnClickListener(this);
        mLoginButton.setOnClickListener(this);
        return root;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.withdrawals_tv:
                Toast.makeText(getActivity(), "我是提现", Toast.LENGTH_SHORT).show();
                break;
            case R.id.clear_cache_ll:
                mDialog.show();
                final boolean isRunning = true;
                Thread mThread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        while (isRunning) {
                            try {
                                Thread.sleep(3000);
                                mDialog.hide();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }
                });
                mThread.start();
                break;
            case R.id.loginOutBt:
                // TODO 退出登录 选择框
                mDialog.show();
                WXApplication.ContastPF.clearData();
                WXApplication.getInstance().loginoutStartLogin();
                mDialog.hide();
                break;
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mDialog.dismiss();
    }
}
