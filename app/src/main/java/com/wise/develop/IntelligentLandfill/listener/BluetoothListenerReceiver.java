package com.wise.develop.IntelligentLandfill.listener;

import android.bluetooth.BluetoothAdapter;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class BluetoothListenerReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        switch (intent.getAction()) {
            case BluetoothAdapter.ACTION_STATE_CHANGED:
                int blueState = intent.getIntExtra(BluetoothAdapter.EXTRA_STATE, 0);
                switch (blueState) {
                    case BluetoothAdapter.STATE_TURNING_ON:
                        Log.e("1","onReceive---------蓝牙正在打开中");

                        break;
                    case BluetoothAdapter.STATE_ON:
                        Log.e("1","onReceive---------蓝牙已经打开");

                        break;
                    case BluetoothAdapter.STATE_TURNING_OFF:
                        Log.e("1","onReceive---------蓝牙正在关闭中");

                        break;
                    case BluetoothAdapter.STATE_OFF:
                        Log.e("1","onReceive---------蓝牙已经关闭");

                        break;
                }
                break;
        }

    }
}