package com.wise.develop.Landfill.listener;

public interface UploadCallBack {

    void onProgress(int progress);

    void onCompleted(String imageUrl);

    void onError(String msg);

}
