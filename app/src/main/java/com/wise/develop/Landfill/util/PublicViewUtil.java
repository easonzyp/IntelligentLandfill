package com.wise.develop.Landfill.util;

import android.app.Activity;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;

/**
 * Created by zyp on 2018/8/6 0006.
 *
 */

public class PublicViewUtil {
    public static AlphaAnimation showAction() {
        AlphaAnimation mShowAction = new AlphaAnimation(0.0f, 1.0f);
        mShowAction.setDuration(100);
        return mShowAction;
    }
    public static AlphaAnimation hiddenAction() {
        AlphaAnimation mHiddenAction = new AlphaAnimation(1.0f, 0.0f);
        mHiddenAction.setDuration(100);
        return mHiddenAction;
    }
    /**
     * popwin背景
     */
    public static void backgroundAlpha(Activity activity , float bgAlpha) {
        Window window = activity.getWindow();
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.alpha = bgAlpha; //0.0-1.0
        window.setAttributes(lp);
    }
}
