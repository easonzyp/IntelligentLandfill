package com.wise.develop.IntelligentLandfill.dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.wise.develop.IntelligentLandfill.R;


/**
 * Created by zyp on 2018/3/12.
 * note:双按钮dialog
 */

public class SingleButtonCenterDialog extends Dialog {

    private OnClickRateDialog onClickRateListener;
    private TextView tv_title;
    private TextView textView;

    public SingleButtonCenterDialog(Context context) {
        super(context, R.style.DialogTheme);
        setCustomDialog();
    }

    public void setTips(String title, String tips) {
        textView.setText(tips);
        tv_title.setText(title);
    }

    private void setCustomDialog() {
        View mView = LayoutInflater.from(getContext()).inflate(R.layout.dialog_single_button_center, null);
        tv_title = mView.findViewById(R.id.tv_title);
        textView = mView.findViewById(R.id.tv_tips);

        TextView button = mView.findViewById(R.id.button);
        button.setOnClickListener(v -> {
            if (onClickRateListener != null) {
                onClickRateListener.onClickLeft();
            }
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
    }

    public void setOnClickRateDialog(OnClickRateDialog onClickRateListener) {
        this.onClickRateListener = onClickRateListener;
    }
}
