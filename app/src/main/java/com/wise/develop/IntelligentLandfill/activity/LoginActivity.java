package com.wise.develop.IntelligentLandfill.activity;

import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.wise.develop.IntelligentLandfill.R;
import com.wise.develop.IntelligentLandfill.base.BaseActivity;
import com.wise.develop.IntelligentLandfill.base.BaseObserver;
import com.wise.develop.IntelligentLandfill.bean.LoginBean;
import com.wise.develop.IntelligentLandfill.http.HttpAction;
import com.wise.develop.IntelligentLandfill.util.ToastUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by zyp on 2019/8/20 0020.
 * class note:登陆
 */

public class LoginActivity extends BaseActivity {

    private EditText et_username;
    private EditText et_password;
    private Button btn_login;
    private TextView tv_register;

    @Override
    protected void initView() {
        et_username = findViewById(R.id.et_username);
        et_password = findViewById(R.id.et_password);
        btn_login = findViewById(R.id.btn_login);
        tv_register = findViewById(R.id.tv_register);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initClick() {
        btn_login.setOnClickListener(v -> {
//            login()
            toClass(context, MainActivity.class);
            finish();
        });

        tv_register.setOnClickListener(v -> {

        });
    }

    private void login() {
        String username = et_username.getText().toString().trim();
        if (TextUtils.isEmpty(username)) {
            ToastUtil.show(this, "用户名不能为空");
            return;
        }

        String password = et_password.getText().toString().trim();
        if (TextUtils.isEmpty(password)) {
            ToastUtil.show(this, "密码不能为空");
            return;
        }

        Map<String, String> params = new HashMap<>();
        params.put("username", username);
        params.put("password", password);
        HttpAction.getInstance().login(params).subscribe(new BaseObserver<LoginBean>() {
            @Override
            public void onSuccess(LoginBean bean) {
                gotoMain(bean, username, password);
            }

            @Override
            public void onError(String message) {
                ToastUtil.show(context, message);
            }
        });
    }

    private void gotoMain(LoginBean bean, String username, String password) {
//        MainApplication.getContext().setToken(bean.getData().getTokenValue());

        toClass(context, MainActivity.class);
        finish();
    }

    @Override
    protected int setLayout() {
        return R.layout.activity_login;
    }
}