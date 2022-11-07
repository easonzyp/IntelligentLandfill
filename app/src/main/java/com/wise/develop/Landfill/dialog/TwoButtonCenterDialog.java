package com.wise.develop.Landfill.dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.wise.develop.Landfill.R;


/**
 * Created by zyp on 2018/3/12.
 * note:双按钮dialog
 */

public class TwoButtonCenterDialog extends Dialog {

    private OnClickRateDialog onClickRateListener;
    private TextView tv_title;
    private TextView textView;

    public TwoButtonCenterDialog(Context context) {
        super(context, R.style.DialogTheme);
        setCustomDialog();
    }

    public void setTips(String title,String tips) {
        textView.setText(tips);
        tv_title.setText(title);
    }

    private void setCustomDialog() {
        View mView = LayoutInflater.from(getContext()).inflate(R.layout.dialog_two_button_center, null);
        tv_title = mView.findViewById(R.id.tv_title);
        textView = mView.findViewById(R.id.tv_tips);

        TextView positiveButton = mView.findViewById(R.id.button2);
        TextView negativeButton = mView.findViewById(R.id.button1);
        if (positiveButton != null) positiveButton.setOnClickListener(v -> {
            if (onClickRateListener != null)
                onClickRateListener.onClickRight();
            dismiss();
        });
        if (negativeButton != null) negativeButton.setOnClickListener(v -> {
            if (onClickRateListener != null)
                onClickRateListener.onClickLeft();
            dismiss();
        });

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.setContentView(mView);
    }

    @Override
    public void show() {
        super.show();
        Window window = getWindow();
        if (window == null) {
            return;
        }

        WindowManager.LayoutParams params = window.getAttributes();
        params.width = ViewGroup.LayoutParams.MATCH_PARENT;

        window.setAttributes(params);
    }

    public interface OnClickRateDialog {
        void onClickLeft();

        void onClickRight();
    }

    public void setOnClickRateDialog(OnClickRateDialog onClickRateListener) {
        this.onClickRateListener = onClickRateListener;
    }
}
