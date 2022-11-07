package com.wise.develop.Landfill.util;

import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;
import android.widget.TextView;

import java.io.File;
import java.io.FileInputStream;

public class AudioPlayUtil {

    private static MediaPlayer mMediaPlayer = new MediaPlayer();

    public static void playAudioByUrl(Context context, String url,TextView tv) {
        try {
            tv.setText("正在播放");
            Uri uri = Uri.parse(url);
            if (mMediaPlayer.isPlaying()) {
                return;
            }
            mMediaPlayer.reset();
            mMediaPlayer.setDataSource(context, uri);
            mMediaPlayer.prepare();
            mMediaPlayer.start();
            mMediaPlayer.setOnCompletionListener(mp -> tv.setText("点击播放"));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void playAudioByPath(String path) {
        try {
            if (mMediaPlayer.isPlaying()) {
                mMediaPlayer.stop();
            }
            mMediaPlayer.reset();
            File file = new File(path);
            FileInputStream fis = new FileInputStream(file);
            mMediaPlayer.setDataSource(fis.getFD());
            mMediaPlayer.prepare();
            mMediaPlayer.start();
            mMediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                public void onCompletion(MediaPlayer mp) {

                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
