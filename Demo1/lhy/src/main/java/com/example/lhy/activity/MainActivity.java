package com.example.lhy.activity;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Window;
import android.widget.LinearLayout;

import com.example.lhy.R;
import com.example.lhy.adapter.TabAdapter;

public class MainActivity extends AppCompatActivity {


    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private String[] mTitles;
    private String[] mColorPrimary;
    private Toolbar mToolbar;
    private LinearLayout mTabContainer;
    private String[] mColorPrimaryDark;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mTabContainer = (LinearLayout) findViewById(R.id.tab_container_ll);
        initViewPager();
    }

    private void initViewPager() {

        mTabLayout = (TabLayout) findViewById(R.id.tab_layout);
        mViewPager = (ViewPager) findViewById(R.id.viewpager);
        mTitles = getResources().getStringArray(R.array.indicator_titles);
        mColorPrimary = getResources().getStringArray(R.array.colorPrimary);
        mColorPrimaryDark = getResources().getStringArray(R.array.colorPrimaryDark);

        mViewPager.setAdapter(new TabAdapter(getSupportFragmentManager(), mTitles.length));
        mTabLayout.setupWithViewPager(mViewPager);

        for (int i = 0; i < mTitles.length; i++) {
            mTabLayout.getTabAt(i).setText(mTitles[i]);
        }

        //设置页面切换的监听, 切换时改变主题颜色
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

                mToolbar.setBackgroundColor(Color.parseColor(mColorPrimary[position]));
                mTabContainer.setBackgroundColor(Color.parseColor(mColorPrimary[position]));

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    Window window = getWindow();
                    window.setStatusBarColor(Color.parseColor(mColorPrimaryDark[position]));
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
}
