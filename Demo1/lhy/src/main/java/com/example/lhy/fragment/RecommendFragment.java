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
import android.widget.ImageView;
import android.widget.LinearLayout;
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
import com.example.lhy.util.UIUtil;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by 刘焕宇 on 16/7/13.
 * QQ：310719413
 * Email：freshmboy@126.com
 */
public class RecommendFragment extends Fragment {


    private ViewPager vp_banner;
    private BannerAdapter mBannerAdapter;
    private RelativeLayout rl_banner;
    private RecyclerView lv_content;

    private Handler mHandler;
    private CommenAdapter mCommenAdapter;
    private LinearLayout ll_dots_container;
    private Timer mTimer;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.fragment_recommend, null);
        initUI(inflate);
        return inflate;
    }

    private void initUI(View inflate) {
        vp_banner = (ViewPager) inflate.findViewById(R.id.vp_banner);
        rl_banner = (RelativeLayout) inflate.findViewById(R.id.rl_banner);
        lv_content = (RecyclerView) inflate.findViewById(R.id.lv_content);
        ll_dots_container = (LinearLayout) inflate.findViewById(R.id.ll_dots_container);
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
                        final List<BannerBean> datas = JSON.parseArray(json, BannerBean.class);
                        mBannerAdapter.setDatas(datas);
                        mBannerAdapter.notifyDataSetChanged();
                        rl_banner.setVisibility(View.VISIBLE);

                        for (int t = 0; t < datas.size(); t++) {
                            ImageView dot = new ImageView(getContext());
                            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(10, 10);
                            params.setMargins(20, 0, 0, 0);
                            dot.setLayoutParams(params);
                            dot.setImageResource(R.drawable.dot_selecter);


                            ll_dots_container.addView(dot);
                        }


                        vp_banner.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                            @Override
                            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                            }

                            @Override
                            public void onPageSelected(int position) {
                                int size = datas.size();
                                int index = position % size;
                                for (int i = 0; i < size; i++) {
                                    ll_dots_container.getChildAt(i).setEnabled(i == index);
                                }
                                ll_dots_container.invalidate();
                            }

                            @Override
                            public void onPageScrollStateChanged(int state) {

                            }
                        });


                        vp_banner.setCurrentItem(Integer.MAX_VALUE / 2 - Integer.MAX_VALUE / 2 % datas.size());

                        mTimer = new Timer();
                        mTimer.schedule(new TimerTask() {
                            @Override
                            public void run() {
                                UIUtil.postTaskSafely(new Runnable() {
                                    @Override
                                    public void run() {
                                        vp_banner.setCurrentItem(vp_banner.getCurrentItem() + 1);
                                    }
                                });
                            }
                        }, 2000, 2000);

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
        mBannerAdapter = new BannerAdapter(getActivity());
        vp_banner.setAdapter(mBannerAdapter);


        lv_content.setLayoutManager(new LinearLayoutManager(getActivity()));
        mCommenAdapter = new CommenAdapter(getActivity());
        lv_content.setAdapter(mCommenAdapter);
    }

    @Override
    public void onStop() {
        super.onStop();
        mTimer.cancel();

    }
}
