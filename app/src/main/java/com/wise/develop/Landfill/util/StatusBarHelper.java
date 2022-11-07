package com.wise.develop.Landfill.util;

import android.app.Activity;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Build;
import android.os.Environment;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Properties;

/**
 * 工具类
 * Created by SmileXie on 2017/6/29.
 */

public final class StatusBarHelper {

    public Integer statusBarHeight = null;
    public Integer navigationHeight = null;


    public static StatusBarHelper getInstance() {
        return InnerInstance.INSTANCE;
    }

    private static class InnerInstance {
        private static StatusBarHelper INSTANCE = new StatusBarHelper();
    }

    public StatusBarHelper init(Activity activity) {

        getStatusBarHeight(activity);
        getNavigationBarHeight(activity);

        return this;
    }

    public void setStatusBarPadding(Activity activity, View view) {
        int h = StatusBarHelper.getInstance().getStatusBarHeight(activity);
        view.setPadding(view.getPaddingLeft(), view.getPaddingTop() + h, view.getPaddingRight(), view.getPaddingBottom());
    }

    /**
     * 通过反射的方式获取状态栏高度
     *
     * @return
     */
    public int getStatusBarHeight(Activity activity) {
        if (statusBarHeight != null) return statusBarHeight;
        if (activity == null) return 0;
        try {
            Class<?> c = Class.forName("com.android.internal.R$dimen");
            Object obj = c.newInstance();
            Field field = c.getField("status_bar_height");
            int x = Integer.parseInt(field.get(obj).toString());
            statusBarHeight = activity.getResources().getDimensionPixelSize(x);
        } catch (Exception e) {
            e.printStackTrace();
            statusBarHeight = 0;
        }
        return statusBarHeight;
    }


    /**
     * 获取底部导航栏高度
     *
     * @return
     */
    public int getNavigationBarHeight(Activity activity) {

        if (navigationHeight != null) return navigationHeight;
        if (activity == null) return 0;

        if (!checkDeviceHasNavigationBar(activity)) {
            navigationHeight = 0;
            return navigationHeight;
        }

        Resources resources = activity.getResources();
        int resourceId = resources.getIdentifier("navigation_bar_height", "dimen", "android");
        //获取NavigationBar的高度
        navigationHeight = resources.getDimensionPixelSize(resourceId);

        //部分机器不需要这个高度设置为0 就可以正常展示。
        navigationHeight = 0;

        return navigationHeight;
    }

    //获取是否存在NavigationBar
    private boolean checkDeviceHasNavigationBar(Activity activity) {
        boolean hasNavigationBar = false;
        Resources rs = activity.getResources();
        int id = rs.getIdentifier("config_showNavigationBar", "bool", "android");
        if (id > 0) {
            hasNavigationBar = rs.getBoolean(id);
        }
        try {
            Class systemPropertiesClass = Class.forName("android.os.SystemProperties");
            Method m = systemPropertiesClass.getMethod("get", String.class);
            String navBarOverride = (String) m.invoke(systemPropertiesClass, "qemu.hw.mainkeys");
            if ("1".equals(navBarOverride)) {
                hasNavigationBar = false;
            } else if ("0".equals(navBarOverride)) {
                hasNavigationBar = true;
            }
        } catch (Exception e) {
        }
        return hasNavigationBar;
    }


    /**
     * @param useThemeStatusBarColor 是否要状态栏的颜色，不设置则为透明色
     * @param orOtherColor           不透明时颜色
     */
    public void setStatusBar(Activity activity, boolean useThemeStatusBarColor, int orOtherColor) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {//5.0及以上
            activity.getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            activity.getWindow().setStatusBarColor(useThemeStatusBarColor ? orOtherColor : Color.TRANSPARENT);
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {//4.4到5.0
            WindowManager.LayoutParams localLayoutParams = activity.getWindow().getAttributes();
            localLayoutParams.flags = (WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS | localLayoutParams.flags);
        }
    }

    /**
     * 设置状态栏文字色值为深色调
     *
     * @param useDart 是否使用深色调
     */
    public void setStatusTextColor(Activity activity, boolean useDart) {
        if (isFlyme()) {
            processFlyMe(activity, useDart);
            return;
        }
        if (isMIUI()) {
            processMIUI(activity, useDart);
            return;
        }

        if (useDart) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                activity.getWindow().getDecorView().setSystemUiVisibility( View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            }
        } else {
            activity.getWindow().getDecorView().setSystemUiVisibility( View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
        }
        activity.getWindow().getDecorView().findViewById(android.R.id.content).setPadding(0, 0, 0, navigationHeight);
    }

    /**
     * 判断手机是否是小米
     *
     * @return
     */
    private Boolean miui = null;

    public boolean isMIUI() {
        if (miui != null) return miui;
        try {
            Properties properties = new Properties();
            properties.load(new FileInputStream(new File(Environment.getRootDirectory(), "build.prop")));

            miui = properties.getProperty("ro.miui.ui.version.code", null) != null
                    || properties.getProperty("ro.miui.ui.version.name", null) != null
                    || properties.getProperty("ro.miui.internal.storage", null) != null;
        } catch (final IOException e) {
            miui = false;
        }
        return miui;
    }

    /**
     * 判断手机是否是魅族
     *
     * @return
     */
    private Boolean flyme = null;

    public boolean isFlyme() {
        if (flyme != null) return flyme;
        try {
            // Invoke Build.hasSmartBar()
            final Method method = Build.class.getMethod("hasSmartBar");
            flyme = method != null;
        } catch (final Exception e) {
            flyme = false;
        }
        return flyme;
    }

    /**
     * 改变魅族的状态栏字体为黑色，要求FlyMe4以上
     */
    private void processFlyMe(Activity activity, boolean isLightStatusBar) {
        WindowManager.LayoutParams lp = activity.getWindow().getAttributes();
        try {
            Class<?> instance = Class.forName("android.view.WindowManager$LayoutParams");
            int value = instance.getDeclaredField("MEIZU_FLAG_DARK_STATUS_BAR_ICON").getInt(lp);
            Field field = instance.getDeclaredField("meizuFlags");
            field.setAccessible(true);
            int origin = field.getInt(lp);
            if (isLightStatusBar) {
                field.set(lp, origin | value);
            } else {
                field.set(lp, (~value) & origin);
            }
        } catch (Exception ignored) {
            ignored.printStackTrace();
        }
    }

    /**
     * 改变小米的状态栏字体颜色为黑色, 要求MIUI6以上  lightStatusBar为真时表示黑色字体
     */
    private void processMIUI(Activity activity, boolean lightStatusBar) {
        Class<? extends Window> clazz = activity.getWindow().getClass();
        try {
            int darkModeFlag;
            Class<?> layoutParams = Class.forName("android.view.MiuiWindowManager$LayoutParams");
            Field field = layoutParams.getField("EXTRA_FLAG_STATUS_BAR_DARK_MODE");
            darkModeFlag = field.getInt(layoutParams);
            Method extraFlagField = clazz.getMethod("setExtraFlags", int.class, int.class);
            extraFlagField.invoke(activity.getWindow(), lightStatusBar ? darkModeFlag : 0, darkModeFlag);
        } catch (Exception ignored) {
            ignored.printStackTrace();
        }
    }
}
