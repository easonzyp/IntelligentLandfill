package com.wise.develop.Landfill.activity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;

import com.wise.develop.Landfill.MainApplication;
import com.wise.develop.Landfill.R;
import com.wise.develop.Landfill.base.BaseActivity;
import com.wise.develop.Landfill.util.ToastUtil;


/**
 * Created by zyp on 2019/8/20 0020.
 * class note:欢迎页面
 */

public class WelcomeActivity extends BaseActivity {

    private final String[] permissionList = {Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.RECORD_AUDIO, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE};

    @Override
    protected void initView() {
        initPermission();
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initClick() {

    }

    private void gotoActivity() {
        if (TextUtils.isEmpty(MainApplication.getContext().getToken())) {
            toClass(context, LoginActivity.class);
        } else {
            toClass(context, MainActivity.class);
        }
        finish();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                // 判断用户是否 点击了不再提醒。(检测该权限是否还可以申请)
                if (shouldShowRequestPermissionRationale(permissions[0])) {
                    // 循环申请权限
                    initPermission();
                } else {
                    ToastUtil.show(context, "请给与app相应权限，否则将无法正常使用");
                    finish();
                }
            } else {
                delayed();
            }
        }
    }

    private void initPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkPermission(permissionList[0]) || checkPermission(permissionList[1])) {
                ActivityCompat.requestPermissions(this, permissionList, 1);
            } else {
                delayed();
            }
        } else {
            delayed();
        }
    }

    private void delayed(){
        new Handler(Looper.getMainLooper()).postDelayed(this::gotoActivity, 2000);
    }

    @Override
    protected int setLayout() {
        return R.layout.activity_welcome;
    }
}