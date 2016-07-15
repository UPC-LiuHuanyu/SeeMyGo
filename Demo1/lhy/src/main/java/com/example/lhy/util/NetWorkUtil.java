package com.example.lhy.util;

import android.util.Log;

import com.example.lhy.Protocol.IModelChangedListener;
import com.example.lhy.cons.NetCons;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;
import java.util.Set;

/**
 * Created by 刘焕宇 on 16/7/10.
 * QQ：310719413
 * Email：freshmboy@126.com
 */
public class NetWorkUtil {

    public static void doGet(final String urlPath, final IModelChangedListener listener) {

        new Thread() {

            public void run() {

                try {
                    String path = "";
                    if (!urlPath.contains("?")) {
                        path = urlPath + NetCons.EXTRA_TAIL;
                    } else {
                        path = urlPath + NetCons.EXTRA_TAIL.replace("?", "&");

                    }

                    URL url = new URL(path);
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    if (conn.getResponseCode() == 200) {
                        InputStream is = conn.getInputStream();
                        BufferedReader br = new BufferedReader(new InputStreamReader(is));
                        String strJson = "";
                        strJson += br.readLine();
                        listener.onDataChanged(strJson);
                    } else {
                        Log.i("520it", "请求不对");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    Log.i("520it", "网络请求出错啦！");
                }
            }
        }.start();
    }

    public static void doPost(final String urlPath, final IModelChangedListener listener, final Map<String, String> map) {
        try {
            URL url = new URL(urlPath);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setDoInput(true);

            String inputStr = "";
            Set<Map.Entry<String, String>> entries = map.entrySet();
            for (Map.Entry<String, String> entry : entries) {
                String key = entry.getKey();
                String value = entry.getValue();
                inputStr += "&" + key + "=" + value;
            }
            inputStr = inputStr.substring(1);
            Log.i("520it", "inputStr=" + inputStr);
            conn.getOutputStream().write(inputStr.getBytes());

            if (conn.getResponseCode() == 200) {
                InputStream is = conn.getInputStream();
                BufferedReader br = new BufferedReader(new InputStreamReader(is));
                String strJson = br.readLine();
                listener.onDataChanged(strJson);
            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.i("520it", "网络请求出错啦！");
        }
    }


}
