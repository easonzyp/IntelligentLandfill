package com.wise.develop.IntelligentLandfill.http;

import com.wise.develop.IntelligentLandfill.base.BaseResponse;
import com.wise.develop.IntelligentLandfill.bean.LoginBean;

import java.util.List;
import java.util.Map;

import io.reactivex.Flowable;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.Streaming;
import retrofit2.http.Url;

public interface HttpService {
    @Streaming
    @GET
    Flowable<ResponseBody> downloadFile(@Url String fileUrl);

    @Multipart
    @POST("File/UploadSampleImage")
    Flowable<BaseResponse> uploadFiles(@Part MultipartBody.Part file);

    @Multipart
    @POST()
    Flowable<BaseResponse> uploadFiles(@Url String url, @Part MultipartBody.Part file);

    @Multipart
    @POST()
    Flowable<BaseResponse> uploadFileList(@Url String url, @Part List<MultipartBody.Part> fileList);

    @Multipart
    @POST()
    Flowable<ResponseBody> UploadCompleteImage(@Url String url, @Part MultipartBody.Part file);

    @Multipart
    @POST("File/UploadSampleImage")
    Flowable<ResponseBody> uploadFiles(@Part List<MultipartBody.Part> parts);

    @Multipart
    @POST("/auth/Login/LoginCheck")
    Flowable<LoginBean> login(@PartMap Map<String, RequestBody> params);
}