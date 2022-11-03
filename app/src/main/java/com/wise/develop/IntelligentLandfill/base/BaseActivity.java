package com.wise.develop.IntelligentLandfill.base;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

/**
 * Activity基类
 * <p>
 * Created by zyp on 2018/2/6.
 */
public abstract class BaseActivity extends AppCompatActivity  {

    public Context context;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        initSystemStatusBar(0);

        super.onCreate(savedInstanceState);
        context = this;
        setContentView(setLayout());
        initIntentData();
        initView();
        initAdapter();
        initData();
        initClick();
    }

    protected void initSystemStatusBar(int status) {
        //5.x开始需要把颜色设置透明，否则导航栏会呈现系统默认的浅灰色
        Window window = getWindow();
        View decorView = window.getDecorView();
        //两个 flag 要结合使用，表示让应用的主体内容占用系统状态栏的空间
        int option;
        if (status == 1) {
            option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
        } else {
            option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;
        }
        decorView.setSystemUiVisibility(option);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(Color.TRANSPARENT);
    }

    public static int getStatusBarHeight() {
        int result = 0;
        try {
            int resourceId = Resources.getSystem().getIdentifier("status_bar_height", "dimen", "android");
            if (resourceId > 0) {
                result = Resources.getSystem().getDimensionPixelSize(resourceId);
            }
        } catch (Resources.NotFoundException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 初始化页面传值
     */
    protected void initIntentData() {
    }

    /**
     * 初始化组件
     */
    protected abstract void initView();

    /**
     * 设置列表adapter
     */
    protected void initAdapter() {
    }

    /**
     * 设置数据等逻辑代码
     */
    protected abstract void initData();

    /**
     * 设置点击事件
     */
    protected void initClick() {
    }

    /**
     * 绑定布局
     */
    protected abstract int setLayout();

    /**
     * Intent跳转
     */
    protected void toClass(Context context, Class<? extends Activity> clazz) {
        Intent intent = new Intent(context, clazz);
        startActivity(intent);
    }

    /**
     * Intent带值跳转
     */
    protected void toClass(Context context, Class<? extends Activity> clazz, Bundle bundle) {
        Intent intent = new Intent(context, clazz);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    /**
     * 带返回值的跳转
     */
    protected void toClass(Context context, Class<? extends Activity> clazz, int requestCode) {
        Intent intent = new Intent(context, clazz);
        startActivityForResult(intent, requestCode);
    }

    /**
     * 带返回值的跳转
     */
    protected void toClass(Context context, Class<? extends BaseActivity> clazz, Bundle bundle, int requestCode) {
        Intent intent = new Intent(context, clazz);
        intent.putExtras(bundle);
        startActivityForResult(intent, requestCode);
    }

    /**
     * 隐藏软件盘
     */
    public void hideSoftInput() {
        InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        if (getCurrentFocus() != null) {
            if (imm != null) {
                imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
            }
        }
    }

    /**
     * 显示软键盘
     */
    public void showInputMethod() {
        if (getCurrentFocus() != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            if (imm != null) {
                imm.showSoftInputFromInputMethod(getCurrentFocus().getWindowToken(), 0);
            }
        }
    }

    public boolean checkPermission(@NonNull String permission) {
        return ActivityCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED;
    }
}