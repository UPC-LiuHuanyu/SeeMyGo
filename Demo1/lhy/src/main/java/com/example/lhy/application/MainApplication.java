package com.example.lhy.application;

import android.app.Application;
import android.content.Context;
import android.os.Handler;

import com.facebook.drawee.backends.pipeline.Fresco;

/**
 * Created by 刘焕宇 on 16/7/13.
 * QQ：310719413
 * Email：freshmboy@126.com
 */
public class MainApplication extends Application {

    public static Context mContext;
    public static Handler mhandler;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();
        mhandler = new Handler();

        //初始化Fresco图片加载器
        Fresco.initialize(mContext);
    }
}
