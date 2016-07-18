package com.example.lhy.fragment;

import android.content.Intent;
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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.alibaba.fastjson.JSON;
import com.example.lhy.Protocol.IModelChangedListener;
import com.example.lhy.Protocol.IRecycleViewClickListener;
import com.example.lhy.R;
import com.example.lhy.activity.VRActivity;
import com.example.lhy.adapter.BannerAdapter;
import com.example.lhy.adapter.CommenAdapter;
import com.example.lhy.bean.BannerBean;
import com.example.lhy.bean.RItembean;
import com.example.lhy.bean.RResponse;
import com.example.lhy.cons.IntentValues;
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


    private final int ITEM_LOADED = 1;
    private final int BANNER_LOADED = 0;

    private static final String ORDER_RECOMMEND = "recommend";
    private static final int PAGE_SIZE = 20;
    private static final int CHANNEL_ID = 19;

    private ViewPager vp_banner;
    private BannerAdapter mBannerAdapter;
    private RelativeLayout rl_banner;
    private RecyclerView lv_content;
    private Handler mHandler;
    private CommenAdapter mCommenAdapter;

    private LinearLayout ll_dots_container;
    private Timer mTimer;
    private List<RItembean> mRItembeen;

    private SwipeRefreshLayout mRefreshLayout;
    private boolean isLoading;
    private int mPageIndex = 1;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.fragment_recommend, null);
        initReturnView(inflate);
        return inflate;
    }

    private void initReturnView(View inflate) {
        lv_content = (RecyclerView) inflate.findViewById(R.id.lv_content);
        lv_content = (RecyclerView) inflate.findViewById(R.id.lv_content);
        mRefreshLayout = (SwipeRefreshLayout) inflate.findViewById(R.id.refresh_layout);
        mRefreshLayout.setColorSchemeColors(R.color.colorAccent);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initUI();
        initHandler();

        NetWorkUtil.doGet(NetCons.BANNER_URL, new IModelChangedListener<RResponse>(RResponse.class) {
            @Override
            public void onChangeUI(RResponse rResponse) {
                mHandler.obtainMessage(BANNER_LOADED, rResponse.getData()).sendToTarget();
            }
        });

        loadRecommendData();

    }

    private void loadRecommendData() {
        NetWorkUtil.doGet(NetCons.getUrl(CHANNEL_ID,mPageIndex,PAGE_SIZE,ORDER_RECOMMEND), new IModelChangedListener<RResponse>(RResponse.class) {
            @Override
            public void onChangeUI(RResponse rResponse) {
                mHandler.obtainMessage(ITEM_LOADED, rResponse.getData()).sendToTarget();
            }
        });
    }

    private void initHandler() {
        mHandler = new Handler() {

            @Override
            public void handleMessage(Message msg) {
                String json = (String) msg.obj;
                switch (msg.what) {
                    case BANNER_LOADED:
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


                        vp_banner.setCurrentItem(1000 - 1000  % datas.size());

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
                    case ITEM_LOADED:
                        mRItembeen = JSON.parseArray(json, RItembean.class);
                        mCommenAdapter.setDatas(mRItembeen);
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
        lv_content.setLayoutManager(new LinearLayoutManager(getActivity()));
        mCommenAdapter = new CommenAdapter(getActivity());
        mCommenAdapter.setItemOnClickListener(new IRecycleViewClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                RItembean bean = mRItembeen.get(position);
                String preview = bean.html5_3dpreview;
                String thumburl = bean.html5_path;
                Intent intent = new Intent(getActivity(), VRActivity.class);
                intent.putExtra(IntentValues.VR_PREVIEW, preview);
                intent.putExtra(IntentValues.VR_IMAGE_URL, thumburl);
                intent.putExtra(IntentValues.VR_TYPE, VRActivity.VR_IMAGE);
                startActivity(intent);
            }
        });
        lv_content.setAdapter(mCommenAdapter);
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
        ll_dots_container = (LinearLayout) view.findViewById(R.id.ll_dots_container);
        vp_banner = (ViewPager) view.findViewById(R.id.vp_banner);
        rl_banner = (RelativeLayout) view.findViewById(R.id.rl_banner);
        mBannerAdapter = new BannerAdapter(getActivity());
        vp_banner.setAdapter(mBannerAdapter);

        final LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        lv_content.setLayoutManager(layoutManager);
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


    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mTimer != null) {
            mTimer.cancel();
        }
    }
}
