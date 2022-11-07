package com.wise.develop.Landfill.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Build;
import android.os.Environment;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.wise.develop.Landfill.R;
import com.wise.develop.Landfill.util.DateUtil;
import com.wise.develop.Landfill.util.RandomUtil;
import com.wise.develop.Landfill.util.SoundMeter;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Date;


/**
 * Created by zyp on 2018/3/12.
 * note:双按钮dialog
 */

public class RecordAudioDialog extends Dialog {

    private Context context;
    private OnDialogClickListener listener;
    private TextView tv_duration;
    private TextView tv_cancel;
    private TextView tv_ok;
    private SoundMeter mSensor;
    private String filePath = "";
    private String fileName = "";
    private Handler mHandler = new Handler();

    public RecordAudioDialog(Context context) {
        super(context, R.style.DialogTheme);
        this.context = context;
        setCustomDialog();
        mSensor = new SoundMeter();
    }

    private void setCustomDialog() {
        View mView = LayoutInflater.from(getContext()).inflate(R.layout.dialog_record_audio, null);
        tv_duration = mView.findViewById(R.id.tv_duration);
        tv_cancel = mView.findViewById(R.id.tv_cancel);
        tv_ok = mView.findViewById(R.id.tv_ok);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setCanceledOnTouchOutside(false);
        super.setContentView(mView);
        initClick();
    }

    private void initClick() {
        tv_cancel.setOnClickListener(view -> {
            stop();
            dismiss();
        });

        tv_ok.setOnClickListener(view -> {
            if (listener != null) {
                stop();
                listener.onClickRight(filePath, fileName);
                dismiss();
            }
        });
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

        fileName = "voice_" + RandomUtil.getSmallLetter(4) + "_" + new Date().getTime() / 1000 + ".mp3";
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            filePath = context.getExternalFilesDir(Environment.DIRECTORY_MUSIC) + "/" + fileName;
        } else {
            filePath = Environment.getExternalStorageDirectory() + "/" + fileName;
        }
        start(filePath);
        duration = 0;
        tv_duration.setText("00:00");
    }

    public void saveVoice(String fileName) {
        try {
            //图片沙盒文件夹
            File VOICE = context.getExternalFilesDir(Environment.DIRECTORY_MUSIC);
            File hiddenVoice = new File(VOICE + "/" + "hiddenVoice");
            if (hiddenVoice.exists()) {
                File voiceFile = new File(VOICE + "/hiddenVoice/" + fileName);
                FileOutputStream fileOutputStream = new FileOutputStream(voiceFile);
                fileOutputStream.flush();
                fileOutputStream.close();
            } else if (hiddenVoice.mkdir()) {//如果该文件夹不存在，则新建
                //new一个文件
                File imageFile = new File(VOICE + "/hiddenVoice/" + fileName);
                //通过流将图片写入
                FileOutputStream fileOutputStream = new FileOutputStream(imageFile);
                fileOutputStream.flush();
                fileOutputStream.close();
            }
        } catch (Exception e) {
        }
    }

    private void start(String filePath) {
        mSensor.start(filePath);
        mHandler.postDelayed(mPollTask, 1000);
    }

    private void stop() {
        mHandler.removeCallbacks(mPollTask);
        mSensor.stop();
    }

    private int duration = 0;

    private Runnable mPollTask = new Runnable() {
        @Override
        public void run() {
            duration++;
            tv_duration.setText(DateUtil.getTimeStrBySecond(duration));
            mHandler.postDelayed(this, 1000);
        }
    };

    public interface OnDialogClickListener {
        void onClickRight(String filePath, String fileName);
    }

    @Override
    public void onBackPressed() {

    }

    public void setOnClickRateDialog(OnDialogClickListener listener) {
        this.listener = listener;
    }
}
