package com.example.lhy.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.lhy.R;
import com.example.lhy.cons.IntentValues;
import com.example.lhy.ui.EdgeCircleImageView;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.player.panoplayer.PanoPlayer;
import com.player.panoplayer.PanoPlayerUrl;
import com.player.renderer.PanoPlayerSurfaceView;

import org.opencv.android.OpenCVLoader;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class VRActivity extends Activity {
    public static final int VR_IMAGE = 0;
    public static final int VR_VIDEO = 1;

    public static final String mVideo_Url_template = "<DetuVr> \n" +
            "\t<settings init=\"pano1\" initmode=\"default\" enablevr=\"false\" title=\"\" /> \n" +
            "\t<scenes> \n" +
            "\t\t<!-- video --> \n" +
            "\t\t<scene name=\"pano-130008\" title=\"【法晚VR新闻】第一视角带你看特战魔鬼周\" thumburl=\"http://media.qicdn.detu.com/@/17301281-9356-8DF4-8425-A934F01236889/2016-07-04/577a00440f356-1000x500.jpg\"> \n" +
            "\t\t\t<view hlookat=\"0\" vlookat=\"0\" fov=\"65\" vrfov=\"90\" vrz=\"0.5\" righteye=\"0\" fovmax=\"95\" defovmax=\"95\" viewmode=\"default\" /> \n" +
            "\t\t\t<preview type=\"sphere\" url=\"http://media.qicdn.detu.com/@/17301281-9356-8DF4-8425-A934F01236889/2016-07-04/577a00440f356.jpg\" /> \n" +
            "\t\t\t<image type=\"video\" url=\"http://media.qicdn.detu.com/@/17301281-9356-8DF4-8425-A934F01236889/2016-07-04/577a00440f356.m3u8\" device=\"0\" /> \n" +
            "\t\t</scene> \n" +
            "\t</scenes> \n" +
            "</DetuVr>";
    private static final String mImage_Url_template = "<DetuVr> \n" +
            "\t<settings init=\"pano1\" initmode=\"default\" enablevr=\"false\" title=\"\" /> \n" +
            "\t<scenes> \n" +
            "\t\t<!-- pano --> \n" +
            "\t\t<scene name=\"pano-132079\" title=\"广东现代国际展览中心\" thumburl=\"http://media.qicdn.detu.com/pano256151468285576336023987/thumb/500_500/panofile.jpg\"> \n" +
            "\t\t\t<view hlookat=\"0\" vlookat=\"0\" fov=\"65\" vrfov=\"90\" vrz=\"0.5\" righteye=\"0\" fovmax=\"130\" defovmax=\"95\" viewmode=\"default\" /> \n" +
            "\t\t\t<preview url=\"%s\" /> \n" +
            "\t\t\t<image type=\"cube\" url=\"%s\" /> \n" +
            "\t\t</scene> \n" +
            "\t</scenes> \n" +
            "</DetuVr>";

    static {
        if (!OpenCVLoader.initDebug()) {
        }
    }

    @InjectView(R.id.glview)
    PanoPlayerSurfaceView mGlview;
    @InjectView(R.id.iv_icon)
    EdgeCircleImageView mIvIcon;
    @InjectView(R.id.tv_author)
    TextView mTvAuthor;
    @InjectView(R.id.tv_desc)
    TextView mTvDesc;
    @InjectView(R.id.lv_comments)
    ListView mLvComments;
    @InjectView(R.id.ll_text_container)
    LinearLayout mLlTextContainer;
    @InjectView(R.id.ll_author_info_container)
    LinearLayout mLlAuthorInfoContainer;
    @InjectView(R.id.tv_panel_anchor)
    TextView mTvPanelAnchor;
    @InjectView(R.id.Gyro)
    Button mGyro;

    private PanoPlayer mPlayer;
    private ArrayList<String> mData;
    private ArrayAdapter<String> mAdapter;
    private int mScreenHeight;
    private int mMaxHeight;
    private float mLastY;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vr_show);
        //SDK的默认配置，不要改
        defaultOptions();
        //初始化listview，用string填充
        initData();
        //初始化UI，就是那些panoplayer啥的
        initUI();

        //根据传过来的intent里面的type，判断是进入图片显示还是视频显示
        Intent intent = getIntent();
        int type = intent.getIntExtra(IntentValues.VR_TYPE, -1);
        switch (type) {
            case VR_IMAGE:
                playVRImage(intent);
                break;
            case VR_VIDEO:
                playVRVideo(intent);
                break;
        }
        //设置点击事件，开启重力感应
        findViewById(R.id.Gyro).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //设置全屏
                fullScreen();
                if (mPlayer != null) {
                    mPlayer.setGyroEnable(true);
                }
            }
        });
    }

    /**
     * 根据intent的内容设置参数
     *
     * @param intent
     */
    private void playVRVideo(Intent intent) {
        play(mVideo_Url_template);
    }

    private void initUI() {
        ButterKnife.inject(this);
        mGlview = (PanoPlayerSurfaceView) findViewById(R.id.glview);
        mPlayer = new PanoPlayer(mGlview, this);
        mGlview.setRenderer(mPlayer);

        mAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, mData);
        mLvComments.setAdapter(mAdapter);

        mScreenHeight = this.getWindowManager().getDefaultDisplay().getHeight();
        mLlTextContainer.measure(0, 0);
        mMaxHeight = mScreenHeight - mLlTextContainer.getMeasuredHeight();

        mLlTextContainer.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        mLastY = event.getRawY();
                        break;
                    case MotionEvent.ACTION_MOVE:
                        float nowY = event.getRawY();
                        ViewGroup.LayoutParams params = mGlview.getLayoutParams();
                        params.height += nowY - mLastY;
                        if (params.height > mMaxHeight) {
                            params.height = mMaxHeight;
                        }
                        if (params.height < 200) {
                            params.height = 200;
                        }
                        mLastY = nowY;
                        mGlview.setLayoutParams(params);
                        mGlview.invalidate();

                        break;
                    case MotionEvent.ACTION_UP:

                        break;
                }
                return true;
            }
        });
    }

    private void fullScreen() {
        RelativeLayout.LayoutParams lp = (RelativeLayout.LayoutParams) mGlview
                .getLayoutParams();
        lp.height = RelativeLayout.LayoutParams.MATCH_PARENT;
        mGlview.setLayoutParams(lp);

    }

    /**
     * 根据intent的内容设置参数
     *
     * @param intent
     */
    private void playVRImage(Intent intent) {

        //设置VR图片的两个默认参数 preview和imageurl
        String preViewUrl = "http://fwpano813.img.detuyun.cn/143573747155939d7fe3635/oper/55939dd8f1db1_preview_detunew.jpg";
        String imageUrl = "http://fwpano813.img.detuyun.cn/143573747155939d7fe3635/oper/55939dd8f1db1_html";
        if (intent != null) {
            String stringExtra = intent.getStringExtra(IntentValues.VR_PREVIEW);
            String stringExtra1 = intent.getStringExtra(IntentValues.VR_IMAGE_URL);
            if (!TextUtils.isEmpty(stringExtra) && !TextUtils.isEmpty(stringExtra1)) {
                preViewUrl = stringExtra;
                imageUrl = stringExtra1;
            }
        }
        String url = String.format(mImage_Url_template, preViewUrl, imageUrl);

        play(url);

    }

    private void play(String url) {
        PanoPlayerUrl panoplayerurl = new PanoPlayerUrl();
        panoplayerurl.setXmlContent(url);
        mPlayer.Play(panoplayerurl);
        mPlayer.setGyroEnable(true);
    }

    private void defaultOptions() {
        DisplayImageOptions defaultOptions = new DisplayImageOptions.Builder()
                .imageScaleType(ImageScaleType.NONE).cacheInMemory()
                .cacheOnDisc().build();

        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(
                this).defaultDisplayImageOptions(defaultOptions)
                .threadPriority(Thread.NORM_PRIORITY - 2)
                .denyCacheImageMultipleSizesInMemory()
                .discCacheFileNameGenerator(new Md5FileNameGenerator())
                // .writeDebugLogs()
                .tasksProcessingOrder(QueueProcessingType.FIFO).build();
        ImageLoader.getInstance().init(config);

    }

    private void initData() {
        mData = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            mData.add("数据+" + i);
        }
    }
}
