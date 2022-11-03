package com.wise.develop.IntelligentLandfill.base;


import com.wise.develop.IntelligentLandfill.MainApplication;
import com.wise.develop.IntelligentLandfill.util.NetUtil;

import org.reactivestreams.Subscription;

import io.reactivex.FlowableSubscriber;

public abstract class BaseObserver<T extends BaseResponse> implements FlowableSubscriber<T> {

    @Override
    public void onNext(T t) {
        try {
            int code = t.getCode();
            if (code == 0) {
                onSuccess(t);
            }else {
                onError(t.getMsg());
            }
        } catch (Exception e) {
            onError(t.getMsg());
            e.printStackTrace();
        }
    }

    @Override
    public void onError(Throwable t) {
        if (!NetUtil.isNetwork(MainApplication.getContext())) {
            return;
        }
        try {
            t.printStackTrace();
            //可自定义错误类型
            onError("网络请求失败");
        } catch (Exception e) {
            e.printStackTrace();
            onError("网络请求失败");
        }
    }

    @Override
    public void onComplete() {

    }

    @Override
    public void onSubscribe(Subscription s) {
        s.request(Integer.MAX_VALUE);
    }

    public abstract void onSuccess(T bean);

    public abstract void onError(String message);
}
