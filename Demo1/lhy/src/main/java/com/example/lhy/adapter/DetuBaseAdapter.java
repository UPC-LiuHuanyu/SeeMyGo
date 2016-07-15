package com.example.lhy.adapter;

import android.widget.BaseAdapter;

import java.util.List;

/**
 * Created by 刘焕宇 on 16/7/15.
 * QQ：310719413
 * Email：freshmboy@126.com
 */
public abstract class DetuBaseAdapter<T> extends BaseAdapter {
    List<T> mDatas;

    public void setDatas(List<T> datas) {
        mDatas = datas;
    }

    @Override
    public int getCount() {
        return mDatas == null ? 0 : mDatas.size();
    }

    @Override
    public Object getItem(int position) {
        return mDatas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
}
