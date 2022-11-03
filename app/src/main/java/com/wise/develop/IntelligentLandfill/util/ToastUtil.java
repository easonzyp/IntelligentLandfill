package com.wise.develop.IntelligentLandfill.util;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by zyp on 2019/8/20 0020.
 * class note:
 */

public class ToastUtil {

    public static void show(Context context, String text) {
        Toast.makeText(context,text,Toast.LENGTH_SHORT).show();
    }
}
