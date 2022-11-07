package com.wise.develop.Landfill.http;

import android.text.TextUtils;
import android.util.Log;

import com.wise.develop.Landfill.MainApplication;
import com.wise.develop.Landfill.constant.ConstantData;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public final class HttpClient {
    private static final class OKHttpHolder {

        private static final int TIME_OUT = 60;

        private static final OkHttpClient OK_HTTP_CLIENT = createOkHttpClientBuilder().build();

        private static OkHttpClient.Builder createOkHttpClientBuilder() {

            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            builder.addInterceptor(chain -> {
                Request.Builder request = chain.request().newBuilder();
                String token = MainApplication.getContext().getToken();
                if (!TextUtils.isEmpty(token)) {
                    request.addHeader("accessToken", token);
                }
                request.build();

                return chain.proceed(request.build());
            });
            //设置连接超时
            builder.connectTimeout(TIME_OUT, TimeUnit.SECONDS);
            //设置从主机读信息超时
            builder.readTimeout(TIME_OUT, TimeUnit.SECONDS);
            //设置写信息超时
            builder.writeTimeout(TIME_OUT, TimeUnit.SECONDS);
            builder.addInterceptor(new HttpLoggingInterceptor(message -> Log.e("======>", message)).setLevel(HttpLoggingInterceptor.Level.BODY));

            return builder;
        }
    }

    // 构建全局Retrofit客户端
    private static final class RetrofitHolder {

        private static final Retrofit RETROFIT_CLIENT = new Retrofit.Builder()
                .baseUrl(ConstantData.HOST)
                .client(OKHttpHolder.OK_HTTP_CLIENT)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    /**
     * HttpService
     */
    private static final class HttpServiceHolder {
        private static final HttpService REST_SERVICE = RetrofitHolder.RETROFIT_CLIENT.create(HttpService.class);
    }

    static HttpService getHttpService() {
        return HttpServiceHolder.REST_SERVICE;
    }
}