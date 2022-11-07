package com.wise.develop.Landfill.dialog;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.wise.develop.Landfill.R;

public class MoreFunctionPopup extends PopupWindow {
    private OnButtonClickListener buttonClickListener;
    private Context context;
    private View parent;
    private View view;
    private TextView tv_agree_delay;
    private TextView tv_time_out;

    public MoreFunctionPopup(Context context, View parent) {
        this.context = context;
        this.parent = parent;
        initPopup();
        initView();
        initClick();
    }

    private void initPopup() {
        view = View.inflate(context, R.layout.popup_choose_label, null);
        setWidth(LinearLayout.LayoutParams.WRAP_CONTENT);
        setHeight(LinearLayout.LayoutParams.WRAP_CONTENT);
        setBackgroundDrawable(new BitmapDrawable());
        setFocusable(true);
        setOutsideTouchable(true);
        setContentView(view);
    }

    private void initView() {
        tv_agree_delay = view.findViewById(R.id.tv_msg);
        tv_time_out = view.findViewById(R.id.tv_history);
    }

    private void initClick() {
        tv_agree_delay.setOnClickListener(v -> {
            if (buttonClickListener != null) {
                buttonClickListener.onDelayClick();
                dismiss();
            }
        });

        tv_time_out.setOnClickListener(v -> {
            if (buttonClickListener != null) {
                buttonClickListener.onOutClick();
                dismiss();
            }
        });
    }

    public void show() {
        showAsDropDown(parent, 0, 0, Gravity.BOTTOM);
        update();
    }

    public interface OnButtonClickListener {
        void onDelayClick();

        void onOutClick();
    }

    public void setOnButtonClickListener(OnButtonClickListener listener) {
        this.buttonClickListener = listener;
    }
}
