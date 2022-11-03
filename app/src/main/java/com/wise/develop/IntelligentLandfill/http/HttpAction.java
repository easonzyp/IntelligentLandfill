package com.wise.develop.IntelligentLandfill.http;


import android.text.TextUtils;

import com.wise.develop.IntelligentLandfill.base.BaseResponse;
import com.wise.develop.IntelligentLandfill.bean.LoginBean;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;


public class HttpAction {

    public static HttpAction getInstance() {
        return HttpActionHolder.instance;
    }

    private static class HttpActionHolder {
        private static HttpAction instance = new HttpAction();
    }

    private <T> Flowable<T> applySchedulers(Flowable<T> responseFlowable) {
        return responseFlowable
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Flowable<BaseResponse> uploadFiles(String url, MultipartBody.Part file) {
        return applySchedulers(HttpClient.getHttpService().uploadFiles(url, file));
    }

    public Flowable<BaseResponse> uploadFiles(String url, List<MultipartBody.Part> fileList) {
        return applySchedulers(HttpClient.getHttpService().uploadFileList(url, fileList));
    }
    public Flowable<LoginBean> login(Map<String, String> params) {
        return applySchedulers(HttpClient.getHttpService().login(generateRequestBody(params)));
    }

    /**
     * 转换为 form-data
     */
    private static Map<String, RequestBody> generateRequestBody(Map<String, String> requestDataMap) {
        Map<String, RequestBody> requestBodyMap = new HashMap<>();
        for (String key : requestDataMap.keySet()) {
            MediaType type = MediaType.parse("multipart/form-data");
            RequestBody requestBody = RequestBody.create(type, TextUtils.isEmpty(requestDataMap.get(key)) ? "" : Objects.requireNonNull(requestDataMap.get(key)));
            requestBodyMap.put(key, requestBody);
        }
        return requestBodyMap;
    }
}