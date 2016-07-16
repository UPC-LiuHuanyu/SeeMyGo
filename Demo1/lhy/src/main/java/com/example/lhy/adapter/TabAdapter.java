package com.example.lhy.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import com.example.lhy.fragment.FragmentFactory;

/**
 * @author Yuan
 * @time 2016/7/16  15:20
 * @desc ${TODD}
 */
public class TabAdapter extends FragmentPagerAdapter {

    private int mCount;

    public TabAdapter(FragmentManager fm, int count) {
        super(fm);
        mCount = count;
    }

    @Override
    public Fragment getItem(int position) {
        return FragmentFactory.createFragment(position);
    }

    @Override
    public int getCount() {
        return mCount;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
    }
}
