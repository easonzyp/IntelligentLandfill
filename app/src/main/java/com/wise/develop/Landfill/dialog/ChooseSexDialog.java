package com.wise.develop.Landfill.dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import com.wise.develop.Landfill.R;

/**
 * Created by zyp on 2018/3/12.
 * note:首页详情弹窗
 */
public class ChooseSexDialog extends Dialog {

    private Context context;
    private OnBtnClickListener listener;
    private TextView tv_man;
    private TextView tv_women;


    public ChooseSexDialog(Context context) {
        super(context, R.style.BottomDialogTheme);
        this.context = context;
        initView();
    }

    private void initView() {
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_choose_sex, null);
        setContentView(view);
        initClick();
    }

    private void initClick() {
    }

    @Override
    public void show() {
        super.show();
        Window window = getWindow();
        if (window == null) {
            return;
        }

        WindowManager.LayoutParams params = window.getAttributes();
        params.gravity = Gravity.BOTTOM;
        params.width = ViewGroup.LayoutParams.MATCH_PARENT;
        window.setWindowAnimations(R.style.pop_win_anim_style);
        window.setAttributes(params);
    }

    public interface OnBtnClickListener {
        void onItemClick(String text);
    }

    public void setOnBtnClickListener(OnBtnClickListener listener) {
        this.listener = listener;
    }
}
