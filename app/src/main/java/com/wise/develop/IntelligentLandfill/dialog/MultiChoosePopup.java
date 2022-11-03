package com.wise.develop.IntelligentLandfill.dialog;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.wise.develop.IntelligentLandfill.R;

public class MultiChoosePopup extends PopupWindow {
    private OnButtonClickListener buttonClickListener;
    private Context context;
    private View parent;
    private View view;
    private TextView tv_multi_pass;
    private TextView tv_multi_report;

    public MultiChoosePopup(Context context, View parent) {
        this.context = context;
        this.parent = parent;
        initPopup();
        initView();
        initClick();
    }

    private void initPopup() {
        view = View.inflate(context, R.layout.popup_multi_choose, null);
        setWidth(LinearLayout.LayoutParams.WRAP_CONTENT);
        setHeight(LinearLayout.LayoutParams.WRAP_CONTENT);
        setBackgroundDrawable(new BitmapDrawable());
        setFocusable(true);
        setOutsideTouchable(true);
        setContentView(view);
    }

    private void initView() {
        tv_multi_pass = view.findViewById(R.id.tv_multi_pass);
        tv_multi_report = view.findViewById(R.id.tv_multi_report);
    }

    private void initClick() {
        tv_multi_pass.setOnClickListener(v -> {
            if (buttonClickListener != null) {
                buttonClickListener.onChoose(1, "批量通过");
                dismiss();
            }
        });

        tv_multi_report.setOnClickListener(v -> {
            if (buttonClickListener != null) {
                buttonClickListener.onChoose(2, "批量上报");
                dismiss();
            }
        });
    }

    public void show() {
        showAsDropDown(parent, 0, 0, Gravity.BOTTOM);
        update();
    }

    public interface OnButtonClickListener {
        void onChoose(int type, String name);
    }

    public void setOnButtonClickListener(OnButtonClickListener listener) {
        this.buttonClickListener = listener;
    }
}
