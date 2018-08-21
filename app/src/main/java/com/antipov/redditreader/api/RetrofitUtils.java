package com.antipov.redditreader.api;


import com.antipov.redditreader.BuildConfig;

import java.io.IOException;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.antipov.redditreader.utils.common.Const.BASE_URL;

/**
 * Utility for instantiating retrofit
 *
 * Created by AlexanderAntipov on 21.08.2018.
 */

public class RetrofitUtils {
    public static Retrofit getApi() {

        OkHttpClient.Builder httpClientBuilder = new OkHttpClient.Builder();
        // set up logger for debug
        if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);
            httpClientBuilder.addInterceptor(logging);
        }

        RxJavaCallAdapterFactory rxAdapter = RxJavaCallAdapterFactory.create();
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(rxAdapter)
                .client(httpClientBuilder.build())
                .build();
    }

}
