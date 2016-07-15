package com.example.lhy.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import com.example.lhy.R;
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

public class VRActivity extends Activity {

    static {
        if (!OpenCVLoader.initDebug()) {
        }
    }

    PanoPlayerSurfaceView ppsview;
    private PanoPlayer mPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        defaultOptions();

        ppsview = (PanoPlayerSurfaceView) findViewById(R.id.glview);
        mPlayer = new PanoPlayer(ppsview, this);
        ppsview.setRenderer(mPlayer);
        findViewById(R.id.videolay).setVisibility(View.GONE);

        play();
        fullScreenAndGyro();

        findViewById(R.id.Gyro).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (mPlayer != null) {
                    mPlayer.setGyroEnable(true);
                }
            }
        });


    }

    private void fullScreenAndGyro() {
        RelativeLayout.LayoutParams lp = (RelativeLayout.LayoutParams) ppsview
                .getLayoutParams();
        lp.height = RelativeLayout.LayoutParams.MATCH_PARENT;
        ppsview.setLayoutParams(lp);

    }

    private void play() {
        PanoPlayerUrl panoplayerurl = new PanoPlayerUrl();
        String myURL = "<DetuVr> \n" +
                "\t<settings init=\"pano1\" initmode=\"default\" enablevr=\"false\" title=\"\" /> \n" +
                "\t<scenes> \n" +
                "\t\t<!-- pano --> \n" +
                "\t\t<scene name=\"pano-132079\" title=\"广东现代国际展览中心\" thumburl=\"http://media.qicdn.detu.com/pano256151468285576336023987/thumb/500_500/panofile.jpg\"> \n" +
                "\t\t\t<view hlookat=\"0\" vlookat=\"0\" fov=\"65\" vrfov=\"90\" vrz=\"0.5\" righteye=\"0\" fovmax=\"130\" defovmax=\"95\" viewmode=\"default\" /> \n" +
                "\t\t\t<preview url=\"http://media.qicdn.detu.com/pano256151468285576336023987/oper/panofile_preview_detunew.jpg\" /> \n" +
                "\t\t\t<image type=\"cube\" url=\"http://media.qicdn.detu.com/pano256151468285576336023987/oper/panofile_html_%s.jpg\" /> \n" +
                "\t\t</scene> \n" +
                "\t</scenes> \n" +
                "</DetuVr>";
        panoplayerurl.setXmlContent(myURL);
        mPlayer.Play(panoplayerurl);
        mPlayer.setGyroEnable(true);

    }

    private void defaultOptions() {
        setContentView(R.layout.activity_vr_show);
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
}
