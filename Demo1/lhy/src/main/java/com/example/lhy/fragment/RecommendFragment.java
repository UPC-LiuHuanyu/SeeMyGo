package com.example.lhy.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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

    private static final String ORDER_RECOMMEND = "recommend";
    private static final int PAGE_SIZE = 20;
    private static final int CHANNEL_ID = 19;
    private ViewPager vp_banner;
    private BannerAdapter mAdapter;
    private RelativeLayout rl_banner;
    private RecyclerView lv_content;

    private Handler mHandler;
    private CommenAdapter mCommenAdapter;
    private SwipeRefreshLayout mRefreshLayout;
    private boolean isLoading;
    private int mPageIndex = 1;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.fragment_recommend, null);
        lv_content = (RecyclerView) inflate.findViewById(R.id.lv_content);
        mRefreshLayout = (SwipeRefreshLayout) inflate.findViewById(R.id.refresh_layout);
        mRefreshLayout.setColorSchemeColors(R.color.colorAccent);
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

        loadRecommendData();

    }

    private void loadRecommendData() {
        NetWorkUtil.doGet(NetCons.getUrl(CHANNEL_ID,mPageIndex,PAGE_SIZE,ORDER_RECOMMEND), new IModelChangedListener<RResponse>(RResponse.class) {
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
                        mRefreshLayout.setRefreshing(false);
                        mPageIndex++;
                        isLoading = false;
                        break;
                }
            }
        };
    }

    private void initUI() {

        mRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                mRefreshLayout.setRefreshing(true);
            }
        });

        mRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadRecommendData();
            }
        });

        View view = LayoutInflater.from(getContext())
                .inflate(R.layout.item_header, null);
        vp_banner = (ViewPager) view.findViewById(R.id.vp_banner);
        rl_banner = (RelativeLayout) view.findViewById(R.id.rl_banner);
        mAdapter = new BannerAdapter(getActivity());
        vp_banner.setAdapter(mAdapter);

        final LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        lv_content.setLayoutManager(layoutManager);
        mCommenAdapter = new CommenAdapter(getActivity());
        lv_content.setAdapter(mCommenAdapter);
        mCommenAdapter.addHeaderView(view);

        lv_content.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                int lastVisibleItemPosition = layoutManager.findLastVisibleItemPosition();
                if (lastVisibleItemPosition + 2 == mCommenAdapter.getItemCount()) {
                    if (!isLoading) {
                        isLoading = true;
                        Log.d("RecommendFragment", "onScrolled: loadmore");
                        loadRecommendData();
                    }

                }
            }
        });
    }
}
