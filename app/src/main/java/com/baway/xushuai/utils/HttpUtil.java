package com.baway.xushuai.utils;

import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 * date:2017/8/25
 * author:徐帅(acer)
 * funcation:OKHttp网络请求的工具类
 */
public class HttpUtil {

    //网络请求OKHttp
    public static void sendOkHttpRequest(String address, Callback callback) {
        //实例化OkHttpClient
        OkHttpClient client = new OkHttpClient();
        //获得Request对象 里面放url
        Request request = new Request.Builder()
                .url(address)
                .build();
        //实例化callback
        client.newCall(request).enqueue(callback);
    }
}