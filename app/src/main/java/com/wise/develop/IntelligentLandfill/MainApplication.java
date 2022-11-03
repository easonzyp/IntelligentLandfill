package com.wise.develop.IntelligentLandfill;

import android.app.Application;
import android.content.SharedPreferences;

import androidx.annotation.NonNull;

import com.alibaba.android.arouter.launcher.ARouter;

public class MainApplication extends Application {
    private static MainApplication application;
    private SharedPreferences preferences = null;
    public final static String TOKEN = "token";
    public final static String ROLE_NAME = "roleName";
    public final static String IS_SAFE = "isSafe";
    public final static String DEP_USER = "dep_user";
    public final static String HIDDEN_LEVEL = "hidden_level";
    public final static String HIDDEN_TYPE = "hidden_type";

    public final static String IS_TOAST = "isToast";
    public final static String USER_NAME = "userName";
    public final static String USER_ID = "userId";
    public final static String PASSWORD = "password";

    @Override
    public void onCreate() {
        super.onCreate();
        application = this;
        ARouter.init(this);
        preferences = getSharedPreferences("ShenBao", MODE_PRIVATE);
    }

    public static MainApplication getContext() {
        return application;
    }

    public String getToken() {
        return preferences.getString(TOKEN, "");
    }

    public void setToken(@NonNull String token) {
        preferences.edit().putString(TOKEN, token).apply();
    }
}