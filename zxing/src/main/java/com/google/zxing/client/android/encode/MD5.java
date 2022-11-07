package com.google.zxing.client.android.encode;

import android.text.TextUtils;
import android.util.Base64;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5 {
    public static String en(String string) {
        if (TextUtils.isEmpty(string)) {
            return "";
        }
        MessageDigest md5;
        try {
            md5 = MessageDigest.getInstance("MD5");
            byte[] bytes = md5.digest(string.getBytes());
            StringBuilder result = new StringBuilder();
            for (byte b : bytes) {
                String temp = Integer.toHexString(b & 0xff);
                if (temp.length() == 1) {
                    temp = "0" + temp;
                }
                result.append(temp);
            }
            return result.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static String getBase64(String str){
        String result = "";
        if (str != null) {
            result = new String(Base64.encode(str.getBytes(StandardCharsets.UTF_8), Base64.NO_WRAP), StandardCharsets.UTF_8);
        }
        return result;
    }

    //解密
    public static String getFromBase64(String str){
        String result = "";
        if (str != null) {
            result = new String(Base64.decode(str, Base64.NO_WRAP), StandardCharsets.UTF_8);
        }
        return result;
    }
}
