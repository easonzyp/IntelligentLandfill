package com.wise.develop.Landfill.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.wise.develop.Landfill.R;
import com.wise.develop.Landfill.base.BaseActivity;
import com.wise.develop.Landfill.constant.RouterPath;

@Route(path = RouterPath.TON_BAG_INFO_ACTIVITY)
public class TonBagInfoActivity extends BaseActivity {
    private RelativeLayout rl_container;
    private ImageView iv_back;
    private TextView tv_title;
    private TextView tv_bill_id;
    private TextView tv_waste_id;
    private TextView tv_state;
    private TextView tv_management_type;
    private TextView tv_weight;
    private TextView tv_location;
    private TextView tv_back_location;
    private TextView tv_create_time;
    private TextView tv_back;
    private TextView tv_confirm;

    @Override
    protected void initView() {
        rl_container = findViewById(R.id.rl_container);
        iv_back = findViewById(R.id.iv_back);
        tv_title = findViewById(R.id.tv_title);
        rl_container.setPadding(0, getStatusBarHeight(), 0, 0);
        tv_bill_id = findViewById(R.id.tv_bill_id);
        tv_waste_id = findViewById(R.id.tv_waste_id);
        tv_state = findViewById(R.id.tv_state);
        tv_management_type = findViewById(R.id.tv_management_type);
        tv_weight = findViewById(R.id.tv_weight);
        tv_location = findViewById(R.id.tv_location);
        tv_back_location = findViewById(R.id.tv_back_location);
        tv_create_time = findViewById(R.id.tv_create_time);
        tv_back = findViewById(R.id.tv_back);
        tv_confirm = findViewById(R.id.tv_confirm);
    }

    @Override
    protected void initClick() {
        tv_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                H5ServiceActivity.start(context,"http://www.baidu.com");
            }
        });
    }

    @Override
    protected void initData() {

    }

    @Override
    protected int setLayout() {
        return R.layout.activity_ton_bag_info;
    }

}