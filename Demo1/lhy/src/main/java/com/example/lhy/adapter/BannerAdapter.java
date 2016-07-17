package com.example.lhy.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;

import com.example.lhy.bean.BannerBean;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 刘焕宇 on 16/7/13.
 * QQ：310719413
 * Email：freshmboy@126.com
 */
public class BannerAdapter extends PagerAdapter {

    List<BannerBean> mDatas;
    List<SimpleDraweeView> images;
    private Context c;

    public BannerAdapter(Context c) {
        this.c = c;
    }

    public void setDatas(List<BannerBean> datas) {
        mDatas = datas;
        images = new ArrayList<>();
        for (BannerBean data : mDatas) {
<<<<<<< HEAD
            ImageView iv = new ImageView(c);
            iv.setLayoutParams(new ViewGroup.LayoutParams(ViewPager.LayoutParams.MATCH_PARENT, ViewPager.LayoutParams.MATCH_PARENT));
            Picasso.with(c).load(data.getImg()).into(iv);
            images.add(iv);
=======
            SimpleDraweeView sdv = new SimpleDraweeView(c);
            sdv.setLayoutParams(new ViewGroup.LayoutParams(ViewPager.LayoutParams.MATCH_PARENT, ViewPager.LayoutParams.MATCH_PARENT));
            sdv.setImageURI(data.getImg());
            images.add(sdv);
>>>>>>> master
        }

    }

    @Override
    public int getCount() {
        return mDatas == null ? 0 : Integer.MAX_VALUE;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
<<<<<<< HEAD
        ImageView child = images.get(position % mDatas.size());
=======
        SimpleDraweeView child = images.get(position);
>>>>>>> master
        container.addView(child);
        return child;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(images.get(position % mDatas.size()));
    }
}
