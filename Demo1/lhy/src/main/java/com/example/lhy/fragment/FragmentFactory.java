package com.example.lhy.fragment;

import android.support.v4.app.Fragment;
import android.util.Log;

import java.util.HashMap;

/**
 * @author Yuan
 * @time 2016/7/1  22:52
 * @desc ${TODD}
 */
public class FragmentFactory {
    public static final int FRAGMENT_RECOMMEND = 0;//精品推荐

    public static Fragment createFragment(int position) {
        Fragment fragment = null;
        HashMap<Integer, Fragment> mFragmentMap = new HashMap<>();
        if (mFragmentMap.containsKey(position)) {
            fragment = mFragmentMap.get(position);
            return fragment;
        }
        switch (position) {
            case FRAGMENT_RECOMMEND:
                fragment = new RecommendFragment();
                Log.d("FragmentFactory", "createFragment: RecommendFragment");
                break;
            default:
                fragment = new EmptyFragment();
                Log.d("FragmentFactory", "createFragment: EmptyFragment");
                break;
        }
        mFragmentMap.put(position, fragment);
        return fragment;
    }
}
