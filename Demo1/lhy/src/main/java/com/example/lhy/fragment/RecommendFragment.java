package com.example.lhy.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.alibaba.fastjson.JSON;
import com.example.lhy.Protocol.IModelChangedListener;
import com.example.lhy.R;
import com.example.lhy.adapter.BannerAdapter;
import com.example.lhy.adapter.CommenAdapter;
import com.example.lhy.bean.BannerBean;
import com.example.lhy.bean.RItembean;
import com.example.lhy.bean.RResponse;
import com.example.lhy.cons.NetCons;
import com.example.lhy.util.NetWorkUtil;

import java.util.List;

/**
 * Created by 刘焕宇 on 16/7/13.
 * QQ：310719413
 * Email：freshmboy@126.com
 */
public class RecommendFragment extends Fragment {


    private ViewPager vp_banner;
    private BannerAdapter mAdapter;
    private RelativeLayout rl_banner;
    private RecyclerView lv_content;

    private Handler mHandler;
    private CommenAdapter mCommenAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.fragment_recommend, null);
        vp_banner = (ViewPager) inflate.findViewById(R.id.vp_banner);
        rl_banner = (RelativeLayout) inflate.findViewById(R.id.rl_banner);
        lv_content = (RecyclerView) inflate.findViewById(R.id.lv_content);
        return inflate;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initUI();
        initHandler();

        NetWorkUtil.doGet(NetCons.BANNER_URL, new IModelChangedListener<RResponse>(RResponse.class) {
            @Override
            public void onChangeUI(RResponse rResponse) {
                mHandler.obtainMessage(0, rResponse.getData()).sendToTarget();
            }
        });

        NetWorkUtil.doGet(NetCons.SPECIFIC_CHANNEL_URL + NetCons.SPECIFIC_CHANNEL_PARAMS, new IModelChangedListener<RResponse>(RResponse.class) {
            @Override
            public void onChangeUI(RResponse rResponse) {
                mHandler.obtainMessage(1, rResponse.getData()).sendToTarget();
            }
        });

    }

    private void initHandler() {
        mHandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                String json = (String) msg.obj;
                switch (msg.what) {
                    case 0:
                        List<BannerBean> datas = JSON.parseArray(json, BannerBean.class);
                        mAdapter.setDatas(datas);
                        mAdapter.notifyDataSetChanged();
                        rl_banner.setVisibility(View.VISIBLE);
                        break;
                    case 1:
                        List<RItembean> rItembeen = JSON.parseArray(json, RItembean.class);
                        mCommenAdapter.setDatas(rItembeen);
                        mCommenAdapter.notifyDataSetChanged();
                        break;
                }
            }
        };
    }

    private void initUI() {
        mAdapter = new BannerAdapter(getActivity());
        vp_banner.setAdapter(mAdapter);
        lv_content.setLayoutManager(new LinearLayoutManager(getActivity()));
        mCommenAdapter = new CommenAdapter(getActivity());
        lv_content.setAdapter(mCommenAdapter);
    }
}
