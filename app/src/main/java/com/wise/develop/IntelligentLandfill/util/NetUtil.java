package com.wise.develop.IntelligentLandfill.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class NetUtil {
    public static boolean isNetwork(Context context) {
        //通过getSystemService()方法得到connectionManager这个系统服务类，专门用于管理网络连接
        ConnectivityManager connectionManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectionManager.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isAvailable();
    }
}