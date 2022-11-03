package com.wise.develop.IntelligentLandfill.dialog;

import android.app.ProgressDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import com.wise.develop.IntelligentLandfill.R;

public class WaitUI {
    private static ProgressDialog progressDialog = null;

    public static void show(Context context) {
        show(context, "加载中...");
    }

    public static void show(Context context, String message) {
        if (progressDialog == null) {
            // 创建ProgressDialog对象
            progressDialog = new ProgressDialog(context, R.style.CustomDialog);
            progressDialog.setCancelable(false);
            progressDialog.show();
            View loading = LayoutInflater.from(context).inflate(R.layout.loading, null);

            TextView  tv_message = loading.findViewById(R.id.tv_message);
            tv_message.setText(message);
            progressDialog.setContentView(loading);
        }
    }

    public static void cancel() {
        if (progressDialog != null) {
            progressDialog.cancel();
            progressDialog = null;
        }
    }
}
