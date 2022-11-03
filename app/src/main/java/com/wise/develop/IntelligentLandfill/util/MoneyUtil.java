package com.wise.develop.IntelligentLandfill.util;

import android.annotation.SuppressLint;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import java.math.BigDecimal;
import java.text.DecimalFormat;

/**
 * 金钱格式输入相关
 * Created by zyp on 2016/11/9.
 */
public class MoneyUtil {
    private static DecimalFormat df = new DecimalFormat("#######0.00");

    public static void setPricePoint(final EditText editText) {

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.toString().contains(".")) {
                    if (s.length() - 1 - s.toString().indexOf(".") > 2) {
                        s = s.toString().subSequence(0,
                                s.toString().indexOf(".") + 3);
                        editText.setText(s);
                        editText.setSelection(s.length());
                    }
                }
                if (s.toString().trim().equals(".")) {
                    s = "0" + s;
                    editText.setText(s);
                    editText.setSelection(2);
                }
                if (s.toString().startsWith("0") && s.toString().trim().length() > 1) {
                    if (!s.toString().substring(1, 2).equals(".")) {
                        editText.setText(s.subSequence(0, 1));
                        editText.setSelection(1);
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable edt) {
                String temp = edt.toString();
                int posDot = temp.indexOf(".");//返回指定字符在此字符串中第一次出现处的索引
                int index = editText.getSelectionStart();//获取光标位置
                if (posDot < 0) {//不包含小数点
                    if (temp.length() <= 6) {
                        return;//小于x位数直接返回
                    } else {
                        edt.delete(index-1, index);//删除光标前的字符
                        return;
                    }
                }
                if (posDot > 6) {//小数点前大于x位数就删除光标前一位
                    edt.delete(index-1, index);//删除光标前的字符
                }
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }
        });
    }

    public static String doubleTrans(double num) {
        if (num % 1.0 == 0) {
            return String.valueOf((int) num);
        }
        return String.valueOf(num);
    }

    public static String formatMoney(long money) {
        BigDecimal d = BigDecimal.valueOf(money).divide(new BigDecimal(100),6, BigDecimal.ROUND_HALF_UP);
        String moneyStr = "0";
        try {
            moneyStr = MoneyUtil.doubleTrans(Double.parseDouble(df.format(d)));
        } catch (Exception ignored) {

        }
        return moneyStr;
    }

    public static String formatMoneyMarker(long money) {
        return "¥ " + formatMoney(money);
    }

    public static String formatPrice(long money){
        String f = null;
        if (money%100 == 0){
            Float f1 = Float.valueOf(money/100);
            @SuppressLint("DefaultLocale")
            String format = String.format("%.2f", f1);
            f = format;
        }else {
            f = (float)money/100+"";
        }
        return f;
    }

    /**
     * 将分为单位的转换为元并返回金额格式的字符串 （除100）
     *
     * @param amount
     * @return
     * @throws Exception
     */
    public static String changeF2Y(Long amount){

        int flag = 0;
        String amString = amount.toString();
        if (amString.charAt(0) == '-') {
            flag = 1;
            amString = amString.substring(1);
        }
        StringBuffer result = new StringBuffer();
        if (amString.length() == 1) {
            result.append("0.0").append(amString);
        } else if (amString.length() == 2) {
            result.append("0.").append(amString);
        } else {
            String intString = amString.substring(0, amString.length() - 2);
            for (int i = 1; i <= intString.length(); i++) {
//                if ((i - 1) % 3 == 0 && i != 1) {
//                    result.append(",");
//                }
                result.append(intString.substring(intString.length() - i, intString.length() - i + 1));
            }
            result.reverse().append(".").append(amString.substring(amString.length() - 2));
        }
        if (flag == 1) {
            return "-" + result.toString();
        } else {
            return result.toString();
        }
    }
}
