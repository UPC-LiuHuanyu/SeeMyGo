package com.example.lhy.util;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * @author Serena
 * @time 2016/7/10  16:07
 * @desc 处理网络数据请求的工具类(get和post)
 */
public class HttpUtil {

    public static String loadDataFromNetByGet(String urlPath) {
        try {
            OkHttpClient okHttpClient = new OkHttpClient();
            //String url = NetWorkConts.BASEURL + getInterFaceKey();

            Request request = new Request.Builder().url(urlPath).build();
            Response response = null;
            response = okHttpClient.newCall(request).execute();
            if (response.isSuccessful()) {
                return response.body().string();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static String loadDataFromNetByPost(String urlPath, HashMap<String, String> params) {
        try {
            OkHttpClient okHttpClient = new OkHttpClient();
            //String url = NetWorkConts.BASEURL + getInterFaceKey();
            RequestBody formBody = null;
            FormBody.Builder builder = new FormBody.Builder();
            for (Map.Entry<String, String> entry : params.entrySet()) {
                formBody = builder.add(entry.getKey(), entry.getValue()).build();
            }

            Request request = new Request.Builder().url(urlPath).post(formBody).build();
            Response response = null;

            response = okHttpClient.newCall(request).execute();
            if (response.isSuccessful()) {
                return response.body().string();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    //public abstract String getInterFaceKey();
}
