package com.example.lhy.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.lhy.R;
import com.example.lhy.bean.BannerBean;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 刘焕宇 on 16/7/13.
 * QQ：310719413
 * Email：freshmboy@126.com
 */
public class BannerAdapter extends PagerAdapter {

    List<BannerBean> mDatas;
    List<ImageView> images;
    private Context c;

    public BannerAdapter(Context c) {
        this.c = c;
    }

    public void setDatas(List<BannerBean> datas) {
        mDatas = datas;
        images = new ArrayList<>();
        for (BannerBean data : mDatas) {
            ImageView iv = new ImageView(c);
            iv.setLayoutParams(new ViewGroup.LayoutParams(ViewPager.LayoutParams.MATCH_PARENT, ViewPager.LayoutParams.MATCH_PARENT));
            Picasso.with(c).load(data.getImg()).into(iv);
//            iv.setImageResource(R.drawable.ic);
            images.add(iv);
        }

    }

    @Override
    public int getCount() {
        return mDatas == null ? 0 : mDatas.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        ImageView child = images.get(position);
        container.addView(child);
        return images.get(position);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(images.get(position));
    }
}
