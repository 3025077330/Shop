package com.bw.net;


import com.bw.common.ShoppingConstant;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

//定义网络框架
public class RetroCreator {

    private static ShoppService shoppService;


    public static ShoppService getShoppService() {
        if (shoppService == null) {
            synchronized (ShoppService.class) {
                if (shoppService == null) {
                    shoppService = createKSApiService();
                }
            }
        }
        return shoppService;
    }

    private static ShoppService createKSApiService() {
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(5, TimeUnit.SECONDS)
                .readTimeout(5, TimeUnit.SECONDS)
                .writeTimeout(5, TimeUnit.SECONDS)
                .addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
              .addInterceptor(new TokenInterceptor())
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(ShoppingConstant.BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        return retrofit.create(ShoppService.class);
    }
}
