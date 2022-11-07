package com.wise.develop.Landfill.activity;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.wise.develop.Landfill.R;
import com.wise.develop.Landfill.base.BaseActivity;


/**
 * Created by zyp on 2019/8/20 0020.
 * class note:欢迎页面
 */

public class TestActivity extends BaseActivity {

    @Override
    protected void initView() {
        EditText editText = findViewById(R.id.et_input);
        Button button = findViewById(R.id.btn_commit);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                H5ServiceActivity.start(context, editText.getText().toString());
            }
        });
    }

    @Override
    protected void initData() {

    }

    @Override
    protected int setLayout() {
        return R.layout.activity_test;
    }
}