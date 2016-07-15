package com.example.lhy.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.example.lhy.R;
import com.example.lhy.fragment.RecommendFragment;
import com.facebook.drawee.backends.pipeline.Fresco;

/**
 * Created by 刘焕宇 on 16/7/13.
 * QQ：310719413
 * Email：freshmboy@126.com
 */
public class MainActivity extends FragmentActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fresco.initialize(this);
        setContentView(R.layout.activity_main);

        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        RecommendFragment fragment = new RecommendFragment();
        transaction.add(R.id.fl_work_list_container, fragment);
        transaction.commit();

    }
}
