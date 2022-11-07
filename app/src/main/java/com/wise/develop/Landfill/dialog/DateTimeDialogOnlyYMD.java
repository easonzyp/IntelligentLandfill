package com.wise.develop.Landfill.dialog;

import android.app.AlertDialog;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.LinearLayout;

import com.wise.develop.Landfill.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class DateTimeDialogOnlyYMD extends AlertDialog implements View.OnClickListener {
    private DatePicker mDatePicker;
    private MyOnDateSetListener onDateSetListener;

    // 是否 显示 日选择器   true 显示 ，false 隐藏
    private boolean isDayVisible = true;
    // 是否 显示 月选择器   true 显示 ，false 隐藏
    private boolean isMonthVisible = true;
    // 是否 显示 年选择器   true 显示 ，false 隐藏
    private boolean isYearVisible = true;

    private SimpleDateFormat mFormatter = new SimpleDateFormat("yyyy-MM-dd");

    protected DateTimeDialogOnlyYMD(Context context) {
        super(context);
    }

    protected DateTimeDialogOnlyYMD(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    /**
     * @param context        上下文对象
     * @param isDayVisible   日 是否可见
     * @param isMonthVisible 月 是否可见
     * @param isYearVisible  年 是否可见
     */
    public DateTimeDialogOnlyYMD(Context context, boolean isDayVisible, boolean isMonthVisible, boolean isYearVisible) {
        super(context);
        this.isDayVisible = isDayVisible;
        this.isMonthVisible = isMonthVisible;
        this.isYearVisible = isYearVisible;
        init();
    }

    private void init() {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.view_date_picker_dialog, null);
        setView(view);
        mDatePicker = view.findViewById(R.id.datePicker);
        LinearLayout buttonGroup = view.findViewById(R.id.buttonGroup);
        Button cancelButton = view.findViewById(R.id.cancelButton);
        Button okButton = view.findViewById(R.id.okButton);

        Calendar mCalendar = Calendar.getInstance();
        mCalendar.setTime(new Date());

        cancelButton.setOnClickListener(this);
        okButton.setOnClickListener(this);

        // 是否 显示 年
        if (!this.isYearVisible) {
            ((ViewGroup) ((ViewGroup) mDatePicker.getChildAt(0)).getChildAt(0)).getChildAt(0).setVisibility(View.GONE);
        }

        // 是否 显示 月
        if (!this.isMonthVisible) {
            ((ViewGroup) ((ViewGroup) mDatePicker.getChildAt(0)).getChildAt(0)).getChildAt(1).setVisibility(View.GONE);
        }

        // 是否 显示 日
        if (!this.isDayVisible) {
            ((ViewGroup) ((ViewGroup) mDatePicker.getChildAt(0)).getChildAt(0)).getChildAt(2).setVisibility(View.GONE);
        }

        // 设置 显示 宽高
        int width = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        int height = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);

        buttonGroup.measure(width, height);
        mDatePicker.measure(width, height);
    }


    public void hideOrShow() {
        if (!this.isShowing()) {
            this.show();
            //设置 显示 的 宽高
            WindowManager.LayoutParams attributes = this.getWindow().getAttributes();
            attributes.width = ViewGroup.LayoutParams.MATCH_PARENT;
            this.getWindow().setAttributes(attributes);
        } else {
            this.dismiss();
        }
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.cancelButton:
                dismiss();
                break;
            case R.id.okButton:
                if (isYearVisible && !isMonthVisible && !isDayVisible) {
                    Log.e("type", "年选择器");
                    onOkButtonClick(1);
                } else if (isYearVisible && isMonthVisible && !isDayVisible) {
                    Log.e("type", "年月选择器");
                    onOkButtonClick(2);
                } else if (isYearVisible && isMonthVisible) {
                    Log.e("type", "年月日选择器");
                    onOkButtonClick(3);
                }
                dismiss();
                break;
        }
    }

    /**
     * 确认 按钮 点击 事件
     */
    private void onOkButtonClick(int type) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, mDatePicker.getYear());
        calendar.set(Calendar.MONTH, mDatePicker.getMonth());
        calendar.set(Calendar.DAY_OF_MONTH, mDatePicker.getDayOfMonth());
        calendar.getTime();
        if (onDateSetListener != null) {
            String str = mFormatter.format(calendar.getTime());
            String[] s = str.split("-");
            if (type == 1) {
                onDateSetListener.onDateSet(s[0], 1);
            } else if (type == 2) {
                onDateSetListener.onDateSet(s[0] + "-" + s[1], 2);
            } else if (type == 3) {
                onDateSetListener.onDateSet(s[0] + "-" + s[1] + "-" + s[2], 3);
            }
        }

        Log.i("testss", mDatePicker.getYear() + "====" + (mDatePicker.getMonth() + 1) + "==" + mDatePicker.getDayOfMonth());
    }

    /**
     * 时间  改变 监听
     */
    public interface MyOnDateSetListener {
        void onDateSet(String date, int type);
    }

    public void setMyOnDateSetListener(MyOnDateSetListener listener) {
        this.onDateSetListener = listener;
    }
}
