package com.example.lhy.util;

import android.app.Activity;
import android.content.Intent;

/**
 * Created by 刘焕宇 on 16/7/13.
 * QQ：310719413
 * Email：freshmboy@126.com
 */
public class ActivityUtil {
    /**
     *
     * @param activity 当前activity
     * @param claz 要打开的activity
     * @param finish 是否关闭
     * @param delay 是否有延时
     */
    public static void startActivity(Activity activity, Class claz, boolean finish, int delay) {
        Intent intent = new Intent(activity, claz);
        activity.startActivity(intent);

    }
}
