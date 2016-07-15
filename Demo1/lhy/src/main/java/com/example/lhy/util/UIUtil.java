package com.example.lhy.util;

import android.content.Context;
import android.widget.Toast;

import com.example.lhy.application.MainApplication;

/**
 * Created by 刘焕宇 on 16/7/13.
 * QQ：310719413
 * Email：freshmboy@126.com
 */
public class UIUtil {

    public static Context getContext() {
        return MainApplication.mContext;
    }

    public static void tip(String tip) {
        Toast.makeText(getContext(), tip, Toast.LENGTH_SHORT).show();
    }

    public static void postTaskSafely(Runnable run) {
        MainApplication.mhandler.post(run);
    }


}
