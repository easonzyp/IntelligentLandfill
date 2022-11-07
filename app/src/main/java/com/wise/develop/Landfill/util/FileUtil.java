package com.wise.develop.Landfill.util;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;

import com.luck.picture.lib.entity.LocalMedia;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class FileUtil {


    public static long getFileSize(File file) {
        long size = 0;
        if (file.exists()) {
            size = file.length();
        }
        return size;
    }

    public static String getPath(LocalMedia localMedia) {
        String path;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            path = localMedia.getAndroidQToPath();
        } else {
            path = localMedia.getPath();
        }
        return path;
    }

    public static String getCutPath(LocalMedia localMedia) {
        String path;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            path = localMedia.getAndroidQToPath();
        } else {
            path = localMedia.getCutPath();
        }
        return path;
    }

    public static String getCompressPath(LocalMedia localMedia) {
        String path;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            path = localMedia.getAndroidQToPath();
        } else {
            path = localMedia.getCompressPath();
        }
        return path;
    }

    //删除文件夹和文件夹里面的文件
    public static void deleteDirWihtFile(File dir) {
        if (dir == null || !dir.exists() || !dir.isDirectory())
            return;
        for (File file : dir.listFiles()) {
            if (file.isFile())
                file.delete(); // 删除所有文件
            else if (file.isDirectory())
                deleteDirWihtFile(file); // 递规的方式删除文件夹
        }
        dir.delete();// 删除目录本身
    }

    public static Bitmap urlToBitmap(String imgUrl) {
        URL fileUrl = null;
        Bitmap bitmap = null;

        try {
            fileUrl = new URL(imgUrl);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        try {
            HttpURLConnection conn = (HttpURLConnection) fileUrl.openConnection();
            conn.setDoInput(true);
            conn.connect();
            InputStream is = conn.getInputStream();
            bitmap = BitmapFactory.decodeStream(is);
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bitmap;
    }
}
