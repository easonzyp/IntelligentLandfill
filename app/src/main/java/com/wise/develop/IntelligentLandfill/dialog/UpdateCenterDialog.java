package com.wise.develop.IntelligentLandfill.dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.wise.develop.IntelligentLandfill.R;


/**
 * Created by zyp on 2018/3/12.
 * note:双按钮dialog
 */

public class UpdateCenterDialog extends Dialog {

    private OnClickRateDialog onClickRateListener;
    private TextView textView;
    private ProgressBar progress_horizontal;
    private TextView positiveButton;

    public UpdateCenterDialog(Context context) {
        super(context, R.style.CenterDialogTheme);
        setCancelable(false);
        setCanceledOnTouchOutside(false);
        setCustomDialog();
    }

    public void setTips(String tips) {
        textView.setText(tips);
    }

    public void setProgress(int progress) {
        progress_horizontal.setProgress(progress);
    }

    public void setEnabled(boolean isEnabled){
        positiveButton.setEnabled(isEnabled);
    }

    public void setMaxProgress(int progress) {
        progress_horizontal.setMax(progress);
    }

    public void setProgressBarVISIBLE() {
        progress_horizontal.setVisibility(View.VISIBLE);
    }

    private void setCustomDialog() {
        View mView = LayoutInflater.from(getContext()).inflate(R.layout.dialog_update, null);
        progress_horizontal = mView.findViewById(R.id.progress_horizontal);
        textView = mView.findViewById(R.id.tv_tips);

        positiveButton = mView.findViewById(R.id.button2);
        if (positiveButton != null) positiveButton.setOnClickListener(v -> {
            if (onClickRateListener != null) {
                onClickRateListener.onClickUpdate();
            }
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
        params.gravity = Gravity.CENTER;
        params.height = ViewGroup.LayoutParams.WRAP_CONTENT;
        params.width = ViewGroup.LayoutParams.MATCH_PARENT;
        window.setAttributes(params);
    }

    public interface OnClickRateDialog {

        void onClickUpdate();
    }

    public void setOnClickRateDialog(OnClickRateDialog onClickRateListener) {
        this.onClickRateListener = onClickRateListener;
    }
}
