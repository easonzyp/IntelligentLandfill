package com.wise.develop.Landfill.dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import com.wise.develop.Landfill.R;

import androidx.annotation.NonNull;

/**
 * Created by zyp on 2019/8/28 0028.
 * class note:
 */

public class CustomDialog extends Dialog {
    private int style;
    private int gravity;

    //    style引用style样式
    public CustomDialog(Context context, int style, int gravity) {
        super(context, R.style.DialogTheme);
        this.style = style;
        this.gravity = gravity;
    }

    @Override
    public void setContentView(@NonNull View view) {
        super.setContentView(view);
    }

    @Override
    public void show() {
        super.show();
        Window window = getWindow();
        if (window == null) {
            return;
        }

        WindowManager.LayoutParams params = window.getAttributes();
        params.gravity = gravity;
        params.width = ViewGroup.LayoutParams.MATCH_PARENT;

        if (style != 0) {
            window.setWindowAnimations(style);
        }
        window.setAttributes(params);
    }
}
