package com.wise.develop.Landfill.util;

import android.util.Log;

import com.wise.develop.Landfill.bean.HomeDayListBean;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class DateUtil {

    public static final String YYYY_MM_DD = "yyyy-MM-dd";
    public static final String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";
    public static final String HH_MM_SS = "HH:mm:ss";
    private static final int HOUR_SECOND = 60 * 60;
    private static final int MINUTE_SECOND = 60;

    public static String getCurrentTimeStr(String format) {
        return new SimpleDateFormat(format, Locale.getDefault()).format(new Date());
    }

    public static String dateToStr(String format, Date date) {
        return new SimpleDateFormat(format, Locale.getDefault()).format(date);
    }

    public static String getAppointTimeStr(String time, String format) {
        SimpleDateFormat form = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        Date date = new Date();
        try {
            date = form.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return new SimpleDateFormat(format, Locale.getDefault()).format(date);
    }

    public static String friendlyTime(String time) {
        SimpleDateFormat form = new SimpleDateFormat(YYYY_MM_DD_HH_MM_SS, Locale.getDefault());
        Date date = new Date();
        try {
            date = form.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        long timestamp = 0;
        if (date != null) {
            timestamp = date.getTime();
        }
        long currentSeconds = System.currentTimeMillis() / 1000;
        long timeGap = currentSeconds - timestamp / 1000;// 与现在时间相差秒数

        long toZero = currentSeconds / (24 * 60 * 60) * (24 * 60 * 60);
        long todayGap = currentSeconds - toZero;
        Log.e("TAG", "friendlyTime: " + timeGap);
        String timeStr = null;
        if (timeGap >= 24 * 60 * 60 || timeGap > todayGap) {// 1天以上
            timeStr = getStandardTimeWithDate(timestamp);
        } else if (timeGap >= 60 * 60 && timeGap < todayGap) {// 1小时-24小时
            timeStr = getStandardTimeWithHour(timestamp);
        } else if (timeGap >= 60 && timeGap < 3600) {// 1分钟-59分钟
            timeStr = timeGap / 60 + "分钟前";
        } else if (timeGap >= 0 && timeGap < 60) {// 1秒钟-59秒钟
            timeStr = "刚刚";
        }
        return timeStr;
    }

    public static boolean isShowTimeLabel(String time1, String time2) {
        try {
            SimpleDateFormat form = new SimpleDateFormat(YYYY_MM_DD_HH_MM_SS, Locale.getDefault());
            Date date1 = form.parse(time1);
            Date date2 = form.parse(time2);
            long timestamp1 = date1.getTime() / 1000;
            long timestamp2 = date2.getTime() / 1000;
            long timeGap = timestamp2 - timestamp1;
            // 5分钟
            return timeGap > 300;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static String getStandardTimeWithDate(long timestamp) {
        SimpleDateFormat sdf = new SimpleDateFormat("MM-dd HH:mm", Locale.getDefault());
        Date date = new Date(timestamp * 1000);
        return sdf.format(date);
    }

    public static String getStandardTimeWithHour(long timestamp) {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm", Locale.getDefault());
        Date date = new Date(timestamp * 1000);
        return sdf.format(date);
    }

    /**
     * 根据秒数获取时间串
     */
    public static String getTimeStrBySecond(int second) {
        if (second <= 0) {
            return "00:00";
        }
        int minutes = second / MINUTE_SECOND;
        if (minutes > 0) {
            second -= minutes * MINUTE_SECOND;
        }
        return (minutes >= 10 ? (minutes + "") : ("0" + minutes)) + ":" + (second >= 10 ? (second + "") : ("0" + second));
    }

    public static HomeDayListBean getPreOrNextDate(int distanceDay) {
        SimpleDateFormat month = new SimpleDateFormat("MM", Locale.getDefault());
        SimpleDateFormat day = new SimpleDateFormat("dd", Locale.getDefault());
        Date beginDate = new Date();
        Calendar date = Calendar.getInstance();
        date.setTime(beginDate);
        date.set(Calendar.DATE, date.get(Calendar.DATE) + distanceDay);
        return new HomeDayListBean(month.format(date.getTime()), day.format(date.getTime()));
    }
}
